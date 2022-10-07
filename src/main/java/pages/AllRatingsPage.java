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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AllRatingsPage extends BasePage {

    public AllRatingsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "acrCustomerReviewLink")
    protected WebElement allRatings;
    @FindBy(xpath = "//*[@id=\"histogramTable\"]/tbody/tr[1]/td[3]/span[2]/a")
    protected WebElement fiveRatings;
    @FindBy(xpath = "//*[@id=\"histogramTable\"]/tbody/tr[2]/td[3]/span[2]/a")
    protected WebElement forRatings;
    @FindBy(xpath = "//*[@id=\"histogramTable\"]/tbody/tr[3]/td[3]/span[2]/a")
    protected WebElement threeRatings;
    @FindBy(xpath = "//*[@id=\"histogramTable\"]/tbody/tr[4]/td[3]/span[2]/a")
    protected WebElement twoRatings;
    @FindBy(xpath = "//*[@id=\"histogramTable\"]/tbody/tr[5]/td[3]/span[2]/a")
    protected WebElement oneRatings;
    protected int rating;

    public void clickAllRatings() {
        allRatings.click();
    }

    public int getCountOfRatings(WebElement element) {
        String string = element.getText();
        Pattern pattern = Pattern.compile("[0-9]+");
        Matcher matcher = pattern.matcher(string);
        matcher.find();
        rating = Integer.parseInt(matcher.group());
        return rating;
    }

    public int sumOfAllRatings() {
        return (getCountOfRatings(fiveRatings) + getCountOfRatings(forRatings) +
                getCountOfRatings(threeRatings) + getCountOfRatings(twoRatings) + getCountOfRatings(oneRatings));
    }

    public void waitForAllRatingsPageLoaded() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(allRatings));
    }

    public void waitForAllRatingsOpened() {
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.elementToBeClickable(fiveRatings));
    }

}

