package Testscript;

import java.util.HashMap;

import org.testng.annotations.Test;
import Driver.DataReader;
import Driver.DriverTestcase;
import ScriptHelper.LoginHelper;

public class NewOrders extends DriverTestcase {

	@Test(dataProviderClass = DataReader.class, dataProvider = "NewOrderOnnet")
	public void EndtoEndOrderOnnet(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		//newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		newOrderOnnnet.get().CompletedValidation(Data);
		newOrderOnnnet.get().WriteServiceOrderNumber(Data);
	}
	
	@Test(dataProviderClass = DataReader.class, dataProvider = "Cease")
	public void Cease(Object[] Data) throws Exception {
		Login.get().Login("Sieble");
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		newOrderOnnnet.get().CompletedValidation(Data);
		newOrderOnnnet.get().WriteServiceOrderNumber(Data);
		Cease.get().openServiceOrder(Data);
		Cease.get().CeaseMainMethod(Data);
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		Cease.get().CeaseCommercialValidation(Data[9].toString());
		Cease.get().DeliveryValidation(Data[9].toString()); // Addedd by Devesh
		Cease.get().CeaseCompletedValidation(); // added By Devesh
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "CeaseExisting")
	public void CeaseExisting(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		Cease.get().openExistingServiceOrder(Data[0].toString());
		Cease.get().CeaseExistingService(Data);
		newOrderOnnnet.get().SelectAttachmentTab(Data[1].toString());
		newOrderOnnnet.get().UploadDocument(Data[1].toString());
		Cease.get().CeaseCommercialValidation(Data[1].toString());
		Cease.get().DeliveryValidation(Data[1].toString());
		newOrderOnnnet.get().clickOnManualValidationA();
		Cease.get().CeaseCompletedValidation();
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "Mode")
	public void Mod(Object[] Data) throws Throwable 
	{
		String Product = Data[9].toString().trim();
		String SubProduct = Data[10].toString().trim();
		Login.get().Login("Sieble");
		//System.out.println(SubProduct);
		if (Data[0].toString().equalsIgnoreCase("Yes")) 
		{
			newOrderOnnnet.get().accountTabDetails(Data);
			newOrderOnnnet.get().createCustomerOrder(Data);
			newOrderOnnnet.get().productSelectionHelper(Data);
			newOrderOnnnet.get().openServiceOrderNumber();
			newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
			newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().VoiceConfigTab(Data);
			newOrderOnnnet.get().VoiceFeatureTab(Data);
			newOrderOnnnet.get().NumberManagementTab(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().SelectServiceGroupTab(Data);
			newOrderOnnnet.get().OperationAttribute(Data);
			newOrderOnnnet.get().MandatoryFields(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			newOrderOnnnet.get().getReferenceNo(Data);// added new
			newOrderOnnnet.get().CompletedValidation(Data);
			newOrderOnnnet.get().WriteServiceOrderNumber(Data);
		}
		if (Data[Data.length - 1].toString().contains("Tech")) 
		{
			newOrderOnnnet.get().ServiceTab(Data);
			modHelper.get().ModTech(Data);
			newOrderOnnnet.get().ModTechModCommWaveAndLine(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			
			newOrderOnnnet.get().TechnicalValidation(Data); // updated
			modHelper.get().LeadCapacity(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			modHelper.get().ProductSpecificCompleted(Data);
			if (!(SubProduct.equalsIgnoreCase("IP VPN Access"))&& !(SubProduct.equalsIgnoreCase("IP VPN Plus"))
					&& !(SubProduct.equalsIgnoreCase("IP VPN Wholesale"))&& !(SubProduct.equalsIgnoreCase("PrizmNet"))) 
			{
				newOrderOnnnet.get().CompletedValidation(Data);
			}
		}
		if (Data[Data.length - 1].toString().contains("Com")) 
		{
			newOrderOnnnet.get().ModComExisting(Data);
			newOrderOnnnet.get().installationTimeUpdate(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			if (Product.equalsIgnoreCase("Ethernet Spoke")) 
			{
				newOrderOnnnet.get().ColtPromissDate(Data);
			}
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			if (SubProduct.equalsIgnoreCase("IP VPN Access") || SubProduct.equalsIgnoreCase("IP VPN Plus")|| SubProduct.equalsIgnoreCase("IP VPN Wholesale") || SubProduct.equalsIgnoreCase("PrizmNet")) 
			{
				newOrderOnnnet.get().ServiceChargeforIPVPNSite(Data, "4");
			}
			newOrderOnnnet.get().CommercialValidation(Data);
			modHelper.get().ProductSpecificCompleted(Data);
			if (!SubProduct.equalsIgnoreCase("IP VPN Access") && !SubProduct.equalsIgnoreCase("IP VPN Plus")&& !SubProduct.equalsIgnoreCase("IP VPN Wholesale") && !SubProduct.equalsIgnoreCase("PrizmNet")) {
				newOrderOnnnet.get().CompletedValidation(Data);
			}
		}
		if (Data[Data.length - 1].toString().contains("Carnor")) 
		{
			
			newOrderOnnnet.get().ServiceTab(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().installationTimeUpdate(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			if (SubProduct.equalsIgnoreCase("IP VPN Service")) 
			{
				newOrderOnnnet.get().ServiceChargeforIPVPNSite(Data, "4");
			}
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().Carnor(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().Carnor_SelectServiceGroupTab(Data);
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			newOrderOnnnet.get().Carnor_getReferenceNo(Data);
			newOrderOnnnet.get().OperationAttribute_Carnor(Data);
			//newOrderOnnnet.get().CarnorCompletedValidation(Data);
			
		}
	}
		
	@Test(dataProviderClass = DataReader.class, dataProvider = "Abandoned")
	public void Abandoned(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// Login.get().VerifySuccessLogin("Sieble");
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		if (Data[9].toString().equalsIgnoreCase("IP VPN Service")) {
			newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
			newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			newOrderOnnnet.get().AbandonedforIPVPN(Data); // No Change
			newOrderOnnnet.get().CommercialValidation(Data);
			Abandoned.get().statusReason(Data);
			Abandoned.get().AbandonedOrder(Data);
			Abandoned.get().verifyOrderAbandonedforIPVPN();
		} else {
			newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);

			newOrderOnnnet.get().MandatoryFields(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			Abandoned.get().statusReason(Data);
			Abandoned.get().AbandonedOrder(Data);
			Abandoned.get().verifyOrderAbandoned();

		}
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "OmpDatereader")
	public void OMPGenric(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		newOrderOnnnet.get().CompletedValidation(Data);
		newOrderOnnnet.get().WriteServiceOrderNumber(Data);
		Login.get().Login("OMP");
		OmpOrder.get().verficationOfProduct(Data);

	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "OmpDatereaderMod")
	public void OMPMod(Object[] Data) throws Throwable 
	{
		String Product = Data[9].toString().trim();
		String SubProduct = Data[10].toString().trim();
		Login.get().Login("Sieble");
		if (Data[0].toString().equalsIgnoreCase("Yes"))
		{
			newOrderOnnnet.get().accountTabDetails(Data);
			newOrderOnnnet.get().createCustomerOrder(Data);
			newOrderOnnnet.get().productSelectionHelper(Data);
			newOrderOnnnet.get().openServiceOrderNumber();
			newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
			newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().VoiceConfigTab(Data);
			newOrderOnnnet.get().VoiceFeatureTab(Data);
			newOrderOnnnet.get().NumberManagementTab(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().SelectServiceGroupTab(Data);
			newOrderOnnnet.get().OperationAttribute(Data);
			newOrderOnnnet.get().MandatoryFields(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			newOrderOnnnet.get().getReferenceNo(Data);// added new
			newOrderOnnnet.get().CompletedValidation(Data);
			newOrderOnnnet.get().WriteServiceOrderNumber(Data);
		}
		if (Data[Data.length - 1].toString().contains("Tech")) 
		{
			newOrderOnnnet.get().ServiceTab(Data);
			modHelper.get().ModTech(Data);
			newOrderOnnnet.get().ModTechModCommWaveAndLine(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data); // updated
			modHelper.get().LeadCapacity(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			modHelper.get().ProductSpecificCompleted(Data);
			if (!(SubProduct.equalsIgnoreCase("IP VPN Access"))&& !(SubProduct.equalsIgnoreCase("IP VPN Plus"))
					&& !(SubProduct.equalsIgnoreCase("IP VPN Wholesale"))&& !(SubProduct.equalsIgnoreCase("PrizmNet"))) 
			{
				newOrderOnnnet.get().CompletedValidation(Data);
			}
		}
		if (Data[Data.length - 1].toString().contains("Com")) 
		{
			newOrderOnnnet.get().ModComExisting(Data);
			newOrderOnnnet.get().installationTimeUpdate(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			if (Product.equalsIgnoreCase("Ethernet Spoke")) 
			{
				newOrderOnnnet.get().ColtPromissDate(Data);
			}
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			if (SubProduct.equalsIgnoreCase("IP VPN Access") || SubProduct.equalsIgnoreCase("IP VPN Plus")|| SubProduct.equalsIgnoreCase("IP VPN Wholesale") || SubProduct.equalsIgnoreCase("PrizmNet")) 
			{
				newOrderOnnnet.get().ServiceChargeforIPVPNSite(Data, "4");
			}
			newOrderOnnnet.get().CommercialValidation(Data);
			modHelper.get().ProductSpecificCompleted(Data);
			if (!SubProduct.equalsIgnoreCase("IP VPN Access") && !SubProduct.equalsIgnoreCase("IP VPN Plus")&& !SubProduct.equalsIgnoreCase("IP VPN Wholesale") && !SubProduct.equalsIgnoreCase("PrizmNet")) {
				newOrderOnnnet.get().CompletedValidation(Data);
			}
		}
		if (Data[Data.length - 1].toString().contains("Carnor")) 
		{
			newOrderOnnnet.get().ServiceTab(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().installationTimeUpdate(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			if (SubProduct.equalsIgnoreCase("IP VPN Service")) 
			{
				newOrderOnnnet.get().ServiceChargeforIPVPNSite(Data, "4");
			}
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "4");
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().Carnor(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().Carnor_SelectServiceGroupTab(Data);
			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			newOrderOnnnet.get().Carnor_getReferenceNo(Data);
			newOrderOnnnet.get().OperationAttribute_Carnor(Data);
			//newOrderOnnnet.get().CarnorCompletedValidation(Data);
		}
		Login.get().Login("OMP");
		OmpMOdOrder.get().verficationOfProduct(Data);
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "PreMaster")
	public void PremiseMaster(Object[] Data) throws Exception {
		HashMap<String, String> SiteDetails = new HashMap<String, String>();
		String SiteId = null;
		String BuildingID = null;
		String NewSiteId = null;
		String UpdateSiteID = null;
		String ProductName = Data[9].toString();
		Login.get().Login("Sieble");
		String CurrentUrl = Login.get().GetCurrentUrl();
		// newOrderOnnnet.get().OpenServiceOrder(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		System.out.println("New Code");
		premiseHelper.get().ClickSitesSearch(Data);
		SiteDetails = premiseHelper.get().AddSiteAndBuilding(Data);
		newOrderOnnnet.get().savePage();
		SiteId = premiseHelper.get().SiteidReference(Data);
		newOrderOnnnet.get().ClickHereSave();
		premiseHelper.get().PremiseMasterAppOpen();
		Login.get().Login("PremiseMaster");
		premiseHelper.get().ClickIcon();
		BuildingID = premiseHelper.get().SearchSiteAndVerified(SiteId);
		SiteDetails.put("BuildingID", BuildingID);
		premiseHelper.get().SearchBuildingAndEdit(BuildingID, Data);
		premiseHelper.get().SearchSiteAndEdit(SiteId, Data);
		UpdateSiteID = premiseHelper.get().GetUpdateSiteId(BuildingID);
		SiteDetails.put("UpdateSiteID", UpdateSiteID);
		premiseHelper.get().PremiseMasterAppClosed();
		Login.get().NavigateUrl(CurrentUrl);
		newOrderOnnnet.get().savePage();
		premiseHelper.get().ClickSitesSearch(Data);
		premiseHelper.get().BuildingVerificationInSiebel(SiteDetails, Data);
		premiseHelper.get().SiteVerifcationInSibel(Data, UpdateSiteID);
		newOrderOnnnet.get().savePage();
		NewSiteId = premiseHelper.get().SiteidReference(Data);
		premiseHelper.get().SiteUpdateVerification(UpdateSiteID, NewSiteId);
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "InFlightOrder")
	public void InflightMod(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// Login.get().VerifySuccessLogin("Sieble");
		// newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		// newOrderOnnnet.get().clickOnManualValidationA();
		inFlightGeneric.get().ServiceTabInFlight(Data);
		// Code for InflightMod

	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "PartialDelivery")
	public void PartialDelivery(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		if (!Data[8].toString().equalsIgnoreCase("IP Access")) {
			
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
		}
		newOrderOnnnet.get().getReferenceNo(Data);// added new
		newOrderOnnnet.get().PartialCompletedValidation(Data);
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "NewOrderOffnet")
	public void EndtoEndOrderOffnet(Object[] Data) throws Exception {
		Login.get().Login("Sieble");
		//newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		if (!Data[9].toString().equalsIgnoreCase("IP VPN Service")) 
		{
			if (Data[32].toString().equalsIgnoreCase("Offnet")) 
			{
				newOrderOnnnet.get().CEOS_Offnet();
				newOrderOnnnet.get().LaunchingCEOSApplication(Data);
				//newOrderOnnnet.get().getReferenceNo(Data);// added new
				newOrderOnnnet.get().CompletedValidation(Data);
			}
		} 
		else 
		{
			newOrderOnnnet.get().CompletedValidation(Data);
		}
		//newOrderOnnnet.get().getReferenceNo(Data);// added new
		newOrderOnnnet.get().CompletedValidation(Data);
		newOrderOnnnet.get().validateSlaMatrix(Data);
	}

	// As Add By Ayush
	@Test(dataProviderClass = DataReader.class, dataProvider = "XNGData")
	public void XNGGeneric(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();

		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		newOrderOnnnet.get().CompletedValidation(Data);
		// xng Code
		newOrderOnnnet.get().LaunchingXNGApplication(Data);

	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "Cancelled")
	public void Cancelled(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		//newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationA();
		if (!Data[9].toString().equalsIgnoreCase("IP VPN Service")) 
		{
			Cancelled.get().statusReason(Data);
			Cancelled.get().CancelOrder(Data);
			Cancelled.get().verifyOrderCancelled();
		} 
		else {
			newOrderOnnnet.get().CompletedValidationforCancel(Data);
			Cancelled.get().statusReason(Data);
			Cancelled.get().CancelOrder(Data);
			Cancelled.get().verifyOrderCancelled();
		}
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "Xtrac")
	public void SiebleToXtrac(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// newOrderOnnnet.get().Check(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
		newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
		newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
		newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
		newOrderOnnnet.get().VoiceConfigTab(Data);
		newOrderOnnnet.get().VoiceFeatureTab(Data);
		newOrderOnnnet.get().NumberManagementTab(Data);
		newOrderOnnnet.get().EnterDateInFooter(Data);
		newOrderOnnnet.get().EnterBillingDateInFooter(Data);
		newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");
		newOrderOnnnet.get().SelectAttachmentTab(Data);
		newOrderOnnnet.get().UploadDocument(Data);
		newOrderOnnnet.get().SelectServiceGroupTab(Data);
		newOrderOnnnet.get().OperationAttribute(Data);
		newOrderOnnnet.get().MandatoryFields(Data);
		newOrderOnnnet.get().CommercialValidation(Data);
		newOrderOnnnet.get().validateXtrac();
		newOrderOnnnet.get().TechnicalValidation(Data);
		newOrderOnnnet.get().clickOnManualValidationB(Data);
		newOrderOnnnet.get().getReferenceNo(Data);
		newOrderOnnnet.get().InstallationTest();
		newOrderOnnnet.get().DeliveryValidation(Data);
		newOrderOnnnet.get().openServiceOrder(Data);
		newOrderOnnnet.get().validateXtrac();
		newOrderOnnnet.get().clickOnManualValidationA();
		newOrderOnnnet.get().getReferenceNo(Data);// added new
		newOrderOnnnet.get().CompletedValidation(Data);
		newOrderOnnnet.get().openServiceOrder(Data);
		newOrderOnnnet.get().validateXtracComplete();
	}

	@Test(dataProviderClass = DataReader.class, dataProvider = "SiebelSAPData")
	public void SiebelToSap(Object[] Data) throws Exception {

		Login.get().Login("Sieble");
		// newOrderOnnnet.get().OpenServiceOrder(Data);
		newOrderOnnnet.get().accountTabDetails(Data);
		newOrderOnnnet.get().createCustomerOrder(Data);
		newOrderOnnnet.get().productSelectionHelper(Data);
		newOrderOnnnet.get().openServiceOrderNumber();
			newOrderOnnnet.get().enterMandatoryFieldsInHeader(Data);
			newOrderOnnnet.get().enterMandatoryDetailsInMiddleApplet(Data);
			newOrderOnnnet.get().MiddleAppOperationAttributes(Data[9].toString());
			newOrderOnnnet.get().VoiceConfigTab(Data);
			newOrderOnnnet.get().VoiceFeatureTab(Data);
			newOrderOnnnet.get().NumberManagementTab(Data);
			newOrderOnnnet.get().EnterDateInFooter(Data);
			newOrderOnnnet.get().EnterBillingDateInFooter(Data);
			newOrderOnnnet.get().EnterServiceChargeInFooter(Data, "2");

			newOrderOnnnet.get().SelectAttachmentTab(Data);
			newOrderOnnnet.get().UploadDocument(Data);
			newOrderOnnnet.get().SelectServiceGroupTab(Data);
			newOrderOnnnet.get().OperationAttribute(Data);
		
			newOrderOnnnet.get().MandatoryFields(Data);
			newOrderOnnnet.get().CommercialValidation(Data);
			newOrderOnnnet.get().TechnicalValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationB(Data);
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().InstallationTest();
			newOrderOnnnet.get().DeliveryValidation(Data);
			newOrderOnnnet.get().clickOnManualValidationA();
			if (Data[74].toString().equals("Offnet")) {
				newOrderOnnnet.get().CEOS_Offnet();
				newOrderOnnnet.get().LaunchingCEOSApplication(Data);
			}
			newOrderOnnnet.get().getReferenceNo(Data);
			newOrderOnnnet.get().CompletedValidation(Data);
			HashMap<Integer, HashMap<String, String>> Detail = newOrderOnnnet.get().EROData();
			newOrderOnnnet.get().WriteServiceOrderNumber(Data);
		
	}

}