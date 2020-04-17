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

public class Product extends CommonActions {

	/**
	 * CLick on Create Button in Product page
	 * @return
	 */
	@Keyword
	def clickOnCreateButton(){

		safeClickWithoutScroll(findTestObject('Home_Page/CREATE_BUTTON'), "CREATE_BUTTON", GlobalVariable.MEDIUM_WAIT)
	}




	/**
	 * Select Assignment policy from the list
	 * @param sAssignmentPolicy
	 * @return
	 */
	@Keyword
	def selectAssignmentPolicy(String sAssignmentPolicy){
		selectListDropdown("Assignment Policy", sAssignmentPolicy)
	}

	/**
	 * Select Applies to Inventory Yes or no
	 * @param sAppliesToInventory
	 * @return
	 */
	@Keyword
	def selectAppliesToInventory(String sAppliesToInventory){
		selectSwitch(' Applies To Inventory', sAppliesToInventory)
	}

	/**
	 * Select Applies to Asset Yes or No
	 * @param sAppliesToAsset
	 * @return
	 */
	@Keyword
	def selectAppliesToAsset(String sAppliesToAsset){
		selectSwitch(' Applies To Asset', sAppliesToAsset)
	}


	/**
	 * Select Asset Details
	 * @param sBarcodeRequirement
	 * @param sSerialNumberRequirement
	 * @param sLotNumberRequirement
	 * @return
	 */
	@Keyword
	def selectAssetDetails(String sBarcodeRequirement,String sSerialNumberRequirement,String sLotNumberRequirement){
		selectListDropdown("Barcode Requirement", sBarcodeRequirement)
		selectListDropdown("Serial Number Requirement", sSerialNumberRequirement)
		selectListDropdown("Lot Number Requirement", sLotNumberRequirement)
	}

	/**
	 * Enter data in to Supplier field
	 * @param sSupplier
	 * @return
	 */
	@Keyword
	def setSupplier(String sSupplier){
		setTextFieldFromTheList(' Supplier', sSupplier)
	}

	/**
	 * Click on Manufacturer Heading
	 * @return
	 */
	@Keyword
	def clickManufacturerHeading(){
		clickOnHeading('Manufacturer')
	}

	/**
	 * Select Operational Equipment > Fire arms > Pistol
	 * @return
	 */
	@Keyword
	def openFirearmsPistol(){
		safeClickWithoutScroll(findTestObject('Product_Page/OPERATIONAL_EQUIPMENT'), 'Operationl Equipment',GlobalVariable.MEDIUM_WAIT)
		safeClickWithoutScroll(findTestObject('Product_Page/FIREARMS'), 'FIREARMS', GlobalVariable.MEDIUM_WAIT)

		safeClickWithoutScroll(findTestObject('Product_Page/PISTOL'), 'PISTOL', GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Enter data into Asset Class field
	 * @param sAssetClass
	 * @return
	 */
	@Keyword
	def setAssetClass(String sAssetClass){
		setTextFieldFromTheList(' Asset Class', sAssetClass)
	}

	/**
	 * Enter product Name and Type
	 * @param sName
	 * @param sProductType
	 * @return
	 */
	@Keyword
	def enterProductDetails(String sName,String sProductType){
		setTextFieldFromTheList(' Product Type', sProductType)
		Thread.sleep(3000)
		safeType(findTestObject('Object Repository/Product_Page/NAME_INPUT'), sName, sName, GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click on Product Supplier Heading tab
	 * @return
	 */
	@Keyword
	def clickProductSupplierHeading(){
		clickOnHeading('Product Supplier')
	}

	/**
	 * Select Operational Equipment > Fire arms > Pistol
	 * @return
	 */
	@Keyword
	def openOperationalSafetyOCSpray(){
		safeClickWithoutScroll(findTestObject('Product_Page/OPERATIONAL_EQUIPMENT'), 'Operationl Equipment',GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(findTestObject('Product_Page/OPERATIONAL_SAFETY'), 'Operational Safety', GlobalVariable.MEDIUM_WAIT)
		WebUI.scrollToElement(findTestObject('Object Repository/Product_Page/NightVisionEquipment'), GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(findTestObject('Object Repository/Product_Page/OC_SPRAY'),"OC Spray",GlobalVariable.MEDIUM_WAIT)
		
	}
	@Keyword
	def getProductNames(){
		String locator="//tbody[@role='rowgroup']//tr//td[3]"

		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		List<String> productNames=new ArrayList<String> ()
		for(WebElement element: elements){
			String productName=element.getText()
			if(productName.startsWith("OC spray")){
			productNames.add(productName)
			}
		}
		println productNames
		return productNames;
	}
}
