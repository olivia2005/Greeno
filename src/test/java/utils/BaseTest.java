package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

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
    public void setupBrowser(@Optional("firefox") String browser) {
        ConfigReader.setBrowserPropertyValue(browser);
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
    }

    @BeforeMethod
    @Parameters({"testName", "testDescription"})
    public void beforeMethod(@Optional("DefaultTestName") String testName, @Optional("DefaultTestDescription") String testDescription) {
        test = extent.createTest(testName, testDescription);
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
