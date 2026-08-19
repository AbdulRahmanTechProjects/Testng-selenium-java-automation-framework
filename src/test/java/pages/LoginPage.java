package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    WebDriver driver;

    //Locators
    By usernameField = By.id("user-name");
    By passwordField = By.name("password");
    By loginButton = By.className("submit-button");

    //Constructors
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    // Actions
    public void enterUsername(String username){
        driver.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickLogin() {
        driver.findElement(loginButton).click();
    }

    //Method
    public void login(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();
    }

}
