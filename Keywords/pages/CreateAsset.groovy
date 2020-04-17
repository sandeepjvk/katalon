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
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.text.SimpleDateFormat

import org.openqa.selenium.By

import org.openqa.selenium.WebDriver

import internal.GlobalVariable

public class CreateAsset extends CommonActions{
	/**
	 * Enter data in to Product field
	 * @param productName
	 * @return
	 */
	@Keyword
	def setProductName(String productName){
		setTextFieldFromTheList(' Product',  productName)
		String getBarcode=safeGetValue( findTestObject('Object Repository/Create_Asset_Page/BARCODE_VALUE'), GlobalVariable.MEDIUM_WAIT)
		return getBarcode
	}

	/**
	 * Validate Navigation To Create Asset Page
	 * @return
	 */
	@Keyword
	def ValidateNavigationToCreateAssetPage(){
		validate_page_headding("Create Asset")
	}
	/**
	 * Set Status
	 * @return
	 */
	@Keyword
	def setStatus(String status){
		selectVlaueFromDropdown('Status',status)
	}
	
	/**
	 * Enter The Details To Create Asset
	 * @param productName
	 * @param barcode
	 * @param value
	 * @param lotNumber
	 * @param lotExpiry
	 * @return
	 */
	@Keyword
	def enterTheDetailsToCreateAsset(String productName,String barcode,String value,String lotNumber,String lotExpiry){
		setTextFieldFromTheList('Product', productName)
		setTextField('Barcode',barcode)
		selectVlaueFromDropdown('Status',value)
		setTextField('Lot Number',lotNumber)
		setTextField('Lot Expiry',lotExpiry)
	}

	/**
	 * Enter Supplier Value
	 * @param sSupplierValue
	 * @return
	 */
	@Keyword
	def enterSupplierValue(String sSupplierValue){
		setTextField('Supplier', sSupplierValue)
	}
	
	/**
	 * Enter Org Details
	 * @param organizationName
	 * @return
	 */
	@Keyword
	def enterOrgDetails(String organizationName){
		setTextFieldFromTheList('Organisation Unit',organizationName)
	}

	/**
	 * Tick Notes CheckBox
	 * @param flag
	 * @return
	 */
	@Keyword
	def tickNotesCheckBox(boolean flag){
		clickOnSubHeading("Notes")
		/*		WebDriver driver = DriverFactory.getWebDriver();
		 boolean isChecked= driver.findElement(By.cssSelector("[type='checkbox']")).isSelected();*/
		//safeCheckForElement(findTestObject('Create_Asset_Page/VALIDATE_UNCHECK'),flag, GlobalVariable.MEDIUM_WAIT)
		safeCheckForElement(findTestObject('Create_Asset_Page/CREATE_NOTES_CHECKBOX'),flag, GlobalVariable.MEDIUM_WAIT)

	}

	/**
	 * Validate Purchase Date Is Today
	 * @return
	 */
	@Keyword
	def validatePurchaseDateIsToday() {
		String date=safeGetValue(findTestObject('Object Repository/Create_Asset_Page/PURCHASE_DATE'), GlobalVariable.MEDIUM_WAIT)
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateValue = new Date();
		String date_Value= dateFormat.format(dateValue)
		if(date_Value.startsWith("0")){
			date_Value = date_Value.replaceFirst("0", "")
		}
		assertion(date.equals(date_Value))
	}
}
