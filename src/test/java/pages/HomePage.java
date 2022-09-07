package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage extends BasePage {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    protected final String HOME_URL = "https://www.amazon.com";
    protected final String INPUT_TEXT = "Java";

    public void openHomePage() {
        driver.manage().window().maximize();
        driver.get(HOME_URL);
    }

    @FindBy(id = "glow-ingress-line2")
    protected WebElement deliveryAddress;
    @FindBy(id = "nav-search-dropdown-card")
    protected WebElement dropDownButton;
    @FindBy(css = "#searchDropdownBox > option:nth-child(6)")
    protected WebElement preferredCategory;
    @FindBy(id = "twotabsearchtextbox")
    protected WebElement inputForSearch;
    @FindBy(id = "nav-search-submit-button")
    protected WebElement searchButton;

    public String getDeliveryAddress() {
        return deliveryAddress.getText();
    }

    public void chooseCategory() {
        dropDownButton.click();
        preferredCategory.click();
        inputForSearch.sendKeys(INPUT_TEXT);
        searchButton.click();
    }
    public void waitForHomePageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(searchButton));
    }
}
