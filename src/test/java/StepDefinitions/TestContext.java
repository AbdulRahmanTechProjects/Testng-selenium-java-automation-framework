package StepDefinitions;

import com.aventstack.extentreports.ExtentTest;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class TestContext {
    WebDriver driver;
    ExtentTest test;
    LoginPage loginPage;
    ProductsPage productsPage;
}
