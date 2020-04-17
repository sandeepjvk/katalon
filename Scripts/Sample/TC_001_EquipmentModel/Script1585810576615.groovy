import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.annotation.Keyword as Keyword
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testcase.Variable as Variable
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testobject.ConditionType as ConditionType
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.util.KeywordUtil as KeywordUtil
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import jdk.nashorn.internal.codegen.ApplySpecialization.AppliesFoundException as AppliesFoundException

//'Launch Browser'
//CustomKeywords.'utilities.SafeActions.openBrowser'(GlobalVariable.URL, (([]) as int[]))

'Enter Valid Username and Password and Login to application'
CustomKeywords.'pages.Login.login'(GlobalVariable.Username, GlobalVariable.Password, 10)

'Select General > Products'
CustomKeywords.'pages.Home.gotoProducts'()

'Click on Create Button'
CustomKeywords.'pages.Product.clickOnCreateButton'()

ProductName = ProductName.toString().concat(System.currentTimeMillis().toString())

'Enter Name of the product and Product Type '
CustomKeywords.'pages.Product.enterProductDetails'(ProductName, ProductType)

'Select Assignment Policy as \'Ongoing\''
CustomKeywords.'pages.Product.selectAssignmentPolicy'(AssignmentPolicyValue)

'Select Applies to Inventory as \'No\''
CustomKeywords.'pages.Product.selectAppliesToInventory'(AppliesToInventory)

'Select Applies to Asset as "Yes"'
CustomKeywords.'pages.Product.selectAppliesToAsset'(AppliesToAsset)

'Select Barcode requirement as \'Mandatory\'Select Serial Number requirement as \'Mandatory\'Select Lot Number requirement as \'Not allowed\''
CustomKeywords.'pages.Product.selectAssetDetails'(BarcodeRequirement, SerialNumberRequirement, LotNumberRequirement)

'Enter \'FireArm\' in Asset class then select from list'
CustomKeywords.'pages.Product.setAssetClass'(AssetClass)

'Click on Product Supplier Tab'
CustomKeywords.'pages.Product.clickProductSupplierHeading'()

'Enter \'Gun Corporation\' into Supplier Field'
CustomKeywords.'pages.Product.setSupplier'(Supplier)

'Click on Manufacturer Tab'
CustomKeywords.'pages.Product.clickManufacturerHeading'()

'Click on Save Button'
CustomKeywords.'pages.Product.clickOnSave'()

'Select Operational Equipment > Fire arms > Pistol'
CustomKeywords.'pages.Product.openFirearmsPistol'()

'Verify the added item in the table'
CustomKeywords.'pages.CommonActions.verifyTableColumnData'('Name', ProductName)

