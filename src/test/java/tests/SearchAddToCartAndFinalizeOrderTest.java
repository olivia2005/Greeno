package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BrowserFactory;

import java.time.Duration;

public class SearchAddToCartAndFinalizeOrderTest {

    private WebDriver driver;

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");
    private By addToCartButton = By.cssSelector("button.btn-primary:nth-child(1)");
    private By finalizeOrderButton = By.cssSelector(".cart-content-btn > a:nth-child(1)");

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
    }

    @Test
    public void searchMinoxidilAddToCartAndFinalizeOrder() throws InterruptedException {
        driver.get("https://greeno.ro");

        // Input the search term and click the search button.
        driver.findElement(searchInput).sendKeys("minoxidil");
        driver.findElement(searchButton).click();

        // Wait for the search results to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductImage));

        // Use JavaScript to click on the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);

        // Wait for the add to cart button and click it
        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartButton));
        addToCart.click();

        // Introducing a 5-second wait after adding to cart
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Wait for the finalize order button and click it
        WebElement finalizeOrder = wait.until(ExpectedConditions.elementToBeClickable(finalizeOrderButton));
        finalizeOrder.click();

        // Introducing a 5-second wait after finalizing the order
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Add any assertions or further actions as needed
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
