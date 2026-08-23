import io.github.bonigarcia.wdm.WebDriverManager
import org.openqa.selenium.chrome.ChromeDriver

driver = {
    WebDriverManager.chromedriver().setup()
    new ChromeDriver()
}

baseUrl = "https://www.saucedemo.com"
