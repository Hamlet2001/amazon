package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    protected final String HOME_URL = "https://www.amazon.com";

    public void openHomePage() {
        driver.manage().window().maximize();
        driver.get(HOME_URL);
    }

    @FindBy(id = "nav-logo-sprites")
    protected WebElement amazonLogo;
    @FindBy(id = "glow-ingress-line2")
    protected WebElement deliveryAddress;
    @FindBy(id = "searchDropdownBox")
    protected WebElement dropdownButton;
    @FindBy(id = "twotabsearchtextbox")
    protected WebElement inputForSearch;
    @FindBy(id = "nav-search-submit-button")
    protected WebElement searchButton;

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public void chooseCategory(String preferredCategory) {
        Select select = new Select(dropdownButton);
        select.selectByVisibleText(preferredCategory);
    }

    public void searchText(String textForSearch) {
        inputForSearch.sendKeys(textForSearch);
        searchButton.click();
    }

    public void clickOnAmazonLogo() {
        amazonLogo.click();
    }

    public void clickOnTodaySDeals(String text) {
        clickOnAmazonLogo();
        driver.findElement(By.linkText(text)).click();
    }

    public void waitForHomePageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(searchButton));
    }
}
