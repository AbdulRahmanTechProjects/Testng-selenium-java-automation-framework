package StepDefinitions;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ExtentReportManager;
import utils.ScreenshotUtil;

import java.time.Duration;

public class Hooks {

    private final TestContext context;

    public Hooks(TestContext context) {
        this.context = context;
    }

    @Before
    public void setup(Scenario scenario) {
        ExtentReports extent = ExtentReportManager.getInstance();
        context.test = extent.createTest(scenario.getName());

        WebDriverManager.chromedriver().setup();
        context.driver = new ChromeDriver();
        context.driver.manage().window().maximize();
        context.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        context.driver.get("https://www.saucedemo.com");
        context.loginPage = new LoginPage(context.driver);
        context.productsPage = new ProductsPage(context.driver);
    }

    @After
    public void tearDown(Scenario scenario) {
        String screenshotPath = ScreenshotUtil.captureScreenshot(context.driver, scenario.getName().replaceAll("\\s+", "_"));
        context.test.addScreenCaptureFromPath(screenshotPath);

        if (scenario.isFailed()) {
            context.test.log(Status.FAIL, "Scenario failed");
        } else {
            context.test.log(Status.PASS, "Scenario passed");
        }

        if (context.driver != null) {
            context.driver.quit();
        }
    }

    @AfterAll
    public static void flushReport() {
        ExtentReportManager.getInstance().flush();
    }
}
