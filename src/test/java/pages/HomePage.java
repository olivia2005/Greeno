package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import java.time.Duration;
import org.openqa.selenium.JavascriptExecutor;


public class HomePage {
    private WebDriver driver;

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");
    private By firstProductInProduseNoi = By.cssSelector("#content > div.elementor > div.elementor-section.elementor-element.elementor-element-q5307m4.elementor-top-section.elementor-section-boxed.elementor-section-height-default.elementor-section-height-default > div > div > div > div > div > div.elementor-widget.elementor-element.elementor-element-llfv8sj.elementor-widget-prestashop-widget-ProductsList > div > section > div > div > div > div.slick-slide.slick-current.slick-active > div > div > article > div.product-description > h3 > a");


    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods related to search functionality
    public void enterSearchTerm(String searchTerm) {
        driver.findElement(searchInput).sendKeys(searchTerm);
    }

    public void clickSearchButton() {
        driver.findElement(searchButton).click();
    }

    public void searchFor(String searchTerm) {
        enterSearchTerm(searchTerm);
        clickSearchButton();
    }

    // Method to navigate to the first product in Produse Noi
    public ProductDetailsPage clickFirstProductInProduseNoi() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(firstProductInProduseNoi));

        // Scroll the element into view using JavaScript
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);

        // Provide a slight delay after scrolling to ensure the page has settled.
        try {
            Thread.sleep(1000);  // This is just a small delay of 1 second
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        wait.until(ExpectedConditions.visibilityOf(element));
        element.click();

        return new ProductDetailsPage(driver);
    }


}
