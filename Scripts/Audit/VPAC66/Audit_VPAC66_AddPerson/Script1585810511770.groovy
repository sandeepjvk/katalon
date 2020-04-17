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

'Select Configaration > Personnel > People'
CustomKeywords.'pages.Home.gotoPeople'()

'Select Create Button'
CustomKeywords.'pages.PersonSearch.ClickOnCreateButton'()

'Create Person'
PersonName = PersonName.toString().concat(System.currentTimeMillis().toString())
CustomKeywords.'pages.CreatePerson.EnterMandatoryDetailsAndSaveTheDetails'(PersonName,OrganizationUnit,PersonPassword)
CustomKeywords.'pages.CommonActions.clickOnSaveButton'()
CustomKeywords.'pages.CommonActions.clickOnSaveAndCloseButton'()
GlobalVariable.PersonName=PersonName
'Validate Person Saved'
CustomKeywords.'pages.PersonSearch.ValidatePersonSaved'(PersonName)