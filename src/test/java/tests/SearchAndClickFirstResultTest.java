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
import utils.BaseTest;

import java.time.Duration;

public class SearchAndClickFirstResultTest extends BaseTest {
    private HomePage homePage;
    private ProductDetailsPage productDetailsPage;  // Declare ProductDetailsPage object
    private LoginPage loginPage;  // Declare LoginPage object

    @BeforeMethod
    public void setup() {
        // Navigate to the homepage of the website
        driver.get("https://greeno.ro");
        homePage = new HomePage(driver);
        productDetailsPage = new ProductDetailsPage(driver);  // Initialize ProductDetailsPage object
        loginPage = new LoginPage(driver);  // Initialize LoginPage object
    }

    @Test
    public void searchMinoxidilAndClickFirstResult() {

        // Input the search term and click the search button.
        homePage.searchFor("minoxidil");
        homePage.clickSearchButton();

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        acceptCookies();

        // Wait for the search results to appear
        openProductPage("Solutie 3 Luni, Equate, Minoxidil");

        System.out.println("done!");


        // Add any assertions or further actions as needed
    }
    private void openProductPage(String productName){
        String productXpath="//h3[@class='h3 product-title']/a[contains(text(),'"+productName+"')]";
        WebElement product=driver.findElement(By.xpath(productXpath));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
       wait.until(ExpectedConditions.elementToBeClickable(product));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", product);
    }
}
