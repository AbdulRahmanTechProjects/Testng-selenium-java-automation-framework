import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.pool.TypePool;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;

public class FirstTest {
    WebDriver driver;
    @BeforeTest
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.saucedemo.com");
    }
    @Test(priority = 1)
    public void testTitle() {
        System.out.println("Page title is " + driver.getTitle());

    }

    @Test(priority = 2)
    public void loginSuccessfully(){
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");
        driver.findElement(By.className("submit-button")).click();
        System.out.println("The current page url is " + driver.getCurrentUrl());
        Assert.assertEquals(driver.findElement(By.xpath("//span[@class = 'title']")).getText(), "Products");
        WebElement ProductTitle = driver.findElement(By.xpath("//span[@class = 'title']"));
        Assert.assertNotEquals(ProductTitle.getText(), "Productss");
        System.out.println(ProductTitle.getText());
        System.out.println("So far correct");
    }

    @Test(priority = 3)
    public void cartVerification(){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement addBackpack = driver.findElement(By.cssSelector(".btn.btn_primary.btn_small.btn_inventory"));
        addBackpack.click();
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//span[@class = 'shopping_cart_badge']"), "1"));
        String cartCount = driver.findElement(By.className("shopping_cart_badge")).getText();
        System.out.println("Cart badge now shows: " + cartCount);
        Assert.assertEquals(cartCount, "1");
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }

}
