package StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.testng.Assert;

public class Login {

    private final TestContext context;

    public Login(TestContext context) {
        this.context = context;
    }

    @Given("I login to webpage")
    public void i_login_to_webpage() {
        context.loginPage.login("standard_user", "secret_sauce");
        Assert.assertEquals(context.productsPage.getTitleText(), "Products");
    }

    @Then("I click on product")
    public void i_click_on_product() {
        context.productsPage.clickProductTitle();
    }

    @Then("I add product to the cart")
    public void i_add_product_to_the_cart() {
        context.productsPage.addBackpackToCart();
        Assert.assertEquals(context.productsPage.getCartCount(), "1");
    }

    @Then("log off")
    public void log_off() {
        context.productsPage.logout();
    }
}
