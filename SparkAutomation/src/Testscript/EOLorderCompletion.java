package Testscript;


import org.testng.annotations.Test;
import Driver.DataReader;

import Driver.DriverTestcase;
import ScriptHelper.EOLorderCompletionHelper;
import ScriptHelper.LoginHelper;


public class EOLorderCompletion extends DriverTestcase {
	
	@Test(dataProviderClass=DataReader.class,dataProvider="EOLorderCompletion")
	public void createNewEOLOrder(Object[] Data) throws Exception
	{
		
		Login.get().LoginEOL("EOL");
		
		EOLorderCompletionHelper.get().switchToSalesuserType();
		
		if(Data[10].toString().equalsIgnoreCase("Colt Managed Virtual Firewall") || Data[10].toString().equalsIgnoreCase("Colt Managed Dedicated Firewall") )
		{
	    EOLorderCompletionHelper.get().coltManagedFirewall(Data);
		}
	       else if(Data[10].toString().equalsIgnoreCase("Colt Ethernet VPN"))
	      {
		   EOLorderCompletionHelper.get().ethernetVPN(Data);
	      }
	      else if(Data[10].toString().equalsIgnoreCase("Colt Ethernet Line"))
	      {
		   EOLorderCompletionHelper.get().ethernetLine(Data);
	      }
	      else if(Data[10].toString().equalsIgnoreCase("Colt Ethernet Hub"))
	      {
		 EOLorderCompletionHelper.get().ethernetHub(Data);
	      }
		  else if(Data[10].toString().equalsIgnoreCase("Colt Wave"))
		  {
			EOLorderCompletionHelper.get().ethernetWave(Data);
		  }
		  else if(Data[10].toString().equalsIgnoreCase("Ethernet Hub"))
		   {
			EOLorderCompletionHelper.get().ethernetWave(Data);
		   }
		  else if(Data[10].toString().equalsIgnoreCase("Colt Ethernet Spoke"))
		  {
			EOLorderCompletionHelper.get().ethernetSpoke(Data);
		  }
		  else if(Data[10].toString().equalsIgnoreCase("Colt IP Domain") || (Data[10].toString().equalsIgnoreCase("Colt IP Guardian")))
		 {
			EOLorderCompletionHelper.get().ipDomain(Data);
		 }
		  else
		 {
		 EOLorderCompletionHelper.get().createNewEOLOrder(Data);
		 }
		//Login.get().Login("Sieble");
		//EOLorderCompletionHelper.get().serviceTab();
	}
	
	@Test(dataProviderClass=DataReader.class,dataProvider="ModComEOLCompletion")
		public void eolModcomOfAllProducts(Object[] Data) throws Exception
		{
        Login.get().LoginEOL("EOL");
		EOLorderCompletionHelper.get().switchToSalesuserType();
		EOLorderCompletionHelper.get().modComOrderEOL(Data);
		//Login.get().Login("Sieble");
		//EOLorderCompletionHelper.get().serviceTab();

		}
	
	@Test(dataProviderClass=DataReader.class,dataProvider="ModTechEOLCompletion")
	public void eolModtechOfAllProducts(Object[] Data) throws Exception
	{
    Login.get().LoginEOL("EOL");
	EOLorderCompletionHelper.get().switchToSalesuserType();
	EOLorderCompletionHelper.get().eolMOdTech(Data);
	//Login.get().Login("Sieble");
	//EOLorderCompletionHelper.get().serviceTab();

	}
			
	}
			
		
