package commonpages

import configuration.CommonConfig
import geb.Page
import org.openqa.selenium.By


/**
 * Created by Kateryna on 09.12.2017.
 */
class CreatedPlanPage extends Page
{
    static at = { $(By.id("breadcrumb:" + CommonConfig.projKey.toString() + "-" + CommonConfig.planKey.toString())).text() == CommonConfig.planName }
    
    static content = 
    {
        runDropdown { $("button.aui-button.aui-dropdown2-trigger", text : "Run") }
        manualBuild { $(By.id("manualBuild_" + CommonConfig.projKey + "-" + CommonConfig.planKey)) }
    }
    
    def PlanBuildPage runManualBuild()
    {
        runDropdown.click()
        manualBuild.click()
        browser.at PlanBuildPage
    }
}
