package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.BaseTest;

import java.time.Duration;

public class SearchAndClickFirstResultTest extends BaseTest {

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");

    @BeforeMethod
    public void setup() {
        // Navigate to the homepage of the website
        driver.get("https://greeno.ro");
    }

    @Test
    public void searchMinoxidilAndClickFirstResult() {

        // Input the search term and click the search button.
        driver.findElement(searchInput).sendKeys("minoxidil");
        driver.findElement(searchButton).click();

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
