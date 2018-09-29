package commonpages

import configuration.CommonConfig
import geb.Page


class LoginPage extends Page
{
    static url = CommonConfig.context + "/userlogin!doDefault.action"
    static at = { $("#content h1").text() == "Log in" }

    static content =
    {
        username { $("#loginForm_os_username") }
        password { $("#loginForm_os_password") }
        loginButton(to: DashboardPage) { $("#loginForm_save") }
    }

    def DashboardPage login(username, password)
    {
        this.username = username
        this.password = password
        this.loginButton.click()
        browser.at DashboardPage
    }
}