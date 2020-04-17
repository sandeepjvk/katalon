package utilities

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class DynamicLocators extends SafeActions{

	public String tabLocator="//*[contains(text(),'%s')]/../../..//*[contains(@class,'icon')]"
	/**
	 * Get the locator;
	 * @param tabValue
	 * @return
	 */
	@Keyword
	def createDynamicLocators(String tabValue){
		tabLocator=String.format(tabLocator,tabValue)

		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,tabLocator)
		return testObj
	}
}
