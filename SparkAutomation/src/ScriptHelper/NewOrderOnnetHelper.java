package ScriptHelper;

import java.io.IOException;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.security.auth.Refreshable;

import org.dom4j.DocumentException;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Reporter;

import com.gargoylesoftware.htmlunit.Page;
import com.relevantcodes.extentreports.LogStatus;
import com.relevantcodes.extentreports.model.Log;

import Driver.DriverHelper;
import Driver.XMLReader;
import Reporter.ExtentTestManager;
import org.apache.log4j.Logger;
import org.apache.pdfbox.contentstream.operator.state.Save;
import org.apache.poi.xssf.streaming.examples.SavePasswordProtectedXlsx;

public class NewOrderOnnetHelper extends DriverHelper {

	
	String timeStamp = TimeStamp();
	
	public NewOrderOnnetHelper(WebDriver dr) {
		super(dr);

	}

	WebElement el;
	
	
	XMLReader xml = new XMLReader("src\\Locators\\SiebelOrder.xml");
	XMLReader xml2= new XMLReader("src\\Locators\\CloudUc.xml");
	XMLReader xmlIP = new XMLReader("src\\Locators\\IPVoiceSolution.xml");
	
	
	
	
	public void accountTabDetails(Object[] Inputdata) throws Exception {
	
		
		//Thread.sleep(10000);
		waitforPagetobeenable();
		try {
			Clickon(getwebelement(xml.getlocator("//locators/Accounts")));
		} catch (Exception e) {
			try {
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/Accounts")));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Accounts");
		System.out.println(Inputdata[0].toString());
		SendKeys(getwebelement(xml.getlocator("//locators/OCNfield")), Inputdata[0].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter OCN field");
		Clickon(getwebelement(xml.getlocator("//locators/GoButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on GoButton");
		waitforPagetobeenable();
		Thread.sleep(5000);
		
          Clickon(getwebelement(xml.getlocator("//locators/AccountName").replace("OCN", Inputdata[0].toString())));
         ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccountName");
         waitforPagetobeenable();
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/InstalledAssetNew")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installed Asset New");

	}

	public void createCustomerOrder(Object[] Inputdata) throws Exception {
		
		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), Inputdata[1].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity No");
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")),CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");
		
		
		SendKeys(getwebelement(xml.getlocator("//locators/DeliveryChannel")), Inputdata[3].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Delivery Channel");
		//System.out.println("Hello");
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartySearch")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Search");
		Clickon(getwebelement(xml.getlocator("//locators/PartySearchPopupDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Search Popup Dropdown");
		Clickon(getwebelement(xml.getlocator("//locators/PartyName")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Name");
		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyname")), Inputdata[4].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");
		Clickon(getwebelement(xml.getlocator("//locators/PickAccoutSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Account Ok");
		waitforPagetobeenable();
		//Clickon(getwebelement(xml.getlocator("//locators/PickAccntOk")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyAddrSearch")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Odering Party Address Search");
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyAddrSearchDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Address Search Dropdown");
		Clickon(getwebelement(xml.getlocator("//locators/PartyAddr")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Address");
		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyAddr")), Inputdata[5].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Input Party Address");
		//Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/PickAddrSearch")));
		waitforPagetobeenable();
		
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/PickAddrSubmit1")));
		
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address Submit");
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyContactSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Contact Search");
		Clickon(getwebelement(xml.getlocator("//locators/PartyContactPopupDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Contact Popup Dropdown");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/LastName"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/LastName")));
		
		//Thread.sleep(6000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on First Name");
		SendKeys(getwebelement(xml.getlocator("//locators/InputFirstname")), Inputdata[6].toString());
		Clickon(getwebelement(xml.getlocator("//locators/PickContactSearch")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Input First Name");
		Clickon(getwebelement(xml.getlocator("//locators/FirstnameSubmit")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on First Name Submit");
		
		Thread.sleep(4000);
		SendKeys(getwebelement(xml.getlocator("//locators/SalesChannel")), Inputdata[7].toString());
		
		
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Sales Channel");
		waitforPagetobeenable();
		//Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
		Thread.sleep(10000);
	}

	public void productSelectionHelper(Object[] Inputdata) throws InterruptedException, DocumentException {
		
		try {
			Clickon(getwebelement(xml.getlocator("//locators/ProductSelection").replace("ProductName", Inputdata[8].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add "+Inputdata[8].toString());
		
		}
		catch(Exception e)
		{
			Clickon(getwebelement(xml.getlocator("//locators/ProductSelectionArrow")));
			Clickon(getwebelement(xml.getlocator("//locators/ProductSelection").replace("ProductName", Inputdata[8].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add "+Inputdata[8].toString());
			
		}
		
	}

	

	public void openServiceOrderNumber() throws Exception {
		ServiceOrder.set(Gettext(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo"))));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated Service Order Reference No: " + ServiceOrder.get());
//Log.info(ServiceOrder.get());

		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
		Thread.sleep(5000);
	}


	public void enterMandatoryFieldsInHeader(Object[] Inputdata) throws Exception {
		
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		System.out.println("Enter New Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/AddOrderSubType"));
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
		SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")),Inputdata[9].toString());
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");


		WaitforElementtobeclickable(xml.getlocator("//locators/SubmitSubOrderType"));
		Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/ContractSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/ContractSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Contract Search");
		
		SendKeys(getwebelement(xml.getlocator("//locators/InputContractId")), Inputdata[10].toString());
		Clickon(getwebelement(xml.getlocator("//locators/ContractIdSearch")));
		waitforPagetobeenable();
		//Clickon(getwebelement(xml.getlocator("//locators/ContractIdSubmit")));
		
		//Clickon(getwebelement(
				//"//table[@summary='Pick Contract']//tr//td[text()='" + Inputdata[52 + 12].toString() + "']"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:  Select Contract");
		//Clickon(getwebelement(xml.getlocator("//locators/SubmitContract")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Contract");
		Thread.sleep(5000);
		if(!Inputdata[8].toString().equals("Cloud Unified Communications")|| !Inputdata[8].toString().equals("IP Voice Solutions") || !Inputdata[8].toString().equals("Professional Services")) {
		WaitforElementtobeclickable(xml.getlocator("//locators/NetworkReferenceSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
		Clickon(getwebelement(xml.getlocator("//locators/NetworkPlusSign")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectNetworkReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference Search");
		//SendKeys(getwebelement(xml.getlocator("//locators/InputNetworkReference")), HubNetworkReference.get());
//SendKeys(getwebelement(xml.getlocator("//locators/InputNetworkReference")),"HNS-871331328");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Input Network Reference");
		//Clickon(getwebelement(xml.getlocator("//locators/SearchNetworkReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SubmitNetworkReference")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Network Reference");
		}
		Thread.sleep(5000);
		savePage();
		Thread.sleep(10000);
		if(!Inputdata[8].toString().equals("Voice Line V")) {
		WaitforElementtobeclickable(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary"));
		SendKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")),
				Inputdata[12].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Existing Capacity Lead Time Primary");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
		waitforPagetobeenable();
//		savePage();
//		Thread.sleep(10000);
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/MaintenancePartySearch"));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartySearch")));
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyPopupDropdown")));
		Clickon(getwebelement(xml.getlocator("//locators/AccountStatus")));
		SendKeys(getwebelement(xml.getlocator("//locators/InputAccountStatus")), Inputdata[13].toString());
		Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSearch")));
		waitforPagetobeenable();
		Thread.sleep(4000);
		Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSubmit")));
		Thread.sleep(3000);
		
		
		WaitforElementtobeclickable(xml.getlocator("//locators/MaintenancePartyContact"));
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyContact")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyContactPopupDropdown")));
		waitandForElementDisplay((xml.getlocator("//locators/MaintenanceLastName")),5);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/MaintenanceLastName")));
		
		SendKeys(getwebelement(xml.getlocator("//locators/InputMaintenanceLastName")), Inputdata[14].toString());
		Clickon(getwebelement(xml.getlocator("//locators/InputMaintenanceLastNameSearch")));
		waitforPagetobeenable();
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyContactSubmit")));
		Thread.sleep(3000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/MaintenancePartyAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyAddress")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyAddresPopupDropdown")));
		Clickon(getwebelement(xml.getlocator("//locators/PartyAddresStreetName")));
		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyAddresStreetName")), Inputdata[15].toString());
		Clickon(getwebelement(xml.getlocator("//locators/InputPartyAddresStreetNameSearch")));
		waitforPagetobeenable();
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyAddressSubmit")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(8000);
		if(isElementPresent(xml.getlocator("//locators/SaveOrderChanges")))
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
			Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
			waitForpageload();
			System.out.println("page load succesfuuly now come to middle applet");
			waitforPagetobeenable();
		}
		}
	}
	public void VoiceFeatureTab(Object[] Inputdata) throws Exception {
		if(Inputdata[0].toString().equals("Voice Line V")) {
		Thread.sleep(5000);	
		Clickon(getwebelement(xml.getlocator("//locators/VoicefeaturesTab")));
		Thread.sleep(5000);
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on SaveAccess");
		waitforPagetobeenable();
		Thread.sleep(30000);
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/showfullinfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full Info");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ByPassNumber")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BypassNumber");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/CallBarring")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallBarring");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/CallBarringDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/CallBarringDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallBarringDropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/Callbarringvalue"));
		Clickon(getwebelement(xml.getlocator("//locators/Callbarringvalue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallBarringValue");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/Connectivity"));
		Clickon(getwebelement(xml.getlocator("//locators/Connectivity")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Connectivity");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/carrierHotel"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/carrierHotel")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on carrierHotel");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/FastTrack"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/FastTrack")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on FastTrack");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/InternalCabling"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/InternalCabling")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InternalCabling");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/LOngLining"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/LOngLining")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LongLining");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/DisasterRecovery"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/DisasterRecovery")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecovery");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberOfDrPlans"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberOfDrPlans")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberOfDRPlans");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberofDRplansValue"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberofDRplansValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NoofDrValues");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/InboundCallRerouting"));
		Clickon(getwebelement(xml.getlocator("//locators/InboundCallRerouting")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InboundCallRerouting");
		Thread.sleep(5000);
		//WaitforElementtobeclickable(xml.getlocator("//locators/DestinationNumberforRerouting"));
		Clickon(getwebelement(xml.getlocator("//locators/DestinationNumberforRerouting")));
		SendKeys(getwebelement(xml.getlocator("//locators/DestinationNumberforRerouting")),"1234");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DestinationNumberforRerouting");
		Thread.sleep(5000);
		//WaitforElementtobeclickable(xml.getlocator("//locators/MinimumSpendCommitment"));
		Clickon(getwebelement(xml.getlocator("//locators/MinimumSpendCommitment")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MinimumSpendCommitment");
		Thread.sleep(5000);
		//WaitforElementtobeclickable(xml.getlocator("//locators/PartialNumberReplacement"));
		Clickon(getwebelement(xml.getlocator("//locators/PartialNumberReplacement")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PartialNumberReplacement");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/internationalCode")));
		SendKeys(getwebelement(xml.getlocator("//locators/internationalCode")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InternationalCode");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/LOcalAreaCode")));
		SendKeys(getwebelement(xml.getlocator("//locators/LOcalAreaCode")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LOcalAreaCode");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NUmberRangeStart")));
		SendKeys(getwebelement(xml.getlocator("//locators/NUmberRangeStart")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NUmberRangeStart");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeEnd")));
		SendKeys(getwebelement(xml.getlocator("//locators/NumberRangeEnd")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeEnd");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementLOcalAreaCode")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementLOcalAreaCode")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementLOcalAreaCode");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementMainNumber")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementMainNumber")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementMainNumber");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeStart")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeStart")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementNumberRangeStart");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeEnd")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeEnd")),"1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementNumberRangeEnd");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PartialNumberConfiguration")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PartialNumberConfiguration");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/internationalCode1")));
		SendKeys(getwebelement(xml.getlocator("//locators/internationalCode1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on internationalCode1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/LOcalAreaCode1")));
		SendKeys(getwebelement(xml.getlocator("//locators/LOcalAreaCode1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LOcalAreaCode1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NUmberRangeStart1")));
		SendKeys(getwebelement(xml.getlocator("//locators/NUmberRangeStart1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NUmberRangeStart1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeEnd1")));
		SendKeys(getwebelement(xml.getlocator("//locators/NumberRangeEnd1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeEnd1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementLOcalAreaCode1")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementLOcalAreaCode1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementLOcalAreaCode1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementMainNumber1")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementMainNumber1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementMainNumber1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeStart1")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeStart1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementNumberRangeStart1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeEnd1")));
		SendKeys(getwebelement(xml.getlocator("//locators/ReplacementNumberRangeEnd1")),"2");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ReplacementNumberRangeEnd1");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceActivationSupport")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceActivationSupport");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/LanguageDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/LanguageDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LanguageDropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/LanguageValue"));
		Clickon(getwebelement(xml.getlocator("//locators/LanguageValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LanguageValue");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/TelephoneDirectoryServices")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TelephoneDirectoryServices");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/CrossButton1")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CrossButton");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryContact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryContact");
		Thread.sleep(5000);
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickSaveConfig")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickSaveConfig")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ClickSaveConfig");
		waitforPagetobeenable();
		Thread.sleep(30000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryContact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryContact");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryAdd")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryAdd");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterAdd2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterAdd2");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryAdd")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryAdd");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryOk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryOk");
		Thread.sleep(5000);
		}
		}
	public void MandatoryFields(Object[] Inputdata) throws Exception {
		if(Inputdata[0].toString().equals("Voice Line V")) {
		
		Clickon(getwebelement(xml.getlocator("//locators/NumberManagementTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberManagementTab");
		Thread.sleep(15000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatus")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusDropDown")));
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusValueActive"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusValueActive")));
		Thread.sleep(5000);
    	waitforPagetobeenable();
		/*Clickon(getwebelement(xml.getlocator("//locators/ManualValidation")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Manual Validation Button");
		waitforPagetobeenable();*/
		Thread.sleep(10000);
		}
    }
	public void OperationAttribute(Object[] Inputdata) throws Exception {
		if(Inputdata[0].toString().equals("Voice Line V")) {
	Clickon(getwebelement(xml.getlocator("//locators/Voiceconfigtab")));	
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Voiceconfigtab");
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute"));
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/AttributeNew")));
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/AttributeNameDropDown"));
	Clickon(getwebelement(xml.getlocator("//locators/AttributeNameDropDown")));
	Clickon(getwebelement(xml.getlocator("//locators/AttributeName")));
	//SendKeys(getwebelement(xml.getlocator("//locators/AttributeName")),"Scenario");
	//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeName")),Keys.ENTER);
	Thread.sleep(5000);
	/*Clickon(getwebelement(xml.getlocator("//locators/AttributeValue")));
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/AttributeValueDropDown"));
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValueDropDown")));
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValue")));*/
	//SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),"Scenario 1");
	//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),Keys.ENTER);
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/OtherTab")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OtherTab");
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute1"));
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute1")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValue1")));
	SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")),"test");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute2"));
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute2")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValue1")));
	SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")),"test");
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute"));
	Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/AttributeValueDropDown"));
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValueDropDown")));
	Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/AttributeValue")));
	//SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),"Scenario 1");
	//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),Keys.ENTER);
	Thread.sleep(5000);
		}
    }
	
	public void SelectServiceGroupTab(Object[] Inputdata) throws Exception {
		if(Inputdata[0].toString().equals("Voice Line V")) {
	waitforPagetobeenable();
	Clickon(getwebelement(xml.getlocator("//locators/DropDown")));
//	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Drop down");
//	Clickon(getwebelement(xml.getlocator("//locators/InstallationAndTestTab")));
	Select(getwebelement(xml.getlocator("//locators/DropDown")),"Service Group");
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Group Tab");
	Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupNew")));
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/serviceGrouplookup"));
	Clickon(getwebelement(xml.getlocator("//locators/serviceGrouplookup")));
	//Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupOk")));
	Thread.sleep(5000);
	/*Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupItemNew")));
	Thread.sleep(5000);
	WaitforElementtobeclickable(xml.getlocator("//locators/ServiceGroupItemlookup"));
	Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupItemlookup")));
	Thread.sleep(5000);
	//Clickon(getwebelement(xml.getlocator("//locators/selectitem")));
	//Thread.sleep(5000);
	Clickon(getwebelement(xml.getlocator("//locators/ServiceorderOk")));
	Thread.sleep(5000);
	savePage();*/
		}
	}
		public void NumberManagementTab(Object[] Inputdata) throws Exception {
			if(Inputdata[0].toString().equals("Voice Line V")) {
		Thread.sleep(5000);	
		Clickon(getwebelement(xml.getlocator("//locators/NumberManagementTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberManagementTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortGroupTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupNew")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortGroupNew");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/CallDistribution")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallDistribution");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/callDistributioninput")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on callDistributioninput");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/CallDistributionDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/CallDistributionDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallDistributionDropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/CallDistributionValue"));
		Clickon(getwebelement(xml.getlocator("//locators/CallDistributionValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallDistributionValue");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/AssignedPortNew")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AssignedPortNew");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Unassignedport")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Unassignedport");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderListTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceOrderListTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderLink")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceOrderLink");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberManagementTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberManagementTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortGroupTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/AssignedPortNew")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AssignedPortNew");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Unassignedport")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Unassignedport");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeTab");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeListNew")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeListNew");
		Thread.sleep(10000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberPlan")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberPlan");
		Thread.sleep(5000);
		//safeJavaScriptClick(getwebelement(xml.getlocator("//locators/NumberPlaninput")));
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberpLanDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberpLanDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberpLanDropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberPlanValue"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberPlanValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberPlanValue");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ColtREservationRef")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ColtREservationRef");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/ColtNumberReservatioRefinput")));
		SendKeys(getwebelement(xml.getlocator("//locators/ColtNumberReservatioRefinput")),"123");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ColtNumberReservatioRefinput");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/MainNumber")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MainNumber");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/MainnumberField")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MainnumberField");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/MainNumberInput")));
		SendKeys(getwebelement(xml.getlocator("//locators/MainNumberInput")),"12");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MainNumberInput");
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/LocalAreaCode")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LocalAreaCode");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/LocalAreaValue")));
		SendKeys(getwebelement(xml.getlocator("//locators/LocalAreaValue")),"12");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on LocalAreaValue");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberrangeStart")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStartValue")));
		SendKeys(getwebelement(xml.getlocator("//locators/NumberRangeStartValue")),"12");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeEnd2")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeinput")));
		SendKeys(getwebelement(xml.getlocator("//locators/NumberRangeinput")),"15");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatus")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusDropDown")));
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusValue"));
		Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusValue")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupId")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortGroupIdLookup"));
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupIdLookup")));
		//Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PortGroupOk")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/addressUid")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/addressUidLookup"));
		Clickon(getwebelement(xml.getlocator("//locators/addressUidLookup")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), "parker");			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
		WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[62].toString());			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[63].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Thread.sleep(3000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");			
		Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");			
		Clickon(getwebelement(xml.getlocator("//locators/BCN")));
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/BCNLookup"));
		Clickon(getwebelement(xml.getlocator("//locators/BCNLookup")));
		//Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/BillingProfileOK")));
		Thread.sleep(5000);
			}
		}
	public void VoiceConfigTab(Object[] Inputdata) throws Exception {
		if(Inputdata[0].toString().equals("Voice Line V")) {
		Clickon(getwebelement(xml.getlocator("//locators/TrunkName")));
		SendKeys(getwebelement(xml.getlocator("//locators/TrunkName")),"Trunk"+timeStamp+"");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TrunkName");
		WaitforElementtobeclickable(xml.getlocator("//locators/AccessLineTypeDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/AccessLineTypeDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccessLineDropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/AccessLineType"));
		Clickon(getwebelement(xml.getlocator("//locators/AccessLineType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccessLineTypw");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/AccessServiceIdlookup")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access ServiceId Search");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/PickServiceSubmit")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Submit Pick Service");
		Thread.sleep(5000);
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/showfullinfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full info");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/TrunkConfigurationplus")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trunk Configuration Plus");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/outsidebuissnesshourinstallation")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on outside buissness hour installation");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/SaveButtonClick")));
		Thread.sleep(5000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/TrunckConfigplus")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trunk Configuration Plus");
		Thread.sleep(5000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/OtherTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Other Tab");
		Thread.sleep(5000);
		//Thread.sleep(5000);
		//WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			
		//waitforPagetobeenable();
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SelectSiteSearchAccess"));
		//Moveon(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Site");	
		WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), "parker");			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
		WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[62].toString());			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[63].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Thread.sleep(3000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");			
		Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");			
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickBuildingAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Buiding for Site");
		Clickon(getwebelement(xml.getlocator("//locators/PickBuildingButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Buiding for Site");
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickSiteAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Site");
		Clickon(getwebelement(xml.getlocator("//locators/PickSiteButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Site");
		Thread.sleep(10000);			
		
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click ON Port ID Drop Down");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortId"));
		Clickon(getwebelement(xml.getlocator("//locators/PortId")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Port ID");
		Thread.sleep(5000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirectionDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/TrafficDirectionDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Traffic Direction DropDown");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirection"));
		Clickon(getwebelement(xml.getlocator("//locators/TrafficDirection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Traffic Direction");
		Thread.sleep(5000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/CabinetTypeDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/CabinetTypeDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/Cabinet_Type"));
		Clickon(getwebelement(xml.getlocator("//locators/Cabinet_Type")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet Type");
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/Cabinet_Id")));
		SendKeys(getwebelement(xml.getlocator("//locators/Cabinet_Id")),"Test");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet ID");
		Clickon(getwebelement(xml.getlocator("//locators/Shelf_ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/Shelf_ID")),"Test");	
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Shelf ID");
		Clickon(getwebelement(xml.getlocator("//locators/SIPShowfullinfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full info");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/Isdnport"));
		Clickon(getwebelement(xml.getlocator("//locators/Isdnport")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on IsdnPort");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on POrtID DropDown Access");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccessValue"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccessValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortID DropDown Access Value");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/Isdnport"));
		Clickon(getwebelement(xml.getlocator("//locators/Isdnport")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Isdn Port");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccess2"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccess2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Port ID DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccessValue2"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccessValue2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on POrtID Value");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirectionDropDown2"));
		Clickon(getwebelement(xml.getlocator("//locators/TrafficDirectionDropDown2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Traffic Direction DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirection1"));
		Clickon(getwebelement(xml.getlocator("//locators/TrafficDirection1")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TRaffic Direction Value");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SipTDMGateway"));
		Clickon(getwebelement(xml.getlocator("//locators/SipTDMGateway")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on sip TDM Gateway");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccess3"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccess3")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortId DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PortIDDropDownAccessValue3"));
		Clickon(getwebelement(xml.getlocator("//locators/PortIDDropDownAccessValue3")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortId Value");
		Thread.sleep(5000);
		
		//WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirectionDropDown"));
		//Clickon(getwebelement(xml.getlocator("//locators/TrafficDirectionDropDown")));
		//Thread.sleep(5000);
		//WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirection"));
		//Clickon(getwebelement(xml.getlocator("//locators/TrafficDirection")));
		//Thread.sleep(5000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/CabinetTypeDropDown2"));
		Clickon(getwebelement(xml.getlocator("//locators/CabinetTypeDropDown2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/CabinetType1"));
		Clickon(getwebelement(xml.getlocator("//locators/CabinetType1")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet Type");
		Thread.sleep(5000);
		
		Clickon(getwebelement(xml.getlocator("//locators/Cabinet_Id1")));
		SendKeys(getwebelement(xml.getlocator("//locators/Cabinet_Id1")),"Test");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Cabinet ID");
		Clickon(getwebelement(xml.getlocator("//locators/ShelfID1")));
		SendKeys(getwebelement(xml.getlocator("//locators/ShelfID1")),"Test");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ShelfID");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
		Thread.sleep(5000);
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Save");
		waitforPagetobeenable();
		Thread.sleep(30000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SelectSiteSearchAccess"));
		//Moveon(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		Thread.sleep(3000);
		//WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		//Thread.sleep(3000);
		//WaitforElementtobeclickable(xml.getlocator("//locators/SelectSiteSearchAccess"));
		//Moveon(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		//safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Site");	
		WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), "parker");			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
		WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[62].toString());			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[63].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), "*");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Thread.sleep(3000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");			
		Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");			
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickBuildingAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Buiding for Site");
		Clickon(getwebelement(xml.getlocator("//locators/PickBuildingButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Buiding for Site");
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickSiteAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Site");
		Clickon(getwebelement(xml.getlocator("//locators/PickSiteButtonAccess")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Site");
		Thread.sleep(10000);			
		//Thread.sleep(30000);
		
		
		Thread.sleep(10000);
										
		WaitforElementtobeclickable(xml.getlocator("//locators/TypeoflineDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/TypeoflineDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TypeofLine DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/TypeofLinevalue"));
		Clickon(getwebelement(xml.getlocator("//locators/TypeofLinevalue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on typeOfLineValue");
		Thread.sleep(5000);
				
		//Clickon(getwebelement(xml.getlocator("//locators/NumberOfLines")));
		//clear();
		//SendKeys(getwebelement(xml.getlocator("//locators/NumberOfLines")),"2");
		//Thread.sleep(5000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/NumberOfLines"));		
		Clear(getwebelement(xml.getlocator("//locators/NumberOfLines")));
		SendKeys(getwebelement(xml.getlocator("//locators/NumberOfLines")),"2" );
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberOfLines");
		//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/CabinetID"))), Keys.TAB);
		Thread.sleep(4000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/CRC4DropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/CRC4DropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CRC4 DropDown");
				
		WaitforElementtobeclickable(xml.getlocator("//locators/CRC4Value"));
		Clickon(getwebelement(xml.getlocator("//locators/CRC4Value")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CRC4 Value");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/MaxChannelDropDown"));
		Clickon(getwebelement(xml.getlocator("//locators/MaxChannelDropDown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MaxChannel DropDown");
		//Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/MaxChannelvalue"));
		Clickon(getwebelement(xml.getlocator("//locators/MaxChannelvalue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MaxChannelValue");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/VoiceOptionshowinfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full info");
		Thread.sleep(5000);
				
		Clickon(getwebelement(xml.getlocator("//locators/ColtDDIRanges")));
		Thread.sleep(5000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ColtDDIRanges");
		WaitforElementtobeclickable(xml.getlocator("//locators/POrtInDDIRanges"));
		Clickon(getwebelement(xml.getlocator("//locators/POrtInDDIRanges")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortInDDIRanges");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/ResellerConfiguration"));
		Clickon(getwebelement(xml.getlocator("//locators/ResellerConfiguration")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ResellerConfiguration");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/UnratedCDRs"));
		Clickon(getwebelement(xml.getlocator("//locators/UnratedCDRs")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on UnratedCRD's");
		Thread.sleep(5000);
		
								
		Clickon(getwebelement(xml.getlocator("//locators/NOofsingleColtDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/NOofsingleColtDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/NOofsingleColtDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/NOofsingleColtDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NoofsingleColtDDI's");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Noof10ColtDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/Noof10ColtDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/Noof10ColtDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Noof10ColtDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Noof10ColtDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Noof100coltDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/Noof100coltDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/Noof100coltDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Noof100coltDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Noof100coltDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Noof1000coldDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/Noof1000coldDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/Noof1000coldDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Noof1000coldDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Noof1000coldDDIs");
		Thread.sleep(5000);
				
		Clickon(getwebelement(xml.getlocator("//locators/NoOfsinglePOrtedDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/NoOfsinglePOrtedDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/NoOfsinglePOrtedDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/NoOfsinglePOrtedDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NoOfsinglePOrtedDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Noof10PortedDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/Noof10PortedDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/Noof10PortedDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Noof10PortedDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Noof10PortedDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/NOof100PortedDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/NOof100PortedDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/NOof100PortedDDIs")),"1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/NOof100PortedDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NOof100PortedDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/Noof1000PortedDDIs")));
		Clickon(getwebelement(xml.getlocator("//locators/Noof1000PortedDDIs")));
		Clear(getwebelement(xml.getlocator("//locators/Noof1000PortedDDIs")));
		SendKeys(getwebelement(xml.getlocator("//locators/Noof1000PortedDDIs")),"1");
		//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Noof1000PortedDDIs"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NOof1000PortedDDIs");
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/CrossButton1")));
		Thread.sleep(5000);
		}
		}
	
	public void middleApplet(Object Inputdata[]) throws InterruptedException, DocumentException, IOException
	{
		System.out.println("middle applet start");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml2.getlocator("//locators/SupportStarDate"));
		//Clear(getwebelement(xml2.getlocator("//locators/SupportStarDate")));
		System.out.println("dhoni");
		//getwebelement(xml2.getlocator("//locators/SupportStarDate")).clear();
		//ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");
		Clickon(getwebelement(xml2.getlocator("//locators/SupportStarDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupportStarDate");
		SendKeys(getwebelement(xml2.getlocator("//locators/SupportStarDate")),Inputdata[78].toString());
		
		System.out.println("middle applet start1");
		WaitforElementtobeclickable(xml2.getlocator("//locators/SupprtEndDate"));
		//Clear(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));
		System.out.println("dhoni");
		//getwebelement(xml2.getlocator("//locators/SupprtEndDate")).clear();
	//	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");
		
		Clickon(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupprtEndDate");
		System.out.println("the End date value is"+Inputdata[79].toString());
		
		SendKeys(getwebelement(xml2.getlocator("//locators/SupprtEndDate")),Inputdata[79].toString());
		System.out.println("dhoni");
		
		
		
		Clickon(getwebelement(xml2.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Recurring Charge MRC Search");
		Thread.sleep(10000);
		
		System.out.println("middle applet end");
	}

	public void enterMandatoryDetailsInMiddleApplet(Object[] Inputdata) throws Exception {
		switch (Inputdata[8].toString()) {
		case "Cloud Unified Communications": {
			middleApplet(Inputdata);
		break;
		}
		case "IP Voice Solutions": {
			middleApplet(Inputdata);
		break;
		}

		case "SIP Trunking": {
			
			System.out.println("Enter SIP information Middle Applet");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter SIP Trunking information Middle Applet");
			// Took Input data[34] from excel need to change the cell value as per data present in Excel file //
			
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountryDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountry"));
			Clickon(getwebelement(xml.getlocator("//locators/NewVoiceServiceCountry")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Voice Service Country Selected");
						
			WaitforElementtobeclickable(xml.getlocator("//locators/CallAdmissionControl"));	
			Clickon(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
			Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
			SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")),"12" );
			
			Thread.sleep(5000);
			
			
			
			
			//	Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountry")));
		//	getwebelement(xml.getlocator("//locators/VoiceServiceCountry")).clear();
		//	SendKeys(getwebelement(xml.getlocator("//locators/VoiceServiceCountry")),Inputdata[77].toString());
		//	Thread.sleep(5000);
		//  Clickon(getwebelement(xml.getlocator("//locators/DropDownCountry")));
		//  Thread.sleep(5000);
		//	Clickon(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		//	getwebelement(xml.getlocator("//locators/CallAdmissionControl")).clear();
		//	SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")),"12");
		    
		//	ExtentTestManager.getTest().log(LogStatus.PASS, " Provide Value toCall Admission Control");
		   
		//	WaitforElementtobeclickable(xml.getlocator("//locators/CallAdmissionControl"));
			//WaitforElementtobeclickable(xml.getlocator("//locators/CallAdmissionControl"));		
			//Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		//	SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")),"12" );
			//Thread.sleep(30000);
			
			
		//	Clickon(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		//	SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl"))"12");
		//	SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl").replace("Value","Call Admission Control")), "12");		
			

			
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountry"));
		//	Clickon(getwebelement(xml.getlocator("//locators/NewVoiceServiceCountry")));
			
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/NumberOfSignallingTrunks"));		
			Clickon(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")));
			Clear(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")));
			SendKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")),"1");
					 
		    ExtentTestManager.getTest().log(LogStatus.PASS, " Input Number of Signalling Trunks");
		    
		
		    Thread.sleep(3000);
			WaitforElementtobeclickable(xml.getlocator("//locators/EgressNumberFormatDropDownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/EgressNumberFormatDropDownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/EgreeNUmberFormat"));
			Clickon(getwebelement(xml.getlocator("//locators/EgreeNUmberFormat")));
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/EgreeNUmberFormat")), Keys.TAB);
			
		    Thread.sleep(8000);
		    
		    WaitforElementtobeclickable(xml.getlocator("//locators/InvlidCLITreatmet"));
		    Clickon(getwebelement(xml.getlocator("//locators/InvlidCLITreatmet")));
		    Clear(getwebelement(xml.getlocator("//locators/InvlidCLITreatmet")));
		    SendKeys(getwebelement(xml.getlocator("//locators/InvlidCLITreatmet")),"Allow");
		
			Thread.sleep(10000);
	 			   
		    WaitforElementtobeclickable(xml.getlocator("//locators/TotalNumberDDIs"));
		    Clickon(getwebelement(xml.getlocator("//locators/TotalNumberDDIs")));
		    Clear(getwebelement(xml.getlocator("//locators/TotalNumberDDIs")));
		   
		    SendKeys(getwebelement(xml.getlocator("//locators/TotalNumberDDIs")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Provide Value to Total Number of DDI");
			Thread.sleep(10000);
			
		    WaitforElementtobeclickable(xml.getlocator("//locators/IncomingDDIs"));
		    Clickon(getwebelement(xml.getlocator("//locators/IncomingDDIs")));
			Clear(getwebelement(xml.getlocator("//locators/IncomingDDIs")));
			SendKeys(getwebelement(xml.getlocator("//locators/IncomingDDIs")), "10");
		
			Thread.sleep(5000);
					
			WaitforElementtobeclickable(xml.getlocator("//locators/RoutingSequence"));
			Clickon(getwebelement(xml.getlocator("//locators/RoutingSequence")));
			Clear(getwebelement(xml.getlocator("//locators/RoutingSequence")));
			SendKeys(getwebelement(xml.getlocator("//locators/RoutingSequence")), "Sequential");
			
			Thread.sleep(8000);
			
			Clickon(getwebelement(xml.getlocator("//locators/CLIBillingChkBox")));
		    Thread.sleep(5000);
						
			Clickon(getwebelement(xml.getlocator("//locators/FirstCodec")));
			
			WaitforElementtobeclickable(xml.getlocator("//locators/FirstCodec"));
		
			Clear(getwebelement(xml.getlocator("//locators/FirstCodec")));
			SendKeys(getwebelement(xml.getlocator("//locators/FirstCodec")), "G.711alaw");
			
			Thread.sleep(5000);
								 
			Clickon(getwebelement(xml.getlocator("//locators/ClickToShowFullInfo")));
			Thread.sleep(8000);
		//	Clickon(getwebelement(xml.getlocator("//locators/TextBoxSiteContact")));
			
		//	Thread.sleep(3000);	
			Clickon(getwebelement(xml.getlocator("//locators/SIPSiteContact")));
			
			Thread.sleep(10000);
		//	Clickon(getwebelement(xml.getlocator("//locators/FirstRecordSelection")));
		//	Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/ClickOK")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/ServiceParty")));
			Thread.sleep(5000);
		//	Clickon(getwebelement(xml.getlocator("//locators/ServicePartyRecordSelect")));
		//	Thread.sleep(5000);
		
			WaitforElementtobeclickable(xml.getlocator("//locators/OKSelection"));
			Clickon(getwebelement(xml.getlocator("//locators/OKSelection")));
			Thread.sleep(8000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPDeliveryTeamNewCountry"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPDeliveryTeamNewCountry")));
		    SendKeys(getwebelement(xml.getlocator("//locators/SIPDeliveryTeamNewCountry")),"United Kingdom");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Delivery  Team Country ");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPDeliveryTeamNewCity")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPDeliveryTeamNewCity")),"London");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIPDeliveryTeamNewCity")),Keys.ENTER);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Delivery Team City ");
			Thread.sleep(5000);
			/*
			WaitforElementtobeclickable(xml.getlocator("//locators/WLECInvoicingRadioButton"));
			Clickon(getwebelement(xml.getlocator("//locator/WLECInvoicingRadioButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " WLEC Radio Button");
			
			Thread.sleep(5000); */
			Clickon(getwebelement(xml.getlocator("//locators/CloseSymbol")));
			Thread.sleep(5000); 
						 
			Clickon(getwebelement(xml.getlocator("//locators/Save")));
			Thread.sleep(10000);
			
			//Assert.assertTrue("SIP Trunking", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, " SIP Trunking Middle Applet Success ");
		      //	assert equals("SIP Trunking");
			
			/*-------------Voice Config with Trunk 1--------*/
									
			/*Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigTab")));
			//ExtentTestManager.getTest().log(LogStatus.PASS, " SIPVoiceConfigTab");
			 * 
			 */
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPVoiceConfigExpandSymbol"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigExpandSymbol")));
			
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkSequence")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkSequence")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Trunk Sequence ");
			
			Thread.sleep(5000);
			
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkName")),"Trunk1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Name");
			Thread.sleep(3000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/AccessLineTypeDropDown"));
			Clickon(getwebelement(xml.getlocator("//locators/AccessLineTypeDropDown")));
			Thread.sleep(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/AccessLineType"));
			Clickon(getwebelement(xml.getlocator("//locators/AccessLineType")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/AccessServiceIdlookup")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Access Service Idlookup ");
			
			//		ExtentTestManager.getTest
			//Try end
			/*
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigExpandSymbol")));
		
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkSequence")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkSequence")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Trunk Sequence ");
			Thread.sleep(3000);
			
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkName")),"Trunk1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Name");
			Thread.sleep(3000);
			
						
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPAccessLineType"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPAccessLineType")));
			Clear(getwebelement(xml.getlocator("//locators/SIPAccessLineType")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPAccessLineType")), "Colt Access");
			Thread.sleep(5000);
			
		
			
			//	SendKeys(getwebelement(xml.getlocator("//locators/SIPAccessLineType")),Inputdata[12].toString());
			//Clickon(getwebelement(xml.getlocator("//locators/SIPAccessLineTypeValue")));
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPAccessServieId"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPAccessServieId"))); */
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SearchServiceIDbox"));
			Clear(getwebelement(xml.getlocator("//locators/SearchServiceIDbox")));
			Clickon(getwebelement(xml.getlocator("//locators/SearchServiceIDbox")));
			SendKeys(getwebelement(xml.getlocator("//locators/SearchServiceIDbox")), "Product");	
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SearchText"));
			Clickon(getwebelement(xml.getlocator("//locators/SearchText")));
			SendKeys(getwebelement(xml.getlocator("//locators/SearchText")), "IP Access");
			Thread.sleep(3000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SearchButtonProcess"));
			Clickon(getwebelement(xml.getlocator("//locators/SearchButtonProcess")));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SearchServiceIdOKButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SearchServiceIdOKButton")));
			Thread.sleep(5000);
			
			
		//	SendKeys(getwebelement(xml.getlocator("//locators/ExternalAccessServiceRef")),Inputdata[11].toString());		
		//	ExtentTestManager.getTest().log(LogStatus.PASS, "Enter external access Service reference");
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerOriginatingIPv4"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerOriginatingIPv4")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPCustomerOriginatingIPv4")),"10.7.235.29/24");	
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Customer Originating IPv4");
			Thread.sleep(5000);
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SearchServiceIdOKButton"));
		//	Clickon(getwebelement(xml.getlocator("//locators/SearchServiceIdOKButton")));
		//	Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigExpandSymbol")));
			//Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerOriginatingIPv6"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerOriginatingIPv6")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPCustomerOriginatingIPv6")),"10.7.235.29/24");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Customer Originating IPv6");
			Thread.sleep(5000);
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerTerminatingIPv4"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv4")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv4")),"10.7.235.2/24");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Customer Terminating IPv4");
			
			Thread.sleep(3000);
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerTerminatingIPv6"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv6")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv6")),"10.7.235.2/24");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Customer Terminating IPv6 ");
			Thread.sleep(5000);
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPSignalingTransportProtocol"));
		//	Clickon(getwebelement(xml.getlocator("//locators/SIPSignalingTransportProtocol")));
		//	Clear(getwebelement(xml.getlocator("//locators/SIPSignalingTransportProtocol")));
		//	SendKeys(getwebelement(xml.getlocator("//locators/SIPSignalingTransportProtocol")), "TCP");	
	//		ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPSignalingTransportProtocol");
	//		Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPSignalingTransportProtocol"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPSignalingTransportProtocol")));
			Thread.sleep(2000);
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPSignalingTransportProtocolDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPSignalingTransportProtocolDropdown")));
			Thread.sleep(5000);
				
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPvoIPProtocol"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPvoIPProtocol")));
			Thread.sleep(2000);
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPvoIPProtocolDropDown"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPvoIPProtocolDropDown")));
			Thread.sleep(5000);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPvoIPProtocol");
			//	Clickon(getwebelement(xml.getlocator("//locators/SIPvoIPProtocol")));
			Thread.sleep(5000);
		
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPEncryptionType"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPEncryptionType")));
			Clear(getwebelement(xml.getlocator("//locators/SIPEncryptionType")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPEncryptionType")), "TLS on, SRTP off");	
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPEncryptionType");

			Thread.sleep(5000);
							
		//	Clickon(getwebelement(xml.getlocator("//locators/SIPEncryptionDropdownValue")));
			//Thread.sleep(5000);
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPProportionPercent"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPProportionPercent")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPProportionPercent")),"12");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPProportionPercent");
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkSiteNameAlias"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkSiteNameAlias")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkSiteNameAlias")),"Alias1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPTrunkSiteNameAlias");
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPTLSCipherSuiteDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTLSCipherSuiteDropdown")));
			Clear(getwebelement(xml.getlocator("//locators/SIPTLSCipherSuiteDropdown")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTLSCipherSuiteDropdown")),"TLS_RSA_WITH_AES_128_CBC_SHA (Priority 1)");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPTLSCipherSuite");
			Thread.sleep(5000);
					
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPTLSCertifiedSupplier"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTLSCertifiedSupplier")));	
			Clear(getwebelement(xml.getlocator("//locators/SIPTLSCertifiedSupplier")));
		    SendKeys(getwebelement(xml.getlocator("//locators/SIPTLSCertifiedSupplier")),"NA"); 
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPTLSCertifiedSupplier");		
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPNATRequired"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPNATRequired")));					
		    Clear(getwebelement(xml.getlocator("//locators/SIPNATRequired")));
		    SendKeys(getwebelement(xml.getlocator("//locators/SIPNATRequired")),"N");
		 	ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPNATRequired");			
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPNNIWANIPv4Address"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPNNIWANIPv4Address")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNNIWANIPv4Address")),"10.7.235.24/2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter NNI WAN IPv4");			
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPNNIWANIPv6Address"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPNNIWANIPv6Address")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNNIWANIPv6Address")),"10.7.235.24/2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter NNI WAN IPv6 ");			
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPv4AddressRangeNAT"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPv4AddressRangeNAT")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPv4AddressRangeNAT")),"2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter SIPSignalingTransportProtocol ");
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPVLANStartEndRange"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVLANStartEndRange")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPVLANStartEndRange")),"2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter VLAN StartEnd Range ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPVLANID"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVLANID")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPVLANID")),"2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter VLANID ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPASNumber"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPASNumber")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPASNumber")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter ASNumber ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPColtHostEquipmentIPv4Address"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPColtHostEquipmentIPv4Address")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPColtHostEquipmentIPv4Address")),"10.7.235.24/2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "EnterColtHostEquipmentIPv4Address ");
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPColtHostEquipmentIPv6Address"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPColtHostEquipmentIPv6Address")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPColtHostEquipmentIPv6Address")),"10.7.235.24/2");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter ColtHostEquipmentIPv6Address ");			
			Thread.sleep(5000);
		
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkMaxNumberofSimoultaniousCalls"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkMaxNumberofSimoultaniousCalls")));
			Clear(getwebelement(xml.getlocator("//locators/SIPTrunkMaxNumberofSimoultaniousCalls")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkMaxNumberofSimoultaniousCalls")),"4");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Max Number of Simoultanious Calls ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkInboundOutboundSplit"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkInboundOutboundSplit")));
			Clear(getwebelement(xml.getlocator("//locators/SIPTrunkInboundOutboundSplit")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkInboundOutboundSplit")),"No");			
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Inbound Outbound Split ");
			Thread.sleep(5000);

		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkMaxInboundCalls"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkMaxInboundCalls")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkMaxInboundCalls")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Max InboundCalls ");
			Thread.sleep(5000);		
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkOverCallLimitForTrunk"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkOverCallLimitForTrunk")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkOverCallLimitForTrunk")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Over Call Limit For Trunk ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkIncallLimitForTrunk"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkIncallLimitForTrunk")));		
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkIncallLimitForTrunk")),"10");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Incall Limit For Trunk ");
			Thread.sleep(5000);
			
		//	WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkOutCallLimitForTrunk"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkOutCallLimitForTrunk")));				
			SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkOutCallLimitForTrunk")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Out Call Limit For Trunk");
			Thread.sleep(5000);
			
			//WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkTypeofCAC"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkTypeofCAC")));		
			Clear(getwebelement(xml.getlocator("//locators/SIPTrunkTypeofCAC")));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkTypeofCACDropDown")));	
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkTypeofCACDropDownSelect")));	
			//SendKeys(getwebelement(xml.getlocator("//locators/SIPTrunkTypeofCAC")),"Individual Trunk CAC");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Trunk Type of CAC ");
			Thread.sleep(10000);
			
			/*
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPTrunkShowFullInfo"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPTrunkShowFullInfo"))); */
		
					
		//	Thread.sleep(5000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerIPPbxIdTab"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerIPPbxIdTab")));
			
			Thread.sleep(5000);
	
			Clickon(getwebelement(xml.getlocator("//locators/SIPAddrecordSymbolIPPBX")));
			Thread.sleep(5000);
	
							
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAliasName")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click IPPBX Alias Name Field");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAliasNameInputField")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAliasNameInputField")),"test1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter IPPBX Alias Name Field");
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXModel")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click IPPBX Model");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXModelInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAliasNameInputField")),"SWE Lite 7.0");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter IPPBX Model Field");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXVendor")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click IPPBX Vendor");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXVendorInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAliasNameInputField")),"Sonus");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter IPPBX Vendor Field");
			Thread.sleep(5000);
					
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPSOftwareVersionField")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click IPPBX Version");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPSoftwareVersionInput")));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupoptionClick")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter IPPBX Version Field");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address UID");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupoptionClick"))); 
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupStreetName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupStreetName")),"Parker");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Street Name ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")),"United Kingdom");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")), Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Country");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")),"London");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter City");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")),"*");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Postal Code");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")),"*");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Premise Code");
			Thread.sleep(5000);
			
			//Click Address search button
			Clickon(getwebelement(xml.getlocator("//locators/SIPAddressUIDSearchButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Address Search button ");
			Thread.sleep(5000);
			
			//Select address record
			Clickon(getwebelement(xml.getlocator("//locators/SIPSelectAddressRecord")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Select Address record ");
			Thread.sleep(5000);
			
			//Click Pick address button
			Clickon(getwebelement(xml.getlocator("//locators/SIPPickAddressButtonForUID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click Pick Address button ");
			Thread.sleep(5000);
			
			
			 //IPBX Technical Contact
				
			Clickon(getwebelement(xml.getlocator("//locators/SIPPBXTechnicalContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click IP PBX Technical Contact ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/PBXTecnicalContactLookup")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click Lookup ");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPPBXTechnicalContactLookupOKButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPPBXTechnicalContactLookupOKButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click OK ");
			
			Thread.sleep(10000);
			
			savePage();  //saving IP PBX details
			
			//click on Voice Config tab to enter the IP PBX details
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigTab"))); 
			
			// Selection of IP PBX in TRUNK --
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPReClickToIPPBXLookUp"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPReClickToIPPBXLookUp")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click ");
			
			Thread.sleep(5000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPOKSelectionButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPOKSelectionButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click ");
			
			Thread.sleep(5000);
			/* ABove code for Trunk including IPPBX code*/
			
					
			//Other Tab in Voice Config 		
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SIPOtherTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Other Tab");
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigOthersTabShowFullInfo")));
			Thread.sleep(5000);
			//Select Colt DDI Ranges
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigOthersTabColtDDIRanges")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberOfSingleColtDDIs")));
			Clear(getwebelement(xml.getlocator("//locators/SIPNumberOfSingleColtDDIs")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberOfSingleColtDDIs")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Number Of single Colt DDI ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberOf10ColtDDIRanges")));
			Clear(getwebelement(xml.getlocator("//locators/SIPNumberOf10ColtDDIRanges")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberOf10ColtDDIRanges")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Number Of Colt DDI Range ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberOf100ColtDDIRanges")));
			Clear(getwebelement(xml.getlocator("//locators/SIPNumberOf100ColtDDIRanges")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberOf100ColtDDIRanges")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Number of COlt DDI Ranges");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberOf1000ColtDDIRanges")));
			Clear(getwebelement(xml.getlocator("//locators/SIPNumberOf1000ColtDDIRanges")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberOf1000ColtDDIRanges")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Number of Colt Ranges ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigOthersTabColtDDIRangesCloseWindow")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Colt DDI Ranges window closed");
			Thread.sleep(5000);
			savePage();
			
			// Number Management Tab
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementTab")));
	      	Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberRangeListPlusButton")));		
			Thread.sleep(5000);
			//***************************
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementMainNumber")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MainNumber");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementMainNumberInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberManagementMainNumberInput")),"12");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter MainNumberInput");
			Thread.sleep(5000);
			 //Number DDI
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberOfDDI")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Number of DDI");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberOfDDI")));
			SendKeys(getwebelement(xml.getlocator("//locators/MainNumberInput")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Number od DDI");
			Thread.sleep(5000);
			  //NumberPlan
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberPlan")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Number Plan");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberPlanDropDownArrow")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberPlanDropDownSelect")));
			Thread.sleep(5000);
			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Number Plan ");
			Thread.sleep(5000);
			
			//Local Area Code      	
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementLocalAreaCode")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Local Area Code");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementLocalAreaCodeInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberManagementLocalAreaCodeInput")),"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Local Area Code");
			Thread.sleep(5000);
			      	
			//Number Range Start
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStart")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Number Range Start");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStartInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStartInput")),"10");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Number Range Start");
			Thread.sleep(5000);
			
			//Number Range End
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeEnd")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Number Range End");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeEndInput")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeEndInput")),"15");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Number Range End");
			Thread.sleep(5000);
					
			//Estimated Delivery Date
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementEstimatedDeliveryDate")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Estimated Delivery Date");	
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementCalenderClickforEstimateDeliveryDate")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementCalenderClickforEstimateDeliveryDateDoneButton")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Estimated delivery Date");
			Thread.sleep(5000);
			
			//Service Party 
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementServicePartyInput")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Service Party");	
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementServicePartyInputLookup")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementServicePartyInputLookupOkButton")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Service Party");
			Thread.sleep(5000);
			
			//Address UID
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementAddressUID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Address UID");	
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementAddressUIDLookUp")));
			Thread.sleep(3000);
			//Enter Address
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupStreetName")));
			//Address Search PopUp Window
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXLookupStreetName")),"Parker");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Street Name ");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")),"United Kingdom");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")), Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Country");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")),"London");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter City");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")),"*");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Postal Code");
			Thread.sleep(5000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")),"*");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Premise Code");
			Thread.sleep(5000);
			
			//Click Address search button
			Clickon(getwebelement(xml.getlocator("//locators/SIPAddressUIDSearchButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Address Search button ");
			Thread.sleep(5000);
			
			//Select address record
			Clickon(getwebelement(xml.getlocator("//locators/SIPSelectAddressRecord")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Select Address record ");
			Thread.sleep(5000);
			
			//Click Pick address button
			Clickon(getwebelement(xml.getlocator("//locators/SIPPickAddressButtonForUID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click Pick Address button ");
			Thread.sleep(5000);
			//Address Search PopUp Window Ends
			//End of Address UID
			
			//Number Range Status
			
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStatus")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Number Range Status");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStatusDropDownArrow")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPNumberManagementNumberRangeStatusDropDownValue")));
			Thread.sleep(5000);
			savePage();
			//End of Number Range Status
			
						
		//Voice Features Tab
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeaturesTab")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeaturesTabShowFullInfo")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPCallBarring")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPCallBarringOptionDropDown")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPCallBarringValue")));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Call Barring ");
			Thread.sleep(2000);
			/*
			Clickon(getwebelement(xml.getlocator("//locators/SIPDisasterRecovery")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPDisasterRecoveryDropDown")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/SIPDisasterRecoveryDropDownValue")));
			Thread.sleep(2000); */
			Clickon(getwebelement(xml.getlocator("//locators/CloseWindowButton")));
			savePage();
			
			break;
			
		} 
		
		/* ENd Of SIP Trunking*/
		
		
		
case "Voice Line V": {
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountryDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountry"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountry")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Voice Service Country");
			Thread.sleep(5000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/EgressNumberFormatDropDownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/EgressNumberFormatDropDownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/EgreeNUmberFormat"));
			Clickon(getwebelement(xml.getlocator("//locators/EgreeNUmberFormat")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on EgreeNumberFormat");
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/TopologyDropDown"));
			Clickon(getwebelement(xml.getlocator("//locators/TopologyDropDown")));
			//Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/Topology"));
			Clickon(getwebelement(xml.getlocator("//locators/Topology")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Topology");
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/TotalDDi"));
			Clickon(getwebelement(xml.getlocator("//locators/TotalDDi")));
			SendKeys(getwebelement(xml.getlocator("//locators/TotalDDi")),"4");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TotalDDi")),Keys.ENTER);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Total DDI");
			Thread.sleep(5000);
			
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			//Pagerefresh();
			//Thread.sleep(8000);
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full infor");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SiteContactlookup")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/SiteContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Site Contact");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/ServicePartsearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/PickAccntOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Service party");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/DeliveryTeamDropDown"));
			Clickon(getwebelement(xml.getlocator("//locators/DeliveryTeamDropDown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Delivery Tram drop down");
			//Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/DeliveryTeamCountry"));
			Clickon(getwebelement(xml.getlocator("//locators/DeliveryTeamCountry")));
			SendKeys(getwebelement(xml.getlocator("//locators/DeliveryTeamCountry")),Inputdata[78].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Delivery Team Country");
			Thread.sleep(10000);
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/DeliveryTeamCity"));
			Clickon(getwebelement(xml.getlocator("//locators/DeliveryTeamCity")));
			SendKeys(getwebelement(xml.getlocator("//locators/DeliveryTeamCity")),"Vienna");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/DeliveryTeamCity")),Keys.ENTER);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Delivery Team City");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/PerformanceReporting"));
			Clickon(getwebelement(xml.getlocator("//locators/PerformanceReporting")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Performance Reporting");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/WLECInvoicing"));
			Clickon(getwebelement(xml.getlocator("//locators/WLECInvoicing")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on WLECInvoicing");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
			Thread.sleep(5000);
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on save");
				
			waitforPagetobeenable();
			Thread.sleep(30000);
			
																										
													
			break;
		}
		case "Interconnect": 
		{
			
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceServiceCountry"));
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountry")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voice service country");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[76].toString())));
		
		
		
			System.out.println("go to if loop of call admission control");
		Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		Thread.sleep(6000);
		SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), "8");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter call admission control");
		
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), Keys.TAB);
		
		Clear(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")));
		Thread.sleep(4000);
		SendKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), "4");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter number of signalling trunks");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), Keys.TAB);
		
		Clear(getwebelement(xml.getlocator("//locators/VoiceConfigurationReference")));
		Thread.sleep(4000);
		SendKeys(getwebelement(xml.getlocator("//locators/VoiceConfigurationReference")), "yes");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter voice configuration reference");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/VoiceConfigurationReference")), Keys.TAB);
	
		WaitforElementtobeclickable(xml.getlocator("//locators/TrafficDirection"));
		Clickon(getwebelement(xml.getlocator("//locators/TrafficDirection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on traffic direction");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[77].toString())));
		
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","First Codec")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")));
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value","Show More")));
		Thread.sleep(3000);
		
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Second Codec")));
		
		//System.out.println(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[2]");
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[2]"));
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Third Codec")));
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[3]"));	
		
		Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")));
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")), "G.729");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")), Keys.ENTER);
	
		Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")));
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")), "G.729");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")), Keys.ENTER);
		
		
		Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")));
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")), "G.729");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")), Keys.ENTER);
		Thread.sleep(5000);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitforPagetobeenable();
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/ShowMore"));
		Clickon(getwebelement(xml.getlocator("//locators/ShowMore")));
		
		
		WaitforElementtobeclickable(xml.getlocator("//locators/ClickToShowFullInfo"));
		Clickon(getwebelement(xml.getlocator("//locators/ClickToShowFullInfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on click to show full info");
		waitforPagetobeenable();
		
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContact"));
		Clickon(getwebelement(xml.getlocator("//locators/SiteContact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on site contact");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/OkButton"));
		Clickon(getwebelement(xml.getlocator("//locators/OkButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on ok button of site contact");
		waitforPagetobeenable();
		
		
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceParty"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceParty")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on service party");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/OkButtonService"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on ok button of service party");
		Clickon(getwebelement(xml.getlocator("//locators/OkButtonService")));
		waitforPagetobeenable();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/DelieveryTeamCountry"));
		Clickon(getwebelement(xml.getlocator("//locators/DelieveryTeamCountry")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on deleiver team country");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[80].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in deleiver team country");
		
		SendKeys(getwebelement(xml.getlocator("//locators/DeliveryTeamCity")), "Shanghai");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in deleiver team city");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/DeliveryTeamCity")), Keys.TAB);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/CrossButtonTrunk"));
		Clickon(getwebelement(xml.getlocator("//locators/CrossButtonTrunk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on cross button");
		waitforPagetobeenable();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitforPagetobeenable();
		
		javascriptexecutor(getwebelement(xml.getlocator("//locators/TrunkConfiguration")));
		SendKeys(getwebelement(xml.getlocator("//locators/TrunkSequence")), "1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in trunk sequence");
		
		SendKeys(getwebelement(xml.getlocator("//locators/TrunkName")), "Trunk1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in trunk name");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/AccessLineType"));
		Clickon(getwebelement(xml.getlocator("//locators/AccessLineType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on access line type");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[81].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in access line type");	
		
		WaitforElementtobeclickable(xml.getlocator("//locators/AccessServiceID"));
		Clickon(getwebelement(xml.getlocator("//locators/AccessServiceID")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on access service id");
		waitForpageload();
		System.out.println("page load successfully for service id in middle applet");
		WaitforElementtobeclickable(xml.getlocator("//locators/OkButtonAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/OkButtonAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on ok button");
		
		if(isElementPresent(xml.getlocator("//locators/ShowFullInfoTrunk")))
		{
			System.out.println("full trunk info is available");
		WaitforElementtobeclickable(xml.getlocator("//locators/ShowFullInfoTrunk"));
		Clickon(getwebelement(xml.getlocator("//locators/ShowFullInfoTrunk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on ShowFullInfoTrunk");
		
		}
		
		WaitforElementtobeclickable(xml.getlocator("//locators/AddButtonTrunk"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/AddButtonTrunk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on plus button");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/CustomerOriginatingIP4Address"));
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerOriginatingIP4Address")), "192.168.1.1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in customer originating IPV4 address");
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerOriginatingIP6Address")), "192.18.1.1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in customer originating IPV6 address");
		
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerTerminatingIP4Address")), "38.168.1.1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in customer terminating IPV4 address");
		
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerTerminatingIP6Address")), "38.168.1.1");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in customer terminating IPV6 address");
		
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SignallingTransportProtocol"));
		Clickon(getwebelement(xml.getlocator("//locators/SignallingTransportProtocol")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on signalling transport protocol");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[82].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in signalling transport protocol");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/VoipProtocol"));
		Clickon(getwebelement(xml.getlocator("//locators/VoipProtocol")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voip protocol");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[83].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in Voip protocol");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/EncryptionProtocol"));
		Clickon(getwebelement(xml.getlocator("//locators/EncryptionProtocol")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on encryption protocol");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[84].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in encryption protocol");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/TLSCipherSuite"));
		Clickon(getwebelement(xml.getlocator("//locators/TLSCipherSuite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on TLSCipherSuite");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[85].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in TLSCipherSuite");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/TLSCertificateSupplier"));
		Clickon(getwebelement(xml.getlocator("//locators/TLSCertificateSupplier")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on TLSCertificateSupplier");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue2").replace("value", Inputdata[86].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in TLSCertificateSupplier");
		
		Clear(getwebelement(xml.getlocator("//locators/NATRequired")));
		SendKeys(getwebelement(xml.getlocator("//locators/NATRequired")), "N");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in NATR");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/NATRequired")), Keys.TAB);
		
		javascriptexecutor(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Scroll down ");
		WaitforElementtobeclickable(xml.getlocator("//locators/InboundOutboundSplit"));
		Clear(getwebelement(xml.getlocator("//locators/InboundOutboundSplit")));
		SendKeys(getwebelement(xml.getlocator("//locators/InboundOutboundSplit")), "No");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in InboundOutboundSplit field");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InboundOutboundSplit")), Keys.TAB);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/TypeOfCAC"));
		Clickon(getwebelement(xml.getlocator("//locators/TypeOfCAC")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voip protocol");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[87].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter value in TypeOfCAC field");
					
		WaitforElementtobeclickable(xml.getlocator("//locators/CrossButtonTrunk"));
		Clickon(getwebelement(xml.getlocator("//locators/CrossButtonTrunk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on cross button");
		waitforPagetobeenable();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitforPagetobeenable();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/VoiceTab"));
		Clickon(getwebelement(xml.getlocator("//locators/VoiceTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voice feature tab");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/VoiceTab"));
		Clickon(getwebelement(xml.getlocator("//locators/VoiceTab")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voice feature tab");
		waitforPagetobeenable();
		waitForpageload();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/Resillence"));
		Clickon(getwebelement(xml.getlocator("//locators/Resillence")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on resillence dropdown");
		Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").replace("value", Inputdata[88].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on resillence value ");
		
		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitforPagetobeenable();
		waitForpageload();
		
//		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceGroupTab"));
//		Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupTab")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on service tab");
		
		Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Service Group");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Dropdown button and Select Installation and Test");
		
		waitforPagetobeenable();
		waitForpageload();
		waitandForElementDisplay(xml.getlocator("//locators/ClickOnPlusButtonService"), 5);
		WaitforElementtobeclickable(xml.getlocator("//locators/ClickOnPlusButtonService"));
		Clickon(getwebelement(xml.getlocator("//locators/ClickOnPlusButtonService")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on plus button");
		waitforPagetobeenable();
		
		waitandForElementDisplay(xml.getlocator("//locators/ServiceGroupReference"), 5);
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceGroupReference"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on service reference");
		Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on plus button");
		waitforPagetobeenable();
		
		WaitforElementtobeclickable(xml.getlocator("//locators/ClickOkButton"));
		Clickon(getwebelement(xml.getlocator("//locators/ClickOkButton")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on ok button");
				
			break;
			

		}
		case "IP Access": {

		WaitforElementtobeclickable(xml.getlocator("//locators/capacitycheckreference"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: wait on Capicityreference");
			getwebelement(xml.getlocator("//locators/capacitycheckreference")).clear();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear capicity check reference");
			Clickon(getwebelement(xml.getlocator("//locators/capacitycheckreference")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:  click on Capicityreference");
			SendKeys(getwebelement(xml.getlocator("//locators/capacitycheckreference")),Inputdata[36].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:  Input on Capicityreference");
			//Thread.sleep(4000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/Inputossplatformflag"));
			Clickon(getwebelement(xml.getlocator("//locators/Inputossplatformflag")));
			SendKeys(getwebelement(xml.getlocator("//locators/Inputossplatformflag")),Inputdata[38].toString());
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Inputossplatformflag")),Keys.TAB);
			
	//		Thread.sleep(4000);
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/RouterTypeDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/RouterTypeDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/SelectRouterTypeDropDownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/SelectRouterTypeDropDownAccess")));
			//Thread.sleep(4000);
			//getwebelement(xml.getlocator("//locators/Routertype")).clear();
			//SendKeys(getwebelement(xml.getlocator("//locators/Routertype")),Inputdata[37].toString());
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Routertype")),Keys.TAB);
			
	//		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
	//		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			
		//	Thread.sleep(6000);
			
			//Pagerefresh();
			//Thread.sleep(6000);
			
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			
			
			waitforPagetobeenable();
		
			WaitforElementtobeclickable(xml.getlocator("//locators/Layer3ResillanceDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/Layer3ResillanceDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/Layer3ResillanceSelectDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/Layer3ResillanceSelectDropdownAccess")));
			//getwebelement(xml.getlocator("//locators/Layer3ResillanceDropdownAccess")).clear();
			//SendKeys(getwebelement(xml.getlocator("//locators/Inputlayer3resillence")),Inputdata[39].toString());
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Inputlayer3resillence")),Keys.ENTER);
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Inputlayer3resillence")),Keys.TAB);
	//		Thread.sleep(4000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthSelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthSelectAccess")));
			//getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")).clear();
			//SendKeys(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")),Inputdata[40].toString());
			//EnterText(xml.getlocator("//locators/ServiceBandwidthIPAccess"),Inputdata[40].toString());
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")),Keys.ENTER);
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")),Keys.TAB);
		//	Thread.sleep(4000);
		// Pagerefresh();
		
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			
			
			waitforPagetobeenable();
			
			//Pagerefresh();
		//	Thread.sleep(6000);
			
			
			//getwebelement(xml.getlocator("//locators/RouterTechnologyAccess")).clear();
			//SendKeys(getwebelement(xml.getlocator("//locators/RouterTechnologyAccess")),Inputdata[41].toString());
			//Thread.sleep(2000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SelectSiteSearchAccess"));
			Moveon(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
			System.out.println("Enter Search ");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Site");	
			WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
			SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), Inputdata[61].toString());			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
			WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));
			SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[62].toString());			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
			SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[63].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
			SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), Inputdata[64].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
			SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), Inputdata[65].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
			Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");			
			Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");			
			Clickon(getwebelement(xml.getlocator("//locators/SelectPickBuildingAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Buiding for Site");
			Clickon(getwebelement(xml.getlocator("//locators/PickBuildingButtonAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Buiding for Site");
			Clickon(getwebelement(xml.getlocator("//locators/SelectPickSiteAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Site");
			Clickon(getwebelement(xml.getlocator("//locators/PickSiteButtonAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Site");
			Thread.sleep(10000);			

			WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			//Thread.sleep(8000);
			
			waitforPagetobeenable();
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartySearchAccess"));
			Moveon(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));		
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");
			
			//System.out.println("EnterService");
			waitandForElementDisplay((xml.getlocator("//locators/ServicePartyDropdownAccess")),8);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartyDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/ServicePartyDropdownAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/PartyNameAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Party Name");
			
			SendKeys(getwebelement(xml.getlocator("//locators/InputPartyNameAccess")), Inputdata[69].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");
			
			Clickon(getwebelement(xml.getlocator("//locators/PartyNameSearchAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
			
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/PartyNameSubmitAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");
			
			
			WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactSearchAccess"));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SiteContactSearchAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Site Contact");
			
			waitandForElementDisplay((xml.getlocator("//locators/SiteContactDropdownAccess")),8);
			WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/SiteContactDropdownAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Name Dropdown");
			
			//waitandForElementDisplay((xml.getlocator("//locators/SiteLastNameAccess")),8);
			//WaitforElementtobeclickable(xml.getlocator("//locators/SiteLastNameAccess"));
			//Thread.sleep(8000);
			//Clickon(getwebelement(xml.getlocator("//locators/SiteLastNameAccess")));
			//System.out.println("Last Name Click");
			//ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Last Name");
			
			WaitforElementtobeclickable(xml.getlocator("//locators/InputSiteNameAccess"));
			SendKeys(getwebelement(xml.getlocator("//locators/InputSiteNameAccess")), Inputdata[70].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Site Name");
			
			WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSearchAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSearchAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
			
			WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSubmitAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSubmitAccess")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");	
			
			WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			//Thread.sleep(8000);
			
			waitforPagetobeenable();
			
			
				WaitforElementtobeclickable(xml.getlocator("//locators/AccessTypeDropdownAccess"));
			//Clear(getwebelement(xml.getlocator("//locators/Accesstype")));
			//SendKeys(getwebelement(xml.getlocator("//locators/Accesstype")),Inputdata[42].toString());
			//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Accesstype"))), Keys.TAB);
				Clickon(getwebelement(xml.getlocator("//locators/AccessTypeDropdownAccess")));
				WaitforElementtobeclickable(xml.getlocator("//locators/AccessTypeSelectAccess"));
				Clickon(getwebelement(xml.getlocator("//locators/AccessTypeSelectAccess")));
			//Thread.sleep(4000);
			
			WaitforElementtobeclickable(xml.getlocator("//locators/AccesstechnologyDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/AccesstechnologyDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/AccesstechnologySelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/AccesstechnologySelectAccess")));
			//getwebelement(xml.getlocator("//locators/Accesstechnology")).clear();	
			//SendKeys(getwebelement(xml.getlocator("//locators/Accesstechnology")),Inputdata[43].toString());
			//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Accesstechnology"))), Keys.TAB);
			//Thread.sleep(4000);	
			
			//WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			//Thread.sleep(8000);
			//Pagerefresh();
		//Thread.sleep(8000);
			//getwebelement(xml.getlocator("//locators/Thirdpartyaccessprovider")).clear();
					//SendKeys(getwebelement(xml.getlocator("//locators/Thirdpartyaccessprovider")),Inputdata[0].toString());
		//SendKeys(getwebelement(xml.getlocator("//locators/relayfibre")),Inputdata[0].toString());
			//WaitforElementtobeclickable(xml.getlocator("//locators/Thirdpartyconectionreference"));
					//getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")).clear();
					//SendKeys(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")),Inputdata[44].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference"))), Keys.TAB);
					//Thread.sleep(4000);
				
					//Pagerefresh();
					//Thread.sleep(8000);
			
			
					
					//Clear(getwebelement(xml.getlocator("//locators/Thirdpartyslrtier")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Thirdpartyslrtier")),Inputdata[0].toString());

					WaitforElementtobeclickable(xml.getlocator("//locators/BuildingTypeDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/BuildingTypeDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/BuildingTypeSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/BuildingTypeSelectAccess")));
					//Clear(getwebelement(xml.getlocator("//locators/Buildingtype")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Buildingtype")),Inputdata[45].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Buildingtype"))), Keys.TAB);
					//Thread.sleep(4000);
					
					WaitforElementtobeclickable(xml.getlocator("//locators/CustomerSitePopStatusDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/CustomerSitePopStatusDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/CustomerSitePopStatusSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/CustomerSitePopStatusSelectAccess")));
					//getwebelement(xml.getlocator("//locators/CustomerSitePopStatus")).clear();	
					//SendKeys(getwebelement(xml.getlocator("//locators/CustomerSitePopStatus")),Inputdata[46].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/CustomerSitePopStatus"))), Keys.TAB);
					//Thread.sleep(4000);
					
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					
					waitforPagetobeenable();
					WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
					Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
					
					//AcceptJavaScriptMethod();
					Thread.sleep(3000);
					//Pagerefresh();
					//Thread.sleep(8000);
					//WaitforElementtobeclickable(xml.getlocator("//locators/BCPReference"));
					//getwebelement(xml.getlocator("//locators/BCPReference")).clear();	
					//SendKeys(getwebelement(xml.getlocator("//locators/BCPReference")),Inputdata[47].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/BCPReference"))), Keys.TAB);
					//Thread.sleep(4000);
					
					
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Pagerefresh();
					//Thread.sleep(8000);
					//Clear(getwebelement(xml.getlocator("//locators/relayfibre")));
					//SendKeys(getwebelement(xml.getlocator("//locators/relayfibre")),Inputdata[0].toString());
					WaitforElementtobeclickable(xml.getlocator("//locators/CabinetTypeDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/CabinetTypeDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/CabinetTypeSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/CabinetTypeSelectAccess")));
					//Clear(getwebelement(xml.getlocator("//locators/cabinettype")));
					//SendKeys(getwebelement(xml.getlocator("//locators/cabinettype")), Inputdata[48].toString());
					//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/cabinettype")), Keys.TAB);
					//Thread.sleep(4000); 
					
					WaitforElementtobeclickable(xml.getlocator("//locators/CabinetID"));		
					Clear(getwebelement(xml.getlocator("//locators/CabinetID")));
					SendKeys(getwebelement(xml.getlocator("//locators/CabinetID")), Inputdata[49].toString());
					SendkeaboardKeys((getwebelement(xml.getlocator("//locators/CabinetID"))), Keys.TAB);
					Thread.sleep(4000);
					
					WaitforElementtobeclickable(xml.getlocator("//locators/shelfid"));
					Clear(getwebelement(xml.getlocator("//locators/shelfid")));
					SendKeys(getwebelement(xml.getlocator("//locators/shelfid")), Inputdata[50].toString());
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/shelfid")), Keys.TAB);
					
					//waitandForElementDisplay((xml.getlocator("//locators/ClickheretoSaveAccess")),8);
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Pagerefresh();
					//Thread.sleep(8000);
					
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					
					waitforPagetobeenable();
				
					WaitforElementtobeclickable(xml.getlocator("//locators/Slotid"));		
					Clear(getwebelement(xml.getlocator("//locators/Slotid")));
					SendKeys(getwebelement(xml.getlocator("//locators/Slotid")), Inputdata[51].toString());
					SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Slotid"))), Keys.TAB);
					//Thread.sleep(4000);
					
					WaitforElementtobeclickable(xml.getlocator("//locators/Physicalportid"));
					Clear(getwebelement(xml.getlocator("//locators/Physicalportid")));
					SendKeys(getwebelement(xml.getlocator("//locators/Physicalportid")), Inputdata[52].toString());
					SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Physicalportid"))), Keys.TAB);
					//Thread.sleep(4000);
					
					
					WaitforElementtobeclickable(xml.getlocator("//locators/PresentationInterfaceDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/PresentationInterfaceDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/PresentationInterfaceSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/PresentationInterfaceSelectAccess")));
					//Clear(getwebelement(xml.getlocator("//locators/PhysicalInterface")));
					//SendKeys(getwebelement(xml.getlocator("//locators/PhysicalInterface")), Inputdata[53].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/PhysicalInterface"))), Keys.TAB);
					//Thread.sleep(4000);
					
					WaitforElementtobeclickable(xml.getlocator("//locators/ConnectorTypeDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/ConnectorTypeDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/ConnectorTypeSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/ConnectorTypeSelectAccess")));
					//Clear(getwebelement(xml.getlocator("//locators/Connectortype")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Connectortype")), Inputdata[54].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Connectortype"))), Keys.TAB);
					//Thread.sleep(4000);
					
					//Pagerefresh();
					//Thread.sleep(8000);
					//WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Thread.sleep(8000);
					//Pagerefresh();
					//Thread.sleep(8000);
					WaitforElementtobeclickable(xml.getlocator("//locators/FibreTypeDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/FibreTypeDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/FibreTypeSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/FibreTypeSelectAccess")));
					//Clickon(getwebelement(xml.getlocator("//locators/Fibretype")));
					//Clear(getwebelement(xml.getlocator("//locators/Fibretype")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Fibretype")), Inputdata[55].toString());
					///SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Fibretype"))), Keys.TAB);
					//Thread.sleep(4000);
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Pagerefresh();
					//Thread.sleep(8000);
					waitforPagetobeenable();
					//Clear(getwebelement(xml.getlocator("//locators/Portrole")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Portrole")), Inputdata[0].toString());
					
					
					

				//	Clear(getwebelement(xml.getlocator("//locators/Dualcustomerpowersource")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Dualcustomerpowersource")), Inputdata[0].toString());
					//Clear(getwebelement(xml.getlocator("//locators/Diversitytype")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Diversitytype")), Inputdata[0].toString());
					//Clear(getwebelement(xml.getlocator("//locators/Customerdedicatedaccessring")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Customerdedicatedaccessring")),Inputdata[0].toString());
					
					WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/InstallTimeDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/InstallTimeSelectAccess")));
					//Clear(getwebelement(xml.getlocator("//locators/installtime")));
					//SendKeys(getwebelement(xml.getlocator("//locators/installtime")), Inputdata[56].toString());
					//SendkeaboardKeys((getwebelement(xml.getlocator("//locators/installtime"))), Keys.TAB);
					//Thread.sleep(4000);
			
					WaitforElementtobeclickable(xml.getlocator("//locators/Accesstimewindow"));
					Clear(getwebelement(xml.getlocator("//locators/Accesstimewindow")));
					SendKeys(getwebelement(xml.getlocator("//locators/Accesstimewindow")), Inputdata[57].toString());
					SendkeaboardKeys((getwebelement(xml.getlocator("//locators/Accesstimewindow"))), Keys.TAB);
					//Thread.sleep(4000);
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Pagerefresh();
					//Thread.sleep(8000);
					waitforPagetobeenable();
				
					//Clear(getwebelement(xml.getlocator("//locators/Coltcpeid")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Coltcpeid")), Inputdata[0].toString());
					//Clear(getwebelement(xml.getlocator("//locators/Routerspecification")));
					//SendKeys(getwebelement(xml.getlocator("//locators/Routerspecification")), Inputdata[0].toString());

				
					


					WaitforElementtobeclickable(xml.getlocator("//locators/RouterCountryAccess"));
					Clear(getwebelement(xml.getlocator("//locators/RouterCountryAccess")));
					SendKeys(getwebelement(xml.getlocator("//locators/RouterCountryAccess")), Inputdata[58].toString());
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/RouterCountryAccess")), Keys.TAB);
					Thread.sleep(10000);
					WaitforElementtobeclickable(xml.getlocator("//locators/routermodel"));
					Clear(getwebelement(xml.getlocator("//locators/routermodel")));
					SendKeys(getwebelement(xml.getlocator("//locators/routermodel")), Inputdata[59].toString());
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/routermodel")), Keys.TAB);
					
//					WaitforElementtobeclickable(xml.getlocator("//locators/RouterSiteNameAccess"));
//					Clear(getwebelement(xml.getlocator("//locators/RouterSiteNameAccess")));
//					SendKeys(getwebelement(xml.getlocator("//locators/RouterSiteNameAccess")), Inputdata[60].toString());
//					Thread.sleep(10000);
//					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/RouterSiteNameAccess")), Keys.TAB);
//					Thread.sleep(4000);
					
					
					WaitforElementtobeclickable(xml.getlocator("//locators/RouterSiteNameDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/RouterSiteNameDropdownAccess")));
					Thread.sleep(5000);
					WaitforElementtobeclickable(xml.getlocator("//locators/RouterSiteNameSelectAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/RouterSiteNameSelectAccess")));
					Thread.sleep(5000);
					
					
					
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					//Pagerefresh();
					//Thread.sleep(8000);
					waitforPagetobeenable();
					
					 

					Clickon(getwebelement(xml.getlocator("//locators/ClickShowFullInfoAccess")));
					Thread.sleep(5000);
					WaitforElementtobeclickable((xml.getlocator("//locators/IPAdressingFormatDropdownAccess")));
					Clickon(getwebelement(xml.getlocator("//locators/IPAdressingFormatDropdownAccess")));
					WaitforElementtobeclickable((xml.getlocator("//locators/IPAdressingFormatSelectAccess")));
					Clickon(getwebelement(xml.getlocator("//locators/IPAdressingFormatSelectAccess")));
					Thread.sleep(5000);
					//Clear(getwebelement(xml.getlocator("//locators/IPAdressingFormatAccess")));
					//SendKeys(getwebelement(xml.getlocator("//locators/IPAdressingFormatAccess")), Inputdata[66].toString());
					//Thread.sleep(8000);
	
					//Clear(getwebelement(xml.getlocator("//locators/IPV4AdressingTypeAccess")));
					//SendKeys(getwebelement(xml.getlocator("//locators/IPV4AdressingTypeAccess")), Inputdata[67].toString());
					//Thread.sleep(8000);
					WaitforElementtobeclickable((xml.getlocator("//locators/IPV4AdressingTypeDropdownAccess")));
					Clickon(getwebelement(xml.getlocator("//locators/IPV4AdressingTypeDropdownAccess")));
					WaitforElementtobeclickable((xml.getlocator("//locators/IPV4AdressingTypeSelectAccess")));
					Clickon(getwebelement(xml.getlocator("//locators/IPV4AdressingTypeSelectAccess")));
					Thread.sleep(5000);
					//Clear(getwebelement(xml.getlocator("//locators/NumberIPV4AdressAccess")));
					//SendKeys(getwebelement(xml.getlocator("//locators/NumberIPV4AdressAccess")), Inputdata[68].toString());		
					//Thread.sleep(8000);
					//getwebelement(xml.getlocator("//locators/IPaddressingtype")).clear();
					//SendKeys(getwebelement(xml.getlocator("//locators/IPaddressingtype")), Inputdata[0].toString());
					Clickon(getwebelement(xml.getlocator("//locators/Crossbuttonforipaccess")));
					Thread.sleep(6000);
					//Clickon(getwebelement(xml.getlocator("//locators/SaveButtonClick")));
					//Thread.sleep(8000);
					
					WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
					//Thread.sleep(8000);
					
					waitforPagetobeenable();

					//accepTaLLERT(GetText(getwebelement(xml.getlocator("Locator of the message area"))),getwebelement(xml.getlocator("locator of button")));
					
					
					//Clickon(getwebelement(xml.getlocator("//locators/SearchAddressSiteB")));
					
					WaitforElementtobeclickable(xml.getlocator("//locators/RouterTechnologyDropdownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/RouterTechnologyDropdownAccess")));
					WaitforElementtobeclickable(xml.getlocator("//locators/SelectRouterTechnologyDropDownAccess"));
					Clickon(getwebelement(xml.getlocator("//locators/SelectRouterTechnologyDropDownAccess")));
		
					WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
					//WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
					Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
					
					waitforPagetobeenable();

					WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
					Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
					Thread.sleep(25000);
					
					savePage();
					waitforPagetobeenable();
					Thread.sleep(8000);
					

//					Circuitreferencenumber.set(Gettext(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess']"))));
//							ExtentTestManager.getTest().log(LogStatus.PASS,
//									" Step: Generated Service Order Reference No: " + Circuitreferencenumber.get());



			
			break;
		}
		case "IP Domain": {
			
			System.out.println("Enter Middle Applet");
			Thread.sleep(2000);
			//Moveon(getwebelement("//a[text()='Click to Show Full info']"));
			//Moveon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			System.out.println("Move Performed");
			
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			Thread.sleep(3000);
			
			SendKeys(getwebelement(xml.getlocator("//locators/Domainname")), Inputdata[16].toString());
			Clickon(getwebelement(xml.getlocator("//locators/Domainordertype")));
			Thread.sleep(4000);
			Clickon(getwebelement(xml.getlocator("//locators/DomainordertypeData")));
			//WaitforElementtobeclickable(xml.getlocator("//locators/DNStype"));
			Clickon(getwebelement(xml.getlocator("//locators/DNStype")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/DNSTypeInput")));
			//getwebelement(xml.getlocator("//locators/DNStypeinput")).clear();
			//Thread.sleep(3000);
			//SendKeys(getwebelement(xml.getlocator("//locators/DNStypeinput")), Inputdata[17].toString());
			//Clickon(getwebelement(xml.getlocator("//locators/DNStypeinput")));
			Thread.sleep(6000);
			//savePage();
			
			Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
			Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/SaveButtonClick")));
			Thread.sleep(10000);
			 
			System.out.println("Middle Complete");
			break;
		}
		
		case "IP Guardian": {
		
			System.out.println("Enter Gurdian Middle Applet");
			
			getwebelement(xml.getlocator("//locators/AlertingNotification")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/AlertingNotification")), Inputdata[27].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter AlertingNotification");
			
			getwebelement(xml.getlocator("//locators/Automigration")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/Automigration")),Inputdata[28].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter Automigration");
			Thread.sleep(2000);
			//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Automigration")), Keys.TAB);
			
			getwebelement(xml.getlocator("//locators/Customerdnsresolve")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/Customerdnsresolve")), Inputdata[29].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter Customer dns resolve");
		
			getwebelement(xml.getlocator("//locators/IpguardianVariant")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/IpguardianVariant")), Inputdata[30].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter IP guardiant variant");

			
			getwebelement(xml.getlocator("//locators/Colttechnicaltestcalls")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/Colttechnicaltestcalls")), Inputdata[31].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter Enter Colt technical cells");
			Thread.sleep(2000);

			getwebelement(xml.getlocator("//locators/servicebandwidth")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/servicebandwidth")), Inputdata[32].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter service bandwidth");

			SendKeys(getwebelement(xml.getlocator("//locators/Testwindowipaccess")), Inputdata[33].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter Test Window");
			

			

			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			Thread.sleep(8000);
			
			Moveon(getwebelement(xml.getlocator("//locators/CircuitipaddressClick")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/CircuitipaddressClick")));
			//System.out.println("Click Full");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:click on circuit ip address");
			Thread.sleep(3000);
 
			Clickon(getwebelement(xml.getlocator("//locators/CircuitIPAddressInput")));
			getwebelement(xml.getlocator("//locators/CircuitIPAddressInput")).clear();
			SendKeys(getwebelement(xml.getlocator("//locators/CircuitIPAddressInput")), Inputdata[34].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:click on circuit ip address");

			SendKeys(getwebelement(xml.getlocator("//locators/IpRange")), Inputdata[35].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Input IP Range");
			
			Clickon(getwebelement(xml.getlocator("//locators/CrossButtonGurdian")));
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			Thread.sleep(10000);

			break;
		}
		
		case "Number Hosting": {
			
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Voice Service Country")));
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value","France")));
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Call Admission Control (Number of Channels)")));
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Call Admission Control (Number of Channels)")), "2");
			Thread.sleep(2000);
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Number of Signalling Trunks")));
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Number of Signalling Trunks")), "1");
			Thread.sleep(2000);
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Voice Configuration Reference")));
			Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Voice Configuration Reference")), "12");
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Traffic Direction")));
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value","Incoming")));
			Thread.sleep(2000);
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","First Codec")));
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")));
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value","Show More")));
			Thread.sleep(3000);
			
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Second Codec")));
			
			//System.out.println(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[2]");
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[2]"));
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Third Codec")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[3]"));
			
			
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")));
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")), "G.729");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fourth Codec")), Keys.ENTER);
		
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")));
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")), "G.729");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Fifth Codec")), Keys.ENTER);
			
			
			Clear(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")));
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")), "G.729");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Sixth Codec")), Keys.ENTER);
			Thread.sleep(2000);
			
			
			
//			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Fourth Codec")));
//			Thread.sleep(2000);
//			
//			System.out.println(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[4]");
//			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[3]"));
//			
//			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Fifth Codec")));
//			Thread.sleep(2000);
//			System.out.println(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[5]");
//			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[3]"));
//			
//			Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Sixth Codec")));
//			Thread.sleep(2000);
//			System.out.println(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[6]");
//			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.722.1")+"[3]"));
//			
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			waitforPagetobeenable();
			
			Clickon(getwebelement(xml.getlocator("//locators/ClickShowFullInfoAccess")));
			Thread.sleep(3000);
			
			Clickon(getwebelement(xml.getlocator("//locators/SearchInput").replace("Value","Service Party")));
			waitforPagetobeenable();
			
			Clickon(getwebelement(xml.getlocator("//locators/PopUpDropdown")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Dropdown");
			
			Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value","Party Name")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Party Name");
			
			SendKeys(getwebelement(xml.getlocator("//locators/PopUpInput")),"Colt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");
			
			Clickon(getwebelement(xml.getlocator("//locators/PopUpSearch")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
			waitforPagetobeenable();
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/PopUpSubmit")));			
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");
			waitforPagetobeenable();
			
			
			
		Clickon(getwebelement(xml.getlocator("//locators/SearchInput").replace("Value","Site Contact")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/PopUpDropdown")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Dropdown");
		
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value","Last Name")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select First  Name");
		
		SendKeys(getwebelement(xml.getlocator("//locators/PopUpInput")),"Chambial");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Last Name");
		
//		Clickon(getwebelement(xml.getlocator("//locators/PopUpSearch")));			
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
//		waitforPagetobeenable();
	
		Clickon(getwebelement(xml.getlocator("//locators/PopUpSubmit")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");
		waitforPagetobeenable();
		                                             
			
		//System.out.println(xml.getlocator("//locators/ClickDropdown").replace("Value","Delivery Team Country"));
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Delivery Team Country")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "France")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Delivery Team City")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Paris")));
		
		Clickon(getwebelement(xml.getlocator("//locators/CrossforHosting")));
		waitforPagetobeenable();
		
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		waitforPagetobeenable();
			
		
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Trunk Sequence")), "1");		
			
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Trunk Name")), "ABC");
			
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","External Access Service Reference")), "12");
			
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Customer Originating IPv4 Address Range")), "24.12.5");
			
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Customer Originating IPv6 Address Range")), "21.25.69");
			
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value", "Access Line Type")));
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Colt Access")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/SearchInput").replace("Value", "Access Service ID")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/PopUpDropdown")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Service Dropdown");
		
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Status")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Status");
		
		SendKeys(getwebelement(xml.getlocator("//locators/PopUpInput")),"Completed");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Status");
		
		Clickon(getwebelement(xml.getlocator("//locators/PopUpSearch")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
		waitforPagetobeenable();
	
		Clickon(getwebelement(xml.getlocator("//locators/PopUpSubmit")));			
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Show full info")));
		Thread.sleep(3000);
		
//		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
//		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickPlus")));
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Customer Terminating IPv4 Address")), "22.65");
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Customer Terminating IPv6 Address")), "29.62");
				
				
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "Signalling Transport Protocol")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "TCP")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "VoIP Protocol")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "SIP")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "TLS Certificate Supplier")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", " Colt")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "TLS Cipher Suite")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "TLS_RSA_WITH_AES_128_CBC_SHA (Priority 1)")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "Encryption Type")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "TLS on, SRTP off")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "NAT Required?")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Y")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "Inbound Outbound Split")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Yes")));
		
		Clickon(getwebelement(xml.getlocator("//locators/NewPageDropdown").replace("Value", "Type of CAC")));
		Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Both ways CAC with individual Trunk CAC")));
		Thread.sleep(2000);
		
		Clickon(getwebelement(xml.getlocator("//locators/CrossButtonTrunk")));
		
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/OtherTabClick")));
		waitforPagetobeenable();
		
		
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Internal Routing Prefix")), "45");
		
		SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value","Reseller Profile")), "Ab");
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Service Group")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupPlus")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupSearch")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/PopUpSubmit")));
		waitforPagetobeenable();
		
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Voice Features")));
		waitforPagetobeenable();
		
		
	
		
		
		
		
		
		
		
		
			
//			for(int i=0; i<2;i++) {
//				Clickon(getwebelement(xml.getlocator("//locators/BCNFilling").replace("value","'+i+'" )));
//			}
			
			break;
		}

		default:
			System.out.println("Above product is not exist in current list");
			break;
		}
	}

	public void EnterDateInFooter(Object Inputdata[]) throws Exception {
		
		//////// ORDER   DATE
			//System.out.println("Enter Domain Footer");
			Moveon(getwebelement(xml.getlocator("//locators/OrderDates")));
		//	System.out.println("Moved Mouse");
		Clickon(getwebelement(xml.getlocator("//locators/OrderDates")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Dates");
		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable((xml.getlocator("//locators/CustomerRequestedDate")));
		Clear(getwebelement(xml.getlocator("//locators/CustomerRequestedDate")));
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerRequestedDate")), Inputdata[71].toString());
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSignedDate"));
		SendKeys(getwebelement(xml.getlocator("//locators/OrderSignedDate")), Inputdata[18].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Signed Date");
		WaitforElementtobeclickable(xml.getlocator("//locators/ColtActualDate"));
		SendKeys(getwebelement(xml.getlocator("//locators/ColtActualDate")), Inputdata[19].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Colt Actual Date");
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderReceivedDate"));
		SendKeys(getwebelement(xml.getlocator("//locators/OrderReceivedDate")), Inputdata[20].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date");//savePage();
		
		
//
//		System.out.println("Order Date Complete");
//		Thread.sleep(3000);
	}
		///////////BILLING DATE


	
	public void EnterBillingDateInFooter(Object Inputdata[]) throws Exception {


		Clickon(getwebelement(xml.getlocator("//locators/Billing")));
		waitforPagetobeenable();
	//	System.out.println("BILLING TAB");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Billing");
		
		Thread.sleep(3000);
		if(isElementPresent(xml.getlocator("//locators/ClickheretoSaveAccess")))
		{
		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
		waitforPagetobeenable();
		}
		
		Clear(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")));
		SendKeys(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")), Inputdata[22].toString());
		//System.out.println("contract renewal flag");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Renewal Flag");
		//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")), Keys.TAB);
		Thread.sleep(4000);
		
		
		Clear(getwebelement(xml.getlocator("//locators/ContractTerm")));
		SendKeys(getwebelement(xml.getlocator("//locators/ContractTerm")), Inputdata[21].toString());
		Clickon(getwebelement(xml.getlocator("//locators/SelectContractTerm")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Term");
		Thread.sleep(8000);
		
		WaitforElementtobeclickable((xml.getlocator("//locators/BillingStartDateAccess")));
		Clear(getwebelement(xml.getlocator("//locators/BillingStartDateAccess")));
		SendKeys(getwebelement(xml.getlocator("//locators/BillingStartDateAccess")), CurrentDate());
		
		WaitforElementtobeclickable((xml.getlocator("//locators/POStartDateAccess")));
		Clear(getwebelement(xml.getlocator("//locators/POStartDateAccess")));
		SendKeys(getwebelement(xml.getlocator("//locators/POStartDateAccess")), CurrentDate());
		
		WaitforElementtobeclickable((xml.getlocator("//locators/POEndDateAccess")));
		Clear(getwebelement(xml.getlocator("//locators/POEndDateAccess")));
		SendKeys(getwebelement(xml.getlocator("//locators/POEndDateAccess")), CurrentDate());
		
		Thread.sleep(5000);
		
		
		
		//System.out.println("Billing Date Done!");
	}

		///////////// SERVICE CHARGE
	public void EnterServiceChargeInFooter(Object[] Inputdata,String Amount) throws Exception {
		WaitforElementtobeclickable(xml.getlocator("//locators/ExpandAllButton")); 
		Clickon(getwebelement(xml.getlocator("//locators/ExpandAllButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Expand All Button");
		Thread.sleep(3000);
		while(!Getattribute(getwebelement(xml.getlocator("//locators/BillingLastRow")),"class").contains("highlight"))
		{

		 int RowCount = getwebelementscount((xml.getlocator("//locators/BillingRow")))-1;
		 System.out.println(RowCount);
		 
		 for(int i=1;i<=RowCount;i++)
		 {

		 Clickon(getwebelement(xml.getlocator("//locators/BillingRowAmount").replace("Value", String.valueOf(i))));
		 waitforPagetobeenable();
		 if(!Getattribute(getwebelement(xml.getlocator("//locators/BillingRowAmount").replace("Value", String.valueOf(i))),"class").contains("disabled"))
		 {
		 
		 Clear(getwebelement(xml.getlocator("//locators/BillingRowAmountInput").replace("Value", String.valueOf(i))));
		 SendKeys(getwebelement(xml.getlocator("//locators/BillingRowAmountInput").replace("Value", String.valueOf(i))), Amount);
		 waitforPagetobeenable();
		 }
		 else
		 {
		 System.out.println("Not Required to fill");
		 }
		 
		 Clickon(getwebelement(xml.getlocator("//locators/BillingRowBCN").replace("Value", String.valueOf(i))));
		 waitforPagetobeenable();
		 if(!Getattribute(getwebelement(xml.getlocator("//locators/BillingRowBCN").replace("Value", String.valueOf(i))),"class").contains("disabled"))
		 {

		Clickon(getwebelement(xml.getlocator("//locators/BCNSearchClick")));
		       waitforPagetobeenable();
		SendKeys(getwebelement(xml.getlocator("//locators/BCNInstallationChargeNRCInput")),Inputdata[25].toString());
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/BCNNRCSearch")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		//Clickon(getwebelement(xml.getlocator("//locators/BCNNRCSubmit")));//Should add in BSW enviroment
		waitforPagetobeenable();
		 }
		 else
		 {
		 System.out.println("Not Required to fill");
		 }
		 }
		 Clickon(getwebelement(xml.getlocator("//locators/FirstLineitem")));
		 Thread.sleep(5000);
		 Clickon(getwebelement(xml.getlocator("//locators/ClickNextPage")));
		 waitforPagetobeenable();
		 Thread.sleep(5000);
		}


		}


		

		
	
	public void EnterInstallationChargeInFooter(Object Inputdata[]) throws Exception {	
		if(!Inputdata[8].toString().equals("Cloud UC") ) {
		Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Installation and Test");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Dropdown button and Select Installation and Test");
			
			Thread.sleep(5000);
			Clear(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")));
			SendKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), Keys.TAB);
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SaveOrderContinue")));
			waitforPagetobeenable();
			Thread.sleep(3000);
		}
	}	
		
	

	public void CommercialValidation(Object[] Inputdata) throws Exception {

		
			getwebelement(xml.getlocator("//locators/OrderStatus")).clear();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Order Status");
			SendKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), "Commercial Validation");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Commercial Validation");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.ENTER);
			//Thread.sleep(5000);
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.TAB);
			waitforPagetobeenable();
			Thread.sleep(2000);
			waitforPagetobeenable();
			
	}
	 public void SelectAttachmentTab(Object[] Inputdata) throws IOException, InterruptedException, DocumentException
	    {
	    if(Inputdata[8].toString().equals("IP Voice Solutions") || Inputdata[8].toString().equals("Voice Line V") )	{
		 Select(getwebelement(xmlIP.getlocator("//locators/tabDropdown")),"Attachments");
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Selected");
	    	Clickon(getwebelement(xmlIP.getlocator("//locators/AttachmentTabSelection")));
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Clicked");
	    	Thread.sleep(10000);
	    }
	    }
	 public void UploadDocument(Object[] Inputdata) throws Exception
	    {
	    if(Inputdata[8].toString().equals("IP Voice Solutions"))	{
	    	UploadSOWTypeDocument(Inputdata, "SOW");
	    }
	    else if(Inputdata[8].toString().equals("Voice Line V"))
	    {
	    	UploadSOWTypeDocument(Inputdata, "Call Barring Form");
	    	UploadSOWTypeDocument(Inputdata, "Disaster Recovery Documents");
	    }
	    }
	    public void UploadSOWTypeDocument(Object[] Inputdata, String Filetype) throws Exception
	    {
	    	
	    	System.out.println(xmlIP.getlocator("//locators//FileUpload"));
	    	//WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
	    	uploadafile(xmlIP.getlocator("//locators//FileUpload"),"test.txt");
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
	    	Thread.sleep(10000);
	    	Clickon(getwebelement(xmlIP.getlocator("//locators/DocumnetTypeOther")));
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
	    	Clickon(getwebelement(xmlIP.getlocator("//locators/DownArrow")));
	    	Clickon(getwebelement(xmlIP.getlocator("//locators/DoucmentTypeSelection").replace("Filetype", Filetype)));
	    	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: SOW File Type Selected");
	    	
	    }
	public void TechnicalValidation(Object[] Inputdata) throws Exception {
		//System.out.println("Aman,");
		//Thread.sleep(10000);
		//waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Technical Validation");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectTechnicalValidation")));
			waitforPagetobeenable();
	         // savePage();
	          Thread.sleep(2000);
	          waitforPagetobeenable();
	                savePage();
		         Thread.sleep(5000);
		         if(isElementPresent((xml.getlocator("//locators/AlertAccept"))))
		 		{
		 		WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
		 		Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		 		}   
	}
	
	
			
	public void DeliveryValidation(Object[] Inputdata) throws Exception {
	
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectDeliveryValidation")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Delivery");
			waitforPagetobeenable();
			Thread.sleep(5000);
			
			
			
	     
			
//			SendKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), "Not Required");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in primary test method");
//			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), Keys.TAB);
//			Thread.sleep(3000);
//			savePage();

			
	}
	
public void TriggerPopup(Object[] Inputdata) throws Exception {
	//waitforPagetobeenable();
		if((isProductValid(Inputdata[8].toString())))
      {
		//System.out.println("DHONI");	
		
		Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButtonCheckbox")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click On Check Box");
       Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trigger TR Button");
		Thread.sleep(10000); 
		 javascriptexecutor(getwebelement(xml.getlocator("//locators/InstallationDropdown")));
			Select(getwebelement(xml.getlocator("//locators/InstallationDropdown")), "Installation and Test");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Click on Installation Dropdown button and Select Installation and Test");

			SendKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), "Not Required");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in primary test method");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), Keys.TAB);
			Thread.sleep(5000);
	}
	
	}

public void clickOnManualValidationA() throws Exception
{
	if(isDisplayed(xml.getlocator("//locators/manualvalidation1")))
	{
		
	//Waitforvisibilityofelement(xml.getlocator("//locators/manualvalidation"));
	WaitforElementtobeclickable(xml.getlocator("//locators/manualvalidation1"));
Clickon(getwebelement(xml.getlocator("//locators/manualvalidation1")));
ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Manual Validation Button");
waitforPagetobeenable();

Thread.sleep(5000);
	}	
}
public void clickOnManualValidationB() throws Exception
{
	if(isDisplayed(xml.getlocator("//locators/manualvalidation2")))
	{
		
	//Waitforvisibilityofelement(xml.getlocator("//locators/manualvalidation"));
	WaitforElementtobeclickable(xml.getlocator("//locators/manualvalidation2"));
	Clickon(getwebelement(xml.getlocator("//locators/manualvalidation2")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Manual Validation Button");
	waitforPagetobeenable();

Thread.sleep(5000);
	}	
}
	
	public void CompletedValidation(Object[] Inputdata) throws Exception {
	  waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();
			
			Thread.sleep(5000);
			//savePage();
			//Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
			waitforPagetobeenable();
			Thread.sleep(5000);
			savePage();
			Thread.sleep(10000);
			savePage();
			Thread.sleep(10000);
			if(isElementPresent(xml.getlocator("//locators/AlertAccept")))
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}
			//Pagerefresh();
			//Thread.sleep(5000);
		
			System.out.println("Order complete");
			
	

		}

	public void ServiceTab(Object[] Inputdata) throws Exception {
		Thread.sleep(10000);
				try {
					Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
				} catch (Exception e) {
					try {
						safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
					} catch (Exception e1) {

						e1.printStackTrace();
					}
				}
				waitforPagetobeenable();
				waitForpageload();
				waitandForElementDisplay(xml.getlocator("//locators/InputServiceOrder"), 5);
				//String x= ServiceOrder.get();
				//System.out.println(x);
				//String string = "004-034556";
				//String[] parts = x.split("/");
				//String part1 = parts[0]; 
				//String part2 = parts[1]; 
				//System.out.println(part1);
				//System.out.println(part2);
				Clickon(getwebelement(xml.getlocator("//locators/InputServiceOrder")));
				SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")),"871572919/190904-0162");
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
				waitforPagetobeenable();
				Thread.sleep(6000);
				Clickon(getwebelement(xml.getlocator("//locators/ModifyButtonClick")));
				waitforPagetobeenable();
				Thread.sleep(3000);
				SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), Inputdata[1].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity No");
				SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")),CurrentDate());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");
				Thread.sleep(3000);
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
				waitforPagetobeenable();
				WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
				Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
				//System.out.println("Enter New Order");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");
				Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
				waitforPagetobeenable();
				Thread.sleep(3000);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
				//SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")),"Upgrade Bandwith");
				SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")),"BCN Change");
				SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);

				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");

				
				Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
				waitforPagetobeenable();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
				Thread.sleep(2000);
				
//				Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","Voice Service Country")));
//				Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value","Belgium")));
//				
//				Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value","First Codec")));
//				Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "G.729")));
//				Thread.sleep(2000);
				//WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
				//Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
				
				WaitforElementtobeclickable(xml.getlocator("//locators/MaintenancePartySearch"));
				waitforPagetobeenable();
				Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartySearch")));
				Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyPopupDropdown")));
				Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Party Name")));
				SendKeys(getwebelement(xml.getlocator("//locators/InputAccountStatus")), "Colt");
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/AccountStatusSearch")));
				Thread.sleep(2000);
				Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSearch")));
				waitforPagetobeenable();
				Thread.sleep(4000);
				waitforPagetobeenable();
				Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSubmit")));
				Thread.sleep(3000);
				
				savePage();
				waitforPagetobeenable();
	}
	public void Check1(Object[] Inputdata) throws Exception {
		ServiceOrder.get();
		System.out.println(ServiceOrder.get());
		String x= ServiceOrder.get();
		//String string = "004-034556";
		String[] parts = x.split("/");
		String part1 = parts[0]; 
		String part2 = parts[1]; 
		System.out.println(part1);
		System.out.println(part2);
		Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Customer Orders")));
		waitforPagetobeenable();
		SendKeys(getwebelement(xml.getlocator("//locators/CustomerOrderInput")), part2);
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/CustomerOrderSearch")));
		
	}
	public void statusReason(Object[] InputData) throws Exception
	{
	Thread.sleep(4000);
	//Waitforvisibilityofelement(xml.getlocator("//locators/StatusReasonDropdown"));
	WaitforElementtobeclickable(xml.getlocator("//locators/StatusReasonDropdown"));
	Clickon(getwebelement(xml.getlocator("//locators/StatusReasonDropdown")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on status reason dropdown");

	Clickon(getwebelement(xml.getlocator("//locators/StatusReasonValue").replace("value","Abandoned, Order replaced")));
	System.out.println(InputData[90].toString());
	//SendKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), InputData[75].toString());
	System.out.println("status reason");
	//ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on abandonked status popup");
	Thread.sleep(4000);
	//SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), Keys.ENTER);
//		Thread.sleep(4000);
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), Keys.ENTER);
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), Keys.TAB);
	System.out.println("enter tab");

	savePage();
	}
	public void AbandonedOrder(Object[] Inputdata) throws Exception {

	WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
	Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clcik on Order Status dropdown");


	Moveon(getwebelement(xml.getlocator("//locators/OrderStatusAbandoned")));
	waitforPagetobeenable();
	System.out.println("1st page enable of order status abandon");
	waitForpageload();
	System.out.println("page load successfully of abandon");
//		WaitforElementtobeclickable(xml.getlocator("//locators/AbandonYes"));
//		Clickon(getwebelement(xml.getlocator("//locators/AbandonYes")));

	savePage();
	waitForpageload();
	waitandForElementDisplay(xml.getlocator("//locators/ServiceTab"), 5);


	}
	public void verifyOrderAbandoned() throws Exception
	{

	WaitforElementtobeclickable(xml.getlocator("//locators/ServicTab"));
	safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceTab")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on service tab");
	Thread.sleep(3000);
	WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderSearch"));
	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderSearch")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on service order search field");

	SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderSearch")), ServiceOrder.get());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in servie field");
	WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOderArrow"));
	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on service order arrow");

	String expected = Gettext(getwebelement(xml.getlocator("//locators/VerifyAbandoned")));
	System.out.println(expected);
	String actual="Abandoned";
	    Assert.assertEquals(expected, actual);
	    Reporter.log("Test has been passed", true);


	}
	




	
	}


	