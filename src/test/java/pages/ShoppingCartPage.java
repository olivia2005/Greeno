package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ShoppingCartPage {
    WebDriver driver;

    public ShoppingCartPage(WebDriver driver) {
        this.driver = driver;
    }

    private By continueShoppingButton = By.cssSelector("button.btn-secondary:nth-child(2)");
    private By cartButton = By.cssSelector("#cart-toogle > i:nth-child(1)");
    private By trashCanIcon = By.cssSelector(".fa-trash-o");
    private By closeShoppingCart = By.cssSelector("#js-cart-close");
    private By finalizeOrderButton = By.cssSelector(".cart-content-btn > a:nth-child(1)");  // Added this line

    public void clickContinueShopping(WebDriverWait wait) {
        WebElement continueShopping = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueShopping.click();
    }

    public void clickCartButton(WebDriverWait wait) {
        WebElement cart = wait.until(ExpectedConditions.elementToBeClickable(cartButton));
        cart.click();
    }

    public void clickTrashCanIcon(WebDriverWait wait) {
        WebElement trashCan = wait.until(ExpectedConditions.elementToBeClickable(trashCanIcon));
        trashCan.click();
    }

    public void clickCloseShoppingCart(WebDriverWait wait) {
        WebElement closeCart = wait.until(ExpectedConditions.elementToBeClickable(closeShoppingCart));
        closeCart.click();
    }

    public void clickFinalizeOrder(WebDriverWait wait) {  // Added this method
        WebElement finalizeOrder = wait.until(ExpectedConditions.elementToBeClickable(finalizeOrderButton));
        finalizeOrder.click();
    }
}
