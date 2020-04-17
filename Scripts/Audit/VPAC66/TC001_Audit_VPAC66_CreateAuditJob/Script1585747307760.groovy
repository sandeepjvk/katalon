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
import java.util.Date as Date

//'Launch Browser'
//CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.URL, (([]) as int[]))

'Enter Valid Username and Password and Login to application'
CustomKeywords.'pages.Login.login'(UserName, Password, GlobalVariable.MEDIUM_WAIT)

'Select Create > AuditJobs'
CustomKeywords.'pages.Home.gotoAuditJobs'()

'Validate Navigation to Create Audit Job Page'
CustomKeywords.'pages.CreateAuditJobSearch.validateNavigationToCreateAuditJobPage'()

'Validate Description And Allow Asset Capture Fields'
CustomKeywords.'pages.CreateAuditJobSearch.validateDescriptionAndAllowAssetCaptureFields'()

'Create Audit Job'
CustomKeywords.'pages.CreateAuditJobSearch.createAuditJob'(Description)

'Click on Add Icon'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnAddIcon'()

'Select Multiple Locations'
CustomKeywords.'pages.CreateAuditJobSearch.selectMultipleLocations'(Locations)

'Click on Add Button'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnAddButton'()

'Click on Cancel Button'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnCancelButton'()

'Verify the added item in the table'
CustomKeywords.'pages.CreateAuditJobSearch.verifyDescription'(Description)
CustomKeywords.'pages.CreateAuditJobSearch.verifyTableColumnData'(Locations)


