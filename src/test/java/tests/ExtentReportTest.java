package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ExcelReader;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import java.time.Duration;

public class ExtentReportTest {
    WebDriver driver;
    ExtentReports extent;
    ExtentTest test;

    String dataPath = System.getProperty("user.dir") + "/DataFiles/Input_testdata.xlsx";
    String sheet = "Sheet1";   // change if your tab name is different

    @BeforeTest
    public void setupReport() {
        extent = ExtentReportManager.getInstance();
    }

    @BeforeMethod
    public void setup() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }

    @Test
    public void tc1_login() {
        String name = ExcelReader.getCellData(dataPath, sheet, 1, 1);   // "Login with credentials"
        test = extent.createTest("TC1 - " + name);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "TC1_Login"));
        test.log(Status.PASS, "Logged in successfully");

        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test
    public void tc2_productTitle() {
        String name = ExcelReader.getCellData(dataPath, sheet, 2, 1);   // "Get the title of the product page"
        test = extent.createTest("TC2 - " + name);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        String title = productsPage.getTitleText();

        test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "TC2_Title"));
        test.log(Status.PASS, "Product page title is: " + title);

        Assert.assertEquals(title, "Products");
    }

    @Test
    public void tc3_addToCart() {
        String name = ExcelReader.getCellData(dataPath, sheet, 3, 1);   // "Add a product to cart"
        test = extent.createTest("TC3 - " + name);

        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");

        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        String count = productsPage.getCartCount();

        test.addScreenCaptureFromPath(ScreenshotUtil.captureScreenshot(driver, "TC3_AddToCart"));

        if (count.equals("1")) {
            test.log(Status.PASS, "Product added to cart, badge shows: " + count);
        } else {
            test.log(Status.FAIL, "Product was not added to cart");
        }

        Assert.assertEquals(count, "1");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterTest
    public void flushReport() {
        extent.flush();   // REQUIRED - writes the report to disk
    }
}