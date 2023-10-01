package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;
import pages.ProductDetailsPage;  // Import the ProductDetailsPage class
import pages.HomePage;  // Import the HomePage class
import java.time.Duration;
import pages.LoginPage;
public class AddToCartTest extends BaseTest {

    private HomePage homePage;  // Declare HomePage object
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private LoginPage loginPage;  // Declare LoginPage object
    @BeforeMethod
    public void setupTest() {
        driver.get("https://greeno.ro");
        homePage = new HomePage(driver);  // Initialize HomePage object
        productDetailsPage = new ProductDetailsPage(driver);  // Initialize ProductDetailsPage object
        loginPage = new LoginPage(driver);  // Initialize LoginPage object
    }

    @Test
    public void searchMinoxidilAndAddToCart() {
        // Using methods from HomePage to perform the search
        homePage.searchFor("minoxidil");
        acceptCookies();

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();

        // Using methods from ProductDetailsPage to interact with the page
        productDetailsPage.clickSecondProduct();  // Clicks the second product

        // Introducing a 5-second wait after clicking the second product
        try {
            Thread.sleep(5000);  // 5 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Use method from ProductDetailsPage to click the Add to Cart button
        productDetailsPage.clickAddToCart();

        // Introducing a 5-second wait after adding to cart
        try {
            Thread.sleep(5000);  // 5 seconds in milliseconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // You can also add assertions here to check if the item was successfully added to the cart.
    }

}
