package ScriptHelper;

import java.io.IOException;

import org.dom4j.DocumentException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import com.relevantcodes.extentreports.LogStatus;
import Driver.DriverHelper;
import Driver.XMLReader;

import Reporter.ExtentTestManager;

import java.util.List;
import java.util.Random;

public class ModHelper extends DriverHelper {
	Random rand = new Random();
	WebElement el;
	XMLReader xml = new XMLReader("src\\Locators\\SiebelOrder.xml");
	XMLReader xml2 = new XMLReader("src\\Locators\\VLV.xml");
	XMLReader xmlCld = new XMLReader("src\\Locators\\CloudUc.xml");
	XMLReader xml3 = new XMLReader("src\\Locators\\SiebelOrderEtherline.xml"); // added by shivananda
	XMLReader xmlHns = new XMLReader("src\\Locators\\EtherNetHubSpoke.xml");

	public ModHelper(WebDriver parentdriver) {
		super(parentdriver);
	}

	public void ClickHereSave() throws InterruptedException, DocumentException {
		try 
		{		//Added by Ayush
			waitForpageload();
			waitforPagetobeenable();
			Thread.sleep(10000);
			List<WebElement> savelist1=GetWebElements("//div[@class='colt-bottom-panel']//a[@class='colt-noedit-order-save colt-blue-color']");
			List<WebElement> savelist2=GetWebElements("//div[contains(@style,'block')]//a[contains(text(),'save')]");
			List<WebElement> savelist3=GetWebElements("//a[text()='Click here to save your order changes.']");
			if(savelist1.size()>0)
				{
				System.out.println("I am save without popup found");
				Clickon(savelist1.get(0));
				}
			if(savelist2.size()>0)
				{
				System.out.println("I am save with popup found");
				Clickon(savelist2.get(0));
				}
			if(savelist3.size()>0)
			{
			System.out.println("I am save with popup found");
			Clickon(savelist3.get(0));
			}
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on SaveAccess");
			waitforPagetobeenable();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public void MiddleAppDropdown(String DropdownName, String DropdownValue) throws InterruptedException, DocumentException
	{
		String lct=xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", DropdownName);
		String lctValue=xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", DropdownValue);
		
		WaitforElementtobeclickable(lct);
		Clickon(getwebelement(lct));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  : " + DropdownName);
		Thread.sleep(1500);
		waitForpageload();
	    waitforPagetobeenable();
		Clickon(getwebelement(lctValue));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select "+DropdownName+" value : " + DropdownValue);
	}
	
	public void MiddleAppTextBox(String TextBoxName, String InputText) throws InterruptedException, DocumentException, IOException
	{
		waitForpageload();
	    waitforPagetobeenable();
	    
	    String lct=xmlCld.getlocator("//locators/MidlleAppText").replace("Value", TextBoxName);
		WaitforElementtobeclickable(lct);
		ClearSendKeys(getwebelement(lct),InputText);
		SendkeaboardKeys(getwebelement(lct),Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Enter in ("+TextBoxName+") as  : " + InputText);
	}
	public void AEndDropdownSelection(String DropdownName, String DropdownValue) throws InterruptedException, DocumentException
	{
		
	    String eleLoct=xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", DropdownName);
	    System.out.println(eleLoct);
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		eleLoct=xml.getlocator("//locators/R4/SiteABSelection").replace("Value", DropdownValue);
		System.out.println(eleLoct);
		waitForpageload();
	    waitforPagetobeenable();
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Dropdwon ("+DropdownName+") Select: " + DropdownValue);
	}
	public void BEndDropdownSelection(String DropdownName, String DropdownValue) throws InterruptedException, DocumentException
	{
		String eleLoct=xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", DropdownName);
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		
		eleLoct=xml.getlocator("//locators/R4/SiteABSelection").replace("Value", DropdownValue);
		waitForpageload();
	    waitforPagetobeenable();
		WaitforElementtobeclickable(eleLoct);
		Clickon(getwebelement(eleLoct));
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Dropdwon ("+DropdownName+") Select: " + DropdownValue);
	}
	public void AEndInputEnter(String TextBoxName, String InputText) throws InterruptedException, DocumentException, IOException
	{
		waitForpageload();
	    waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)),InputText);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", TextBoxName)),Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Enter in ("+InputText+") as  : " + InputText);
		
	}
	public void BEndInputEnter(String TextBoxName, String InputText) throws InterruptedException, DocumentException, IOException
	{
		waitForpageload();
	    waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)),InputText);
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", TextBoxName)),Keys.ENTER);
		ExtentTestManager.getTest().log(LogStatus.PASS," Step: Enter in ("+InputText+") as  : " + InputText);
		
	}
	// Created by Aman
	public void modTechMiddleAppletCPESolutionService() throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Access Primary & Backup"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Access Primary & Backup")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Dual Access Primary & Backup");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  B End Resilience Option");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Access Primary & Backup"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Access Primary & Backup")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Dual Access Primary & Backup");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	// Added By Devesh
	public void LeadCapacity(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitforPagetobeenable();
		String ProductName = InputData[8].toString();
		if (ProductName.equalsIgnoreCase("Private Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary"));
			SendKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")),
					InputData[12].toString());
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Existing Capacity Lead Time Primary");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.ENTER);
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/ExistingCapacityLeadTimePrimary")), Keys.TAB);
			waitforPagetobeenable();
			// ClickHereSave();
			savePage();
		}
	}

	// Added By Devesh
	public void ModTechR4(Object[] InputData) throws InterruptedException, DocumentException {
		String ProductName = InputData[8].toString();
		if (ProductName.equalsIgnoreCase("Private Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth")));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[35].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Service Bandwidth : " + InputData[35].toString());

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
		} else if (ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Hard Modify Flag")));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", InputData[29].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Hard Modify Flag Type : " + InputData[29].toString());

		}

	}

	// Added By Devesh
	public void ModTechPrivateEthernet(Object[] InputData) throws InterruptedException, DocumentException {
		String ProductName = InputData[8].toString();
		if (ProductName.equalsIgnoreCase("Private Ethernet") || ProductName.equalsIgnoreCase("DCA Ethernet")) {

			// Site A
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
			Thread.sleep(7000);
			waitforPagetobeenable();

			// Site B
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
		if (ProductName.equalsIgnoreCase("DCA Ethernet")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/AEndSiteDropDown").replace("Value", "Access Type")));

			WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/AList").replace("Value", "Colt Fibre"));
			Clickon(getwebelement(
					xml.getlocator("//locators/DarkFiber/AList").replace("Value", InputData[84].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Access Type : " + InputData[84].toString());

			// Setting A
			ClickHereSave();
			savePage();

			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteASetting"));
			// clickUsingAction(getwebelement(xml.getlocator("//locators/R4/SiteASetting")));
			// javascriptexecutor(el);
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteASetting")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Site A Setting Click");
			waitforPagetobeenable();
			Thread.sleep(15000);

			// Pop Up
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
			ClickHereSave();
			// Setting B
			savePage();
			WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteBSetting"));
			clickUsingAction(getwebelement(xml.getlocator("//locators/R4/SiteBSetting")));
			// Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBSetting")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Site B Setting Click");
			waitforPagetobeenable();

			// Pop up
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
			ClickHereSave();
			waitforPagetobeenable();
			waitForpageload();

		}
	}

	// Added By Devesh
	public void Save() throws InterruptedException, DocumentException {
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/Save"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/Save")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click  on Save");
		waitforPagetobeenable();
	}

	public void ModTechforcommanproduct(Object[] Inputdata) throws Exception {
		System.out.println("middle applet start");
		Waitforvisibilityofelement(xml2.getlocator("//locators/SupportStarDate"));
		WaitforElementtobeclickable(xml2.getlocator("//locators/SupportStarDate"));
		Clear(getwebelement(xml2.getlocator("//locators/SupportStarDate")));

		// getwebelement(xml2.getlocator("//locators/SupportStarDate")).clear();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");
		Clickon(getwebelement(xml2.getlocator("//locators/SupportStarDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupportStarDate");
		SendKeys(getwebelement(xml2.getlocator("//locators/SupportStarDate")), Inputdata[78].toString());

		WaitforElementtobeclickable(xml2.getlocator("//locators/SupprtEndDate"));
		Clear(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));

		// getwebelement(xml2.getlocator("//locators/SupprtEndDate")).clear();
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clear Date");
		Clickon(getwebelement(xml2.getlocator("//locators/SupprtEndDate")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter  SupprtEndDate");
		System.out.println("the End date value is" + Inputdata[79].toString());

		SendKeys(getwebelement(xml2.getlocator("//locators/SupprtEndDate")), Inputdata[79].toString());

		Clickon(getwebelement(xml2.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on BCN Recurring Charge MRC Search");
		Thread.sleep(10000);

		System.out.println("middle applet end");

	}

	public void ModTech(Object[] Inputdata) throws Exception 
	{
		switch (Inputdata[9].toString()) {
		// added by aman
		case "SIP Trunking": {
			modHeader(Inputdata);
			modSipTrunkingMiddleapplet(Inputdata);
			break;
		}
		case "Private Web Service": {
			modHeader(Inputdata);
			modTechPrivateWebServiceFibreMiddleApplet(Inputdata);
			// modTechPrivateWaveNode(Inputdata);
			modTechDarkFibreA(Inputdata);
			modTechDarkFibreB(Inputdata);
			waitForpageload();
			waitforPagetobeenable();
			break;
		}
		// added by Aman
		case "IP VPN Service": {
			if (Inputdata[10].toString().equalsIgnoreCase("IP VPN Wholesale")) {
				modTechPrizmNetMiddleApplet(Inputdata);
				ClickHereSave();
			}
			if (Inputdata[10].toString().equalsIgnoreCase("IP VPN Access")) {
				modTechPrizmNetMiddleApplet(Inputdata);
				ClickHereSave();
			}
			if (Inputdata[10].toString().equalsIgnoreCase("IP VPN Plus")) {
				modTechPrizmNetMiddleApplet(Inputdata);
				ClickHereSave();
			} //
			if (Inputdata[10].toString().equalsIgnoreCase("SWIFTNet")) {
				modTechPrizmNetMiddleApplet(Inputdata);
				ClickHereSave();
			}
			if (Inputdata[10].toString().equalsIgnoreCase("PrizmNet")) {
				modTechPrizmNetMiddleApplet(Inputdata);
				ClickHereSave();
			}
			break;
		}
		case "CPE Solutions Service": {
			modHeader(Inputdata);
			modTechMiddleAppletCPESolutionService();
			break;

		}
		case "Ethernet Access": {
			modHeader(Inputdata);
			modTechEthernetAccessMiddleApplet(Inputdata);
			modTechPrivateWaveNode(Inputdata);
			waitForpageload();
			waitforPagetobeenable();
			break;

		}
		case "Dark Fibre": {
			modHeader(Inputdata);
			modTechDarkFibreMiddleApplet(Inputdata);
			modTechDarkFibreA(Inputdata);
			modTechDarkFibreB(Inputdata);
			// modTechPrivateWaveNode(Inputdata);
			ClickHereSave();
			waitForpageload();
			waitforPagetobeenable();
			break;

		}
		case "Private Wave Service": {
			modHeader(Inputdata);
			modTechPrivateWebServiceFibreMiddleApplet(Inputdata);
			modTechPrivateWaveNode(Inputdata);
			waitForpageload();
			waitforPagetobeenable();
			break;

		}
		case "Ultra Low Latency": {
			modHeader(Inputdata);
			modTechultraLowLatencyMiddleApplet(Inputdata);
			modTechPrivateWaveNode(Inputdata);
			waitForpageload();
			waitforPagetobeenable();
			break;
		}
		case "Private Wave Node": {
			modHeader(Inputdata);
			modTechPrivateWaveNodeMiddleApplet(Inputdata);
			modTechPrivateWaveNode(Inputdata);
			Save();
			waitForpageload();
			waitforPagetobeenable();
			break;
		}
		case "Ethernet Line": 
		{
			MiddleAppDropdown("Service Bandwidth",Inputdata[50].toString());
			AEndDropdownSelection("Install Time","Business Hours");
			BEndDropdownSelection("Install Time","Business Hours");
			ClickHereSave();
			break;
		}
		case "Wave": {
			MiddleAppDropdown("Service Bandwidth",Inputdata[50].toString());
			AEndDropdownSelection("Install Time","Business Hours");
			BEndDropdownSelection("Install Time","Business Hours");
			ClickHereSave();
			break;
		}
		case "Private Ethernet": {
			ModTechR4(Inputdata);
			ModTechPrivateEthernet(Inputdata);
			Save();
			break;
		}
		case "DCA Ethernet": {
			ModTechR4(Inputdata);
			ModTechPrivateEthernet(Inputdata);
			// Save();
			break;
		}
		case "IP Guardian": {

			// Added By Aman Gupta
			waitforPagetobeenable();
			MiddleAppTextBox("Alerting Notification Email Address",Inputdata[47].toString());
			MiddleAppDropdown("Service Bandwidth",Inputdata[52].toString());
			MiddleAppTextBox("Test Window",Inputdata[53].toString());
			ClickHereSave();
			break;
		}
		case "Voice Line V": 
		{
			MiddleAppTextBox("Total Number of DDIs",Integer.toString(GetRandomNumber(20)));
			ClickHereSave();
			break;
		}
		case "IP Access": {
			WaitforElementtobeclickable(xml.getlocator("//locators/RouterTypeDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/RouterTypeDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/SelectRouterTypeDropDownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/SelectRouterTypeDropDownAccess")));
			waitforAttributeloader();
			waitforPagetobeenable();
			

			WaitforElementtobeclickable(xml.getlocator("//locators/Layer3ResillanceDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/Layer3ResillanceDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/Layer3ResillanceSelectDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/Layer3ResillanceSelectDropdownAccess")));
			waitforAttributeloader();
			waitforPagetobeenable();
			

			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthDropdownAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthDropdownAccess")));
			WaitforElementtobeclickable(xml.getlocator("//locators/ServiceBandwidthSelectAccess"));
			Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthSelectAccess")));
			waitforAttributeloader();
			waitforPagetobeenable();
			

			ClickHereSave();

			waitforPagetobeenable();
			break;
		}
		case "Interconnect": {

			System.out.println("go to else loop of call admission control");
			Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
			Thread.sleep(6000);
			SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), "5");
			Thread.sleep(3000);
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter call admission control");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), Keys.TAB);

			Clear(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")));
			Thread.sleep(4000);
			SendKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), "9");
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter number of signalling trunks");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), Keys.TAB);

			WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
			Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
			waitForpageload();
			waitforPagetobeenable();
			break;

		}
		case "Cloud Unified Communications": {
			ModTechforcommanproduct(Inputdata);
			break;
		}
		case "Professional Services": {
			ModTechforcommanproduct(Inputdata);
			break;
		}
		case "IP Voice Solutions": {
			ModTechforcommanproduct(Inputdata);
			break;
		}
		case "Ethernet Spoke": {

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Resilience Option")));
			Thread.sleep(2000);
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					Inputdata[27].toString())));

			// waitforAttributeloader();
			waitforPagetobeenable();

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdownB").replace("Value", "Install Time")));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value",
					Inputdata[59].toString())));
			waitforPagetobeenable();

			if (isElementPresent(xml.getlocator("//locators/SaveOrderChanges"))) {
				WaitforElementtobeclickable(xml.getlocator("//locators/SaveOrderChanges"));
				Clickon(getwebelement(xml.getlocator("//locators/SaveOrderChanges")));
				// waitForpageload();
				System.out.println("page load succesfuuly now come to middle applet");
			} else {
				savePage();
			}
			waitforPagetobeenable();
			Thread.sleep(3000);

//			Clickon(getwebelement(xmlHns.getlocator("//locators/CustomizeButton")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Customize Button");
//			Thread.sleep(5000);
//
//			Select(getwebelement(xmlHns.getlocator("//locators/ServiceBandwidth")), Inputdata[11].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Service Bandwidth");
//			Thread.sleep(5000);
//			Clickon(getwebelement(xmlHns.getlocator("//locators/Proceed")));
//			Thread.sleep(5000);
//
//			savePage();
//			Thread.sleep(5000);
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/EthernetConnectionLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection Link");
//
//			safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SpokeSiteLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Spoke Site Link");
//
//			Clickon(getwebelement(xmlHns.getlocator("//locators/InstallationTimeLink")));
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time Link");
//
//			Select(getwebelement(xmlHns.getlocator("//locators/InstallTime")), Inputdata[112].toString());
//			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time");
//			Clickon(getwebelement(xmlHns.getlocator("//locators/DoneEthernetConnection")));
//			Thread.sleep(20000);

			break;
		}
		case "Ethernet Hub": {
			modHeader(Inputdata);
			modEthernetHubMiddleApplet(Inputdata);
			break;
		}
		// Added By Aman Gupta
		case "Number Hosting": {
			modHeader(Inputdata);
			modTechNumberHosting(Inputdata);
			break;
		}
		// added by aman gupta
		case "PrizmNet": {
			modHeader(Inputdata);
			modTechPrizmNetMiddleApplet(Inputdata);
			break;
		}
		case "SWIFTNet": {
			modHeader(Inputdata);
			modTechSwiftNetMiddleApplet(Inputdata);
			modTechDarkFibreA(Inputdata);
			break;
		}
		// aman gupta
		case "Managed Dedicated Firewall": {
			modHeader(Inputdata);
			modTechMangedDeicatedFirewall(Inputdata);
			break;
		}
		// Added by aman gupta
		case "Ethernet VPN Access": {
			modHeader(Inputdata);
			modTechPrivateWaveNode(Inputdata);
			ClickHereSave();
			ethernetVpnAccessMissleApplet(Inputdata);
			// modtechInstallation(Inputdata);
			// modTechPrivateWaveNode(Inputdata);
			ClickHereSave();
			waitforPagetobeenable();

			break;
		}

		default: {
			break;
		}
		}

	}

	public void modtechInstallation(Object[] Inputdata) throws Exception {
		Thread.sleep(5000);
		waitForpageload();
		waitforPagetobeenable();

		System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
		Clear(getwebelement(xml.getlocator("//locators/SiteADropdownClick").replace("Value", "Install Time")));
		SendKeys(getwebelement(xml.getlocator("//locators/SiteADropdownClick").replace("Value", "Install Time")),
				"Business Hours");
		SendkeaboardKeys(
				(getwebelement(xml.getlocator("//locators/SiteADropdownClick").replace("Value", "Install Time"))),
				Keys.TAB);

		int rand_int1 = rand.nextInt(1000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")),
				Integer.toString(rand_int1));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

		int rand_int2 = rand.nextInt(1000);
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")),
				Integer.toString(rand_int2));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
	}

	public void ethernetVpnAccessMissleApplet(Object[] Inputdata) throws Exception {
		Thread.sleep(5000);
		waitforPagetobeenable();
		Clickon(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")));
		Clear(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")));
		SendKeys(getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess")), "20 Mbps");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/ServiceBandwidthIPAccess"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on ServiceBandwidth");
		ClickHereSave();
		Pagerefresh();
		waitForpageload();
		waitforPagetobeenable();
	}

	public void modEthernetHubMiddleApplet(Object[] Inputdata) throws Exception {
		waitforPagetobeenable();
		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")), "121");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Slot ID")),
				Keys.ENTER);
		Thread.sleep(2000);

		Clear(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")));
		SendKeys(getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
				"NA");
		SendkeaboardKeys(
				getwebelement(xml.getlocator("//locators/IPVPNSite/TextInput").replace("Value", "Physical Port ID")),
				Keys.ENTER);
		Thread.sleep(2000);
		Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "Install Time")));
		Clickon(getwebelement(
				xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", Inputdata[59].toString())));
		waitforPagetobeenable();
//		WaitforElementtobeclickable(xml.getlocator("//locators/CustomizeButton"));
//		Clickon(getwebelement(xmlHns.getlocator("//locators/CustomizeButton")));
//		Thread.sleep(30000);
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Customize button");
//		WaitforElementtobeclickable(xml.getlocator("//locators/CustomizeServiceBandwidth"));
//		Clickon(getwebelement(xmlHns.getlocator("//locators/CustomizeServiceBandwidth")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Service Bandwidth");
//		Select(getwebelement(xmlHns.getlocator("//locators/CustomizeServiceBandwidth")), Inputdata[11].toString());
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Picked the updated bandwidth");
//		savePage();
//		Thread.sleep(5000);
//		WaitforElementtobeclickable(xml.getlocator("//locators/EthernetConnectionLink"));
//		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/EthernetConnectionLink")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection link");
//		Thread.sleep(10000);
//		WaitforElementtobeclickable(xml.getlocator("//locators/HubSiteLink"));
//		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/HubSiteLink")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Aend Site link");
//		Thread.sleep(10000);
//		WaitforElementtobeclickable(xml.getlocator("//locators/InstallationTimeLink"));
//		Clickon(getwebelement(xmlHns.getlocator("//locators/InstallationTimeLink")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time link");
//		SendKeys(getwebelement(xmlHns.getlocator("//locators/InstallTime")), Inputdata[95].toString());
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Update the Installation time");
//		WaitforElementtobeclickable(xml.getlocator("//locators/DoneEthernetConnection"));
//		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/DoneEthernetConnection")));
//		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection link");
//		savePage();
//		Thread.sleep(10000);
//		waitforPagetobeenable();
	}

	// aman gupta
	public void modTechNumberHosting(Object[] Inputdata) throws InterruptedException, DocumentException, IOException {
		System.out.println("go to else loop of call admission control");
		Clear(getwebelement(xml.getlocator("//locators/CallAdmissionControl")));
		Thread.sleep(6000);
		SendKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), "3");
		Thread.sleep(3000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter call admission control");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/CallAdmissionControl")), Keys.TAB);

		Clear(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")));
		Thread.sleep(4000);
		SendKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), "9");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Enter number of signalling trunks");
		SendkeaboardKeys(getwebelement(xml.getlocator("//locators/NumberOfSignallingTrunks")), Keys.TAB);

		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitForpageload();
		waitforPagetobeenable();
	}

	// aman gupta
	public void modTechMangedDeicatedFirewall(Object[] Inputdata)
			throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		System.out.println("valu of 178=" + Inputdata[179].toString());
		System.out.println("valu of 178=" + Inputdata[177].toString());
		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "CPE Combination ID"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "CPE Combination ID")));
		System.out.println("valu of 180=" + Inputdata[180].toString());
		SendKeys(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "CPE Combination ID")), "50");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click  CPE Combination ID");
		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequired")
				.replace("Value", "High Availability Required"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequired")
				.replace("Value", "High Availability Required")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  High Avilabilty");
		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequiredli"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/HighAvailabilityRequiredli")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Yes");
		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Save");
		waitForpageload();
		waitforPagetobeenable();
	}

	// Aman gupta
	public void modTechDarkFibreA(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		String ProductName = InputData[8].toString();
		int rand_int1 = rand.nextInt(1000);
		int rand_int2 = rand.nextInt(1000);
		if (!(ProductName.equalsIgnoreCase("Private Wave Service"))) {
			System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
			// .SiteAInstallationTimePUD(Inputdata);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period"));
			Clear(getwebelement(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")),
					Integer.toString(rand_int2));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
		}
		if (ProductName.equalsIgnoreCase("Ultra Low Latency") || ProductName.equalsIgnoreCase("Private Wave Service")
				|| ProductName.equalsIgnoreCase("Dark Fibre")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[112].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Install Time : " + InputData[112].toString());

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period"));
			Clear(getwebelement(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")),
					Integer.toString(rand_int2));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
			Thread.sleep(7000);
			waitforPagetobeenable();
		}
		waitForpageload();
		waitforPagetobeenable();
	}

	public void modTechDarkFibreB(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		String ProductName = InputData[8].toString();
		int rand_int1 = rand.nextInt(1000);
		int rand_int2 = rand.nextInt(1000);
		if (!(ProductName.equalsIgnoreCase("Private Wave Service"))) {
			System.out.print(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteBSelection").replace("Value", InputData[95].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
			// .SiteAInstallationTimePUD(Inputdata);

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period"));
			Clear(getwebelement(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")),
					Integer.toString(rand_int2));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
		}
		if (ProductName.equalsIgnoreCase("Ultra Low Latency") || ProductName.equalsIgnoreCase("Private Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[112].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Install Time : " + InputData[112].toString());

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period"));
			Clear(getwebelement(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")),
					Integer.toString(rand_int2));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
			Thread.sleep(7000);
			waitforPagetobeenable();
		}
	}

	// Aman gupta
	public void modTechPrivateWaveNode(Object[] InputData) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		String ProductName = InputData[8].toString();

		System.out.print(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time"));
		Clickon(getwebelement(xml.getlocator("//locators/R4/SiteADropdownClick").replace("Value", "Install Time")));
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString()));
		Clickon(getwebelement(
				xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[95].toString())));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select Install Time : " + InputData[95].toString());
		// .SiteAInstallationTimePUD(Inputdata);
		int rand_int1 = rand.nextInt(1000);
		WaitforElementtobeclickable(
				xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")));
		SendKeys(
				getwebelement(
						xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Notification Period")),
				Integer.toString(rand_int1));
		ExtentTestManager.getTest().log(LogStatus.PASS,
				" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

		int rand_int2 = rand.nextInt(1000);
		WaitforElementtobeclickable(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window"));
		Clear(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")));
		SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteAInput").replace("Value", "Access Time Window")),
				Integer.toString(rand_int2));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));

		if (ProductName.equalsIgnoreCase("Ultra Low Latency") || ProductName.equalsIgnoreCase("Private Wave Service")) {
			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time"));
			Clickon(getwebelement(xml.getlocator("//locators/R4/SiteBDropdownClick").replace("Value", "Install Time")));
			Clickon(getwebelement(
					xml.getlocator("//locators/R4/SiteABSelection").replace("Value", InputData[112].toString())));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Select Install Time : " + InputData[112].toString());

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period"));
			Clear(getwebelement(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")));
			SendKeys(
					getwebelement(
							xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Notification Period")),
					Integer.toString(rand_int1));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Access Notification Period  : " + Integer.toString(rand_int1));

			WaitforElementtobeclickable(
					xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window"));
			Clear(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")));
			SendKeys(getwebelement(xml.getlocator("//locators/R4/SiteBInput").replace("Value", "Access Time Window")),
					Integer.toString(rand_int2));
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Enter Cabinet ID  : " + Integer.toString(rand_int2));
			Thread.sleep(7000);
			waitforPagetobeenable();
		}
	}

	// Added By Aman Gupta
	public void modTechPrivateWebServiceFibreMiddleApplet(Object[] InputData)
			throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Unprotected"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Unprotected")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Unprotected");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  B End Resilience Option");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Unprotected"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Unprotected")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Unprotected");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	// Added By Aman Gupta
	public void modTechEthernetAccessMiddleApplet(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Single Pair")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Single Pair");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	// Added By Aman Gupta
	public void modTechDarkFibreMiddleApplet(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Diverse Pair"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Diverse Pair")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Dual Diverse Pair");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  B End Resilience Option");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Diverse Pair"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Dual Diverse Pair")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Dual Diverse Pair");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	// By aman Gupta
	public void EnterInstallationChargeInFooters(Object Inputdata[]) throws Exception {
		if (!Inputdata[8].toString().equals("Cloud UC")) {
			Select(getwebelement(xml.getlocator("//locators/InstalltionDropdown")), "Installation and Test");
			ExtentTestManager.getTest().log(LogStatus.PASS,
					" Step: Click on Installation Dropdown button and Select Installation and Test");

			Thread.sleep(5000);
			Clear(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")));
			SendKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), "Not Required");
			SendkeaboardKeys(getwebelement(xml.getlocator("//locators/PrimaryTestingMethod")), Keys.TAB);
			savePage();
			Thread.sleep(3000);
			if (!Inputdata[8].toString().equalsIgnoreCase("Wave")
					&& !Inputdata[8].toString().equalsIgnoreCase("Ethernet Line")) {
				Clickon(getwebelement(xml.getlocator("//locators/SaveOrderContinue")));
				waitforPagetobeenable();
				Thread.sleep(3000);
			}
		}
	}

	// as per Aman Gupta
	public void modHeader(Object[] Inputdata) throws InterruptedException, DocumentException, IOException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(xml.getlocator("//locators/PrimaryLeadInput"));
		Clear(getwebelement(xml.getlocator("//locators/PrimaryLeadInput")));
		SendKeys(getwebelement(xml.getlocator("//locators/PrimaryLeadInput")), "No");
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Enter No  Eexisting primary lead");
		savePage();
		waitForpageload();
		waitforPagetobeenable();
	}

	// As per Aman gupta

	public void modTechultraLowLatencyMiddleApplet(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Service Bandwidth");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "5 Mbps"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "5 Mbps")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  5 Mbps");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "A End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  A End Resilience Option");
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Protected");

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "B End Resilience Option")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  B End Resilience Option");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Protected")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Protected");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
		waitForpageload();
		waitforPagetobeenable();
	}

	// Added By Aman Gupta
	public void modTechPrivateWaveNodeMiddleApplet(Object[] InputData) throws InterruptedException, DocumentException {
		waitForpageload();
		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Network Topology"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Network Topology")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Network Topology");
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/changeMiddleLi").replace("Value", "Any to Any"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/changeMiddleLi").replace("Value", "Any to Any")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Any to Any");
		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Save Button ");
	}

	/*
	 * Created by Aman Gupta
	 */
	public void modTechPrizmNetMiddleApplet(Object[] Inputdata) throws Exception {

		{
			waitForpageload();
			waitforPagetobeenable();
			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/ClickDropdown").replace("Value", "IP Addressing Format")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on dropdown IP Addressing format");

			Clickon(getwebelement(
					xml.getlocator("//locators/IPVPNSite/SelectValueDropdown").replace("Value", "IPv4 and IPv6")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Select value from IP Addressing format dropdown");

			WaitforElementtobeclickable((xml.getlocator("//locators/IPVPNSite/ClickheretoSaveAccess")));
			Clickon(getwebelement(xml.getlocator("//locators/IPVPNSite/ClickheretoSaveAccess")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on save");
			waitForpageload();
			waitforPagetobeenable();

		}
	}

	/* Created by Aman */
	public void modTechSwiftNetMiddleApplet(Object[] Inputdata) throws Exception {

		waitforPagetobeenable();
		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth (Primary)"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Service Bandwidth (Primary)")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Service Bandwidth (Primary)");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "4 Mbps"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "4 Mbps")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  4 Mbps");

		///////////// Routing Sequence

		WaitforElementtobeclickable(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Routing Sequence"));
		Clickon(getwebelement(
				xml.getlocator("//locators/DarkFiber/MiddleDropDown").replace("Value", "Routing Sequence")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Routing Sequence");

		WaitforElementtobeclickable(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Round Robin"));
		Clickon(getwebelement(xml.getlocator("//locators/DarkFiber/MiddleLi").replace("Value", "Round Robin")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Round Robin");
		///////////////////

		WaitforElementtobeclickable(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "Capacity Check Reference"));
		Clickon(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "Capacity Check Reference")));
		Clear(getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "Capacity Check Reference")));
		SendKeys(getwebelement2(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "Capacity Check Reference")), "1");
		SendkeaboardKeys((getwebelement(xml.getlocator("//locators/ManagedDedicatedFirewall/CPECombinationIDGeneric")
				.replace("Value", "Capacity Check Reference"))), Keys.TAB);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on  Service  capacity");

		WaitforElementtobeclickable(xml.getlocator("//locators/SaveButton"));
		Clickon(getwebelement(xml.getlocator("//locators/SaveButton")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step:Click on save button");
		waitForpageload();
		waitforPagetobeenable();

	}

	/* Created by Aman */
	public void modSipTrunkingMiddleapplet(Object[] Inputdata) throws Exception {
		waitforPagetobeenable();
		MiddleAppTextBox("Call Admission Control (Number of Channels)",Integer.toString(GetRandomNumber(20)));
		MiddleAppTextBox("Total Number of DDIs",Integer.toString(GetRandomNumber(20)));
		ClickHereSave();
	}

	/* Created by Aman */

	public void modTechEthernetSpokeMiddleApplet(Object[] Inputdata) throws Exception {
		waitforPagetobeenable();
		Clickon(getwebelement(xmlHns.getlocator("//locators/CustomizeButton")));
		Thread.sleep(30000);
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Customize button");
		Clickon(getwebelement(xml3.getlocator("//locators/CustomizeServiceBandwidth")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Clicked on Service Bandwidth");
		Select(getwebelement(xml3.getlocator("//locators/CustomizeServiceBandwidth")), Inputdata[11].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Picked the updated bandwidth");
		savePage();
		Thread.sleep(5000);

		safeJavaScriptClick(getwebelement(xml3.getlocator("//locators/EthernetConnectionLink")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection link");
		Thread.sleep(10000);
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/SpokeSiteLink")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Spoke Site Link");

		Thread.sleep(10000);
		Clickon(getwebelement(xml3.getlocator("//locators/InstallationTimeLink")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Installation Time link");
		SendKeys(getwebelement(xml3.getlocator("//locators/InstallTime")), Inputdata[95].toString());
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Update the Installation time");
		safeJavaScriptClick(getwebelement(xmlHns.getlocator("//locators/DoneEthernetConnection")));
		ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Ethernet Connection link");
		Thread.sleep(10000);
	}

	/* Created by Aman */
	public void ProductSpecificCompleted(Object[] Inputdata) throws Throwable {
		if (Inputdata[11].toString().equalsIgnoreCase("IP VPN Access")

				|| Inputdata[11].toString().equalsIgnoreCase("IP VPN Plus")
				|| Inputdata[11].toString().equalsIgnoreCase("PrizmNet")
				|| Inputdata[11].toString().equalsIgnoreCase("IP VPN Wholesale")

		) {
			waitforPagetobeenable();
			WaitforElementtobeclickable(xml.getlocator("//locators/OrderStatusDropdown"));
			Clickon(getwebelement(xml.getlocator("//locators/OrderStatusDropdown")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click on Order status drop down");
			Thread.sleep(3000);
			Clickon(getwebelement(xml.getlocator("//locators/SelectCompleted")));
			ExtentTestManager.getTest().log(LogStatus.PASS, " Step: Click Completed Status");
			waitforPagetobeenable();
			Clickon(getwebelement(xml.getlocator("//locators/OrderComplete")));
			if (isElementPresent(xml.getlocator("//locators/AlertAccept"))) {
				System.out.println("");
				System.out.println("Alert Present");
				WaitforElementtobeclickable((xml.getlocator("//locators/AlertAccept")));
				Clickon(getwebelement(xml.getlocator("//locators/AlertAccept")));
			}
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

		}

	}

	public void ModCom(Object[] Inputdata) throws Exception {
		{
			switch (Inputdata[8].toString()) {

			case "Number Hosting": {
				modHeader(Inputdata);
				modTechNumberHosting(Inputdata);
				break;
			}
			case "Interconnect": {
				modHeader(Inputdata);
				modTechNumberHosting(Inputdata);
				break;
			}
			default:
				System.out.println("Product is not selected:" + Inputdata[8].toString());
				break;
			}
		}
	}

}
