package tests;
import utils.ExcelReader;
import utils.ScreenshotUtil;
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
        String beforePath = ScreenshotUtil.captureScreenshot(driver, "BeforeLogin");
        System.out.println("Screenshot saved at: " + beforePath);
        loginPage.login("standard_user", "secret_sauce");
        String afterPath = ScreenshotUtil.captureScreenshot(driver, "AfterLogin");
        System.out.println("Screenshot saved at: " + afterPath);
        ScreenshotUtil.captureScreenshot(driver, "AfterLogin");
        ProductsPage productsPage = new ProductsPage(driver);
        System.out.println(productsPage.getTitleText());
        Assert.assertEquals(productsPage.getTitleText(),"Products");

        productsPage.addBackpackToCart();
        Assert.assertEquals(productsPage.getCartCount(), "1");

        System.out.println("POM test passed - cart shows: " + productsPage.getCartCount());
    }

    @Test
    public void readExcelTest() {
        String path = System.getProperty("user.dir") + "/DataFiles/Input_testdata.xlsx";
        ExcelReader.readTestData(path, "Sheet1");
    }
    @Test
    public void testCellRead() {
        String path = System.getProperty("user.dir") + "/DataFiles/Input_testdata.xlsx";
        System.out.println(ExcelReader.getCellData(path, "Sheet1", 1, 1));  // should print "Login with credentials"
    }
    @AfterTest
    public void tearDown() {
        driver.quit();
    }
}

