package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchResultPage {
    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

    @FindBy(xpath = "//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[3]")
    protected WebElement searchResult;
    @FindBy(xpath = "//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[1]")
    protected WebElement numberOfResultsInPage;
    @FindBy(xpath = "//div[@class='s-result-item s-asin sg-col-0-of-12 sg-col-16-of-20 sg-col s-widget-spacing-small sg-col-12-of-16']")
    protected List<WebElement> listOfResults;
    @FindBy(xpath = "//*[@id=\"nav-logo-sprites\"]")
    protected WebElement amazonLogo;

    public String getSearchResultText() {
        return searchResult.getText();
    }

    public String getNumberOfResultInPage() {
        return numberOfResultsInPage.getText();
    }

    public Integer getCountInPage() {
        return listOfResults.size();
    }

    public void waitForSearchResultPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(amazonLogo));
    }
}
