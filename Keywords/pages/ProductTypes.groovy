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

import internal.GlobalVariable
import utilities.SafeActions

public class ProductTypes {

	SafeActions safe = new SafeActions();
	@Keyword
	def clickOnCreateButton(){

		safe.safeClickWithoutScroll(findTestObject('Object Repository/Product_Page/CREATE_BUTTON'), "CREATE_BUTTON", GlobalVariable.MEDIUM_WAIT)
	}

	@Keyword
	def enterProductDetails(String sName,String sProductType){

		safe.safeType(findTestObject('Object Repository/Product_Page/NAME_INPUT'), sName, sName, GlobalVariable.MEDIUM_WAIT)

		WebUI.mouseOver(findTestObject('Object Repository/Product_Page/PRODUCT_TYPE'))
		safe.safeClickWithoutScroll(findTestObject('Object Repository/Product_Page/PRODUCT_TYPE'), "PRODUCT_TYPE", GlobalVariable.MEDIUM_WAIT)
		safe.safeType(findTestObject('Object Repository/Product_Page/PRODUCT_TYPE'), sProductType, sProductType, GlobalVariable.MEDIUM_WAIT)
	}
}
