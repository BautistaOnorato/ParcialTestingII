package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.RegisterPage;
import reports.ExtentFactory;

import java.time.Duration;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class RegisterTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final ExtentSparkReporter info = new ExtentSparkReporter("target/RegisterReport.html");
    private static ExtentReports extent;

    @BeforeAll
    public static void setUp() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(6000));
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.setUp();
        registerPage.setUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Registro")
    @Tag("All")
    public void register() {
        ExtentTest test = extent.createTest("Proceso de registro");
        test.log(Status.INFO, "Iniciando test...");

        RegisterPage registerPage = new RegisterPage(driver, wait);

        registerPage.clickAccount();
        assertEquals(registerPage.getDropdwonMenuText(), "Register\nLogin");
        test.log(Status.PASS, "Ventana emergente con opciones de register y login");

        registerPage.clickRegsiter();
        test.log(Status.PASS, "Redirección a forumlario de registro");

        registerPage.sendFirstName("Juan");
        registerPage.sendLastName("Gomez");
        registerPage.sendEmail(UUID.randomUUID() + "@gmail.com");
        registerPage.sendTelephone("123456789");
        registerPage.sendPassword("1234");
        registerPage.sendConfirmPassword("1234");
        test.log(Status.PASS, "Formulario lleno");

        Boolean suscribeNewsletter = registerPage.suscribeNewsletter(false);
        assertTrue(suscribeNewsletter);
        test.log(Status.PASS, "Selecionar no para el newsletter");

        Boolean policy = registerPage.agreePrivacyPolicy();
        assertTrue(policy);
        test.log(Status.PASS, "Aceptar las politicas de privacidad");

        registerPage.sendForm();
        String message = registerPage.accountCreated();
        assertEquals("Congratulations! Your new account has been successfully created!", message);
        test.log(Status.PASS, "Cuenta registrada con éxito");
    }

    @AfterAll
    public static void tearDown() {
        RegisterPage registerPage = new RegisterPage(driver, wait);
        registerPage.close();
        extent.flush();
    }
}
