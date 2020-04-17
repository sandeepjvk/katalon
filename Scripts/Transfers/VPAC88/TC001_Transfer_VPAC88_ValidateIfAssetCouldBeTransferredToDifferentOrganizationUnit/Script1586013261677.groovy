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
List<String> activeBarcodes = CustomKeywords.'pages.AssetSearch.fetchBarcodesOfStatus'(ActiveStatus)

'Select  Actions > Transfer'
CustomKeywords.'pages.Home.gotoTransferPage'()

'Validate Navigation to Actions > Transfer Page'
CustomKeywords.'pages.Transfer.ValidateNavigationToActionsTransferPage'()

'Enter the details in transfer page'
CustomKeywords.'pages.Transfer.enterTheDetails'(Transfer_To_Name,Description)

int NunberOfBarcodesToAdd = NunberOfBarcodesToAdd.toInteger()
'Set Active Item Barcode'
CustomKeywords.'pages.Transfer.setSelectItemBarcode'(activeBarcodes,NunberOfBarcodesToAdd)

'Validate the timestamp and asset details'
CustomKeywords.'pages.Transfer.ValidateTimeStampAndAssetDetails'(activeBarcodes,NunberOfBarcodesToAdd,ActiveStatus,Transfer_To_Name)

'Select Save Button'
CustomKeywords.'pages.CommonActions.clickOnSaveButton'()

