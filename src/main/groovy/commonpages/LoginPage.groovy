package commonpages

import geb.Page

class LoginPage extends Page
{
    static url = "/bamboo/userlogin!doDefault.action"
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