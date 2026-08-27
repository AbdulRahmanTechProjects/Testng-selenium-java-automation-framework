package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import utils.ScreenshotUtil;

import java.time.Duration;
public class ProductTest{
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
    public void DropdownTest(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        ScreenshotUtil.captureScreenshot(driver, "AfterLogin");
        ProductsPage productsPage = new ProductsPage(driver);
        System.out.println(productsPage.getTitleText());
        Assert.assertEquals(productsPage.getTitleText(),"Products");
        System.out.println(productsPage.getSelectedOption());
        ScreenshotUtil.captureScreenshot(driver, "BeforeSelectingDropdownOption");
        System.out.println(productsPage.getSortOptionCount());
        productsPage.setSort("Price (low to high)");
        ScreenshotUtil.captureScreenshot(driver, "AfterSelectingDropdownOption");
        System.out.println(productsPage.getSelectedOption());
        ScreenshotUtil.captureScreenshot(driver, "BeforeAddingBackPackToCart");
        productsPage.addBackpackToCart();
        ScreenshotUtil.captureScreenshot(driver, "BeforeAddingBackPackToCart");
        Assert.assertEquals(productsPage.getCartCount(), "1");
        System.out.println("POM test passed - cart shows: " + productsPage.getCartCount());
        productsPage.removeBackpackFromCart();
        ScreenshotUtil.captureScreenshot(driver, "BeforeAddingBackPackToCart");


    }
    @Test
    public void navigationTest(){
        driver.navigate().refresh();
        System.out.println("Refresh successful");
        driver.navigate().to("https://saucelabs.com/");
        driver.navigate().back();
        System.out.println(driver.getCurrentUrl());
        driver.navigate().forward();
        System.out.println(driver.getCurrentUrl());
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }


}