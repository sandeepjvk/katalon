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
CustomKeywords.'pages.Login.login'(GlobalVariable.Username, GlobalVariable.Password, 10)

'Select Actions > Loan'
String barcode=CustomKeywords.'pages.CommonActions.createAsset'(selectItem)
CustomKeywords.'pages.Home.gotoActionsLoanPage'()

'Validate Navigation to Actions > Loan Page'
CustomKeywords.'pages.Loan.ValidateNavigationToActionsLoanPage'()

'Enter The Details In Loan Page'
CustomKeywords.'pages.Loan.enterTheDetailsInLoanPage'(loanedTo,date,reason,reservationOption,reservationValue,assignValue,checkoutOption)

'Select Person Details'
CustomKeywords.'pages.CommonActions.setPersonDetails'(personName)

'Validate The Person Name Selected'
CustomKeywords.'pages.Loan.ValidatePersonName'(personName)

'Select Item Details'
CustomKeywords.'pages.CommonActions.setAssetItem'(barcode)

'Validate The Item Name Selected'
CustomKeywords.'pages.Loan.ValidateItemDetails'(barcode)

'Click On Save Button'
CustomKeywords.'pages.CommonActions.clickOnSaveButton'()

'Page Should Be Saved Successfully'
CustomKeywords.'pages.CommonActions.pageSavedSuccessfully'()

'Validation The Fields Loan Page'
CustomKeywords.'pages.Loan.validationTheFieldsInLoanPage'(loanedTo,date,reason,reservationOption,reservationValue,assignValue,checkoutOption,barcode)

'Select Actions > AssetLookUp'
CustomKeywords.'pages.Home.gotoAssertLookUp'()

'Validate Navigation to Actions > AssetLookUp'
CustomKeywords.'pages.Loan.ValidateNavigationToActionsAssetLookUp'()

'Set Barcode In Asset LookUp Page'
CustomKeywords.'pages.AssetLookUp.setAssetbarcode'(barcode)

'Validate The Reserved For Person Value'
CustomKeywords.'pages.AssetLookUp.validateTheReservedForPerson'(personName)

'Validate The Assigned Person'
CustomKeywords.'pages.AssetLookUp.validateTheAssignedPerson'(personName)

'Validate The Loaned To Org Unit'
CustomKeywords.'pages.AssetLookUp.validateLoanedToOrgUnit'()

'Validate The Current Location Doesnt Contain Any Transction Of Location'
CustomKeywords.'pages.AssetLookUp.validateTheCurrentLocationShouldntHaveAnyTransaction'()
