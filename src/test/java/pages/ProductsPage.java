package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.security.PublicKey;

public class ProductsPage {

    WebDriver driver;

    // Locators for the products page
    By productTitle = By.xpath("//span[@class='title']");
    By cartBadge = By.className("shopping_cart_badge");
    By addBackpackButton = By.id("add-to-cart-sauce-labs-backpack");
    By sortDropdown = By.className("product_sort_container");
    By bpRemoveButton = By.cssSelector("#remove-sauce-labs-backpack");

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


    public void setSort( String option){
        Select select = new Select(driver.findElement(sortDropdown));
        select.selectByVisibleText(option);
    }

    public String getSelectedOption(){
        Select select = new Select(driver.findElement(sortDropdown));
        return select.getFirstSelectedOption().getText();
    }

    public void clickRemoveButton(){
        WebElement bpRemoveButton = driver.findElement(By.cssSelector("#remove-sauce-labs-backpack"));
        bpRemoveButton.click();
    }

    public int getSortOptionCount(){
        Select select = new Select(driver.findElement(sortDropdown));
        return select.getOptions().size();
    }
    public String getCartCount() {
        return driver.findElement(cartBadge).getText();
    }
}