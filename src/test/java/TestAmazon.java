import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.*;

public class TestAmazon {
    @BeforeMethod
    public void initDriver() {
        DriverFactory.initDriver(BrowserType.CHROME);
    }

    @Test
    public void testForChoosingAmazonCategory() {
        String textForSearch = "Java";
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(homePage.getDeliveryAddress(), "Armenia");
        homePage.chooseCategory("Books");
        homePage.searchText(textForSearch);
        SearchResultPage searchResultPage = new SearchResultPage(DriverFactory.getDriver());
        searchResultPage.waitForSearchResultPageLoaded();
        searchResultPage.getSearchResultText();
        softAssert.assertEquals(searchResultPage.getSearchResultText().replaceAll("\"", ""), textForSearch);
        softAssert.assertEquals(searchResultPage.getCountInPage(), searchResultPage.getNumberOfResultInPage());
        softAssert.assertAll();
    }

    @Test
    public void testForCheckAllRatings() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        homePage.clickTheAllButton();
        homePage.selectCategory();
        SelectedCategoryPage selectedCategoryPage = new SelectedCategoryPage(DriverFactory.getDriver());
        selectedCategoryPage.waitForSearchResultPageLoaded();
        SelectRandomItemPage selectRandomItemPage = new SelectRandomItemPage(DriverFactory.getDriver());
        selectRandomItemPage.waitForSelectRandomItemPageLoaded();
        selectRandomItemPage.clickRandomItem();
        AllRatingsPage allRatingsPage = new AllRatingsPage(DriverFactory.getDriver());
        allRatingsPage.waitForAllRatingsPageLoaded();
        allRatingsPage.clickAllRatings();
        allRatingsPage.waitForAllRatingsOpened();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(allRatingsPage.sumOfAllRatings(), 100);
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

