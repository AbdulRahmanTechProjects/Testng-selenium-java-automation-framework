package tests;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import java.time.Duration;

public class LoginTest {
    WebDriver driver;

    @BeforeTest
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void loginAndAddToCart() {
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        System.out.println(productsPage.getTitleText());
        Assert.assertEquals(productsPage.getTitleText(),"Products");

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartCount(), "1");

        System.out.println("POM test passed - cart shows: " + productsPage.getCartCount());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

