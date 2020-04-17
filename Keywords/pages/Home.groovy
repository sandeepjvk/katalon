package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject

import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement

import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable
import utilities.DynamicLocators
import utilities.SafeActions
import pages.CommonActions

public class Home extends CommonActions {

	DynamicLocators locator=new DynamicLocators()

	/**
	 * Click on Tab Element
	 * @param subMenuName
	 * @return
	 */
	@Keyword
	def clickOnSubMenu(String subMenuName){
		String locator="//li[contains(@class,'open')]//span[contains(text(),'%s')]"
		TestObject testObj=getLocator(locator,subMenuName)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		safeClickwithScroll(testObj,"clickOnsubmenu",GlobalVariable.MEDIUM_WAIT)
	}
	/**
	 * Mouse Hover to Tab Element
	 * @param subMenuName
	 * @return
	 */
	@Keyword
	def mouseOverSubMenu(String subMenuName){
		String locator="//li[contains(@class,'open')]//span[contains(text(),'%s')]"
		TestObject testObj=getLocator(locator,subMenuName)
		highLightElement(testObj, GlobalVariable.MEDIUM_WAIT)
		WebUI.mouseOver(testObj, FailureHandling.STOP_ON_FAILURE)
	}
	/**
	 * Navigate to Configuration > General > Products
	 * @return
	 */
	@Keyword
	def gotoProducts(){
		clickOnTab('Configuration')
		mouseOverSubMenu("General")
		Thread.sleep(3000)
		clickOnSubMenu("Products")
	}
	/**
	 * Navigate to Create > Audit Jobs
	 * @return
	 */
	@Keyword
	def gotoAuditJobs(){
		clickOnTab('Create')
		clickOnSubMenu("Audit Job")
	}
	/**
	 * Navigate to Configuration > Personnel > People
	 * @return
	 */
	@Keyword
	def gotoPeople (){
		clickOnTab('Configuration')
		mouseOverSubMenu("Personnel")
		clickOnSubMenu("People")
	}
	/**
	 * Navigate to Search > Audit Jobs
	 * @return
	 */
	@Keyword
	def gotoSearchAuditJobs(){
		clickOnTab('Search')
		clickOnSubMenu("Audit Jobs")
	}
	/**
	 * Navigate to Actions >  Check Out
	 * @return
	 */
	@Keyword
	def gotoCheckOutPage(){
		clickOnTab('Actions')
		clickOnSubMenu("Check Out")
	}
	/**
	 * Navigate to Search > Asset
	 * @return
	 */
	@Keyword
	def gotoAssetPage(){
		clickOnTab('Search')
		clickOnSubMenu("Asset")
	}
	/**
	 * Navigate to Actions > Asset Lookup
	 * @return
	 */
	@Keyword
	def gotoAssertLookUp(){
		clickOnTab('Actions')
		clickOnSubMenu("Asset Lookup")
	}
	/**
	 * Navigate to Actions >  Check In
	 * @return
	 */
	@Keyword
	def gotoCheckInPage(){
		clickOnTab('Actions')
		clickOnSubMenu("Check In")
	}
	/**
	 * Navigate to Actions >  Transfer
	 * @return
	 */
	@Keyword
	def gotoTransferPage(){
		clickOnTab('Actions')
		clickOnSubMenu("Transfer")
	}
	/**
	 * Navigate to Create >  Asset
	 * @return
	 */
	@Keyword
	def gotoCreateAsset(){
		clickOnTab('Create')
		clickOnSubMenu("Asset")
	}
	/**
	 * Navigate to Actions >  Issue Return
	 * @return
	 */
	@Keyword
	def gotoIssueReturnPage(){
		clickOnTab('Actions')
		clickOnSubMenu("Issue Return")
	}
	/**
	 * Navigate to Actions >  Loan
	 * @return
	 */
	@Keyword
	def gotoActionsLoanPage(){
		clickOnTab('Actions')
		clickOnSubMenu("Loan")
	}
}
