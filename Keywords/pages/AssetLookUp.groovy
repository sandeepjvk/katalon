package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.annotation.Keyword
import org.openqa.selenium.WebDriver
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
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject



public class AssetLookUp extends CommonActions{
	String tableRowLocator="//tbody[@role='rowgroup'] //tr[@role='row']"
	/**
	 * set Asset barcode In Asset Lookup Page
	 * @param name
	 */
	@Keyword
	def setAssetbarcode(String barcode){
		selectItemFromBarcode(barcode)
		//setTextFieldAndEnter('Select Asset', barcode)
	}
	/**
	 * validate The Reserved For Person In Asset Lookup Page
	 * @param name
	 */
	@Keyword
	def validateTheReservedForPerson(String name){
		validateTheField("Reserved For",name)
	}
	/**
	 * validate The Assigned Person In Asset Lookup Page
	 * @param name
	 */
	@Keyword
	def validateTheAssignedPerson(String name){
		validateTheField("Assigned Person",name)
	}

	/**
	 * validate Loaned To Org Unit In Asset Lookup Page
	 */
	@Keyword
	def validateLoanedToOrgUnit(){
		validateTheField("Loaned To Org Unit","Organisation_Units")
	}
	/**
	 * validate The Current Location Shouldnt Have Any Transaction In Asset Lookup Page
	 */
	@Keyword
	def validateTheCurrentLocationShouldntHaveAnyTransaction(){
		String locator="//div[contains(text(),'%s')]/../../..//div/input"
		TestObject testObj=getLocator(locator,"Current Location")
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		String value=safeGetValue(testObj, GlobalVariable.MEDIUM_WAIT)
		assertion(!(value.contains('from')&&value.contains('to')))
	}
	/**
	 * select Item From Barcode In Asset Lookup Page
	 * @param barcode
	 */
	@Keyword
	def selectItemFromBarcode(String barcode){
		safeClickwithScroll(findTestObject('Object Repository/AssetLookUp_Page/SELECT_ITEM_TRIANGLE_ICON') ,"Select Item Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		String locator="//tbody[@role='rowgroup']//tr[@role='row']//td[text()='%s']"
		TestObject Obj=getLocator(locator,barcode)
		safeClickwithScroll(findTestObject('CommonActions_Page/SHOW_MORE_RESULTS'),"Show More Results", GlobalVariable.MEDIUM_WAIT)
		highLightElement(Obj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(Obj,barcode,GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll( findTestObject('Transfer_page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}
}
