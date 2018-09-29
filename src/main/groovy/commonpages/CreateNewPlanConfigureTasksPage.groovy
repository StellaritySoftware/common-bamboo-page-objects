package commonpages

import configuration.CommonConfig
import geb.Page
import org.openqa.selenium.By

class CreateNewPlanConfigureTasksPage extends Page
{
    static url =  CommonConfig.context + "/build/admin/create/createPlanTasks.action"
    static at = { $("#content header h1").text() == "Create plan" && $("#onePageCreate > h2").text() ==~ /Configure (tasks|Job)/ || $("#content header h1 a").text() == "configuration - ${CommonConfig.planName}" }

    static content =
    {
        buttonAddTask{$("#addTask")}
        enablePlanCheckBox(required: false){$("input#finalisePlanCreation_chainEnabled", type:"checkbox")}
        buttonCreate(required: false){$("#finalisePlanCreation_save")}
        buttonCreatePlan{$("#createPlan")} // Bamboo version 6.4.0
        editTaskLink {$(By.cssSelector("a[href='/bamboo/build/admin/edit/editTask.action?planKey=${CommonConfig.projKey}-${CommonConfig.planKey}-JOB1&taskId=1']"))}
    }

    def addTask(def pageType){
       buttonAddTask.click()
       browser.at pageType
    }

    def clickCreateButton(){
        if(!buttonCreate.empty){
            buttonCreate.click()
        }
        else if(!buttonCreatePlan.empty){
            js."document.querySelector('#createPlan').click()"
        }
        browser.at CreatedPlanPage
    }

    def markEnablePlanCheckbox(){
        if(enablePlanCheckBox.isDisplayed()){
            enablePlanCheckBox = true
        }
    }

    def editTask(def pageType){
        editTaskLink.click()
        browser.at pageType
    }
}