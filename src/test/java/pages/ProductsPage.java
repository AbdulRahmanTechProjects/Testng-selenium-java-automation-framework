package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductsPage {

    WebDriver driver;

    // Locators for the products page
    By productTitle = By.xpath("//span[@class='title']");
    By cartBadge = By.className("shopping_cart_badge");
    By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");

    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Actions
    public String getTitleText() {
        return driver.findElement(productTitle).getText();
    }

    public void addBackpackToCart() {
        driver.findElement(addBackpackButton).click();
    }

    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }
}