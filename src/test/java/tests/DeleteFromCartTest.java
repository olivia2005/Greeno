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

public class DeleteFromCartTest {

    private WebDriver driver;

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");
    private By addToCartButton = By.cssSelector("#add-to-cart-or-refresh > div.product-add-to-cart.pt-3 > div > div.col.col-12.col-sm-auto.col-add-btn > div > button");
    private By continueShoppingButton = By.cssSelector("button.btn-secondary:nth-child(2)");
    private By cartButton = By.cssSelector("#cart-toogle > i:nth-child(1)");
    private By trashCanIcon = By.cssSelector(".fa-trash-o");
    private By closeShoppingCart = By.cssSelector("#js-cart-close");

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
    }

    @Test
    public void searchMinoxidilAddToCartDeleteAndCloseCart() throws InterruptedException {
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

        // Wait for the continue shopping button and click it
        WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShopping.click();

        // Introducing a 5-second wait after continuing shopping
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Click on the shopping cart button
        WebElement cartBtn = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cartBtn.click();

        // Wait for 5 seconds
        Thread.sleep(5000);

        // Click on the trashcan icon to delete the product
        WebElement trashCan = wait.until(ExpectedConditions.elementToBeClickable(trashCanIcon));
        trashCan.click();

        // Wait for 5 seconds
        Thread.sleep(5000);

        // Click on the 'x' to close the shopping cart
        WebElement closeCartBtn = wait.until(ExpectedConditions.elementToBeClickable(closeShoppingCart));
        closeCartBtn.click();

        // Wait for another 5 seconds for presentation purposes
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
