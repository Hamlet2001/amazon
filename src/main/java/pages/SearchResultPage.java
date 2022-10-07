package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchResultPage extends BasePage {

    public SearchResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "span[class='a-color-state a-text-bold']")
    protected WebElement searchResult;
    @FindBy(xpath = "//span[contains(text(),'results')]")
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
