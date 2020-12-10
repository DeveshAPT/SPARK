package ScriptHelper;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import Driver.DriverHelper;
import Driver.XMLReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.dom4j.DocumentException;
import Reporter.ExtentTestManager;

public class CeasHelper extends DriverHelper {

	WebElement el;
	XMLReader xml = new XMLReader("src\\Locators\\SiebelOrder.xml");

	public CeasHelper(WebDriver parentdriver) 
	{
		super(parentdriver);
	}

	public void openServiceOrder(Object[] InputData) throws Exception {
		Thread.sleep(15000);
		Pagerefresh();
		try 
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceTab"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceTab")));
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) 
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}
			System.out.println("Service tab clickon");
		} 
		catch (Exception e) 
		{
			try 
			{
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceTab")));
				System.out.println("Service tab javascript");
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		waitforPagetobeenable();
		waitForpageload();
		Thread.sleep(4000);
	//	ServiceOrder.set(InputData[187].toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll"))); // as per Ayush
		//System.out.println("click service order search field"+ InputData[0].toString());
		if (InputData[9].toString().equalsIgnoreCase("IP VPN Service")) 
		{
				ExtentTestManager.getTest().log(LogStatus.PASS," Step: Working On newly Created service order  :" + ServiceOrder2.get().toString());
				SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll")),ServiceOrder2.get().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS," Step: Search Service Order No :" + ServiceOrder2.get().toString());
		}
		else 
		{
				ExtentTestManager.getTest().log(LogStatus.PASS," Step: Working On newly Created service order  :" + ServiceOrder.get().toString());
				SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll")),ServiceOrder.get().toString());
				ExtentTestManager.getTest().log(LogStatus.PASS," Step: Search Service Order No :" + ServiceOrder.get().toString());
		}
		System.out.println("enter data order search field");
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
	}
	
	public void CeaseMainMethod(Object[] InputData) throws Exception 
	{
		
		String[] billerror= {"BILLING ERROR","DOWNSTREAM SYSTEM ERRO"};
		boolean billing = false;
		String BillingStatus = null;
		for (int i = 0; i < 35; i++) {
			Thread.sleep(3 * 10000);
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
			waitforPagetobeenable();
			waitForpageload();
			Thread.sleep(4000);
			BillingStatus = GetBillingStatus();
			System.out.println("Service Order Current Status : " + BillingStatus);
			// if(isElementPresent(xml.getlocator("//locators/BillingComplete"))) BILLING
			// ERROR
			if (BillingStatus.equalsIgnoreCase("COMPLETE")||BillingStatus.equalsIgnoreCase("N/A")) 
			{
				billing = true;
				break;
			} 
			else if (Arrays.asList(billerror).indexOf(BillingStatus)>=0)
			{
				billing = false;
				break;
			}
		}
		String TempOrder = InputData[9].toString().equalsIgnoreCase("IP VPN Service") ? ServiceOrder2.get().toString(): ServiceOrder.get().toString();
		//Assert.assertTrue(BillingStatus.equalsIgnoreCase("COMPLETE") || BillingStatus.equalsIgnoreCase("BILLING ERROR")|| BillingStatus.equalsIgnoreCase("SENT TO BILLING"),"Not Able to Proceed The Cease for Order Number : " + TempOrder + "as Billing Status is : "			+ BillingStatus);
		Assert.assertTrue(BillingStatus.equalsIgnoreCase("COMPLETE") ,"Not Able to Proceed The Cease for Order Number : " + TempOrder + "as Billing Status is : "+ BillingStatus);
		if (!billing || BillingStatus.equalsIgnoreCase("SENT TO BILLING")) 
		{
			// Internal cease functionality
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Doing Cease by Internal Cease Option");
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderReferenceNo"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
			waitForpageload(); // added by Ayush
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
			Thread.sleep(5000);

			// internal cease click
			WaitforElementtobeclickable(xml.getlocator("//locators/CeaseInternal"));
			Clickon(getwebelement(xml.getlocator("//locators/CeaseInternal")));
			waitForpageload(); // added by Ayush
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Internal Cease");
			Thread.sleep(5000);
		} 
		else 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Doing Cease by External Cease Option");
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/CeaseOrder")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on cease order number");
		}
		/*
		 * Assert.assertTrue(billing,
		 * "Billing for Service Order No :"+ServiceOrder.get().toString()
		 * +"Not completed");
		 * Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
		 * System.out.println("service order click");
		 */
		Thread.sleep(30000);
		waitForpageload();
		waitforPagetobeenable();
		SiebelCeaseOrdernumber.set(Gettext(getwebelement(xml.getlocator("//locators/ServiceOrderModifyNumber']"))));
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Generated Service Order Reference No: " + SiebelCeaseOrdernumber.get());
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");

		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
		
		CeaseAction(InputData);
	}

	public void openExistingServiceOrder(String ExistingOrder) throws Exception {
		Thread.sleep(15000);
		Pagerefresh();
		try 
		{
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceTab"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceTab")));
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) 
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}
			System.out.println("Service tab clickon");
		} 
		catch (Exception e) 
		{
			try 
			{
				safeJavaScriptClick(getwebelement(xml.getlocator("//locators/ServiceTab")));
				System.out.println("Service tab javascript");
			} 
			catch (Exception e1) 
			{
				e1.printStackTrace();
			}
		}
		waitforPagetobeenable();
		waitForpageload();
		Thread.sleep(4000);
	//	ServiceOrder.set(InputData[187].toString());
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll"))); // as per Ayush
		System.out.println("click service order search field"+ ExistingOrder);
		
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Cease For service order  :" + ExistingOrder);
		SendKeys(getwebelement(xml.getlocator("//locators/ServiceOrderSearchForAll")),ExistingOrder);
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Search Service Order No :" + ExistingOrder);
		
		System.out.println("enter data order search field");
		WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
	}
	
	public void CeaseExistingService(Object[] InputData) throws Exception 
	{
		String ExistingOrder=InputData[0].toString();
		String[] billerror= {"BILLING ERROR","DOWNSTREAM SYSTEM ERRO"};
		boolean billing = false;
		String BillingStatus = null;
		for (int i = 0; i < 35; i++) 
		{
			//Thread.sleep(3 * 10000);
			waitforPagetobeenable();
			waitForpageload();
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderArrow"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderArrow")));
			waitforPagetobeenable();
			waitForpageload();
			Thread.sleep(4000);
			BillingStatus = GetBillingStatus();
			System.out.println("Service Order Current Status : " + BillingStatus);
			if (BillingStatus.equalsIgnoreCase("COMPLETE")||BillingStatus.equalsIgnoreCase("N/A")) 
			{
				billing = true;
				break;
			} 
			else if (Arrays.asList(billerror).indexOf(BillingStatus)>=0)
			{
				billing = false;
				break;
			}
		}
		Assert.assertTrue(BillingStatus.equalsIgnoreCase("COMPLETE") || BillingStatus.equalsIgnoreCase("BILLING ERROR")||
				BillingStatus.equalsIgnoreCase("N/A")|| BillingStatus.equalsIgnoreCase("SENT TO BILLING"),"Not Able to Proceed The Cease for Order Number : " 
					      + ExistingOrder + "as Billing Status is : "+ BillingStatus);
		if (!billing || BillingStatus.equalsIgnoreCase("SENT TO BILLING")) 
		{
			// Internal cease functionality
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Doing Cease by Internal Cease Option");
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceOrderReferenceNo"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
			waitForpageload(); // added by Ayush
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
			Thread.sleep(5000);

			// internal cease click
			WaitforElementtobeclickable(xml.getlocator("//locators/CeaseInternal"));
			Clickon(getwebelement(xml.getlocator("//locators/CeaseInternal")));
			waitForpageload(); // added by Ayush
			waitforPagetobeenable();
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Internal Cease");
			Thread.sleep(5000);
		} 
		else 
		{
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Doing Cease by External Cease Option");
			safeJavaScriptClick(getwebelement(xml.getlocator("//locators/CeaseOrder")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on cease order number");
		}
		
		Thread.sleep(30000);
		waitForpageload();
		waitforPagetobeenable();
		SiebelCeaseOrdernumber.set(Gettext(getwebelement(xml.getlocator("//locators/ServiceOrderModifyNumber']"))));
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Generated Service Order Reference No: " + SiebelCeaseOrdernumber.get());
		
		SendKeys(getwebelement(xml.getlocator("//locators/OpportunityNo")), InputData[4].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Opportunity as : "+InputData[4].toString());
		
		SendKeys(getwebelement(xml.getlocator("//locators/RequestReceivedDate")), CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Request Received Date");

		Clickon(getwebelement(xml.getlocator("//locators/ServiceOrderReferenceNo")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Service Order Reference No");
		
		CeaseAction();
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
	public void CeaseAction(Object[] InputData) throws Exception
	{
		waitforPagetobeenable();
		waitForpageload();
		Thread.sleep(7000);
		
		String SourceRef = "Test" + Integer.toString(GetRandomNumber(20));
		
		SendKeys(getwebelement("//input[@aria-labelledby='COLT_Service_Order_Source_Ref_Label']"),SourceRef);
		
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		System.out.println("Enter New Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/AddOrderSubType"));
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");

		
		SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "All");
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

		if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) 
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
		Thread.sleep(10000);
		waitForpageload();
		waitforPagetobeenable();
		String _curdate= CurrentDate();
	
		String temp1 = xml.getlocator("//locators/OrderDateField/CalendrButton");
		String temp = temp1.replace("Value", "Order Signed Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(_curdate);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Signed Date " + _curdate);

		
		
		temp = temp1.replace("Value", "Order Received Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(_curdate);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date " + _curdate);

	
		temp = temp1.replace("Value", "Customer Requested Date");
		//WaitforElementtobeclickable(temp);
		List<WebElement> RequestListDate = GetWebElements(temp);
		WebElement ele1 = RequestListDate.get(1);
		Clickon(ele1);
		CalenderdateSelection(CurrentDate());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Customer Requested Date " + CurrentDate());

		
		WaitforElementtobeclickable(xml.getlocator("//locators/Billing"));
		Clickon(getwebelement(xml.getlocator("//locators/Billing")));
		System.out.println("BILLING TAB");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Billing");
		
		Thread.sleep(10000);
		waitForpageload();
		waitforPagetobeenable();
		System.out.println("Current Date : " + CurrentDate());
		String fDate = FutureDate(1);
		System.out.println("Future Date : " + fDate);
		
		temp = temp1.replace("Value", "Billing End Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(fDate);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Customer Requested Date " + fDate);
		
		try 
		{
			if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) 
			{
				WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
				Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
				waitForpageload();
				System.out.println("page load succesfuuly now come to middle applet");
				waitforPagetobeenable();
				Thread.sleep(5000);
			}
		} 
		catch (Exception e) 
		{
			savePage();
			waitforPagetobeenable();
			waitForpageload();
			System.out.println("save button comes");
		}

	}	
	public void CeaseAction() throws Exception
	{
		waitforPagetobeenable();
		waitForpageload();
		Thread.sleep(7000);
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderSubTypeSearch"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderSubTypeSearch")));
		System.out.println("Enter New Order");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order Sub Type Search");

		WaitforElementtobeclickable(xml.getlocator("//locators/AddOrderSubType"));
		Clickon(getwebelement(xml.getlocator("//locators/AddOrderSubType")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Add Order Sub Type");

		SendKeys(getwebelement(xml.getlocator("//locators/InputOrderSubType")), "All");
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

		if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) 
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
		Thread.sleep(10000);
		waitForpageload();
		waitforPagetobeenable();
		String _curdate= CurrentDate();
		
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
		
			
		WaitforElementtobeclickable(xml.getlocator("//locators/Billing"));
		Clickon(getwebelement(xml.getlocator("//locators/Billing")));
		System.out.println("BILLING TAB");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Billing");
		Thread.sleep(10000);
		waitForpageload();
		waitforPagetobeenable();
		System.out.println("Current Date : " + CurrentDate());
		String fDate = FutureDate(1);
		System.out.println("Future Date : " + fDate);
		
		temp = xml.getlocator("//locators/OrderDateField/CalendrButton");
		temp = temp.replace("Value", "Billing End Date");
		WaitforElementtobeclickable(temp);
		Clickon(getwebelement(temp));
		CalenderdateSelection(fDate);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Order Received Date " + CurrentDate());
		
		// Term");
		
		Clickon(getwebelement(xml.getlocator("//locators/POStartDateAccess")));
		try 
		{
			if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) 
			{
				WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
				Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
				waitForpageload();
				System.out.println("page load succesfuuly now come to middle applet");
				waitforPagetobeenable();
				Thread.sleep(5000);
			}
		} 
		catch (Exception e) 
		{
			savePage();
			waitforPagetobeenable();
			waitForpageload();
			System.out.println("save button comes");
		}

	}

	public void alertPopUp() throws DocumentException, InterruptedException 
	{
		if (isDisplayed((xml.getlocator("//locators/AlertAccept")))) 
		{
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
	}

	public void CeaseCompletedValidation() throws Exception 
	{
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
		if (isDisplayed(xml.getlocator("//locators/AlertAccept"))) 
		{
			System.out.println("");
			System.out.println("Alert Present");
			WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
			Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
		}
		Thread.sleep(5000);
		try 
		{
			if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) 
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
				Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		System.out.println("Order complete");
		Thread.sleep(5000);
		// Added by Abhay
		String CompValidation = null;
		CompValidation = getwebelement2(xml.getlocator("//locators/IPVPNSite/OrderStatusInput")).getAttribute("value");
		System.out.println(CompValidation);

		Assert.assertTrue(CompValidation.contains("Comp")," Order status failed to Complete. It is displayed as : " + CompValidation);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Order Staus Verified in as Completed");

	}

	public void CeaseCommercialValidation(String ProductName) throws Exception {

		getwebelement(xml.getlocator("//locators/OrderStatus")).clear();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Order Status");
		
		SendKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), "Commercial Validation");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Commercial Validation");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.ENTER);
		
		Thread.sleep(5000);
		if (!ProductName.equalsIgnoreCase("Ethernet Hub"))
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/OrderStatus")), Keys.TAB);
		Thread.sleep(2000);
		if (ProductName.equalsIgnoreCase("Ethernet Hub")) 
		{
			try 
			{
				if (isElementPresent(xml.getlocator("//locators/PopupYes"))) 
				{
					WaitforElementtobeclickable(xml.getlocator("//locators/PopupYes"));
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/PopupYes")));
					waitForpageload();
					System.out.println("Pop Up Yes Clicked");
					waitforPagetobeenable();
					Thread.sleep(5000);
				}
			} 
			catch (Exception e) 
			{
				System.out.println("Pop Up Yes Clicked");
			}
		}
		waitforPagetobeenable();
		try 
		{
			if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) 
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
				Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));
			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		waitforPagetobeenable();
		Thread.sleep(10000);
	}

	public void DeliveryValidation(String ProductName) throws Exception {
		waitforPagetobeenable(); // as per Aman
		Thread.sleep(5000);
		WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
		Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
		Thread.sleep(3000);
		Clickon(getwebelement(xml.getlocator("//locators/SelectDeliveryValidation")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Entered Order Status Delivery");
		if (!ProductName.equalsIgnoreCase("Ethernet Hub")) 
		{
			waitforPagetobeenable();
			Thread.sleep(5000);
		}
		if (ProductName.equalsIgnoreCase("IP VPN Wholesale")) // Added by Abhay
		{
			ClickContinue();
			waitforPagetobeenable();
			Thread.sleep(3000);
		}
		Thread.sleep(5000);
		if (ProductName.equalsIgnoreCase("Ethernet Hub")) 
		{
			try 
			{
				if (isElementPresent(xml.getlocator("//locators/PopupYes"))) 
				{
					WaitforElementtobeclickable(xml.getlocator("//locators/PopupYes"));
					safeJavaScriptClick(getwebelement(xml.getlocator("//locators/PopupYes")));
					waitForpageload();
					System.out.println("Pop Up Yes Clicked");
					waitforPagetobeenable();
					Thread.sleep(5000);
				}
			} 
			catch (Exception e) 
			{
				System.out.println("Pop Up Yes Clicked");
			}
		}
//			SendKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), "Not Required");
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter value in primary test method");
//			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/primarytestingmethod")), Keys.TAB);
//			Thread.sleep(3000);
//			savePage();
		try 
		{
			if (isElementPresent(xml.getlocator("//locators/SubnetworkPopUP"))) 
			{
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/SubnetworkPopUP")));
				Clickon(getwebelement(xml.getlocator("//locators/SubnetworkPopUP")));

			}
		} 
		catch (Exception e) 
		{
			System.out.println(e.toString());
		}
		waitforPagetobeenable();
	}

	public void ClickContinue() throws DocumentException, InterruptedException 
	{
		if (isElementPresent((xml.getlocator("//locators/IPVPNSite/BlankPriceContinue")))) 
		{
			WaitforElementtobeclickable((xml.getlocator("//locators/IPVPNSite/BlankPriceContinue")));
			Clickon(getwebelement(xml.getlocator("//locators//IPVPNSite/BlankPriceContinue")));
		}
		Thread.sleep(3000);
	}

	public String GetBillingStatus() throws InterruptedException, DocumentException, IOException 
	{
		List<WebElement> HeaderList = GetWebElements(xml.getlocator("//locators/ServiceOrderGridHeader"));
		int StatusHeader = -1;
		int i = 0;
		for (WebElement ele : HeaderList) 
		{
			javascriptexecutor(ele);
			String Text = ele.getText();
			System.out.println("Column : " + Text);
			if (Text.equalsIgnoreCase("Billing Status")) 
			{
				StatusHeader = i;
				break;
			}
			i++;
		}
		Assert.assertTrue(StatusHeader > -1, "Column Name 'Billing Status' not found");
		String temp = xml.getlocator("//locators/ServiceOrderGridItem").replace("-10", String.valueOf(i + 1));
		String ServiceOrderStatus = Gettext(getwebelement(temp));
		System.out.println("Service Order Current Status : " + ServiceOrderStatus);
		return ServiceOrderStatus;
	}

}
