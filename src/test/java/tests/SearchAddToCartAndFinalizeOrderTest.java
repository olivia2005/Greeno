package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import pages.HomePage;
import pages.LoginPage;
import pages.ShoppingCartPage;
import pages.ProductDetailsPage;
import java.time.Duration;

public class SearchAddToCartAndFinalizeOrderTest extends BaseTest {
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private LoginPage loginPage;  // Declare LoginPage object
    private ShoppingCartPage shoppingCartPage;  // Declare ShoppingCartPage object
    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");
    private By addToCartButton = By.cssSelector("button.btn-primary:nth-child(1)");
    private By finalizeOrderButton = By.cssSelector(".cart-content-btn > a:nth-child(1)");

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
    public void searchMinoxidilAddToCartAndFinalizeOrder() throws InterruptedException {

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

        // Introducing a 5-second wait after clicking the second product
        try {
            Thread.sleep(5000);  // 5 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();

        // Wait for the add to cart button and click it
        // Use method from ProductDetailsPage to click the Add to Cart button
        productDetailsPage.clickAddToCart();

        // Introducing a 5-second wait after adding to cart
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Wait for the finalize order button and click it
        shoppingCartPage.clickFinalizeOrder(wait);

        // Introducing a 5-second wait after finalizing the order
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Add any assertions or further actions as needed
    }
}
