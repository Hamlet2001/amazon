//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SelectCategoryPage extends BasePage {

    public SelectCategoryPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "i[class='hm-icon nav-sprite']")
    protected WebElement allButton;

    public void clickTheAllButton() {
        allButton.click();
    }

    public void selectCategoryOne(String categoryOne) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(By.linkText(categoryOne)));
        WebElement element = driver.findElement(By.linkText(categoryOne));
        element.click();
    }

    public void selectCategoryTwo(String categoryTwo) {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(By.linkText(categoryTwo)));
        WebElement element = driver.findElement(By.linkText(categoryTwo));
        element.click();
    }
}
