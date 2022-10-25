package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.time.Duration.ofSeconds;

public class WomenSFashionFromDailyRitualPage extends BasePage {

    public WomenSFashionFromDailyRitualPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//span[contains(text(),'Up to $25')]")
    public WebElement filterButtonUpTo25;
    final String buttonForTheResultText = "//div[contains(@class, 'pageNum')]/span";
    @FindBy(xpath = buttonForTheResultText)
    protected WebElement resultText;
    @FindBy(xpath = "//span[text()='price']/parent::span")
    protected List<WebElement> listOfValuesButton;

    public String getTextAboutTheNumberOfResults() {
        return resultText.getText();
    }

    public void clickOnFilterButtonUpTo25() {
        String textAboutTheNumberOfResultsBeforeFiltering = getTextAboutTheNumberOfResults();
        filterButtonUpTo25.click();
        new WebDriverWait(driver, ofSeconds(20)).
                until(ExpectedConditions.invisibilityOfElementWithText
                        (By.xpath(buttonForTheResultText), textAboutTheNumberOfResultsBeforeFiltering));
    }

    protected List<String> valuesAsString = new ArrayList<>();

    public void getValuesAsString() {
        for (WebElement el : listOfValuesButton)
            valuesAsString.add(el.getText().replaceAll("\\$", "").replaceAll("\n", ""));
    }

    protected List<Integer> listOfValues;

    public void getTheValuesInCents() {
        getValuesAsString();
        listOfValues = valuesAsString.stream()
                .map(Integer::valueOf).collect(Collectors.toList());
    }

    public boolean makeSureTheValuesAreEqualToOrLessThan2500Cents() {
        getTheValuesInCents();
        for (int i = 0; i < listOfValues.size(); i++) {
            if ((listOfValues.get(i)) > 2500)
                return false;
        }
        return true;
    }

    public void waitForWomenSFashionFromDailyRitualPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.visibilityOfAllElements(listOfValuesButton));
        wait.until(ExpectedConditions.elementToBeClickable(filterButtonUpTo25));
    }
}


