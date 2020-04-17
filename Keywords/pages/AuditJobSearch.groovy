package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebDriver
import internal.GlobalVariable
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.util.KeywordUtil

import utilities.DynamicLocators
import utilities.SafeActions
import pages.CommonActions
import org.openqa.selenium.By

public class AuditJobSearch extends CommonActions{

	/**
	 * Click on menuitem icon of given code
	 * @param code
	 * Click on Edit button
	 * @return
	 */
	@Keyword
	def clickEditAuditJob(String code=null){
		String locator
		if(code==null){
			locator="//*[@role='rowgroup'] //li[@role='menuitem']"
		}
		else{
			locator="//td[contains(text(),'%s')]/..//li[@role='menuitem']"
			locator=String.format(locator,code)
		}

		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(testObj,"menuitem", GlobalVariable.MEDIUM_WAIT)

		String locatorValue="//span[contains(text(),'Edit')]"
		TestObject testObj2=new TestObject()
		testObj2.addProperty("xpath",ConditionType.EQUALS,locatorValue)
		highLightElement(testObj2, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(testObj2,"Edit", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click on Participants Heading
	 * @param tabName
	 * @return
	 */

	@Keyword
	def clickOnParticipantsHeading(){
		clickOnHeading("Participants")
	}

	/**
	 * Click on Add icon
	 * @return
	 */
	@Keyword
	def clickOnAddIcon(){
		String locator="ui-command-button[class='au-target'] button[data-id='CREATE']"
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.cssSelector(locator))
		for(WebElement element: elements){
			if(element.isDisplayed() && element.isEnabled()) {
				safeClickWithoutScroll(WebUI.convertWebElementToTestObject(element),"AddIcon", GlobalVariable.MEDIUM_WAIT)
			}
		}
	}


	/**
	 * Enter Person name in Person field
	 * @param personName
	 *  Select Capability
	 * @param capability
	 * @return
	 */
	@Keyword
	def setAuditJobParticipantDetails(String personName,String capability){
		setTextFieldFromTheList(' Person', personName)
		selectListDropdown("Capability", capability)
	}

	/**
	 * Verify Table data
	 * @param data
	 * @return
	 */
	@Keyword
	def verifyTableColumnData(String data){
		TestObject dataObj=getLocator("//table[@role='grid'] //td[text()='%s']",data)
		highLightElement(dataObj, GlobalVariable.MEDIUM_WAIT)
		safeElementVisible(dataObj, GlobalVariable.MEDIUM_WAIT)
	}
}



