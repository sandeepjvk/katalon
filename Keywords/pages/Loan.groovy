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

public class Loan extends CommonActions{


	/**
	 * Enter The Details In Loan Page
	 * @param loanedTo
	 * @param date
	 * @param reason
	 * @param reservationOption
	 * @param reservationValue
	 * @param assignValue
	 * @param CheckoutOption
	 * @return
	 */
	@Keyword
	def enterTheDetailsInLoanPage(String loanedTo,String date,String reason,String reservationOption,String reservationValue,String assignValue,String CheckoutOption){
		setTextFieldFromTheList("Loaned To",loanedTo)
		setTextField("Expected Return Date",date)
		setTextField("Reason",reason)
		chooseOption("Allow Reservation",reservationOption)
		selectVlaueFromDropdown('Reservation Type',reservationValue)
		chooseOption('Assign',assignValue)
		chooseOption("Create Checkout",CheckoutOption)
	}



	/**
	 * Validate Navigation To Actions Loan Page
	 * @return
	 */
	@Keyword
	def ValidateNavigationToActionsLoanPage(){
		validate_page_headding("Loan")
	}

	/**
	 * Validation The Fields In Loan Page
	 * @param loanedTo
	 * @param date
	 * @param reason
	 * @param reservationOption
	 * @param reservationValue
	 * @param assignValue
	 * @param CheckoutOption
	 * @return
	 */
	@Keyword
	def validationTheFieldsInLoanPage(String loanedTo,String date,String reason,String reservationOption,String reservationValue,String assignValue,String CheckoutOption,String barcode){
		validateTheField("Loaned To",loanedTo)
		validateTheField("Expected Return Date",date)
		validateTheField("Reason",reason)
		validateTheField("Allow Reservation",reservationOption)
		validateTheField('Reservation Type',reservationValue)
		validateTheField('Assign',assignValue)
		validateTheField("Create Checkout",CheckoutOption)
		validateTheFieldInTheTable("Asset Barcode",barcode)
	}

	@Keyword
	def ValidatePersonName(String personName){
		validateTheField("Reserve For",personName)
	}
	@Keyword
	def ValidateItemDetails(String barcode){
		validateTheField("Select Item",barcode)
	}
	@Keyword
	def ValidateNavigationToActionsAssetLookUp(){
		validateTheFieldsName("Select Asset")
	}
}
