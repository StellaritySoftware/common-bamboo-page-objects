package commonpages

import configuration.CommonConfig
import geb.Page
import org.openqa.selenium.By
import org.openqa.selenium.WebElement


/**
 * Created by Kateryna on 09.12.2017.
 */
class PlanBuildPage extends Page
{
    static at = { $(By.id("breadcrumb:${CommonConfig.projKey}-${CommonConfig.planKey}")) }
    
    static content = 
    {
        successfulHeader { $("div#sr-build.status-ribbon-status.Successful") }
        failedHeader { $("div#sr-build.status-ribbon-status.Failed") }
        buttonActions { $("button.aui-button.aui-dropdown2-trigger span.aui-icon.aui-icon-small.aui-iconfont-configure") }
        configurePlanLink { $(By.id("editBuild:${CommonConfig.projKey}-${CommonConfig.planKey}")) }
        defaultJobLink { $(By.id("viewJob_${CommonConfig.projKey}-${CommonConfig.planKey}-JOB1")) }
        failedLabel_upToVersion_6_7 (required: false){ $(By.cssSelector("li#testsSummaryFailed strong.failedLabel")) }
        failedLabel_version_6_8 (required: false){ $(By.cssSelector("li.new-failures a")) }
        compilationWarining_upToVersion_6_7 (required: false) { $(By.cssSelector("div.result-summary-tab div.aui-message.warning p strong")) }
        compilationWarining_version_6_8 (required: false) { $(By.cssSelector("div.result-summary-tab div.aui-message.warning")) }
        testsTabLink { $(By.id("tests:${CommonConfig.projKey}-${CommonConfig.planKey}-1")) }
        logsTabLink { $(By.id("logs:${CommonConfig.projKey}-${CommonConfig.planKey}-1")) }
    }

    def waitForSuccessfulHeader()
    {
        waitFor { successfulHeader.isDisplayed() }
    }

    def waitForFailedHeader() 
    {
        waitFor { failedHeader.isDisplayed() }
    }

    def clickEditPalnLink()
    {
        buttonActions.click()
        configurePlanLink.click()
    }

    def clickDefaultJobLink()
    {
        waitFor { defaultJobLink.isDisplayed() }
        defaultJobLink.click()
    }

    def checkNumberOfFailedTests(CharSequence number) {

        waitFor { failedLabel_upToVersion_6_7.isDisplayed() || failedLabel_version_6_8.isDisplayed() }

        def failedLabel = failedLabel_version_6_8 ? failedLabel_version_6_8 : failedLabel_upToVersion_6_7
        failedLabel.text().contains(number)
    }

    def waitForCompilationWarning()
    {
        waitFor { compilationWarining_upToVersion_6_7.isDisplayed() || compilationWarining_version_6_8.isDisplayed() }
        
        def compilationWarining = compilationWarining_version_6_8 ? compilationWarining_version_6_8 : compilationWarining_upToVersion_6_7
        compilationWarining.text() == "No failed tests found, a possible compilation error occurred."
    }

    def checkTextAddedToTests(String fileName, Integer expectedTestsCount) 
    {
        return driver.findElements(By.xpath("//table[@id='new-failed-tests']//td/span[contains(text(), '{${fileName}}')]")).size() == expectedTestsCount
    }

    def checkNoTestsWithTexts(String fileName, Integer expectedTestsCount) {
        List<WebElement> list = driver.findElements(By.xpath("//table[@id='new-failed-tests']//td/span[contains(text(), '{${fileName}}')]"))
        return list.size() == expectedTestsCount
    }

    def openLogsSubPage(){
        logsTabLink.click()
        LogsSubPage page = browser.at LogsSubPage
        page.waitForOutputIsDisplayed()
        return page
    }
 }
