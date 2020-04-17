package pages
import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.checkpoint.CheckpointFactory
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testcase.TestCaseFactory
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testdata.TestDataFactory
import com.kms.katalon.core.testobject.ObjectRepository
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords

import internal.GlobalVariable
import utilities.SafeActions
import MobileBuiltInKeywords as Mobile
import WSBuiltInKeywords as WS
import WebUiBuiltInKeywords as WebUI

import org.openqa.selenium.WebElement
import org.openqa.selenium.WebDriver
import org.openqa.selenium.By

import com.kms.katalon.core.mobile.keyword.internal.MobileDriverFactory
import com.kms.katalon.core.webui.driver.DriverFactory

import com.kms.katalon.core.testobject.RequestObject
import com.kms.katalon.core.testobject.ResponseObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObjectProperty

import com.kms.katalon.core.mobile.helper.MobileElementCommonHelper
import com.kms.katalon.core.util.KeywordUtil

import com.kms.katalon.core.webui.exception.WebElementNotFoundException


class CustomFields_page {

	WebDriver driver=DriverFactory.getWebDriver()
	def map_Tablecount=[:]

	SafeActions safeActions = new SafeActions()
	/**
	 * Refresh browser
	 */
	@Keyword
	def refreshBrowser() {
		KeywordUtil.logInfo("Refreshing")
		WebDriver webDriver = DriverFactory.getWebDriver()
		webDriver.navigate().refresh()
		KeywordUtil.markPassed("Refresh successfully")
	}

	/**
	 * Click element
	 * @param to Katalon test object
	 */
	@Keyword
	def clickElement(TestObject to) {
		try {
			WebElement element = WebUiBuiltInKeywords.findWebElement(to);
			KeywordUtil.logInfo("Clicking element")
			element.click()
			KeywordUtil.markPassed("Element has been clicked")
		} catch (WebElementNotFoundException e) {
			KeywordUtil.markFailed("Element not found")
		} catch (Exception e) {
			KeywordUtil.markFailed("Fail to click on element")
		}
	}

	/**
	 * Get all rows of HTML table
	 * @param table Katalon test object represent for HTML table
	 * @param outerTagName outer tag name of TR tag, usually is TBODY
	 * @return All rows inside HTML table
	 */
	@Keyword
	def List<WebElement> getHtmlTableRows(TestObject table, String outerTagName) {
		WebElement mailList = WebUiBuiltInKeywords.findWebElement(table)
		List<WebElement> selectedRows = mailList.findElements(By.xpath("./" + outerTagName + "/tr"))
		return selectedRows
	}

	@Keyword
	def setCustomFieldDetails(String fieldName,String screen,String fieldType,String options){
		try{

			if(safeElementVisible(findTestObject('Object Repository/Defined_Custom_Fields_Page/ADD_BUTTON'), GlobalVariable.MEDIUM_WAIT)){
				//if(WebUI.verifyElementPresent(findTestObject('Object Repository/Defined_Custom_Fields_Page/ADD_BUTTON'), 30)){
				safeActions.safeClickWithoutScroll(findTestObject('Object Repository/Defined_Custom_Fields_Page/ADD_BUTTON'), 'Add ',
						(([GlobalVariable.pageLoadTime]) as int[]))
				KeywordUtil.markPassed(" Clicked on Add button")
			}
			else{

				KeywordUtil.markFailed("Enter custom field name")
			}

			safeActions.safeType(findTestObject('Object Repository/Defined_Custom_Fields_Page/CUSTOM_FIELD_NAME'),fieldName,'field name',(([GlobalVariable.pageLoadTime]) as int[]))

			safeActions.safeSelectOptionInDropDownByVisibleText(findTestObject('Object Repository/Defined_Custom_Fields_Page/SCREEN_DROPDOWN'), screen, "Screen", (([GlobalVariable.pageLoadTime]) as int[]))

			safeActions.safeSelectOptionInDropDownByVisibleText(findTestObject('Object Repository/Defined_Custom_Fields_Page/TYPE_DROPDOWN'), fieldType, "Field Type", (([GlobalVariable.pageLoadTime]) as int[]))

			if(fieldType.equalsIgnoreCase("Drop Down")){

				safeActions.safeType(	findTestObject('Object Repository/Defined_Custom_Fields_Page/SELECT_OPTIONS_FIELD'),options,'Dropdown options',(([GlobalVariable.pageLoadTime]) as int[]))
			}
			safeActions.safeClickWithoutScroll(findTestObject('Object Repository/Defined_Custom_Fields_Page/SAVE_BUTTON')	, "save",
					(([GlobalVariable.pageLoadTime]) as int[]))
		}

		catch(Exception e){
			KeywordUtil.markError("Unable to set custom field details")
		}
	}

	@Keyword
	def getRowsCount(){


		WebElement table
		table= driver.findElement(By.xpath("//table[@id='customFieldList']/tbody"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))

		if(rows.size()=='10'){

			safeActions.safeElementVisible(findTestObject('Object Repository/Defined_Custom_Fields_Page/CHECK_ALL'), 30)
			safeActions.safeClickWithoutScroll(findTestObject('Object Repository/Defined_Custom_Fields_Page/DELETE_BUTTON'), "Delete button ", (([GlobalVariable.pageLoadTime]) as int[]))
		}

		return rows.size();
	}


	@Keyword
	def getCustomFieldNameColumnCount(String columnName){

		WebDriver driver=DriverFactory.getWebDriver()
		WebElement table= driver.findElement(By.xpath("//table[@id='customFieldList']/thead"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))
		for(int i=0;i<rows.size();i++){
			List<WebElement> cols=rows.get(i).findElements(By.tagName('th'))
			for(int j=0;j<cols.size();j++){
				if(cols.get(j).getText().equalsIgnoreCase(columnName)){
					map_Tablecount[columnName]=j
				}
			}
		}
	}

	@Keyword
	def verifyCustomField(String fieldName){
		getCustomFieldNameColumnCount("Custom Field Name")

		WebElement table
		table= driver.findElement(By.xpath("//table[@id='customFieldList']/tbody"))
		List<WebElement> rows=table.findElements(By.tagName('tr'))

		for(int i=1;i<rows.size();i++){
			List<WebElement> cols=rows.get(i).findElements(By.tagName('td'))
			String nameOfField = cols.get(map_Tablecount['Custom Field Name']).getText()
			if(nameOfField.equalsIgnoreCase(fieldName)){
				//WebElement element=driver.findElement(By.linkText(fieldName))
				KeywordUtil.markPassed("Custom field is present ")

				break
			}
		}
		return rows.size
	}
}


