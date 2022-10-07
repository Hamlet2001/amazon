//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class RandomItemPage extends BasePage {

    public RandomItemPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath = "//div[@class='a-section a-spacing-base']")
    protected List<WebElement> listOfResults;

    public int getCountOfList() {
        return listOfResults.size();
    }

    public void clickRandomItem() {
        Random random = new Random();
        int randomInt = random.nextInt(this.getCountOfList());
        listOfResults.get(randomInt).click();
    }

    public void waitForSelectRandomItemPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.visibilityOfAllElements(listOfResults));
    }
}
