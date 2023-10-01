package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductDetailsPage;
import pages.WishListPage;
import utils.BaseTest;
import utils.DatabaseUtils;

import java.time.Duration;

public class WishlistTest extends BaseTest {

    private LoginPage loginPage;
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private WishListPage wishListPage; // Declare WishListPage object

    @BeforeMethod
    public void setupPages() {
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);  // Initialize ProductDetailsPage object
        wishListPage = new WishListPage(driver); // Initialize WishListPage object
    }

    @Test
    public void addToWishlistAndRemove() throws InterruptedException {
        driver.get("https://greeno.ro/ro/autentificare?back=my-account");

        // Fetch login credentials from the database and login
        String[] credentials = DatabaseUtils.getLoginCredentials();
        loginPage.enterEmail(credentials[0]);
        loginPage.enterPassword(credentials[1]);
        loginPage.clickLoginButton();

        acceptCookies();

        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait

        // Search for the term and click on the first result
        homePage.searchFor("minoxidil");

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        acceptCookies();

        // Wait for the search results to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Using methods from ProductDetailsPage to interact with the page
        productDetailsPage.clickSecondProduct();  // Clicks the second product

        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait
        acceptCookies();

        // Wait for the 'Add to Wishlist' button to be present and click on it
        productDetailsPage.clickAddToWishlist();
        Thread.sleep(5000);  // 5 seconds wait

        // Navigate to wishlist
        wishListPage.clickNavigateToWishlist(wait);
        Thread.sleep(5000);  // 5 seconds wait

        // Remove the product from the wishlist
        wishListPage.clickRemoveProductFromWishlist(wait);
        Thread.sleep(5000);  // 5 seconds wait

        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait

        // Logout using JavaScript
        WebElement logoutButton = driver.findElement(By.cssSelector("a.col-lg-4:nth-child(11) > span:nth-child(1)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);
        Thread.sleep(5000);  // 5 seconds wait
    }


}
