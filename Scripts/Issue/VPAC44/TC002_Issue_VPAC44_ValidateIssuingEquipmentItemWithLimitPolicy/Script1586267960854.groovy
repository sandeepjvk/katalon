import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import pages.CommonActions

//'Launch Browser'
//CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.URL, (([]) as int[]))

'Enter Valid Username and Password and Login to application'
CustomKeywords.'pages.Login.login'(Username, Password, GlobalVariable.MEDIUM_WAIT)

personName = PersonName.toString().concat(System.currentTimeMillis().toString())

'Create a Person and Validate Person Saved'
CustomKeywords.'pages.CommonActions.createPersonAndValidatePersonName'(personName,OrganizationUnit,AssetPolicy,PersonPassword)

List<String> itemBarcodes = new ArrayList<String>()

'Create a Asset '
String barcode1=CustomKeywords.'pages.CommonActions.createAsset'(ProductName,AssetStatus)

itemBarcodes.add(barcode1)

'Create another Asset '
String barcode2=CustomKeywords.'pages.CommonActions.createAsset'(ProductName,AssetStatus)

itemBarcodes.add(barcode2)

'Select  Actions > Issue Return'
CustomKeywords.'pages.Home.gotoIssueReturnPage'()

'Validate Navigation to Issue and Return Page'
CustomKeywords.'pages.IssueReturn.validateNavigationToIssueAndReturnPage'()

'Set Return Location'
CustomKeywords.'pages.IssueReturn.setReturnLocationDetails'(ReturnLocation)

'Set Return Cycle dropdown Value'
CustomKeywords.'pages.IssueReturn.setReturnCycleDropdownValue'(ReturnCycleValue)

'Set Person Name'
CustomKeywords.'pages.IssueReturn.setPersonDetails'(personName)

'Set Item Barcode'
CustomKeywords.'pages.IssueReturn.setItemDetails'(itemBarcodes)

'Select Save Button'
CustomKeywords.'pages.IssueReturn.clickOnSave'()

'Validate Navigation to Issue and Return Page'
CustomKeywords.'pages.IssueReturn.validateNavigationToIssueAndReturnPage'()

'Click on Issue Items Remove Icon'
CustomKeywords.'pages.IssueReturn.clickOnIssueItemsRemoveIcon'()

'Select Save Button'
CustomKeywords.'pages.IssueReturn.clickOnSave'()

'Validation of Date Field'
CustomKeywords.'pages.IssueReturn.validateDateField'()

'Validation of Return Location'
CustomKeywords.'pages.IssueReturn.validateReturnLocation'(ReturnLocation)

'Validation of Person Name'
CustomKeywords.'pages.IssueReturn.validatePersonName'(personName)

'Validation of Issued Items Count'
CustomKeywords.'pages.IssueReturn.validateIssuedItemsCount'(IssuedItemsCount)


