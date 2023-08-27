package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchPage extends BasePage {
    By inputSearch = By.name("search");
    By searchBtn = By.className("btn-default");
    By searchTitle = By.xpath("//*[@id=\"content\"]/h1");
    By firstResultTitle = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[1]/h4/a");
    By firstResultCart = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");
    By firstResultWishList = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[2]");
    By firstResultCompare = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[3]");
    By successMessage = By.className("alert-success");

    public SearchPage(WebDriver driver) {
        super(driver);
    }

    public SearchPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void searchProduct(String product) {
        this.sendText(inputSearch, product);
    }

    public void sendSearch() {
        this.click(searchBtn);
    }

    public String getProductSearchTitle() {
        return this.getText(searchTitle);
    }

    public String getFirstResultTitle() {
        return this.getText(firstResultTitle);
    }

    public void addFisrtToCart() {
        this.click(firstResultCart);
    }

    public void addFirstToComparison() {
        this.click(firstResultCompare);
    }

    public void addFirstToWishList() {
        this.click(firstResultWishList);
    }

    public String getSuccessMessage() {
        String res = this.getText(successMessage);
        System.out.println(res);
        return res;
    }
}
