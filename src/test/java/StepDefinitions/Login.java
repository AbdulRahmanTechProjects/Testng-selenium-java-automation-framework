package StepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import pages.LoginPage;
import pages.ProductsPage;

import java.time.Duration;

public class Login {

    private WebDriver driver;
    private LoginPage loginPage;
    private ProductsPage productsPage;

    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
        loginPage = new LoginPage(driver);
        productsPage = new ProductsPage(driver);
    }

    @Given("I login to webpage")
    public void i_login_to_webpage() {
        loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(productsPage.getTitleText(), "Products");
    }

    @Then("I click on product")
    public void i_click_on_product() {
        productsPage.clickProductTitle();
    }

    @Then("I add product to the cart")
    public void i_add_product_to_the_cart() {
        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartCount(), "1");
    }

    @Then("log off")
    public void log_off() {
        productsPage.logout();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
