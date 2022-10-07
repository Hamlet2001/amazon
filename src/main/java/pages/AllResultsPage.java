package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AllResultsPage extends BasePage {

    public AllResultsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(linkText = "See all results")
    protected WebElement allResultsButton;
    @FindBy(xpath = "//span[contains(text(),'Best Seller')]")
    protected List<WebElement> listOfBestSellers;
    @FindBy(xpath = "//div[@class='a-section a-text-center s-pagination-container']")
    protected WebElement paginationDiv;
    @FindBy(linkText = "Visit the help section")
    protected WebElement visitTheHelpSectionLink;
    @FindBy(css = "span[aria-label='Current page, page 1']")
    protected WebElement pageOneButton;
    @FindBy(css = "span[class='a-badge-supplementary-text a-text-ellipsis']")
    protected List<WebElement> listOfBestSellersSpan;

    public void clickOnAllResultsButton() {
        allResultsButton.click();
    }

    public void checkForIsDisplayedPagination() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(visitTheHelpSectionLink));
        paginationDiv.isDisplayed();
    }

    public void checkTheFirstPageIsSelected() {
        pageOneButton.isSelected();
    }

    protected int indexOfBestSeller;

    public void hoverToRandomBestSeller() {
        Actions action = new Actions(driver);
        Random random = new Random();
        indexOfBestSeller = random.nextInt(listOfBestSellers.size());
        WebElement el = listOfBestSellers.get(indexOfBestSeller);
        action.moveToElement(el).build().perform();
    }

    protected int count = 0;
    protected String[] allHoveringTests = {"in Electrical Light Switches", "in Ceiling Fan Wall Controls", "in Dimmer Switches",
            "in Motion-Activated Wall Switches", "in Home Automation Hubs & Controllers"};

    public int getCountOfMatchesHoveringText() {
        for (String s : allHoveringTests) {
            if (listOfBestSellersSpan.get(indexOfBestSeller).getText().equals(s))
                count++;
        }
        return count;
    }

    public void waitForAllResultsPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(allResultsButton));
    }
}
