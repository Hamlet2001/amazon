import initDriver.BrowserType;
import initDriver.DriverFactory;
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

    public void selectCategory() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        SelectCategoryPage selectedCategoryPage = new SelectCategoryPage(DriverFactory.getDriver());
        selectedCategoryPage.clickTheAllButton();
        selectedCategoryPage.selectCategoryOne("Smart Home");
        selectedCategoryPage.selectCategoryTwo("Smart Home Lighting");
    }

    public void openHomePageAndClickOnTodaySDealButton() {
        HomePage homePage = new HomePage(DriverFactory.getDriver());
        homePage.openHomePage();
        homePage.waitForHomePageLoaded();
        homePage.clickOnTodaySDeals();
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
        selectCategory();
        RandomItemPage selectRandomItemPage = new RandomItemPage(DriverFactory.getDriver());
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

    @Test
    public void checkPaginationAndHoveringOnBestSeller() {
        selectCategory();
        AllResultsPage allResultsPage = new AllResultsPage(DriverFactory.getDriver());
        allResultsPage.waitForAllResultsPageLoaded();
        allResultsPage.clickOnAllResultsButton();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertTrue(allResultsPage.checkForIsDisplayedPagination());
        if (allResultsPage.getCountOfBestSellers() > 0) {
            softAssert.assertFalse(allResultsPage.hoverToBestSeller(2).isEmpty());
        }
        softAssert.assertAll();
    }

    @Test
    public void checkFilteringFunctionality() {
        openHomePageAndClickOnTodaySDealButton();
        TodaySDealsPage todaySDealsPage = new TodaySDealsPage(DriverFactory.getDriver());
        todaySDealsPage.waitTodaySDealsPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Select All", todaySDealsPage.getTextFromSelectAllButton());
        String filterItemOne = "Baby";
        String filterItemTwo = "Electronics";
        todaySDealsPage.selectTwoFilterItems(filterItemOne, filterItemTwo);
        todaySDealsPage.waitForTwoFilterItemsLoaded();
        softAssert.assertTrue(todaySDealsPage.isSelectedTwoFilterCheckboxes(filterItemOne, filterItemTwo));
        softAssert.assertEquals("Clear", todaySDealsPage.getTextFromClearButton());
        todaySDealsPage.clickOnClearButton();
        softAssert.assertFalse(todaySDealsPage.anyCheckboxIsSelected(), "Any checkbox was selected");
        softAssert.assertAll();
    }

    @Test
    public void filterFunctionality() {
        openHomePageAndClickOnTodaySDealButton();
        TodaySDealsPage todaySDealsPage = new TodaySDealsPage(DriverFactory.getDriver());
        todaySDealsPage.waitTodaySDealsPageLoaded();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals("Select All", todaySDealsPage.getTextFromSelectAllButton());
        todaySDealsPage.clickOnWomenSFashionRitual();
        WomenSFashionFromDailyRitualPage womenSFashionFromDailyRitualPage = new WomenSFashionFromDailyRitualPage(DriverFactory.getDriver());
        womenSFashionFromDailyRitualPage.waitForWomenSFashionFromDailyRitualPageLoaded();
        womenSFashionFromDailyRitualPage.clickOnFilterButtonUpTo25();
        softAssert.assertTrue(womenSFashionFromDailyRitualPage.makeSureTheValuesAreEqualToOrLessThan2500Cents());
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

