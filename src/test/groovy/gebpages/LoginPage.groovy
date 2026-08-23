package gebpages

import geb.Page

class LoginPage extends Page {

    static url = "/"

    static at = { $("div", class: "login_logo").displayed }

    static content = {
        usernameField { $("#user-name") }
        passwordField { $("#password") }
        loginButton { $("#login-button") }
    }

    void login(String username, String password) {
        usernameField.value(username)
        passwordField.value(password)
        loginButton.click()
    }
}
