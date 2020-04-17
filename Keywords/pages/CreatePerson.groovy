package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import internal.GlobalVariable

public class CreatePerson extends CommonActions{

	@Keyword
	def EnterMandatoryDetailsAndSaveTheDetails(String PersonName,String OrganizationUnit,String Password){
		setPersonNameAndOrganisationUnit(PersonName,OrganizationUnit)
		setPasswordAndConfirmPassword(Password)
	}

	/**
	 * Enter Person name in Name field
	 * @param personName
	 * Enter Organization Unit name in Organization Unit field
	 * @param organizationUnit
	 * @return
	 */
	@Keyword
	def setPersonNameAndOrganisationUnit(String personName,String organizationUnit){
		setTextField("Name",personName)
		setTextFieldFromTheList("Organisation Unit",organizationUnit)
	}

	/**
	 * Enter policy name in Asset Policy field
	 * @param assetPolicy
	 * @return
	 */
	@Keyword
	def setAssetPolicy(String assetPolicy){
		setTextFieldFromTheList(" Asset Policy",assetPolicy)
	}

	/**
	 * Enter password in Password field
	 * @param password
	 * Enter password in Confirm Password field
	 * @param confirmPassword
	 * @return
	 */
	@Keyword
	def setPasswordAndConfirmPassword(String password){
		setTextField("Password",password)
		setTextField("Confirm Password",password)
	}
}
