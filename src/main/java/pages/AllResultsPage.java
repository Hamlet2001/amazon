package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

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
    @FindBy(css = "span[class='a-badge-supplementary-text a-text-ellipsis']")
    protected List<WebElement> listOfBestSellersHoveringText;

    public void clickOnAllResultsButton() {
        allResultsButton.click();
    }

    public boolean checkForIsDisplayedPagination() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(visitTheHelpSectionLink));
        return paginationDiv.isDisplayed();
    }
    public int getCountOfBestSellers(){
        return listOfBestSellers.size();
    }

    public String hoverToBestSeller(int indexOfBestSeller) {
        try {
            Actions action = new Actions(driver);
            WebElement el = listOfBestSellers.get(indexOfBestSeller);
            action.moveToElement(el).build().perform();
            return listOfBestSellersHoveringText.get(indexOfBestSeller).getText();
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }

    public void waitForAllResultsPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.elementToBeClickable(allResultsButton));
    }
}
