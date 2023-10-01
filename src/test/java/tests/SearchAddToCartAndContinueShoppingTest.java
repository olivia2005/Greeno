package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import pages.HomePage;
import pages.ShoppingCartPage;
import pages.ProductDetailsPage;
import java.time.Duration;

public class SearchAddToCartAndContinueShoppingTest extends BaseTest {
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private LoginPage loginPage;  // Declare LoginPage object
    private ShoppingCartPage shoppingCartPage;  // Declare ShoppingCartPage object

    @BeforeMethod
    public void setup() {
        // Navigate to the homepage of the website
        driver.get("https://greeno.ro");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);  // Initialize ProductDetailsPage object
        loginPage = new LoginPage(driver);  // Initialize LoginPage object
        shoppingCartPage = new ShoppingCartPage(driver);  // Initialize ShoppingCartPage object
    }

    @Test
    public void searchMinoxidilAddToCartAndContinueShopping() throws InterruptedException {

        // Input the search term and click the search button.
        homePage.searchFor("minoxidil");
        homePage.clickSearchButton();

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        acceptCookies();

        // Wait for the search results to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Using methods from ProductDetailsPage to interact with the page
        productDetailsPage.clickSecondProduct();  // Clicks the second product

        // Wait for the add to cart button and click it
        // Use method from ProductDetailsPage to click the Add to Cart button
        productDetailsPage.clickAddToCart();

        // Introducing a 5-second wait after adding to cart
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Wait for the continue shopping button and click it
        shoppingCartPage.clickContinueShopping(wait);

        // Introducing a 5-second wait after continuing shopping
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Add any assertions or further actions as needed
    }
}
