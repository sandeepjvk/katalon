package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.List
import java.text.SimpleDateFormat
import org.openqa.selenium.Keys as Keys
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
import org.openqa.selenium.interactions.Actions
import utilities.DynamicLocators
import utilities.SafeActions
import pages.CommonActions
import org.openqa.selenium.By

public class IssueReturn extends CommonActions{
	String tableRowLocator="//tbody[@role='rowgroup'] //tr[@role='row']"

	/**
	 * Enter data in to Return Location field
	 * @param returnLocation
	 * @return
	 */
	@Keyword
	def setReturnLocationDetails(String returnLocation){
		setTextFieldFromTheList(' Return Location', returnLocation)
	}
	/**
	 * Select Select Person or Item field Triangle Icon
	 * Select Person option
	 * Select Person Name
	 * @param personeName
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setPersonDetails(String personeName){
		safeClickwithScroll(findTestObject('Object Repository/IssueAndReturn_Page/SELECT_PERSON_OR_ITEM_TRIANGLE_ICON'),"Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(findTestObject('Object Repository/IssueAndReturn_Page/PERSON'), 'Person',GlobalVariable.MEDIUM_WAIT)

		String PersonNameLocator=tableRowLocator+"//td[text()='%s']"
		TestObject PersonNameObj=getLocator(PersonNameLocator,personeName)
		highLightElement(PersonNameObj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(PersonNameObj,personeName,GlobalVariable.MEDIUM_WAIT)

		safeClickwithScroll( findTestObject('Object Repository/IssueAndReturn_Page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Set item barcodes in Select Person or Item field
	 * @param itemBarcodes
	 * @return
	 */
	@Keyword
	def setItemDetails(List<String> itemBarcodes){
		for(String itemBarcode : itemBarcodes){
			setTextFieldAndEnter('Select Person or Item',itemBarcode)
		}
	}
	/**
	 * Click on Save button
	 * @return
	 */
	@Keyword
	def clickOnSave(){
		safeClickWithoutScroll(findTestObject('Object Repository/IssueAndReturn_Page/SAVE_BUTTON'),"Save", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Validate Navigation to Issue and Return page
	 * @return
	 */
	@Keyword
	def validateNavigationToIssueAndReturnPage(){
		safeElementVisible( findTestObject('Object Repository/IssueAndReturn_Page/ISSUE_AND_RETURN_TITLE'), GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 *Remove Issued Item by clicking remove icon
	 *@return
	 */
	@Keyword
	def clickOnIssueItemsRemoveIcon(){
		safeClickWithoutScroll( findTestObject('Object Repository/IssueAndReturn_Page/REMOVE_ICON'),"Remove Icon",GlobalVariable.MEDIUM_WAIT)
		Thread.sleep(500)
	}

	/**
	 * Validate Return Location Name
	 * @param expectedReturnLocation
	 * @return
	 */
	@Keyword
	def validateReturnLocation(String expectedReturnLocation){
		String getReturnLocation=safeGetValue( findTestObject('Object Repository/IssueAndReturn_Page/RETURN_LOCATION_VALUE'), GlobalVariable.MEDIUM_WAIT)
		assertion(getReturnLocation.equals(expectedReturnLocation))
	}
	/**
	 * Validate Person Name
	 * @param expectedPersonName
	 * @return
	 */
	@Keyword
	def validatePersonName(String expectedPersonName){
		String getPersonName=safeGetValue( findTestObject('Object Repository/IssueAndReturn_Page/PERSON_NAME'), GlobalVariable.MEDIUM_WAIT)
		assertion(getPersonName.equals(expectedPersonName))
	}
	/**
	 * Validate Issued Items Count
	 * @param issuedItemCount
	 * @return
	 */
	@Keyword
	def validateIssuedItemsCount(String issuedItemCount){
		String itemCountText="Showing all %s results"
		String expectedItemCountText=String.format(itemCountText,issuedItemCount)
		String getItemsCountText=safeGetText( findTestObject('Object Repository/IssueAndReturn_Page/ISSUED_ITEMS_COUNT_TEXT'), GlobalVariable.MEDIUM_WAIT)
		assertion(getItemsCountText.equals(expectedItemCountText))
	}
	/**
	 * Validate Date Field
	 * @param @return
	 */
	def validateDateField(){
		String date=safeGetValue(findTestObject('Object Repository/IssueAndReturn_Page/DATE_VALUE'), GlobalVariable.MEDIUM_WAIT)
		boolean status=date.isEmpty()
		assertion( status.equals(false))
	}
	/**
	 * Set Return Cycle Dropdown Value
	 * returnCycleValue
	 * @return
	 */
	@Keyword
	def setReturnCycleDropdownValue(String returnCycleValue){
		selectListDropdown(" Return Cycle", returnCycleValue)
	}
	/**
	 * Validate Issued Items Barcodes
	 * @param barcodes
	 * @return
	 */
	@Keyword
	def verifyIssuedItems(List<String> barcodes){
		String recordLocator = "//span[text()='Issued Items']/../..//table[@role='grid'] //td[text()='%s']"
		for(String barcode : barcodes){
			TestObject barcodeObj=getLocator(recordLocator,barcode)
			highLightElement(barcodeObj, GlobalVariable.MEDIUM_WAIT)
			safeElementVisible(barcodeObj, GlobalVariable.MEDIUM_WAIT)
		}
	}
}
