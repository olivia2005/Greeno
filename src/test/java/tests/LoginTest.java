package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;
import utils.BaseTest;
import utils.DatabaseUtils;

public class LoginTest extends BaseTest {

    private LoginPage loginPage;

    @BeforeMethod
    public void setupTest() {
        driver.get("https://greeno.ro/ro/autentificare?back=my-account");
        loginPage = new LoginPage(driver);  // This line ensures that the driver is initialized before using it
    }

    @Test
    public void loginUserFromDatabase() throws InterruptedException {
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
}
