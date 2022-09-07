import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.HomePage;
import pages.SearchResultPage;

public class TestAmazon {
    @Test
    public void testOne() {
        DriverFactory.initDriver(BrowserType.CHROME);
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getDeliveryAddress(), "Armenia");
        homePage.chooseCategory();
        SearchResultPage searchResultPage = new SearchResultPage(DriverFactory.getDriver());
        searchResultPage.waitForSearchResultPageLoaded();
        softAssert.assertEquals(searchResultPage.getSearchResultText().replace('"', ' '), " Java ");
        softAssert.assertEquals("16", searchResultPage.getCountInPage().toString());
        softAssert.assertAll();
    }
    @AfterTest
    public void tearDown() {
        WebDriver driver = DriverFactory.getDriver();
        if (driver != null) {
            driver.quit();
        }
    }
}

