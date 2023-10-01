package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;  // Adding WebDriver instance
    protected static ExtentReports extent;
    protected ExtentTest test;

    @BeforeSuite
    public void setUp() {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReports.html");
        extent = new ExtentReports();
        extent.attachReporter(spark);
    }

    @BeforeTest  // Executed before each <test> tag in your XML
    @Parameters("browser")
    public void setupTest(@Optional("firefox") String browser) {
        if (driver == null) {
            setupBrowser(browser);
        }
    }


    public void setupBrowser(String browser) {
        ConfigReader.setBrowserPropertyValue(browser);
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @Parameters({"testName", "testDescription", "browser"})
    public void beforeMethod(@Optional("DefaultTestName") String testName, @Optional("DefaultTestDescription") String testDescription, @Optional("firefox") String browser) {
        test = extent.createTest(testName, testDescription);
        if (driver == null) {
            setupBrowser(browser);
        }
    }

    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            test.log(Status.FAIL, "Test Case Failed is " + result.getName());
        } else if (result.getStatus() == ITestResult.SKIP) {
            test.log(Status.SKIP, "Test Case Skipped is " + result.getName());
        } else {
            test.log(Status.PASS, "Test passed");
        }
    }
    // A method to accept the cookies banner if it is present
    protected void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("iqitcookielaw-accept")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Cookie banner did not appear, so continue with the test
        }
    }
    @AfterTest  // Executed after each <test> tag in your XML
    public void tearDownBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
    }
}
