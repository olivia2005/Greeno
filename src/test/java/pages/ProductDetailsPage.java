package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProductDetailsPage {

    private WebDriver driver;

    // Web elements
    private By secondProduct = By.xpath("//h3[@class='h3 product-title']/a[contains(@href, 'solutie-1-luna-equate-minoxidil-2-tratament-impotriva-caderii-parului-1x-60ml')]");
    private By addToCartButton = By.xpath("//div[@class='add']/button[contains(@class,'add-to-cart')]");
    private By addToWishlistButton = By.xpath("//button[contains(@class,'btn-iqitwishlist-add')]");

    // Constructor
    public ProductDetailsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the page
    public void clickSecondProduct() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(secondProduct));
        element.click();
    }

    public void clickAddToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        element.click();
    }
    public void clickAddToWishlist() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(addToWishlistButton));
        element.click();
    }
}
