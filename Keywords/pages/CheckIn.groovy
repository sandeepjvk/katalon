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

public class CheckIn extends CommonActions{
	String tableRowLocator="//tbody[@role='rowgroup'] //tr[@role='row']"
	String commentFieldLocator="//div[contains(text(),' Comment')]/../..//input"
	String ConditionDropDownLocator="//*[contains(text(),' Condition')]/../..//*[contains(@class,'icon')]"
	String itemStatusLocator="[class='hc-card-asset-status'] [ref='refTextboxElement']"

	/**
	 * Enter data in to Site field
	 * @param site
	 * Enter data in to Default Location field
	 * @param defaultLocation
	 * @return
	 */
	@Keyword
	def setSiteAndDefaultLocationDetails(String site,String defaultLocation){
		setTextFieldFromTheList(' Site', site)
		setTextFieldFromTheList(" Default Location", defaultLocation)
	}
	/**
	 * Select Person field Triangle Icon
	 * Select Person Name
	 * @param fromLocation
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setDeliveredByPersonAndReference(String personeName,String reference){
		setPersonAndReferenceDetails(tableRowLocator,personeName,reference)
	}
	/**
	 * Select Select Item field Triangle Icon
	 * Select 'n' number of barcodes from the barcodes list
	 * @param barcode
	 * @param numberOfBarcodes
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setSelectItemBarcode(List<String> barcodes,int numberOfBarcodes){
		setItemBarcodeValue(tableRowLocator,barcodes,numberOfBarcodes)
	}
	/**
	 * Click on Condition Dropdown icons
	 * Select the value from the dropdown
	 * @param conditionValues
	 * @return
	 */
	@Keyword
	def setConditionDetails(String conditionValues){
		setConditionValue(ConditionDropDownLocator,conditionValues)
	}

	/**
	 * Enter data in to Comment fields
	 * @param commentsList
	 * @return
	 */
	@Keyword
	def setCommentDetails(String commentsList){
		setCommentValue(commentFieldLocator,commentsList)
	}

	/**
	 * Validate Check In Site name
	 * @param expectedSiteName
	 * @return
	 */

	@Keyword
	def validateSiteName(String expectedSiteName){
		String getSiteName=safeGetValue(findTestObject('Object Repository/CheckIn_Page/SITE_NAME'), GlobalVariable.MEDIUM_WAIT)
		assertion(getSiteName.equals(expectedSiteName))
	}
	/**
	 * Validate Date, Delivery Person and ID Fields
	 * @return
	 */
	@Keyword
	def validateDateAndDeliveryPersonAndIDFields(){
		validateGeneratedIdField()
		validateDateField()
		validateDeliveryPersonField()
	}
	/**
	 * Validate Generated ID Field
	 * @return
	 */
	def validateGeneratedIdField(){
		String getID=safeGetText(findTestObject('Object Repository/CheckIn_Page/VALIDATE_ID'), GlobalVariable.MEDIUM_WAIT)
		boolean status=getID.isEmpty()
		assertion( status.equals(false))
	}
	/**
	 * Validate Date Field
	 * @return
	 */
	def validateDateField(){
		String date=safeGetValue(findTestObject('Object Repository/CheckIn_Page/VALIDATE_DATE'), GlobalVariable.MEDIUM_WAIT)
		boolean status=date.isEmpty()
		assertion( status.equals(false))
	}
	/**
	 * Validate Delivery Person Field
	 * @return
	 */
	def validateDeliveryPersonField(){
		String deliveryPersonName=safeGetValue(findTestObject('Object Repository/CheckIn_Page/VALIDATE_DELIVERY_PERSON_NAME'), GlobalVariable.MEDIUM_WAIT)
		boolean status=deliveryPersonName.isEmpty()
		assertion( status.equals(false))
	}

	/**
	 * Validate CheckIn Items barcodes 
	 * @param barcodes
	 * @return
	 */
	@Keyword
	def verifyCheckInItems(List<String> barcodes,int numberOfBarcodes){
		String locator = "//table[@role='grid'] //td[text()='%s']"
		int i=0
		for(String barcode : barcodes){
			TestObject barcodeObj=getLocator(locator,barcode)
			highLightElement(barcodeObj, GlobalVariable.MEDIUM_WAIT)
			safeElementVisible(barcodeObj, GlobalVariable.MEDIUM_WAIT)
			i=i+1;
			if(i==numberOfBarcodes){
				break
			}
		}
	}
	/**
	 * Validate Navigation to Check In Page
	 * @return
	 */
	@Keyword
	def validateNavigationToCheckInPage(){
		safeElementVisible( findTestObject('Object Repository/CheckIn_Page/CHECK_IN_TITLE'), GlobalVariable.MEDIUM_WAIT)
	}
}
