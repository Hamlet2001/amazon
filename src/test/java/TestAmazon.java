import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SearchResultPage;

public class TestAmazon {
    @BeforeMethod
    public void initDriver() {
        DriverFactory.initDriver(BrowserType.CHROME);
    }

    @Test
    public void testOne() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getDeliveryAddress(), "Armenia");
        homePage.chooseCategory();
        homePage.searchText(homePage.SEARCH_TEXT);
        SearchResultPage searchResultPage = new SearchResultPage(DriverFactory.getDriver());
        searchResultPage.waitForSearchResultPageLoaded();
        softAssert.assertEquals(searchResultPage.getSearchResultText().replaceAll("\"", ""), homePage.SEARCH_TEXT);
        softAssert.assertEquals(searchResultPage.getCountInPage(), searchResultPage.getNumberOfResultInPage());
        softAssert.assertAll();
    }

    @AfterMethod
    public void tearDown() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}

