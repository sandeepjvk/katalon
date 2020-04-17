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

'Get OC Spray Product Names From Products Page'
List<String> productNames=CustomKeywords.'pages.CommonActions.getOCSprayProductNames'()

'Select Create > Assert'
CustomKeywords.'pages.Home.gotoCreateAsset'()

'Validate Navigation to Create > Asset Page'
CustomKeywords.'pages.CreateAsset.ValidateNavigationToCreateAssetPage'()

'Enter The Details In Create Asset Page'
CustomKeywords.'pages.CreateAsset.enterTheDetailsToCreateAsset'(productNames[0], barcode, status, lotNumber, lotExpiry)

'Set Organization Unit'
CustomKeywords.'pages.CreateAsset.enterOrgDetails'(organisationUnit)

'Validate Purchase date Is Today date'
CustomKeywords.'pages.CreateAsset.validatePurchaseDateIsToday'()

'Enter Supplier Value'
CustomKeywords.'pages.CreateAsset.enterSupplierValue'(supplier)

'Uncheck Create Notes checkbox'
CustomKeywords.'pages.CreateAsset.tickNotesCheckBox'(false)

'Click On Save Button'
CustomKeywords.'pages.CommonActions.clickOnSaveButton'()

'Click On Save And Close Button'
CustomKeywords.'pages.CommonActions.clickOnSaveAndCloseButton'()

'Navigate To Search > Asset'
CustomKeywords.'pages.Home.gotoAssetPage'()
