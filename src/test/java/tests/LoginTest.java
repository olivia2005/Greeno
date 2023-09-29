package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BrowserFactory;
import utils.DatabaseUtils;

public class LoginTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.startBrowser();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
    }

    @Test
    public void loginUserFromDatabase() throws InterruptedException {
        driver.get("https://greeno.ro/ro/autentificare?back=my-account");

        // Fetch login credentials from the database
        String[] credentials = DatabaseUtils.getLoginCredentials();

        // Use the credentials to perform login
        loginPage.enterEmail(credentials[0]);
        loginPage.enterPassword(credentials[1]);
        loginPage.clickLoginButton();

        // Pause for 5 seconds for visibility
        Thread.sleep(5000);

        // Click on the #hide_promo if present
        loginPage.clickHidePromoIfExists();

        // Logout
        loginPage.clickLogoutButton();

        // Pause for 5 seconds for visibility
        Thread.sleep(5000);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
