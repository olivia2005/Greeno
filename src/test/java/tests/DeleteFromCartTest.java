package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductDetailsPage;
import utils.BaseTest;
import pages.HomePage;  // Import the HomePage class
import java.time.Duration;
import pages.ShoppingCartPage;
public class DeleteFromCartTest extends BaseTest {
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private LoginPage loginPage;  // Declare LoginPage object
    private ShoppingCartPage shoppingCartPage;  // Declare ShoppingCartPage object
    // Web elements

    @BeforeMethod
    public void setupTest() {
        driver.get("https://greeno.ro");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);  // Initialize ProductDetailsPage object
        loginPage = new LoginPage(driver);  // Initialize LoginPage object
        shoppingCartPage = new ShoppingCartPage(driver);  // Initialize ShoppingCartPage object
    }

    @Test
    public void searchMinoxidilAddToCartDeleteAndCloseCart() throws InterruptedException {
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
        // Use method from ProductDetailsPage to click the Add to Cart button
        productDetailsPage.clickAddToCart();

        // Introducing a 5-second wait after adding to cart
        try {
            Thread.sleep(5000);  // 5 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // Wait for the continue shopping button and click it
        shoppingCartPage.clickContinueShopping(wait);

        // Introducing a 5-second wait after continuing shopping
        Thread.sleep(5000);  // 5 seconds in milliseconds

        // Click on the shopping cart button
        shoppingCartPage.clickCartButton(wait);
        // Wait for 5 seconds
        Thread.sleep(5000);

        // Click on the trashcan icon to delete the product
        shoppingCartPage.clickTrashCanIcon(wait);

        // Wait for 5 seconds
        Thread.sleep(5000);

        // Click on the 'x' to close the shopping cart
        shoppingCartPage.clickCloseShoppingCart(wait);

        // Wait for another 5 seconds for presentation purposes
        Thread.sleep(5000);
    }
}
