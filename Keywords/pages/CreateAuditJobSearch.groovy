package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.webui.driver.DriverFactory
import org.openqa.selenium.Keys as Keys

import internal.GlobalVariable

public class CreateAuditJobSearch  extends CommonActions{

	/**
	 * Create Audit Job
	 * @param description
	 * @return
	 */
	@Keyword
	def createAuditJob(String description){
		setDescriptionField(' Description',description)
		clickOnSaveAuditJob()
	}

	/**
	 * Click On Save Audit Job
	 * @return
	 */
	@Keyword
	def clickOnSaveAuditJob(){
		safeClickWithoutScroll(findTestObject('Create_Audit_Job_Search_Page/SAVE_BUTTON_AUDIT_JOB'),"Save", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click On Cancel Button
	 * @return
	 */
	@Keyword
	def clickOnCancelButton(){
		safeClickWithoutScroll( findTestObject('Create_Audit_Job_Search_Page/CANCEL_BUTTON') ,"Cancel", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click On Add Button
	 * @return
	 */
	@Keyword
	def clickOnAddButton(){
		safeClickWithoutScroll( findTestObject('Object Repository/Create_Audit_Job_Search_Page/ADD_BUTTON') ,"Save", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click On Add Button
	 * @param locationNames
	 * @return
	 */
	@Keyword
	def verifyTableColumnData(String locationNames){
		String[] names= locationNames.split(',');
		String locator = "//table[@role='grid'] //td[text()='%s']"
		for(String name : names){
			String locatorNew= String.format(locator,name)
			TestObject testObj=new TestObject()
			testObj.addProperty("xpath",ConditionType.EQUALS,locatorNew)
			highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
			safeElementVisible(testObj, GlobalVariable.MEDIUM_WAIT)
		}
	}

	/**
	 * Click On Add Icon
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
	 * Verify Description
	 * @param description
	 * @return
	 */
	@Keyword
	def verifyDescription(String description){
		String Description=safeGetValue(findTestObject('Object Repository/Create_Audit_Job_Search_Page/DESCRIPTION'), GlobalVariable.MEDIUM_WAIT)
		assertion( Description.equals(description))
	}

	/**
	 * Select Multiple Locations
	 * @param locationNames
	 * @return
	 */
	@Keyword
	def selectMultipleLocations(String locationNames){
		String[] names= locationNames.split(',');
		String locator = "//*[@role='row']//*[text()='%s']"
		Actions actions = new Actions(DriverFactory.getWebDriver())
		for(String name : names){
			String locatorNew= String.format(locator,name)
			actions.keyDown(Keys.LEFT_CONTROL).click(DriverFactory.getWebDriver().findElement(By.xpath(locatorNew))).build().perform()
		}
	}

	/**
	 * Click on Participants Heading
	 * @return
	 */
	@Keyword
	def clickOnParticipantsHeading(){
		clickOnHeading("Participants")
	}
	/**
	 * Validate Description And Allow Asset Capture Fields
	 */
	@Keyword
	def validateDescriptionAndAllowAssetCaptureFields(){
		validateTheFieldsName("Description")
		validateTheFieldsName("Allow Asset Capture")
	}

	/**
	 * Set Audit Job Participant Details
	 * @param personName
	 * @param capability
	 * @return
	 */
	@Keyword
	def setAuditJobParticipantDetails(String personName,String capability){
		setTextFieldFromTheList(' Person', personName)
		selectListDropdown("Capability", capability)
	}

	/**
	 * Click on Save button
	 * @return
	 */
	@Keyword
	def clickOnParticipantSaveButton(){
		safeClickWithoutScroll(findTestObject('Object Repository/Create_Audit_Job_Search_Page/SAVE_BUTTON'),"Save", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Validate Navigation to Create Audit Job
	 * @return
	 */
	@Keyword
	def validateNavigationToCreateAuditJobPage(){
		safeElementVisible(findTestObject('Object Repository/Create_Audit_Job_Search_Page/CREATE_AUDIT_JOB_TITLE') , GlobalVariable.MEDIUM_WAIT)
	}
}
