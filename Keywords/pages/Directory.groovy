package pages

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.openqa.selenium.By
import com.kms.katalon.core.webui.driver.DriverFactory
import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.util.KeywordUtil
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI

import internal.GlobalVariable

public class Directory {

	@Keyword
	def verifyValues(String selectedDropDownValue){
		try{
			WebDriver driver=DriverFactory.getWebDriver()
			WebElement table
			table= driver.findElement(By.xpath("//table[@id='resultTable']/tbody"))
			List<WebElement> rows=table.findElements(By.tagName('tr'))
			for(int i=1;i<rows.size()-1;i++){

				WebElement elementValue=driver.findElement(By.xpath("tr[{i}]///td[2]//li[2]"))
				String listValue = elementValue.getText()
				if(listValue.equalsIgnoreCase(selectedDropDownValue)){
					KeywordUtil.markPassed("The expected value matched with actual Value")
				}
			}
		}
		catch(Exception e){
			KeywordUtil.markFailed("unable to verify actual and expected values")
		}
	}

	@Keyword
	def verifyCount(){
		try{
			WebDriver driver=DriverFactory.getWebDriver()
			WebElement table
			table= driver.findElement(By.xpath("//table[@id='resultTable']/tbody"))
			List<WebElement> rows=table.findElements(By.tagName('tr'))
			if(rows.size()>0){
				KeywordUtil.markPassed("The rows got filtered")
			}
			else{
				KeywordUtil.markFailed("There are no rows filtered")
			}
		}
		catch(Exception e){
			KeywordUtil.markFailed("No rows got filtered")
		}
	}
}
