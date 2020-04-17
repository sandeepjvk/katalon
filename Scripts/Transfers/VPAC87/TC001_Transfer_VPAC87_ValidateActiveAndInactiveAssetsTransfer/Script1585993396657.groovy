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

//'Launch Browser'
//CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.URL, (([]) as int[]))

'Enter Valid Username and Password and Login to application'
CustomKeywords.'pages.Login.login'(Username, Password, GlobalVariable.MEDIUM_WAIT)

'Select  Search > Asset'
CustomKeywords.'pages.Home.gotoAssetPage'()

'Fetch Active Status Barcodes '
List<String> activeBarcodes =CustomKeywords.'pages.AssetSearch.fetchBarcodesOfStatus'(ActiveStatus)

'Fetch inActive Status Barcodes '
List<String> inactiveBarcodes =CustomKeywords.'pages.AssetSearch.fetchBarcodesOfStatus'(InactiveStatus)

'Select  Actions > Check In'
CustomKeywords.'pages.Home.gotoCheckInPage'()

'Validate Navigation to Check In Page'
CustomKeywords.'pages.CheckIn.validateNavigationToCheckInPage'()

String RandomNumber = System.currentTimeMillis().toString()

'Set Site and Default Location'
CustomKeywords.'pages.CheckIn.setSiteAndDefaultLocationDetails'(SiteName, DefaultLocation)

'Set Delivered By Person Name and Reference'
CustomKeywords.'pages.CheckIn.setDeliveredByPersonAndReference'(DeliveredByPerson,ReferenceText+RandomNumber)

int NumberOfBarcodes = NumberOfBarcodes.toInteger()
'Set Active Item Barcode'
CustomKeywords.'pages.CheckIn.setSelectItemBarcode'(activeBarcodes,NumberOfBarcodes)

'Set Inactive Item Barcode'
CustomKeywords.'pages.CheckIn.setSelectItemBarcode'(inactiveBarcodes,NumberOfBarcodes)

'Set Condition details'
CustomKeywords.'pages.CheckIn.setConditionDetails'(ConditionValueList)

'Set Comment details'
CustomKeywords.'pages.CheckIn.setCommentDetails'(CommentsList)

'Select Save Button'
CustomKeywords.'pages.CommonActions.clickOnSaveButton'()

'Validation of Site Name'
CustomKeywords.'pages.CheckIn.validateSiteName'(SiteName)

'Validation of Date, Delivery Person and ID Fields'
CustomKeywords.'pages.CheckIn.validateDateAndDeliveryPersonAndIDFields'()

'Validation of Active CheckIn Items'
CustomKeywords.'pages.CheckIn.verifyCheckInItems'(activeBarcodes,NumberOfBarcodes)

'Validation of Inactive CheckIn Items'
CustomKeywords.'pages.CheckIn.verifyCheckInItems'(inactiveBarcodes,NumberOfBarcodes)

'Click in Actions Heading'
CustomKeywords.'pages.CommonActions.clickOnActionsHeading'()

'Validate View and Create Types with Date and Time'
CustomKeywords.'pages.CommonActions.validateViewAndCreateTypesWithDateAndTime'()

