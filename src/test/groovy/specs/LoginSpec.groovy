package specs

import gebpages.LoginPage
import gebpages.ProductsPage
import geb.spock.GebSpec

class LoginSpec extends GebSpec {

    def "login and add a product to the cart"() {
        given: "the user is on the login page"
        to LoginPage

        when: "the user logs in with valid credentials"
        login("standard_user", "secret_sauce")

        then: "the user lands on the products page"
        at ProductsPage

        when: "the user clicks on a product"
        clickProductTitle()

        and: "the user adds the product to the cart"
        addBackpackToCart()

        then: "the cart shows one item"
        getCartCount() == "1"

        when: "the user logs off"
        logout()

        then: "the user is returned to the login page"
        at LoginPage
    }
}
