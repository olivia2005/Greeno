package tests;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BrowserFactory;
import utils.JsonDataReader;

import java.util.Iterator;

public class SearchFunctionalityTest {

    private WebDriver driver;
    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        driver = BrowserFactory.getDriver();
        homePage = new HomePage(driver);
        driver.get("https://greeno.ro/ro/");
    }

    @DataProvider(name = "searchTerms")
    public Iterator<Object[]> searchDataProvider() {
        return JsonDataReader.readTestData().getSearchTerms().stream()
                .map(term -> new Object[]{term})
                .iterator();
    }

    @Test(dataProvider = "searchTerms")
    public void testSearchFunctionality(String searchTerm) {
        homePage.searchFor(searchTerm);
        // TODO: Assert or validate the results if necessary.
        // For instance, you might want to check if the results page has results.
    }

    @AfterMethod
    public void tearDown() {
        BrowserFactory.quitDriver();
    }
}
