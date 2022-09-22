package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage {
    WebDriver driver;

    public SearchResultPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span[class='a-color-state a-text-bold']")
    protected WebElement searchResult;
    @FindBy(xpath = "//*[@id=\"search\"]/span/div/h1/div/div[1]/div/div/span[1]")
    protected WebElement numberOfResultsInPage;
    @FindBy(css = "div[data-component-type='s-search-result']")
    protected List<WebElement> listOfResults;

    public String getSearchResultText() {
        return searchResult.getText();
    }

    public int getNumberOfResultInPage() {
        String string = numberOfResultsInPage.getText();
        Pattern pattern = Pattern.compile("(?<=1-)\\d+(?= of)");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        return Integer.parseInt(matcher.group());
    }

    public int getCountInPage() {
        return listOfResults.size();
    }

    public void waitForSearchResultPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOf(searchResult));
    }
}
