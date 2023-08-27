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
import pages.SearchPage;
import reports.ExtentFactory;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProdcutCartTest {
    private static WebDriver driver;
    private static WebDriverWait wait;
    private static final ExtentSparkReporter info = new ExtentSparkReporter("target/SearchReport.html");
    private static ExtentReports extent;

    @BeforeAll
    public static void setUp() {
        extent = ExtentFactory.getInstance();
        extent.attachReporter(info);
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(5000));
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.setUp();
        searchPage.setUrl("https://opencart.abstracta.us/index.php?route=common/home");
    }

    @Test
    @Tag("Producto")
    @Tag("All")
    public void addFirstProductToCart() {
        ExtentTest test = extent.createTest("Búsqueda y adición del producto al carrito");
        test.log(Status.INFO, "Iniciando test...");

        SearchPage searchPage = new SearchPage(driver, wait);
        String product = "Iphone";

        searchPage.searchProduct(product);
        test.log(Status.PASS, "Campo de busqueda contine " + product);

        searchPage.sendSearch();
        String productSearchTitle = searchPage.getProductSearchTitle();
        assertEquals("Search - " + product, productSearchTitle);
        test.log(Status.PASS, "Redirección a la página de busqueda");

        searchPage.addFisrtToCart();
        String message = searchPage.getSuccessMessage();
        String productTitle = searchPage.getFirstResultTitle();
        assertEquals(message, "Success: You have added " + productTitle + " to your shopping cart!\n×");

        test.log(Status.PASS, "Producto agregado al carrito con éxito");
    }

    @AfterAll
    public static void tearDown() {
        SearchPage searchPage = new SearchPage(driver, wait);
        searchPage.close();
        extent.flush();
    }
}
