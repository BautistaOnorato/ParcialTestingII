package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofMillis(4000));
    }

    public BasePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver.manage().window().maximize();
    }

    public void setUrl(String url) {
        driver.get(url);

    }

    public void close() {
        driver.quit();
    }

    public WebElement findElement(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return driver.findElement(locator);
    }

    public void sendText(By locator, String text) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        findElement(locator).clear();
        findElement(locator).sendKeys(text);
    }

    public void sendKey(By locator, CharSequence key) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        findElement(locator).sendKeys(key);
    }

    public void click(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator));
        findElement(locator).click();
    }

    public String getText(By locator) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        return findElement(locator).getText();
    }

    public Boolean isSelected(By locator) {
        return findElement(locator).isSelected();
    }

}
