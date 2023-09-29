package tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.BaseTest;
import utils.JsonDataReader;

import java.util.Iterator;

public class SearchFunctionalityTest extends BaseTest {

    private HomePage homePage;

    @BeforeMethod
    public void setup() {
        // Navigate to the homepage of the website
        driver.get("https://greeno.ro/ro/");
        homePage = new HomePage(driver);
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
}
