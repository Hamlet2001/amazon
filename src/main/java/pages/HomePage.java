package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
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

    @FindBy(id = "glow-ingress-line2")
    protected WebElement deliveryAddress;
    @FindBy(id = "searchDropdownBox")
    protected WebElement dropdownButton;
    @FindBy(id = "twotabsearchtextbox")
    protected WebElement inputForSearch;
    @FindBy(id = "nav-search-submit-button")
    protected WebElement searchButton;
    @FindBy(linkText = "Today's Deals")
    protected WebElement todaySDeals;

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

    public void clickOnTodaySDeals() {
        try {
            if (driver.findElement(By.cssSelector("div[data-toaster-slot='DEFAULT']")).isDisplayed()) {
                driver.findElement(By.xpath("//div[@class='glow-toaster-footer']/span[contains(@class,'dismiss')]")).click();
            }
        } catch (NoSuchElementException ignored) {
        }
        todaySDeals.click();
    }

    public void waitForHomePageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(searchButton));
    }
}
