package commonpages

import geb.Page

class DashboardPage extends Page
{
    static url = "/bamboo/start.action"
    static at = { $("#content h1").text() ==~ /Build (D|d)ashboard/ }

    static content =
    {
        buttonCreate { $("#createPlanLink") }
        createPlan(to: CreateNewPlanConfigurePlanPage) { $("#createNewPlan") }
        administrationButton { $("#system_admin_menu .aui-dropdown2-trigger") }
        userManagementOption { $("[href='/bamboo/admin/user/viewUsers.action']") }
    }

    def UserManagementPage openUserManagementPage()
    {
        waitFor { administrationButton.click() }
        waitFor { userManagementOption.click() }
        browser.at UserManagementPage
    }

    def CreateNewPlanConfigurePlanPage createNewPlan()
    {
        buttonCreate.click()
        createPlan.click()
        browser.at CreateNewPlanConfigurePlanPage
    }
}