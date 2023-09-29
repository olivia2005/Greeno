package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    // Element selectors
    private By emailField = By.cssSelector("#login-form > section:nth-child(1) > div:nth-child(2) > div:nth-child(2) > input:nth-child(1)");
    private By passwordField = By.cssSelector("#login-form > section:nth-child(1) > div:nth-child(3) > div:nth-child(2) > div:nth-child(1) > input:nth-child(1)");
    private By loginButton = By.cssSelector("#login-form > footer:nth-child(2) > button:nth-child(2)");
    private By hidePromo = By.cssSelector("#hide_promo");
    private By logoutButton = By.cssSelector("a.col-lg-4:nth-child(11) > span:nth-child(1)");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    public void clickHidePromoIfExists() {
        if(driver.findElements(hidePromo).size() > 0) {
            driver.findElement(hidePromo).click();
        }
    }

    public void clickLogoutButton() {
        driver.findElement(logoutButton).click();
    }
}
