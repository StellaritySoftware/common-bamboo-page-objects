package commonpages

import configuration.CommonConfig
import geb.Page
import org.openqa.selenium.By

class CreateNewPlanConfigureTasksPage extends Page
{
    static url = "/bamboo/build/admin/create/createPlanTasks.action"
    static at = { $("#content header h1").text().matches("Create(.*)plan") && $("#onePageCreate > h2").text() ==~ /Configure (tasks|Job)/ || $("#content header h1 a").text() == "Configuration - ${Config.planName}" }

    static content =
    {
        buttonAddTask { $("#addTask") }
        enablePlanCheckBox(required: false) { $("input#finalisePlanCreation_chainEnabled", type:"checkbox") }
        buttonCreate(required: false) { $("#finalisePlanCreation_save") }
        buttonCreateDefault(required: false) { $("input#finalisePlanCreation_defaultSave") }
        buttonCreatePlan { $("#createPlan") } // Bamboo version 6.4.0
        editTaskLink { $(By.cssSelector("a[href='/bamboo/build/admin/edit/editTask.action?planKey=${CommonConfig.projKey}-${CommonConfig.planKey}-JOB1&taskId=1']")) }
    }

    def addTask(def pageType)
    {
       buttonAddTask.click()
       browser.at pageType
    }

    def CreatedPlanPage clickCreateButton()
    {
        if (!buttonCreate.empty)
        {
            js."document.querySelector('input#finalisePlanCreation_save').click()"  //6.0.0
        }
        else if (!buttonCreateDefault.empty){
            js."document.querySelector('input#finalisePlanCreation_defaultSave').click()"
        }
        else if (!buttonCreatePlan.empty){
            js."document.querySelector('#createPlan').click()"
        }
        
        browser.at CreatedPlanPage
    }

    def markEnablePlanCheckbox()
    {
        if (enablePlanCheckBox.isDisplayed())
        {
            js."document.querySelector('input#finalisePlanCreation_chainEnabled').scrollIntoView()"
            enablePlanCheckBox = true
        }
    }

    def editTask(def pageType)
    {
        editTaskLink.click()
        browser.at pageType
    }
}