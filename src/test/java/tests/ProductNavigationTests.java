package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductDetailsPage;
import utils.BaseTest;
import org.openqa.selenium.TimeoutException;

import java.time.Duration;

public class ProductNavigationTests extends BaseTest {

    HomePage homePage;

    @BeforeMethod
    public void setup() {
        // Navigate to the homepage of the website
        driver.get("https://greeno.ro/ro/");

        // Initializing HomePage
        homePage = new HomePage(driver);

        // Wait for the cookie banner and accept it if it appears
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("iqitcookielaw-accept")));
            acceptCookiesButton.click();
        } catch (TimeoutException e) {
            // Cookie banner did not appear within 10 seconds, so continue with the test
        }

        try {
            WebElement promoCloseButton = new WebDriverWait(driver, Duration.ofSeconds(10))
                    .until(ExpectedConditions.visibilityOfElementLocated(By.id("hide_promo")));
            promoCloseButton.click();
        } catch (TimeoutException e) {
            System.out.println("Promotion banner close button not found or not clickable.");
        }

        // Optional: Scroll down a bit to ensure the target element is in view
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,500)", "");
    }

    @Test
    public void testNavigateToProduseNoiAndOpenFirstProduct() {
        ProductDetailsPage productDetailsPage = homePage.clickFirstProductInProduseNoi();

        // Pause for 5 seconds to see what happened
        try {
            Thread.sleep(5000);  // Pausing for 5 seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Verify that you're on the correct product details page
        // This will be based on some elements or details unique to that page.
        // Assertions can be added here to confirm the product page details.
    }
}
