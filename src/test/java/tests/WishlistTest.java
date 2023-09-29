package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.BrowserFactory;
import utils.DatabaseUtils;
import java.time.Duration;

public class WishlistTest {

    private WebDriver driver;
    private LoginPage loginPage;
    private HomePage homePage;
    private By firstProductImage = By.cssSelector("img[alt='Spuma 9 Luni, Kirkland,...']");

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
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

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait

        // Search for the term and click on the first result
        homePage.searchFor("minoxidil");

        // Wait for the search results to appear
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement firstResult = wait.until(ExpectedConditions.visibilityOfElementLocated(firstProductImage));

        // Use JavaScript to click on the element
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", firstResult);

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait

        // Wait for the 'Add to Wishlist' button to be present and use JavaScript to click on it
        WebElement addToWishlistButton = new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#iqit-wishlist-product-btn")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addToWishlistButton);
        Thread.sleep(5000);  // 5 seconds wait

        // Navigate to wishlist and wait
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.d-inline-block:nth-child(3) > a:nth-child(1)"))).click();
        Thread.sleep(5000);  // 5 seconds wait

        // Remove the product from the wishlist and wait
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(By.cssSelector(".fa-trash-o"))).click();
        Thread.sleep(5000);  // 5 seconds wait

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();
        Thread.sleep(5000);  // 5 seconds wait

        // Logout using JavaScript
        WebElement logoutButton = driver.findElement(By.cssSelector("a.col-lg-4:nth-child(11) > span:nth-child(1)"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutButton);

        // Pause for 5 seconds for visibility
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    // A method to accept the cookies banner if it is present
    private void acceptCookies() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        try {
            WebElement acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("iqitcookielaw-accept")));
            acceptCookiesButton.click();
        } catch (Exception e) {
            // Cookie banner did not appear, so continue with the test
        }
    }
}
