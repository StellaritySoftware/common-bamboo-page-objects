package commonpages

import geb.Page
import org.openqa.selenium.By

class LogsSubPage extends Page
{
    static at = { $(By.cssSelector("div.tabs-pane.active-pane h1")).text() == "Logs" }
    
    static content = 
    {
        logsOutput{$(By.cssSelector("table#job-logs table td.buildOutputLog"))}
    }

    def waitForOutputIsDisplayed(){
        waitFor{logsOutput.isDisplayed()}
    }
 }