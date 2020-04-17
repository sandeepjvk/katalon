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

WebUI.callTestCase(findTestCase('Audit/VPAC66/Audit_VPAC66_AddPerson'), [('Username') : Username, ('Password') : Password
        , ('PersonName') : PersonName, ('PersonPassword') : PersonPassword, ('OrganizationUnit') :OrganizationUnit], FailureHandling.STOP_ON_FAILURE)

'Select Create > AuditJobs'
CustomKeywords.'pages.Home.gotoAuditJobs'()

'Validate Navigation to Create Audit Job Page'
CustomKeywords.'pages.CreateAuditJobSearch.validateNavigationToCreateAuditJobPage'()

'Create Audit Job'
CustomKeywords.'pages.CreateAuditJobSearch.createAuditJob'(Description)

'Click on Participants Heading'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnParticipantsHeading'()

'Click on Add Icon'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnAddIcon'()

'Set Audit Job Participant Details'
CustomKeywords.'pages.CreateAuditJobSearch.setAuditJobParticipantDetails'(GlobalVariable.PersonName, Capability)

'Click on Save Button'
CustomKeywords.'pages.CreateAuditJobSearch.clickOnParticipantSaveButton'()

List<String> data = new ArrayList<String>()

data.add(GlobalVariable.PersonName)

data.add(Capability)

'Verify the PersonName and Capability'
CustomKeywords.'pages.CreateAuditJobSearch.verifyTableColumnData'(data)

