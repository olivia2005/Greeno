package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    // Constructor
    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Web elements
    private By searchInput = By.name("s");
    private By searchButton = By.cssSelector(".search-btn");

    // Methods
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
}
