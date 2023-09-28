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

public class AddToCartTest {

    private WebDriver driver;

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");
    private By addToCartButton = By.cssSelector("button.btn-primary.btn-lg.add-to-cart");

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
    }

    @Test
    public void searchMinoxidilAndAddToCart() {
        driver.get("https://greeno.ro");

        // Input the search term and click the search button.
        driver.findElement(searchInput).sendKeys("minoxidil");
        driver.findElement(searchButton).click();

        // Wait for the search results to appear and click on the first product image.
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductImage));

        // Use JavaScript to click on the first result
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);

        // Wait for the "Add to Cart" button to appear and click it.
        WebElement addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        addToCart.click();
        // Introducing a 10-second wait after adding to cart
        try {
            Thread.sleep(5000);  // 10 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // You can also add assertions here to check if the item was successfully added to the cart.
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
