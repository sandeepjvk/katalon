package utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.*
import org.openqa.selenium.support.ui.*

import java.text.DateFormat
import java.text.SimpleDateFormat

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.exception.StepFailedException
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.webui.common.WebUiCommonHelper
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.configuration.RunConfiguration

import internal.GlobalVariable as GlobalVariable

public class SafeActions {

	Sync syncObj = new Sync()
	String reportsFolderPath = RunConfiguration.getReportFolder()

	/**
	 * Launch the browser
	 * @param url
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def openBrowser(String url,int... optionWaitTime){
		boolean bflag=true
		int waitTime=0
		try{
			KeywordUtil.logInfo("Opening Browser")
			waitTime = syncObj.getWaitTime(optionWaitTime)

			if(bflag){
				WebUI.openBrowser(url)
				WebUI.maximizeWindow()
				KeywordUtil.logInfo("Browser Launched Successfully")
				WebUI.enableSmartWait()
			}
			else{
				KeywordUtil.markFailed("Unable to open browser with url:${url} in time - ${waitTime} Seconds")
			}
		}

		catch(StepFailedException e){
			KeywordUtil.markFailed("unable to open Browser with url:${url}")
		}
	}

	/**
	 * Enter text into input field
	 * @param testObj
	 * @param text
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeType(TestObject testObj,String text,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try{
			waitTime = syncObj.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObj, waitTime)

			if(WebUI.verifyElementPresent(testObj, waitTime, FailureHandling.OPTIONAL)){

				highLightElement(testObj,waitTime)
				WebUI.click(testObj)
				Thread.sleep(1000)
				WebUI.setText(testObj, text, FailureHandling.STOP_ON_FAILURE)
			}
			else{
				KeywordUtil.markWarning(syncObj.getTestCaseName()+"unable to enter"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
			}
		}
		catch(Exception e) {
			KeywordUtil.markWarning("unable to enter"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
		}
	}

	/**
	 * Enter value in text field after checking visibility of element
	 * @param testObj
	 * @param text
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeType1(TestObject testObj,String text,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try{
			waitTime = syncObj.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObj, waitTime)
			if(!WebUI.verifyElementVisible(testObj))
				WebUI.scrollToElement(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj,waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj)
				WebUI.setText(testObj, text, FailureHandling.STOP_ON_FAILURE)
			}
			else{
				KeywordUtil.markError(syncObj.getTestCaseName()+"unable to enter"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
			}
		}
		catch(Exception e) {
			KeywordUtil.markError("unable to enter"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
		}
	}
	/**
	 *  Select dropdown value
	 * @param testObj
	 * @param text
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeSelectOptionInDropDownByVisibleText(TestObject testObj,String text,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try{
			waitTime = syncObj.getWaitTime(optionWaitTime)
			WebUI.waitForElementVisible(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj,waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj)
				WebUI.selectOptionByLabel(testObj, text, false)
			}
			else{
				KeywordUtil.markError(syncObj.getTestCaseName()+"unable to select"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
			}
		}
		catch(Exception e) {
			KeywordUtil.markError(syncObj.getTestCaseName()+"unable to select"+ text +" in "+friendlyWebElementName+" in time -"+waitTime+"seconds")
		}
	}
	/**
	 * Select the dropdown
	 * @param selectObj
	 * @return
	 */
	@Keyword
	def dropDownSelect(TestObject[] selectObj){
		WebUI.click(selectObj[0])
		WebUI.click(selectObj[1])
	}
	/**
	 * Click on element without scrolling
	 * @param testObj
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeClickWithoutScroll(TestObject testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try{
			KeywordUtil.logInfo("Safe Click Without scroll")
			waitTime = syncObj.getWaitTime(optionWaitTime)
			if(waitUntilClickable(testObj,friendlyWebElementName,waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.logInfo(syncObj.getTestCaseName()+" click on the "+friendlyWebElementName)
			}
			else{
				KeywordUtil.markFailedAndStop(syncObj.getTestCaseName()+friendlyWebElementName+" is not clickable in time - "+waitTime+"seconds")
			}
		}
		catch(Exception e){
			KeywordUtil.markFailedAndStop(syncObj.getTestCaseName()+friendlyWebElementName+" is not clickable in time - "+waitTime+"seconds")
		}
	}
	/**
	 * Click on element using Webdriver library
	 * @param testObj
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeWebDriverClick(WebElement testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0;
		try{

			testObj.click()
		}
		catch(Exception e){
			KeywordUtil.markFailedAndStop(syncObj.getTestCaseName()+friendlyWebElementName+" is not clickable in time - "+waitTime+"seconds")
		}
	}

	/**
	 * Wait for an element until it is clickable
	 * @param testObj
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def waitUntilClickable(TestObject testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=syncObj.getWaitTime(optionWaitTime)
		boolean bFlag=false
		try{
			KeywordUtil.logInfo(syncObj.getTestCaseName()+"waiting until "+friendlyWebElementName+" is Clickable")
			WebUI.waitForElementClickable(testObj,waitTime)
			if(WebUI.verifyElementVisible(testObj)){
				bFlag=true
			}
			else{
				KeywordUtil.markFailedAndStop(syncObj.getTestCaseName()+friendlyWebElementName+" is not visible to perform Click action")
			}
		}
		catch(Exception e){
			KeywordUtil.markFailedAndStop(syncObj.getTestCaseName()+friendlyWebElementName+" is not Clickable")
		}
	}
	/**
	 * CLick on element by scrolling into it
	 * 
	 * @param testObj
	 * @param friendlyWebElementName
	 * @param optionWaitTime
	 * @return
	 */
	@Keyword
	def safeClickwithScroll(TestObject testObj,String friendlyWebElementName,int... optionWaitTime){
		int waitTime=0
		try{
			waitTime=syncObj.getWaitTime(optionWaitTime)
			WebUI.waitForElementPresent(testObj, waitTime)
			if(!WebUI.verifyElementVisible(testObj))
				WebUI.scrollToElement(testObj, waitTime)
			if(waitUntilClickable(testObj, friendlyWebElementName, waitTime)){
				highLightElement(testObj,waitTime)
				WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
				KeywordUtil.logInfo(syncObj.getTestCaseName()+" Clicked on the "+friendlyWebElementName)
			}
			else{
				KeywordUtil.markFailed(friendlyWebElementName+" is not clickable in time -"+waitTime+" seconds")
			}
		}
		catch(Exception e){
			KeywordUtil.markError(friendlyWebElementName+" was not found on the webpage")
		}
	}
	/**
	 * Get text displayed on element
	 * @param testObj
	 * @param waitTime
	 * @return
	 */
	@Keyword
	def safeGetText(TestObject testObj,int waitTime){
		String sValue=null
		try{
			WebUI.waitForElementPresent(testObj, waitTime)
			WebUI.scrollToElement(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj, waitTime)){
				highLightElement(testObj,waitTime)
				sValue=WebUI.getText(testObj)
			}
			else{
				KeywordUtil.markFailed("Unable to element in time - "+waitTime)
			}
		}
		catch(Exception e){
			KeywordUtil.markError("Unable to element in time - "+waitTime)
		}
		return sValue
	}
	/**
	 * Click on check box
	 * @param testObj
	 * @param waitTime
	 * @return
	 */
	@Keyword
	def safeCheckForElement(TestObject testObj,boolean flag, int waitTime){
		try{
			WebUI.waitForElementPresent(testObj, waitTime)

			if(WebUI.verifyElementPresent(testObj, waitTime)){
				if(flag){
					WebUI.check(testObj)
				}else {
					WebUI.uncheck(testObj)
				}
			}
			else{
				KeywordUtil.markFailed("Element is not visible to check")
			}
		}
		catch(Exception e){

			KeywordUtil.markError("Cannot check for Checkbox")
		}
	}
	@Keyword
	def safeElementVisible(TestObject testObj,int waitTime){
		try{
			WebUI.waitForElementPresent(testObj, waitTime)
			if(!WebUI.verifyElementVisible(testObj))
				WebUI.scrollToElement(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj, waitTime)){
				highLightElement(testObj,waitTime)
				assert(WebUI.verifyElementPresent(testObj, waitTime))
			}
			else{
				KeywordUtil.markFailed("Element is not visible to check")
			}
		}
		catch(Exception e){
			WebUI.takeScreenshot(reportsFolderPath+"/safeCheckForElement.png")
			KeywordUtil.markError("Cannot check for Element")
		}
	}


	/**
	 * Highlight the element to be interacted
	 * @param testObject
	 * @param timeOut
	 * @return
	 */
	@Keyword
	def highLightElement(TestObject testObject,int timeOut){
		try{
			if(GlobalVariable.HighLightElements.equalsIgnoreCase("true")){
				String attributeValue="border:2px solid red";
				WebDriver driver = DriverFactory.getWebDriver()
				JavascriptExecutor je=(JavascriptExecutor)driver
				WebElement ele=WebUiCommonHelper.findWebElement(testObject, timeOut)
				String getAttribute=ele.getAttribute("style")
				je.executeScript("arguments[0].setAttribute('style',arguments[1]);", ele,attributeValue)
			}
		}
		catch(Exception e){
			KeywordUtil.markError("Expection while highlighting Test Object")
		}
	}

	/**
	 * Enter text using javascript library
	 * @param testObject
	 * @param amount
	 * @return
	 */
	@Keyword
	def safeTypeUsingJavascript(TestObject testObject,String amount){
		WebDriver driver = DriverFactory.getWebDriver()
		JavascriptExecutor je=(JavascriptExecutor)driver
		WebElement ele=WebUiCommonHelper.findWebElement(testObject, GlobalVariable.delayForElement)
		je.executeScript("arguments[0].value='${amount}';", ele)
	}

	/**
	 * Highlight element to be interacted	
	 * @param element
	 * @param timeOut
	 * @return
	 */
	@Keyword
	def highLightElement(WebElement element,int timeOut){
		try{
			if(GlobalVariable.HighLightElements.equalsIgnoreCase("true")){
				String attributeValue="border:2px solid red";
				WebDriver driver = DriverFactory.getWebDriver()
				JavascriptExecutor je=(JavascriptExecutor)driver
				String getAttribute=element.getAttribute("style")
				je.executeScript("arguments[0].setAttribute('style',arguments[1]);", element,attributeValue)
			}
		}
		catch(Exception e){

			KeywordUtil.markError("Expection while highlighting Web Element")
		}
	}
	/**
	 * Get text displayed on element
	 * @param testObj
	 * @param waitTime
	 * @return
	 */
	@Keyword
	def safeGetValue(TestObject testObj,int waitTime){
		String sValue=null
		try{
			WebUI.waitForElementPresent(testObj, waitTime)

			WebUI.scrollToElement(testObj, waitTime)
			if(WebUI.verifyElementPresent(testObj, waitTime)){
				highLightElement(testObj,waitTime)
				sValue=WebUI.getAttribute(testObj, 'value')
			}
			else{
				KeywordUtil.markFailed("Unable to element in time - "+waitTime)
			}
		}
		catch(Exception e){

			KeywordUtil.markError("Unable to element in time - "+waitTime)
		}
		return sValue
	}
}
