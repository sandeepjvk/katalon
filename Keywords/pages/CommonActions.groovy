package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import java.util.List

import org.openqa.selenium.Keys as Keys
import org.checkerframework.checker.nullness.qual.AssertNonNullIfNonNull
import org.eclipse.persistence.internal.oxm.record.json.JSONParser.value_return
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable
import utilities.SafeActions

public class CommonActions extends SafeActions {
	String createDateAndTime="//td[contains(text(),'Create')]/preceding-sibling::td[contains(text(),'%s')]"
	String viewDateAndTime="//td[contains(text(),'View')]/preceding-sibling::td[contains(text(),'%s')]"

	/**
	 * Select Text field data and press enter
	 * @param textFieldName
	 * @param value
	 * @return
	 */
	@Keyword
	def setTextFieldAndEnter(String textFieldName,String value){
		String locator="//div[contains(text(),'%s')]/../../..//input"
		locator=String.format(locator,textFieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(testObj,value, FailureHandling.STOP_ON_FAILURE)
		Actions actions = new Actions(DriverFactory.getWebDriver())
		actions.sendKeys(Keys.ENTER).click().build().perform()
	}
	/**
	 * Validate Page Title
	 * @param title
	 * @return
	 */
	@Keyword
	def validate_page_headding(String title) {
		String locator="//div[@ref='refTitleCaption'][text()='%s']"
		locator=String.format(locator,title)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeElementVisible(testObj, GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Click on Save Button
	 * @return
	 */
	@Keyword
	def clickOnSaveButton(){
		safeClickWithoutScroll(findTestObject('Object Repository/Create_Person_Page/SAVE_BUTTON'),"Save", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Click on Save and Close Button
	 * @return
	 */
	@Keyword
	def clickOnSaveAndCloseButton(){
		safeClickWithoutScroll(findTestObject('Object Repository/Create_Person_Page/SAVE_AND_CLOSE'),"SaveAndClose", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Click on Tab
	 * @param tabName
	 * @return
	 */
	@Keyword
	def clickOnTab(String tabName){
		String tabLocator='#strip-menu .nav.navbar-nav .hc-root-menu-item'
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.cssSelector(tabLocator))
		for(WebElement element: elements){

			if(element.getText().equals(tabName)) {
				safeWebDriverClick(element,tabName,GlobalVariable.MEDIUM_WAIT)
				break;
			}
		}
	}

	/**
	 * Select Text field data
	 * @param textFieldName
	 * @param value
	 * @return
	 */
	@Keyword
	def setTextField(String textFieldName,String value){
		String locator="//div[contains(text(),'%s')]/../../..//input"
		locator=String.format(locator,textFieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
		if(!(value.isEmpty())){
			WebUI.setText(testObj,value, FailureHandling.STOP_ON_FAILURE)
		}
	}
	/**
	 * Choose a option from the field
	 * @param fieldName
	 * @param value
	 * @return
	 */
	@Keyword
	def chooseOption(String fieldName,String value){
		String locator="//div[contains(text(),'%s')]/../../.."
		locator=String.format(locator,fieldName)
		locator=locator+"//span[text()='%s']"
		locator=String.format(locator,value)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Click on Sub-Heading 
	 * @param subHeaddingName
	 * @return
	 */

	@Keyword
	def clickOnSubHeading(String subHeaddingName){
		String locator="//ul[@ref='refHeadings']//a[text()='%s']"
		locator=String.format(locator,subHeaddingName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Select Discription field and Enter data
	 * @param textFieldName
	 * @param value
	 * @return
	 */
	@Keyword
	def setDescriptionField(String textFieldName,String value){
		String locator="//div[contains(text(),'%s')]/../../..//textarea"
		locator=String.format(locator,textFieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(testObj,value, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Click on add icon
	 * @return
	 */
	@Keyword
	def clickOnAddIcon(){
		safeClickWithoutScroll(findTestObject('Object Repository/AuditJobSearch_Page/ADD_ICON'),"AddIcon", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Click on dropdown field icon
	 * @param dropdownName
	 * Select a value from dropdown
	 * @param dropDownValue
	 * @return
	 */
	@Keyword
	def selectVlaueFromDropdown(String dropdownName, String dropDownValue){
		String locator="(//div[contains(text(),'%s')]/../../..//span[contains(@class,'down')])[last()]"
		locator=String.format(locator,dropdownName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)

		locator="(//div[@class='k-list-scroller']/ul/li[text()='%s'])[last()]"
		locator=String.format(locator,dropDownValue)
		testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Verify Table data
	 * @param tableHeader
	 * @param data
	 * @return
	 */
	@Keyword
	def verifyTableColumnData(String tableHeader,String data){

		String locator="//tr[@role='row']//th"

		WebDriver webDriver = DriverFactory.getWebDriver()

		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		int i=1
		boolean flag = false
		for(WebElement element: elements){
			if(element.getText().equalsIgnoreCase(tableHeader)){
				String locator1 ="//table[@role='grid']//td["+i+"]"
				List<WebElement> elements1 = webDriver.findElements(By.xpath(locator1))
				for(WebElement element1 : elements1){

					if (element1.getText().equals(data)){
						highLightElement(element1,GlobalVariable.MEDIUM_WAIT)
						KeywordUtil.markPassed(data + "Found in the table")
						break
					}
				}
			}

			i=i+1
		}
	}

	/**
	 * Click on Heading 
	 * @param headingName
	 * @return
	 */
	@Keyword
	def clickOnHeading(String headingName){

		String tabLocator="[ref='refHeadings'] a"

		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.cssSelector(tabLocator))
		for(WebElement element: elements){

			if(element.getText().equals(headingName)) {

				safeWebDriverClick(element,headingName,GlobalVariable.MEDIUM_WAIT)
				break;
			}
		}
	}

	/**
	 * Select Text field data from the list
	 * @param textFieldName
	 * @param value
	 * @return
	 */
	@Keyword
	def setTextFieldFromTheList(String textFieldName,String value){
		String locator="//div[contains(text(),'%s')]/../../..//input"
		locator=String.format(locator,textFieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
		WebUI.setText(testObj,value, FailureHandling.STOP_ON_FAILURE)

		String locatorValue="//div[contains(text(),'"+value+"')]"
		Thread.sleep(500)

		TestObject testObj1=new TestObject()
		testObj1.addProperty("xpath",ConditionType.EQUALS,locatorValue)

		WebUI.click(testObj1, FailureHandling.STOP_ON_FAILURE)
	}


	/**
	 * Select List dropdown
	 * @param dropdownName
	 * @param value
	 * @return
	 */
	@Keyword
	def selectListDropdown(String dropdownName,String value){
		String locator="//*[contains(text(),'%s')]/../../..//*[contains(@class,'icon')]"
		locator=String.format(locator,dropdownName)
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		safeClickWithoutScroll(testObj, dropdownName, GlobalVariable.MEDIUM_WAIT)

		String locatorValue="//*[@aria-hidden='false']//li[contains(text(),'"+value+"')]"
		Thread.sleep(500)

		TestObject testObj1=new TestObject()
		testObj1.addProperty("xpath",ConditionType.EQUALS,locatorValue)

		WebUI.click(testObj1, FailureHandling.STOP_ON_FAILURE)
	}

	/**
	 * Select Switch Options in the application
	 * @param switchName
	 * @param value
	 * @return
	 */
	@Keyword
	def selectSwitch(String switchName,String value){
		String locator="//*[contains(text(),'%s')]/../../..//div[@ref='refSwitch']//span[@class='hc-switch-caption']"
		locator=String.format(locator,switchName)
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		for(WebElement element: elements){

			if(element.getText().equalsIgnoreCase(value)) {
				highLightElement(element, 0)
				safeWebDriverClick(element,switchName,GlobalVariable.MEDIUM_WAIT)
				break;
			}
		}
	}

	/**
	 * Select Triangle Icon of Text field 
	 * @return
	 */
	@Keyword
	def selectTriangleIconOfField(String textFieldName){
		String locator="//span[contains(text(),'%s')]/../../../..//button[@type='button']"

		locator=String.format(locator,textFieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.click(testObj, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Generate xpath Locator
	 * @param locator
	 * @param value
	 * @return
	 */
	@Keyword
	def getLocator(String locator,String value){
		locator=String.format(locator,value)
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		return testObj
	}

	/**
	 * Validate Asseration
	 * @return
	 */
	@Keyword
	def assertion(boolean status){
		if(status){
			KeywordUtil.logInfo("Assertion passed")
		}
		else{
			KeywordUtil.logInfo("Assertion failed")
			KeywordUtil.logInfo("Aborting test case due to assertion failed")
		}
		assert status
	}
	/**
	 * Select Person field Triangle Icon
	 * Select Person Name
	 * @param fromLocation
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setPersonAndReferenceDetails(String tableRowLocator,String personeName,String reference){
		selectTriangleIconOfField(' Person ')
		String locator
		if(personeName=='null'){
			locator=tableRowLocator
		}
		else{
			locator=tableRowLocator+"//td[text()='%s']"
		}
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(testObj, "Barcode",GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll( findTestObject('Object Repository/CheckIn_Page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
		setDescriptionField(' Reference', reference)
	}
	/**
	 * Select Select Item field Triangle Icon
	 * Select Barcode
	 * @param barcode
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setItemBarcodeValue(String tableRowLocator,List<String> barcodes,int numberOfBarcodes){

		safeClickwithScroll(findTestObject('Object Repository/CheckIn_Page/SELECT_ITEM_TRIANGLE_ICON') ,"Select Item Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		String barcodeLocator=tableRowLocator+"//td[text()='%s']"
		Actions actions = new Actions(DriverFactory.getWebDriver())
		int i=0
		for(String barcode : barcodes){
			String locatorNew= String.format(barcodeLocator,barcode)
			actions.keyDown(Keys.LEFT_CONTROL).click(DriverFactory.getWebDriver().findElement(By.xpath(locatorNew))).build().perform()
			i=i+1
			if(i==numberOfBarcodes){
				break
			}
		}
		safeClickwithScroll( findTestObject('Object Repository/CheckIn_Page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Set Condition dropdown
	 * @param fromLocation
	 * @return
	 */
	@Keyword
	def setConditionValue(String ConditionDropDownLocator,String conditionValues){
		String locator=ConditionDropDownLocator
		String[] conditions= conditionValues.split(',');
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		int i=0
		for(WebElement element: elements){
			highLightElement(element, 0)
			element.click()
			Thread.sleep(500)
			TestObject conditionOptionObj=getLocator("//*[@aria-hidden='false']//li[contains(text(),'%s')]",conditions[i])
			safeClickwithScroll( conditionOptionObj,conditions[i], GlobalVariable.MEDIUM_WAIT)
			i=i+1
		}
	}
	/**
	 * Enter data in to Comment field
	 * @param toLocation
	 * @return
	 */
	@Keyword
	def setCommentValue(String commentFieldLocator,String commentsList){
		String locator=commentFieldLocator
		String[] comments= commentsList.split(',');
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		int i=0
		for(WebElement element: elements){
			highLightElement(element, 0)
			element.click();
			element.sendKeys("")
			element.sendKeys(comments[i])
			i=i+1;
		}
	}
	/**
	 * Click on Actions Heading
	 * @return
	 */
	@Keyword
	def clickOnActionsHeading(){
		safeClickwithScroll( findTestObject('Object Repository/CommonObjects/ACTIONS_TAB'),"Actions Tab", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Validate View and Create Types with Date and Time
	 * @return
	 */
	@Keyword
	def validateViewAndCreateTypesWithDateAndTime(){

		String locator="//td[contains(text(),'%s')]"
		TestObject CreateTypeObj=getLocator(locator,"Create")
		safeElementVisible(CreateTypeObj, GlobalVariable.MEDIUM_WAIT)

		TestObject viewTypeObj=getLocator(locator,"View")
		safeElementVisible(viewTypeObj, GlobalVariable.MEDIUM_WAIT)

		String getCreatedAtDate=safeGetValue( findTestObject('Object Repository/CommonObjects/CREATED_AT_VALUE') , GlobalVariable.MEDIUM_WAIT)
		getCreatedAtDate=getCreatedAtDate.split(':')[0]
		String[] dateAndTimeArray= getCreatedAtDate.split(' ')
		String date=dateAndTimeArray[0]
		TestObject createDateAndTimeObj=getLocator(createDateAndTime,date)
		safeElementVisible(createDateAndTimeObj, GlobalVariable.MEDIUM_WAIT)
		TestObject viewDateAndTimeObj=getLocator(viewDateAndTime,date)
		safeElementVisible(viewDateAndTimeObj, GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Create a person
	 * Navigate to Configuration> Personnel> People
	 * Click on Create button
	 * Set Person name
	 * @param personName
	 * Set Organization Unit
	 * @param organizationUnit
	 * Set Asset Policy
	 * @param assetPolicy
	 * set Password and ConfirmPassword
	 * @return
	 */
	@Keyword
	def createPersonAndValidatePersonName(String personName,String organizationUnit, String assetPolicy,String password){
		Home home=new Home()
		home.gotoPeople()

		PersonSearch personSearch=new PersonSearch()
		personSearch.ClickOnCreateButton()

		CreatePerson createPerson=new CreatePerson()
		createPerson.setPersonNameAndOrganisationUnit(personName,organizationUnit)
		createPerson.setAssetPolicy(assetPolicy)
		createPerson.setPasswordAndConfirmPassword(password)
		createPerson.clickOnSaveButton()
		createPerson.clickOnSaveAndCloseButton()

		personSearch.ValidatePersonSaved(personName)
	}

	/**
	 * Create a Asset
	 * Navigate to Create > Asset
	 * Set Product Name
	 * @param productName
	 * Click on Save button
	 * @return
	 */
	@Keyword
	def createAsset(String productName,String status='Active'){
		Home home=new Home()
		home.gotoCreateAsset()

		CreateAsset createAsset=new CreateAsset()
		String barcode=createAsset.setProductName(productName)
		createAsset.setStatus(status)
		clickOnSaveButton()
		return barcode
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
	def setPersonDetails(String personName){
		safeClickwithScroll(findTestObject('CommonActions_Page/SELECT_PERSON_OR_ITEM_TRIANGLE_ICON'),"Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(findTestObject('CommonActions_Page/PERSON'), 'Person',GlobalVariable.MEDIUM_WAIT)

		String locator="//tbody[@role='rowgroup']//tr[@role='row']//td[text()='%s']"
		TestObject PersonNameObj=getLocator(locator,personName)
		highLightElement(PersonNameObj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(PersonNameObj,personName,GlobalVariable.MEDIUM_WAIT)

		safeClickwithScroll( findTestObject('Object Repository/IssueAndReturn_Page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Select Select Person or Item field Triangle Icon
	 * Select Asset option
	 * Select Asset Name
	 * @param assetItem
	 * Click Select Button
	 * @return
	 */
	@Keyword
	def setAssetItem(String assetItem){
		safeClickwithScroll(findTestObject('CommonActions_Page/SELECT_PERSON_OR_ITEM_TRIANGLE_ICON'),"Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(findTestObject('CommonActions_Page/ASSET'), 'Asset',GlobalVariable.MEDIUM_WAIT)

		String locator="//tbody[@role='rowgroup']//tr[@role='row']//td[text()='%s']"
		TestObject Obj=getLocator(locator,assetItem)

		safeClickwithScroll(findTestObject('CommonActions_Page/SHOW_MORE_RESULTS'),"Show More Results", GlobalVariable.MEDIUM_WAIT)
		highLightElement(Obj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(Obj,assetItem,GlobalVariable.MEDIUM_WAIT)

		safeClickwithScroll( findTestObject('Object Repository/IssueAndReturn_Page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Validate the field value
	 * @param fieldName
	 */
	@Keyword
	def validateTheFieldsName(String fieldName){
		String locator="//div[contains(text(),'%s')]"
		locator=String.format(locator,fieldName)
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeElementVisible(testObj, GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Validate the field value
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@Keyword
	def validateTheField(String fieldName,String fieldValue){
		String locator="//div[contains(text(),'%s')]/../../..//div/input"
		locator=String.format(locator,fieldName)
		TestObject testObj=new TestObject()

		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		String value=safeGetValue(testObj, GlobalVariable.MEDIUM_WAIT)
		assertion(fieldValue.equals(value))
	}
	@Keyword
	def pageSavedSuccessfully(){
		String locator="//button/span[text()='Save']"
		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		WebUI.verifyElementNotPresent(testObj, GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Validate the value in the table
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	@Keyword
	def validateTheFieldInTheTable(String fieldName,String fieldValue){
		String locator="//th[contains(text(),'%s')]/../../.."
		locator=String.format(locator,fieldName)

		locator=locator+"//td[text()='%s']"
		locator=String.format(locator,fieldValue)

		TestObject testObj=new TestObject()
		testObj.addProperty("xpath",ConditionType.EQUALS,locator)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeElementVisible(testObj, GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Get OC Spary Product Names
	 * @return
	 */
	@Keyword
	def getOCSprayProductNames(){
		Home home=new Home()
		home.gotoProducts()
		Product product=new Product()
		product.openOperationalSafetyOCSpray()
		List<String> productNames=product.getProductNames()
		return productNames
	}
}
