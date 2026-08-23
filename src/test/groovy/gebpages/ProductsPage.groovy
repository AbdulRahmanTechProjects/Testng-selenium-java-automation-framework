package gebpages

import geb.Page

class ProductsPage extends Page {

    static at = { title == "Swag Labs" && $("span", class: "title").displayed }

    static content = {
        productTitle { $("span", class: "title", 0) }
        cartBadge { $(".shopping_cart_badge") }
        addBackpackButton { $("#add-to-cart-sauce-labs-backpack") }
        menuButton { $("#react-burger-menu-btn") }
        logoutLink { $("#logout_sidebar_link") }
    }

    void clickProductTitle() {
        productTitle.click()
    }

    void addBackpackToCart() {
        addBackpackButton.click()
    }

    String getCartCount() {
        cartBadge.text()
    }

    void logout() {
        menuButton.click()
        waitFor { logoutLink.displayed }
        logoutLink.click()
    }
}
