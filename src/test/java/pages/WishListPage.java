package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WishListPage {
    private WebDriver driver;

    // Web elements
    private By navigateToWishlistButton = By.cssSelector("div.d-inline-block:nth-child(3) > a:nth-child(1)");
    private By removeProductFromWishlistButton = By.cssSelector(".js-iqitwishlist-remove");

    // Constructor
    public WishListPage(WebDriver driver) {
        this.driver = driver;
    }

    // Methods to interact with the page
    public void clickNavigateToWishlist(WebDriverWait wait) {
        WebElement navigateButton = wait.until(ExpectedConditions.elementToBeClickable(navigateToWishlistButton));
        navigateButton.click();
    }

    public void clickRemoveProductFromWishlist(WebDriverWait wait) {
        WebElement removeButton = wait.until(ExpectedConditions.elementToBeClickable(removeProductFromWishlistButton));
        removeButton.click();
    }
}
