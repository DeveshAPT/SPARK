package ScriptHelper;

import java.util.Map.Entry;
import org.openqa.selenium.By;
import Driver.PropertyReader;
import org.openqa.selenium.support.ui.Select;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.awt.Robot;
import ScriptHelper.ServiceOrders;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.dom4j.DocumentException;
//import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.Reporter;
import java.awt.AWTException;
import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.ibm.icu.impl.ICUService.Key;
import com.relevantcodes.extentreports.LogStatus;

import Driver.DriverHelper;
import Driver.Log;
import Driver.XMLReader;
import Reporter.ExtentTestManager;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.server.handler.GetElementEnabled;
import org.openqa.selenium.remote.server.handler.GetElementText;
import org.openqa.selenium.security.UserAndPassword;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class NewOrderOnnetHelper extends DriverHelper {

	String timeStamp = TimeStamp();
	String timeStamp1 = TimeStamp();
	Random rnd = new Random();

	public NewOrderOnnetHelper(WebDriver parentdriver) {
		super(parentdriver);

	}

	WebElement el;

	XMLReader xml = new XMLReader("src\\Locators\\SiebelOrder.xml");
	XMLReader xml2 = new XMLReader("src\\Locators\\CloudUc.xml");
	XMLReader xmlIP = new XMLReader("src\\Locators\\IPVoiceSolution.xml");
	XMLReader xml3 = new XMLReader("src\\Locators\\SiebelOrderEtherline.xml"); // added by shivananda
	XMLReader xmlHns = new XMLReader("src\\Locators\\EtherNetHubSpoke.xml");
	//static ServiceOrders orders=new ServiceOrders();

	public void accountTabDetails(Object[] InputData) throws Exception {

		Thread.sleep(8000);
		System.out.println("Page to be refresed");
		do {
			Pagerefresh();
			System.out.println("Page to be refresed");
			Thread.sleep(20000);
		} while (!isElementPresent("//a[text()='Accounts']"));
		waitforPagetobeenable();

		//// a[text()='Accounts Home']
		Thread.sleep(10000);
		try {
			Clickon(getwebelement(xml.getlocator("//locators/Accounts")));
		} catch (Exception e) {
			try {
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/Accounts")));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Accounts Tab");
		System.out.println(InputData[0].toString());

		try {
			SendKeys(getwebelement(xml.getlocator("//locators/OCNfield")), InputData[1].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter OCN field value :" + InputData[1].toString());

			Clickon(getwebelement(xml.getlocator("//locators/GoButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Go Button");

			waitforPagetobeenable();
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/AccountName").replace("OCN", InputData[1].toString())));
			OrderingCustomer.set(Gettext(
					getwebelement(xml.getlocator("locators/AccountName").replace("OCN", InputData[1].toString()))));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccountName");

			waitforPagetobeenable();
			Thread.sleep(5000);
		} catch (Exception e1) {
			Clickon(getwebelement("//a[text()='Accounts Home']"));

			waitforPagetobeenable();

			SendKeys(getwebelement(xml.getlocator("//locators/OCNfield")), InputData[1].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter OCN field value :" + InputData[1].toString());

			Clickon(getwebelement(xml.getlocator("//locators/GoButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Go Button");

			waitforPagetobeenable();
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/AccountName").replace("OCN", InputData[1].toString())));
			OrderingCustomer.set(Gettext(
					getwebelement(xml.getlocator("locators/AccountName").replace("OCN", InputData[1].toString()))));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccountName");

			waitforPagetobeenable();
			Thread.sleep(5000);
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/InstalledAssetNew"));
		Clickon(getwebelement(xml.getlocator("//locators/InstalledAssetNew")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installed Asset New");
	}

	public void popupFieldSelection(String SearchItem, String DropdownValue, String SearchValue) throws Exception {
		String DropDown = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//span[@data-allowdblclick='true']";
		// String DropValue="//div[contains(@style,'block') and
		// contains(@class,'ui-draggable
		// ui-resizable')]//span[@data-allowdblclick='true']//li[text()='value']";
		String DropValue = "//li[text()='value']";
		String SearchBox = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//input[@aria-labelledby='PopupQuerySrchspec_Label']";
		String Go = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//td[@class='siebui-popup-filter']//button[contains(@title,'Go')]";
		String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
		String ActiveRow = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//tr//td[contains(text(),'Active')]/parent::tr";
		waitForpageload();
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on : " + SearchItem);

		WaitforElementtobeclickable(DropDown);
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(DropDown));
		waitforPagetobeenable();
		waitForpageload();

		DropValue = DropValue.replace("value", DropdownValue);
		System.out.println(DropValue);
		WaitforElementtobeclickable(DropValue);
		Clickon(getwebelement(DropValue));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Search with : " + DropdownValue);

		ClearSendKeys(getwebelement(SearchBox), SearchValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter " + DropdownValue + " as : " + SearchValue);

		WaitforElementtobeclickable(Go);
		Clickon(getwebelement(Go));
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(15000);
		List<WebElement> HeaderList = GetWebElements(DropValue);
		if (HeaderList.size() > 0) {
			try {
				WaitforElementtobeclickable(Ok);
				if (isElementPresent(Go) && isDisplayed(Ok)) {
					Clickon(getwebelement(ActiveRow));
					waitForpageload();
					waitforPagetobeenable();
					Clickon(getwebelement(Ok));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Click on OK");
					waitforPagetobeenable();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	public void AccessServiceID(String DropdownValue, String InputValue) throws Exception {
		String DropDown = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//span[@data-allowdblclick='true']";
		// String DropValue="//div[contains(@style,'block') and
		// contains(@class,'ui-draggable
		// ui-resizable')]//span[@data-allowdblclick='true']//li[text()='value']";
		String DropValue = "//li[text()='value']";
		String SearchBox = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//input[@aria-labelledby='PopupQuerySrchspec_Label']";
		String Go = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//td[@class='siebui-popup-filter']//button[contains(@title,'Go')]";
		String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
		String CompletedRow = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//tr//td[contains(text(),'Completed')]/parent::tr";
		waitForpageload();
		waitforPagetobeenable();
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on :
		// "+SearchItem);

		WaitforElementtobeclickable(DropDown);
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(DropDown));
		waitforPagetobeenable();
		waitForpageload();

		DropValue = DropValue.replace("value", DropdownValue);
		System.out.println(DropValue);
		WaitforElementtobeclickable(DropValue);
		Clickon(getwebelement(DropValue));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Search with : " + DropdownValue);

		ClearSendKeys(getwebelement(SearchBox), InputValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter " + DropdownValue + " as : " + InputValue);

		WaitforElementtobeclickable(Go);
		Clickon(getwebelement(Go));
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(30000);
		List<WebElement> HeaderList = GetWebElements(DropValue);
		if (HeaderList.size() > 0) {
			try {
				WaitforElementtobeclickable(Ok);
				if (isElementPresent(Go) && isDisplayed(Ok)) {
					Clickon(getwebelement(CompletedRow));
					waitForpageload();
					waitforPagetobeenable();
					Clickon(getwebelement(Ok));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Click on OK");
					waitforPagetobeenable();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void orderingPartySearch(Object[] InputData) throws Exception {

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartySearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartySearch")));
		waitforPagetobeenable();

		// WaitforElementtobeclickable(xml.getlocator("//locators/PartySearchPopupDropdown"));
		// Clickon(getwebelement(xml.getlocator("//locators/PartySearchPopupDropdown")));
		// Updated By Devesh
		WaitforElementtobeclickable(xml.getlocator("//locators/PopupCustomDropdown"));
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/PopupCustomDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Contact Popup Dropdown");
		waitforPagetobeenable();
		waitForpageload();

		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Search
		// Popup Dropdown");
		WaitforElementtobeclickable(xml.getlocator("//locators/PartyName"));
		Clickon(getwebelement(xml.getlocator("//locators/PartyName")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Name");
		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyname")), InputData[5].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name as : " + InputData[5].toString());
		WaitforElementtobeclickable(xml.getlocator("//locators/PickAccntOk"));
		Clickon(getwebelement(xml.getlocator("//locators/PickAccntOk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Account Ok");
		waitforPagetobeenable();
	}

	public void orderingPartyAddres(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		waitForpageload();
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartyAddrSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyAddrSearch")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Odering Party Address Search");
		// Updated By Devesh
		WaitforElementtobeclickable(xml.getlocator("//locators/PopupCustomDropdown"));
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/PopupCustomDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Contact Popup Dropdown");
		waitforPagetobeenable();
		waitForpageload();

		// WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartyAddrSearchDropdown"));
		// Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyAddrSearchDropdown")));
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering
		// Party Address Search Dropdown");
		WaitforElementtobeclickable(xml.getlocator("//locators/PartyAddr"));
		Clickon(getwebelement(xml.getlocator("//locators/PartyAddr")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Address");
		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyAddr")), InputData[6].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Enter Input Party Address as : " + InputData[6].toString());
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/PickAddrSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/PickAddrSearch")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Thread.sleep(2000);
		Waitforvisibilityofelement(xml.getlocator("//locators/PickAddrSubmit1"));
		WaitforElementtobeclickable(xml.getlocator("//locators/PickAddrSubmit1"));
		Clickon(getwebelement(xml.getlocator("//locators/PickAddrSubmit1")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address Submit");
	}

	public void orderingPartyContact(Object[] InputData) throws Exception {
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartyContactSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyContactSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Contact Search");
		Thread.sleep(1000);
		// Updated By Devesh
		WaitforElementtobeclickable(xml.getlocator("//locators/PopupCustomDropdown"));
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/PopupCustomDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party Contact Popup Dropdown");
		waitforPagetobeenable();
		waitForpageload();
		// WaitforElementtobeclickable(xml.getlocator("//locators/PartyContactPopupDropdown"));
		// Thread.sleep(1000);
		// Clickon(getwebelement(xml.getlocator("//locators/PartyContactPopupDropdown")));
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Party
		// Contact Popup Dropdown");
		// waitforPagetobeenable();
		// waitForpageload();
		System.out.println("page load successfully of party contact");

		WaitforElementtobeclickable(xml.getlocator("//locators/LastName"));

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/LastName")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on First Name");
		SendKeys(getwebelement(xml.getlocator("//locators/InputFirstname")), InputData[7].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Input First Name as :" + InputData[7].toString());
		Clickon(getwebelement(xml.getlocator("//locators/PickContactSearch")));
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/FirstnameSubmit"));
		Clickon(getwebelement(xml.getlocator("//locators/FirstnameSubmit")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit");
	}

	public void salesChanel(Object[] InputData) throws Exception {
		System.out.println("Sales Channel Print");
		Thread.sleep(4000);
		SendKeys(getwebelement(xml.getlocator("//locators/SalesChannel")), InputData[8].toString());

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Sales Channel");
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NewServiceOrder"));
		Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
		waitForpageload();
		waitforPagetobeenable();
	}

	public void createCustomerOrder(Object[] InputData) throws Exception {
		System.out.println("enter into new order loop");
		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), InputData[2].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity as : " + InputData[2].toString());

		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date as :" + CurrentDate());

		SendKeys(getwebelement(xml.getlocator("//locators/DeliveryChannel")), InputData[4].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Enter Delivery Channel as : " + InputData[4].toString());

		// New Code
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartySearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartySearch")));
		waitforPagetobeenable();

		popupFieldSelection("Ordering Party", "Party UId", InputData[1].toString());

		// orderingPartySearch(InputData);

		waitforPagetobeenable();
		waitForpageload();
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartyAddrSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyAddrSearch")));
		waitforPagetobeenable();

		popupFieldSelection("Ordering Party Address", "OCN", InputData[1].toString());

		// orderingPartyAddres(InputData);

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderingPartyContactSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderingPartyContactSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ordering Party Contact Search");

		// orderingPartyContact(InputData);
		popupFieldSelection("Ordering Party Contact", "OCN", InputData[1].toString());

		Thread.sleep(2000);

		salesChanel(InputData);
	}

	public void openServiceOrderNumber() throws Exception {
		ServiceOrder.set(Gettext(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo"))));
		System.out.println("Service Order number : "+ServiceOrder.get());
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Generated Service Order Reference No: " + ServiceOrder.get());
		Log.info(ServiceOrder.get());
		System.out.println("Service Order A : "+ServiceOrder.get());
		
		ServiceOrders.setPrimaryServiceOrder(ServiceOrder.get());
		System.out.println("Service Order B : "+ServiceOrders.getPrimaryServiceOrder());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
		waitForpageload();
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Click on Service Order Reference No :" + ServiceOrder.get());
		Thread.sleep(5000);
	}

	public void openServiceOrderWithTab(Object[] InputData) throws Exception {
		Thread.sleep(10000);
		do {
			Pagerefresh();
			System.out.println("Page to be refresed");
			Thread.sleep(20000);
		} while (!isElementPresent("//a[text()='Orders']"));
		try {
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Tab");
		} catch (Exception e) {
			try {
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/InputServiceOrder"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get().toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Enter Service Order Number as : " + ServiceOrder.get().toString());

		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Go link ");
		Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Click on Search Service Order Number : " + ServiceOrder.get().toString());
	}

	public void addNetwork(String Network) throws InterruptedException, DocumentException, IOException {
		Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference Search");
		Thread.sleep(1000);
		SendKeys(getwebelement(xml.getlocator("//locators/InputNetworkReference")), Network);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Network reference ");
		Thread.sleep(1000);
		Clickon(getwebelement(xml.getlocator("//locators/SearchNetworkReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference");
		Thread.sleep(1000);

		Clickon(getwebelement(xml.getlocator("//locators/SubmitNetworkReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Network Reference");
		waitforPagetobeenable();
		Thread.sleep(3000);
	}

	public void addNetwork() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/NetworkReferenceSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
		WaitforElementtobeclickable(xml.getlocator("//locators/NetworkPlusSign"));
		Clickon(getwebelement(xml.getlocator("//locators/NetworkPlusSign")));
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SelectNetworkReference"));
		Clickon(getwebelement(xml.getlocator("//locators/SelectNetworkReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference Search");

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Input Network Reference");

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference");
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SubmitNetworkReference"));
		Clickon(getwebelement(xml.getlocator("//locators/SubmitNetworkReference")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Network Reference");
	}

	public void enterMandatoryFieldsInHeader(Object[] InputData) throws Exception {

		waitForpageload(); // as per Aman
		waitforPagetobeenable(); // as per Aman
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		System.out.println("Enter New Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/AddOrderSubType"));
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
		SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), InputData[11].toString());
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);
		Thread.sleep(2000);

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");

		WaitforElementtobeclickable(xml.getlocator("//locators/SubmitSubOrderType"));
		Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
		waitforPagetobeenable();
		Thread.sleep(3000);
		SendkeaboardKeys(getwebelement("//input[@aria-labelledby='COLT_Order_Item_Sub_Type_Label']"), Keys.TAB);
		Thread.sleep(3000);
		
		String SourceRef = "Test" + Integer.toString(GetRandomNumber(20));
		
		SendKeys(getwebelement("//input[@aria-labelledby='COLT_Service_Order_Source_Ref_Label']"),SourceRef);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/ContractSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/ContractSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Contract Search");
		waitForpageload();
		waitforPagetobeenable();
	    List<WebElement> tableRow = GetWebElements("//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//table[@class='ui-jqgrid-btable']//tr");
		Assert.assertTrue(tableRow.size()>0, "Contract Rows not found");
		WebElement ele1 = tableRow.get(0);
		Clickon(ele1);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:  Select Contract");
		waitForpageload();
		waitforPagetobeenable();
		String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
		Clickon(getwebelement(Ok));
		
		/*WaitforElementtobeclickable(xml.getlocator("//locators/InputContractId")); // new added by ayush
		SendKeys(getwebelement(xml.getlocator("//locators/InputContractId")), InputData[12].toString());
		WaitforElementtobeclickable((xml.getlocator("//locators/ContractIdSearch"))); // add by ayush for abandoned
		Clickon(getwebelement(xml.getlocator("//locators/ContractIdSearch")));*/
		waitforPagetobeenable();
		// Clickon(getwebelement(xml.getlocator("//locators/SubmitContract")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Contract");
		Thread.sleep(5000);
		if (InputData[9].toString().equals("Ethernet Hub")) 
		{
			Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference Search");
			Clickon(getwebelement(xml.getlocator("//locators/AddNetworkReferenceSearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Network Reference Search");
			Thread.sleep(3000);
			HubNetworkReference.set(Gettext(getwebelement(xml.getlocator("//locators/TextNetworkReference"))));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Generated Hub Network Reference No: " + HubNetworkReference.get());
			Log.info(HubNetworkReference.get());
			Clickon(getwebelement(xml.getlocator("//locators/SubmitNetworkReference")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Network Reference");
			waitforPagetobeenable();
			Thread.sleep(3000);
		}
		else if (InputData[9].toString().equals("Dark Fibre") || InputData[9].toString().equals("public Wave Node")|| InputData[9].toString().equals("Ethernet VPN Access")) 
		{
			addNetwork(InputData[33].toString());
		} 
		
		else if (InputData[9].toString().equals("HNS") || InputData[9].toString().equals("Ethernet Spoke")) 
		{
			if (HubNetworkReference.get() != null) 
			{
				SendKeys(getwebelement(xml.getlocator("//locators/InputNetworkReference")), HubNetworkReference.get());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Network reference from New Hub");
			} 
			else 
			{
				addNetwork(InputData[33].toString());
			}

		}
		String[] nonNetworkProducts = new String[] { "Cloud Unified Communications", "IP Voice Solutions",
				"Professional Services", "Wave", "Ethernet Line", "Ethernet Spoke", "Ethernet Hub", "Private Ethernet",
				"Private Wave Service", "DCA Ethernet", "Ultra Low Latency", "Ethernet Spoke", "Ethernet Access",
				"Private Wave Node","IP VPN Service", "Ethernet VPN Access","CPE Solutions Site" };
		int index = Arrays.asList(nonNetworkProducts).indexOf(InputData[9].toString());
		System.out.println("click service order search field" + String.valueOf(index));
		if (index < 0) 
		{
			addNetwork();
		}
		Thread.sleep(6000);

		/*
		 * Voice Line maintenance party is mandatory in New OMP so i am commenting if
		 * condition
		 */
		// if (!InputData[9].toString().equals("Voice Line V")) {
		WaitforElementtobeclickable(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary"));
		SendKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), InputData[22].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Existing Capacity Lead Time Primary");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
		waitforPagetobeenable();
		// savePage();
		// Thread.sleep(10000);
		Thread.sleep(5000);

		// InputData[9].toString().equalsIgnoreCase("Wave") and EthernetLine removed
		// from if condition
		/*
		 * Maintenance party Code WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartySearch")); waitforPagetobeenable();
		 * 
		 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartySearch")));
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyPopupDropdown")); // add by ayush
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/MaintenancePartyPopupDropdown")));
		 * safeJavaScriptClick(getwebelement(xml.getlocator("//locators/AccountStatus"))
		 * );
		 * WaitforElementtobeclickable(xml.getlocator("//locators/InputAccountStatus"));
		 * SendKeys(getwebelement(xml.getlocator("//locators/InputAccountStatus")),
		 * InputData[23].toString());
		 * WaitforElementtobeclickable(xml.getlocator("//locators/AccountStatusSearch"))
		 * ; // add by ayush
		 * Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSearch")));
		 * waitforPagetobeenable(); Thread.sleep(4000);
		 * WaitforElementtobeclickable(xml.getlocator("//locators/AccountStatusSubmit"))
		 * ; Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSubmit")));
		 * Thread.sleep(3000);
		 * 
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyContact"));
		 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyContact")));
		 * waitforPagetobeenable(); WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyContactPopupDropdown")); // add // by
		 * Thread.sleep(4000); // ayush
		 * safeJavaScriptClick(getwebelement(xml.getlocator(
		 * "//locators/MaintenancePartyContactPopupDropdown")));
		 * //WaitforElementtobeclickable(xml.getlocator("//locators/DropDown")); // add
		 * by dipesh //Clickon(getwebelement(xml.getlocator("//locators/DropDown")));//
		 * add by dipesh
		 * //waitandForElementDisplay((xml.getlocator("//locators/MaintenanceLastName"))
		 * , 5);
		 * Clickon(getwebelement(xml.getlocator("//locators/MaintenanceLastName")));
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/InputMaintenanceLastName"));
		 * SendKeys(getwebelement(xml.getlocator("//locators/InputMaintenanceLastName"))
		 * , InputData[24].toString()); // add by ayush
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/InputMaintenanceLastNameSearch"));
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/InputMaintenanceLastNameSearch"))); waitforPagetobeenable();
		 * Thread.sleep(2000); WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyContactSubmit"));
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/MaintenancePartyContactSubmit"))); Thread.sleep(3000);
		 * 
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyAddress"));
		 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyAddress")));
		 * waitforPagetobeenable(); Thread.sleep(3000);
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyAddresPopupDropdown"));// add by // ayush
		 * safeJavaScriptClick(getwebelement(xml.getlocator(
		 * "//locators/MaintenancePartyAddresPopupDropdown")));
		 * WaitforElementtobeclickable(xml.getlocator("//locators/PartyAddresStreetName"
		 * )); // add by ayush
		 * Clickon(getwebelement(xml.getlocator("//locators/PartyAddresStreetName")));
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/InputPartyAddresStreetName")); // add by ayush
		 * SendKeys(getwebelement(xml.getlocator("//locators/InputPartyAddresStreetName"
		 * )), InputData[25].toString()); WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/InputPartyAddresStreetNameSearch"));
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/InputPartyAddresStreetNameSearch"))); waitforPagetobeenable();
		 * Thread.sleep(2000); WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/MaintenancePartyAddressSubmit")); // add by ayush
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/MaintenancePartyAddressSubmit"))); waitforPagetobeenable();
		 * Thread.sleep(3000);
		 */

		// proactive Contact
		Clickon(getwebelement("//input[@aria-labelledby='COLT_ProContact_FullName_Label']/following-sibling::span"));
		Thread.sleep(5000);

		popupFieldSelection("Proactive Contact", "Last Name", InputData[7].toString());
		savePage();
		waitForpageload();
		waitforPagetobeenable();
		if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) {
			WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
			Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
			waitForpageload();
			System.out.println("page load succesfuuly now come to middle applet");
			waitforPagetobeenable();
		}
	}

	public void VoiceFeatureTab(Object[] Inputdata) throws Exception {
		// ClickHereSave();
		if (Inputdata[9].toString().equals("Voice Line V")) 
		{
			waitforPagetobeenable();
			OpenTab("Voice Features");
			Thread.sleep(5000);

			waitforPagetobeenable();
			// Thread.sleep(35000);
			Clickon(getwebelement(xml.getlocator("//locators/showfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full Info");
			Thread.sleep(5000);

			// code update start

			WaitforElementtobeclickable((xml.getlocator("//locators/ByPassNumber")));
			Clickon(getwebelement(xml.getlocator("//locators/ByPassNumber")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BypassNumber");

			WaitforElementtobeclickable((xml.getlocator("//locators/CallBarring")));
			Clickon(getwebelement(xml.getlocator("//locators/CallBarring")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallBarring");

			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Service Managemet Section selected");
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Call Barring Option")));
			Thread.sleep(3000);
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Profile 13")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Package Type= Profile 13 Selected");

			// code update end

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
			Thread.sleep(2000);

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
					"Disaster Recovery Options")));
			Thread.sleep(3000);
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "IN TDM")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Disaster Recovery Options= IN TDM");

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Number of DR Plans")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "3DR")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Package Type= 3DR");

			WaitforElementtobeclickable(xml.getlocator("//locators/InboundCallRerouting"));
			Clickon(getwebelement(xml.getlocator("//locators/InboundCallRerouting")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InboundCallRerouting");
			Thread.sleep(5000);
			// WaitforElementtobeclickable(xml.getlocator("//locators/DestinationNumberforRerouting"));

			Clickon(getwebelement(xml.getlocator("//locators/VoiceLineV/DestinationNumber").replace("Value",
					"Destination Number for Re-Routing")));
			SendKeys(getwebelement(xml.getlocator("//locators/VoiceLineV/DestinationNumber").replace("Value",
					"Destination Number for Re-Routing")), "1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InternationalCode");

			Thread.sleep(5000);
			// WaitforElementtobeclickable(xml.getlocator("//locators/MinimumSpendCommitment"));
			Clickon(getwebelement(xml.getlocator("//locators/MinimumSpendCommitment")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on MinimumSpendCommitment");
			Thread.sleep(5000);
			// WaitforElementtobeclickable(xml.getlocator("//locators/PartialNumberReplacement"));
			Clickon(getwebelement(xml.getlocator("//locators/PartialNumberReplacement")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PartialNumberReplacement");
			Thread.sleep(5000);

			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "International Code")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "International Code")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on InternationalCode");

			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Local Area Code")));
			SendKeys(getwebelement(xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Local Area Code")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Local Area Code");

			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Number Range Start")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Number Range Start")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Number Range Start");

			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Number Range End")));
			SendKeys(
					getwebelement(xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Number Range End")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Number Range Start");

			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Local Area Code")));
			SendKeys(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Local Area Code")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Replacement Local Area Code");

			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Main Number")));
			SendKeys(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Main Number")), "1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Replacement Main Number");

			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value",
					"Replacement Number Range Start")));
			SendKeys(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Number Range Start")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Replacement Number Range Start");

			Thread.sleep(5000);
			Clickon(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Number Range End")));
			SendKeys(getwebelement(
					xml.getlocator("//locators/VoiceLineV/TextBox").replace("Value", "Replacement Number Range End")),
					"1");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Replacement Number Range End");

			Clickon(getwebelement(xml.getlocator("//locators/ServiceActivationSupport")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceActivationSupport");
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Language")));
			Thread.sleep(3000);
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "English")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Package Type= 3DR");

			Clickon(getwebelement(xml.getlocator("//locators/TelephoneDirectoryServices")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TelephoneDirectoryServices");
			Thread.sleep(5000);
			closePopUp();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CrossButton");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryContact");
			Thread.sleep(5000);

			ClickHereSave();

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
		} else if (Inputdata[9].toString().equals("SIP Trunking")) { // added as per Gauri
			waitForpageload();
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/VoicefeaturesTab")));

			waitForpageload();
			waitforPagetobeenable();
			WaitforElementtobeclickable((xml.getlocator("//locators/showfullinfo")));
			Clickon(getwebelement(xml.getlocator("//locators/showfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full Info");

			WaitforElementtobeclickable((xml.getlocator("//locators/ByPassNumber")));
			Clickon(getwebelement(xml.getlocator("//locators/ByPassNumber")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BypassNumber");

			WaitforElementtobeclickable((xml.getlocator("//locators/CallBarring")));
			Clickon(getwebelement(xml.getlocator("//locators/CallBarring")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallBarring");
			// Thread.sleep(5000);

			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Service Managemet Section selected");
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Call Barring Option")));
			Thread.sleep(3000);
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Profile 13")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Package Type= Profile 13 Selected");

			// Thread.sleep(5000);

			WaitforElementtobeclickable(xml.getlocator("//locators/DisasterRecovery"));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/DisasterRecovery")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecovery");
			Thread.sleep(2000);

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Number of DR Plans")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "3DR")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Package Type= 3DR");

			Thread.sleep(1000);
			int iCount = getwebelementscount(xml.getlocator("//locators/SIPCheckBoxCount"));
			System.out.println("count of element is" + iCount);

			while (iCount > 1) {
				if (iCount < 6) {
					if (iCount < 5) {
						WaitforElementtobeclickable((xml.getlocator("//locators/SIPCheckBox").replace("value", "1")));
						Thread.sleep(500);
						Clickon(getwebelement(xml.getlocator("//locators/SIPCheckBox").replace("value", "3")));
						iCount--;
					} else {
						WaitforElementtobeclickable((xml.getlocator("//locators/SIPCheckBox").replace("value", "1")));
						Thread.sleep(500);
						Clickon(getwebelement(xml.getlocator("//locators/SIPCheckBox").replace("value", "2")));
						iCount--;
					}
				} else {
					WaitforElementtobeclickable((xml.getlocator("//locators/SIPCheckBox").replace("value", "1")));
					Thread.sleep(500);
					Clickon(getwebelement(xml.getlocator("//locators/SIPCheckBox").replace("value", "1")));
					iCount--;
				}

			}
			Thread.sleep(1000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "International Code"));
			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "International Code")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "International Code")));
			SendKeys(
					(getwebelement(
							xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "International Code"))),
					"12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Local Area Code")));
			Clear(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Local Area Code")));
			SendKeys(
					(getwebelement(
							xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Local Area Code"))),
					"12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range Start")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range Start")));
			SendKeys(
					(getwebelement(
							xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range Start"))),
					"12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range End")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range End")));
			SendKeys(
					(getwebelement(
							xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number Range End"))),
					"12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Local Area Code")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Local Area Code")));
			SendKeys((getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Local Area Code"))),
					"12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Main Number")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Main Number")));
			SendKeys((getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Replacement Main Number"))),
					"12");

			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range Start")));
			Clear(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range Start")));
			SendKeys((getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range Start"))), "12");

			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range End")));
			Clear(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range End")));
			SendKeys((getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Replacement Number Range End"))), "12");

			Clickon(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number of Channels")));
			Clear(getwebelement(
					xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number of Channels")));
			SendKeys(
					(getwebelement(
							xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Number of Channels"))),
					"12");

			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Destination Number for Re-Routing")));
			Clear(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Destination Number for Re-Routing")));
			SendKeys((getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value",
					"Destination Number for Re-Routing"))), "12");

			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Language")));
			Clear(getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Language")));
			SendKeys((getwebelement(xml.getlocator("//locators/SIPVoiceFeatureInput").replace("value", "Language"))),
					"English");
			Thread.sleep(1000);

			closePopUp();

			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryContact");
			// Thread.sleep(5000);
			ClickHereSave();

			WaitforElementtobeclickable((xml.getlocator("//locators/DisasterRecoveryContact")));
			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryContact");
			// Thread.sleep(5000);
			WaitforElementtobeclickable((xml.getlocator("//locators/DisasterRecoveryAdd")));
			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryAdd")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryAdd");
			// Thread.sleep(5000);
			WaitforElementtobeclickable((xml.getlocator("//locators/DisasterAdd2")));
			Clickon(getwebelement(xml.getlocator("//locators/DisasterAdd2")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterAdd2");
			// Thread.sleep(5000);
			WaitforElementtobeclickable((xml.getlocator("//locators/DisasterRecoveryAdd")));
			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryAdd")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryAdd");
			// Thread.sleep(5000);
			WaitforElementtobeclickable((xml.getlocator("//locators/DisasterRecoveryOk")));
			Clickon(getwebelement(xml.getlocator("//locators/DisasterRecoveryOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on DisasterRecoveryOk");
			Thread.sleep(5000);

		}
	}

	public void MandatoryFields(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Voice Line V") || InputData[9].toString().equals("SIP Trunking")) {

			Clickon(getwebelement(xml.getlocator("//locators/NumberManagementTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberManagementTab");
			Thread.sleep(15000);
			
			String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
			String loc = "//table[@id='s_1_l']//tr[@id='1']/td[ind]";
			String span = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			String input = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			
			// Number Range Status
			int	Index = NumberTabIndex("Number Range Status");
			System.out.println(Index);
			String newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
						// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
						
			Clickon(getwebelement(newloc));
						
			ClearSendKeys(getwebelement(newloc), "Active"+Keys.ENTER);
			
			/*Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatus")));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusDropDown"));
			Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusDropDown")));
			// Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/NumberRangeStatusValueActive"));
			Clickon(getwebelement(xml.getlocator("//locators/NumberRangeStatusValueActive")));
			Thread.sleep(5000);
			waitforPagetobeenable();*/
			/*
			 * Clickon(getwebelement(xml.getlocator("//locators/ManualValidation")));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step: Click Manual Validation Button"); waitforPagetobeenable();
			 */
			Thread.sleep(10000);
		}
	}

	public void OperationAttribute(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Voice Line V")) {
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
			// SendKeys(getwebelement(xml.getlocator("//locators/AttributeName")),"Scenario");
			// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeName")),Keys.ENTER);
			Thread.sleep(5000);
			/*
			 * Clickon(getwebelement(xml.getlocator("//locators/AttributeValue")));
			 * Thread.sleep(5000); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/AttributeValueDropDown"));
			 * Clickon(getwebelement(xml.getlocator("//locators/AttributeValueDropDown")));
			 * Thread.sleep(5000);
			 * Clickon(getwebelement(xml.getlocator("//locators/AttributeValue")));
			 */
			// SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),"Scenario
			// 1");
			// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),Keys.ENTER);
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
			SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")), "test");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute2"));
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute2")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/AttributeValue1")));
			SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")), "test");
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
			// SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),"Scenario
			// 1");
			// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/AttributeValue")),Keys.ENTER);
			Thread.sleep(5000);
		}
	}

	public void SelectServiceGroupTab(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Voice Line V") || InputData[9].toString().equals("SIP Trunking")
				|| InputData[9].toString().equals("Number Hosting") || InputData[9].toString().equals("Interconnect")) {

			OpenTab("Service Group");

			waitForpageload();
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupNew")));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/serviceGrouplookup"));
			Clickon(getwebelement(xml.getlocator("//locators/serviceGrouplookup")));
			// Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupOk")));
			Thread.sleep(5000);
		}
	}

	public int NumberTabIndex(String col) throws InterruptedException, DocumentException {
		// int index=0;
		List<WebElement> HeaderList = GetWebElements("//div[@id='a_1']//table[@class='ui-jqgrid-htable']//th");
		int StatusHeader = -1;
		int i = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText().trim();

			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase(col)) {
				StatusHeader = i;
				break;
			}
			i++;
		}
		return StatusHeader + 1;
	}

	public int CustomerIPPBXIndex(String col) throws InterruptedException, DocumentException {
		// int index=0;
		List<WebElement> HeaderList = GetWebElements("//div[@id='s_2_ld']//table[@class='ui-jqgrid-htable']//th");
		int StatusHeader = -1;
		int i = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText().trim();

			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase(col)) {
				StatusHeader = i;
				break;
			}
			i++;
		}
		return StatusHeader + 1;
	}

	public void NumberManagementTab(Object[] Inputdata) throws Exception {
		if (Inputdata[9].toString().equals("Voice Line V")) 
		{
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
			// Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/CallDistributionValue"));
			Clickon(getwebelement(xml.getlocator("//locators/CallDistributionValue")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CallDistributionValue");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/AssignedPortNew")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AssignedPortNew");
			Thread.sleep(5000);
			try {
				// Clickon(getwebelement(xml.getlocator("//locators/ClickDropdown").replace("Value",
				// "First Codec")));
				Clickon(getwebelement(xml.getlocator("//locators/UnassignedPortRow").replace("PortName", "Port 2")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Port 2");
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
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
			try {
				Clickon(getwebelement(xml.getlocator("//locators/UnassignedPortRow").replace("PortName", "Port 4")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Port 4");
			} catch (Exception exx) {
				System.out.println(exx.getMessage());
			}
			Clickon(getwebelement(xml.getlocator("//locators/Unassignedport")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Unassignedport");
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/NumberRangeTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeTab");
			Thread.sleep(5000);

			Clickon(getwebelement(xml.getlocator("//locators/NumberRangeListNew")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeListNew");
			Thread.sleep(10000);

			// NewCode
			// 888
			String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
			String loc = "//table[@id='s_1_l']//tr[@id='1']/td[ind]";
			String span = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			String input = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			// Number Plan
			int Index = NumberTabIndex("Number Plan");
			System.out.println(Index);
			String newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// String temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			Clickon(getwebelement(
					xml.getlocator("//locators/SelectValueDropdown").replace("Value", "New Colt Numbers")));

			// Main Number
			Index = NumberTabIndex("Main Number");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Main Number
			Index = NumberTabIndex("Colt Number Reservation Ref");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Local Area code
			Index = NumberTabIndex("Local Area code");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "12");

			// Number Range Start
			Index = NumberTabIndex("Number Range Start");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Number Range End
			Index = NumberTabIndex("Number Range End");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "12");

			// Number Range Status
			Index = NumberTabIndex("Number Range Status");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
			
			Clickon(getwebelement(newloc));
			
			ClearSendKeys(getwebelement(newloc), "Allocate"+Keys.ENTER);
			//Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Allocate")));

			// Port GroupID
			Index = NumberTabIndex("Port Group ID");
			System.out.println(Index);

			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			waitForpageload();
			Clickon(getwebelement(Ok));

			// BCN
			Index = NumberTabIndex("BCN");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			waitForpageload();
			Clickon(getwebelement(Ok));

			// Address UI ID
			Index = NumberTabIndex("Address UID");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			// temp="//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));

			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
			SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), Inputdata[37].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
			WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));

			SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[38].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
			SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[39].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
			SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), Inputdata[40].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
			SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), Inputdata[41].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
			Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
			Thread.sleep(3000);
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");
			Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");
			ClickHereSave();
			//
		} else if (Inputdata[9].toString().equals("SIP Trunking")) {

			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/NumberManagementTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberManagementTab");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/NumberRangeListNew")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on NumberRangeListNew");
			Thread.sleep(10000);

			// NewCode
			// 888
			String Ok = "//div[contains(@style,'block') and contains(@class,'ui-draggable ui-resizable')]//button[contains(@title,'OK')]";
			String loc = "//table[@id='s_1_l']//tr[@id='1']/td[ind]";
			String span = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//span";
			String input = "//table[@id='s_1_l']//tr[@id='1']/td[ind]//input";

			// Number Plan
			int Index = NumberTabIndex("Number Plan");
			System.out.println(Index);
			String newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));

			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			Clickon(getwebelement(
					xml.getlocator("//locators/SelectValueDropdown").replace("Value", "New Colt Numbers")));

			// Main Number
			Index = NumberTabIndex("Main Number");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Main Number
			Index = NumberTabIndex("Colt Number Reservation Ref");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Local Area code
			Index = NumberTabIndex("Local Area code");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "12");

			// Number Range Start
			Index = NumberTabIndex("Number Range Start");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "10");

			// Number Range End
			Index = NumberTabIndex("Number Range End");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "12");

			// Number Range Status
			Index = NumberTabIndex("Number Range Status");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			ClearSendKeys(getwebelement(newloc), "Allocate"+Keys.ENTER);
			//Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Allocate")));

			// BCN
			Index = NumberTabIndex("BCN");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			waitForpageload();
			Clickon(getwebelement(Ok));

			// Address UI ID
			Index = NumberTabIndex("Address UID");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
			SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), Inputdata[37].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
			WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));

			SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), Inputdata[38].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
			SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), Inputdata[39].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
			SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), Inputdata[40].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
			SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), Inputdata[41].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
			Clickon(getwebelement(xml.getlocator("//locators/SearchButtonAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
			Thread.sleep(3000);
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectPickAddressAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Address for Site");
			Clickon(getwebelement(xml.getlocator("//locators/PickAddressButtonAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Submit Address for Site");
			ClickHereSave();
		}
	}

	public void ExpnadTrunk() throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		List<WebElement> explist = GetWebElements(xml.getlocator("//locators/SIPVoiceConfigExpandSymbol"));
		if (explist.size() > 0) {
			WebElement ele1 = explist.get(0);
			Clickon(ele1);
			waitForpageload();
			waitforPagetobeenable();
		}
	}

	public void TrunkInputs(String InputName, String InputValue)
			throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/SIP/TrunkConfigInput").replace("Value", InputName));
		Clear(getwebelement(xml.getlocator("//locators/SIP/TrunkConfigInput").replace("Value", InputName)));
		SendKeys(getwebelement(xml.getlocator("//locators/SIP/TrunkConfigInput").replace("Value", InputName)),
				InputValue);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIP/TrunkConfigInput").replace("Value", InputName)),
				Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step Enter " + InputName + " values = " + InputValue);
	}

	public void TrunkDropdown(String DropDownName, String DropDownValue)
			throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(
				xml.getlocator("//locators/SIP/TrunkConfigDropdown").replace("Value", DropDownName));
		Clickon(getwebelement(xml.getlocator("//locators/SIP/TrunkConfigDropdown").replace("Value", DropDownName)));
		Clickon(getwebelement(
				xml.getlocator("//locators/SIP/TrunkConfigDrodownValue").replace("Value", DropDownValue)));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select " + DropDownName + " = " + DropDownValue);
	}

	public void TrunkAddmissionInputs(String InputName, String InputValue)
			throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/SIP/AddmissionInput").replace("Value", InputName));
		Clear(getwebelement(xml.getlocator("//locators/SIP/AddmissionInput").replace("Value", InputName)));
		SendKeys(getwebelement(xml.getlocator("//locators/SIP/AddmissionInput").replace("Value", InputName)), "4");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIP/AddmissionInput").replace("Value", InputName)),
				Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step Enter " + InputName + " value  = " + InputValue);

	}

	public void TrunkAddmissionDropdown(String DropDownName, String DropDownValue)
			throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/SIP/AddmissionDropdown").replace("Value", DropDownName));
		Clickon(getwebelement(xml.getlocator("//locators/SIP/AddmissionDropdown").replace("Value", DropDownName)));
		Clickon(getwebelement(xml.getlocator("//locators/SIP/AddmissionDrodownValue").replace("Value", DropDownValue)));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select " + DropDownName + " = " + DropDownValue);
	}

	public void TrunkEntries(String ProductName, String ServiceID) throws Exception {

		waitForpageload();
		waitforPagetobeenable();

		ExpnadTrunk();
		Thread.sleep(5000);

		if (ProductName.equals("SIP Trunking")) {
			TrunkInputs("Trunk Sequence", "1");
			String trunkName = "TR_" + Integer.toString(GetRandomNumber(10000));
			TrunkInputs("Trunk Name", trunkName);
			TrunkDropdown("Access Line Type", "Colt Access");

			// TrunkAccessID
			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkAccessID"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkAccessID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Access Service Idlookup ");
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);
			AccessServiceID("Product", "IP Access");

			TrunkInputs("External Access Service Reference", "NA");
			TrunkInputs("Customer Originating IPv4 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv4 Address", "10.7.235.29");
			ExpnadTrunk();
			TrunkInputs("Customer Originating IPv6 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv6 Address", "10.7.235.29");

			Thread.sleep(3000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPCustomerTerminatingIPv6"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv6")));
			Clear(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv6")));
			SendKeys(getwebelement(xml.getlocator("//locators/SIPCustomerTerminatingIPv6")), "10.7.235.2/24");
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Customer Terminating IPv6 ");
			Thread.sleep(5000);
			TrunkDropdown("Signalling Transport Protocol", "TCP");
			TrunkDropdown("VoIP Protocol", "SIP");
			TrunkDropdown("Encryption Type", "TLS on, SRTP off");
			TrunkInputs("Proportion%", "20");
			TrunkInputs("Trunk Site Name Alias", "Alias1");
			TrunkDropdown("NAT Required?", "N");
			TrunkInputs("NNI WAN IPv4 Address Range (/30)", "10.7.235.24");
			TrunkInputs("NNI WAN IPv6 Address Range", "10.7.235.24");
			TrunkInputs("Public IPv4 Address Range for NAT Side", "2");
			TrunkInputs("VLAN Start / End Range", "2");
			TrunkInputs("VLAN ID", "2");
			TrunkInputs("AS Number", "1");
			TrunkInputs("Colt Host Equipment IPv4 Address", "10.7.235.24/2");
			TrunkInputs("Colt Host Equipment IPv6 Address", "10.7.235.24/2");

			// call Addmission
			TrunkAddmissionInputs("Maximum Number of Simultaneous Calls", "4");
			TrunkAddmissionDropdown("Inbound Outbound Split", "No");
			TrunkAddmissionInputs("Maximum Inbound Calls", "1");
			TrunkAddmissionInputs("Overall Call limit for Trunk", "1");
			TrunkAddmissionInputs("In Call Limit for Trunk", "10");
			TrunkAddmissionInputs("Out Call limit for Trunk", "10");
			TrunkAddmissionDropdown("Type of CAC", "Individual Trunk CAC");
		} else if (ProductName.equals("Number Hosting")) {
			TrunkInputs("Trunk Sequence", "1");
			String trunkName = "TR_" + Integer.toString(GetRandomNumber(10000));
			TrunkInputs("Trunk Name", trunkName);
			TrunkDropdown("Access Line Type", "Colt Access");

			// TrunkAccessID
			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkAccessID"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkAccessID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Access Service Idlookup ");
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);
			AccessServiceID("Product", "IP Access");
			TrunkInputs("External Access Service Reference", "NA");
			TrunkInputs("Customer Originating IPv4 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv4 Address", "10.7.235.29");
			ExpnadTrunk();
			TrunkInputs("Customer Originating IPv6 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv6 Address", "10.7.235.29");
			TrunkDropdown("VoIP Protocol", "SIP");
			TrunkDropdown("Signalling Transport Protocol", "TCP");
			TrunkInputs("Colt Host Equipment IPv4 Address", "10.7.235.24/2");
			TrunkInputs("Colt Host Equipment IPv6 Address", "10.7.235.24/2");
			TrunkDropdown("NAT Required?", "N");
			TrunkInputs("Public IPv4 Address Range for NAT Side", "2");
			TrunkInputs("Proportion%", "20");
			TrunkDropdown("Encryption Type", "TLS on, SRTP off");
			TrunkInputs("NNI WAN IPv4 Address Range (/30)", "10.7.235.24");
			TrunkInputs("NNI WAN IPv6 Address Range", "10.7.235.24");
			TrunkInputs("VLAN Start / End Range", "2");
			TrunkInputs("VLAN ID", "2");
			TrunkInputs("AS Number", "1");

			Thread.sleep(3000);

			// call Addmission
			TrunkAddmissionInputs("Maximum Number of Simultaneous Calls", "4");
			TrunkAddmissionDropdown("Inbound Outbound Split", "No");
			TrunkAddmissionInputs("Maximum Inbound Calls", "1");
			TrunkAddmissionInputs("Overall Call limit for Trunk", "1");
			TrunkAddmissionInputs("In Call Limit for Trunk", "10");
			TrunkAddmissionInputs("Out Call limit for Trunk", "10");
			TrunkAddmissionDropdown("Type of CAC", "Individual Trunk CAC");
		} else if (ProductName.equals("Interconnect")) {
			TrunkInputs("Trunk Sequence", "1");
			String trunkName = "TR_" + Integer.toString(GetRandomNumber(10000));
			TrunkInputs("Trunk Name", trunkName);
			TrunkDropdown("Access Line Type", "Colt Access");

			// TrunkAccessID
			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkAccessID"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkAccessID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Access Service Idlookup ");
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(2000);
			AccessServiceID("Product", "IP Access");
			TrunkInputs("External Access Service Reference", "NA");
			TrunkInputs("Customer Originating IPv4 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv4 Address", "10.7.235.29");
			ExpnadTrunk();
			TrunkInputs("Customer Originating IPv6 Address Range", "10.7.235.29");
			TrunkInputs("Customer Terminating IPv6 Address", "10.7.235.29");
			TrunkDropdown("VoIP Protocol", "SIP");
			TrunkDropdown("Signalling Transport Protocol", "TCP");
			TrunkInputs("Colt Host Equipment IPv4 Address", "10.7.235.24/2");
			TrunkInputs("Colt Host Equipment IPv6 Address", "10.7.235.24/2");
			TrunkDropdown("NAT Required?", "N");
			TrunkInputs("Public IPv4 Address Range for NAT Side", "2");
			TrunkInputs("Trunk Site Name Alias", "TR_" + Integer.toString(GetRandomNumber(100)));
			TrunkInputs("Proportion%", "20");
			TrunkDropdown("Encryption Type", "TLS on, SRTP off");
			TrunkInputs("NNI WAN IPv4 Address Range (/30)", "10.7.235.24");
			TrunkInputs("NNI WAN IPv6 Address Range", "10.7.235.24");
			TrunkInputs("VLAN Start / End Range", "2");
			TrunkInputs("VLAN ID", "2");
			TrunkInputs("AS Number", "1");

			Thread.sleep(3000);

			// call Addmission
			TrunkAddmissionInputs("Maximum Number of Simultaneous Calls", "4");
			TrunkAddmissionDropdown("Inbound Outbound Split", "No");
			TrunkAddmissionInputs("Maximum Inbound Calls", "1");
			TrunkAddmissionInputs("Overall Call limit for Trunk", "1");
			TrunkAddmissionInputs("In Call Limit for Trunk", "10");
			TrunkAddmissionInputs("Out Call limit for Trunk", "10");
			TrunkAddmissionDropdown("Type of CAC", "Individual Trunk CAC");
		}
		// Update
		Thread.sleep(10000);

		ClickHereSave();
	}

	public void VoiceConfigTab(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Voice Line V")) 
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Sequence"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Sequence")));
			ClearSendKeys(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Sequence")),
					"1");
			SendkeaboardKeys(
					(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Sequence"))),
					Keys.TAB);

			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Name"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Name")));
			ClearSendKeys(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Name")),
					"Trunk" + Integer.toString(GetRandomNumber(1000)));
			SendkeaboardKeys((getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Name"))),
					Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on TrunkName");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Site Name Alias"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Site Name Alias")));
			ClearSendKeys(
					getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Site Name Alias")),
					"Trunk" + Integer.toString(GetRandomNumber(1000)));
			SendkeaboardKeys(
					(getwebelement(xml.getlocator("//locators/TrunkInput").replace("Value", "Trunk Site Name Alias"))),
					Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Trunk Site Name Alias Entered");

			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkAccessLine"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkAccessLine")));
			String lctValue = xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Colt Access");
			Clickon(getwebelement(lctValue));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AccessLineType");

			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkAccessID"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkAccessID")));
			Thread.sleep(5000);
			AccessServiceID("Product", "IP Access");
			// AccessServiceID("Access Service ID","Service Id",InputData[54].toString());
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/showfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full info");
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/TrunkConfigurationplus")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trunk Configuration Plus");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/outsidebuissnesshourinstallation")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on outside buissness hour installation");
			Thread.sleep(5000);
			closePopUp();
			// Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
			Thread.sleep(5000);
			Save();
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/TrunckConfigplus")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trunk Configuration Plus");
			Thread.sleep(5000);
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/OtherTab")));
			// OpenTab("Other");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Other Tab");
			Thread.sleep(5000);
			List<WebElement> Portlist = GetWebElements(xml.getlocator("//locators/SelectSiteSearchAccess"));
			WebElement ele1 = Portlist.get(0);

			Clickon(ele1);
			waitForpageload();
			List<String> list = new ArrayList<String>();
			list.add("Parker");
			list.add("United Kingdom");
			list.add("London");
			list.add("E16 2DJ");
			list.add("1");
			siteSearchEnterAndSelection(list);

			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceLineV/RigthShowFullInfo"));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/VoiceLineV/RigthShowFullInfo")));

			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceLineV/SIPTDM"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceLineV/SIPTDM")));

			closePopUp();

			VoiceLineVPopUpEntries();

			ClickHereSave();

			waitforPagetobeenable();

			List<WebElement> Portlist1 = GetWebElements(xml.getlocator("//locators/SelectSiteSearchAccess"));

			WebElement ele2 = Portlist1.get(0);
			Clickon(ele2);
			waitForpageload();

			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Site");

			List<String> list2 = new ArrayList<String>();
			list2.add("Parker");
			list2.add("United Kingdom");
			list2.add("London");
			list2.add("E16 2DJ");
			list2.add("1");
			siteSearchEnterAndSelection(list2);

			ClickHereSave();

			AEndDropdownSelection("Type of Lines", "BRI");

			AEndInputEnter("Number of Lines", "2");

			AEndDropdownSelection("CRC4", "NA");

			AEndDropdownSelection("Max Channels (PRI)", "15");

			ClickHereSave();

		}
	}

	public void VoiceLineVPopUpEntries() throws InterruptedException, DocumentException, IOException {
		// Port ID
		waitForpageload();
		waitforPagetobeenable();
		List<WebElement> Portlist = GetWebElements(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Port ID"));

		WebElement ele1 = Portlist.get(0);
		WebElement ele2 = Portlist.get(1);
		Clickon(ele1);

		Thread.sleep(2500);
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Port 2")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Port ID : Port 2");

		Clickon(ele2);
		Thread.sleep(2500);
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Port 4")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Port ID : Port 4");

		List<WebElement> trafficlist = GetWebElements(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Traffic Direction"));
		for (WebElement ele : trafficlist) {

			Clickon(ele);
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Voice calls both ways")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Traffic Direction : Voice calls both ways");

		}
		List<WebElement> cabinetlist = GetWebElements(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Cabinet Type"));
		for (WebElement ele : cabinetlist) {
			Clickon(ele);
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Existing Colt Cabinet")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet  Type : Existing Colt Cabinet");
		}

		List<WebElement> cabinetIDlist = GetWebElements(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet_ID"));
		for (WebElement ele : cabinetIDlist) {
			SendKeys(ele, "Test");
		}
		List<WebElement> selflist = GetWebElements(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf_ID"));
		for (WebElement ele : selflist) {
			ClearSendKeys(ele, "Test");
		}
	}

	public void siteSearchEnterAndSelection(List<String> address)
			throws InterruptedException, IOException, DocumentException {

		String street = address.get(0);
		String country = address.get(1);
		String city = address.get(2);
		String postcode = address.get(3);
		String premises = address.get(4);

		SendKeys(getwebelement(xml3.getlocator("//locators/StreetNamerfs")), street);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name : ");

		Clickon(getwebelement(xml3.getlocator("//locators/Country")));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", country)));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter country : " + country);

		SendKeys(getwebelement(xml3.getlocator("//locators/City")), city);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City :" + city);
		SendKeys(getwebelement(xml3.getlocator("//locators/PostalCode")), postcode);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code : " + postcode);
		SendKeys(getwebelement(xml3.getlocator("//locators/Premises")), premises);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises : " + premises);
		Clickon(getwebelement(xml3.getlocator("//locators/Search")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();
	}

	public void middleApplet(Object InputData[]) throws InterruptedException, DocumentException, IOException {
		System.out.println("middle applet start");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml2.getlocator("//locators/SupportStarDate"));
		// Clear(getwebelement(xml2.getlocator("//locators/SupportStarDate")));
		// getwebelement(xml2.getlocator("//locators/SupportStarDate")).clear();
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");
		String TempDate = CurrentDate();
		Clickon(getwebelement(xml2.getlocator("//locators/SupportStarDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupportStarDate : " + TempDate);
		SendKeys(getwebelement(xml2.getlocator("//locators/SupportStarDate")), TempDate);

		System.out.println("middle applet start1");
		WaitforElementtobeclickable(xml2.getlocator("//locators/SupprtEndDate"));
		// Clear(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));

		// getwebelement(xml2.getlocator("//locators/SupprtEndDate")).clear();
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");

		TempDate = FutureDate(60);
		Clickon(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupprtEndDate");
		System.out.println("the End date value is" + TempDate);

		SendKeys(getwebelement(xml2.getlocator("//locators/SupprtEndDate")), TempDate);

		Clickon(getwebelement(xml2.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Save Button");
		Thread.sleep(5000);
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, "Middle Applet End");

	}

	public void MiddleAppDropdown(String DropdownName, String DropdownValue)
			throws InterruptedException, DocumentException {
		String lct = xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", DropdownName);
		String lctValue = xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", DropdownValue);

		WaitforElementtobeclickable(lct);
		Clickon(getwebelement(lct));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  : " + DropdownName);
		Thread.sleep(1500);
		waitForpageload();
		waitforPagetobeenable();
		Clickon(getwebelement(lctValue));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select " + DropdownName + " value : " + DropdownValue);
	}

	public void MiddleAppTextBox(String TextBoxName, String InputText)
			throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();

		String lct = xml2.getlocator("//locators/MidlleAppText").replace("Value", TextBoxName);
		WaitforElementtobeclickable(lct);
		ClearSendKeys(getwebelement(lct), InputText);
		SendkeaboardKeys(getwebelement(lct), Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter in (" + TextBoxName + ") as  : " + InputText);
	}

	public void WaveLineMidleApplet(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")
				|| InputData[9].toString().equalsIgnoreCase("Wave")) {
			// 888
			// A End Resilience Option
			MiddleAppDropdown("A End Resilience Option", InputData[47].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[48].toString());
			MiddleAppDropdown("Coverage", InputData[49].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[50].toString());
			MiddleAppDropdown("Bandwidth Type", InputData[51].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[52].toString());
			if (InputData[9].toString().equalsIgnoreCase("Wave")) {
				// OSS Platform Flag
				MiddleAppDropdown("Sub Sea Cable System (Protected Path)", InputData[107].toString());

				MiddleAppDropdown("Sub Sea Cable System (Worker Path)", InputData[108].toString());

			}
		}
		ClickHereSave();
	}

	public void WaveLineSiteEntries(Object[] InputData) throws InterruptedException, DocumentException, IOException 
	{
		waitForpageload();
		waitforPagetobeenable();

		// A End And B End Site Entries Start
		// Access Type Common
		AEndDropdownSelection("Access Type", InputData[53].toString());
		AEndDropdownSelection("Access Technology", InputData[54].toString());

		BEndDropdownSelection("Access Type", InputData[80].toString());
		BEndDropdownSelection("Access Technology", InputData[81].toString());

		if (InputData[32].toString().equalsIgnoreCase("Offnet")) {
			AEndDropdownSelection("Third Party Access Provider", InputData[55].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[62].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[65].toString());

			BEndDropdownSelection("Third Party Access Provider", InputData[82].toString());
			BEndDropdownSelection("DSL SLA Class", InputData[89].toString());
			BEndDropdownSelection("Third Party SLA Tier", InputData[92].toString());

			waitForpageload();
			waitforPagetobeenable();
		}
		AEndDropdownSelection("Building Type", InputData[56].toString());
		AEndDropdownSelection("Customer Site Pop Status", InputData[57].toString());
		AEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
		AEndInputEnter("BCP Reference", Integer.toString(GetRandomNumber(1000)));
		AEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));

		BEndDropdownSelection("Building Type", InputData[83].toString());
		BEndDropdownSelection("Customer Site Pop Status", InputData[84].toString());
		BEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
		BEndInputEnter("BCP Reference", Integer.toString(GetRandomNumber(1000)));
		BEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));

		// A End And B End Site Entries Ended
		waitForpageload();
		waitforPagetobeenable();
		savePage();
		waitForpageload();
		waitforPagetobeenable();

		// A End And B End Installation Entries Start
		AEndDropdownSelection("Install Time", InputData[66].toString());
		// AEndInputEnter("Access Notification Period",InputData[123].toString());
		// AEndInputEnter("Access Time Window",InputData[125].toString());
		BEndDropdownSelection("Install Time", InputData[93].toString());
		// BEndInputEnter("Access Notification Period",InputData[124].toString());
		// BEndInputEnter("Access Time Window",InputData[126].toString());
		// A End And B End Installation Entries Ended

		Save();
		waitForpageload();
		waitforPagetobeenable();

		// A End And B End CPE Information Entries Start
		AEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Cabinet Type", InputData[70].toString());
		AEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));

		BEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		BEndDropdownSelection("Cabinet Type", InputData[97].toString());
		BEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));

		// A End And B End CPE Information Entries Ended
		savePage();
		waitForpageload();
		waitforPagetobeenable();

		// A End And B End CPE Access Port Entries Start
		AEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));

		AEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Presentation Interface", InputData[75].toString());
		AEndDropdownSelection("Connector Type", InputData[76].toString());
		AEndDropdownSelection("Fibre Type", InputData[77].toString());
		AEndDropdownSelection("Port Role", InputData[78].toString());

		BEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
		BEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
		BEndDropdownSelection("Presentation Interface", InputData[102].toString());
		BEndDropdownSelection("Connector Type", InputData[103].toString());
		BEndDropdownSelection("Fibre Type", InputData[104].toString());
		BEndDropdownSelection("Port Role", InputData[105].toString());

		// A End And B End CPE Access Port Entries End
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
		// GetReference();
		// Save();

	}

	public void ModTechModCommWaveAndLine(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")
				|| InputData[9].toString().equalsIgnoreCase("Wave")) {
			ClickHereSave();
			// A End And B End Installation Entries Start

			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			// A End And B End Installation Entries Ended
			if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")
					&& InputData[InputData.length - 1].toString().contains("Tech")) {
				// Update bandwidth with new Values
				// Service BandWidth
				MiddleAppDropdown("Service Bandwidth", InputData[50].toString());

			}
			if (InputData[9].toString().equalsIgnoreCase("Wave")
					&& InputData[InputData.length - 1].toString().contains("Tech")) {
				MiddleAppDropdown("A End Resilience Option", InputData[48].toString());

			}
		}
	}

	public void AEndDropdownSelection(String DropdownName, String DropdownValue)
			throws InterruptedException, DocumentException {

		String eleLoct = xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", DropdownName);
		System.out.println(eleLoct);
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		eleLoct = xml.getlocator("//locators/R4/SiteABSelection").replace("Value", DropdownValue);
		System.out.println(eleLoct);
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Dropdwon (" + DropdownName + ") Select: " + DropdownValue);
	}

	public void BEndDropdownSelection(String DropdownName, String DropdownValue)
			throws InterruptedException, DocumentException {
		String eleLoct = xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", DropdownName);
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));

		eleLoct = xml.getlocator("//locators/R4/SiteABSelection").replace("Value", DropdownValue);
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Dropdwon (" + DropdownName + ") Select: " + DropdownValue);
	}

	public void AEndInputEnter(String TextBoxName, String InputText)
			throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)), InputText);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)),
				Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter in (" + InputText + ") as  : " + InputText);

	}

	public void BEndInputEnter(String TextBoxName, String InputText)
			throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)), InputText);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)),
				Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter in (" + InputText + ") as  : " + InputText);

	}

	public void DarkFibreSiteEntries(Object[] InputData) throws Exception 
	{
		AEndDropdownSelection("Access Type", InputData[51].toString());
		AEndDropdownSelection("Building Type", InputData[52].toString());
		
		BEndDropdownSelection("Access Type", InputData[55].toString());
		BEndDropdownSelection("Building Type", InputData[56].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[67].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[68].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[69].toString());
			AEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
			
			BEndDropdownSelection("Third Party Access Provider", InputData[70].toString());
			BEndDropdownSelection("DSL SLA Class", InputData[71].toString());
			BEndDropdownSelection("Third Party SLA Tier", InputData[72].toString());
			BEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
		} 
		AEndDropdownSelection("Customer Site Pop Status", InputData[53].toString());
		AEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));
		
		BEndDropdownSelection("Customer Site Pop Status", InputData[57].toString());
		BEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));
		
		AEndDropdownSelection("Install Time", InputData[59].toString());
		BEndDropdownSelection("Install Time", InputData[60].toString());

		// Termination Information
		AEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Cabinet Type", InputData[61].toString());
		AEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));
		BEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		BEndDropdownSelection("Cabinet Type", InputData[62].toString());
		BEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));
		// Access Port
		AEndDropdownSelection("Connector Type", InputData[63].toString());
		AEndDropdownSelection("Fibre Type", InputData[64].toString());
		AEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
		AEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
		BEndDropdownSelection("Connector Type", InputData[65].toString());
		BEndDropdownSelection("Fibre Type", InputData[66].toString());
		BEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
		BEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void EthernetAccessSiteEntries(Object[] InputData) throws Exception {

		AEndDropdownSelection("Access Type", InputData[51].toString());
		AEndDropdownSelection("Access Technology", InputData[52].toString());
		AEndDropdownSelection("Building Type", InputData[53].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet"))
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[60].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[61].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[62].toString());
			AEndInputEnter("3rd Party Connection Reference", "KHY"+Integer.toString(GetRandomNumber(1000)));
		} 
		AEndDropdownSelection("Customer Site Pop Status", InputData[54].toString());	
		AEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));	
		AEndDropdownSelection("Install Time", InputData[55].toString());
		// Termination Information	
		AEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Cabinet Type", InputData[56].toString());	
		AEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));
		// Access Port
		AEndDropdownSelection("Presentation Interface", InputData[57].toString());
		AEndDropdownSelection("Connector Type", InputData[58].toString());
		AEndDropdownSelection("Fibre Type", InputData[59].toString());	
		AEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));	
		AEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void UltraLowLatecnySiteEntries(Object[] InputData) throws Exception 
	{
		AEndDropdownSelection("Access Type", InputData[52].toString());
		AEndDropdownSelection("Access Technology", InputData[53].toString());
		AEndDropdownSelection("Building Type", InputData[54].toString());
		BEndDropdownSelection("Access Type", InputData[56].toString());
		BEndDropdownSelection("Access Technology", InputData[57].toString());
		BEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[70].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[71].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[72].toString());
			AEndInputEnter("3rd Party Connection Reference", GetRandomNumberString(1000));
			AEndDropdownSelection("Site Type", "Customer");
			
			BEndDropdownSelection("Third Party Access Provider", InputData[73].toString());
			BEndDropdownSelection("DSL SLA Class", InputData[74].toString());
			BEndDropdownSelection("Third Party SLA Tier", InputData[75].toString());
			BEndInputEnter("3rd Party Connection Reference", GetRandomNumberString(1000));
			BEndDropdownSelection("Site Type", "Customer");
		} 
		AEndDropdownSelection("Customer Site Pop Status", InputData[55].toString());
		AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
		BEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
		BEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
		// Install Time
		AEndDropdownSelection("Install Time", InputData[60].toString());
		BEndDropdownSelection("Install Time", InputData[61].toString());
		// Termination Information
		AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
		AEndDropdownSelection("Cabinet Type", InputData[62].toString());
		AEndInputEnter("Shelf ID", GetRandomNumberString(1000));
		BEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
		BEndDropdownSelection("Cabinet Type", InputData[63].toString());
		BEndInputEnter("Shelf ID", GetRandomNumberString(1000));
		// Access Port
		AEndDropdownSelection("Presentation Interface", InputData[64].toString());
		AEndDropdownSelection("Connector Type", InputData[65].toString());
		AEndDropdownSelection("Fibre Type", InputData[66].toString());
		AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
		AEndInputEnter("Slot ID", GetRandomNumberString(1000));
		BEndDropdownSelection("Presentation Interface", InputData[67].toString());
		BEndDropdownSelection("Connector Type", InputData[68].toString());
		BEndDropdownSelection("Fibre Type", InputData[69].toString());
		BEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
		BEndInputEnter("Slot ID", GetRandomNumberString(1000));
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void PrivateEthernetSiteEntries(Object[] InputData) throws Exception {
		
		AEndDropdownSelection("Access Type", InputData[52].toString());
		AEndDropdownSelection("Access Technology",InputData[68].toString());
		AEndDropdownSelection("Building Type", InputData[53].toString());
		BEndDropdownSelection("Access Type", InputData[55].toString());
		BEndDropdownSelection("Access Technology",InputData[69].toString());
		BEndDropdownSelection("Building Type", InputData[56].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[70].toString());
			BEndDropdownSelection("Third Party Access Provider", InputData[71].toString());
			AEndDropdownSelection("DSL SLA Class",InputData[72].toString());
			BEndDropdownSelection("DSL SLA Class",InputData[73].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[74].toString());
			BEndDropdownSelection("Third Party SLA Tier", InputData[75].toString());
			AEndInputEnter("3rd Party Connection Reference", "AYH"+GetRandomNumberString(1000));
			BEndInputEnter("3rd Party Connection Reference", "BHK"+GetRandomNumberString(1000));
		} 
			
			AEndDropdownSelection("Customer Site Pop Status", InputData[54].toString());
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
			BEndDropdownSelection("Customer Site Pop Status", InputData[57].toString());
			BEndInputEnter("Site Name Alias", GetRandomNumberString(1000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[58].toString());
			BEndDropdownSelection("Install Time", InputData[59].toString());

			// Termination Information
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));
			BEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			BEndDropdownSelection("Cabinet Type", InputData[61].toString());
			BEndInputEnter("Shelf ID", GetRandomNumberString(1000));

			// Access Port
			AEndDropdownSelection("Presentation Interface", InputData[62].toString());
			AEndDropdownSelection("Connector Type", InputData[63].toString());
			AEndDropdownSelection("Fibre Type", InputData[64].toString());
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndInputEnter("Slot ID", GetRandomNumberString(1000));
			BEndDropdownSelection("Presentation Interface", InputData[65].toString());
			BEndDropdownSelection("Connector Type", InputData[66].toString());
			BEndDropdownSelection("Fibre Type", InputData[67].toString());
			BEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			BEndInputEnter("Slot ID", GetRandomNumberString(1000));
	
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void DCAEthernetSiteEntries(Object[] InputData) throws Exception {
		
		AEndDropdownSelection("Access Type", InputData[54].toString());
		AEndDropdownSelection("Access Technology", InputData[55].toString());
		AEndDropdownSelection("Building Type", InputData[56].toString());
		BEndDropdownSelection("Access Technology", InputData[59].toString());
		BEndDropdownSelection("Building Type", InputData[60].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[77].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[78].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[79].toString());
			AEndInputEnter("3rd Party Connection Reference", GetRandomNumberString(1000));
			
		} 
			AEndDropdownSelection("Customer Site Pop Status", InputData[57].toString());
			AEndInputEnter("Site Name Alias",  GetRandomNumberString(1000));
			AEndDropdownSelection("Site Type", InputData[58].toString());
			BEndDropdownSelection("Customer Site Pop Status", InputData[61].toString());
			BEndInputEnter("Site Name Alias",  GetRandomNumberString(1000));
			// Install Time
			AEndDropdownSelection("Install Time", InputData[62].toString());
			BEndDropdownSelection("Install Time", InputData[63].toString());
			// Termination Information
			AEndInputEnter("Cabinet ID",  GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[64].toString());
			AEndInputEnter("Shelf ID",  GetRandomNumberString(1000));
			BEndInputEnter("Cabinet ID",  GetRandomNumberString(1000));
			BEndDropdownSelection("Cabinet Type", InputData[65].toString());
			BEndInputEnter("Shelf ID",  GetRandomNumberString(1000));
			// Access Port
			AEndDropdownSelection("Presentation Interface", InputData[66].toString());
			AEndDropdownSelection("Connector Type", InputData[67].toString());
			AEndDropdownSelection("Fibre Type", InputData[68].toString());
			AEndInputEnter("Physical Port ID",  GetRandomNumberString(1000));
			AEndInputEnter("Slot ID",  GetRandomNumberString(1000));
			BEndDropdownSelection("Presentation Interface", InputData[69].toString());
			BEndDropdownSelection("Connector Type", InputData[70].toString());
			BEndDropdownSelection("Fibre Type", InputData[71].toString());
			BEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			BEndInputEnter("Slot ID",  GetRandomNumberString(1000));
			AEndInputEnter("Ethertype", "NA");
			AEndInputEnter("VLAN Tag ID", "NA");
			BEndInputEnter("Ethertype", "NA");
			BEndInputEnter("VLAN Tag ID", "NA");
			BEndInputEnter("AWS Account Number",  GetRandomNumberString(1000) + Integer.toString(rnd.nextInt(1000))+ Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("CSP NNI Reference Primary",Integer.toString(rnd.nextInt(1000)) + Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("CSP NNI Reference Secondary",Integer.toString(rnd.nextInt(1000)) + Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("CSP PoP (Primary)", "NA");
			BEndInputEnter("CSP Region", "NA");
			BEndInputEnter("Connection Alias", "NA");
			BEndInputEnter("S VLAN Tag ID", Integer.toString(rnd.nextInt(1000)) + Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("Service Key", Integer.toString(rnd.nextInt(1000)) + Integer.toString(rnd.nextInt(1000)));
			BEndDropdownSelection("CSP Bandwidth", InputData[72].toString());
			BEndDropdownSelection("CSP Name", InputData[73].toString());
			BEndDropdownSelection("CSP Port Type", InputData[74].toString());
			BEndDropdownSelection("NAT Range Required", InputData[75].toString());
			BEndDropdownSelection("Routing Domain", InputData[76].toString());

		waitForpageload();
		waitforPagetobeenable();
	}

	public void PrivateWaveServiceSiteEntries(Object[] InputData) throws Exception {
		
		AEndDropdownSelection("Access Type", InputData[53].toString());
		AEndDropdownSelection("Building Type", InputData[54].toString());
		BEndDropdownSelection("Access Type", InputData[56].toString());
		BEndDropdownSelection("Building Type", InputData[57].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet"))
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[67].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[69].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[71].toString());
			AEndInputEnter("3rd Party Connection Reference", "AHY-" + Integer.toString(rnd.nextInt(1000)));
			BEndDropdownSelection("Third Party Access Provider", InputData[68].toString());
			BEndDropdownSelection("DSL SLA Class", InputData[70].toString());
			BEndDropdownSelection("Third Party SLA Tier", InputData[72].toString());
			BEndInputEnter("3rd Party Connection Reference", "HFG-" + Integer.toString(rnd.nextInt(1000)));
			
		}
			AEndDropdownSelection("Customer Site Pop Status", InputData[55].toString());
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
			AEndInputEnter("Node Site Name", "AHYG-" + GetRandomNumberString(1000));
			AEndInputEnter("Node Service ID","NODE" + GetRandomNumberString(10000));
			BEndDropdownSelection("Customer Site Pop Status", InputData[58].toString());
			BEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
			BEndInputEnter("Node Site Name", "AHYG-" + GetRandomNumberString(1000));
			BEndInputEnter("Node Service ID","NODE" + GetRandomNumberString(10000));
			// Install Time
			AEndDropdownSelection("Install Time", InputData[59].toString());
			BEndDropdownSelection("Install Time", InputData[60].toString());
			// Termination Information
			AEndInputEnter("Cabinet ID", GetRandomNumberString(10000));
			AEndDropdownSelection("Cabinet Type", InputData[61].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(10000));
			BEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			BEndDropdownSelection("Cabinet Type", InputData[62].toString());
			BEndInputEnter("Shelf ID", GetRandomNumberString(1000));
			// Access Port
			AEndDropdownSelection("Connector Type", InputData[63].toString());
			AEndDropdownSelection("Fibre Type", InputData[64].toString());
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndInputEnter("Slot ID",GetRandomNumberString(1000));
			BEndDropdownSelection("Connector Type", InputData[65].toString());
			BEndDropdownSelection("Fibre Type", InputData[66].toString());
			BEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			BEndInputEnter("Slot ID", GetRandomNumberString(1000));
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void PrivateWaveNodeSiteEntries(Object[] InputData) throws Exception 
	{
		AEndDropdownSelection("Access Type", InputData[49].toString());
		AEndDropdownSelection("Access Technology", InputData[50].toString());
		AEndDropdownSelection("Building Type", InputData[51].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[55].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[56].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[57].toString());
			AEndInputEnter("3rd Party Connection Reference", "FGT"+GetRandomNumberString(1000));
		}
			AEndDropdownSelection("Customer Site Pop Status", InputData[52].toString());
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
			AEndInputEnter("Node Site Name", "NODE-" + GetRandomNumberString(1000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[53].toString());
			// Termination Information
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[54].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));
		

		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void EthernetSpokeSiteEntries(Object[] InputData) throws Exception {
		Random rnd = new Random();
		int rnd_int = 0;
		if (InputData[32].toString().equalsIgnoreCase("offnet")) {
			//
		} else {
			BEndDropdownSelection("Access Type", InputData[51].toString());
			BEndDropdownSelection("Access Technology", InputData[52].toString());
			BEndDropdownSelection("Building Type", InputData[53].toString());
			BEndDropdownSelection("Customer Site Pop Status", InputData[54].toString());
			BEndInputEnter("3rd Party Connection Reference", Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("BCP Reference", Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("Site Name Alias", Integer.toString(rnd.nextInt(1000)));

			// CPE Information
			rnd_int = rnd.nextInt(1000);
			BEndInputEnter("Cabinet ID", Integer.toString(rnd.nextInt(1000)));
			BEndDropdownSelection("Cabinet Type", InputData[55].toString());
			BEndInputEnter("Shelf ID", Integer.toString(rnd.nextInt(1000)));

			BEndInputEnter("Slot ID", Integer.toString(rnd.nextInt(1000)));
			BEndInputEnter("Physical Port ID", Integer.toString(rnd.nextInt(1000)));
			BEndDropdownSelection("Presentation Interface", InputData[56].toString());
			BEndDropdownSelection("Connector Type", InputData[57].toString());
			BEndDropdownSelection("Fibre Type", InputData[58].toString());

			// Install Time
			BEndDropdownSelection("Install Time", InputData[59].toString());
			BEndInputEnter("VLAN Tag ID", Integer.toString(rnd.nextInt(1000)));

		}
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void EthernetHubSiteEntries(Object[] InputData) throws Exception {
		Random rnd = new Random();
		int rnd_int = 0;
		if (InputData[32].toString().equalsIgnoreCase("offnet")) {
			//
		} else {
			AEndDropdownSelection("Access Type", InputData[51].toString());
			AEndDropdownSelection("Access Technology", InputData[52].toString());
			AEndDropdownSelection("Building Type", InputData[53].toString());
			AEndDropdownSelection("Customer Site Pop Status", InputData[54].toString());
			AEndInputEnter("3rd Party Connection Reference", Integer.toString(rnd.nextInt(1000)));
			AEndInputEnter("BCP Reference", Integer.toString(rnd.nextInt(1000)));
			AEndInputEnter("Site Name Alias", Integer.toString(rnd.nextInt(1000)));

			// CPE Information
			rnd_int = rnd.nextInt(1000);
			AEndInputEnter("Cabinet ID", Integer.toString(rnd.nextInt(1000)));
			AEndDropdownSelection("Cabinet Type", InputData[55].toString());
			AEndInputEnter("Shelf ID", Integer.toString(rnd.nextInt(1000)));

			AEndInputEnter("Slot ID", Integer.toString(rnd.nextInt(1000)));
			AEndInputEnter("Physical Port ID", Integer.toString(rnd.nextInt(1000)));
			AEndDropdownSelection("Presentation Interface", InputData[56].toString());
			AEndDropdownSelection("Connector Type", InputData[57].toString());
			AEndDropdownSelection("Fibre Type", InputData[58].toString());

			// Install Time
			AEndDropdownSelection("Install Time", InputData[59].toString());

		}
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void enterMandatoryDetailsInMiddleApplet(Object[] InputData) throws Exception 
	{
		switch (InputData[9].toString()) 
		{
		case "Ethernet Spoke": {
			System.out.println("Enter Gurdian Middle Applet");
			MiddleAppDropdown("Coverage", InputData[47].toString());
			MiddleAppDropdown("Resilience Option", InputData[48].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());

			Save();
			/*
			 * Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").
			 * replace("Value", "HUB Service Id")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/IPVPNSite/HubTableIDSelect"));
			 * Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/HubTableIDSelect")
			 * )); ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step: Click on Select HubID"); Thread.sleep(3000);
			 * Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/HubTableOK")));
			 * Thread.sleep(3000);
			 * 
			 * ClickHereSave();
			 */
			addSiteBDetails(InputData);
			EthernetSpokeSiteEntries(InputData);

			break;
		}

		case "IP Guardian": {
			System.out.println("Enter Gurdian Middle Applet");
			MiddleAppTextBox("Alerting Notification Email Address", InputData[47].toString());
			MiddleAppDropdown("Auto Mitigation", InputData[48].toString());
			MiddleAppTextBox("Customer DNS Resolvers", InputData[49].toString());
			MiddleAppDropdown("IP Guardian Variant", InputData[50].toString());
			MiddleAppDropdown("Include Colt Technical Contact in Test Calls", InputData[51].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[52].toString());
			MiddleAppTextBox("Test Window", InputData[53].toString());
			Save();
			Thread.sleep(8000);

			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			Thread.sleep(3000);

			PopupInput("Circuit IP Address", InputData[54].toString());
			PopupInput("IP Range", InputData[55].toString());
			closePopUp();

			ClickHereSave();

			break;
		}
		case "IP Domain": {
			System.out.println("Enter Middle Applet");
			Thread.sleep(2000);
			MiddleAppTextBox("Number of Domains", InputData[47].toString());
			// Moveon(getwebelement("//a[text()='Click to Show Full info']"));
			// Moveon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			System.out.println("Move Performed");
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			Thread.sleep(3000);

			SendKeys(getwebelement(xml.getlocator("//locators/Domainname")), InputData[48].toString());
			Clickon(getwebelement(xml.getlocator("//locators/Domainordertype")));
			Thread.sleep(4000);
			Clickon(getwebelement(xml.getlocator("//locators/DomainordertypeData")));
			// WaitforElementtobeclickable(xml.getlocator("//locators/DNStype"));
			Clickon(getwebelement(xml.getlocator("//locators/DNStype")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/DNSTypeInput")));

			Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/Crossbutton")));
			Thread.sleep(6000);
			ClickHereSave();

			System.out.println("Middle Complete");
			break;
		}
		case "Ethernet Hub": {
			MiddleAppDropdown("Resilience Option", InputData[47].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[48].toString());
			MiddleAppDropdown("Bandwidth Type", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());

			ClickHereSave();
			addSiteADetails(InputData);
			EthernetHubSiteEntries(InputData);
			// OperationalAttributeDarkFibre();
			ClickHereSave();
			break;
		}
		case "Private Wave Node": {
			MiddleAppDropdown("OSS Platform Flag", InputData[47].toString());
			MiddleAppDropdown("Network Topology", InputData[48].toString());
			ClickHereSave();
			addSiteADetails(InputData);
			PrivateWaveNodeSiteEntries(InputData);
			//OperationalAttributeDarkFibre();
			ClickHereSave();
			break;
		}
		case "Private Wave Service": {
			// MiddleAppDropdown("Coverage",InputData[47].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[47].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[48].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());
			MiddleAppDropdown("Network Topology", InputData[51].toString());
			MiddleAppDropdown("Service Type", InputData[52].toString());
			ShowfullInfo();
			DiversityCircuitEntry(InputData);
			Save();
			addSiteADetails(InputData);
			addSiteBDetails(InputData);
			PrivateWaveServiceSiteEntries(InputData);
		//	OperationalAttributeDarkFibre();
			ClickHereSave();
			break;
		}
		case "DCA Ethernet": {
			MiddleAppDropdown("Coverage", InputData[47].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[48].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[50].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[51].toString());
			MiddleAppDropdown("Bandwidth Type", InputData[52].toString());
			MiddleAppDropdown("Topology", InputData[53].toString());
			ShowfullInfo();
			DiversityCircuitEntry(InputData);
			Save();
			addSiteADetails(InputData);
			addSiteBDetails(InputData);
			DCAEthernetSiteEntries(InputData);
			ClickHereSave();
			waitforPagetobeenable();
			if (InputData[32].toString().equalsIgnoreCase("offnet")) 
			{
			SiteASettingClick();
			CSPInterconnectSiteAEntry();
			savePage();
			waitforPagetobeenable();
			}
			SiteBSettingClick();
			CSPInterconnectSiteBEntry();
			ClickHereSave();
			break;
		}
		case "Private Ethernet": {
			MiddleAppDropdown("Service Bandwidth", InputData[47].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[48].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());
			MiddleAppDropdown("Bandwidth Type", InputData[51].toString());

			// publicEthernetMiddleAplet(InputData);
			ShowfullInfo();
			// publicEthernetEntry(InputData);
			DiversityCircuitEntry(InputData);
			ClickHereSave();
			addSiteADetails(InputData);
			addSiteBDetails(InputData);
			PrivateEthernetSiteEntries(InputData);
			ClickHereSave();
			break;
		}
		case "Ultra Low Latency": {
			savePage();
			waitForpageload();
			waitforPagetobeenable();
			alertPopUp();

			// Midle App
			MiddleAppDropdown("Coverage", InputData[47].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[48].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[50].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[51].toString());
			ShowfullInfo();
			DiversityCircuitEntry(InputData);
			Save();

			addSiteADetails(InputData);
			addSiteBDetails(InputData);
			UltraLowLatecnySiteEntries(InputData);
			//OperationalAttributeDarkFibre();
			ClickHereSave();

			break;
		}
		case "Ethernet Access": {
			// Midle App Start
			MiddleAppDropdown("Coverage", InputData[47].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[48].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());
			ShowfullInfo();
			DiversityCircuitEntry(InputData);
			Save();
			addSiteADetails(InputData);
			EthernetAccessSiteEntries(InputData);
			//OperationalAttributeDarkFibre();
			ClickHereSave();
			break;
		}
		case "CPE Solutions Service": {
			IPCPESolutionService(InputData);
			break;
		}
		case "Dark Fibre": {
			// Midle App
			waitForpageload();
			waitforPagetobeenable();
			alertPopUp();
			MiddleAppDropdown("Coverage", InputData[47].toString());
			MiddleAppDropdown("A End Resilience Option", InputData[48].toString());
			MiddleAppDropdown("B End Resilience Option", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());
			// OperationalAttributeUltra(InputData);

			alertPopUp();
			ClickHereSave();
			waitforPagetobeenable();
			ShowfullInfo();
			DiversityCircuitEntry(InputData);
			Save();
			// End Midle App
			addSiteADetails(InputData);// Updated With Excel
			addSiteBDetails(InputData);// Updated With Excel
			DarkFibreSiteEntries(InputData);
			// OperationalAttributeUltra(InputData);
			//OperationalAttributeDarkFibre();
			ClickHereSave();
			break;
		}
		case "Ethernet Line": {
			// Updated
			WaveLineMidleApplet(InputData);// Update With Excel
			addSiteADetails(InputData);// Updated With Excel
			addSiteBDetails(InputData);// Updated With Excel
			WaveLineSiteEntries(InputData);
			break;
		}
		case "Wave": {
			// Updated
			WaveLineMidleApplet(InputData);
			addSiteADetails(InputData);// added new
			addSiteBDetails(InputData);// added new
			WaveLineSiteEntries(InputData);
			
			
			break;
		}

		case "CPE Solutions Site": {
			CPSolutionSite(InputData);
			break;
		}
		case "Cloud Unified Communications": {
			// Updated
			middleApplet(InputData);
			// R5DataCoverage(InputData);
			break;
		}
		case "Professional Services": {
			// Updated
			middleApplet(InputData);
			// R5DataCoverage(InputData);
			break;
		}
		case "IP Voice Solutions": {
			// Updated
			middleApplet(InputData);
			// R5DataCoverage(InputData);
			break;
		}
		case "Managed Dedicated Firewall": { // Added by Aman
			alertPopUp();
			middleAppletManagedDedicatedFirewall(InputData);
			break;
		}
		/* Start Of SIP Gauri Update Trunking */

		case "SIP Trunking": {
			// 8888
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter SIP Trunking information Middle Applet");
			waitForpageload();
			waitforPagetobeenable();
			MiddleAppDropdown("Voice Service Country", InputData[47].toString());
			MiddleAppTextBox("Call Admission Control (Number of Channels)", InputData[48].toString());
			Thread.sleep(5000);
			MiddleAppTextBox("Number of Signalling Trunks", InputData[49].toString());
			MiddleAppDropdown("Egress Number Format", InputData[50].toString());
			MiddleAppDropdown("Invalid CLI treatment", InputData[51].toString());
			MiddleAppTextBox("Total Number of DDIs", InputData[52].toString());
			MiddleAppDropdown("Incoming DDI Digits", InputData[53].toString());
			MiddleAppTextBox("Voice Configuration Reference", InputData[54].toString());
			MiddleAppDropdown("Routing Sequence", InputData[55].toString());
			MiddleAppDropdown("First Codec", InputData[56].toString());
			Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Show More")));
			Thread.sleep(3000);
			MiddleAppDropdown("Second Codec", InputData[57].toString());
			MiddleAppDropdown("Third Codec", InputData[58].toString());
			MiddleAppDropdown("Fourth Codec", InputData[59].toString());
			MiddleAppDropdown("Fifth Codec", InputData[60].toString());
			MiddleAppDropdown("Sixth Codec", InputData[61].toString());
			Thread.sleep(5000);
			waitforPagetobeenable();
			Save();
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full infor");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			// Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[47].toString())));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[62].toString())));
			Thread.sleep(5000);

			closePopUp();

			Save();
			Thread.sleep(10000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " SIP Trunking Middle Applet Success ");

			TrunkEntries(InputData[9].toString(), InputData[63].toString());
			// CustomerIPBoxEntries();

			OpenTab("Customer IP PBX");
			waitForpageload();
			waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/SIP/CustomerMenu").replace("Value", "New"));
			Clickon(getwebelement(xml.getlocator("//locators/SIP/CustomerMenu").replace("Value", "New")));

			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(5000);
			String Ok = xml.getlocator("//locators/SIP/Ok");// "//div[contains(@style,'block') and
															// contains(@class,'ui-draggable
															// ui-resizable')]//button[contains(@title,'OK')]";
			String loc = xml.getlocator("//locators/SIP/loc");// "//table[@id='s_2_l']//tr[@id='1']/td[ind]";
			String span = xml.getlocator("//locators/SIP/Span");// "//table[@id='s_2_l']//tr[@id='1']/td[ind]//span";
			String input = xml.getlocator("//locators/SIP/Input");// "//table[@id='s_2_l']//tr[@id='1']/td[ind]//input";

			// IP PBX Alias Name
			int Index = CustomerIPPBXIndex("IP PBX Alias Name");
			System.out.println(Index);
			String newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "test1");

			// IP PBX MODEL
			Index = CustomerIPPBXIndex("IP PBX MODEL");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "SWE Lite7.0");

			// IP PBX Vendor
			Index = CustomerIPPBXIndex("IP PBX Vendor");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "Sonus");

			// Software Version
			Index = CustomerIPPBXIndex("Software Version");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = input.replaceAll("ind", Integer.toString(Index));
			ClearSendKeys(getwebelement(newloc), "1");

			// PBX Technical Contact
			Index = CustomerIPPBXIndex("PBX Technical Contact");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			waitForpageload();
			Clickon(getwebelement(Ok));

			// Address UID
			Index = CustomerIPPBXIndex("Address UID");
			System.out.println(Index);
			newloc = loc.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));
			newloc = span.replaceAll("ind", Integer.toString(Index));
			Clickon(getwebelement(newloc));

			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPPBXAddressUIDStreetName"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDStreetName")));
			// Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDStreetName")),
					InputData[37].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Street Name ");
			Thread.sleep(5000);

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")));
			// Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")),
					InputData[38].toString());
			// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCountryName")),
			// Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Country");
			Thread.sleep(5000);

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")));
			// Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDCityName")), InputData[39].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter City");
			Thread.sleep(3000);

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")));
			// Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPostalCode")),
					InputData[40].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Postal Code");
			Thread.sleep(3000);

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")));
			// Thread.sleep(2000);
			SendKeys(getwebelement(xml.getlocator("//locators/SIPIPPBXAddressUIDPremiseCode")),
					InputData[41].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, "Enter Premise Code");
			Thread.sleep(3000);

			// Click Address search button
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPAddressUIDSearchButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPAddressUIDSearchButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on Address Search button ");
			Thread.sleep(5000);

			// Select address record
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPSelectAddressRecord"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPSelectAddressRecord")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Select Address record ");
			Thread.sleep(5000);

			// Click Pick address button
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPPickAddressButtonForUID"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPPickAddressButtonForUID")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click Pick Address button ");
			Thread.sleep(10000);

			savePage(); // saving IP PBX details
			waitforPagetobeenable();
			// click on Voice Config tab to enter the IP PBX details
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPVoiceConfigTab"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigTab")));
			Thread.sleep(10000);
			// Selection of IP PBX in TRUNK --

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPVoiceConfigExpandSymbolIPPBXSelewction"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigExpandSymbolIPPBXSelewction")));
			Thread.sleep(5000);

			WaitforElementtobeclickable(xml.getlocator("//locators/TrunkIPPBX"));
			Clickon(getwebelement(xml.getlocator("//locators/TrunkIPPBX")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on IP PBX Lookup ");

			// AccessServiceID("Access Service ID","Service Id","010300126");

			Thread.sleep(5000);

			WaitforElementtobeclickable(xml.getlocator("//locators/SIPOKSelectionButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPOKSelectionButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, "Click on SIP Ok Selection Button");

			Thread.sleep(5000);
			//ABove code for Trunk including IPPBX code 

			// Other Tab in Voice Config

			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SIPOtherTab")));
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SIPOtherTab")));
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPOtherTab"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPOtherTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Other Tab");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPVoiceConfigOthersTabShowFullInfo"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigOthersTabShowFullInfo")));
			Thread.sleep(11000);
			// Select Colt DDI Ranges
			WaitforElementtobeclickable(xml.getlocator("//locators/SIPVoiceConfigOthersTabColtDDIRanges"));
			Clickon(getwebelement(xml.getlocator("//locators/SIPVoiceConfigOthersTabColtDDIRanges")));
			Thread.sleep(5000);

			PopupInput("Number of single Colt DDIs", "1");
			PopupInput("Number of 10 Colt DDI Ranges", "1");
			PopupInput("Number of 100 Colt DDI Ranges", "1");
			PopupInput("Number of 1000 Colt DDI Ranges", "1");

			closePopUp();
			savePage();
			waitforPagetobeenable();
			break;
		}
		case "Voice Line V": {
			waitForpageload();
			waitforPagetobeenable();
			savePage();
			waitForpageload();
			waitforPagetobeenable();
			Random rand = new Random();
			String DDI = Integer.toString(GetRandomNumber(100));

			MiddleAppDropdown("Voice Service Country", InputData[47].toString());
			MiddleAppTextBox("Number of Signalling Trunks", InputData[48].toString());
			MiddleAppDropdown("Egress Number Format", InputData[49].toString());
			MiddleAppDropdown("Topology", InputData[50].toString());
			MiddleAppDropdown("Invalid CLI treatment", InputData[51].toString());
			MiddleAppTextBox("Total Number of DDIs", DDI);

			MiddleAppDropdown("Incoming DDI Digits", InputData[52].toString());
			MiddleAppTextBox("Customer Default Number", Integer.toString(GetRandomNumber(10)));

			Save();
			// ClickHereSave();*/

			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full infor");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			// Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[47].toString())));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[53].toString())));
			Thread.sleep(5000);

			// Assert.assertTrue("SIP Trunking", true);
			ExtentTestManager.getTest().log(LogStatus.PASS, "VOICE V Middle Applet Success ");

			/*
			 * WaitforElementtobeclickable(xml.getlocator("//locators/PerformanceReporting")
			 * ); Clickon(getwebelement(xml.getlocator("//locators/PerformanceReporting")));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step: Click on Performance Reporting"); Thread.sleep(5000);
			 */
			WaitforElementtobeclickable(xml.getlocator("//locators/WLECInvoicing"));
			Clickon(getwebelement(xml.getlocator("//locators/WLECInvoicing")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on WLECInvoicing");
			Thread.sleep(5000);
			Thread.sleep(5000);
			closePopUp();
			Thread.sleep(5000);

			ClickHereSave();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on save");
			waitforPagetobeenable();
			Thread.sleep(30000);
			break;
		}
		case "Interconnect": {
			MiddleAppDropdown("Voice Service Country", InputData[47].toString());
			MiddleAppTextBox("Call Admission Control (Number of Channels)", InputData[48].toString());
			MiddleAppTextBox("Number of Signalling Trunks", InputData[49].toString());
			MiddleAppTextBox("Voice Configuration Reference", "Yes");
			MiddleAppDropdown("Incoming DDI Digits", InputData[50].toString());
			MiddleAppDropdown("Traffic Direction", InputData[51].toString());
			MiddleAppDropdown("First Codec", InputData[52].toString());
			Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Show More")));
			Thread.sleep(3000);
			MiddleAppDropdown("Second Codec", InputData[53].toString());
			MiddleAppDropdown("Third Codec", InputData[54].toString());
			MiddleAppDropdown("Fourth Codec", InputData[55].toString());
			MiddleAppDropdown("Fifth Codec", InputData[56].toString());
			MiddleAppDropdown("Sixth Codec", InputData[57].toString());
			Save();
			// ClickHereSave();

			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full infor");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[47].toString())));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[58].toString())));
			Thread.sleep(5000);

			closePopUp();

			Save();

			TrunkEntries(InputData[9].toString(), InputData[59].toString());
			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceTab"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voice feature tab");
			waitforPagetobeenable();
			waitForpageload();

			Clear(getwebelement(xml.getlocator("//locators/Language")));
			Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/Language")), "English");
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Select language");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Language")), Keys.TAB);
			Thread.sleep(3000);
			/*
			 * WaitforElementtobeclickable(xml.getlocator("//locators/Resillence"));
			 * Clickon(getwebelement(xml.getlocator("//locators/Resillence")));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step:Click on resillence dropdown");
			 * Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").
			 * replace("value", InputData[88].toString())));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step:Click on resillence value ");
			 */
			ClickHereSave();
			break;
		}
		case "IP Access": {
			MiddleAppTextBox("Capacity Check Reference", InputData[48].toString());
			MiddleAppDropdown("Type of Billing", InputData[49].toString());
			MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());
			MiddleAppDropdown("Router Type", InputData[51].toString());
			MiddleAppDropdown("Layer 3 Resilience", InputData[52].toString());
			MiddleAppDropdown("Service Bandwidth", InputData[53].toString());
			MiddleAppDropdown("Router Technology", InputData[64].toString());

			ClickHereSave();

			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
			Thread.sleep(3000);
			System.out.println(InputData[58].toString());
			System.out.println(InputData[59].toString());
			System.out.println(InputData[60].toString());
			System.out.println(InputData[61].toString());
			System.out.println(InputData[62].toString());
			System.out.println(InputData[63].toString());
			System.out.println(InputData[64].toString());
			System.out.println(InputData[65].toString());
			PopupInput("IP Addressing Format", InputData[63].toString());

			closePopUp();
			ClickHereSave();
			addSiteADetails(InputData);

			Pagerefresh();
			OpenCurrentServiceOrder();
			AEndDropdownSelection("Access Type", InputData[54].toString());
			AEndDropdownSelection("Access Technology", InputData[55].toString());
			if (InputData[32].toString().equalsIgnoreCase("offnet")) 
			{
				AEndDropdownSelection("Third Party Access Provider", InputData[67].toString());
				AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
			}
			AEndDropdownSelection("Building Type", InputData[56].toString());
			AEndDropdownSelection("Customer Site Pop Status", InputData[57].toString());
			AEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
			AEndInputEnter("BCP Reference", "AHYG-" + GetRandomNumber(1000));
			AEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));

			AEndDropdownSelection("Install Time", InputData[58].toString());
			AEndDropdownSelection("Cabinet Type", InputData[59].toString());
			AEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
			AEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));
			ClickHereSave();
			AlertAccept();

			AEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
			AEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
			AEndDropdownSelection("Presentation Interface", InputData[60].toString());
			AEndDropdownSelection("Connector Type", InputData[61].toString());
			AEndDropdownSelection("Fibre Type", InputData[62].toString());
			AEndDropdownSelection("Dual Customer Power Source", "No");
			AEndDropdownSelection("Customer Dedicated Access Ring", "No");
			ClickHereSave();
			AlertAccept();

			MiddleAppDropdown("Router Technology", InputData[64].toString());

			ClickHereSave();
			AlertAccept();

			RandomDropSelection("B", "Site Name");
			RandomDropSelection("B", "Router Model");
			ClickHereSave();
			OpenTab("IP Details");
			AEndDropdownSelection("IP Addressing Format", "IPv4 format only");

			break;

		}
		case "Number Hosting": {
			MiddleAppDropdown("Voice Service Country", InputData[47].toString());
			MiddleAppTextBox("Call Admission Control (Number of Channels)", InputData[48].toString());
			MiddleAppTextBox("Number of Signalling Trunks", InputData[49].toString());
			MiddleAppTextBox("Voice Configuration Reference", "Yes");
			MiddleAppDropdown("Incoming DDI Digits", InputData[50].toString());
			MiddleAppDropdown("Traffic Direction", InputData[51].toString());
			MiddleAppDropdown("First Codec", InputData[52].toString());
			Clickon(getwebelement(xml.getlocator("//locators/ClickLink").replace("Value", "Show More")));
			Thread.sleep(3000);
			MiddleAppDropdown("Second Codec", InputData[53].toString());
			MiddleAppDropdown("Third Codec", InputData[54].toString());
			MiddleAppDropdown("Fourth Codec", InputData[55].toString());
			MiddleAppDropdown("Fifth Codec", InputData[56].toString());
			MiddleAppDropdown("Sixth Codec", InputData[57].toString());
			Save();
			// ClickHereSave();

			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfo")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Show full infor");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Site Contact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopupInputPick").replace("Value", "Service Party")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopUpOk"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopUpOk")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ok");
			Thread.sleep(5000);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team Country")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[47].toString())));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Delivery Team City")));
			Thread.sleep(2500);
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[58].toString())));
			Thread.sleep(5000);

			closePopUp();

			Save();

			TrunkEntries(InputData[9].toString(), InputData[59].toString());

			// *****code for number hosting*****
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/OtherTabClick")));
			waitForpageload();
			waitforPagetobeenable();

			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value", "Internal Routing Prefix")),
					"45");
			Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/TextInput").replace("Value", "Reseller Profile")),
					"colt");

			Thread.sleep(3000);

			WaitforElementtobeclickable(xml.getlocator("//locators/VoiceTab"));
			Clickon(getwebelement(xml.getlocator("//locators/VoiceTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on voice feature tab");
			waitforPagetobeenable();
			waitForpageload();

			Clear(getwebelement(xml.getlocator("//locators/Language")));
			Thread.sleep(3000);
			SendKeys(getwebelement(xml.getlocator("//locators/Language")), "English");
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Select language");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/Language")), Keys.TAB);
			Thread.sleep(3000);
			/*
			 * WaitforElementtobeclickable(xml.getlocator("//locators/Resillence"));
			 * Clickon(getwebelement(xml.getlocator("//locators/Resillence")));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step:Click on resillence dropdown");
			 * Clickon(getwebelement(xml.getlocator("//locators/VoiceServiceCountryValue").
			 * replace("value", InputData[88].toString())));
			 * ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step:Click on resillence value ");
			 */
			ClickHereSave();
			break;
		}

		case "Managed Virtual Firewall": {
			ManagedVirtualFirewall();
			break;
		}
		case "Ethernet VPN Access": {
			EthernetVPNAccess(InputData);
			break;
		}
		case "IP VPN Service": {
			IPVPNServicePlusAccess(InputData);
			NetworkReferenceFillService(InputData);
			IPVPNServicePlusAccess1(InputData);// need to check with other product except access
			IPVPNServiceNetworkReference(InputData);
			break;
		}
		default:
			System.out.println("Above product is not exist in current list");
			break;
		}
		
	}

	public void EthernetVPNAccess(Object InputData[]) throws Exception 
	{	
	
		// Midle App Start
		MiddleAppDropdown("Link Type", InputData[47].toString());// E-VPN Primary
		MiddleAppDropdown("Service Bandwidth", InputData[48].toString());// 100 Mbps
		MiddleAppDropdown("Resilience Option", InputData[49].toString());// Protected
		MiddleAppDropdown("OSS Platform Flag", InputData[50].toString());// Legacy

		// Midle App End
		ClickHereSave();
		addSiteADetails(InputData);
		ClickHereSave();
		AEndDropdownSelection("Access Type", InputData[51].toString());// Colt Fibre
		AEndDropdownSelection("Access Technology", InputData[52].toString());// Access Technology
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			AEndDropdownSelection("Third Party Access Provider", InputData[60].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[61].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[62].toString());
		} 
		
		// Site ******
		AEndDropdownSelection("Building Type", InputData[53].toString());// Existing Building
		AEndDropdownSelection("Customer Site Pop Status", InputData[54].toString());// Existing
		AEndInputEnter("3rd Party Connection Reference", Integer.toString(GetRandomNumber(1000)));
		// AEndInputEnter("BCP Reference",InputData[94].toString());
		AEndInputEnter("BCP Reference", Integer.toString(GetRandomNumber(1000)));
		// AEndInputEnter("Site Name Alias",InputData[60].toString());
		AEndInputEnter("Site Name Alias", Integer.toString(GetRandomNumber(1000)));
		waitForpageload();
		waitforPagetobeenable();
		// CPE ****
		AEndInputEnter("Cabinet ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Cabinet Type", InputData[55].toString());
		AEndInputEnter("Shelf ID", Integer.toString(GetRandomNumber(1000)));
		// Access Port *****
		AEndInputEnter("Slot ID", Integer.toString(GetRandomNumber(1000)));
		AEndInputEnter("Physical Port ID", Integer.toString(GetRandomNumber(1000)));
		AEndDropdownSelection("Presentation Interface", InputData[56].toString());
		AEndDropdownSelection("Connector Type", InputData[57].toString());
		AEndDropdownSelection("Fibre Type", InputData[58].toString());
		// AEndDropdownSelection("Port Role",InputData[78].toString());
		// Install *****
		AEndDropdownSelection("Install Time", InputData[59].toString());
		RandomDropSelection("B", "Site Name");
		ClickHereSave();

	}

	public void CalenderdateSelection(String InDate) throws InterruptedException, IOException {
		String yearLocate = "//div[@id='ui-datepicker-div']//select[@class='ui-datepicker-year']";
		String mntLocate = "//div[@id='ui-datepicker-div']//select[@class='ui-datepicker-month']";
		String dtLocate = "//div[@id='ui-datepicker-div']//a[text()='-1']";

		String[] parts = InDate.split("/");
		String cl_date = parts[0];
		String cl_month = parts[1].toLowerCase();
		String cl_year = parts[2];
		if (cl_month.equalsIgnoreCase("01"))
			cl_month = "0";
		else if (cl_month.equalsIgnoreCase("02"))
			cl_month = "1";
		else if (cl_month.equalsIgnoreCase("03"))
			cl_month = "2";
		else if (cl_month.equalsIgnoreCase("04"))
			cl_month = "3";
		else if (cl_month.equalsIgnoreCase("05"))
			cl_month = "4";
		else if (cl_month.equalsIgnoreCase("06"))
			cl_month = "5";
		else if (cl_month.equalsIgnoreCase("07"))
			cl_month = "6";
		else if (cl_month.equalsIgnoreCase("08"))
			cl_month = "7";
		else if (cl_month.equalsIgnoreCase("09"))
			cl_month = "8";
		else if (cl_month.equalsIgnoreCase("10"))
			cl_month = "9";
		else if (cl_month.equalsIgnoreCase("11"))
			cl_month = "10";
		else if (cl_month.equalsIgnoreCase("12"))
			cl_month = "11";

		Select(getwebelement(yearLocate), cl_year);
		Clickon(getwebelement(mntLocate));
		Select2(getwebelement(mntLocate), cl_month);
		if (cl_date.equalsIgnoreCase("01"))
			dtLocate = dtLocate.replace("-1", "1");
		else if (cl_date.equalsIgnoreCase("02"))
			dtLocate = dtLocate.replace("-1", "2");
		else if (cl_date.equalsIgnoreCase("03"))
			dtLocate = dtLocate.replace("-1", "3");
		else if (cl_date.equalsIgnoreCase("04"))
			dtLocate = dtLocate.replace("-1", "4");
		else if (cl_date.equalsIgnoreCase("05"))
			dtLocate = dtLocate.replace("-1", "5");
		else if (cl_date.equalsIgnoreCase("06"))
			dtLocate = dtLocate.replace("-1", "6");
		else if (cl_date.equalsIgnoreCase("07"))
			dtLocate = dtLocate.replace("-1", "7");
		else if (cl_date.equalsIgnoreCase("08"))
			dtLocate = dtLocate.replace("-1", "8");
		else if (cl_date.equalsIgnoreCase("09"))
			dtLocate = dtLocate.replace("-1", "9");
		else
			dtLocate = dtLocate.replace("-1", cl_date);
		Clickon(getwebelement(dtLocate));
	}

	public void EnterDateInFooter(Object InputData[]) throws Exception {

		//////// ORDER DATE
		waitForpageload();
		waitforPagetobeenable();
		alertPopUp();
		Moveon(getwebelement(xml.getlocator("//locators/OrderDates")));
		// System.out.println("Moved Mouse");
		Clickon(getwebelement(xml.getlocator("//locators/OrderDates")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Dates");
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		alertPopUp();

		String temp = "//div/input[@aria-label='Customer Requested Date']/parent::div/span";
		System.out.println(temp);

		List<WebElement> RequestListDate = GetWebElements(temp);
		WebElement ele1 = RequestListDate.get(1);
		Clickon(ele1);
		CalenderdateSelection(CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Customer Requested Date " + CurrentDate());

		temp = xml.getlocator("//locators/OrderDateField/CalendrButton");
		temp = temp.replace("Value", "Order Signed Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Signed Date " + CurrentDate());

		temp = xml.getlocator("//locators/OrderDateField/CalendrButton");
		temp = temp.replace("Value", "Order Received Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date " + CurrentDate());

		/*
		 * WaitforElementtobeclickable((xml.getlocator(
		 * "//locators/IPVPNSite/ColtPromissday")));
		 * Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/ColtPromissday")));
		 * SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/ColtPromissday"))
		 * , CurrentDate());
		 */

	}
	/////////// BILLING DATE

	public void EnterBillingDateInFooter(Object InputData[]) throws Exception {
		Clickon(getwebelement(xml.getlocator("//locators/Billing")));
		waitforPagetobeenable();
		// System.out.println("BILLING TAB");
		// OpenTab("Billing");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Billing");

		Thread.sleep(3000);

		ClickHereSave();

		WaitforElementtobeclickable((xml.getlocator("//locators/ContractRenewalFlag")));
		Clear(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")));
		SendKeys(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")), InputData[17].toString());
		// System.out.println("contract renewal flag");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Renewal Flag");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ContractRenewalFlag")), Keys.TAB);
		Thread.sleep(4000);
		WaitforElementtobeclickable((xml.getlocator("//locators/ContractTerm")));
		Clear(getwebelement(xml.getlocator("//locators/ContractTerm")));
		SendKeys(getwebelement(xml.getlocator("//locators/ContractTerm")), InputData[16].toString());
		Clickon(getwebelement(xml.getlocator("//locators/SelectContractTerm")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Term");
		Thread.sleep(8000);

		String temp = xml.getlocator("//locators/OrderDateField/CalendrButton");
		temp = temp.replace("Value", "Billing Start Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Billing Start Date " + CurrentDate());

		/*
		 * WaitforElementtobeclickable((xml.getlocator(
		 * "//locators/BillingStartDateAccess")));
		 * Clear(getwebelement(xml.getlocator("//locators/BillingStartDateAccess")));
		 * SendKeys(getwebelement(xml.getlocator("//locators/BillingStartDateAccess")),
		 * CurrentDate());
		 */

		/*
		 * WaitforElementtobeclickable((xml.getlocator("//locators/POStartDateAccess")))
		 * ; Clear(getwebelement(xml.getlocator("//locators/POStartDateAccess")));
		 * SendKeys(getwebelement(xml.getlocator("//locators/POStartDateAccess")),
		 * CurrentDate());
		 * 
		 * WaitforElementtobeclickable((xml.getlocator("//locators/POEndDateAccess")));
		 * Clear(getwebelement(xml.getlocator("//locators/POEndDateAccess")));
		 * SendKeys(getwebelement(xml.getlocator("//locators/POEndDateAccess")),
		 * CurrentDate());
		 */
		savePage();
		waitforPagetobeenable();

		Thread.sleep(5000);

		// System.out.println("Billing Date Done!");
	}

	public int BillingIndex(String col) throws InterruptedException, DocumentException {
		// int index=0;
		List<WebElement> HeaderList = GetWebElements(
				"(//div[@class='ui-jqgrid-hbox']//table[@class='ui-jqgrid-htable'])[1]//th//div");
		int StatusHeader = -1;
		int i = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();

			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase(col)) {
				StatusHeader = i;
				break;
			}
			i++;
		}
		return StatusHeader + 1;
	}

	// update
	public void EnterServiceChargeInFooter(Object[] Inputdata, String Amount) throws Exception {
		String Td_lc = "//table[@summary='Service Charges']//tr[@id='idd']//td[index]";
		String Td_input = "//table[@summary='Service Charges']//tr[@id='idd']//td[index]//input";
		String Td_span = "//table[@summary='Service Charges']//tr[@id='idd']//td[index]//span";
		String LocNew = null;
		// ClickHereSave();
		WaitforElementtobeclickable(xml.getlocator("//locators/ExpandAllButton"));
		Clickon(getwebelement(xml.getlocator("//locators/ExpandAllButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Expand All Button");
		Thread.sleep(3000);
		int RowCount = getwebelementscount((xml.getlocator("//locators/BillingRow"))) - 1;
		System.out.println(RowCount);
		// int firstrow=1;
		if (RowCount > 0) {
			int BcnIndex = BillingIndex("BCN");
			System.out.println("BCN Index = " + Integer.toString(BcnIndex));
			int AmountIndex = BillingIndex("Amount");
			System.out.println("Ammount Index = " + Integer.toString(AmountIndex));
			while (!Getattribute(getwebelement(xml.getlocator("//locators/BillingLastRow")), "class")
					.contains("highlight")) {
				RowCount = getwebelementscount((xml.getlocator("//locators/BillingRow"))) - 1;
				System.out.println(RowCount);
				for (int i = 1; i <= RowCount; i++) {
					LocNew = Td_lc.replace("idd", Integer.toString(i)).replace("index", Integer.toString(AmountIndex));
					System.out.println(LocNew);
					Clickon(getwebelement(LocNew));
					waitforPagetobeenable();
					if (!Getattribute(getwebelement(LocNew), "class").contains("disabled")) {
						LocNew = Td_input.replace("idd", Integer.toString(i)).replace("index",
								Integer.toString(AmountIndex));
						String currentAmount = Gettext(getwebelement(LocNew));
						System.out.println("Amount : " + currentAmount);
						if (currentAmount == null || currentAmount.isEmpty() || currentAmount.contains("0.00")) {
							ClearSendKeys(getwebelement(LocNew), Amount);
							waitforPagetobeenable();
						}
					} else {
						System.out.println("Not Required to fill");
					}

					LocNew = Td_lc.replace("idd", Integer.toString(i)).replace("index", Integer.toString(BcnIndex));
					System.out.println(LocNew);
					Clickon(getwebelement(LocNew));
					waitforPagetobeenable();
					if (!Getattribute(getwebelement(LocNew), "class").contains("disabled")) {
						LocNew = Td_span.replace("idd", Integer.toString(i)).replace("index",
								Integer.toString(BcnIndex));
						Clickon(getwebelement(LocNew));
						waitforPagetobeenable();
						SendKeys(getwebelement(xml.getlocator("//locators/BCNInstallationChargeNRCInput")),
								Inputdata[30].toString());
						Thread.sleep(3000);
						Clickon(getwebelement(xml.getlocator("//locators/BCNNRCSearch")));
						waitforPagetobeenable();
						Thread.sleep(3000);
						waitforPagetobeenable();
					} else {
						System.out.println("Not Required to fill");
					}
				}
				Clickon(getwebelement(xml.getlocator("//locators/FirstLineitem")));
				Thread.sleep(5000);
				// firstrow=RowCount-1;
				Clickon(getwebelement(xml.getlocator("//locators/ClickNextPage")));
				waitforPagetobeenable();
				Thread.sleep(5000);
			}
			ClickHereSave();
			waitForpageload();
			waitforPagetobeenable();
		}

	}

	public void siteSearchByID(String SiteID, String SiteSide)
			throws InterruptedException, IOException, DocumentException {
		String siteId = null;
		if (SiteSide.equalsIgnoreCase("A"))
			siteId = SiteID;
		else if (SiteSide.equalsIgnoreCase("B"))
			siteId = SiteID;
		waitForpageload();
		waitforPagetobeenable();
		SendKeys(getwebelement("//input[@name='siteId']"), siteId);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Site ID :" + siteId);

		Clickon(getwebelement("//fieldset[@id='colt-siteselection-site-fieldset']//button"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Thread.sleep(7000);
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();
	}

	public void searchSite(Object InputData[], String SiteSide)
			throws InterruptedException, IOException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		String street = null;
		String country = null;
		String city = null;
		String postcode = null;
		String premises = null;
		if (SiteSide.equalsIgnoreCase("A")) {
			street = InputData[37].toString();
			country = InputData[38].toString();
			city = InputData[39].toString();
			postcode = InputData[40].toString();
			premises = InputData[41].toString();
		}
		if (SiteSide.equalsIgnoreCase("B")) {
			street = InputData[42].toString();
			country = InputData[43].toString();
			city = InputData[44].toString();
			postcode = InputData[45].toString();
			premises = InputData[46].toString();
		}
		SendKeys(getwebelement(xml3.getlocator("//locators/StreetNamerfs")), street);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name : ");

		Clickon(getwebelement(xml3.getlocator("//locators/Country")));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", country)));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter country : " + country);

		SendKeys(getwebelement(xml3.getlocator("//locators/City")), city);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City :" + city);
		SendKeys(getwebelement(xml3.getlocator("//locators/PostalCode")), postcode);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code : " + postcode);
		SendKeys(getwebelement(xml3.getlocator("//locators/Premises")), premises);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises : " + premises);
		Clickon(getwebelement(xml3.getlocator("//locators/Search")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();
	}

	public void InstallationTest() throws IOException, InterruptedException, DocumentException {
		ClickHereSave();
		waitforPagetobeenable();
		OpenTab("Installation and Test");
		try {
			Clear(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")));
			SendKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), Keys.TAB);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(3000);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void EnterInstallatiInFooter(Object InputData[]) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		if (!InputData[9].toString().equals("Cloud Unified Communications")

				&& !InputData[9].toString().equals("Professional Services")
				&& !InputData[9].toString().equals("Ethernet Hub")) {
			Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Installation and Test");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Click on Installation Dropdown button and Select Installation and Test");

			Thread.sleep(5000);
			Clear(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")));
			SendKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), Keys.TAB);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(3000);
			if (!InputData[9].toString().equalsIgnoreCase("Wave")
					&& !InputData[9].toString().equalsIgnoreCase("Interconnect")
					&& !InputData[9].toString().equalsIgnoreCase("Ethernet Spoke")
					&& !InputData[9].toString().equalsIgnoreCase("SWIFTNet")
					&& !InputData[9].toString().equalsIgnoreCase("SIP Trunking")
					&& !InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")
					&& !InputData[9].toString().equalsIgnoreCase("Ethernet Line")) {
				Clickon(getwebelement(xml.getlocator("//locators/SaveOrderContinue")));
				waitforPagetobeenable();
				Thread.sleep(3000);
			}
			alertPopUp();
		}
		alertPopUp();
		savePage();
		waitforPagetobeenable();
	}

	public void CommercialValidation(Object[] InputData) throws Exception {

		getwebelement(xml.getlocator("//locators/OrderStatus")).clear();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Order Status");
		SendKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), "Commercial Validation");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Commercial Validation");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.ENTER);
		// Thread.sleep(5000);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.TAB);
		waitforPagetobeenable();
		Thread.sleep(2000);
		waitforPagetobeenable();
		Thread.sleep(10000);

	}

	public void SelectAttachmentTab(String ProductName) throws IOException, InterruptedException, DocumentException {
		String[] AttachementProduct = new String[] { "IP Voice Solutions", "SIP Trunking", "Voice Line V",
				"Managed Dedicated Firewall", "Managed Virtual Firewall" };
		int index = Arrays.asList(AttachementProduct).indexOf(ProductName);
		if (index >= 0) {
			Select(getwebelement(xmlIP.getlocator("//locators/tabDropdown")), "Attachments");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Selected");
			Clickon(getwebelement(xmlIP.getlocator("//locators/AttachmentTabSelection")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Clicked");
			Thread.sleep(10000);
		}
	}

	public void UploadDocument(String ProductName) throws Exception {
		if (ProductName.equals("IP Voice Solutions")) {
			UploadSOWTypeDocument(ProductName, "SOW");
		} else if (ProductName.equals("Voice Line V") || ProductName.equals("SIP Trunking")) {
			UploadSOWTypeDocument(ProductName, "Call Barring Form");
			// UploadSOWTypeDocument(InputData, "Disaster Recovery Documents");
		} else if (ProductName.equals("Managed Virtual Firewall") || ProductName.equals("Managed Dedicated Firewall")) {
			UploadSOWTypeDocument(ProductName, "Security Policy");
			// UploadSOWTypeDocument(InputData, "Documents");
		}
	}

	public void SelectAttachmentTab(Object[] InputData) throws IOException, InterruptedException, DocumentException {
		String[] AttachementProduct = new String[] { "IP Voice Solutions", "SIP Trunking", "Voice Line V",
				"Managed Dedicated Firewall", "Managed Virtual Firewall" };
		int index = Arrays.asList(AttachementProduct).indexOf(InputData[9].toString());
		if (index >= 0) {
			OpenTab("Attachments");
			// Select(getwebelement(xmlIP.getlocator("//locators/tabDropdown")),
			// "Attachments");
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab
			// Selected");
			// Clickon(getwebelement(xmlIP.getlocator("//locators/AttachmentTabSelection")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab
			// Clicked");
			Thread.sleep(10000);
		}
	}

	public void UploadDocument(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("IP Voice Solutions")) {
			UploadSOWTypeDocument(InputData, "SOW");
		} else if (InputData[9].toString().equals("Voice Line V") || InputData[9].toString().equals("SIP Trunking")) {
			UploadSOWTypeDocument(InputData, "Call Barring Form");
			// UploadSOWTypeDocument(InputData, "Disaster Recovery Documents");
		} else if (InputData[9].toString().equals("Managed Virtual Firewall")
				|| InputData[9].toString().equals("Managed Dedicated Firewall")) {
			UploadSOWTypeDocument(InputData, "Security Policy");
			// UploadSOWTypeDocument(InputData, "Documents");
		}
	}

	public void UploadSOWTypeDocument(Object[] InputData, String Filetype) throws Exception {

		if (InputData[9].toString().equals("Voice Line V") || InputData[9].toString().equals("SIP Trunking")) // Added
																												// by
																												// Rekha
		{
			System.out.println(xml.getlocator("//locators//FileUpload"));
			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			uploadafile(xml.getlocator("//locators//FileUpload"), "test.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);

			uploadafile(xml.getlocator("//locators//FileUpload"), "test1.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);

			String trtd = "//div[@id='a_3']//table[@id='s_3_l']//tr[contains(@class,'jqgrow')]//td[ind]";
			List<WebElement> HeaderList = GetWebElements("//div[@id='a_3']//table[@class='ui-jqgrid-htable']//th");
			int thIndex = -1;
			int i = 0;
			for (WebElement ele : HeaderList) {
				javascriptexecutor(ele);
				String Text = ele.getText().trim();

				System.out.println("Column : " + Text);
				if (Text.equalsIgnoreCase("Created By")) {
					thIndex = i;
					break;
				}
				i++;
			}
			thIndex = thIndex + 1;

			List<WebElement> otherList = GetWebElements(xml.getlocator("//locators/DocumnetTypeOther"));
			WebElement ele1 = otherList.get(0);

			Clickon(ele1);
			waitForpageload();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xml.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xml.getlocator("//locators/DocumentTypeCallBaringForm")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: CallBarring Form File Type Selected");
			System.out.println(xml.getlocator("//locators//FileUpload"));
			Thread.sleep(10000);

			trtd = trtd.replace("ind", Integer.toString(thIndex));
			List<WebElement> td = GetWebElements(trtd);
			ele1 = td.get(0);

			Clickon(ele1);

			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			List<WebElement> otherList2 = GetWebElements(xml.getlocator("//locators/DocumnetTypeOther"));
			ele1 = otherList2.get(0);

			Clickon(ele1);
			waitForpageload();

			Clickon(getwebelement(xml.getlocator("//locators/DocumnetTypeOther")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xml.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xml.getlocator("//locators/DocumentTypeDisaster")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Disaster File Type Selected");
			trtd = trtd.replace("ind", Integer.toString(thIndex));
			List<WebElement> td1 = GetWebElements(trtd);
			ele1 = td1.get(0);

			Clickon(ele1);

		} else {

			System.out.println(xmlIP.getlocator("//locators//FileUpload"));

			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			uploadafile(xmlIP.getlocator("//locators//FileUpload"), "test.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);
			Clickon(getwebelement(xmlIP.getlocator("//locators/DocumnetTypeOther")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xmlIP.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xmlIP.getlocator("//locators/DoucmentTypeSelection").replace("Filetype", Filetype)));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: SOW File Type Selected");

		}
		// --> end of Rekha's code

	}

	public void UploadSOWTypeDocument(String ProductName, String Filetype) throws Exception {

		if (ProductName.equals("Voice Line V") || ProductName.equals("SIP Trunking")) // Added by Rekha
		{
			System.out.println(xml.getlocator("//locators//FileUpload"));
			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			uploadafile(xml.getlocator("//locators//FileUpload"), "test.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);
			Clickon(getwebelement(xml.getlocator("//locators/DocumnetTypeOther")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xml.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xml.getlocator("//locators/DocumentTypeCallBaringForm")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: CallBarring Form File Type Selected");
			System.out.println(xml.getlocator("//locators//FileUpload"));
			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			uploadafile(xml.getlocator("//locators//FileUpload"), "test1.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);
			Clickon(getwebelement(xml.getlocator("//locators/DocumnetTypeOther")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xml.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xml.getlocator("//locators/DocumentTypeDisaster")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Disaster File Type Selected");

		} else {
			System.out.println(xmlIP.getlocator("//locators//FileUpload"));
			// WaitforElementtobeclickable(xmlIP.getlocator("//locators//FileUpload"));
			uploadafile(xmlIP.getlocator("//locators//FileUpload"), "test.txt");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
			Thread.sleep(10000);
			Clickon(getwebelement(xmlIP.getlocator("//locators/DocumnetTypeOther")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
			Clickon(getwebelement(xmlIP.getlocator("//locators/DownArrow")));
			Clickon(getwebelement(xmlIP.getlocator("//locators/DoucmentTypeSelection").replace("Filetype", Filetype)));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: SOW File Type Selected");

		}
		// --> end of Rekha's code

	}

	public void TechnicalValidation(Object[] InputData) throws Exception {
		// System.out.println("Aman,");
		// Thread.sleep(10000);
		waitforPagetobeenable(); // as per Aman
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Technical Validation");
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/SelectTechnicalValidation"));
		Clickon(getwebelement(xml.getlocator("//locators/SelectTechnicalValidation")));
		alertPopUp();
		waitforPagetobeenable();
		// savePage();
		Thread.sleep(2000);

		savePage();
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(5000);
		if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
		{
			ClickContinue();
			Thread.sleep(3000);
			waitForpageload();
			waitforPagetobeenable();
		}
		if (isDisplayed((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			waitForpageload();
			waitforPagetobeenable();
		}
		
		String locator="//div[@id='colt-workflows-selection-popup-content']";
		String TrBtn="//button[@id='TriggerTR']";
		String Checkbox="//div[@id='colt-workflows-selection-popup-content']//input[@type='checkbox']";
		List<WebElement> optionlist = GetWebElements(locator);
		if(optionlist.size()>0)
		{
			List<WebElement> Btn = GetWebElements(TrBtn);
			WebElement ele=Btn.get(0);
			if(ele.isEnabled())
			{
				Clickon(ele);
			}
			else
			{
				Clickon(getwebelement(Checkbox));
				waitForpageload();
				waitforPagetobeenable();
				Btn = GetWebElements(TrBtn);
				ele=Btn.get(0);
				Clickon(ele);
			}
		}
		waitForpageload();
		waitforPagetobeenable();
		/*if (InputData[9].toString().equalsIgnoreCase("Wave")
				|| InputData[9].toString().equalsIgnoreCase("public Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Line")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Hub")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Spoke")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Access")
				|| InputData[9].toString().equalsIgnoreCase("Ultra Low Latency")
				|| InputData[9].toString().equalsIgnoreCase("Dark Fibre")
				|| InputData[9].toString().equalsIgnoreCase("DCA Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("SWIFTNet")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet VPN Access")
				|| InputData[9].toString().equalsIgnoreCase("IP Access")
				|| InputData[9].toString().equalsIgnoreCase("CPE Solutions Site"))
		// || InputData[9].toString().equals("Voice Line V") removed this By Devesh
		{
			Thread.sleep(5000);
			// as per Aman added try catch
			try {
				WaitforElementtobeclickable((xml.getlocator("//locators/TriggerTRButton")));
				Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButton")));
				alertPopUp();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trigger TR Button");
				Thread.sleep(10000);
			} catch (Exception ex) {
				WaitforElementtobeclickable(xml.getlocator("//locators/BDWREVW_CheckBox"));
				Clickon(getwebelement(xml.getlocator("//locators/BDWREVW_CheckBox")));
				WaitforElementtobeclickable(xml.getlocator("//locators/TriggerTRButton"));
				Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButton")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trigger TR Button");
				Thread.sleep(10000);
			}
		}
		if (InputData[9].toString().equalsIgnoreCase("IP Domain")
				|| InputData[9].toString().equalsIgnoreCase("IP VPN Service")) // Added by Rekha
		{
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButtonCheckbox")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click On Check Box");
			WaitforElementtobeclickable((xml.getlocator("//locators/TriggerTRButton"))); // as per Abhay
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/TriggerTRButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Trigger TR Button");
			Thread.sleep(10000);
		}*/
		
	}

	public void DeliveryValidation(Object[] InputData) throws Exception {
		waitforPagetobeenable(); // as per Aman
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectDeliveryValidation")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Delivery");
		waitforPagetobeenable();
		Thread.sleep(5000);
		if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
		{
			ClickContinue();
			waitforPagetobeenable();
			Thread.sleep(3000);
		}
//			SendKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), "Not Required");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in primary test method");
//			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), Keys.TAB);
//			Thread.sleep(3000);
//			savePage();

	}

	public void TriggerPopup(Object[] InputData) throws Exception {
		// waitforPagetobeenable();
		if ((isProductValid(InputData[9].toString()))) {
			// System.out.println("DHONI");

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

	public void clickOnManualValidationA() throws Exception {
		if (isDisplayed(xml.getlocator("//locators/manualvalidation1"))) {

			// Waitforvisibilityofelement(xml.getlocator("//locators/manualvalidation"));
			if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
				Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
			}
			WaitforElementtobeclickable(xml.getlocator("//locators/manualvalidation1"));
			Clickon(getwebelement(xml.getlocator("//locators/manualvalidation1")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Manual Validation Button");
			waitforPagetobeenable();

			Thread.sleep(5000);
		}
	}

	public void clickOnManualValidationB(Object[] Inputdata) throws Exception { // changes as per Ayush
		/*
		 * if (isDisplayed(xml.getlocator("//locators/manualvalidation2"))) {
		 * 
		 * // Waitforvisibilityofelement(xml.getlocator("//locators/manualvalidation"));
		 * WaitforElementtobeclickable(xml.getlocator("//locators/manualvalidation2"));
		 * Clickon(getwebelement(xml.getlocator("//locators/manualvalidation2")));
		 * ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step: Click Manual Validation Button"); waitforPagetobeenable();
		 * 
		 * Thread.sleep(5000); }
		 */
		if (Inputdata[9].toString().equals("Voice Line V") || Inputdata[9].toString().equals("Number Hosting")
				|| Inputdata[9].toString().equals("Interconnect") || Inputdata[9].toString().equals("SIP Trunking")) {
			try {
				if (isElementPresent(xml.getlocator("//locators/manualvalidation2"))) {
					System.out.println("go to else if loop1");
					WaitforElementtobeclickable(xml.getlocator("//locators/manualvalidation2"));
					Clickon(getwebelement(xml.getlocator("//locators/manualvalidation2")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Manual Validation Button");
				} else {
					System.out.println("Manual validation button is not present");
				}
			} catch (Exception e) {
				System.out.println("Manual validation button is not present in catch block");
			}
		}
	}

	public void CompletedValidation(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (InputData[9].toString().equals("IP VPN Service"))// **Start** Added By Abhay dated 28-Sep-2019
		{
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders")));
			String ServOrder = ServiceOrder.get().toString();

			String[] parts = ServOrder.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrder")), part2);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrderGo")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickSeibelOrder")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
			Thread.sleep(3000);
			Selectproduct("IP VPN Site");
			openIPVPNSite();
			enterMandatoryFieldsInHeader(InputData);
			NetworkReferenceFill();
			IPVPNSITEMiddleApplet(InputData);
			EnterDateInFooter(InputData);
			EnterBillingDateInFooter(InputData);
			EnterServiceChargeInFooter(InputData, "2");
			if (InputData[10].equals("IP VPN Access")||InputData[10].equals("IP VPN Plus")||InputData[10].equals("SWIFTNet")) {
				OperationalAttributesforIPVPN(InputData);
			}
			else if (InputData[10].equals("IP VPN Wholesale")||InputData[10].equals("PrizmNet")) {
				WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites"));
				Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites")));
				waitforPagetobeenable();
			}
			AEndInputEnter("Cabinet ID", Integer.toString(rnd.nextInt(1000)));
			//AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			ClickHereSave();
			// EnterInstallationChargeInFooter(InputData);
			InstallationTest();
			CommercialValidation(InputData);
			TechnicalValidation(InputData);
			DeliveryValidation(InputData);
			AlertAccept();
			clickOnManualValidationA();

			if (InputData[9].toString().contains("IP VPN Service") && InputData[32].toString().contains("Offnet")) {
				CEOS_Offnet();
				LaunchingCEOSApplication(InputData);
			}
			waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();

			Thread.sleep(5000);
			if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
			{
				ClickContinue();
				Thread.sleep(3000);
			}
			// savePage();
			// Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			AlertAccept();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
			waitforPagetobeenable();
			Thread.sleep(5000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}

			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);

			MovetoIPService();

			// Save the Current URL
			// Create and completed new IP VPN SItes
			// Open the Older Order number;
			// **End**
		} 

		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
		waitforPagetobeenable();

		Thread.sleep(5000);
		// savePage();
		// Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
		waitforPagetobeenable();
		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (isDisplayed(xml.getlocator("//locators/AlertAccept"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}

		// Pagerefresh();
		Thread.sleep(5000);
		// =======================Added by Rekha ==================== difft pop up was
		// arriving for Ethernet VPN Access=====================
		if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
			Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
		}
		// ============================================================================================================
		System.out.println("Order complete");
		Thread.sleep(5000);
		// Added by Abhay
		String CompValidation = null;
		CompValidation = getwebelement2(xml.getlocator("//locators/IPVPNSite/OrderStatusInput")).getAttribute("value");
		System.out.println(CompValidation);

		Assert.assertTrue(CompValidation.contains("Comp"),
				" Order status failed to Complete. It is displayed as : " + CompValidation);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Order staus verified as Completed");
		Thread.sleep(5000);

	}

	public void ServiceTab(Object[] InputData) throws Exception {
		Thread.sleep(10000);
		// ServiceOrder.set("CPE150500227LON103/1504-881226");
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
		if (InputData[0].toString().trim() != "Yes"
				&& (InputData[0].toString().trim() != "" || InputData[0].toString() != null)) {
			ServiceOrder.set(InputData[0].toString().trim());
		}
		Clickon(getwebelement(xml.getlocator("//locators/InputServiceOrder")));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get().toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Checking Billing Status for service order number :" + ServiceOrder.get().toString());
		waitforPagetobeenable();
		Thread.sleep(6000);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Checking Billing Status for service orer number :" + ServiceOrder.get().toString());
		for (int i = 0; i < 15; i++) {
			if (isElementPresent("//*[text()='BILLING ERROR']")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is ERROR");
				break;
			}
			if (isElementPresent("//*[text()='DOWNSTREAM SYSTEM ERROR']")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is DOWNSTREAM SYSTEM ERROR");
				break;
			}
			if (!isElementPresent("//*[text()='COMPLETE']")) {
				Thread.sleep(60000);
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is  NOT COMPLETED");
			} else if (isElementPresent("//*[text()='COMPLETE']")) {
				break;
			}
		}
		waitforPagetobeenable();
		Thread.sleep(3000);
		try // By Aman Gupta
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyButtonClick"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyButtonClick")));
		} catch (Exception e) {

			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyBtn"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyBtn")));
		}
		waitforPagetobeenable();
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), InputData[2].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity No");
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");
		Thread.sleep(3000);
		ModifiedServiceOrder.set(Gettext(getwebelement(xml.getlocator("//locators/ModifyOrderNumber"))));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated Modify Order Reference No: " + ModifiedServiceOrder.get());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
		// ---> Condition need to be added for mod com and mod tech
		/*
		 * System.out.println(Integer.toString(InputData.length -
		 * 1)+":"+InputData[InputData.length - 1].toString());
		 * System.out.println(Integer.toString(InputData.length -
		 * 2)+":"+InputData[InputData.length - 2].toString());
		 * System.out.println(Integer.toString(InputData.length -
		 * 3)+":"+InputData[InputData.length - 3].toString());
		 * System.out.println(Integer.toString(InputData.length -
		 * 4)+":"+InputData[InputData.length - 4].toString());
		 * System.out.println(Integer.toString(InputData.length -
		 * 5)+":"+InputData[InputData.length - 5].toString());
		 * System.out.println("-----");
		 */

		if (InputData[InputData.length - 1].toString().contains("Com")
				|| InputData[InputData.length - 1].toString().contains("Carnor")) // Added by Dipesh
		{
			SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "BCN Change");
		} else if (InputData[InputData.length - 1].toString().contains("Tech")) {
			SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "Other");
		}
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");
		Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
		Thread.sleep(2000);
		waitforPagetobeenable();
		Thread.sleep(3000);
		Clickon(getwebelement("//input[@aria-labelledby='COLT_ProContact_FullName_Label']/following-sibling::span"));
		Thread.sleep(5000);
		WaitforElementtobeclickable("//button[@aria-label='Pick Contact:OK']");
		Clickon(getwebelement("//button[@aria-label='Pick Contact:OK']"));
		Thread.sleep(8000);
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary"));
		SendKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), InputData[12].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Existing Capacity Lead Time Primary");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
		waitforPagetobeenable();

		if (!InputData[9].toString().equalsIgnoreCase("Wave")
				&& !InputData[9].toString().equalsIgnoreCase("Ethernet Line")) // added shivananda
		{
			/*
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartySearch")); waitforPagetobeenable();
			 * 
			 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartySearch")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyPopupDropdown")); // add by ayush
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/MaintenancePartyPopupDropdown")));
			 * safeJavaScriptClick(getwebelement(xml.getlocator("//locators/AccountStatus"))
			 * );
			 * WaitforElementtobeclickable(xml.getlocator("//locators/InputAccountStatus"));
			 * SendKeys(getwebelement(xml.getlocator("//locators/InputAccountStatus")),
			 * InputData[13].toString());
			 * WaitforElementtobeclickable(xml.getlocator("//locators/AccountStatusSearch"))
			 * ; // add by ayush
			 * Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSearch")));
			 * waitforPagetobeenable(); Thread.sleep(4000);
			 * WaitforElementtobeclickable(xml.getlocator("//locators/AccountStatusSubmit"))
			 * ; Clickon(getwebelement(xml.getlocator("//locators/AccountStatusSubmit")));
			 * Thread.sleep(3000);
			 * 
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyContact"));
			 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyContact")));
			 * waitforPagetobeenable(); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyContactPopupDropdown")); // add // by
			 * Thread.sleep(4000); // ayush Clickon(getwebelement(xml.getlocator(
			 * "//locators/MaintenancePartyContactPopupDropdown")));
			 * //WaitforElementtobeclickable(xml.getlocator("//locators/DropDown")); // add
			 * by dipesh //Clickon(getwebelement(xml.getlocator("//locators/DropDown")));//
			 * add by dipesh
			 * waitandForElementDisplay((xml.getlocator("//locators/MaintenanceLastName")),
			 * 5); safeJavaScriptClick(getwebelement(xml.getlocator(
			 * "//locators/MaintenanceLastName")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/InputMaintenanceLastName"));
			 * SendKeys(getwebelement(xml.getlocator("//locators/InputMaintenanceLastName"))
			 * ,InputData[14].toString()); // add by ayush
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/InputMaintenanceLastNameSearch"));
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/InputMaintenanceLastNameSearch"))); waitforPagetobeenable();
			 * Thread.sleep(2000); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyContactSubmit"));
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/MaintenancePartyContactSubmit"))); Thread.sleep(3000);
			 * 
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyAddress"));
			 * Clickon(getwebelement(xml.getlocator("//locators/MaintenancePartyAddress")));
			 * waitforPagetobeenable(); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyAddresPopupDropdown"));// add by // ayush
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/MaintenancePartyAddresPopupDropdown")));
			 * WaitforElementtobeclickable(xml.getlocator("//locators/PartyAddresStreetName"
			 * )); // add by ayush safeJavaScriptClick(getwebelement(xml.getlocator(
			 * "//locators/PartyAddresStreetName")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/InputPartyAddresStreetName")); // add by ayush
			 * SendKeys(getwebelement(xml.getlocator("//locators/InputPartyAddresStreetName"
			 * )), InputData[15].toString()); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/InputPartyAddresStreetNameSearch"));
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/InputPartyAddresStreetNameSearch"))); waitforPagetobeenable();
			 * Thread.sleep(2000); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/MaintenancePartyAddressSubmit")); // add by ayush
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/MaintenancePartyAddressSubmit"))); waitforPagetobeenable();
			 */
			Thread.sleep(3000);
			Clickon(getwebelement(
					"//input[@aria-labelledby='COLT_ProContact_FullName_Label']/following-sibling::span"));
			Thread.sleep(5000);
			WaitforElementtobeclickable("//button[@aria-label='Pick Contact:OK']");
			Clickon(getwebelement("//button[@aria-label='Pick Contact:OK']"));
			Thread.sleep(8000);
			waitforPagetobeenable();

		}
		savePage();
		waitforPagetobeenable();

	}

	public void ModComExisting(Object[] InputData) throws Exception {
		Thread.sleep(10000);
		// ServiceOrder.set("CPE150500227LON103/1504-881226");
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
		if (InputData[0].toString().trim() != "Yes"
				&& (InputData[0].toString().trim() != "" || InputData[0].toString() != null)) {
			ServiceOrder.set(InputData[0].toString().trim());
		}
		Clickon(getwebelement(xml.getlocator("//locators/InputServiceOrder")));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get().toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Checking Billing Status for service order number :" + ServiceOrder.get().toString());
		waitforPagetobeenable();
		Thread.sleep(6000);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Checking Billing Status for service orer number :" + ServiceOrder.get().toString());
		for (int i = 0; i < 15; i++) {
			if (isElementPresent("//*[text()='BILLING ERROR']")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is ERROR");
				Assert.fail("Billing Error for Servic Order : " + InputData[0].toString().trim());
				break;
			}
			if (isElementPresent("//*[text()='DOWNSTREAM SYSTEM ERROR']")) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is DOWNSTREAM SYSTEM ERROR");
				Assert.fail("Billing Error for Servic Order : " + InputData[0].toString().trim());
				break;
			}
			if (!isElementPresent("//*[text()='COMPLETE']") && !isElementPresent("//*[text()='N/A']")) {
				Thread.sleep(60000);
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Billing Status for service orer number is  NOT COMPLETED");
			} else if (isElementPresent("//*[text()='COMPLETE']") || isElementPresent("//*[text()='N/A']")) {
				break;
			}
		}
		waitforPagetobeenable();
		Thread.sleep(3000);
		try // By Aman Gupta
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyButtonClick"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyButtonClick")));
		} catch (Exception e) {

			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyBtn"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyBtn")));
		}
		waitforPagetobeenable();
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), InputData[2].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity No");
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");
		Thread.sleep(3000);
		ModifiedServiceOrder.set(Gettext(getwebelement(xml.getlocator("//locators/ModifyOrderNumber"))));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated Modify Order Reference No: " + ModifiedServiceOrder.get());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
		System.out.println(InputData[11].toString());
		SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), InputData[11].toString());
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");
		Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
		Thread.sleep(2000);
		waitforPagetobeenable();
		Thread.sleep(3000);
		
		String SourceRef = "Test" + Integer.toString(GetRandomNumber(20));
		
		SendKeys(getwebelement("//input[@aria-labelledby='COLT_Service_Order_Source_Ref_Label']"),SourceRef);
		waitforPagetobeenable();
		Thread.sleep(3000);
		/*Clickon(getwebelement("//input[@aria-labelledby='COLT_ProContact_FullName_Label']/following-sibling::span"));
		Thread.sleep(5000);
		WaitforElementtobeclickable("//button[@aria-label='Pick Contact:OK']");
		Clickon(getwebelement("//button[@aria-label='Pick Contact:OK']"));*/
		Thread.sleep(8000);
		waitforPagetobeenable();
		if (InputData[9].toString().equalsIgnoreCase("Ethernet VPN Access")
				|| (InputData[9].toString().equalsIgnoreCase("Ethernet Access"))
				|| (InputData[9].toString().equalsIgnoreCase("IP VPN Service"))
				|| (InputData[9].toString().equals("public Wave Node"))) // added Rekha
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/InstallTimeDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeSelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/InstallTimeSelectAccess")));
			ClickHereSave();
		} else if (InputData[9].toString().equals("public Wave Service")
				|| (InputData[9].toString().equals("DCA Ethernet") || InputData[9].toString().equals("Dark Fibre")
						|| InputData[9].toString().equals("public Ethernet"))) {
			WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/InstallTimeDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeSelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/InstallTimeSelectAccess")));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/InstalltimeBside"));
			Clickon(getwebelement(xml.getlocator("//locators/InstalltimeBside")));
			WaitforElementtobeclickable(xml.getlocator("//locators/InstallTimeSelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/InstallTimeSelectAccess")));
			WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			waitforPagetobeenable();
			ClickHereSave();

		}
		WaitforElementtobeclickable(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary"));
		SendKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), InputData[12].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Existing Capacity Lead Time Primary");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
		waitforPagetobeenable();

		if (!InputData[9].toString().equalsIgnoreCase("Wave")
				&& !InputData[9].toString().equalsIgnoreCase("Ethernet Line")) // added shivananda
		{

			Thread.sleep(3000);
			Clickon(getwebelement(
					"//input[@aria-labelledby='COLT_ProContact_FullName_Label']/following-sibling::span"));
			Thread.sleep(5000);
			WaitforElementtobeclickable("//button[@aria-label='Pick Contact:OK']");
			Clickon(getwebelement("//button[@aria-label='Pick Contact:OK']"));
			Thread.sleep(8000);
			waitforPagetobeenable();

		}
		savePage();
		waitforPagetobeenable();

	}

	public void Check1(Object[] InputData) throws Exception {
		ServiceOrder.get();
		System.out.println(ServiceOrder.get());
		String x = ServiceOrder.get();
		// String string = "004-034556";
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

	/*
	 * Added as per Ayush
	 */
	public void Check(Object[] InputData) throws Exception {
		Thread.sleep(10000);
		ServiceOrder.set("228433212/200920-0012");
		do {
			Pagerefresh();
			System.out.println("Page to be refresed");
			Thread.sleep(20000);
		} while (!isElementPresent("//a[text()='Accounts']"));

		try {
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
		} catch (Exception e) {
			try {
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/InputServiceOrder"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get().toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter value in service order " + ServiceOrder.get().toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on go button");
		Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Click on service order " + ServiceOrder.get().toString());

	}

	public void OpenCurrentServiceOrder() throws Exception {
		Thread.sleep(10000);
		do 
		{
			Pagerefresh();
			System.out.println("Page to be refresed");
			Thread.sleep(20000);
		} while (!isElementPresent("//a[text()='Accounts']"));
		try 
		{
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
		} 
		catch (Exception e) 
		{
			try 
			{
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/InputServiceOrder"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get().toString());
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: enter value in service order " + ServiceOrder.get().toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on go button");
		Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Click on service order " + ServiceOrder.get().toString());
		waitForpageload();
		waitforPagetobeenable();

	}
	public void statusReason(Object[] InputData) throws Exception {
		Thread.sleep(4000);
		// Waitforvisibilityofelement(xml.getlocator("//locators/StatusReasonDropdown"));
		WaitforElementtobeclickable(xml.getlocator("//locators/StatusReasonDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/StatusReasonDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on status reason dropdown");

		Clickon(getwebelement(
				xml.getlocator("//locators/StatusReasonValue").replace("value", "Abandoned, Order replaced")));
		System.out.println(InputData[90].toString());
		// SendKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")),
		// InputData[75].toString());
		System.out.println("status reason");
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on abandonked
		// status popup");
		Thread.sleep(4000);
		// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")),
		// Keys.ENTER);
//		Thread.sleep(4000);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/StatusReasonAbandoned")), Keys.TAB);
		System.out.println("enter tab");

		savePage();
		waitforPagetobeenable();
	}

	public void AbandonedOrder(Object[] InputData) throws Exception {

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

	public void verifyOrderAbandoned() throws Exception {

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
		String actual = "Abandoned";
		Assert.assertEquals(expected, actual);
		Reporter.log("Test has been passed", true);

	}

	public void RandomDropSelection(String SiteSide, String DropdownName)
			throws DocumentException, InterruptedException {
		System.out.println("looking for   : " + DropdownName);
		String eleLoct = "";
		if (SiteSide.equalsIgnoreCase("A")) {
			eleLoct = xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", DropdownName);
		} else if (SiteSide.equalsIgnoreCase("B")) {
			eleLoct = xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", DropdownName);
		}
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		waitForpageload();
		waitforPagetobeenable();
		String temp = "//ul[contains(@style,'block')]//li[@class='ui-menu-item']";
		List<WebElement> optionlist = GetWebElements(temp);
		List<String> options = new ArrayList<String>();
		int count = optionlist.size();
		System.out.println("Option Count : " + Integer.toString(count));
		if (count > 0) {
			for (WebElement ele : optionlist) {
				javascriptexecutor(ele);
				String Text = ele.getText();
				System.out.println("Column : " + Text);
				options.add(Text);
			}

			// index=index;
			System.out.println("Option Count : " + Integer.toString(0));
			temp = options.get(0);
			System.out.println("Selection Item  : " + temp);
			if (SiteSide.equalsIgnoreCase("A")) {
				// Clickon(getwebelement(eleLoct));
				AEndDropdownSelection(DropdownName, temp);
			} else if (SiteSide.equalsIgnoreCase("B")) {
				// Clickon(getwebelement(eleLoct));
				BEndDropdownSelection(DropdownName, temp);
			}
			waitForpageload();
			Thread.sleep(1000);
			waitforPagetobeenable();
		}
	}

	public void addSiteADetails(Object[] InputData) throws Exception 
	{

		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/SearchAddressSiteA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Address SiteA");
		Thread.sleep(7000);
		String SiteId = InputData[35].toString();
		System.out.println(SiteId);
		if (SiteId == "" || SiteId == null) {
			searchSite(InputData, "A");
		} 
		else 
		{
			siteSearchByID(SiteId, "A");
		}
		// Site Selection over
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartySearchAccess"));
		Moveon(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");

		popupFieldSelection("Ordering Party", "Party Id", InputData[1].toString());

		if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))
				|| isDisplayed(xml.getlocator("//locators/SaveOrderChanges"))) {
			WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
			Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
			waitForpageload();
			System.out.println("page load succesfuuly now come to middle applet");
			waitforPagetobeenable();
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactSearchAccess"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SiteContactSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Site Contact");

		popupFieldSelection("Site Contact", "Last Name", InputData[7].toString());

		waitforPagetobeenable();

		ClickHereSave();

		waitforPagetobeenable();
		// }
	}

	public void addSiteBDetails(Object[] InputData) throws Exception 
	{

		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/SearchAddressSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Address SiteB");
		String SiteId = InputData[36].toString();
		System.out.println(SiteId);
		if (SiteId == "" || SiteId == null) {
			searchSite(InputData, "B");
		} else {
			siteSearchByID(SiteId, "B");

		}

		waitforPagetobeenable();

		Thread.sleep(10000);
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml3.getlocator("//locators/ServicePartySearchSiteB"));
		Moveon(getwebelement(xml3.getlocator("//locators/ServicePartySearchSiteB")));
		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/ServicePartySearchSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");

		popupFieldSelection("Ordering Party", "Party Id", InputData[1].toString());
		if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))
				|| isDisplayed(xml.getlocator("//locators/SaveOrderChanges"))) {
			WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
			Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
			waitForpageload();
			System.out.println("page load succesfuuly now come to middle applet");
			waitforPagetobeenable();
		}
		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/SiteContactSearchSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search SiteB");
		Thread.sleep(10000);

		popupFieldSelection("Site Contact", "Last Name", InputData[7].toString());
		ClickHereSave();
		waitforPagetobeenable();
	}

	public void ASiteCustomize(Object[] InputData) throws Exception {
		waitForpageload();
		waitforPagetobeenable();
		if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")
				|| InputData[9].toString().equalsIgnoreCase("Wave")) {
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/CustomizeButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Customize Button");
			Select(getwebelement(xml3.getlocator("//locators/Coverage")), InputData[77].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Coverage");
			Thread.sleep(8000);
			Select(getwebelement(xml3.getlocator("//locators/ServiceBandwidth")), InputData[78].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Service Bandwidth");
			Thread.sleep(8000);
			Select(getwebelement(xml3.getlocator("//locators/AEndResilienceOption")), InputData[75].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select AEnd Resilience Option");
			Thread.sleep(2000);
			Select(getwebelement(xml3.getlocator("//locators/BEndResilienceOption")), InputData[76].toString());
			SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/BEndResilienceOption")), Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select BEnd Resilience Option");
			Thread.sleep(2000);

			if (InputData[9].toString().equalsIgnoreCase("Wave")) {
				Select(getwebelement(xml3.getlocator("//locators/subseaWorkerpath")), InputData[13].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Sub Sea Cable System (Worker Path) Option");
				Thread.sleep(2000);

				Select(getwebelement(xml3.getlocator("//locators/subseaprotectedpath")), InputData[14].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Sub Sea Cable System (Protected Path) Option");
				Thread.sleep(2000);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/subseaprotectedpath")), Keys.TAB);
				Thread.sleep(10000);
				// if(isElementPresent((xml3.getlocator("//locators/proceedbotton"))))
				// {
				// WaitforElementtobeclickable((xml3.getlocator("//locators/proceedbotton")));
				// Clickon(getwebelement(xml3.getlocator("//locators/proceedbotton")));
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Proceed
				// button");
				// }
			}

			// savePage();
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/Connectionlink")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection Link");
			Clickon(getwebelement(xml3.getlocator("//locators/AEndSite")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AEnd Site");
			Thread.sleep(10000);
			if (InputData[74].toString().equals("Offnet")) {

				waitforPagetobeenable();
				WaitforElementtobeclickable(xml3.getlocator("//locators/AccessTechnologySearch"));
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Search");
				Thread.sleep(20000);
				WaitforElementtobeclickable(xml3.getlocator("//locators/SelectAccessTechnology1"));
				Clickon(getwebelement(xml3.getlocator("//locators/SelectAccessTechnology1")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology value");
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySubmit")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Submit");
				Thread.sleep(5000);
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party  Access Provider Search");
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSelect")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party Access Provider Select");
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSubmit")));
				Thread.sleep(10000);
				// Select(getwebelement(xml3.getlocator("//locators/Siebel/ThirdPartyAccessProvider")),InputData[45].toString());
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
				// Access Provider");

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
				Clear(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
				Thread.sleep(500);
				SendKeys(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")), InputData[44].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Provide Third Party Connection Reference");
				Thread.sleep(5000);

				// WaitforElementtobeclickable(xml3.getlocator("//locators/ThirdPartySlaTier"));
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTier")));
				WaitforElementtobeclickable(xml3.getlocator("//locators/ThirdPartySlaTierValue"));
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTierValue")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party SlA Tier");
				Thread.sleep(5000);

			} else {

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessTechnologySearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Search");
				Thread.sleep(20000);
				safeJavaScriptClick(getwebelement("//table[@summary='Select Access Technology']//tr//td[text()='"
						+ InputData[119].toString() + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology value");
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySubmit")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Submit");
				Thread.sleep(5000);

				Select(getwebelement(xml3.getlocator("//locators/CustomerSitePopStatusselect")),
						InputData[94].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site Pop Status");
				Thread.sleep(5000);
				Select(getwebelement(xml3.getlocator("//locators/Buildingtyperfs")), InputData[93].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building");
				Thread.sleep(10000);

			}

			// if(InputData[98].toString().equalsIgnoreCase("Ethernet over NGN"))
			// {

			// }
			// else
			// {
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSearch")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party
			// Access Provider Search");
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSelect")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party
			// Access Provider Select");
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSubmit")));
			// Thread.sleep(10000);
			// Select(getwebelement(xml3.getlocator("//locators/Siebel/ThirdPartyAccessProvider")),InputData[45].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
			// Access Provider");

			// safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
			// Clear(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
			// Thread.sleep(500);
			// SendKeys(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")),InputData[65].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Provide Third Party
			// Connection Reference");
			// Thread.sleep(5000);
			//
			// Select(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTier")),InputData[66].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
			// SLA Tier");
			// Thread.sleep(5000);
			// }

			if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")) {
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/CPEInformationLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CPE Information Link");

				Select(getwebelement(xml3.getlocator("//locators/CabinetType")), InputData[96].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type");
				Thread.sleep(10000);
				getwebelement(xml3.getlocator("//locators/CabinetId")).clear();
				Thread.sleep(500);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/CabinetId")), InputData[97].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Enter Cabinet Id");
				// Clickon(getwebelement(xml3.getlocator("//locators/ShelfID")));
				getwebelement(xml3.getlocator("//locators/ShelfID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Shelf Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID");
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), Keys.TAB);
				Thread.sleep(10000);
				savePage();
				waitforPagetobeenable();

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessPortLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Link");
				Thread.sleep(10000);
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/PresentationInterfaceSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Search");
				Clickon(getwebelement(
						"//table[@summary='Select Presentation Interface-Connector Type']//tr//td[text()='"
								+ InputData[99].toString() + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Select Presentation Interface");
				Clickon(getwebelement(xml3.getlocator("//locators/SubmitPresentationInterface")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Presentation Interface");
				Thread.sleep(10000);
				// Clickon(getwebelement(xml3.getlocator("//locators/Proceed")));
				// Thread.sleep(5000);
				Select(getwebelement(xml3.getlocator("//locators/PortRole")), InputData[98].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Port Role");
				Thread.sleep(10000);

				getwebelement(xml3.getlocator("//locators/SlotID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Slot Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/SlotID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot Id");
				Thread.sleep(1000);
				getwebelement(xml3.getlocator("//locators/PhysicalPortID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Physical Port Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Physical Port Id");
				Thread.sleep(500);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), Keys.TAB);
				Thread.sleep(10000);
				if (InputData[98].toString().equalsIgnoreCase("Physical Port")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: No VLan Tagging mode required");
				} else {
					Clickon(getwebelement(xml3.getlocator("//locators/VLANTaggingMode")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN Tagging Mode");
					Select(getwebelement(xml3.getlocator("//locators/VLANTaggingMode")), InputData[100].toString());
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Update VLAN Tagging Mode");
					Thread.sleep(10000);
					safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/Vlannew")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN link");
					Thread.sleep(5000);
					getwebelement(xml3.getlocator("//locators/VlanTagId")).clear();
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Tag Id");
					SendKeys(getwebelement(xml3.getlocator("//locators/VlanTagId")), InputData[101].toString());
					SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/VlanTagId")), Keys.ENTER);
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Vlan Tag Id");
					savePage();
					waitforPagetobeenable();
					Thread.sleep(5000);
				}
			}

			if (InputData[9].toString().equalsIgnoreCase("Wave")) {
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/Terminationinformation")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Termination link");

				Select(getwebelement(xml3.getlocator("//locators/CabinetType")), InputData[96].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type");
				Thread.sleep(10000);
				getwebelement(xml3.getlocator("//locators/CabinetId")).clear();
				Thread.sleep(500);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/CabinetId")), InputData[97].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Enter Cabinet Id");
				// Clickon(getwebelement(xml3.getlocator("//locators/ShelfID")));
				getwebelement(xml3.getlocator("//locators/ShelfID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Shelf Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID");
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), Keys.TAB);
				Thread.sleep(10000);
				savePage();
				waitforPagetobeenable();

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessPortLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Link");
				Thread.sleep(10000);

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/PresentationInterfaceSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Search");
				Clickon(getwebelement(
						"//table[@summary='Select Presentation Interface-Connector Type']//tr//td[text()='"
								+ InputData[99].toString() + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Select Presentation Interface");
				Clickon(getwebelement(xml3.getlocator("//locators/SubmitPresentationInterface")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Presentation Interface");
				Thread.sleep(10000);

				getwebelement(xml3.getlocator("//locators/SlotID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Slot Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/SlotID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot Id");
				Thread.sleep(1000);
				getwebelement(xml3.getlocator("//locators/PhysicalPortID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Physical Port Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Physical Port Id");
				Thread.sleep(500);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), Keys.TAB);
				Thread.sleep(10000);
				savePage();
				waitforPagetobeenable();
			}

			Thread.sleep(10000);
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AEndSite")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on A End Site");
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/InstallationTimeLink")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time Link");
			Select(getwebelement(xml3.getlocator("//locators/InstallTime")), InputData[95].toString());
			SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/InstallTime")), Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time");
			Thread.sleep(2000);
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/Connectionlink")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection Link");
		}
	}

	public void BSiteCustomize(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Ethernet Line") || InputData[9].toString().equals("Wave")) {
			Clickon(getwebelement(xml3.getlocator("//locators/BEndSite")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on B End Site");

			if (InputData[74].toString().equals("Offnet")) {

				waitforPagetobeenable();
				WaitforElementtobeclickable(xml3.getlocator("//locators/AccessTechnologySearch"));
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Search");
				Thread.sleep(20000);
				WaitforElementtobeclickable(xml3.getlocator("//locators/SelectAccessTechnology1"));
				Clickon(getwebelement(xml3.getlocator("//locators/SelectAccessTechnology1")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology value");
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySubmit")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Submit");
				Thread.sleep(5000);
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party  Access Provider Search");
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSelect")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party Access Provider Select");
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSubmit")));
				Thread.sleep(10000);
				// Select(getwebelement(xml3.getlocator("//locators/Siebel/ThirdPartyAccessProvider")),InputData[45].toString());
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
				// Access Provider");

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
				Clear(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
				Thread.sleep(500);
				SendKeys(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")), InputData[44].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Provide Third Party Connection Reference");
				Thread.sleep(5000);

				// WaitforElementtobeclickable(xml3.getlocator("//locators/ThirdPartySlaTier"));
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTier")));
				WaitforElementtobeclickable(xml3.getlocator("//locators/ThirdPartySlaTierValue"));
				Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTierValue")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party SlA Tier");
				Thread.sleep(5000);

			} else {
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessTechnologySearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Search");
				Thread.sleep(10000);

				safeJavaScriptClick(getwebelement("//table[@summary='Select Access Technology']//tr//td[text()='"
						+ InputData[120].toString() + "']"));
				/* This one working in bsw */
				// safeJavaScriptClick(getwebelement(
				// "(//table[@summary='Select Access Technology']//tr//td[text()='Colt
				// Fibre']/following-sibling::td[text()='"
				// + InputData[120].toString() + "'])[1]"));

				// Clickon(getwebelement("//table[@summary='Select Access
				// Technology']//tr//td[text()='"+InputData[28+64].toString()+"']"));
				// SendKeys(getwebelement(xml3.getlocator("//locators/AccessTechnologyInput")),InputData[28+64].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Select");
				Clickon(getwebelement(xml3.getlocator("//locators/AccessTechnologySubmit")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Submit");
				Thread.sleep(5000);

				Select(getwebelement(xml3.getlocator("//locators/CustomerSitePopStatusBEnd")),
						InputData[111].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site Pop Status B End");
				Thread.sleep(5000);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building type BEnd");
				Thread.sleep(5000);

				Select(getwebelement(xml.getlocator("//locators/BuildingtypeBEnd")), InputData[110].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building type BEnd");
				Thread.sleep(5000);

			}
			// if(InputData[28+63].toString().equalsIgnoreCase("Ethernet over NGN"))
			// {
			// Select(getwebelement(xml3.getlocator("//locators/CustomerSitePopStatusBEnd")),InputData[28+55].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site
			// Pop Status B End");
			// Thread.sleep(5000);
			// Select(getwebelement(xml3.getlocator("//locators/BuildingtypeBEnd")),InputData[28+54].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building type
			// BEnd");
			// Thread.sleep(5000);
			// }
			// else
			// {
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSearch")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party
			// Access Provider Search");
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSelect")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Third Party
			// Access Provider Select");
			// Clickon(getwebelement(xml3.getlocator("//locators/ThirdPartyAccessProviderSubmit")));
			// Thread.sleep(10000);
			// Select(getwebelement(xml3.getlocator("//locators/Siebel/ThirdPartyAccessProvider")),InputData[45].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
			// Access Provider"

			// safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
			// Clear(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")));
			// Thread.sleep(500);
			// SendKeys(getwebelement(xml3.getlocator("//locators/ThirdPartyConnecRef")),InputData[65].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Provide Third Party
			// Connection Reference");
			// Thread.sleep(5000);
			//
			// Select(getwebelement(xml3.getlocator("//locators/ThirdPartySlaTier")),InputData[68].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Third Party
			// SLA Tier");
			// Thread.sleep(5000);

			// }

			if (InputData[9].toString().equalsIgnoreCase("Ethernet Line")) {
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/CPEInformationLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CPE Information Link");
				Select(getwebelement(xml3.getlocator("//locators/CabinetTypeBEnd")), InputData[113].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type B End");
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")));
				Thread.sleep(500);
				getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")).clear();
				Thread.sleep(500);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id BEnd");
				SendKeys(getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")), InputData[114].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet Id BEnd");
				getwebelement(xml3.getlocator("//locators/ShelfID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), "1234");
				Thread.sleep(10000);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), Keys.TAB);
				Thread.sleep(5000);
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessPortLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Link");
				Thread.sleep(5000);
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/PresentationInterfaceSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Search");
				Clickon(getwebelement(
						"//table[@summary='Select Presentation Interface-Connector Type']//tr//td[text()='"
								+ InputData[116].toString() + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Select Presentation Interface");
				Clickon(getwebelement(xml3.getlocator("//locators/SubmitPresentationInterface")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Presentation Interface");
				Thread.sleep(5000);
				// Clickon(getwebelement(xml3.getlocator("//locators/Proceed")));
				// Thread.sleep(5000);
				Select(getwebelement(xml3.getlocator("//locators/PortRoleBEnd")), InputData[115].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Port Role B End");
				Thread.sleep(5000);

				getwebelement(xml3.getlocator("//locators/SlotID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Slot Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/SlotID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot Id");
				Thread.sleep(1000);
				getwebelement(xml3.getlocator("//locators/PhysicalPortID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Physical Port Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Physical Port Id");
				Thread.sleep(500);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), Keys.TAB);
				Thread.sleep(10000);

				if (InputData[115].toString().equalsIgnoreCase("Physical Port")) {
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: No VLan Tagging mode required");
				} else {
					Clickon(getwebelement(xml3.getlocator("//locators/VLANTaggingMode")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN Tagging Mode");
					Select(getwebelement(xml3.getlocator("//locators/VLANTaggingMode")), InputData[117].toString());
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Update VLAN Tagging Mode");
					Thread.sleep(10000);
					Clickon(getwebelement(xml3.getlocator("//locators/VLAN")));
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN link");
					Thread.sleep(2000);
					getwebelement(xml3.getlocator("//locators/VlanTagId")).clear();
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Tag Id");
					SendKeys(getwebelement(xml3.getlocator("//locators/VlanTagId")), InputData[118].toString());
					SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/VlanTagId")), Keys.ENTER);
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Vlan Tag Id");
					savePage();
					waitforPagetobeenable();
					Thread.sleep(5000);
				}
			}
			if (InputData[9].toString().equalsIgnoreCase("Wave")) {
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/Terminationinformation")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Termination link");
				Thread.sleep(2000);

				Select(getwebelement(xml3.getlocator("//locators/CabinetTypeBEnd")), InputData[113].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type B End");
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")));
				Thread.sleep(500);
				getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")).clear();
				Thread.sleep(500);
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id BEnd");
				SendKeys(getwebelement(xml3.getlocator("//locators/CabinetIdBEnd")), InputData[114].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet Id BEnd");
				getwebelement(xml3.getlocator("//locators/ShelfID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), "1234");
				Thread.sleep(10000);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/ShelfID")), Keys.TAB);
				Thread.sleep(5000);
				Select AccessPort = new Select(getwebelement(xml.getlocator("//locators/AccessPortItem")));
				AccessPort.selectByVisibleText("Access Port");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Item");

				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/AccessPortLink")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Link");
				Thread.sleep(10000);
				safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/PresentationInterfaceSearch")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Search");
				Clickon(getwebelement(
						"//table[@summary='Select Presentation Interface-Connector Type']//tr//td[text()='"
								+ InputData[99].toString() + "']"));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Select Presentation Interface");
				Clickon(getwebelement(xml3.getlocator("//locators/SubmitPresentationInterface")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Presentation Interface");
				Thread.sleep(10000);
				Select PhysicalPort = new Select(getwebelement(xml.getlocator("//locators/PortRole")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on PortRole");
				PhysicalPort.selectByVisibleText("Physical Port");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Physical Port");

				WaitforElementtobeclickable(xml3.getlocator("//locators/SlotID"));
				getwebelement(xml3.getlocator("//locators/SlotID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Slot Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/SlotID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot Id");
				Thread.sleep(1000);
				getwebelement(xml3.getlocator("//locators/PhysicalPortID")).clear();
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Physical Port Id");
				SendKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), "1234");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Physical Port Id");
				Thread.sleep(500);
				SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/PhysicalPortID")), Keys.TAB);
				Thread.sleep(10000);
				savePage();
				waitforPagetobeenable();
			}
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/BEndSite")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on B End Site");
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/InstallationTimeLink")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time Link");
			Select(getwebelement(xml3.getlocator("//locators/InstallTimeBEnd")), InputData[112].toString());
			SendkeaboardKeys(getwebelement(xml3.getlocator("//locators/InstallTimeBEnd")), Keys.TAB);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time BEnd");
			Thread.sleep(2000);
			safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/DoneEthernetConnection")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Done Ethernet Connection");
			// AcceptJavaScriptMethod();
			Thread.sleep(20000);
		}
	}

	public void getReferenceNo(Object[] InputData) throws Exception, Exception {
		String Product = InputData[9].toString();
		if (Product.equalsIgnoreCase("Wave") || Product.equalsIgnoreCase("Ethernet Line")
				|| Product.equalsIgnoreCase("Ethernet VPN Access") || Product.equalsIgnoreCase("Dark Fibre")
				|| Product.equalsIgnoreCase("Ultra Low Latency") || Product.equalsIgnoreCase("Private Ethernet")
				|| Product.equalsIgnoreCase("DCA Ethernet") || Product.equalsIgnoreCase("Ethernet Hub")
				|| Product.equalsIgnoreCase("IP Access") || Product.equalsIgnoreCase("Ethernet Spoke")
				||Product.equalsIgnoreCase("Private Wave Service")) {
			waitforPagetobeenable();
			savePage();
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement("//a[text()='Sites']"));
			MiddleAppDropdown("OSS Platform Flag", "Legacy");
			ClickHereSave();
			GetReference();
			ClickHereSave();
			// Save();
			savePage();
		}
	}

	public void addEthernetSiteHub(Object[] InputData) throws Exception {
		if (InputData[9].toString().equalsIgnoreCase("Ethernet Hub")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Spoke")) {

			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/Searchbutton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Button");
			Thread.sleep(3000);
			SendKeys(getwebelement(xmlHns.getlocator("//locators/StreetName")), InputData[86].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/Country")), InputData[87].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/City")), InputData[88].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/PostalCode")), InputData[89].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/Premises")), InputData[90].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
			Clickon(getwebelement(xmlHns.getlocator("//locators/Search")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
			Clickon(getwebelement(xmlHns.getlocator("//locators/pickAddress")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");
			Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
			Clickon(getwebelement(
					"//table[@id='address_table']//tr//td[contains(text(),'" + InputData[91].toString() + "')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Building");
			Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
			Clickon(getwebelement(
					"//table[@id='address_table']//tr//td[contains(text(),'" + InputData[92].toString() + "')]"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Site");
			Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
			Thread.sleep(10000);
			safeJavaScriptClick(getwebelement(xmlHns.getlocator("locators/ServicePartySearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Search");
			Select(getwebelement(xmlHns.getlocator("//locators/SelectServiceParty")), "Party Name");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Service Party Search");

			SendKeys(getwebelement(xmlHns.getlocator("//locators/InputServicePartyName")), InputData[16].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Service Party Name");
			Clickon(getwebelement(xmlHns.getlocator("//locators/SearchServicePartyName")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Name");
			Clickon(getwebelement(xmlHns.getlocator("//locators/pickServiceParty")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party");
			Clickon(getwebelement(xmlHns.getlocator("//locators/PickButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Button");
			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SiteContactSearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/InputContactName")), InputData[17].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contact Name");
			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SearchContactName")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Contact Name");
			Clickon(getwebelement(xmlHns.getlocator("//locators/pickContactName")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Contact Name");
			Clickon(getwebelement(xmlHns.getlocator("//locators/PickContact")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Contact");
			Thread.sleep(10000);
		}
	}

	/*
	 * added by-Vikram for Hub product for Spoke
	 */
	public void hubSiteCustomize(Object[] InputData) throws Exception {

		waitforPagetobeenable();
		Thread.sleep(8000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Resilience Option")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[27].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthDropdownAccess")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/ServiceBandwidthSelectAccess").replace("value", InputData[32].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/ServiceBandwidthSelectAccess").replace("value", InputData[32].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Hard Modify Flag")));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "N")));
		Thread.sleep(2000);
		waitforPagetobeenable();

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "OSS Platform Flag")),
				InputData[38].toString());
		waitforAttributeloader();
		waitforPagetobeenable();
		Thread.sleep(3000);

		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));

		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));

		waitforPagetobeenable();

		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/SearchAddressSiteA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Address SiteA");
		SendKeys(getwebelement(xml3.getlocator("//locators/StreetNamerfs")), InputData[86].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");

		Clickon(getwebelement(xml3.getlocator("//locators/Country")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[87].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: public Ehternet >> Service Bandwidth Select : " + InputData[32].toString());
		// SendKeys(getwebelement(xml3.getlocator("//locators/Country")),
		// InputData[87].toString());
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml3.getlocator("//locators/City")), InputData[88].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml3.getlocator("//locators/PostalCode")), InputData[89].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml3.getlocator("//locators/Premises")), InputData[90].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xml3.getlocator("//locators/Search")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();

//	WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
//	Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
//	 Thread.sleep(8000);
//
//	waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartySearchAccess"));
		Moveon(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");

		// System.out.println("EnterService");
		waitandForElementDisplay((xml.getlocator("//locators/ServicePartyDropdownAccess")), 8);

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartyDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/ServicePartyDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Dropdown");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Party Name");

		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyNameAccess")), InputData[69].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");

		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactSearchAccess"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SiteContactSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Site Contact");

		waitandForElementDisplay((xml.getlocator("//locators/SiteContactDropdownAccess")), 8);
		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/SiteContactDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Name Dropdown");

		WaitforElementtobeclickable(xml.getlocator("//locators/InputSiteNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputSiteNameAccess")), InputData[70].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Site Name");

		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSearchAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
		waitforPagetobeenable();
		Thread.sleep(3000);
		// waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSubmitAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

		WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
		Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
		waitforPagetobeenable();

		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Access Type")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[42].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Access Technology")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[43].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Building Type")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[45].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Customer Site Pop Status")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[46].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Thread.sleep(2000);
		Clear(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "3rd Party Connection Reference")));
		SendKeys(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "3rd Party Connection Reference")),
				"As12");
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "BCP Reference")),
				"As12");
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Cabinet Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[48].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
				"12");
		SendkeaboardKeys(
				getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Presentation Interface")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[53].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Connector Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[54].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Fibre Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[55].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Port Role")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Physical Port")));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Install Time")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[56].toString())));
		waitforPagetobeenable();

//	WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
//	Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
//	waitforAttributeloader();
//	waitforPagetobeenable();
//	Thread.sleep(30000);
//
//	Circuitreferencenumber.set(Getattribute(getwebelement2(xml.getlocator("//locators/CircuitReferenceValue")),"value"));
//	System.out.println(Circuitreferencenumber.get());
//	ExtentTestManager.getTest().log(LogStatus.PASS,
//			" Step: Generated circuit reference No: " + Circuitreferencenumber.get());
//
		ClickHereSave();
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(5000);

	}

	public void CircuitReferenceGeneration(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
		waitforAttributeloader();
		waitforPagetobeenable();
		Thread.sleep(30000);

		Circuitreferencenumber
				.set(Getattribute(getwebelement2(xml.getlocator("//locators/CircuitReferenceValue")), "value"));
		System.out.println(Circuitreferencenumber.get());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated circuit reference No: " + Circuitreferencenumber.get());

		ClickHereSave();
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(8000);
	}

//		if (InputData[9].toString().equalsIgnoreCase("Ethernet Hub")) {
//
//			Clickon(getwebelement(xmlHns.getlocator("//locators/CustomizeButton")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Customize Button");
//			Thread.sleep(5000);
//			Select(getwebelement(xmlHns.getlocator("//locators/ServiceBandwidth")), InputData[32].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Service Bandwidth");
//			Thread.sleep(5000);
//			Select(getwebelement(xmlHns.getlocator("//locators/SelectResilienceOption")), InputData[75].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select ResilienceOption");
//			savePage();
//			waitforPagetobeenable();
//			Thread.sleep(5000);
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/EthernetConnectionLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection Link");
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/HubSiteLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Hub Site Link");
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/AccessTechnologySearch")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology Search");
//			Clickon(getwebelement("//table[@summary='Select Access Technology']//tr//td[text()='"
//					+ InputData[119].toString() + "']"));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Technology");
//			Clickon(getwebelement(xmlHns.getlocator("//locators/SubmitAccessTechnology")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Technology Search");
//			Thread.sleep(10000);
//			Select(getwebelement(xmlHns.getlocator("//locators/CustomerSitePopStatusselect")),
//					InputData[94].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site Pop Status");
//			Thread.sleep(5000);
//
//			Select(getwebelement(xmlHns.getlocator("//locators/BuildingType")), InputData[93].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type");
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/CPEInformationLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on CPE Information Link");
//			Thread.sleep(2000);
//			waitForpageload();
//			waitforPagetobeenable();
//			Select(getwebelement(xmlHns.getlocator("//locators/CabinetType")), InputData[96].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type");
//			Thread.sleep(4000);
//			getwebelement(xmlHns.getlocator("//locators/CabinetId")).clear();
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Cabinet ID");
//			Thread.sleep(2000);
//			SendKeys(getwebelement(xmlHns.getlocator("//locators/CabinetId")), InputData[97].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet ID");
//			Thread.sleep(2000);
//			getwebelement(xmlHns.getlocator("//locators/ShelfID")).clear();
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Shelf ID");
//			Thread.sleep(2000);
//			SendKeys(getwebelement(xmlHns.getlocator("//locators/ShelfID")), "1234");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID");
//			Thread.sleep(2000);
//			SendkeaboardKeys(getwebelement(xmlHns.getlocator("//locators/ShelfID")), Keys.TAB);
//			Thread.sleep(5000);
//			savePage();
//			waitforPagetobeenable();
//			Thread.sleep(5000);
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/AccessPortLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Access Port Link");
//			Thread.sleep(5000);
//
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/PresentationInterfaceSearch")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Search");
//			Clickon(getwebelement("//table[@summary='Select Presentation Interface-Connector Type']//tr//td[text()='"
//					+ InputData[99].toString() + "']"));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Presentation Interface Hub");
//			Clickon(getwebelement(xmlHns.getlocator("//locators/SubmitPresentationInterface")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Presentation Interface");
//			Thread.sleep(5000);
////	Clickon(getwebelement(xmlHns.getlocator("//locators/Proceed")));
////	Thread.sleep(5000);
//			Select(getwebelement(xmlHns.getlocator("//locators/PortRole")), InputData[98].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Port Role");
//			Thread.sleep(1000);
//			getwebelement(xmlHns.getlocator("//locators/SlotID")).clear();
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Slot Id");
//			SendKeys(getwebelement(xmlHns.getlocator("//locators/SlotID")), "1234");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot Id");
//			Thread.sleep(1000);
//			getwebelement(xmlHns.getlocator("//locators/PhysicalPortID")).clear();
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Physical Port Id");
//			SendKeys(getwebelement(xmlHns.getlocator("//locators/PhysicalPortID")), "1234");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Physical Port Id");
//			Thread.sleep(1500);
//			SendkeaboardKeys(getwebelement(xmlHns.getlocator("//locators/PhysicalPortID")), Keys.TAB);
//			Thread.sleep(10000);
//			if (InputData[98].toString().equalsIgnoreCase("Physical Port")) {
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: No VLan Tagging mode required");
//			} else {
//				Clickon(getwebelement(xmlHns.getlocator("//locators/VLANTaggingMode")));
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN Tagging Mode");
//				Select(getwebelement(xmlHns.getlocator("//locators/VLANTaggingMode")), InputData[100].toString());
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Update VLAN Tagging Mode");
//				Thread.sleep(10000);
//				safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/Vlannew")));
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on VLAN link");
//				Thread.sleep(5000);
//				getwebelement(xmlHns.getlocator("//locators/VlanTagId")).clear();
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Tag Id");
//				SendKeys(getwebelement(xmlHns.getlocator("//locators/VlanTagId")), InputData[101].toString());
//				SendkeaboardKeys(getwebelement(xmlHns.getlocator("//locators/VlanTagId")), Keys.ENTER);
//				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Vlan Tag Id");
//				savePage();
//				waitforPagetobeenable();
//				Thread.sleep(5000);
//			}
//
//			Thread.sleep(10000);
//
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/HubSiteLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Hub Site Link");
//			Clickon(getwebelement(xmlHns.getlocator("//locators/InstallationTimeLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time Link");
//			Select(getwebelement(xmlHns.getlocator("//locators/InstallTime")), InputData[95].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time");
//			waitForpageload();
//			waitforPagetobeenable();
//			WaitforElementtobeclickable(xmlHns.getlocator("//locators/DoneEthernetConnection"));
//			Clickon(getwebelement(xmlHns.getlocator("//locators/DoneEthernetConnection")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection");
//			Thread.sleep(30000);
//		}
//	}
//	/*
//	 * added by-Vikram for Hub product for Spoke
//	 */

	public void ManagedVirtualFirewall() throws Exception {
		waitForpageload();
		waitforPagetobeenable();
		OpenTab("Billing");

		WaitforElementtobeclickable(xml.getlocator("//locators/MVFShowfullInfo"));
		Clickon(getwebelement(xml.getlocator("//locators/MVFShowfullInfo")));
		Thread.sleep(5000);

		WaitforElementtobeclickable(xml.getlocator("//locators/MVFSecurityPolicyAttachmentlink"));
		Clickon(getwebelement(xml.getlocator("//locators/MVFSecurityPolicyAttachmentlink")));

		// 88888
		Random rand = new Random();
		int rand_int1 = rand.nextInt((99999 - 10000) + 1) + 10000;

		SendKeys(getwebelement(xml.getlocator("//locators/MVFSecurityPolicyAttachmentlink")),
				Integer.toString(rand_int1));
		ExtentTestManager.getTest().log(LogStatus.PASS, "Click  ManagedVirtualFirewall ShowfullInfo link ");
		Thread.sleep(3000);

		WaitforElementtobeclickable(xml.getlocator("//locators/MVFAddbutton"));
		Clickon(getwebelement(xml.getlocator("//locators/MVFAddbutton")));
		Thread.sleep(3000);

		closePopUp();
		waitForpageload();
		waitforPagetobeenable();
		Save();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void addEthernetSiteSpoke(Object[] InputData) throws Exception {
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SearchAddressSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Address SiteB");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/StreetName")), InputData[103].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/Country")), InputData[104].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/City")), InputData[105].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/PostalCode")), InputData[106].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/Premises")), InputData[107].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xmlHns.getlocator("//locators/Search")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");
		Clickon(getwebelement(xmlHns.getlocator("//locators/pickAddress")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");
		Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
		Clickon(getwebelement(
				"//table[@id='address_table']//tr//td[contains(text(),'" + InputData[108].toString() + "')]"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Building");
		Thread.sleep(20000);
		Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
		Clickon(getwebelement(
				"//table[@id='address_table']//tr//td[contains(text(),'" + InputData[109].toString() + "')]"));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Site");
		Clickon(getwebelement(xmlHns.getlocator("//locators/Pick")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");
		Thread.sleep(10000);
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/ServicePartySearchSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Search SiteB");
		Select(getwebelement(xmlHns.getlocator("//locators/SelectServiceParty")), "Party Name");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Service Party");
		SendKeys(getwebelement(xmlHns.getlocator("//locators/InputServicePartyName")), InputData[16].toString()); // Domain
																													// Name
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Service Party Name");
		Thread.sleep(10000);
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SearchServicePartyName")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Service Party Name");
		Clickon(getwebelement(xmlHns.getlocator("//locators/pickServiceParty")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Service Party");
		Clickon(getwebelement(xmlHns.getlocator("//locators/PickButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Button");
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SiteContactSearchSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Contact Search SiteB");
		Thread.sleep(10000);
		SendKeys(getwebelement(xmlHns.getlocator("//locators/InputContactName")), InputData[17].toString()); // DNS Type
																												// Input
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contact Name");
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SearchContactName")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Contact Name");
		Clickon(getwebelement(xmlHns.getlocator("//locators/pickContactName")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Contact Name");
		Clickon(getwebelement(xmlHns.getlocator("//locators/PickContact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Contact");
		Thread.sleep(10000);
	}

	/*
	 * added by-Vikram for Hub product for Spoke
	 */
	public void spokeSiteCustomize(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		Thread.sleep(8000);

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Coverage")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[28].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Resilience Option")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[27].toString())));

		// waitforAttributeloader();
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthDropdownAccess")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/ServiceBandwidthSelectAccess").replace("value", InputData[32].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/ServiceBandwidthSelectAccess").replace("value", InputData[32].toString())));
		// waitforAttributeloader();
		waitforPagetobeenable();

		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));

		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));

		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Hard Modify Flag")));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "N")));
		Thread.sleep(2000);
		waitforPagetobeenable();

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "OSS Platform Flag")),
				InputData[38].toString());
		waitforAttributeloader();
		waitforPagetobeenable();
		Thread.sleep(3000);

		WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));

		Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));

		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "HUB Service Id")));
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/HubTableIDSelect"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/HubTableIDSelect")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Select HubID");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/HubTableOK")));
		Thread.sleep(3000);

		waitforPagetobeenable();

		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/SearchAddressSiteB")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Address SiteB");
		SendKeys(getwebelement(xml3.getlocator("//locators/StreetNamerfs")), InputData[86].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");

		Clickon(getwebelement(xml3.getlocator("//locators/Country")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[87].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: public Ehternet >> Service Bandwidth Select : " + InputData[32].toString());
		// SendKeys(getwebelement(xml3.getlocator("//locators/Country")),
		// InputData[87].toString());
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml3.getlocator("//locators/City")), InputData[88].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml3.getlocator("//locators/PostalCode")), InputData[89].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml3.getlocator("//locators/Premises")), InputData[90].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises");
		Clickon(getwebelement(xml3.getlocator("//locators/Search")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick Address");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Pick");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();

//	WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
//	Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
//	 Thread.sleep(8000);
//
//	waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartySearchAccess"));
		Moveon(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");

		// System.out.println("EnterService");
		waitandForElementDisplay((xml.getlocator("//locators/ServicePartyDropdownAccess")), 8);

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartyDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/ServicePartyDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Dropdown");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Party Name");

		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyNameAccess")), InputData[69].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");

		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactSearchAccess"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SiteContactSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Site Contact");

		waitandForElementDisplay((xml.getlocator("//locators/SiteContactDropdownAccess")), 8);
		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/SiteContactDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Name Dropdown");

		WaitforElementtobeclickable(xml.getlocator("//locators/InputSiteNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputSiteNameAccess")), InputData[70].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Site Name");

		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSearchAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
		waitforPagetobeenable();
		Thread.sleep(3000);
		// waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSubmitAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

		WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
		Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
		waitforPagetobeenable();

//-----	
		if (InputData[74].toString().equalsIgnoreCase("offnet")) {
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Access Type")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[42].toString())));

			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Access Technology")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[43].toString())));

			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value",
					"Third Party Access Provider")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[44].toString())));
			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Third Party SLA Tier")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[35].toString())));
			waitforAttributeloader();
			waitforPagetobeenable();
		} else {

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Access Type")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[42].toString())));

			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Access Technology")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[43].toString())));

			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Building Type")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[45].toString())));
			waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value",
					"Customer Site Pop Status")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					InputData[46].toString())));
			waitforAttributeloader();
			waitforPagetobeenable();
		}
//------
		Thread.sleep(2000);
		Clear(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "3rd Party Connection Reference")));
		SendKeys(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "3rd Party Connection Reference")),
				"As12");
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "BCP Reference")),
				"As12");
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Cabinet Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[48].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Cabinet ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Cabinet ID")), "12");
		SendkeaboardKeys(
				getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Cabinet ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Shelf ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Shelf ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Shelf ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Slot ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Slot ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Slot ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Physical Port ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Physical Port ID")),
				"12");
		SendkeaboardKeys(
				getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "Physical Port ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Presentation Interface")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[53].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Connector Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[54].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Fibre Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[55].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Port Role")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Physical Port")));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "VLAN Tag ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "VLAN Tag ID")),
				"12");
		SendkeaboardKeys(
				getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputB").replace("Value", "VLAN Tag ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Install Time")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[56].toString())));
		waitforPagetobeenable();

		ClickHereSave();
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	public void OrderCompleteEthernetHubSpoke(Object[] InputData) throws Exception, Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
//		Select(getwebelement(xmlHns.getlocator("//locators/OSSPlatformFlag")), "Legacy");
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select OSS Platform Flag");
//		Thread.sleep(30000);
//		Clickon(getwebelement(xmlHns.getlocator("//locators/Getreferencebutton")));
//		Thread.sleep(20000);
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Get Reference button");
//		Thread.sleep(10000);
//
//		Circuitreferencenumber
//				.set(Getattribute(getwebelement(xmlHns.getlocator("//locators/ReferenceInput")), "value"));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Reference Input No: " + Circuitreferencenumber.get());
//		Log.info("Reference Input value: " + Circuitreferencenumber.get());
//		Clickon(getwebelement(xmlHns.getlocator("//locators/ServiceOrderTab")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Tab");
//		Thread.sleep(5000);
//
//		SendKeys(getwebelement(xmlHns.getlocator("//locators/InputOrderNo")), ServiceOrder.get());
//		// SendKeys(getwebelement(xmlHns.getlocator("//locators/InputOrderNo")),"871600916/190915-0033");
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Hub Order to Complete: " + ServiceOrder.get());
//		Thread.sleep(10000);
//
//		Clickon(getwebelement(xmlHns.getlocator("//locators/SearchOrderNo")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Order No");
//		Clickon(getwebelement(xmlHns.getlocator("//locators/ClickOrderNo")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order No");
//		Thread.sleep(30000);
		Select(getwebelement(xmlHns.getlocator("//locators/DropDown")), "Installation and Test");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation And Test Tab");
		Thread.sleep(10000);
		SendKeys(getwebelement(xmlHns.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Primary Testing Method");
		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(5000);

		Clickon(getwebelement(xmlHns.getlocator("//locators/ManualValidation")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Manual Validation");
		Thread.sleep(5000);
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/OrderStatus")));
		getwebelement(xmlHns.getlocator("//locators/OrderStatus")).clear();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Order Status");
//		Clickon(getwebelement(xmlHns.getlocator("//locators/WarningOk")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Ok in displayed Warning");
		Thread.sleep(10000);
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
		Thread.sleep(3000);
		Clickon(getwebelement(xmlHns.getlocator("//locators/SelectCompleted")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
		Thread.sleep(10000);
		Clickon(getwebelement(xmlHns.getlocator("//locators/OrderComplete")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
		Thread.sleep(50000);
	}

	/*
	 * added by-Vikram for Hub product for Spoke
	 */
	public void installationCharges(Object InputData[]) throws Exception {

		if (InputData[9].toString().equalsIgnoreCase("Ethernet Hub")
				|| (InputData[9].toString().equalsIgnoreCase("Ethernet Spoke"))) {

			Clickon(getwebelement(xmlHns.getlocator("//locators/ExpandAllButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Expand All Button");
			Thread.sleep(3000);
			Clickon(getwebelement(xmlHns.getlocator("//locators/InstallationChargeNRC")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Charge NRC");
			getwebelement(xmlHns.getlocator("//locators/InputinstallationChargeNRC")).clear();
			SendKeys(getwebelement(xmlHns.getlocator("//locators/InputinstallationChargeNRC")),
					InputData[23].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Installation Charge NRC");
			Clickon(getwebelement(xmlHns.getlocator("//locators/RecurringChargeMRC")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Recurring Charge MRC");
			getwebelement(xmlHns.getlocator("//locators/InputRecurringChargeMRC")).clear();
			SendKeys(getwebelement(xmlHns.getlocator("//locators/InputRecurringChargeMRC")), InputData[24].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Recurring Charge MRC");
			Clickon(getwebelement(xmlHns.getlocator("//locators/BCNInstallationChargeNRC")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Installation Charge NRC");
			Thread.sleep(1000);
			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/BCNInstallationChargeNRCSearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Installation Charge NRC Search");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/BCNInstallationChargeNRCInput")),
					InputData[25].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter BCN Installation Charge NRC Input");
			Clickon(getwebelement(xmlHns.getlocator("//locators/BCNNRCSubmit")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN NRC Submit");
			Clickon(getwebelement(xmlHns.getlocator("//locators/BCNRecurringChargeMRC")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Recurring Charge MRC");
			Thread.sleep(1000);
			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/BCNRecurringChargeMRCSearch")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Recurring Charge MRC Search");
			SendKeys(getwebelement(xmlHns.getlocator("//locators/BCNRecurringChargeMRCInput")),
					InputData[26].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter BCN Recurring Charge MRC Input");
			Clickon(getwebelement(xmlHns.getlocator("//locators/BCNNRCSubmit")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN NRC Submit");
			Thread.sleep(5000);
		}
	}

	public void serviceGroupReference() throws Exception {

		if (isElementPresent(xml.getlocator("//locators/ServiceGroupTab"))) {
			System.out.println("go to if loop of service group tab");
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceGroupTab"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on service tab");
		} else {
			System.out.println("service group tab present in dropdown");
			Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Service Group");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Click on Installation Dropdown button and Select Installation and Test");

			waitforPagetobeenable();
			waitForpageload();
		}
		Thread.sleep(2000);
		if (isElementPresent(xml.getlocator("//locators/ServiceOrderCheck"))) {

			System.out.println("Element is present");
			Boolean t = isElementPresent(xml.getlocator("//locators/ServiceOrderCheck"));
			System.out.println(t);
			if (t == true) {
				System.out.println("Service Record already exist!");
			}
		} else {
			System.out.println("Service Record not exist!");
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
		}

	}

	/*
	 * Added Devesh
	 */
	public void closePopUp() throws Exception {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopClose"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopClose")));
	}

	/* Added by Devesh for R4 Products */
	public void ShowfullInfo() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/ShowFullinfo"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/ShowFullinfo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click On : Show Full info");
		waitforPagetobeenable();
	}

	/* Added by Devesh for R4 Products */

	public void Save() throws InterruptedException, DocumentException {
		try {
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/Save"));
			if (isElementPresent(xml.getlocator("//locators/R4/Save"))
					&& isDisplayed(xml.getlocator("//locators/R4/Save"))) {

				Clickon(getwebelement(xml.getlocator("//locators/R4/Save")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click  on Save");
				waitforPagetobeenable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* Added by Devesh for R4 Products */
	public void publicEthernetEntry(Object[] InputData) throws InterruptedException, DocumentException {
		String ProductName = InputData[9].toString();
		if (ProductName.contains("Ethernet Access")) {
			System.out.println("Inside the case ethernet Access");
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Coverage"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Coverage")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[138].toString())));
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Service Bandwidth"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Service Bandwidth")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[32].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: public Ehternet >> Service Bandwidth Select : " + InputData[32].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "A End Resilience Option")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[75].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: public Ehternet >> A End Resilience Option Select : " + InputData[75].toString());

		if (!ProductName.contains("Ethernet Access")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "B End Resilience Option"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "B End Resilience Option")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[76].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: public Ehternet >> B End Resilience Option Select : " + InputData[32].toString());

		}
	}

	public void PopupDropdown(String dropDownName, String dropDownValue)
			throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", dropDownName));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", dropDownName)));
		Clickon(getwebelement(xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", dropDownValue)));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter " + dropDownName + " value as : " + dropDownName);
	}

	public void PopupInput(String textBoxName, String textBoxValue)
			throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopInput").replace("Value", textBoxName));
		ClearSendKeys(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", textBoxName)),
				textBoxValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter " + textBoxName + " value as : " + textBoxValue);

	}

	/* Added by Devesh for R4 Products */
	public void DiversityCircuitEntry(Object[] InputData) throws InterruptedException, DocumentException, IOException {

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link"));
		Clear(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")),
				"NA"/* InputData[33].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Diversity Circuit >> Attachment Link Entered : NA " /* + InputData[33].toString() */);

		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference"));
		Clear(getwebelement(
				xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")),
				"NA"/* InputData[34].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Diversity Circuit >> Diverse From Service Reference: NA" /* + InputData[34].toString() */);

		Clickon(getwebelement(xml.getlocator("//locators/R4/PopClose")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click  on Close");
	}

	/* Added by Devesh for R4 Products */
	public void SearchSiteA(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/LeftSiteSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/LeftSiteSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Site A ");
		waitforPagetobeenable(); // added by Ayush

	}

	/* Added by Devesh for R4 Products */
	public void SearchSiteB(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/RightSiteSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/RightSiteSearch")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Site B ");
	}

	/* Added by Devesh for R4 Products */
	public void SearchSiteAEntry(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name")),
				InputData[86].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street: " + InputData[86].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[87].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Country: " + InputData[87].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")),
				InputData[88].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City/Town : " + InputData[88].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")),
				InputData[89].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code : " + InputData[89].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")),
				InputData[90].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises : " + InputData[90].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchButton"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Address");
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();
	}

	/* Added by Devesh for R4 Products */
	public void SiteAServiceParty(Object[] InputData) throws Exception {
		if (isDisplayed((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Service Party"));
		safeJavaScriptClick(
				getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Service Party")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Service Party");
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	/* Added by Devesh for R4 Products */
	public void SiteBServiceParty(Object[] InputData) throws Exception {
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Service Party"));
		safeJavaScriptClick(
				getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Service Party")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Service Party");
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	/* Added by Devesh for R4 Products */
	public void SiteASiteContact(Object[] InputData) throws Exception {
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Site Contact"));
		safeJavaScriptClick(
				getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Site Contact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Site Contact");
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	/* Added by Devesh for R4 Products */
	public void SiteBSiteContact(Object[] InputData) throws Exception {
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Site Contact"));
		safeJavaScriptClick(
				getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Site Contact")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Search Site Contact");
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	/* Added by Devesh for R4 Products */
	public void PickServiceParty(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountDropdown")));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[68].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Field : " + InputData[68].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountValue"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")));
		Clear(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")), InputData[69].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter value : " + InputData[69].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountGo"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Go");

		/*
		 * WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountOk"));
		 * Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountOk")));
		 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Ok");
		 */
		waitforPagetobeenable();
		Thread.sleep(7000);
	}

	/* Added by Devesh for R4 Products */
	public void PickSiteContactParty(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountDropdown")));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[30].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Field : " + InputData[30].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAccountValue"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")));
		Clear(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/PickAccountValue")), InputData[31].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter value :" + InputData[31].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickContactGo"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickContactGo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Go");
		waitforPagetobeenable();
		Thread.sleep(5000);

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickContactOk"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickContactOk")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Ok");
		waitforPagetobeenable();
		Thread.sleep(5000);
	}

	/* Added by Devesh for R4 Products */
	public void ClickHereSave() throws InterruptedException, DocumentException {
		try { // Added by Ayush
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(10000);

			List<WebElement> savelist1 = GetWebElements(
					"//div[@class='colt-bottom-panel']//a[@class='colt-noedit-order-save colt-blue-color']");
			List<WebElement> savelist2 = GetWebElements("//div[contains(@style,'block')]//a[contains(text(),'save')]");
			List<WebElement> savelist3 = GetWebElements("//a[text()='Click here to save your order changes.']");
			if (savelist1.size() > 0) {
				System.out.println("I am save without popup found");
				Clickon(savelist1.get(0));
			}
			if (savelist2.size() > 0) {
				System.out.println("I am save with popup found");
				Clickon(savelist2.get(0));
			}
			if (savelist3.size() > 0) {
				System.out.println("I am save with popup found");
				Clickon(savelist3.get(0));
			}
			// WaitforElementtobeclickable((xml.getlocator("//locators/ClickheretoSaveAccess")));
			// WaitforElementtobeclickable("//div[@class='colt-bottom-panel']//a[@class='colt-noedit-order-save
			// colt-blue-color']");
			// Clickon(getwebelement(xml.getlocator("//locators/ClickheretoSaveAccess")));
			// Clickon(getwebelement("//div[@class='colt-bottom-panel']//a[@class='colt-noedit-order-save
			// colt-blue-color']"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on SaveAccess");
			waitforPagetobeenable();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/* Added by Devesh for R4 Products */
	public void SiteBInstallationTime(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {
			// Entering Install Time
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[112].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Install Time : " + InputData[112].toString());
			Thread.sleep(7000);
			waitforPagetobeenable();
		}
	}

	/* Added by Devesh for R4 Products */
	public void SearchSiteBEntry(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Street Name")),
				InputData[103].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street: " + InputData[103].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[104].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Country: " + InputData[104].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")),
				InputData[105].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City/Town : " + InputData[105].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")),
				InputData[106].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code : " + InputData[106].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")),
				InputData[107].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Premises : " + InputData[107].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchButton"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search");

		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Address");
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Building");

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Row and Pick Site");
		waitforPagetobeenable();
	}

	/*
	 * Created by Devesh for R4 Edited as per Ayush case
	 */
	public void SiteADiversityCircuitConfig(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {

		String ProductName = InputData[9].toString();

		switch (ProductName) {
		case "public Ethernet": {
			// Entering Customer Dedicated Access Ring
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
					"Customer Dedicated Access Ring"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
					"Customer Dedicated Access Ring")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[151].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Customer Dedicated Access Ring : " + InputData[151].toString());
			waitforPagetobeenable();

			// Diversity Type
			/*
			 * WaitforElementtobeclickable(
			 * xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
			 * "Diversity Type")); Clickon(getwebelement(
			 * xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
			 * "Diversity Type"))); Clickon(getwebelement(
			 * xml.getlocator("//locators/R4/SiteABSelection").replace("Value",
			 * InputData[152].toString()))); ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step: Select Diversity Type : " + InputData[152].toString());
			 * waitforPagetobeenable();
			 */

			// Entering Customer Dedicated Access Ring
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Dual Customer Power Source"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Dual Customer Power Source")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[152].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Dual Customer Power Source : " + InputData[152].toString());
			waitforPagetobeenable();
			break;
		}
		case "Ethernet Access": {

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")),
					"Akash.colt.net");

			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Select Diverse From Service Reference");
			SendKeys(getwebelement(
					xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")),
					"already exist");
			waitforPagetobeenable();
			break;
		}
		case "public Wave Service": {

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/PopInput").replace("Value", "Attachment Link")),
					"Ayush.colt.net");

			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Select Diverse From Service Reference");
			SendKeys(getwebelement(
					xml.getlocator("//locators/R4/PopInput").replace("Value", "Diverse From Service Reference")),
					"already exist");
			waitforPagetobeenable();
			break;
		}
		default: {

		}
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteBDiversityCircuitConfig(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {

		String ProductName = InputData[9].toString();
		if (ProductName == "public Ethernet") {
			// Entering Customer Dedicated Access Ring
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
					"Customer Dedicated Access Ring"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
					"Customer Dedicated Access Ring")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[154].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Customer Dedicated Access Ring : " + InputData[154].toString());
			waitforPagetobeenable();

			// Diversity Type
			/*
			 * WaitforElementtobeclickable(
			 * xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
			 * "Diversity Type")); Clickon(getwebelement(
			 * xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
			 * "Diversity Type"))); Clickon(getwebelement(
			 * xml.getlocator("//locators/R4/SiteABSelection").replace("Value",
			 * InputData[155].toString()))); ExtentTestManager.getTest().log(LogStatus.PASS,
			 * " Step: Select Diversity Type : " + InputData[155].toString());
			 * waitforPagetobeenable();
			 */

			// Entering Customer Dedicated Access Ring
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Dual Customer Power Source"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Dual Customer Power Source")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[156].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Dual Customer Power Source : " + InputData[156].toString());
			waitforPagetobeenable();
		}
	}

	/* Added by Devesh for R4 Products */
	public void publicEthernetMiddleAplet(Object[] InputData) throws InterruptedException, DocumentException {
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Coverage"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Coverage")));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[138].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Connection Type : " + InputData[138].toString());
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Connection Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Connection Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[27].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Connection Type : " + InputData[27].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[32].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select Service Bandwidth : " + InputData[32].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[75].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select A End Resilience Option : " + InputData[75].toString());

		waitForpageload();
		Thread.sleep(7000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));

		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[76].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select B End Resilience Option : " + InputData[76].toString());

		waitForpageload();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "OSS Platform Flag"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "OSS Platform Flag")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[38].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select OSS Platform Flag : " + InputData[38].toString());

		waitForpageload();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Bandwidth Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Bandwidth Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[28].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Bandwidth Type : " + InputData[28].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[29].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select Hard Modify Flag Type : " + InputData[29].toString());
	}

	/* Added by Devesh for R4 Products */
	public void SiteAInstallationTime(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")
				|| ProductName.equalsIgnoreCase("Ethernet Access")) {
			// Entering Install Time

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
			Thread.sleep(7000);
			waitforPagetobeenable();

		}
	}

	/* Added by Devesh for R4 Products */
	public void GetReference() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/GetReference"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/GetReference")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click  on GetReference");
		waitforPagetobeenable();
		Thread.sleep(30000);
		Circuitreferencenumber
				.set(Getattribute(getwebelement2(xml.getlocator("//locators/CircuitReferenceValue")), "value"));
		System.out.println(Circuitreferencenumber.get());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated circuit reference No: " + Circuitreferencenumber.get());
	}

	/*
	 * created by : Shivananda Rai Date : 14/09/2019 purpose : in order to create
	 * car-nor order we should update install time on modify car-nor order based on
	 * product please update the code in case checked
	 */
	public void installationTimeUpdate(Object[] InputData) throws Exception {
		switch (InputData[9].toString()) {
		case "DCA Ethernet": {
			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "public Wave Service": {
			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "public Wave Node": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "IP VPN Service": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ethernet Access": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ethernet VPN Access": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ethernet Spoke": {
			BEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ethernet Hub": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ethernet Line": {
			ModTechModCommWaveAndLine(InputData);
			ClickHereSave();
			break;
		}
		case "public Ethernet": {

			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Ultra Low Latency": {

			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();

			break;
		}
		case "IP Access": {
			AEndDropdownSelection("Install Time", "Business Hours");
			ClickHereSave();
			break;
		}
		case "Dark Fibre": {

			AEndDropdownSelection("Install Time", "Business Hours");
			BEndDropdownSelection("Install Time", "Business Hours");
			Save();
			waitforPagetobeenable();
			break;
		}
		case "Wave": {
			ModTechModCommWaveAndLine(InputData);
			ClickHereSave();
			break;
		}
		default: {
			break;
		}
		}
	}

	// Added by Rekha
	public void Carnor(Object[] InputData) throws Exception {
		WaitforElementtobeclickable(xml.getlocator("//locators/CarReason"));
		Clickon(getwebelement(xml.getlocator("//locators/CarReason")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Carnor reason field");
		SendKeys(getwebelement(xml.getlocator("//locators/CarReason")), "Access Line Change");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/CarReason")), Keys.TAB);
		Thread.sleep(10000);
		Clickon(getwebelement(xml.getlocator("//locators/CarNorRunFlag")));
		Thread.sleep(5000);
		Clickon(getwebelement(xml.getlocator("//locators/CarNorRunFlagValue")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered carnorFlag Value");
		Thread.sleep(10000);
		/*
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/ExistingCapacityLeadTimePrimary"));
		 * SendKeys(getwebelement(xml.getlocator(
		 * "//locators/ExistingCapacityLeadTimePrimary")), InputData[22].toString());
		 * ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step: Enter Existing Capacity Lead Time Primary");
		 * SendkeaboardKeys(getwebelement(xml.getlocator(
		 * "//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
		 * SendkeaboardKeys(getwebelement(xml.getlocator(
		 * "//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
		 * waitforPagetobeenable();
		 */
		savePage();
		Thread.sleep(10000);
		WaitforElementtobeclickable((xml.getlocator("//locators/CarNorButton")));
		Clickon(getwebelement(xml.getlocator("//locators/CarNorButton")));
		Thread.sleep(5000);
		Thread.sleep(20000);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.F5);
		waitforPagetobeenable();
		savePage();

		// below lines of code will work based on product / please update the as per
		// your product
		if (InputData[9].toString().equalsIgnoreCase("Wave")
				|| InputData[9].toString().equalsIgnoreCase("Ethernet Line")
				|| InputData[9].toString().equals("Ethernet Spoke")) {

			String Carnor = null;
			Carnor = getwebelement2(xml.getlocator("//locators/carnorOrderReferencevalue")).getAttribute("value");
			System.out.println(Carnor);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Generated Service Order Reference No: " + Carnor);
			Log.info("Carnor order No:" + Carnor);

		} else {

			CarNorOrderNumber.set(Gettext(getwebelement(xml.getlocator("//locators/Carnororder"))));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Generated Carnor Order Number: " + CarNorOrderNumber.get());
			Thread.sleep(30000);
		}
	}

	public void OperationAttribute_Carnor(Object[] InputData)
			throws IOException, InterruptedException, DocumentException {
		if (InputData[9].toString().equals("Voice Line V")) {

			Clickon(getwebelement(xml.getlocator("//locators/Voiceconfigtab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Voiceconfigtab");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/OtherTab")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OtherTab");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute1"));
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute1")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/AttributeValue1")));
			SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")), "test");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/OperationAttribute2"));
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttribute2")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OperationAttribute");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/AttributeValue1")));
			SendKeys(getwebelement(xml.getlocator("//locators/AttributeValue1")), "test");
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/OperationAttributeSubmit")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit ");
			Thread.sleep(5000);
		}

	}

	/*
	 * Created by: Aman Gupta Purpose: Specific for middle applet of Managed
	 * Dedicated Firewall
	 */
	public void middleAppletManagedDedicatedFirewall(Object[] InputData) throws Exception {
		waitForpageload();
		waitforPagetobeenable();
		System.out.println("valu of 178=" + InputData[48].toString());
		System.out.println("valu of 178=" + InputData[49].toString());

		MiddleAppTextBox("CPE Combination ID", InputData[48].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequired")
				.replace("Value", "High Availability Required"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequired")
				.replace("Value", "High Availability Required")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  High Avilabilty");

		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequiredli"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequiredli")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Yes");

		MiddleAppTextBox("Security Policy Attachment Link", InputData[49].toString());

		Save();
		waitForpageload();
		waitforPagetobeenable();
	}

	/*
	 * By Aman
	 */
	public void middleAppletpublicWaveNode(Object[] InputData) throws Exception {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Network Topology"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Network Topology")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Network Topology");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Point to Point Single Node"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Point to Point Single Node")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Point to Point Single Node");

		OperationalAttributeUltra(InputData);
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	/*
	 * By Aman
	 */
	public void middleUltraLowLatency(Object[] InputData) throws Exception {
		middleAppletDarkFibre(InputData);
	}

	/*
	 * By Aman
	 */
	public void AEndSitePUD(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();

		if (InputData[9].toString().contains("Ultra Low Latency")
				|| (InputData[9].toString().contains("public Wave Node")
						|| (InputData[9].toString().equalsIgnoreCase("public Wave Service")
								|| (InputData[9].toString().equalsIgnoreCase("Dark Fibre")))))

		{

			if (InputData[74].toString().contains("Offnet")) {
				// for selecting access type as 3rd party leased line//
				// Added by Ayush//
				System.out.println("Enter into offnet part of A end site pud");
				WaitforElementtobeclickable(xml.getlocator("//locators/AccessTypeDropdownAccess"));
				Clickon(getwebelement(xml.getlocator("//locators/AccessTypeDropdownAccess")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select A end access type");
				// WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet")+"[1]");
				// Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet")+"[1]"));
				WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
				Thread.sleep(20000);
				// For access type technology//
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology : NA ");

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[83].toString());

				// for 3rd party connection provider//
				Thread.sleep(2000);
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessproviderDropDown"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessproviderDropDown")));
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString())));

				// for 3rd party connection reference//
				Clear(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")));
				Clickon(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")));
				SendKeys(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")), "12");

				// for third party SLA tier//
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyDropDown"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyDropDown")));
				// WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartySLATiervalueultra").replace("value",
				// "1"));
				// Clickon(getwebelement(xml.getlocator("//locators/ThirdpartySLATiervalueultra").replace("value",
				// "1")));
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartySLATiervalue").replace("SLAValue",
						InputData[35].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartySLATiervalue").replace("SLAValue",
						InputData[35].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));

				waitforPagetobeenable();
				Thread.sleep(20000);

			} // out of if inner loop

			else {
				System.out.println("Enter into new order part of B end site pud");

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology")));
//
//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology : NA ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Type : " + InputData[83].toString());
				Thread.sleep(2000);
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "Colt Fibre"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "Colt Fibre")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Colt Fibre ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[42].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[42].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[83].toString());
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Demarcation Device Required"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Demarcation Device Required")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "No"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "No")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select No ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[81].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[81].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Demarcation Device Required : " + InputData[81].toString());

			}
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Building Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Building Type")));

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type : NA ");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")),
				"NA"/* InputData[180].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  BCP Reference : NA");
		waitForpageload();
		waitforPagetobeenable();

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Customer Site Pop Status"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Customer Site Pop Status")));
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site Pop Status Type : NA ");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "DSL SLA Class"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "DSL SLA Class")));
//		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "N/A"));
//		Thread.sleep(3000);
//		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "N/A")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select DSL SLA Class Type : N/A ");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[78].toString()));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[78].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select DSL SLA Class Type :" + InputData[78].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")));
		SendKeys(
				getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")),
				"NA"/* InputData[180].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Site Name Alias : NA");

		if (InputData[9].toString().contains("public Wave Node")
				|| InputData[9].toString().contains("public Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Site Name"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Site Name")));
			Clear(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Site Name")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Site Name")),
					"NA"/* InputData[180].toString() */);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  onNode Site Name: NA");
		}
		if (InputData[9].toString().contains("public Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Service ID"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Service ID")));
			Clear(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Service ID")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Node Service ID")),
					"BIGT14124"/* InputData[180].toString() */);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Node Service ID: BIGT14124");
		}
	}

	public void BEndSitePUD(Object[] InputData) throws InterruptedException, DocumentException, IOException {

		if (InputData[9].toString().contains("Ultra Low Latency")
				|| InputData[9].toString().contains("public Wave Node")
				|| InputData[9].toString().contains("Dark Fibre"))

		{

			if (InputData[74].toString().contains("Offnet")) {
				// for selecting access type as 3rd party leased line//
				// Added by Ayush//
				System.out.println("Enter into offnet part of B end site pud");
				WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypedropdownB"));
				Clickon(getwebelement(xml.getlocator("//locators/AccesstypedropdownB")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select B end access type");
				// WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet")+"[1]");
				// Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet")+"[1]"));
				WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
				Thread.sleep(20000);
				// For access type technology//
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology : NA ");

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[83].toString());

				// for 3rd party connection provider//

				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessproviderDropDownB"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessproviderDropDownB")));
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString())));

				// for 3rd party connection reference//
				Clear(getwebelement(xml.getlocator("//locators/ThirdpartyconectionreferenceB")));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyconectionreferenceB")));
				SendKeys(getwebelement(xml.getlocator("//locators/ThirdpartyconectionreferenceB")), "12");

				// for third party SLA tier//
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyDropDownB"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyDropDownB")));
				// WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartySLATiervalueultra").replace("value",
				// "1"));
				// Clickon(getwebelement(xml.getlocator("//locators/ThirdpartySLATiervalueultra").replace("value",
				// "1")));
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartySLATiervalue").replace("SLAValue",
						InputData[35].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartySLATiervalue").replace("SLAValue",
						InputData[35].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));

				waitforPagetobeenable();
				Thread.sleep(20000);

			} // out of if inner loop

			else {

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology")));
				//
				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "NA"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "NA")));
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access
				// Technology : NA ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Type : " + InputData[83].toString());

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Type"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Type")));

				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "Colt Fibre"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "Colt Fibre")));
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Colt Fibre ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[84].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[84].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[84].toString());
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
						"Demarcation Device Required"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
						"Demarcation Device Required")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "No"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "No")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select No ");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[81].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[81].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Demarcation Device Required : " + InputData[81].toString());

			}
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Building Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Building Type")));
//		WaitforElementtobeclickable(
//				xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[85].toString()));
//		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[85].toString())));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type : " + InputData[85].toString());

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BList").replace("Value", "NA"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BList").replace("Value", "NA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type : NA ");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")),
				"NA"/* InputData[180].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  BCP Reference : NA");
		waitForpageload();
		waitforPagetobeenable();

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Customer Site Pop Status"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Customer Site Pop Status")));
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BList").replace("Value", "NA"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BList").replace("Value", "NA")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Customer Site Pop Status Type : NA ");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "DSL SLA Class"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "DSL SLA Class")));
//		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "N/A"));
//		Thread.sleep(3000);
//		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "N/A")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select DSL SLA Class Type : N/A ");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[78].toString()));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[78].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select DSL SLA Class Type :" + InputData[78].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")));
		SendKeys(
				getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")),
				"NA"/* InputData[180].toString() */);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Site Name Alias : NA");

		if (InputData[9].toString().contains("public Wave Node")
				|| InputData[9].toString().contains("public Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Site Name"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Site Name")));
			Clear(getwebelement(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Site Name")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Site Name")),
					"NA"/* InputData[180].toString() */);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  onNode Site Name: NA");
		}
		if (InputData[9].toString().contains("public Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Service ID"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Service ID")));
			Clear(getwebelement(
					xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Service ID")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Node Service ID")),
					"BIGT14124"/* InputData[180].toString() */);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Node Service ID: BIGT14124");
		}
	}
	/*
	 * Aman created
	 */

	public void SiteATerminationTimePUD(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		String ProductName;
		ProductName = InputData[9].toString();
		// InputData[2].toString();

		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Dark Fibre"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))
				|| (ProductName.equalsIgnoreCase("public Wave Node"))) {
			// CabinetID
			String rand_int1 = GetRandomNumberString(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID")),
					rand_int1);
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + rand_int1);

			// Shelf ID
			rand_int1 = GetRandomNumberString(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID")),
					rand_int1);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " + rand_int1);
			waitforPagetobeenable();

			waitforPagetobeenable();

			// CabinetType
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Cabinet Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Cabinet Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "Customer Cabinet")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type : Customer Cabinet");
			waitforPagetobeenable();

			// LinkAggregationRequired

			if (!(InputData[9].toString().contains("Ultra Low Latency"))
					&& !(InputData[9].toString().contains("Dark Fibre")) &&

					!(InputData[9].toString().contains("public Wave Node"))
					&& !(InputData[9].toString().contains("public Wave Service"))) {
				WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
						"Link Aggregation Required"));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
						"Link Aggregation Required")));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "No")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Link Aggregation Required : No");
				waitforPagetobeenable();
			}

		}
	}

	/*
	 * Created by Aman
	 */
	public void SiteBTerminationTimePUD(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();
		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Dark Fibre"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))) {
			// CabinetID
			int rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int1));
			waitforPagetobeenable();

			// CabinetType
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Cabinet Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Cabinet Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "Customer Cabinet")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Cabinet Type : Customer Cabinet");
			waitforPagetobeenable();

			if (!(InputData[9].toString().contains("Ultra Low Latency"))
					&& !(InputData[9].toString().contains("Dark Fibre"))
					&& !(InputData[9].toString().contains("public Wave Service"))) {
				// LinkAggregationRequired
				WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
						"Link Aggregation Required"));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value",
						"Link Aggregation Required")));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "No")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Link Aggregation Required : No");
				waitforPagetobeenable();
			}

			// Shelf ID
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " + Integer.toString(rand_int1));
			waitforPagetobeenable();
		}
	}

	/*
	 * Created by Aman
	 * 
	 */
	public void SiteBInstallationTimePUD(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Dark Fibre"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))) {
			System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			// Entering Install Time
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[112].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Install Time : " + InputData[112].toString());
			Thread.sleep(7000);
			waitforPagetobeenable();
		}
	}

	/*
	 * Created by Aman
	 */

	public void SiteAInstallationTimePUD(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		String ProductName = InputData[9].toString();

		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Dark Fibre"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))
				|| (ProductName.equalsIgnoreCase("public Wave Node"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))) {
			// Entering Install Time
			System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
			Thread.sleep(7000);
			waitforPagetobeenable();

		}
	}

	/*
	 * Created by Aman
	 */
	public void SiteAAccessPortPUD(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();
		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Ultra Low Latency"))) {
			int rand_int1 = rand.nextInt(1000);
			// Presentation Interface
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Presentation Interface"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Presentation Interface")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[99].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Presentation Interface: " + InputData[99].toString());
			waitforPagetobeenable();
			if ((ProductName.equalsIgnoreCase("public Ethernet"))) {
				// VLAN Tagging Mode
				WaitforElementtobeclickable(
						xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "VLAN Tagging Mode"));
				Clickon(getwebelement(
						xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "VLAN Tagging Mode")));
				Clickon(getwebelement(
						xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[100].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select VLAN Tagging Mode : " + InputData[100].toString());
				waitforPagetobeenable();
			}
		}
		if ((ProductName.equalsIgnoreCase("Dark Fibre")) || (ProductName.equalsIgnoreCase("public Ethernet"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))) {
			int rand_int1 = rand.nextInt(1000);
			// Connector Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Connector Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Connector Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[54].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Connector Type : " + InputData[54].toString());
			waitforPagetobeenable();

			// Fibre Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Fibre Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Fibre Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[55].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Fibre Type : " + InputData[55].toString());
			waitforPagetobeenable();
			// Physical Port ID
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID")));
			if (InputData[101].toString() == "") {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID")),
						Integer.toString(rand_int1));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Physical Port ID : " + Integer.toString(rand_int1));
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID")),
						InputData[101].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Physical Port ID : " + InputData[101].toString());
			}
			waitforPagetobeenable();

			// Slot ID
			rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID")));
			if (InputData[66].toString() == "") {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID")),
						Integer.toString(rand_int1));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Enter Slot ID : " + Integer.toString(rand_int1));
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID")),
						InputData[66].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " + InputData[66].toString());
			}
			waitforPagetobeenable();
		}

	}

	public void SiteBAccessPortPUD(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();

		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Dark Fibre"))
				|| (ProductName.equalsIgnoreCase("public Wave Service"))
				|| (ProductName.equalsIgnoreCase("Ultra Low Latency"))) {
			// Connector Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Connector Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Connector Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[54].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Connector Type : " + InputData[54].toString());
			waitforPagetobeenable();

			// Fibre Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Fibre Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Fibre Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[55].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Fibre Type : " + InputData[55].toString());
			waitforPagetobeenable();

			// Physical Port ID
			int rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID")));
			if (InputData[115].toString() == "") {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID")),
						Integer.toString(rand_int1));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Physical Port ID : " + Integer.toString(rand_int1));
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID")),
						InputData[101].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Physical Port ID : " + InputData[115].toString());
			}
			waitforPagetobeenable();

			// Slot ID
			rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID")));
			if (InputData[67].toString() == "") {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID")),
						Integer.toString(rand_int1));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Enter Slot ID : " + Integer.toString(rand_int1));
			} else {
				SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID")),
						InputData[67].toString());
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " + InputData[66].toString());
			}
			waitforPagetobeenable();
		}

		if ((ProductName.equalsIgnoreCase("public Ethernet")) || (ProductName.equalsIgnoreCase("Ultra Low Latency"))) {
			// Presentation Interface
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Presentation Interface"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Presentation Interface")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[116].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Presentation Interface: " + InputData[116].toString());
			waitforPagetobeenable();

			if ((ProductName.equalsIgnoreCase("public Ethernet"))) {
				// VLAN Tagging Mode
				WaitforElementtobeclickable(
						xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "VLAN Tagging Mode"));
				Clickon(getwebelement(
						xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "VLAN Tagging Mode")));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select VLAN Tagging Mode : " + InputData[121].toString());
				waitforPagetobeenable();
			}

		}
	}

	/*
	 * Created by Aman
	 */
	public void middleAppletDarkFibre(Object[] InputData) throws Exception {
		waitForpageload();
		waitforPagetobeenable();

		if (InputData[9].toString().contains("Ultra Low Latency")
				|| InputData[9].toString().contains("public Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Service Bandwidth");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[32].toString()));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[32].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  4 Mbps");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Bandwidth Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Bandwidth Type")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Bandwidth Type");

			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Layer 1"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Layer 1")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Layer 1");

		}

		if ((InputData[9].toString().contains("Dark Fibre"))
				|| (InputData[9].toString().contains("Ultra Low Latency"))) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Coverage"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Coverage")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Coverage");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[138].toString()));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[138].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  " + InputData[138]);
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		if (!(InputData[9].toString().contains("Ultra Low Latency"))
				&& !(InputData[9].toString().contains("public Wave Service"))) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Single pair");
		}
		if (InputData[9].toString().contains("Ultra Low Latency")
				|| (InputData[9].toString().contains("public Wave Service"))) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[75].toString()));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[75].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  " + InputData[75]);
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  B End Resilience Option");
		if (!(InputData[9].toString().contains("Ultra Low Latency"))
				&& !(InputData[9].toString().contains("public Wave Service"))) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Single pair");
		}
		if ((InputData[9].toString().contains("Ultra Low Latency")
				|| (InputData[9].toString().contains("public Wave Service")))) {
			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Protected");
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "OSS Platform Flag"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "OSS Platform Flag")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OSS Platform Flag ");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Legacy"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Legacy")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Metro");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Hard Modify Flag");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Y"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Y")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  yes");

		if ((InputData[9].toString().contains("public Wave Service"))) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Circuit Category"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Circuit Category")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Hard Modify Flag");

			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "LANLINK"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "LANLINK")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  LANLINK");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Type")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Service Type");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "100 Gbps Ethernet"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "100 Gbps Ethernet")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  100 Gbps Ethernet");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Circuit Prefix"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Circuit Prefix")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Circuit Prefix");

			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "LE-"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "LE-")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  LE-");
		}

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/ClicktoShowFulls"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/ClicktoShowFulls")));
//			
		//
//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Attachment Link"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Attachment Link")));
//			SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Attachment Link")),InputData[187].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Attachment Link");
//			
		//
//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Diverse From Service Reference"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Diverse From Service Reference")));
//			SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Diverse From Service Reference")),InputData[187].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on Site Name Alias");
		//
////			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Circuit Reference"));
////			Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/MiddleDropDown").replace("Value", "Circuit Reference")));
////			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Coverage");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();

	}
	/*
	 * Created by Aman
	 */

	public void settingpublicWaveNode(Object[] InputData) throws Exception {
		waitForpageload();
		waitforPagetobeenable();
		savePage();
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/SettingsButton"));
		Thread.sleep(1000);

		// Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/Dipesh")));

		// clickUsingAction(getwebelement(xml.getlocator("//locators/publicWaveNode/SettingsButton")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/publicWaveNode/SettingsButton")));
		// waitforPagetobeenable();
		Thread.sleep(10000);

		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/InputValuesVendor"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")));
		SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")), "ADVA");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")), Keys.TAB);

		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/TechonolgyValuecell"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/TechonolgyValuecell")));
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy"));
		SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy")), "CWDM");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy")), Keys.TAB);

		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/OKButton"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/OKButton")));
	}

	/*
	 * Created by Ayush
	 * 
	 */

	public void IPCPESolutionService(Object[] InputData) throws Exception {
		System.out.println("enter into if loop of cpe solution");
		MiddleAppDropdown("A End Resilience Option", InputData[47].toString());
		MiddleAppDropdown("B End Resilience Option", InputData[48].toString());
		MiddleAppDropdown("Network Topology", InputData[49].toString());
		MiddleAppDropdown("Service Bandwidth", InputData[50].toString());
		MiddleAppDropdown("Service Type", InputData[51].toString());
		ClickHereSave();
	}
	public void OperationalAttributes(Object[] InputData) throws Exception {

		waitForpageload();
		savePage();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SettingsButton"));
		Thread.sleep(10000);
		Clickon(getwebelement(xml.getlocator("//locators/R4/SettingsButton")));
		waitforPagetobeenable();
		Thread.sleep(10000);
		int count = getwebelementscount(xml.getlocator("//locators/publicWaveService/OperationalAttribueClick"));
		System.out.println(count);
		for (int i = 0; i < count; i++) {

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/OperationalAttribueClick"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/OperationalAttribueClick")
					.replace("index", String.valueOf(i + 1))));
			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/OperationalAttributeDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/OperationalAttributeDropdown")));
			WaitforElementtobeclickable(
					xml.getlocator("//locators/SelectValueDropdown").replace("Value", InputData[7].toString()));
			Clickon(getwebelement(
					xml.getlocator("//locators/SelectValueDropdown").replace("Value", InputData[7].toString())));
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/OkButtonOperationalAttribute"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/OkButtonOperationalAttribute")));

		}
	}

	/*
	 * Added by Ayush
	 */
	public void SaveAndCloseMask() throws Exception {
		if (isDisplayed(xml.getlocator("//locators/R4/SaveEthernetAccess"))) {
			System.out.println("enter if loop of save button");
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SaveEthernetAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SaveEthernetAccess")));
			waitforPagetobeenable();
		}
		Thread.sleep(15000);
		if (isDisplayed(xml.getlocator("//locators/ClickheretoSaveAccess"))) {
			System.out.println("enter if loop of configuration");
			ClickHereSave();
		}
	}
	/*
	 * Added by Ayush
	 */

	public void publicWaveServiceEntry(Object[] InputData) throws Exception {
		String ProductName = InputData[9].toString();
		System.out.println("product is:" + ProductName);
		if (ProductName.contains("public Wave Service")) {
			System.out.println("Inside the case public wave service");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Service Bandwidth"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "Service Bandwidth")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", InputData[32].toString())));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "A End Resilience Option"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/PopDropdownClick").replace("Value", "A End Resilience Option")));
			Clickon(getwebelement(xml.getlocator("//locators/R4/InsideDropdownValues").replace("Data", "Protected")));

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/BendResillenceOption"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/BendResillenceOption")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on B end resillience option");
			Thread.sleep(3000);

			Clickon(getwebelement(xml.getlocator("//locators/CPESolutionService/ValuesInsideDropdownWithIndex")
					.replace("Data", InputData[182].toString()).replace("value", "2")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select value from B end resillence option");

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/NetworkTopologypublicWave"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/NetworkTopologypublicWave")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on network topology");

			Clickon(getwebelement(xml.getlocator("//locators/CPESolutionService/ValueInsideDropdown").replace("Data",
					InputData[183].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select value from network topology");
			Thread.sleep(3000);

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/ServiceTypepublic"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/ServiceTypepublic")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on service type");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/CPESolutionService/ValueInsideDropdown").replace("Data",
					InputData[185].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select value from service type dropdown");
		}
	}

	/*
	 * Added by Ayush
	 */
	public void EthernetAccessNewFields(Object[] InputData) throws Exception {
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/R4/NetworkTopology")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click network topology");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/NetworkTopology")), Keys.TAB);

		// for enter value in circuit reference
		if (InputData[9].toString().contains("Ethernet Access")) {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/CircuitReference")), "2");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in circuit reference");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/CircuitReference")), Keys.TAB);
		}
		// for enter value in oss platform
		Thread.sleep(3000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/ClickDropdown").replace("Value", "OSS Platform Flag"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/ClickDropdown").replace("Value", "OSS Platform Flag")));
		Thread.sleep(3000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Legacy"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Legacy")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select OSS platform flag : Legacy ");

		ClickHereSave();

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in oss platform ");
		waitforPagetobeenable();// new add
		Thread.sleep(5000); // wait 8 to 10
		savePage();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SettingsButton"));
		Thread.sleep(10000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/R4/SettingsButton")));

		waitforPagetobeenable();
		Thread.sleep(10000); // wait 8 to 10
		if (InputData[9].toString().contains("Ethernet Access")) {
			System.out.println("go to if loop");
			for (int i = 0; i < 5; i++) {
				System.out.println("go to for loop");
				if (!isDisplayed(xml.getlocator("//locators/R4/AttributeValueEthernetAccess"))) {
					Thread.sleep(3000);
				} else {
					System.out.println("go to else loop");
					SendKeys(getwebelement2(xml.getlocator("//locators/R4/AttributeValueEthernetAccess")), "test1");
					SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeValueEthernetAccess")),
							Keys.TAB);
					Thread.sleep(5000);
					WaitforElementtobeclickable(xml.getlocator("//locators/R4/OkButtonOperationalAttribute"));
					Clickon(getwebelement(xml.getlocator("//locators/R4/OkButtonOperationalAttribute")));

					Thread.sleep(3000);
					if (isDisplayed("(//div[@class='ui-dialog-buttonset']/button)[2]")) {
						boolean t = isDisplayed("(//div[@class='ui-dialog-buttonset']/button)[2]");
						System.out.println(t);
						WaitforElementtobeclickable("(//div[@class='ui-dialog-buttonset']/button)[2]");
						Thread.sleep(3000);
						Clickon(getwebelement("(//div[@class='ui-dialog-buttonset']/button)[2]"));
						Thread.sleep(4000);
						waitforPagetobeenable();
						break;
					} // end of if loop
				} // end of else loop

			} // end of for loop
		} // end of if loop
		if (InputData[9].toString().contains("public Wave Service")) {
			System.out.println("enter if loop of public wave service");
			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/InputValuesProtection"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/InputValuesProtection")));
			SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveService/InputValuesProtection")),
					"No Protection");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveService/InputValuesProtection")),
					Keys.TAB);

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/VendorValuecell"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/VendorValuecell")));
			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/InputValuesVendor"));
			SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveService/InputValuesVendor")), "ADVA");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveService/InputValuesVendor")),
					Keys.TAB);

			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/TechonolgyValuecell"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/TechonolgyValuecell")));
			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/InputvaluesTechonolgy"));
			SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveService/InputvaluesTechonolgy")), "CWDM");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveService/InputvaluesTechonolgy")),
					Keys.TAB);

			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveService/OKButton"));
			Clickon(getwebelement(xml.getlocator("//locators/publicWaveService/OKButton")));
			Thread.sleep(5000);
			if (isElementPresent("(//div[@class='ui-dialog-buttonset']/button)[2]")) {
				System.out.println("enter if loop");
				boolean t = isDisplayed("(//div[@class='ui-dialog-buttonset']/button)[2]");
				System.out.println(t);
				WaitforElementtobeclickable("(//div[@class='ui-dialog-buttonset']/button)[2]");
				Thread.sleep(3000);
				Clickon(getwebelement("(//div[@class='ui-dialog-buttonset']/button)[2]"));
				Thread.sleep(4000);
				waitforPagetobeenable();
			}
		} // end of if inner loop
	} // end of if outer loop

	/*
	 * Created by Ayush
	 */
	public void SearchSiteEntery(Object[] InputDate) throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SearchInputEthernetAccess").replace("Value", "Street Name"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInputEthernetAccess").replace("Value", "Street Name")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/R4/SearchInputEthernetAccess").replace("Value", "Street Name")),
				"Buckingham Avenue Slough");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchDropdown").replace("Value", "Country")));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "United Kingdom")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "City / Town")), "London");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Postal Code")), "SL14AX");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SearchInput").replace("Value", "Premises")), "1");

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchButton"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchButton")));

		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickAddress"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickAddress")));
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickBuilding"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickBuilding")));

		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SearchAddressRowSelection"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SearchAddressRowSelection")));

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PickSite"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PickSite")));
		waitforPagetobeenable();

	}

	/* Added by Devesh for R4 Products */

	public void AEndSite(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, "SiteA <-------- Entry Started -------->");
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("DCA Ethernet") || ProductName.equalsIgnoreCase("Ethernet Access")
				|| ProductName.equalsIgnoreCase("public Ethernet")) {

			if (InputData[74].toString().equalsIgnoreCase("offnet")) {
				System.out.println("enter into offnet part of A end site");
				WaitforElementtobeclickable(xml.getlocator("//locators/AccessTypeDropdownAccess"));
				Clickon(getwebelement(xml.getlocator("//locators/AccessTypeDropdownAccess")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select A end access type");
				WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
						InputData[42].toString())));

				// Click on save button to populate extra fields//
				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
				waitForpageload();
				waitforPagetobeenable();
				Thread.sleep(20000);

				// Access Tech
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[83].toString());

				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessproviderDropDown"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessproviderDropDown")));
				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString()));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyaccessProvidervalue").replace("value",
						InputData[187].toString())));

				Clear(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")));
				Clickon(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")));
				SendKeys(getwebelement(xml.getlocator("//locators/Thirdpartyconectionreference")), "no colt reference");
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Third Party SLA Tier"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Third Party SLA Tier")));
				Thread.sleep(2000);
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[35].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[35].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyDropDown"));
				Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyDropDown")));
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[35].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[35].toString())));

				WaitforElementtobeclickable(xml.getlocator("//locators/SiteTypeDropDown"));
				Clickon(getwebelement(xml.getlocator("//locators/SiteTypeDropDown")));
				WaitforElementtobeclickable(xml.getlocator("//locators/SiteTypeDropDownvalue"));
				Clickon(getwebelement(xml.getlocator("//locators/SiteTypeDropDownvalue")));

				WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
				Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
				Thread.sleep(20000);
				waitforPagetobeenable();

			}

			else {

				// Access Type
				System.out.println("enter into new order part of A end site");
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[42].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[42].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Type : " + InputData[42].toString());

				// Access Tech
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Technology")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[83].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[83].toString());

				// EFM
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"EFM Enhanced Bandwidth Availability"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"EFM Enhanced Bandwidth Availability")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[82].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[82].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select EFM Enhanced Bandwidth Availability : " + InputData[82].toString());

				// Demarcation Device
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Demarcation Device Required"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value",
						"Demarcation Device Required")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[81].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[81].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Demarcation Device Required : " + InputData[81].toString());

				// Site Type
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Site Type"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Site Type")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[92].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[92].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Type : " + InputData[92].toString());
			}
		}

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Building Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Building Type")));

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[85].toString()));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[85].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type : " + InputData[85].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "BCP Reference")),
				InputData[80].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  BCP Reference : " + InputData[80].toString());
		waitForpageload();
		waitforPagetobeenable();
		Thread.sleep(2000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Customer Site Pop Status"));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Customer Site Pop Status")));
		Thread.sleep(3000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[79].toString()));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[79].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select Customer Site Pop Status Type : " + InputData[79].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "DSL SLA Class"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "DSL SLA Class")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[78].toString()));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[78].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select DSL SLA Class Type :" + InputData[78].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")));
		SendKeys(
				getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Site Name Alias")),
				InputData[77].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on Site Name Alias : " + InputData[77].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, "SiteA <-------- Entry Ended -------->");
	}

	public void BEndSite(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		ExtentTestManager.getTest().log(LogStatus.PASS, "SiteB <-------- Entry Started -------->");

		if (InputData[74].toString().contains("Offnet")) {
			System.out.println("Enter into offnet part of B end site");
			/*
			 * Commented below line as fields are disable right now but need in future
			 * whenever it is fixed
			 */
			// WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypedropdownB"));
			// Clickon(getwebelement(xml.getlocator("//locators/AccesstypedropdownB")));
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select B end access
			// type");
			// WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet")+"[1]");
			// Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet")+"[1]"));
			// WaitforElementtobeclickable(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
			// InputData[42].toString()));
			// Clickon(getwebelement(xml.getlocator("//locators/AccesstypeOffnet").replace("AccessTypeValue",
			// InputData[42].toString())));

			WaitforElementtobeclickable((xml.getlocator("//locators/IpGurdianSave")));
			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			Thread.sleep(20000);
			// For access type technology//
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology")));

//			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA"));
//			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "NA")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Technology : NA ");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString()));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[83].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Access Technology : " + InputData[83].toString());

			// for 3rd party connection provider//
			/*
			 * Commented below line as fields are disable right now but need in future
			 * whenever it is fixed
			 */
			/*
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/ThirdpartyaccessproviderDropDownB"));
			 * Clickon(getwebelement(xml.getlocator(
			 * "//locators/ThirdpartyaccessproviderDropDownB")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/ThirdpartyaccessProvidervalue").replace("value",
			 * InputData[187].toString())); Clickon(getwebelement(xml.getlocator(
			 * "//locators/ThirdpartyaccessProvidervalue").replace("value",
			 * InputData[187].toString())));
			 * 
			 * //for 3rd party connection reference//
			 * Clear(getwebelement(xml.getlocator("//locators/ThirdpartyconectionreferenceB"
			 * ))); Clickon(getwebelement(xml.getlocator(
			 * "//locators/ThirdpartyconectionreferenceB")));
			 * SendKeys(getwebelement(xml.getlocator(
			 * "//locators/ThirdpartyconectionreferenceB")),"12");
			 * 
			 * //for third party SLA tier//
			 * WaitforElementtobeclickable(xml.getlocator("//locators/ThirdpartyDropDownB"))
			 * ; Clickon(getwebelement(xml.getlocator("//locators/ThirdpartyDropDownB")));
			 * //WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/ThirdpartySLATiervalueultra").replace("value", "1"));
			 * //Clickon(getwebelement(xml.getlocator(
			 * "//locators/ThirdpartySLATiervalueultra").replace("value", "1")));
			 * WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/ThirdpartySLATiervalue").replace("SLAValue",
			 * InputData[35].toString()));
			 * Clickon(getwebelement(xml.getlocator("//locators/ThirdpartySLATiervalue").
			 * replace("SLAValue", InputData[35].toString())));
			 */

			WaitforElementtobeclickable(xml.getlocator("//locators/IpGurdianSave"));
			Clickon(getwebelement(xml.getlocator("//locators/IpGurdianSave")));
			Thread.sleep(20000);
			waitforPagetobeenable();

		} // out of if inner loop

		else {

			if (ProductName.equalsIgnoreCase("DCA Ethernet")) {
				// Access Type
				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
				// "Access Type"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
				// "Access Type")));

				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "Colt Fibre"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value","Colt
				// Fibre" /* InputData[93].toString()*/)));
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Type :
				// " + "Colt Fibre" /* InputData[93].toString()*/);
				System.out.println("Enter into new order part of B end site");
				// Access Tech
				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology"));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Access Technology")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[123].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[123].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select Access Technology : " + InputData[123].toString());

				// EFM
				WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
						"EFM Enhanced Bandwidth Availability"));
				Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
						"EFM Enhanced Bandwidth Availability")));

				WaitforElementtobeclickable(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[124].toString()));
				Clickon(getwebelement(
						xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[124].toString())));
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Select EFM Enhanced Bandwidth Availability : " + InputData[124].toString());

				// Demarcation Device
				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
				// "Demarcation Device Required"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value",
				// "Demarcation Device Required")));

				// WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value",
				// "No"));
				// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AList").replace("Value","No"
				// /* InputData[93].toString()*/)));
				// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Demarcation
				// Device Required : " + "No" /* InputData[93].toString()*/);
			}
		}
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Building Type"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Building Type")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[125].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[125].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Building Type : " + InputData[125].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "BCP Reference")),
				InputData[126].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  BCP Reference : " + InputData[126].toString());
		waitforPagetobeenable();

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Customer Site Pop Status"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "Customer Site Pop Status")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[127].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[127].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select Customer Site Pop Status Type : " + InputData[127].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")));
		SendKeys(
				getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Site Name Alias")),
				InputData[128].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on Site Name Alias : " + InputData[128].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "DSL SLA Class"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BEndSiteDropDown").replace("Value", "DSL SLA Class")));

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[150].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/BList").replace("Value", InputData[150].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select DSL SLA Class Type :" + InputData[150].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, "SiteB <-------- Entry Ended -------->");
	}

	/* Added by Devesh for R4 Products */
	public void SiteATerminationTime(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		Random rand = new Random();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")
				|| ProductName.equalsIgnoreCase("Ethernet Access")) { // added ayush product
																		// CabinetID

			int rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID")));
			// if (InputData[97].toString() == "")
			// {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Cabinet ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace(
			 * "Value", "Cabinet ID")), InputData[97].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet ID  : "
			 * + InputData[97].toString()); }
			 */
			waitforPagetobeenable();

			// CabinetType
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Cabinet Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Cabinet Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[129].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Cabinet Type : " + InputData[129].toString());
			waitforPagetobeenable();

			// LinkAggregationRequired
			if (!InputData[9].toString().contains("Ethernet Access")) {
				WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
						"Link Aggregation Required"));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value",
						"Link Aggregation Required")));
				Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "No")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Link Aggregation Required : No");
				waitforPagetobeenable();
			}
			// Shelf ID
			rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID")));
			// if (InputData[50].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Shelf ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace(
			 * "Value", "Shelf ID")), InputData[50].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " +
			 * InputData[50].toString()); }
			 */
			waitforPagetobeenable();
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteBTerminationTime(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {
			// CabinetID
			int rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID")));
			// if (InputData[114].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Cabinet ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace(
			 * "Value", "Cabinet ID")), InputData[114].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet ID  : "
			 * + InputData[114].toString()); }
			 */
			waitforPagetobeenable();

			// CabinetType
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Cabinet Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Cabinet Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[113].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Cabinet Type : " + InputData[113].toString());
			waitforPagetobeenable();

			// LinkAggregationRequired
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Link Aggregation Required"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Link Aggregation Required")));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteABSelection").replace("Value", "No")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Link Aggregation Required : No");
			waitforPagetobeenable();

			// Shelf ID
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID")));
			// if (InputData[51].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Shelf ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace(
			 * "Value", "Shelf ID")), InputData[51].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Shelf ID  : " +
			 * InputData[51].toString()); }
			 */
			waitforPagetobeenable();
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteAAccessPort(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")
				|| ProductName.equalsIgnoreCase("Ethernet Access")) {
			int rand_int1 = rand.nextInt(1000);
			// Presentation Interface
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Presentation Interface"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Presentation Interface")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[99].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Presentation Interface: " + InputData[99].toString());
			waitforPagetobeenable();

			// Connector Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Connector Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Connector Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[54].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Connector Type : " + InputData[54].toString());
			waitforPagetobeenable();

			// Fibre Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Fibre Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Fibre Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[55].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Fibre Type : " + InputData[55].toString());
			waitforPagetobeenable();

			// VLAN Tagging Mode
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "VLAN Tagging Mode"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "VLAN Tagging Mode")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[100].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select VLAN Tagging Mode : " + InputData[100].toString());
			waitforPagetobeenable();

			// Physical Port ID
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID")));
			// if (InputData[101].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Physical Port ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Physical Port ID : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace(
			 * "Value", "Physical Port ID")), InputData[101].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Physical Port ID : "
			 * + InputData[101].toString()); }
			 */
			waitforPagetobeenable();

			// Slot ID
			rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID")));
			// if (InputData[66].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Slot ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace(
			 * "Value", "Slot ID")), InputData[66].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " +
			 * InputData[66].toString()); } waitforPagetobeenable();
			 */
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteBAccessPort(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Random rand = new Random();
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("public Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {
			// Presentation Interface
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Presentation Interface"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Presentation Interface")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[116].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Presentation Interface: " + InputData[116].toString());
			waitforPagetobeenable();

			// Connector Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Connector Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Connector Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[54].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Connector Type : " + InputData[54].toString());
			waitforPagetobeenable();

			// Fibre Type
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Fibre Type"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Fibre Type")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[121].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Fibre Type :  " + InputData[121].toString());
			waitforPagetobeenable();

			// VLAN Tagging Mode
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "VLAN Tagging Mode"));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "VLAN Tagging Mode")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[100].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select VLAN Tagging Mode : " + InputData[100].toString());
			waitforPagetobeenable();

			// Physical Port ID
			int rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID")));
			// if (InputData[101].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Physical Port ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Physical Port ID : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace(
			 * "Value", "Physical Port ID")), InputData[101].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Physical Port ID : "
			 * + InputData[101].toString()); }
			 */
			waitforPagetobeenable();

			// Slot ID
			rand_int1 = rand.nextInt(1000);
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID")));
			// if (InputData[66].toString() == "") {
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Slot ID")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " + Integer.toString(rand_int1));
			/*
			 * } else {
			 * SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace(
			 * "Value", "Slot ID")), InputData[66].toString());
			 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Slot ID : " +
			 * InputData[66].toString()); }
			 */
			waitforPagetobeenable();
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteBDedicatedCloudAccess(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		// Aws Account
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "AWS Account Number"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "AWS Account Number")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "AWS Account Number")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "AWS Account Number")),
				InputData[60].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  AWS Account Number : " + InputData[60].toString());

		// CSP bandwidth
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "CSP Bandwidth"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "CSP Bandwidth")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[61].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select CSP Bandwidth : " + InputData[61].toString());
		waitforPagetobeenable();

		// CSp NNI Ref Primary
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Primary"));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Primary")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Primary")));
		SendKeys(
				getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Primary")),
				InputData[62].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  CSP NNI Reference Primary : " + InputData[62].toString());

		// CSP NNI Ref Secondary
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Secondary"));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Secondary")));
		Clear(getwebelement(
				xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Secondary")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP NNI Reference Secondary")),
				InputData[63].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  CSP NNI Reference Secondary : " + InputData[63].toString());

		// CSP Name
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "CSP Name"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "CSP Name")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[64].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select CSP Name : NA " + InputData[64].toString());

		// CSP Pop
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP PoP (Primary)"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP PoP (Primary)")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP PoP (Primary)")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP PoP (Primary)")),
				InputData[65].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  CSP PoP (Primary) : " + InputData[65].toString());

		// CSP Region
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP Region"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP Region")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP Region")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "CSP Region")),
				InputData[130].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  CSP Region : " + InputData[130].toString());

		// Connection Alias
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Connection Alias"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Connection Alias")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Connection Alias")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Connection Alias")),
				InputData[131].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  Connection Alias : " + InputData[131].toString());

		// NAT
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "NAT Range Required"));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "NAT Range Required")));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[132].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Select NAT Range Required : " + InputData[132].toString());
		waitforPagetobeenable();

		// Routing Domain
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Routing Domain"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Routing Domain")));

		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[133].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[133].toString())));
		// Clickon(getwebelement(xml.getlocator("//locators/SelectValueDropdown").replace("Value",
		// InputData[133].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Routing Domain : " + InputData[133].toString());

		// S VLAN Tag ID
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "S VLAN Tag ID"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "S VLAN Tag ID")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "S VLAN Tag ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "S VLAN Tag ID")),
				InputData[134].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: enter  on  S VLAN Tag ID : " + InputData[134].toString());

		// Service Key
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Service Key"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Service Key")));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Service Key")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Service Key")),
				InputData[135].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  Service Key : " + InputData[135].toString());
		waitforPagetobeenable();

	}

	/* Added by Devesh for R4 Products */
	public void SiteAVLan(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		String ProductName = InputData[9].toString();
		if (ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Ethertype"));
			Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Ethertype")));
			Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Ethertype")));
			SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "Ethertype")),
					InputData[56].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: enter  on  Ethertype : " + InputData[56].toString());

			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "VLAN Tag ID"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "VLAN Tag ID")));
			Clear(getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "VLAN Tag ID")));
			SendKeys(
					getwebelement(xml.getlocator("//locators/DarkFiber/AEndSiteInput").replace("Value", "VLAN Tag ID")),
					InputData[57].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: enter  on  VLAN Tag ID : " + InputData[57].toString());
		}
	}

	/* Added by Devesh for R4 Products */
	public void SiteBVLan(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Ethertype"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Ethertype")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Ethertype")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "Ethertype")),
				InputData[58].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  Ethertype : " + InputData[58].toString());

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "VLAN Tag ID"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "VLAN Tag ID")));
		Clear(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "VLAN Tag ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/DarkFiber/BEndSiteInput").replace("Value", "VLAN Tag ID")),
				InputData[59].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: enter  on  VLAN Tag ID : " + InputData[59].toString());
	}

	// Today(27/09/2019) Work
	public void SiteASettingClick() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteASetting"));
		// clickUsingAction(getwebelement(xml.getlocator("//locators/R4/SiteASetting")));
		// javascriptexecutor(el);
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteASetting")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Site A Setting Click");
		waitforPagetobeenable();
		Thread.sleep(15000);
	}

	public void SiteBSettingClick() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBSetting"));
		clickUsingAction(getwebelement(xml.getlocator("//locators/R4/SiteBSetting")));
		// Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBSetting")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Site B Setting Click");
		waitforPagetobeenable();
	}

	public void CSPInterconnectSiteAEntry() throws InterruptedException, DocumentException {
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopPlus"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopPlus")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Add Sign");
		waitforPagetobeenable();
		// Attribute
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopAttributeDrodown"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopAttributeDrodown")));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "CSP interconnect A-end")));
		// Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value","CSP
		// interconnect A-end")));
		// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeName")),
		// Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeName")), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Attribute Name : CSP interconnect A-end");

		// Attribute
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopAttributeDrodown"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopAttributeDrodown")));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dedicated Port")));
		// Clickon(getwebelement(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value","Dedicated
		// Port")));
		// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeValue")),
		// Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeValue")), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Attribute Value : Dedicated Port");

		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteASettingOK"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteASettingOK")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: OK Clicked");
		waitforPagetobeenable();
		waitForpageload();

	}

	public void CSPInterconnectSiteBEntry() throws InterruptedException, DocumentException {
		/*
		 * waitforPagetobeenable();
		 * WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopPlus"));
		 * Clickon(getwebelement(xml.getlocator("//locators/R4/PopPlus")));
		 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Add Sign");
		 * waitforPagetobeenable(); //Attribute
		 * WaitforElementtobeclickable(xml.getlocator(
		 * "//locators/R4/PopAttributeDrodown"));
		 * Clickon(getwebelement(xml.getlocator("//locators/R4/PopAttributeDrodown")));
		 * Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace
		 * ("Value", "CSP interconnect A-end")));
		 * //Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").
		 * replace("Value","CSP interconnect A-end"))); //
		 * SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeName"))
		 * , Keys.ENTER);
		 * SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeName"))
		 * , Keys.TAB); ExtentTestManager.getTest().log(LogStatus.
		 * PASS," Step: Select Attribute Name : CSP interconnect A-end");
		 */

		// Attribute
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/PopAttributeDrodown"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/PopAttributeDrodown")));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dedicated Port")));
		// Clickon(getwebelement(xml.getlocator("//locators/R4/PopDropdownClick").replace("Value","Dedicated
		// Port")));
		// SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeValue")),
		// Keys.ENTER);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/AttributeValue")), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Attribute Value : Dedicated Port");

		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteASettingOK"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteASettingOK")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: OK Clicked");
		waitforPagetobeenable();
		waitForpageload();

	}

	public void IPVPNAccessMiddleApp(Object[] InputData) throws Exception {
		MiddleAppDropdown("Site Type", InputData[50].toString());
		MiddleAppDropdown("Router Type", InputData[51].toString());
		MiddleAppDropdown("Layer 3 Resilience", "No Resilience");
		//MiddleAppDropdown("SD WAN Subtype", "NA");
		MiddleAppDropdown("Router Technology", "N/A");
		MiddleAppDropdown("Service Bandwidth (Primary)", InputData[52].toString());
		MiddleAppTextBox("Capacity Check Reference", InputData[53].toString());
		MiddleAppDropdown("Hard Modify Flag", InputData[54].toString());
		MiddleAppDropdown("OSS Platform Flag (Primary)", InputData[55].toString());

		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);
		addSiteADetails(InputData);
		AEndDropdownSelection("Access Type", InputData[56].toString());
		AEndDropdownSelection("Access Technology", InputData[57].toString());
		AEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			//AEndDropdownSelection("DSL SLA Class", InputData[67].toString());
			//AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
		} 
			
			AEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
			AEndInputEnter("3rd Party Connection Reference", "ASD"+GetRandomNumberString(1000));
			AEndInputEnter("BCP Reference", GetRandomNumberString(1000));
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[64].toString());
			// CPE Information
			
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));
			AEndInputEnter("Slot ID", GetRandomNumberString(1000));
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Presentation Interface", InputData[61].toString());
			AEndDropdownSelection("Connector Type", InputData[62].toString());
			AEndDropdownSelection("Fibre Type", InputData[63].toString());
		
	}

	public void IPVPNAccessPlusMiddleApp(Object[] InputData) throws Exception {
		MiddleAppDropdown("Site Type", InputData[50].toString());
		MiddleAppDropdown("Router Type", InputData[51].toString());
		MiddleAppDropdown("Layer 3 Resilience", "No Resilience");
		//MiddleAppDropdown("SD WAN Subtype", "NA");
		MiddleAppDropdown("Router Technology", "Managed Physical Router");
		MiddleAppDropdown("Service Bandwidth (Primary)", InputData[52].toString());
		MiddleAppTextBox("Capacity Check Reference", InputData[53].toString());
		MiddleAppDropdown("Hard Modify Flag", InputData[54].toString());
		MiddleAppDropdown("OSS Platform Flag (Primary)", InputData[55].toString());

		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);
		addSiteADetails(InputData);
		AEndDropdownSelection("Access Type", InputData[56].toString());
		AEndDropdownSelection("Access Technology", InputData[57].toString());
		AEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) {
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[67].toString());
			//AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
		} 
			
			AEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
			AEndInputEnter("3rd Party Connection Reference", "FTR"+GetRandomNumberString(1000));
			AEndInputEnter("BCP Reference", GetRandomNumberString(1000));
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));
			// Install Time
			AEndDropdownSelection("Install Time", InputData[64].toString());

			// CPE Information
			
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));

			AEndInputEnter("Slot ID", GetRandomNumberString(1000));
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Presentation Interface", InputData[61].toString());
			AEndDropdownSelection("Connector Type", InputData[62].toString());
			AEndDropdownSelection("Fibre Type", InputData[63].toString());
			ClickHereSave();
		
	}

	public void IPVPNWholeSaleMiddleApp(Object[] InputData) throws Exception {
		
		MiddleAppDropdown("Service Bandwidth (Primary)", InputData[52].toString());
		MiddleAppTextBox("Capacity Check Reference", InputData[53].toString());
		MiddleAppDropdown("Hard Modify Flag", InputData[54].toString());
		MiddleAppDropdown("OSS Platform Flag (Primary)", InputData[55].toString());

		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);
		addSiteADetails(InputData);
		AEndDropdownSelection("Access Type", InputData[56].toString());
		AEndDropdownSelection("Access Technology", InputData[57].toString());
		AEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet")) {
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[67].toString());
			//AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
		} 
		 
			
			AEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
			AEndInputEnter("3rd Party Connection Reference", "AXG"+GetRandomNumberString(1000));
			AEndInputEnter("BCP Reference", GetRandomNumberString(1000));
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[64].toString());

			// CPE Information
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));

			AEndInputEnter("Slot ID", GetRandomNumberString(1000));
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Presentation Interface", InputData[61].toString());
			AEndDropdownSelection("Connector Type", InputData[62].toString());
			AEndDropdownSelection("Fibre Type", InputData[63].toString());
		
	}

	public void IPVPNSwiftNetMiddleApp(Object[] InputData) throws Exception {

		MiddleAppDropdown("Site Type", InputData[50].toString());
		MiddleAppDropdown("Router Type",InputData[51].toString());
		MiddleAppDropdown("Layer 3 Resilience", "SILVER");

		ClickHereSave();
		waitforPagetobeenable();
		
		MiddleAppDropdown("Router Technology", "Managed Physical Router");
		MiddleAppDropdown("Service Bandwidth (Primary)", InputData[52].toString());
		MiddleAppTextBox("Capacity Check Reference", InputData[53].toString());
		MiddleAppDropdown("Hard Modify Flag", InputData[54].toString());
		MiddleAppDropdown("OSS Platform Flag (Primary)", InputData[55].toString());
		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);
		addSiteADetails(InputData);
		AEndDropdownSelection("Access Type", InputData[56].toString());
		AEndDropdownSelection("Access Technology", InputData[57].toString());
		AEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet"))
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[67].toString());
			//AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
		} 
			AEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
			AEndInputEnter("3rd Party Connection Reference", "BHY"+GetRandomNumberString(1000));
			AEndInputEnter("BCP Reference", GetRandomNumberString(1000));
			AEndInputEnter("Site Name Alias", GetRandomNumberString(1000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[64].toString());

			// CPE Information
			
			AEndInputEnter("Cabinet ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(1000));

			AEndInputEnter("Slot ID", GetRandomNumberString(1000));
			AEndInputEnter("Physical Port ID", GetRandomNumberString(1000));
			AEndDropdownSelection("Presentation Interface", InputData[61].toString());
			AEndDropdownSelection("Connector Type", InputData[62].toString());
			AEndDropdownSelection("Fibre Type", InputData[63].toString());
		
		ClickHereSave();
		waitforPagetobeenable();

	}

	public void IPVPNPrizmNetMiddleApp(Object[] InputData) throws Exception {
		MiddleAppDropdown("Site Type", InputData[50].toString());
		MiddleAppDropdown("Router Type", InputData[51].toString());
		MiddleAppDropdown("Layer 3 Resilience", "No Resilience");
		//MiddleAppDropdown("SD WAN Subtype", "NA");
		MiddleAppDropdown("Router Technology", "N/A");
		MiddleAppDropdown("Service Bandwidth (Primary)", InputData[52].toString());
		MiddleAppTextBox("Capacity Check Reference", InputData[53].toString());
		MiddleAppDropdown("Hard Modify Flag", InputData[54].toString());
		MiddleAppDropdown("OSS Platform Flag (Primary)", InputData[55].toString());
		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);
		addSiteADetails(InputData);
		AEndDropdownSelection("Access Type", InputData[56].toString());
		AEndDropdownSelection("Access Technology", InputData[57].toString());
		AEndDropdownSelection("Building Type", InputData[58].toString());
		if (InputData[32].toString().equalsIgnoreCase("offnet"))
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[66].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[67].toString());
			//AEndDropdownSelection("Third Party SLA Tier", InputData[68].toString());
		}  
			
			AEndDropdownSelection("Customer Site Pop Status", InputData[59].toString());
			AEndInputEnter("3rd Party Connection Reference","HUYT"+GetRandomNumberString(2000));
			AEndInputEnter("BCP Reference", GetRandomNumberString(2000));
			AEndInputEnter("Site Name Alias", GetRandomNumberString(2000));

			// Install Time
			AEndDropdownSelection("Install Time", InputData[64].toString());

			// CPE Information
			int rnd_int = rnd.nextInt(1000);
			AEndInputEnter("Cabinet ID", GetRandomNumberString(2000));
			AEndDropdownSelection("Cabinet Type", InputData[60].toString());
			AEndInputEnter("Shelf ID", GetRandomNumberString(2000));

			AEndInputEnter("Slot ID", GetRandomNumberString(2000));
			AEndInputEnter("Physical Port ID", GetRandomNumberString(2000));
			AEndDropdownSelection("Presentation Interface", InputData[61].toString());
			AEndDropdownSelection("Connector Type", InputData[62].toString());
			AEndDropdownSelection("Fibre Type", InputData[63].toString());
		
		ClickHereSave();
		waitforPagetobeenable();
	}

	public void IPVPNSITEMiddleApplet(Object[] InputData) throws Exception {

		if (InputData[10].equals("IP VPN Access")) {
			IPVPNAccessMiddleApp(InputData);
		} else if (InputData[10].equals("IP VPN Wholesale")) {
			IPVPNWholeSaleMiddleApp(InputData);
		} else if (InputData[10].equals("SWIFTNet")) {
			IPVPNSwiftNetMiddleApp(InputData);
		} else if (InputData[10].equals("PrizmNet")) {
			IPVPNPrizmNetMiddleApp(InputData);
		}else if (InputData[10].equals("IP VPN Plus")) {
			IPVPNAccessPlusMiddleApp(InputData);
		}
		
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();

		RandomDropSelection("B", "Router Model");
		RandomDropSelection("B", "Site Name");
		ClickHereSave();
		waitForpageload();

		Thread.sleep(3000);
		WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
		waitforAttributeloader();
		Thread.sleep(30000);

		Circuitreferencenumber
				.set(Getattribute(getwebelement2(xml.getlocator("//locators/CircuitReferenceValue")), "value"));
		System.out.println(Circuitreferencenumber.get());
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated circuit reference No: " + Circuitreferencenumber.get());

		savePage();
		waitforPagetobeenable();
		Thread.sleep(8000);
		ClickHereSave();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "IP Details")));
		waitforPagetobeenable();

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "CPE IPv4 Address")),
				"123.65.19.1");

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "IPv4 Prefix")),
				"123.65.19.1");

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "PE IPv4 Address")),
				"123.65.19.1");

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "PE Name")), "xyz");

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "End Point VPN")));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/IPDetailsPlus")));
		Thread.sleep(3000);

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "VPN Bandwidth")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[65].toString())));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubVPNID")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectSubVPNList")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubmitSubVPNList")));
		Thread.sleep(3000);
		waitforPagetobeenable();
		if ( InputData[10].equals("PrizmNet")||InputData[10].equals("SWIFTNet")|| InputData[10].equals("IP VPN Access")|| InputData[10].equals("IP VPN Plus")) 
		{
			Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SearchInput").replace("Value", "Physical Port ID Primary")));
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/AccessPortList")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubmitSubVPNList")));
			Thread.sleep(3000);
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "IP Details")));
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN Interface IPv4 Address")),"123.65.19.1");
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN Interface IPv4 Prefix")),"123.65.19.1");
			if(InputData[10].equals("SWIFTNet"))
			{
				ClearSendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN VRRP IPv4 Address 1")),"123.65.19.1");
				ClearSendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Second CPE LAN Interface IPv4 Address")),"123.65.19.1");
				ClearSendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN VRRP IPv4 Address 2")),"123.65.19.1");
			}

		}
		Thread.sleep(3000);
		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(3000);
	}

	public void OperationalAttributesforIPVPN(Object[] InputData) throws Exception {
		savePage();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites")));
		waitforPagetobeenable();
		String loct=xml.getlocator("//locators/IPVPNSite/RouterSpecificationSetting");
		System.out.println(loct);
		
		if(InputData[9].toString().equalsIgnoreCase("CPE Solutions Site"))
		{
			loct=loct.replace("Router Specification", "Device");
			WaitforElementtobeclickable(loct);
			Thread.sleep(10000);
			Clickon(getwebelement(loct));
			Thread.sleep(4000);
			waitforPagetobeenable();
		}
		else
		{
			WaitforElementtobeclickable(loct);
			Thread.sleep(10000);
			Clickon(getwebelement(loct));
			Thread.sleep(4000);
			waitforPagetobeenable();
		}
		Thread.sleep(5000);
		List<WebElement> HeaderList = GetWebElements("//div[@class='AppletStylePopup']//th//div");
		if(!HeaderList.isEmpty())
		{
		int Attindex = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();
			Attindex = Attindex + 1;
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Attribute Value")) {
				break;
			}
		}
		Thread.sleep(5000);
		int count = getwebelementscount(xml.getlocator("//locators/IPVPNSite/OperationalAttribueCount"));
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex))));
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))));
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))), "Test1");
		}
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK")));
		savePage();
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();
		}
	}

	public void IPVPNServicePlusAccess(Object[] InputData) throws Exception {

		MiddleAppDropdown("Service Type", InputData[48].toString());
		MiddleAppDropdown("Network Topology", InputData[47].toString());
		MiddleAppDropdown("Contains Wholesale NNIs", InputData[49].toString());
		BEndInputEnter("Customer Alias", "Test" + Integer.toString(rnd.nextInt(50)));
		BEndDropdownSelection("IP Addressing Format", "IPv4 only");
		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);

	}

	public void IPVPNServicePlusAccess1(Object[] InputData) throws Exception {
		/*
		 * if (!InputData[10].toString().equalsIgnoreCase("IP VPN Wholesale")) {
		 * WaitforElementtobeclickable(
		 * xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
		 * "Network Topology")); Clickon(getwebelement(
		 * xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
		 * "Network Topology"))); ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step: Click on Network Topology Dropdown"); Thread.sleep(3000);
		 * 
		 * Clickon(getwebelement(
		 * xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
		 * InputData[27].toString()))); ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step:Select value from network topology");
		 * 
		 * WaitforElementtobeclickable(
		 * xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
		 * "Contains Wholesale NNIs")); Clickon(getwebelement(
		 * xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
		 * "Contains Wholesale NNIs"))); ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step: Clear service type value from contains NNIs wholesale field");
		 * 
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/IPVPNSite/SelectValueDropdown").replace("Value", "No")));
		 * ExtentTestManager.getTest().log(LogStatus.PASS,
		 * " Step: Enter value in contains NNIs wholesale");
		 * 
		 * WaitforElementtobeclickable((xml.getlocator(
		 * "//locators/IPVPNSite/ClickheretoSaveAccess")));
		 * Clickon(getwebelement(xml.getlocator(
		 * "//locators/IPVPNSite/ClickheretoSaveAccess")));
		 * ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on save");
		 * waitforPagetobeenable(); Thread.sleep(4000); }
		 */
		if (InputData[10].toString().equalsIgnoreCase("IP VPN Wholesale")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 1 DSCP"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 1 DSCP")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Bussiness DSCP Dropdown");

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "AF11")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in Textbox");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 2 DSCP"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 2 DSCP")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Bussiness DSCP Dropdown");

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "AF13")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in Textbox");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 3 DSCP"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Business 3 DSCP")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Bussiness DSCP Dropdown");

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "AF12")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in Textbox");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Premium DSCP"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Premium DSCP")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Bussiness DSCP Dropdown");

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "AF12")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in Textbox");

			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Standard DSCP"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Standard DSCP")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Bussiness DSCP Dropdown");

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "AF11")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in Textbox");
		}

	}

	public void MovetoIPService() throws InterruptedException, DocumentException {
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Network")));
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/NetworkService")));
		Thread.sleep(4000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/GOIPService").replace("Value", ServiceOrder.get().toString())));
		waitforPagetobeenable();
		Thread.sleep(4000);
	}

	// --- Added By Abhay- 28Sep-2019
	// --- Added By Abhay- 28Sep-2019
	public void IPVPNServiceNetworkReference(Object[] InputData) throws Exception {

		System.out.println(xml.getlocator("//locators/IPVPNSite/NetworkReference"));
		Thread.sleep(5000);
		String NetworkReference = "";
		String NetworkReference2 = "";
		try {
			NetworkReference = getwebelement2(xml.getlocator("//locators/IPVPNSite/NetworkReference")).getAttribute("value");
			System.out.println("try block using value" + NetworkReference);
		} catch (Exception e) {
			System.out.println("Catch block using value" + NetworkReference);
			e.printStackTrace();

		}
		try {
			NetworkReference2 = getwebelement2(xml.getlocator("//locators/IPVPNSite/NetworkReference"))
					.getAttribute("innerHTML");
			System.out.println("try using inner html" + NetworkReference2);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Catch using inner html" + NetworkReference2);
		}

		System.out.println(NetworkReference);
		NetworkReferenceIPVPN.set(NetworkReference);
		System.out.println("Final Value" + NetworkReferenceIPVPN.get().toString());

	}

	// --- Added By Abhay- 28Sep-2019
	// --- Added By Abhay- 28Sep-2019
	public void AlertAccept() throws DocumentException, InterruptedException {
		if (isElementPresent((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
		Thread.sleep(3000);
	}

	// --- Added By Abhay- 28Sep-2019
	public void openIPVPNSite() throws Exception {
		{
			ServiceOrder2.set(Gettext(getwebelement(xml.getlocator("//locators/IPVPNSite/ServiceOrderReferenceNo2"))));
			ExtentTestManager.getTest().log(LogStatus.PASS," Step: Generated Service Order Reference No: " + ServiceOrder2.get());
			Log.info(ServiceOrder2.get());
			ServiceOrders.setSubServiceOrder(ServiceOrder2.get());
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ServiceOrderReferenceNo2")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
			Thread.sleep(5000);
		}

	}
	// --- Added By Abhay- 28Sep-2019

	// --- Added By Abhay- 28Sep-2019
	public void NetworkReferenceFill() throws InterruptedException, DocumentException, IOException {

		Thread.sleep(4000);
		WaitforElementtobeclickable(xml.getlocator("//locators/NetworkReferenceSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/NetworkReferenceInput")),
				NetworkReferenceIPVPN.get().toString());
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/NetworkReferenceSearchin")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/NetworkReferenceGo")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		savePage();
		waitforPagetobeenable();

	}

	// --- Added By Abhay- 28Sep-2019
	// --- Added By Abhay- 28Sep-2019
	public void NetworkReferenceFillService(Object[] InputData)
			throws InterruptedException, DocumentException, IOException {
		if (InputData[10].toString().equalsIgnoreCase("SWIFTNet")
				|| InputData[10].toString().equalsIgnoreCase("PrizmNet")) {

			/*WaitforElementtobeclickable(xml.getlocator("//locators/NetworkReferenceSearch"));
			Clickon(getwebelement(xml.getlocator("//locators/NetworkReferenceSearch")));
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/NetworkPlusSign")));
			// Clickon(getwebelement(xml.getlocator("//locators/SelectNetworkReference")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Network Reference Search");

			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SubmitNetworkReference")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Network Reference");
			savePage();
			waitforPagetobeenable();
			Thread.sleep(5000);*/
			addNetwork();

		}

	}

	// --- Added By Abhay- 28Sep-2019
	public void ServiceChargeforIPVPNSite(Object[] InputData, String Amount) throws Exception {
		WaitforElementtobeclickable(xml.getlocator("//locators/ExpandAllButton"));
		Clickon(getwebelement(xml.getlocator("//locators/ExpandAllButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Expand All Button");
		Thread.sleep(3000);

		while (!Getattribute(getwebelement(xml.getlocator("//locators/BillingLastRow")), "class")
				.contains("highlight")) {

			int RowCount = getwebelementscount((xml.getlocator("//locators/BillingRow"))) - 1;
			System.out.println(RowCount);

			for (int i = 1; i <= RowCount; i++) {

				Clickon(getwebelement(
						xml.getlocator("//locators/BillingRowAmount").replace("Value", String.valueOf(i))));
				waitforPagetobeenable();
				if (!Getattribute(
						getwebelement(
								xml.getlocator("//locators/BillingRowAmount").replace("Value", String.valueOf(i))),
						"class").contains("disabled")) {

					Clear(getwebelement(
							xml.getlocator("//locators/BillingRowAmountInput").replace("Value", String.valueOf(i))));
					SendKeys(getwebelement(
							xml.getlocator("//locators/BillingRowAmountInput").replace("Value", String.valueOf(i))),
							Amount);
					waitforPagetobeenable();
				} else {
					System.out.println("Not Required to fill");
				}

				Clickon(getwebelement(xml.getlocator("//locators/BillingRowBCN").replace("Value", String.valueOf(i))));
				waitforPagetobeenable();
				if (!Getattribute(
						getwebelement(xml.getlocator("//locators/BillingRowBCN").replace("Value", String.valueOf(i))),
						"class").contains("disabled")) {

					Clickon(getwebelement(xml.getlocator("//locators/BCNSearchClick")));
					waitforPagetobeenable();
					SendKeys(getwebelement(xml.getlocator("//locators/BCNInstallationChargeNRCInput")),
							InputData[25].toString());
					Thread.sleep(3000);
					Clickon(getwebelement(xml.getlocator("//locators/BCNNRCSearch")));
					waitforPagetobeenable();
					Thread.sleep(3000);
					// Clickon(getwebelement(xml.getlocator("//locators/BCNNRCSubmit")));// Should
					// add in BSW enviroment
					waitforPagetobeenable();
				} else {
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

	// --- Added By Abhay- 28Sep-2019
	public void productSelectionHelper(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		Selectproduct(InputData[9].toString());
	}

	// Commented By Devesh Covered in Main function
	public void Selectproduct(String productName) throws InterruptedException, DocumentException, IOException {
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Product Selection Started");
		int StatusHeader = -1;
		int i = 0;
		boolean found = false;
		do {
			if (i > 0) {
				Clickon(getwebelement(xml.getlocator("//locators/ProductSelectionArrow")));
				waitForpageload();
				waitforPagetobeenable();
				i = 0;
			}
			List<WebElement> ProductList = GetWebElements(xml.getlocator("//locators/ProductNameList"));
			for (WebElement ele : ProductList) {
				javascriptexecutor(ele);
				String Text = ele.getText();
				System.out.println("Column : " + Text);
				if (Text.equalsIgnoreCase(productName)) {
					StatusHeader = i;
					found = true;
					break;
				}
				i++;
			}
			if (found)
				break;
		} while (isDisplayed(xml.getlocator("//locators/ProductSelectionArrow")));

		String plusButton = xml.getlocator("//locators/AddProduct");
		plusButton = plusButton.replace("-10", String.valueOf(i + 1));
		Clickon(getwebelement(plusButton));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Product(" + productName + ") selected ");
		waitForpageload();
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Product Selection Ended");
	}

	/*
	 * Created by Abhay
	 */
	public void ClickContinue() throws DocumentException, InterruptedException {
		if (isElementPresent((xml.getlocator("//locators/IPVPNSite/BlankPriceContinue")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/IPVPNSite/BlankPriceContinue")));
			Clickon(getwebelement(xml.getlocator("//locators//IPVPNSite/BlankPriceContinue")));
		}
		Thread.sleep(3000);
	}

	// Added by aman gupta
	public void alertPopUp() throws DocumentException, InterruptedException {
		if (isDisplayed((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
	}

	/*
	 * Created by Aman
	 */

	public void settingUltraLowLatency(Object[] InputData) throws Exception {

		waitForpageload();
		waitforPagetobeenable();
		savePage();
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/SettingsButton"));
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/publicWaveNode/SettingsButton")));
		Thread.sleep(10000);

		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/InputValuesVendor"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")));
		SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")), "ADVA");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveNode/InputValuesVendor")), Keys.TAB);

		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/TechonolgyValuecell"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/TechonolgyValuecell")));
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy"));
		SendKeys(getwebelement2(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy")), "CWDM");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/publicWaveNode/InputvaluesTechonolgy")), Keys.TAB);

		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/OKButton"));
		Clickon(getwebelement(xml.getlocator("//locators/publicWaveNode/OKButton")));
	}

	/*
	 * Created by Aman
	 */

	public void OperationalAttributeDarkFibre() throws Exception 
	{
		savePage();
		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/SettingsButton"));
		Thread.sleep(1000);
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/publicWaveNode/SettingsButton")));
		Thread.sleep(4000);
		waitforPagetobeenable();
		//// div[@class='AppletStylePopup']//th//div
		List<WebElement> HeaderList = GetWebElements("//div[@class='AppletStylePopup']//th//div");
		if(!HeaderList.isEmpty())
		{
		int Attindex = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();
			Attindex = Attindex + 1;
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Attribute Value")) {
				break;
			}
		}
		Thread.sleep(5000);
		int count = getwebelementscount(xml.getlocator("//locators/IPVPNSite/OperationalAttribueCount"));
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			////////////////////////////////////

			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex))));
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))));
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))), "Test1");

			
		}
		
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK")));
		savePage();
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();
		}
	}

	public void OperationalAttributeUltra(Object[] Inputdata) throws Exception {
		savePage();
//		   waitForpageload();
//	    	waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/publicWaveNode/SettingsButton"));
		Thread.sleep(1000);

		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/publicWaveNode/SettingsButton")));

		Thread.sleep(4000);
		waitforPagetobeenable();
		Thread.sleep(5000);
		List<WebElement> HeaderList = GetWebElements("//div[@class='AppletStylePopup']//th//div");
		int Attindex = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();
			Attindex = Attindex + 1;
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Attribute Value")) {
				break;
			}
		}
		Thread.sleep(5000);
		int count = getwebelementscount(xml.getlocator("//locators/IPVPNSite/OperationalAttribueCount"));
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex))));
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))));
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))), "Test1");
		}
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK")));
		savePage();
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();
	}

	/*
	 * Created by Rekha if it required will use for future purpose
	 */
	public void Carnor_getReferenceNo1(Object[] InputData) throws Exception, Exception {
		if (InputData[9].toString().equalsIgnoreCase("Ethernet VPN Access")
				|| InputData[9].toString().equalsIgnoreCase("Dark Fibre")
				|| InputData[9].toString().equalsIgnoreCase("Ultra Low Latency")
				|| InputData[9].toString().equalsIgnoreCase("public Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("DCA Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("public Wave Service")
				|| InputData[9].toString().equalsIgnoreCase("IP Access")
				|| InputData[9].toString().equalsIgnoreCase("IP VPN Service")) {
			WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
			Thread.sleep(25000);

			savePage();
			waitforPagetobeenable();
			Thread.sleep(8000);

			Circuitreferencenumber.set(Gettext(getwebelement(xml.getlocator("//locators/CircuitReferenceValue']"))));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Generated Service Order Reference No: " + Circuitreferencenumber.get());

		}

		// for enter value in circuit reference
		else if (InputData[9].toString().contains("Ethernet Access")) {
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/R4/NetworkTopology")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click network topology");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/NetworkTopology")), Keys.TAB);
			SendKeys(getwebelement(xml.getlocator("//locators/R4/CircuitReference")), "2");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in circuit reference");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/CircuitReference")), Keys.TAB);
			Thread.sleep(25000);

			savePage();
			waitforPagetobeenable();
			Thread.sleep(8000);
		}

	}

	public void Carnor_getReferenceNo(Object[] InputData) throws Exception, Exception {
		if (InputData[9].toString().equalsIgnoreCase("Ethernet VPN Access")
				|| InputData[9].toString().equalsIgnoreCase("Dark Fibre")
				|| InputData[9].toString().equalsIgnoreCase("Ultra Low Latency")
				|| InputData[9].toString().equalsIgnoreCase("public Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("DCA Ethernet")
				|| InputData[9].toString().equalsIgnoreCase("public Wave Service")
				|| InputData[9].toString().equalsIgnoreCase("IP Access")
				|| InputData[9].toString().equalsIgnoreCase("IP VPN Service")) {
			WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
			Thread.sleep(25000);

			savePage();
			waitforPagetobeenable();
			Thread.sleep(8000);

			Circuitreferencenumber
					.set(Getattribute(getwebelement2(xml.getlocator("//locators/CircuitReferenceValue")), "value"));
			System.out.println(Circuitreferencenumber.get());
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Generated circuit reference No: " + Circuitreferencenumber.get());

		}
	}

	public void CEOS_Offnet() throws Exception {

		try {
			Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Offnet");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on offnet tab");
		} catch (Exception e) {
			WaitforElementtobeclickable(xml.getlocator("//locators/OffnetTab"));
			Clickon(getwebelement(xml.getlocator("//locators/OffnetTab")));
		}
		CeosOrder.set(Gettext(getwebelement(xml.getlocator("//locators/CEOSRecord"))));// Added by Rekha
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Generated Ceos Order Reference No: " + CeosOrder.get());

		Thread.sleep(5000);
		// Moveon(getwebelement(xmlC.getlocator("//locators/OffnetTab")));
		// System.out.println("Moved Mouse");
		// Clickon(getwebelement(xmlC.getlocator("//locators/OffnetTab")));
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on OffnetTab");
		Thread.sleep(3000);
		waitForpageload();
		waitforPagetobeenable();
		// CeosOrder.set(Gettext(getwebelement(xmlC.getlocator("//locators/CEOSRecord"))));//
		// Added by
		// Rekha
		// ExtentTestManager.getTest().log(LogStatus.PASS,
		// " Step: Generated Ceos Order Reference No: " + CeosOrder.get());
	}

	public void LaunchingCEOSApplication(Object InputData[]) throws Exception {
		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability("requireWindowFocus", true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		System.setProperty("webdriver.ie.driver", ".\\lib\\IEDriverServer.exe");
		InternetExplorerDriver dr = new InternetExplorerDriver(capabilities);
		// dr.get("http://navmctmohs003:8080/arsys/forms/amsceo03/CEOS%3AOff-Net+Service/Support/");
		dr.manage().window().maximize();
		dr.manage().deleteAllCookies();
		dr.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		try {

			String URL = null;
			PropertyReader pr = new PropertyReader();
			// Log.info(environment + "_URL");
			URL = pr.readproperty("CEOS_URL");
			dr.get(URL);
			// driver.get(URL);

			// openurl(Application);
			// dr.navigate().to("http://navmctmohs003:8080/arsys/forms/amsceo03/CEOS%3AOff-Net+Service/Support/");
			// dr.navigate().to("http://navmctmohs003:8080/arsys/forms/amsceo02.eu.colt/CEOS%3AOff-Net+Service/Support_Web/");
			// dr.get("http://navmctmohs003:8080/arsys/forms/amsceo02.eu.colt/CEOS%3AOff-Net+Service/Support_Web/");
			Thread.sleep(3000);
			System.out.println(dr.getTitle());
			// dr.findElement(By.id("username-id")).sendKeys("ajain12");
			dr.findElement(By.id("username-id")).sendKeys(Getkeyvalue("CEOS_Username"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter User Name");
			dr.findElement(By.id("pwd-id")).click();
			dr.findElement(By.id("pwd-id")).clear();
			// dr.findElement(By.id("pwd-id")).sendKeys("password");
			dr.findElement(By.id("pwd-id")).sendKeys(Getkeyvalue("CEOS_Password"));
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Password");
			dr.findElement(By.name("login")).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Login Button");
			dr.findElement(By.xpath("//*[contains(@class,'sr')]")).sendKeys(CeosOrder.get().toString());

			// Clickon(getwebelement(xml.getlocator("//locators/InputServiceOrder")));
			// SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")),
			// CeosOrder.get().toString());*/
			// dr.findElement(By.className("text sr")).sendKeys("CEOS00000513951");
			// SendKeys(getwebelement(xml.getlocator("//locators/CEOSrefNo")),"CEOS00000513675");//InputData[1].toString());
			// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: CEOS Ref No enetered
			// : CEOS00000513951");
			Thread.sleep(5000);
			// $x("//div[text()='Search']")
			dr.findElement(By.id("TBsearchsavechanges")).click();
			// Clickon(getwebelement(xml.getlocator("//locators/Searchbtn")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Searchbtn ");
			Thread.sleep(20000);

			Robot r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(20000);

			dr.findElement(By.id("TBsearchsavechanges")).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Searchbtn ");
			Thread.sleep(60000);

			dr.findElement(By.xpath("//label[text()='Supplier Circuit ID']/following-sibling::textarea"))
					.sendKeys("12345678");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Supplier Circuit ID");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Supplier Product']/following-sibling::textarea")));
			Thread.sleep(5000);
			// Clickon(dr.findElement(By.xpath("//td[text()='ETHERNET']/parent::*")));
			// PickValue("ETHERNET");
			Clickon(dr.findElement(By.xpath("//td[text()='" + InputData[179].toString() + "']/parent::*")));
			Thread.sleep(5000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Supplier Product");
			// Thread.sleep(60000);
			Clickon(dr.findElement(By.xpath("//label[text()='Bandwidth']/following-sibling::textarea")));
			Thread.sleep(5000);
			Clickon(dr.findElement(By.xpath("//td[text()='" + InputData[180].toString() + "']/parent::*")));
			// PickValue("10 Gbps");
			Thread.sleep(5000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Bandwidth");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Interface']/following-sibling::textarea")));
			Thread.sleep(5000);
			Clickon(dr.findElement(By.xpath("//td[text()='N/A']/parent::*")));
			Thread.sleep(5000);
			// PickValue("N/A");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Interface");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Connector']/following-sibling::textarea")));
			Thread.sleep(5000);
			Clickon(dr.findElement(By.xpath("//td[text()='N/A']/parent::*")));
			Thread.sleep(5000);
			// PickValue("N/A");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Connector");
			dr.findElement(By.xpath("//label[text()='Supplier Ordering Reference']/following-sibling::textarea"))
					.sendKeys("2345678");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Supplier Ordering Reference");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='OLO Admin']/following-sibling::textarea")).sendKeys("abcde");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter OLO Admin");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Ordering Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Ordering Date");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Requested Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Requested Date");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Supplier Confirmation Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Supplier Confirmation Date");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Confirmed Delivery Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Confirmed Delivery Date");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Tariff']/following-sibling::textarea")));
			Thread.sleep(5000);
			Clickon(dr.findElement(By.xpath("//td[text()='flat']/parent::*")));
			Thread.sleep(5000);
			// PickValue("flat");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Tariff");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='OLO Use']/following-sibling::div/input")));
			Thread.sleep(5000);
			Clickon(dr.findElement(By.xpath("//td[text()='Customer access ']/parent::*")));
			Thread.sleep(5000);
			// PickValue("Customer access");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter OLO Use");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Install Cost']/following-sibling::input")));
			Clear((dr.findElement(By.xpath("//label[text()='Install Cost']/following-sibling::input"))));
			dr.findElement(By.xpath("//label[text()='Install Cost']/following-sibling::input")).sendKeys("1.00");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Install Cost");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("//label[text()='Recurring Cost']/following-sibling::input")));
			Clear((dr.findElement(By.xpath("//label[text()='Recurring Cost']/following-sibling::input"))));
			dr.findElement(By.xpath("//label[text()='Recurring Cost']/following-sibling::input")).sendKeys("1.00");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Recurring Cost");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Contract Period (Months)']/following-sibling::div/input"))
					.sendKeys("12");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Period");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Contract Ends']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Ends");
			Thread.sleep(2000);
			dr.findElement(By.xpath("//label[text()='Supplier Invoice Start Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Supplier Invoice Start Date");
			Thread.sleep(2000);
			// dr.findElement(By.xpath("//label[text()='Currency']/following-sibling::textarea")).sendKeys("SGD");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Currency");
			Thread.sleep(2000);
			Clickon(dr.findElement(By.xpath("(//div[text()='Save'])[2]")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Save");
			Thread.sleep(10000);

			Clickon(dr.findElement(By.xpath("//a[text()='Interconnect Details']")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Interconnect Details Tab");
			Thread.sleep(5000);

			Clickon(dr.findElement(By.xpath("(//input[@type='checkbox'])[3]")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter I/C Not Required checkbox");
			Thread.sleep(10000);

			// Robot r=new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			Thread.sleep(40000);

			dr.findElement(By.xpath("//label[text()='Actual Delivery (RFS) Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Actual Delivery (RFS) Date");
			Thread.sleep(2000);

			dr.findElement(By.xpath("//label[text()='Expected delivery date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Expected delivery date");
			Thread.sleep(2000);

			dr.findElement(By.xpath("//label[text()='Supplier HandOver Date']/following-sibling::input"))
					.sendKeys(CurrentDate());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Supplier HandOver Date");
			Thread.sleep(2000);

			Clickon(dr.findElement(By.xpath("(//div[text()='Save'])[2]")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Save");
			Thread.sleep(10000);

			dr.close();
		} catch (Exception e) {
			dr.close();
		}

	}

	// Added by Aman
	public void CheckServiceTab(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		Thread.sleep(3000);

		try // By Aman Gupta
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyButtonClick"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyButtonClick")));
		} catch (Exception e) {

			WaitforElementtobeclickable(xml.getlocator("//locators/ModifyBtn"));
			Clickon(getwebelement(xml.getlocator("//locators/ModifyBtn")));

		}

		waitforPagetobeenable();
		Thread.sleep(3000);

		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), InputData[1].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity No");
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");
		Thread.sleep(3000);
		ModifiedServiceOrder.set(Gettext(getwebelement(xml.getlocator("//locators/ModifyOrderNumber"))));// Added by
																											// Rekha
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Generated Modify Order Reference No: " + ModifiedServiceOrder.get());// Added by Rekha
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		// System.out.println("Enter New Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		waitforPagetobeenable();
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");
		// ---> Condition need to be added for mod com and mod tech

		if (InputData[InputData.length - 1].toString().contains("Com")
				|| InputData[InputData.length - 1].toString().contains("Carnor")) // Added by Dipesh

		{
			SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "BCN Change");// Specific for mod
																									// com
		} else if (InputData[InputData.length - 1].toString().contains("Tech")) {
			SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "Upgrade Bandwith"); // specific for
																											// mod tech
		}
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");

		Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
		Thread.sleep(2000);

		if (!InputData[9].toString().equalsIgnoreCase("Wave")
				&& !InputData[9].toString().equalsIgnoreCase("Ether Line")) // added shivananda
		{
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
		}
		savePage();
		waitforPagetobeenable();
	}

	public String RandomName() {
		String[] BuildNames = { "APIAutomation", "Automation", "Selenium", "Chrome", "Opera", "Safari", "FireFox",
				"Ghost", "Edge" };
		int index = rnd.nextInt(BuildNames.length);
		return BuildNames[index];
	}

	public void ClickSection(String SectionName) throws InterruptedException, DocumentException {
		String temp = xml.getlocator("//locators/SectionSelection").replace("Value", SectionName);
		System.out.println(temp);
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(xml.getlocator("//locators/SectionSelection").replace("Value", SectionName)));
		Thread.sleep(3000);
	}

	// fgfg
	public void R5DataCoverage(Object[] InputData) throws Exception {
		System.out.println(InputData[45].toString());
		System.out.println(InputData[46].toString());
		System.out.println(InputData[47].toString());

		String LocalValue = null;
		waitForpageload();
		WaitforElementtobeclickable(xml.getlocator("//locators/EnableFeature"));
		Moveon(getwebelement(xml.getlocator("//locators/EnableFeature")));
		Clickon(getwebelement(xml.getlocator("//locators/ClickShowFullInfoAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Show Full Info Access ");
		Thread.sleep(5000);

		ClickSection("Service Management");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Service Managemet Section selected");
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Package Type")));
		Thread.sleep(5000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[46].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Package Type= " + InputData[46].toString() + " Selected");

		ClickSection("Project Management");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Project Management Section selected");
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Package Type")
				.replace("2", "4")));
		Thread.sleep(5000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[47].toString())));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Package Type= " + InputData[47].toString() + " Selected");

		String temp = xml.getlocator("//locators/InputBox").replace("Value", "Number of Days").replace("-1", "1");
		LocalValue = Integer.toString(rnd.nextInt(1000));
		ClearSendKeys(getwebelement(temp), LocalValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Number of Days Entered = " + LocalValue);
		SendkeaboardKeys(getwebelement(temp), Keys.ENTER);
		Thread.sleep(5000);

		ClickSection("Consultancy Services");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Consultancy Services Section selected");
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Consultancy Service Type")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[45].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Consultancy Service Type = " + InputData[45].toString() + " Selected");

		temp = xml.getlocator("//locators/InputBox").replace("Value", "Statement of Work").replace("-1", "1");
		LocalValue = RandomName();
		ClearSendKeys(getwebelement(temp), LocalValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Statement of Work Entered = " + LocalValue);
		SendkeaboardKeys(getwebelement(temp), Keys.ENTER);
		Thread.sleep(3000);

		temp = xml.getlocator("//locators/InputBox").replace("Value", "Number of Days").replace("-1", "2");
		LocalValue = Integer.toString(rnd.nextInt(1000));
		ClearSendKeys(getwebelement(temp), LocalValue);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Number of Days Entered = " + LocalValue);
		SendkeaboardKeys(getwebelement(temp), Keys.ENTER);
		Thread.sleep(3000);

		closePopUp();
		waitForpageload();
		waitforPagetobeenable();
		ClickHereSave();
		waitForpageload();
		waitforPagetobeenable();

	}
	// Added By Abhay

	public void PartialTab(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		waitForpageload();

		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/PartialCheckbox"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/IPVPNSite/PartialCheckbox")));
		Thread.sleep(3000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(3000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Partial Delivery"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Partial Delivery")));
		waitforPagetobeenable();
		Thread.sleep(3000);

		Clickon(getwebelement("(//td[contains(@id,'Partial_Delivery')])[1]"));
		Thread.sleep(3000);

		Clickon(getwebelement("//input[@name='COLT_Partial_Delivery']"));
		Thread.sleep(5000);

		Clickon(getwebelement("//button[@aria-label='Partial Delivery:Submit']"));
		Thread.sleep(3000);
		if (InputData[11].toString().equalsIgnoreCase("IP VPN Access")
				|| InputData[11].toString().equalsIgnoreCase("IP VPN Plus")) {
			AcceptJavaScriptMethod();
		}

		waitforPagetobeenable();
		Thread.sleep(3000);

	}

	public void PartialDeliveryAttachment(Object[] InputData)
			throws IOException, InterruptedException, DocumentException {
		waitforPagetobeenable();
		Select(getwebelement(xmlIP.getlocator("//locators/tabDropdown")), "Attachments");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Selected");
		Clickon(getwebelement(xmlIP.getlocator("//locators/AttachmentTabSelection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attachments Tab Clicked");
		Thread.sleep(10000);

		uploadafile(xmlIP.getlocator("//locators//FileUpload"), "test.txt");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Test File Uploaded");
		Thread.sleep(10000);
		Clickon(getwebelement(xmlIP.getlocator("//locators/DocumnetTypeOther")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Other File Type Clicked");
		Clickon(getwebelement(xmlIP.getlocator("//locators/DownArrow")));
		Clickon(getwebelement(
				xmlIP.getlocator("//locators/DoucmentTypeSelection").replace("Filetype", "Partial Delivery")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Partial Delivery File Type Selected");

	}

	public void PartialDeliverySite(Object[] InputData) throws Exception {
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Layer 3 Resilience")));
		Thread.sleep(2000);
		if (!InputData[10].equals("IP VPN Wholesale"))
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					"Dual Access Unmanaged")));
		else
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					"Dual Access Primary & Backup")));
		waitforAttributeloader();
		waitforPagetobeenable();
		ClickHereSave();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Secondary")));
		waitforPagetobeenable();
		if (InputData[11].equals("IP VPN Access")) {
			MiddleAppDropdown("Router Type", InputData[51].toString());
		}

		Thread.sleep(4000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value",
				"Service Bandwidth (Secondary)")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[40].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value","Layer 3 Resilience")));
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",InputData[39].toString())));
//					waitforPagetobeenable();

//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Router Type")));
//					Thread.sleep(3000);
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Unmanaged Router")));
//					waitforPagetobeenable();

		Clear(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Capacity Check Reference")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Capacity Check Reference")),
				"12");
		SendkeaboardKeys(
				getwebelement(
						xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Capacity Check Reference")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Hard Modify Flag")));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "N")));
		Thread.sleep(2000);
		waitforPagetobeenable();

		SendKeys(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "OSS Platform Flag (Secondary)")),
				InputData[38].toString());
		waitforAttributeloader();
		waitforPagetobeenable();
		Thread.sleep(3000);
//					Clickon(getwebelement(
//							xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "OSS Platform Flag (Primary)")));
//					Thread.sleep(4000);
//					Clickon(getwebelement(
//							xml.getlocator("//locators/SelectValueDropdown").replace("Value", "Legacy")));
//					waitforPagetobeenable();

		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(4000);

		WaitforElementtobeclickable(xml.getlocator("//locators/SelectSiteSearchAccess"));
		Moveon(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SelectSiteSearchAccess")));

		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Search Site");
		WaitforElementtobeclickable(xml.getlocator("//locators/StreetNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/StreetNameAccess")), InputData[61].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Street Name");
		WaitforElementtobeclickable(xml.getlocator("//locators/CountryAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/CountryAccess")), InputData[62].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Country");
		SendKeys(getwebelement(xml.getlocator("//locators/CityTownAccess")), InputData[63].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter City");
		SendKeys(getwebelement(xml.getlocator("//locators/PostalCodeAccess")), InputData[64].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Postal Code");
		SendKeys(getwebelement(xml.getlocator("//locators/PremisesAccess")), InputData[65].toString());
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
		waitforPagetobeenable();
		Thread.sleep(5000);

//					WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/IpGurdianSave"));
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/IpGurdianSave")));
		// Thread.sleep(8000);
		ClickHereSave();
		Thread.sleep(3000);

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartySearchAccess"));
		Moveon(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServicePartySearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Service Party");

		// System.out.println("EnterService");
		waitandForElementDisplay((xml.getlocator("//locators/ServicePartyDropdownAccess")), 8);

		WaitforElementtobeclickable(xml.getlocator("//locators/ServicePartyDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/ServicePartyDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Party Dropdown");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Party Name");

		SendKeys(getwebelement(xml.getlocator("//locators/InputPartyNameAccess")), InputData[69].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Party Name");

		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
		waitforPagetobeenable();

		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/PartyNameSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactSearchAccess"));
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/SiteContactSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Search Site Contact");

		waitandForElementDisplay((xml.getlocator("//locators/SiteContactDropdownAccess")), 8);
		WaitforElementtobeclickable(xml.getlocator("//locators/SiteContactDropdownAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/SiteContactDropdownAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Site Name Dropdown");

		// waitandForElementDisplay((xml.getlocator("//locators/SiteLastNameAccess")),8);
		// WaitforElementtobeclickable(xml.getlocator("//locators/SiteLastNameAccess"));
		// Thread.sleep(8000);
		// Clickon(getwebelement(xml.getlocator("//locators/SiteLastNameAccess")));
		// System.out.println("Last Name Click");
		// ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Last Name");

		WaitforElementtobeclickable(xml.getlocator("//locators/InputSiteNameAccess"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputSiteNameAccess")), InputData[70].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Site Name");

		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSearchAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSearchAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Search");
		waitforPagetobeenable();
		Thread.sleep(3000);

		WaitforElementtobeclickable(xml.getlocator("//locators/LastNameSiteSubmitAccess"));
		Clickon(getwebelement(xml.getlocator("//locators/LastNameSiteSubmitAccess")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click On Submit");

//					WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/IpGurdianSave"));
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/IpGurdianSave")));
//					// Thread.sleep(8000);

		ClickHereSave();
		Thread.sleep(4000);

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Access Type")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[42].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Access Technology")));
		Thread.sleep(2000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[43].toString())));

		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Building Type")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[45].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Customer Site Pop Status")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[46].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Thread.sleep(2000);
		Clear(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "3rd Party Connection Reference")));
		SendKeys(getwebelement(
				xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "3rd Party Connection Reference")),
				"As12");
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "BCP Reference")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "BCP Reference")),
				"As12");
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Cabinet Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[48].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Shelf ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")),
				Keys.ENTER);
		Thread.sleep(2000);

//					Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")));
//					SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
//							"12");
//					SendkeaboardKeys(
//							getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
//							Keys.ENTER);
//					Thread.sleep(2000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Presentation Interface")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[53].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Connector Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[54].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Fibre Type")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[55].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Port Role")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Physical Port")));
		waitforAttributeloader();
		waitforPagetobeenable();

		//
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Diversity Type")));
//					Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "Type 1")));
//					waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Install Time")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[56].toString())));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Router Model")));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "NA")));
		waitforAttributeloader();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Site Name")));
		Thread.sleep(3000);
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[60].toString())));
		waitforAttributeloader();
		waitforPagetobeenable();

		ClickHereSave();
		Thread.sleep(3000);
		WaitforElementtobeclickable((xml.getlocator("//locators/CircuitReferenceAccess")));
		Clickon(getwebelement(xml.getlocator("//locators/CircuitReferenceAccess")));
		waitforAttributeloader();
		Thread.sleep(25000);

		savePage();
		waitforPagetobeenable();
		Thread.sleep(8000);

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "IP Details")));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Secondary")));
		waitforPagetobeenable();

		SendKeys(
				getwebelement(
						xml.getlocator("//locators/IPVPNSite/TextInputPartial").replace("Value", "CPE IPv4 Address")),
				"123.65.19.1");

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputPartial").replace("Value", "IPv4 Prefix")),
				"123.65.19.1");

		SendKeys(
				getwebelement(
						xml.getlocator("//locators/IPVPNSite/TextInputPartial").replace("Value", "PE IPv4 Address")),
				"123.65.19.1");

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInputPartial").replace("Value", "PE Name")),
				"xyz");

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "End Point VPN")));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Secondary")));
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/IPDetailsPlus")));
		Thread.sleep(3000);

		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/ClickDropdownPartial").replace("Value", "VPN Bandwidth")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", InputData[66].toString())));
		waitforPagetobeenable();

//				Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubVPNID")));
//				Thread.sleep(3000);
//				Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectSubVPNList")));
//				Thread.sleep(3000);
//				Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubmitSubVPNList")));
		Thread.sleep(3000);
		// waitforPagetobeenable();

		if (InputData[11].equals("IP VPN Plus")) {

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SearchInput").replace("Value", "Physical Port ID Secondary")));
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/AccessPortList")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SubmitSubVPNList")));
			Thread.sleep(3000);
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "IP Details")));
			waitforPagetobeenable();

			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Secondary")));
			waitforPagetobeenable();

			Clear(getwebelement(
					xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN VRRP IPv4 Address 1")));
			SendKeys(getwebelement(
					xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "LAN VRRP IPv4 Address 1")),
					"123.65.19.1");
			Thread.sleep(3000);

			Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value",
					"Second CPE LAN Interface IPv4 Address")));

			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value",
					"Second CPE LAN Interface IPv4 Address")), "123.65.19.1");

		}
		Thread.sleep(3000);
		ClickHereSave();
		waitforPagetobeenable();
		Thread.sleep(3000);
	}

	public void OperationalAttributesforIPVPNPartial(Object[] InputData) throws Exception {
		savePage();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Sites")));

		waitforPagetobeenable();

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")), "12");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Cabinet ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Secondary")));
		waitforPagetobeenable();

		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/RouterSpecificationSettingPartial"));
		Thread.sleep(10000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/RouterSpecificationSettingPartial")));
		Thread.sleep(4000);
		waitforPagetobeenable();
		Thread.sleep(5000);
		List<WebElement> HeaderList = GetWebElements("//div[@class='AppletStylePopup']//th//div");
		int Attindex = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();
			Attindex = Attindex + 1;
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Attribute Value")) {
				break;
			}
		}
		Thread.sleep(5000);
		int count = getwebelementscount(xml.getlocator("//locators/IPVPNSite/OperationalAttribueCount"));
		System.out.println(count);
		for (int i = 0; i < count; i++) {
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex))));
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))));
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))), "Test1");
		}
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK")));
		savePage();
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();
	}

	public void EnterInstallationChargeInFooterPartial(Object InputData[]) throws Exception {
		Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Installation and Test");
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Click on Installation Dropdown button and Select Installation and Test");
		waitforPagetobeenable();
		Thread.sleep(5000);
		Clear(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")));
		SendKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), Keys.TAB);

		Clear(getwebelement(xml.getlocator("//locators/SecondaryTestingMethod")));
		SendKeys(getwebelement(xml.getlocator("//locators/SecondaryTestingMethod")), "Not Required");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/SecondaryTestingMethod")), Keys.TAB);

		savePage();
		waitforPagetobeenable();
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SaveOrderContinue")));
		waitforPagetobeenable();
		Thread.sleep(3000);
	}

	public void PartialCompletedValidation(Object[] InputData) throws Exception {
		waitforPagetobeenable();

		if (InputData[9].toString().equals("IP VPN Service"))// **Start** Added By Abhay dated 28-Sep-2019
		{
			/*savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders")));
			String ServOrder = ServiceOrder.get().toString();
			// String x= ServiceOrder.get();
			// System.out.println(x);
			// String string = "004-034556";
			String[] parts = ServOrder.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrder")), part2);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrderGo")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickSeibelOrder")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
			Thread.sleep(3000);
			Selectproduct("IP VPN Site");
			openIPVPNSite();
			enterMandatoryFieldsInHeader(InputData);
			NetworkReferenceFill();*/
			IPVPNSITEMiddleApplet(InputData);
			if (!InputData[11].equals("IP VPN Wholesale")) {
				OperationalAttributesforIPVPN(InputData);
			}

			PartialDeliverySite(InputData);
			if (!InputData[11].equals("IP VPN Wholesale")) {
				OperationalAttributesforIPVPNPartial(InputData);
			}
			EnterDateInFooter(InputData);
			EnterBillingDateInFooter(InputData);
			if (!InputData[11].equals("IP VPN Wholesale")) {
				ServiceChargeforIPVPNSite(InputData, "2");
			}

			EnterInstallationChargeInFooterPartial(InputData);
			PartialDeliveryAttachment(InputData);
			CommercialValidation(InputData);
			TechnicalValidation(InputData);
			DeliveryValidation(InputData);
			AlertAccept();
			clickOnManualValidationA();

			waitforPagetobeenable();
			PartialTab(InputData);
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();

			Thread.sleep(5000);
			if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
			{
				ClickContinue();
				Thread.sleep(3000);
			}
			// savePage();
			// Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			AlertAccept();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
			waitforPagetobeenable();
			Thread.sleep(5000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}

			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);

			MovetoIPService();

			// Save the Current URL
			// Create and completed new IP VPN SItes
			// Open the Older Order number;
		} // **End**

		if (InputData[9].toString().equals("IP Access"))// Added By Rekha
		{
			EnterInstallationChargeInFooterPartial(InputData);
			PartialDeliveryAttachment(InputData);
			CommercialValidation(InputData);
			TechnicalValidation(InputData);
			DeliveryValidation(InputData);
			clickOnManualValidationA();
			waitforPagetobeenable();
			PartialTab(InputData);

		}

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
		waitforPagetobeenable();

		Thread.sleep(5000);
		// savePage();
		// Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
		waitforPagetobeenable();
		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (isDisplayed(xml.getlocator("//locators/AlertAccept"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}

		// Pagerefresh();
		Thread.sleep(5000);
		// =======================Added by Rekha ==================== difft pop up was
		// arriving for Ethernet VPN Access=====================
		if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
			Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
		}
		// ============================================================================================================
		System.out.println("Order complete");
		Thread.sleep(5000);

	}

	/*
	 * Purpose: To open assest Created By: Dipesh Jain
	 */

	public void openAsset() throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/AssetButton"));
		Clickon(getwebelement(xml.getlocator("//locators/AssetButton")));
		if (isElementPresent((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/AssetButton")));
		}
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Assest");
		String Order = ServiceOrder.get();
		String[] Order1 = Order.split("/");
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderOM"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderOM")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceOrderOM");
		SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderOM")), Order1[0]);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter ServiceOrderOM:" + Order1[0]);
		WaitforElementtobeclickable(xml.getlocator("//locators/GoButton"));
		Clickon(getwebelement(xml.getlocator("//locators/GoButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Go Button");
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/AssetNumber"));
		Clickon(getwebelement(xml.getlocator("//locators/AssetNumber")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on AssetNumber");
	}

	/*
	 * Purpose: To validate SLA matrix Created By: Dipesh Jain
	 */

	public void validateSlaMatrix(Object[] InputData) throws InterruptedException, DocumentException, IOException {

		if (InputData[179].toString().contains("SLA")) {
			openAsset();

			try {
				Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Entitlements");
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Entitlements tab");
			} catch (Exception e) {
				WaitforElementtobeclickable(xml.getlocator("//locators/EntitlementsTab"));
				Clickon(getwebelement(xml.getlocator("//locators/EntitlementsTab")));
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Entitlements tab");
			}

			waitforPagetobeenable();
			Thread.sleep(4000);
			String ActualSLA = Gettext(getwebelement(xml.getlocator("//locators/SlaTag")));
			String ExpectedSLA = InputData[179].toString();
			System.out.println(ActualSLA);
			System.out.println(ExpectedSLA);
			if (ActualSLA.contains(ExpectedSLA)) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Validate SLA matrix is displayed as" + ActualSLA);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Step: SLA is not correct" + ActualSLA);
			}
		}

	}

	public void LaunchingXNGApplication(Object InputData[]) throws Exception {

		DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
		capabilities.setCapability(CapabilityType.BROWSER_NAME, "internet explorer");
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
		capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
		capabilities.setCapability("requireWindowFocus", true);

		System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "//lib//IEDriverServer.exe");
		InternetExplorerDriver iedr = new InternetExplorerDriver(capabilities);
		iedr.manage().deleteAllCookies();
		iedr.manage().window().maximize();
		Thread.sleep(15000);
		try {
			String URL = null;
			PropertyReader pr = new PropertyReader();
			// Log.info(environment + "_URL");
			URL = pr.readproperty("XNG_URL");
			iedr.get(URL);
			// iedr.get("http://lonxng65:7778/eut05xn/xperweb.home");

			Thread.sleep(15000);
			Robot rb = new Robot();

			Clipboard cl = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection stringSelection = new StringSelection(Getkeyvalue("XNG_Username"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter username in XNG");
			cl.setContents(stringSelection, null);
			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			Thread.sleep(4000);
			rb.keyPress(KeyEvent.VK_TAB);
			rb.keyRelease(KeyEvent.VK_TAB);
			Thread.sleep(8000);
			Clipboard clpassword = Toolkit.getDefaultToolkit().getSystemClipboard();
			StringSelection stringSelection1 = new StringSelection(Getkeyvalue("XNG_Password"));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter password in XNG");
			cl.setContents(stringSelection1, null);

			rb.keyPress(KeyEvent.VK_CONTROL);
			rb.keyPress(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_V);
			rb.keyRelease(KeyEvent.VK_CONTROL);

			rb.keyPress(KeyEvent.VK_ENTER);
			rb.keyRelease(KeyEvent.VK_ENTER);
			Thread.sleep(3000);
			// System.out.println("The title of page is:"+iedr.getTitle());

			iedr.findElement(By.cssSelector("table tr:nth-child(2) > td:nth-child(5) > a")).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on circuit paths");
			System.out.println("The title of page is:" + iedr.getTitle());
			Thread.sleep(3000);

			iedr.findElement(By.cssSelector("table tbody tr td:nth-child(2) input:first-child[name=pathName]")).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: clIck on circuit id");
			Thread.sleep(3000);
			iedr.findElement(By.cssSelector("table tbody tr td:nth-child(2) input:first-child[name=pathName]"))
					.sendKeys(Circuitreferencenumber.get());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter circuit id");
			Thread.sleep(3000);
			iedr.findElement(
					By.cssSelector("body table:nth-child(3) > tbody > tr > td:nth-child(2) > input[type=submit]"))
					.click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on search button");

			Thread.sleep(3000);
			String xp = "//th[text()='ID']/../following-sibling::tr/td/a";
			iedr.findElement(By.xpath(xp)).click();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on cicuit reference number");

			// Bandwidth verify//
			Thread.sleep(5000);
			String title3 = iedr.getTitle();
			System.out.println("The title of third page is:" + title3);
			Thread.sleep(10000);
			if (!InputData[9].toString().equalsIgnoreCase("Dark Fibre")) {
				WebElement bandwidth = iedr.findElement(By.cssSelector("table tbody tr:nth-child(3) td:nth-child(4)"));
				int firstBandwidth;

				if (bandwidth.getText().toLowerCase().contains("k")) {
					int getNumberFromText = Integer.parseInt(bandwidth.getText().toLowerCase().split("k")[0]);
					firstBandwidth = getNumberFromText / 1024;

				} else if (bandwidth.getText().toLowerCase().contains("g")) {
					firstBandwidth = Integer.parseInt(bandwidth.getText().toLowerCase().split("g")[0]);
				} else {
					firstBandwidth = Integer.parseInt(bandwidth.getText().toLowerCase().split("m")[0]);
				}
				System.out.println("The first text is:" + firstBandwidth);

				if (InputData[9].toString().equalsIgnoreCase("IP VPN Service")) {
					String data = InputData[40].toString();
					int textinput = Integer.parseInt(data.toLowerCase().split("m")[0].trim());
					System.out.println(textinput);
					Assert.assertTrue(textinput == firstBandwidth, "Bandwith not match in XNG");
					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Bandwidth verify has to be done");
				} else if (InputData[9].toString().equalsIgnoreCase("public Wave Service")
						|| InputData[9].toString().equalsIgnoreCase("public Ethernet")
						|| InputData[9].toString().equalsIgnoreCase("DCA Ehternet")
						|| InputData[9].toString().equalsIgnoreCase("Ultra Low Latency")
						|| InputData[9].toString().equalsIgnoreCase("Ethernet VPN Access")
						|| InputData[9].toString().equalsIgnoreCase("Ethernet Hub")
						|| InputData[9].toString().equalsIgnoreCase("IP Access")) {
					String data = InputData[32].toString();
					if (InputData[9].toString().equalsIgnoreCase("public Ethernet")
							|| InputData[9].toString().equalsIgnoreCase("IP Access")) {
						int textinput = Integer.parseInt(data.toLowerCase().split("m")[0].trim());
						System.out.println(textinput);
						Assert.assertTrue(textinput == firstBandwidth, "Bandwith not match in XNG");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Bandwidth verify has to be done");
					} else {
						int textinput = Integer.parseInt(data.toLowerCase().split("g")[0].trim());
						System.out.println(textinput);
						Assert.assertTrue(textinput == firstBandwidth, "Bandwith not match in XNG");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Bandwidth verify has to be done");
					}
				}

				else if (InputData[9].toString().equalsIgnoreCase("Ethernet Spoke")
						|| InputData[9].toString().equalsIgnoreCase("Ethernet Line")
						|| InputData[9].toString().equalsIgnoreCase("Wave")) {
					String data = InputData[78].toString();
					if (InputData[9].toString().equalsIgnoreCase("Wave")) {
						int textinput = Integer.parseInt(data.toLowerCase().split("g")[0].trim());
						System.out.println(textinput);
						Assert.assertTrue(textinput == firstBandwidth, "Bandwith not match in XNG");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Bandwidth verify has to be done");

					} else {
						int textinput = Integer.parseInt(data.toLowerCase().split("m")[0].trim());
						System.out.println(textinput);
						Assert.assertTrue(textinput == firstBandwidth, "Bandwith not match in XNG");
						ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Bandwidth verify has to be done");
					}
				}
			} // end of if loop
				// for ordering customer

			String data1 = OrderingCustomer.get().trim();
			System.out.println(data1);
			String orderingCustomer = iedr
					.findElement(By.xpath("//b[text()='Ordering Customer:']/parent::td/following-sibling::td[1]"))
					.getText();
			System.out.println("THe ordering customer is:" + orderingCustomer);
			Assert.assertTrue(data1.equalsIgnoreCase(orderingCustomer), "Ordering customer not match in XNG");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Verify ordering customer");

			// for topology//

//					String topology = iedr.findElement(By.xpath("//b[text()='Topology:']/parent::td/following-sibling::td[1]")).getText();
//					System.out.println("Topolgy from browser:"+ topology);
//					String topologyin = InputData[27].toString();
//					String sub = topologyin.substring(0, 15).trim();
//					System.out.println("Topology in input sheet:"+sub );
//					Assert.assertTrue(sub.equalsIgnoreCase(topology), "Ordering customer not match in XNG");
//					ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Topology verified");
//					
			// for closing the browser
			iedr.close();

		} catch (Exception e) {
			iedr.close();
		}

	}

	public void CarnorCompletedValidation(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
		waitforPagetobeenable();

		Thread.sleep(5000);
		// savePage();
		// Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
		waitforPagetobeenable();
		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (isDisplayed(xml.getlocator("//locators/AlertAccept"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}

		// Pagerefresh();
		Thread.sleep(5000);
		// =======================Added by Rekha ==================== difft pop up was
		// arriving for Ethernet VPN Access=====================
		if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
			Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
		}
		// ============================================================================================================
		System.out.println("Order complete");
		Thread.sleep(5000);

	}

	public void AbandonedforIPVPN(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (InputData[9].toString().equals("IP VPN Service"))// *Start* Added By Abhay dated 28-Sep-2019
		{
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders")));
			String ServOrder = ServiceOrder.get().toString();
			// String x= ServiceOrder.get();
			// System.out.println(x);
			// String string = "004-034556";
			String[] parts = ServOrder.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrder")), part2);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrderGo")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickSeibelOrder")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
			Thread.sleep(3000);
			Selectproduct("IP VPN Site");
			openIPVPNSite();
			enterMandatoryFieldsInHeader(InputData);
			NetworkReferenceFill();

		}
	}

	public void CompletedValidationforCancel(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (InputData[9].toString().equals("IP VPN Service"))// **Start** Added By Abhay dated 28-Sep-2019
		{
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders")));
			String ServOrder = ServiceOrder.get().toString();
			// String x= ServiceOrder.get();
			// System.out.println(x);
			// String string = "004-034556";
			String[] parts = ServOrder.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrder")), part2);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrderGo")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickSeibelOrder")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
			Thread.sleep(3000);
			Selectproduct("IP VPN Site");
			openIPVPNSite();
			enterMandatoryFieldsInHeader(InputData);
			NetworkReferenceFill();
			IPVPNSITEMiddleApplet(InputData);

			EnterDateInFooter(InputData);
			EnterBillingDateInFooter(InputData);
			if (!InputData[11].equals("IP VPN Wholesale")) {
				ServiceChargeforIPVPNSite(InputData, "2");
			}
			if (!InputData[11].equals("IP VPN Wholesale")) {
				OperationalAttributesforIPVPN(InputData);
			}
			InstallationTest();
			CommercialValidation(InputData);
			TechnicalValidation(InputData);
			DeliveryValidation(InputData);
			AlertAccept();
			clickOnManualValidationA();

			waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();

			Thread.sleep(5000);
			if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
			{
				ClickContinue();
				Thread.sleep(3000);
			}
			// savePage();
			// Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			AlertAccept();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
			waitforPagetobeenable();
			Thread.sleep(5000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}

			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);

			MovetoIPService();

			// Save the Current URL
			// Create and completed new IP VPN SItes
			// Open the Older Order number;
		} // **End**

		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
	}

	/*
	 * Created by: Dipesh New method added to open Service order
	 */

	public void openServiceOrder(Object[] InputData) throws Exception {
		Thread.sleep(5000);

		try {
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			if (isElementPresent((xml.getlocator("//locators/AlertAccept")))) {
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
				Thread.sleep(3000);
				Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			}
			Thread.sleep(3000);
		} catch (Exception e) {
			try {
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceOrderTab")));
			} catch (Exception e1) {

				e1.printStackTrace();
			}
		}
		WaitforElementtobeclickable(xml.getlocator("//locators/InputServiceOrder"));
		SendKeys(getwebelement(xml.getlocator("//locators/InputServiceOrder")), ServiceOrder.get());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderGo")));
		Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderClickOn")));
		Thread.sleep(10000);
	}

	/*
	 * Created by: Dipesh Purpose: Validate Xtrac workflows for Commercial
	 * Validation and Deliver
	 */
	public void validateXtrac() throws InterruptedException, DocumentException {
		Thread.sleep(30000);
		Clickon(getwebelement("//a[text()='Workflows']"));
		Thread.sleep(10000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click workflows  Link");
		List<WebElement> wb = GetWebElements(
				"//table[@summary='Workflow']/tbody/tr/following-sibling::tr/td/following-sibling::td/following-sibling::td[contains(@id,'EXTERNAL_URL')]");
		List<WebElement> statusWf = GetWebElements(
				"//table[@summary=\"Workflow\"]/tbody/tr/following-sibling::tr/td/following-sibling::td[contains(@id,\"Workflow_Status\")]");
		int rowCount = getwebelementscount("//table[@summary='Workflow']/tbody/tr/following-sibling::tr");
		ExtentTestManager.getTest().log(LogStatus.INFO, " Step: Total Workflow available : " + rowCount);

		for (int i = 0; i < rowCount; i++) {
			if (wb.get(i).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Workflow from xtrac is displayed in row no. : " + i);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Step: Workflow from xtrac is not displayed in row no. : " + i);
			}
			if (GetText(statusWf.get(i)).equalsIgnoreCase("in progress")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Workflow status is displayed as In Progress ");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Step: Workflow status is not displayed as In Progress");
			}
		}
	}

	/*
	 * Created by: Dipesh Purpose: Validate Xtrac workflows for completed order
	 */
	public void validateXtracComplete() throws InterruptedException, DocumentException {
		Thread.sleep(5000);
		Clickon(getwebelement("//a[text()='Workflows']"));
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click workflows  Link");
		List<WebElement> wb = GetWebElements(
				"//table[@summary='Workflow']/tbody/tr/following-sibling::tr/td/following-sibling::td/following-sibling::td[contains(@id,'EXTERNAL_URL')]");
		List<WebElement> statusWf = GetWebElements(
				"//table[@summary=\"Workflow\"]/tbody/tr/following-sibling::tr/td/following-sibling::td[contains(@id,\"Workflow_Status\")]");
		int rowCount = getwebelementscount("//table[@summary='Workflow']/tbody/tr/following-sibling::tr");
		ExtentTestManager.getTest().log(LogStatus.INFO, " Step: Total Workflow available : " + rowCount);

		for (int i = 0; i < rowCount; i++) {
			if (wb.get(i).isDisplayed()) {
				ExtentTestManager.getTest().log(LogStatus.PASS,
						" Step: Workflow from xtrac is displayed in row no. : " + i);
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL,
						" Step: Workflow from xtrac is not displayed in row no. : " + i);
			}

			if (GetText(statusWf.get(i)).equalsIgnoreCase("Completed")) {
				ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Workflow status is displayed as Completed ");
			} else {
				ExtentTestManager.getTest().log(LogStatus.FAIL, " Step: Workflow status is not displayed as Completed");
			}

		}
	}

	public void CompletedValidation_offnet(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (InputData[9].toString().equals("IP VPN Service"))// **Start** Added By Abhay dated 28-Sep-2019
		{
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			WaitforElementtobeclickable(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders"));
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickLink").replace("Value", "Customer Orders")));
			String ServOrder = ServiceOrder.get().toString();
			// String x= ServiceOrder.get();
			// System.out.println(x);
			// String string = "004-034556";
			String[] parts = ServOrder.split("/");
			String part1 = parts[0];
			String part2 = parts[1];
			waitforPagetobeenable();
			SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrder")), part2);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/CustOrderGo")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickSeibelOrder")));
			waitforPagetobeenable();
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/NewServiceOrder")));
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on New Service Order");
			Thread.sleep(3000);
			Selectproduct("IP VPN Site");
			openIPVPNSite();
			enterMandatoryFieldsInHeader(InputData);
			NetworkReferenceFill();
			IPVPNSITEMiddleApplet(InputData);

			EnterDateInFooter(InputData);
			EnterBillingDateInFooter(InputData);
			if (!InputData[11].equals("IP VPN Wholesale")) {
				ServiceChargeforIPVPNSite(InputData, "2");
			}
			if (!InputData[11].equals("IP VPN Wholesale")) {
				OperationalAttributesforIPVPN(InputData);
			}
			InstallationTest();
			CommercialValidation(InputData);
			TechnicalValidation(InputData);
			DeliveryValidation(InputData);
			AlertAccept();
			clickOnManualValidationA();

			if (InputData[9].toString().contains("IP VPN Service")) {
				CEOS_Offnet();
				LaunchingCEOSApplication(InputData);
			}

			waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();

			Thread.sleep(5000);
			if (InputData[11].toString().equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
			{
				ClickContinue();
				Thread.sleep(3000);
			}
			// savePage();
			// Thread.sleep(6000);
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			AlertAccept();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
			waitforPagetobeenable();
			Thread.sleep(5000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}

			savePage();
			waitforPagetobeenable();
			Thread.sleep(10000);

			MovetoIPService();

			// Save the Current URL
			// Create and completed new IP VPN SItes
			// Open the Older Order number;
		} // **End**

		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);

		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
		waitforPagetobeenable();

		Thread.sleep(5000);
		// savePage();
		// Thread.sleep(6000);
		Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Order Complete");
		waitforPagetobeenable();
		Thread.sleep(5000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		savePage();
		waitforPagetobeenable();
		Thread.sleep(10000);
		if (isDisplayed(xml.getlocator("//locators/AlertAccept"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}

		// Pagerefresh();
		Thread.sleep(5000);
		// =======================Added by Rekha ==================== difft pop up was
		// arriving for Ethernet VPN Access=====================
		if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) {
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
			Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
		}
		// ============================================================================================================
		System.out.println("Order complete");
		Thread.sleep(5000);
		// Added by Abhay
		String CompValidation = null;
		CompValidation = getwebelement2(xml.getlocator("//locators/IPVPNSite/OrderStatusInput")).getAttribute("value");
		System.out.println(CompValidation);

		Assert.assertTrue(CompValidation.contains("Comp"),
				" Order status failed to Complete. It is displayed as : " + CompValidation);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Order Staus Verified in as Completed");

	}

	public void WriteServiceOrderNumber(Object[] InputData) throws IOException {

		String path = "src//Data//ServiceOrder.xlsx";
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				createFile(path);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		AppendData(path, InputData);
	}

	public void createFile(String Path) throws IOException {
		FileOutputStream fos = new FileOutputStream(Path);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("NewOrder");
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("Product Name");

		Cell cell1 = row.createCell(1);
		cell1.setCellValue("Service OrderNumber");

		Cell cell2 = row.createCell(2);
		cell2.setCellValue("Date");
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

	public void AppendData(String Path, Object[] InputData) throws FileNotFoundException {
		try {

			FileInputStream fis = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			String cururl = CurrentUrl();
			cururl = cururl.substring(0, 20);

			// XSSFSheet sheet = workbook.
			int i = workbook.getNumberOfSheets();
			System.out.println(i);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int num = sheet.getLastRowNum();
			Row row = sheet.createRow(++num);
			String OrderNumber = InputData[9].toString().equalsIgnoreCase("IP VPN Service")
					? ServiceOrder2.get().toString()
					: ServiceOrder.get().toString();
			row.createCell(0).setCellValue(InputData[9].toString());
			row.createCell(1).setCellValue(OrderNumber);
			row.createCell(2).setCellValue(CurrentDate());
			row.createCell(3).setCellValue(cururl);
			fis.close();
			FileOutputStream fos = new FileOutputStream(Path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public void OpenTab(String TabName) throws IOException, InterruptedException, DocumentException {

		// 8888

		// div[@title='Third Level View Bar']//a[text()='Installation and Test']
		try {
			List<WebElement> TabList = GetWebElements(
					"//div[@title='Third Level View Bar']//a[text()='" + TabName + "']");
			if (TabList.size() > 0) {
				waitForpageload();
				waitforPagetobeenable();
				// WaitforElementtobeclickable(xml.getlocator("//locators/Tabs").replace("tabName",
				// TabName));
				Clickon(getwebelement(xml.getlocator("//locators/Tabs").replace("tabName", TabName)));
				System.out.println("Click on tab : " + TabName);
			} else {
				Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), TabName);
				waitForpageload();
				waitforPagetobeenable();
				Clickon(getwebelement(xml.getlocator("//locators/Tabs").replace("tabName", TabName)));
			}
		} catch (Exception e) {
			System.out.println("Exception :" + e.toString());
		}
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Open Tab :" + TabName);
		waitforPagetobeenable();
		waitForpageload();
	}

	public HashMap<Integer, HashMap<String, String>> EROData()
			throws IOException, InterruptedException, DocumentException {
		OpenTab("ERO");
		HashMap<String, String> CPNdetail = new HashMap<String, String>();
		HashMap<Integer, HashMap<String, String>> MainMap = new HashMap<Integer, HashMap<String, String>>();
		List<WebElement> ColumnHeader = GetWebElements(xml.getlocator("//locators/ServiceOrderGridHeader"));
		Assert.assertTrue(ColumnHeader.size() > 0, "ERO table no found");
		int CpnIndex = -1;
		int CityIndex = -1;
		int i = 0;
		for (WebElement ele : ColumnHeader) {
			javascriptexecutor(ele);
			String Text = ele.getText();

			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("CPN Number")) {
				CpnIndex = i;
			} else if (Text.equalsIgnoreCase("City")) {
				CityIndex = i;
			}
			if (CpnIndex > -1 && CityIndex > -1)
				break;
			i++;
		}

		Assert.assertTrue(CpnIndex > -1 && CityIndex > -1, "Not able to locate 'CPN Number' and 'City' column");
		List<WebElement> GridRow = GetWebElements(xml.getlocator("//locators/EroGirdRow"));
		for (int k = 0; k < GridRow.size() - 1; k++) {
			javascriptexecutor(GridRow.get(k));

			String templocator = xml.getlocator("//locators/EroGridData");
			System.out.println("Locator1 : " + templocator);
			String CpnLocator = templocator.replace("-row10", String.valueOf(k + 1)).replace("-td10",
					String.valueOf(CpnIndex + 1));
			System.out.println("Locator2 : " + CpnLocator);
			String CityLocator = templocator.replace("-row10", String.valueOf(k + 1)).replace("-td10",
					String.valueOf(CityIndex + 1));
			System.out.println("Locator3 : " + CityLocator);

			String CpnValue = Gettext(getwebelement(CpnLocator));
			System.out.println("CPN Number : " + CpnValue);
			String CityValue = Gettext(getwebelement(CityLocator));
			System.out.println("City  : " + CityValue);

			Assert.assertTrue(CpnValue != null || CpnValue != "", "Not able to locate CPN no for City : " + CityValue);
			HashMap<String, String> detail = new HashMap<String, String>();
			detail.put(CpnValue, CityValue);
			MainMap.put(k + 1, detail);
		}
		for (Entry<Integer, HashMap<String, String>> entry : MainMap.entrySet()) {
			Map<String, String> ChildMap = entry.getValue();
			for (Entry<String, String> entry2 : ChildMap.entrySet()) {
				String childKey = entry2.getKey();
				String childValue = entry2.getValue();
				System.out.println("Key  : " + childKey + ",Value : " + childValue);
			}
		}

		return MainMap;
	}

	public void ColtPromissDate(Object InputData[]) throws Exception {
		WaitforElementtobeclickable((xml.getlocator("//locators/IPVPNSite/ColtPromissday")));
		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/ColtPromissday")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/ColtPromissday")), CurrentDate());
	}

	public void Carnor_SelectServiceGroupTab(Object[] InputData) throws Exception {
		if (InputData[9].toString().equals("Voice Line V") || InputData[9].toString().equals("SIP Trunking")
				|| InputData[9].toString().equals("Interconnect") || InputData[9].toString().equals("Number Hosting")) {
			waitForpageload();
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/DropDown")));
//	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Drop down");
//	Clickon(getwebelement(xml.getlocator("//locators/InstallationAndTestTab")));
			Thread.sleep(2000);
			Select(getwebelement(xml.getlocator("//locators/DropDown")), "Service Group");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Group Tab");
			Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupNew")));
			Thread.sleep(5000);
			WaitforElementtobeclickable(xml.getlocator("//locators/serviceGrouplookup"));
			Clickon(getwebelement(xml.getlocator("//locators/serviceGrouplookup")));
			// Thread.sleep(5000);
			Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupOk")));
			Thread.sleep(5000);
			/*
			 * Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupItemNew")));
			 * Thread.sleep(5000); WaitforElementtobeclickable(xml.getlocator(
			 * "//locators/ServiceGroupItemlookup"));
			 * Clickon(getwebelement(xml.getlocator("//locators/ServiceGroupItemlookup")));
			 * Thread.sleep(5000);
			 * //Clickon(getwebelement(xml.getlocator("//locators/selectitem")));
			 * //Thread.sleep(5000);
			 * Clickon(getwebelement(xml.getlocator("//locators/ServiceorderOk")));
			 * Thread.sleep(5000); savePage();
			 */
		}
	}

	public void CPSolutionSite(Object[] InputData) throws Exception {
		waitforPagetobeenable();
		Thread.sleep(8000);
		MiddleAppDropdown("A End Resilience Option", InputData[47].toString());
		MiddleAppDropdown("B End Resilience Option",InputData[48].toString());
		MiddleAppDropdown("CPE Solution Type", InputData[52].toString());
		MiddleAppDropdown("Network Topology", InputData[49].toString());
		
		MiddleAppDropdown("Service Bandwidth", InputData[50].toString());
		MiddleAppDropdown("Service Type", InputData[51].toString());
		MiddleAppDropdown("Topology", InputData[53].toString());
		MiddleAppDropdown("OSS Platform Flag", InputData[54].toString());
		
		MiddleAppDropdown("Management Access", InputData[55].toString());
		MiddleAppDropdown("SLA Variant", InputData[56].toString());
		
		ClickHereSave();
		waitforPagetobeenable();

		Clickon(getwebelement(xml.getlocator("//locators/Clicktoshowfullinfomiddle")));
		Thread.sleep(3000);

		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Attachment Link")),"NA");
		waitforAttributeloader();
		Thread.sleep(3000);
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Diverse From Service Reference")),"NA");
		waitforAttributeloader();
		Thread.sleep(3000);

		closePopUp();
		Save();
		addSiteADetails(InputData);
		ClickHereSave();
		
		waitforPagetobeenable();
		AEndDropdownSelection("Access Type", InputData[57].toString());
		AEndDropdownSelection("Access Technology", InputData[58].toString());
		AEndDropdownSelection("Building Type", InputData[59].toString());
		
		if (InputData[32].toString().equalsIgnoreCase("offnet")) 
		{
			ClickHereSave();
			AEndDropdownSelection("Third Party Access Provider", InputData[71].toString());
			AEndDropdownSelection("DSL SLA Class", InputData[72].toString());
			AEndDropdownSelection("Third Party SLA Tier", InputData[73].toString());
			AEndInputEnter("3rd Party Connection Reference", "VBG-"+Integer.toString(rnd.nextInt(1000)));
		} 
			AEndDropdownSelection("Customer Site Pop Status", InputData[60].toString());
			AEndInputEnter("Site Name Alias", "STA-"+Integer.toString(rnd.nextInt(1000)));
			AEndDropdownSelection("Site Type", InputData[61].toString());
			//AEndInputEnter("Hub Service ID", "HUB-"+Integer.toString(rnd.nextInt(1000))+"-"+Integer.toString(rnd.nextInt(1000)));
		
			// Install Time
			AEndDropdownSelection("Install Time", InputData[62].toString());
		
			// CPE Information
			int rnd_int = rnd.nextInt(1000);
			AEndInputEnter("Cabinet ID", Integer.toString(rnd.nextInt(1000)));
			AEndDropdownSelection("Cabinet Type", InputData[63].toString());
			AEndInputEnter("Shelf ID", Integer.toString(rnd.nextInt(1000)));
		
			AEndInputEnter("Slot ID", Integer.toString(rnd.nextInt(1000)));
			AEndInputEnter("Physical Port ID", Integer.toString(rnd.nextInt(1000)));
			AEndDropdownSelection("Presentation Interface", InputData[64].toString());
			AEndDropdownSelection("Connector Type", InputData[65].toString());
			AEndDropdownSelection("Fibre Type", InputData[66].toString());
			
			AEndInputEnter("Ethertype", Integer.toString(rnd.nextInt(1000)));
			AEndInputEnter("VLAN Tag ID", Integer.toString(rnd.nextInt(1000)));
			
			AEndDropdownSelection("Function", InputData[67].toString());
			AEndDropdownSelection("Management", InputData[68].toString());
			AEndDropdownSelection("Vendor", InputData[69].toString());
			AEndDropdownSelection("Model", InputData[70].toString());
		
		ClickHereSave();
		waitforPagetobeenable();
		RandomDropSelection("B", "Router Country");
		RandomDropSelection("B", "Router Model");
		RandomDropSelection("B", "Site Name");
		ClickHereSave();
		waitforPagetobeenable();
		OperationalAttributesforIPVPN(InputData);
		ClickHereSave();
		waitforPagetobeenable();
	}
	public void MiddleAppOperationAttributes(String Product) throws Exception
	{
		
		//wave, dark fibre, ultra low, private wave service,node
		if(Product.equals("Wave")||Product.equals("Dark Fibre")||Product.equals("Ultra Low Latency")||Product.equals("Private Wave Service")||Product.equals("Private Wave Node"))
		{
		List<WebElement> SeetingList = GetWebElements(xml.getlocator("//locators/MiddleAppSetting"));
		
		if(!SeetingList.isEmpty())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Setting Button Found");
			Clickon(SeetingList.get(0));
			EnterOpertaionalAttributes();
			Thread.sleep(3000);
		}
		}
	}
	public void EnterOpertaionalAttributes() throws Exception {
		savePage();
		waitforPagetobeenable();
		Thread.sleep(5000);
		List<WebElement> HeaderList = GetWebElements("//div[@class='AppletStylePopup']//th//div");
		if(!HeaderList.isEmpty())
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Attribute found");
		int Attindex = 0;
		for (WebElement ele : HeaderList) {
			javascriptexecutor(ele);
			String Text = ele.getText();
			Attindex = Attindex + 1;
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Attribute Value")) {
				break;
			}
		}
		Thread.sleep(5000);
		int count = getwebelementscount(xml.getlocator("//locators/IPVPNSite/OperationalAttribueCount"));
		System.out.println(count);
		for (int i = 0; i < count; i++) 
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttribueClick").replace("index", String.valueOf(i + 1)).replace("-9", String.valueOf(Attindex))));
			WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex)));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))));
			ClearSendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeText").replace("-9",String.valueOf(Attindex))), "Test1");
		}
		Thread.sleep(3000);
		WaitforElementtobeclickable(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK"));
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/OperationalAttributeOK")));
		savePage();
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();
		}
	}
}