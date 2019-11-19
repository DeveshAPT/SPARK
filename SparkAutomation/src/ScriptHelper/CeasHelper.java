package ScriptHelper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import Driver.DriverHelper;
import Driver.XMLReader;

import org.dom4j.DocumentException;

import Reporter.ExtentTestManager;



public class CeasHelper extends DriverHelper{
	
	
	
	WebElement el;
	XMLReader xml=new XMLReader("src\\Locators\\SiebelOrder.xml");
	
	public CeasHelper(WebDriver parentdriver)
	{
		super(parentdriver);
	}
	public void Cease(Object[] InputData) throws Exception
	{
	Thread.sleep(8000);
	Pagerefresh();
	try {
	WaitforElementtobeclickable(xml.getlocator("//locators/ServiceTab"));
	Clickon(getwebelement(xml.getlocator("//locators/ServiceTab")));
	System.out.println("Service tab clickon");
	} catch (Exception e) {
	try {
	safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceTab")));
	System.out.println("Service tab javascript");
	} catch (Exception e1) {

	e1.printStackTrace();
	}
	}
	waitforPagetobeenable();
	waitForpageload();
	Thread.sleep(4000);
	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll")));	//as per Ayush
	System.out.println("click service order search field");
	SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll")),ServiceOrder.get().toString());
	System.out.println("enter data order search field");
	WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
	//Need some modification 
	boolean billing=false;
	for (int i=0;i<5;i++) {
		Thread.sleep(3*60000);
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
		if(isElementPresent(xml.getlocator("//locators/BillingComplete")))
		{
			billing=true;
			break;
		}
		if(isElementPresent(xml.getlocator("//locators/BillingError")))
		{
			billing=false;
			break;
		}
	}
	if(!billing)
	{
		//Internal cease functionality 
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderReferenceNo"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
		waitForpageload(); // added by Ayush
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
		Thread.sleep(5000);
		
		//internal cease click
		WaitforElementtobeclickable(xml.getlocator("//locators/CeaseInternal"));
		Clickon(getwebelement(xml.getlocator("//locators/CeaseInternal")));
		waitForpageload(); // added by Ayush
		waitforPagetobeenable();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
		Thread.sleep(5000);
	}
	else
	{
		safeJavaScriptClick(getwebelement(xml.getlocator("//locators/CeaseOrder")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on cease order number");
	}
	/*Assert.assertTrue(billing, "Billing for Service Order No :"+ServiceOrder.get().toString()+"Not completed");
	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
	System.out.println("service order click");*/
    
	waitForpageload();
	waitforPagetobeenable();
	SiebelCeaseOrdernumber.set(Gettext(getwebelement(xml.getlocator("//locators/ServiceOrderModifyNumber']"))));
	ExtentTestManager.getTest().log(LogStatus.PASS,
	" Step: Generated Service Order Reference No: " + SiebelCeaseOrdernumber.get());
	SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");

	Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");

	WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
	Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
	System.out.println("Enter New Order");
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");

	WaitforElementtobeclickable(xml.getlocator("//locators/AddOrderSubType"));
	Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");

	SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")),"All");
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.ENTER);
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), Keys.TAB);
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type DropDown");
	
	Thread.sleep(10000);
	alertPopUp();
	
	WaitforElementtobeclickable(xml.getlocator("//locators/SubmitSubOrderType"));
	Clickon(getwebelement(xml.getlocator("//locators/SubmitSubOrderType")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Submit Sub Order Type");
	waitforPagetobeenable();

	WaitforElementtobeclickable(xml.getlocator("//locators/CeaseReason"));
	Clickon(getwebelement(xml.getlocator("//locators/CeaseReason")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on cease reason field");
	SendKeys(getwebelement(xml.getlocator("//locators/CeaseReason")), "Cease Initiated by Colt");
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/CeaseReason")), Keys.TAB);

	if(isElementPresent(xml.getlocator("//locators/SaveOrderChanges")))
	{
	WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
	Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
	waitForpageload();
	System.out.println("page load succesfuuly now come to middle applet");
	waitforPagetobeenable();
	}
	System.out.println("Enter Domain Footer");
	Moveon(getwebelement(xml.getlocator("//locators/OrderDates")));
	System.out.println("Moved Mouse");
	Clickon(getwebelement(xml.getlocator("//locators/OrderDates")));
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Dates");

	SendKeys(getwebelement(xml.getlocator("//locators/OrderSignedDate")),CurrentDate());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Signed Date");

	SendKeys(getwebelement(xml.getlocator("//locators/OrderReceivedDate")), CurrentDate());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date");


	SendKeys(getwebelement(xml.getlocator("//locators/CustomerRequestedDate")), CurrentDate());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date");

	WaitforElementtobeclickable(xml.getlocator("//locators/Billing"));
	Clickon(getwebelement(xml.getlocator("//locators/Billing")));
	System.out.println("BILLING TAB");
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Billing");

	SendKeys(getwebelement(xml.getlocator("//locators/BillingEndDate")), CurrentDate());
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Term"); 
	ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Contract Term"); 
	SendkeaboardKeys(getwebelement(xml.getlocator("//locators/BillingEndDate")), Keys.TAB);
	Clickon(getwebelement(xml.getlocator("//locators/POStartDateAccess")));
	try {
	if(isElementPresent(xml.getlocator("//locators/SaveOrderChanges")))
	{
	WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
	Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
	waitForpageload();
	System.out.println("page load succesfuuly now come to middle applet");
	waitforPagetobeenable();
	Thread.sleep(5000);
	}
	}
	catch(Exception e)
	{
	System.out.println("save button comes");
	}

	}
	public void alertPopUp() throws DocumentException, InterruptedException
	{
		if (isDisplayed((xml.getlocator("//locators/AlertAccept")))) {
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
	}
}
