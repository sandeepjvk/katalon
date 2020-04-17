package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import org.openqa.selenium.interactions.Actions
import com.kms.katalon.core.annotation.Keyword
import org.openqa.selenium.WebDriver
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
import com.kms.katalon.core.webui.driver.DriverFactory
import internal.GlobalVariable
import org.openqa.selenium.Keys as Keys
import java.text.SimpleDateFormat;
import org.openqa.selenium.WebElement
import com.kms.katalon.core.testobject.ConditionType
import com.kms.katalon.core.util.KeywordUtil
import org.openqa.selenium.By


public class Transfer extends CommonActions{
	String tableRowLocator="//tbody[@role='rowgroup'] //tr[@role='row']"

	/**
	 * Entering The Details
	 * @param Transfer_To_Name
	 * @param discription
	 * @return
	 */
	@Keyword
	def enterTheDetails(String Transfer_To_Name,String discription){
		setTextFieldFromTheList('Transfer To',Transfer_To_Name)
		setTextField('Reference',discription)
	}


	/**
	 * Validate Navigation To Actions TransferPage
	 * @param Transfer_To_Name
	 * @param discription
	 * @return
	 */
	@Keyword
	def ValidateNavigationToActionsTransferPage(){
		validate_page_headding("Transfer")
	}

	/**
	 * Set Select Item Barcode
	 * @param barcodes
	 * @param NumberOfBarcodes
	 * @return
	 */
	@Keyword
	def setSelectItemBarcode(List<String> barcodes,int NumberOfBarcodes){

		safeClickwithScroll(findTestObject('Object Repository/Transfer_Page/SELECT_ITEM_TRIANGLE_ICON') ,"Select Item Triangle Icon", GlobalVariable.MEDIUM_WAIT)
		String locator=tableRowLocator+"//td[text()='%s']"
		Actions actions = new Actions(DriverFactory.getWebDriver())
		int i=0
		for(String barcode : barcodes){
			String locatorNew= String.format(locator,barcode)
			actions.keyDown(Keys.LEFT_CONTROL).click(DriverFactory.getWebDriver().findElement(By.xpath(locatorNew))).build().perform()
			i=i+1
			if(i==NumberOfBarcodes){
				break
			}
		}
		safeClickwithScroll( findTestObject('Transfer_page/SELECT_BUTTON'),"Select Button", GlobalVariable.MEDIUM_WAIT)
	}

	/**
	 * Validate TimeStamp And AssetDetails
	 * @param barcodes
	 * @param NumberOfBarcodes
	 * @param status
	 * @param Transfer_To_Name
	 * @return
	 */
	@Keyword
	def ValidateTimeStampAndAssetDetails(List<String> barcodes,int NumberOfBarcodes,String status,String Transfer_To_Name){
		validateTheTimeStamp()
		validateTheBarcodes(barcodes,NumberOfBarcodes)
		ValidateTheStatus(status)
		ValidateTheOwner(Transfer_To_Name)
		validateAssetName()
	}

	/**
	 * Validate The Owner
	 * @param Transfer_To_Name
	 * @return
	 */
	@Keyword
	def ValidateTheOwner(String Transfer_To_Name){
		String locator="//span[text()='Owner']/..//input"
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.xpath(locator))
		for(WebElement element : elements){
			String Owner_name= element.getAttribute("value")
			Transfer_To_Name.equals(Owner_name)
		}
	}

	/**
	 * Validate The TimeStamp
	 * @return
	 */
	@Keyword
	def validateTheTimeStamp(){
		SimpleDateFormat date_formate = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date()); // Now use today date.
		String startDate = date_formate.format(calendar.getTime());
		String timestamp=safeGetValue(findTestObject('Object Repository/Transfer_page/TIME_STAMP'), GlobalVariable.MEDIUM_WAIT)
		timestamp.contains(startDate)
	}

	/**
	 * Validate The Status
	 * @param status
	 * @return
	 */
	@Keyword
	def ValidateTheStatus(String status){
		String locator=".hc-card-asset-status input"
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.cssSelector(locator))
		for(WebElement element : elements){
			String Status= element.getAttribute("value")
			assertion( status.equals(Status))
		}
	}

	/**
	 * Validate The Barcodes
	 * @param barcodes
	 * @param Number Of Barcodes
	 * @return
	 */
	@Keyword
	def validateTheBarcodes(List<String> barcodes,int NumberOfBarcodes) {
		int i=0
		for(String barcode : barcodes){
			String Barcode=safeGetValue(findTestObject('Object Repository/Transfer_page/BARCODE'), GlobalVariable.MEDIUM_WAIT)
			assertion( Barcode.equals(barcode))
			i=i+1
			if(i==NumberOfBarcodes){
				break
			}
		}
	}

	/**
	 * Validate Asset Name
	 * @return
	 */
	@Keyword
	def validateAssetName(){
		String locator=".hc-card-caption div"
		WebDriver webDriver = DriverFactory.getWebDriver()
		List<WebElement> elements = webDriver.findElements(By.cssSelector(locator))
		for(WebElement element : elements){
			String asset_name= element.getText()
			boolean status=asset_name.isEmpty()
			assertion( status.equals(false))
		}
	}
}
