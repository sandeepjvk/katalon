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
import com.kms.katalon.core.testobject.ConditionType
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import internal.GlobalVariable

public class PersonSearch extends CommonActions{
	
	/**
	 * Click On Create Button
	 * @return
	 */
	@Keyword
	def ClickOnCreateButton(){
		safeClickWithoutScroll(findTestObject('Person_Search_Page/CREATE_BUTTON'),"dateSelect", GlobalVariable.MEDIUM_WAIT)
	}
	
	/**
	 * Validate Person Saved
	 * @param PersonName
	 * @return
	 */
	@Keyword
	def ValidatePersonSaved(String PersonName){
		String locator="//td[contains(text(),'%s')]"
		locator=String.format(locator,PersonName)
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeElementVisible(testObj, GlobalVariable.MEDIUM_WAIT)
	}
}