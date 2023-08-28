package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {
    private By accountDropdown = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]");
    private By dropDownMenu = By.className("dropdown-menu-right");
    private By registerBtn = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");
    private By firstNameInput = By.id("input-firstname");
    private By lastNameInput = By.id("input-lastname");
    private By emailInput = By.id("input-email");
    private By telephoneInput = By.id("input-telephone");
    private By passwordInput = By.id("input-password");
    private By passwordConfirmInput = By.id("input-confirm");
    private By newsletterYesInput = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[1]/input");
    private By newsletterNoInput = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
    private By privacyPolicyInput = By.name("agree");
    private By sendBtn = By.className("btn-primary");
    private By successMessage = By.xpath("//*[@id=\"content\"]/p[1]");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickAccount() {
        this.click(accountDropdown);
    }

    public String getDropdwonMenuText() {
        return this.getText(dropDownMenu);
    }

    public void clickRegsiter() {
        this.click(registerBtn);
    }

    public void sendFirstName(String firstName) {
        this.sendText(firstNameInput, firstName);
    }

    public void sendLastName(String lastName) {
        this.sendText(lastNameInput, lastName);
    }

    public void sendEmail(String email) {
        this.sendText(emailInput, email);
    }

    public void sendTelephone(String telephone) {
        this.sendText(telephoneInput, telephone);
    }

    public void sendPassword(String password) {
        this.sendText(passwordInput, password);
    }

    public void sendConfirmPassword(String passwordConfirm) {
        this.sendText(passwordConfirmInput, passwordConfirm);
    }

    public boolean suscribeNewsletter(Boolean suscription) {
        if (suscription) {
            this.click(newsletterYesInput);
            return this.isSelected(newsletterYesInput);
        } else {
            this.click(newsletterNoInput);
            return this.isSelected(newsletterNoInput);
        }
    }

    public boolean agreePrivacyPolicy() {
        this.click(privacyPolicyInput);
        return this.isSelected(privacyPolicyInput);
    }

    public void sendForm() {
        this.click(sendBtn);
    }

    public String accountCreated() {
        String res = this.getText(successMessage);
        System.out.println(res);
        return res;
    }
}
