package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static java.time.Duration.ofSeconds;

public class TodaySDealsPage extends BasePage {

    public TodaySDealsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "Select All")
    protected WebElement selectAllButton;
    @FindBy(css = "input[type='checkbox']")
    protected List<WebElement> listOfFilterCheckboxes;
    @FindBy(linkText = "Clear")
    protected WebElement clearButton;
    @FindBy(xpath = "//div[contains(@class, 'Content')]//a[contains(@class, 'Card')]")
    protected List<WebElement> listOfFilterItems;

    public String getTextFromSelectAllButton() {
        return selectAllButton.getText();
    }

    protected String inputSelector = "//span[text()='%s']/parent::label/input";

    public void selectTwoFilterItems(String filterItemOne, String filterItemTwo) {
        driver.findElement(By.xpath(String.format(inputSelector, filterItemOne))).click();
        driver.findElement(By.xpath(String.format(inputSelector, filterItemTwo))).click();
    }

    public String getTextFromClearButton() {
        return clearButton.getText();
    }

    public boolean isSelectedTwoFilterCheckboxes(String filterItemOne, String filterItemTwo) {
        return (driver.findElement(By.xpath(String.format(inputSelector, filterItemOne))).isSelected() &&
                driver.findElement(By.xpath(String.format(inputSelector, filterItemTwo))).isSelected());
    }

    public void clickOnClearButton() {
        clearButton.click();
    }

    public boolean anyCheckboxIsSelected() {
        for (WebElement el : listOfFilterCheckboxes) {
            if (el.isSelected()) {
                return true;
            }
        }
        return false;
    }

    public void waitForTwoFilterItemsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, ofSeconds(15));
        wait.until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                if (listOfFilterItems.size() > 0)
                    return true;
                else
                    return false;
            }
        });
        new WebDriverWait(driver, ofSeconds(20)).
                until(ExpectedConditions.elementToBeClickable(listOfFilterItems.get(listOfFilterItems.size() - 1)));
    }

    public void waitTodaySDealsPageLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains("Today's Deals"));
        wait.until(ExpectedConditions.elementToBeClickable(selectAllButton));
    }
}





