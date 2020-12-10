package Driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.DataProvider;

public class DataReader 
{
	
	@DataProvider(name="NewOrderOnnet", parallel=true)
	public static Object[][] NewOrder() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("New");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++)
		 {
			 XSSFRow counter=sheet.getRow(k);
			 if(counter.getCell(1).toString().equals("Yes"))
			 {
				 totalrowrequired=totalrowrequired+1;
			 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes"))
		{
		   for(int j=3;j<=r1.getLastCellNum();j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
				   }
				   else 
				   {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   /*System.out.println(r.getCell(0).toString());
		   System.out.println(r.getCell(1).toString());
		   System.out.println(r.getCell(2).toString());
		   data[r1.getLastCellNum()-3]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-2]=r.getCell(1).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(2).toString();*/
		   System.out.println(r.getCell(0).toString());
		   System.out.println(r.getCell(1).toString());
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="Abandoned", parallel=true)
	public static Object[][] Abandoned() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Abandoned");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="Cancelled", parallel=true)
	public static Object[][] Cancelled() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Cancel");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="InFlightOrder", parallel=true)
	public static Object[][] InFlightreader() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Inflight");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="OmpDatereader", parallel=true)
	public static Object[][] OmpDatereader() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("NewOMP");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="OmpDatereaderMod", parallel=true)
	public static Object[][] OmpDatereaderMod() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("ModOMP");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="Cease", parallel=true)
	public static Object[][] Cease() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Cease");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="CeaseExisting", parallel=true)
	public static Object[][] CeaseExisting() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("CeaseExistingOders");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="ModComExisting", parallel=true)
	public static Object[][] ModComExisting() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("ModComExistingOrders");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++)
		 {
			 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   System.out.println(r.getCell(0).toString());
		   System.out.println(r.getCell(1).toString());
		   System.out.println(r.getCell(2).toString());
		   data[r1.getLastCellNum()-3]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-2]=r.getCell(1).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(2).toString();
		  
		   System.out.print(r.getCell(2).toString());
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="Mode", parallel=true)
	public static Object[][] Mode() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Mod");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(2).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   System.out.println("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   System.out.println("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="EOLorderCompletion", parallel=false)
	public static Object[][] EOL() throws IOException
			{
			
				FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
				//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
				 XSSFWorkbook workbook = new XSSFWorkbook(file);
				 XSSFSheet sheet = workbook.getSheet("NewInputData");
				 XSSFRow r1=sheet.getRow(0);
				 XSSFRow r;
				 int rowindex=0;
				 int totalrowrequired=0;
				 for(int k=1;k<=sheet.getLastRowNum();k++){
				 XSSFRow counter=sheet.getRow(k);
				 if(counter.getCell(1).toString().equals("Yes"))
				 {
					 totalrowrequired=totalrowrequired+1;
				
				 }
				 
				 }
				 Log.info("Total Data Set for EOL Order will be"+totalrowrequired);
				 Object[][] listOfLists = new Object[totalrowrequired][1];
				//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
				 //Log.info("Total number of Columns" +r.getLastCellNum());
				 //Log.info("Total number of Columns" +sheet.getLastRowNum());
				 for(int i=2;i<=sheet.getLastRowNum();i++)
				 { 
					 r=sheet.getRow(i);
					 Object[]  data=new Object[r1.getLastCellNum()];
					 //Log.info(r.getLastCellNum());
				if(r.getCell(1).toString().equals("Yes")){
				   for(int j=3;j<=r1.getLastCellNum()-1;j++)
					 {
					   String strCellValue;
					   try {
						   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
						   {
							   int value=(int)r.getCell(j).getNumericCellValue();
							   strCellValue=String.valueOf(value);
							   Log.info("The Value is in Int Format and Value is:"+strCellValue);
							   
						   }
						   
						   else {
							   strCellValue=r.getCell(j).toString();
							   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
						   }
						   }
						   catch(java.lang.NullPointerException e)
						   {
							   strCellValue="";
							   Log.info("The Value of this cell is: "+strCellValue);
						   }
					   data[j-3] = strCellValue;
					  
					    //data[i-1][j] = ;
					 
					 }
				   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
				   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
				   listOfLists[rowindex][0]=data;
				   rowindex=rowindex+1;
				 }
				 //Log.info(data.toString());
				 workbook.close();
				 }
				 return listOfLists;
				 
			}
	
	@DataProvider(name="ModComEOLCompletion", parallel=false)
	public static Object[][] modComEOL() throws IOException
			{
			
				FileInputStream file = new FileInputStream(new File("src//Data//EOLorder.xlsx"));
				//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
				 XSSFWorkbook workbook = new XSSFWorkbook(file);
				 XSSFSheet sheet = workbook.getSheet("EOL-ModCom");
				 XSSFRow r1=sheet.getRow(0);
				 XSSFRow r;
				 int rowindex=0;
				 int totalrowrequired=0;
				 for(int k=1;k<=sheet.getLastRowNum();k++){
				 XSSFRow counter=sheet.getRow(k);
				 if(counter.getCell(1).toString().equals("Yes"))
				 {
					 totalrowrequired=totalrowrequired+1;
				
				 }
				 
				 }
				 Log.info("Total Data Set for EOL Order will be"+totalrowrequired);
				 Object[][] listOfLists = new Object[totalrowrequired][1];
				//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
				 //Log.info("Total number of Columns" +r.getLastCellNum());
				 //Log.info("Total number of Columns" +sheet.getLastRowNum());
				 for(int i=2;i<=sheet.getLastRowNum();i++)
				 { 
					 r=sheet.getRow(i);
					 Object[]  data=new Object[r1.getLastCellNum()];
					 //Log.info(r.getLastCellNum());
				if(r.getCell(1).toString().equals("Yes")){
				   for(int j=3;j<=r1.getLastCellNum()-1;j++)
					 {
					   String strCellValue;
					   try {
						   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
						   {
							   int value=(int)r.getCell(j).getNumericCellValue();
							   strCellValue=String.valueOf(value);
							   Log.info("The Value is in Int Format and Value is:"+strCellValue);
							   
						   }
						   
						   else {
							   strCellValue=r.getCell(j).toString();
							   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
						   }
						   }
						   catch(java.lang.NullPointerException e)
						   {
							   strCellValue="";
							   Log.info("The Value of this cell is: "+strCellValue);
						   }
					   data[j-3] = strCellValue;
					  
					    //data[i-1][j] = ;
					 
					 }
				   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
				   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
				   listOfLists[rowindex][0]=data;
				   rowindex=rowindex+1;
				 }
				 //Log.info(data.toString());
				 workbook.close();
				 }
				 return listOfLists;
				 
			}
	
	@DataProvider(name="ModTechEOLCompletion", parallel=false)
	public static Object[][] modTechEOL() throws IOException
			{
			
				FileInputStream file = new FileInputStream(new File("src//Data//EOLorder.xlsx"));
				//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
				 XSSFWorkbook workbook = new XSSFWorkbook(file);
				 XSSFSheet sheet = workbook.getSheet("EOL-ModTech");
				 XSSFRow r1=sheet.getRow(0);
				 XSSFRow r;
				 int rowindex=0;
				 int totalrowrequired=0;
				 for(int k=1;k<=sheet.getLastRowNum();k++){
				 XSSFRow counter=sheet.getRow(k);
				 if(counter.getCell(1).toString().equals("Yes"))
				 {
					 totalrowrequired=totalrowrequired+1;
				
				 }
				 
				 }
				 Log.info("Total Data Set for EOL Order will be"+totalrowrequired);
				 Object[][] listOfLists = new Object[totalrowrequired][1];
				//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
				 //Log.info("Total number of Columns" +r.getLastCellNum());
				 //Log.info("Total number of Columns" +sheet.getLastRowNum());
				 for(int i=2;i<=sheet.getLastRowNum();i++)
				 { 
					 r=sheet.getRow(i);
					 Object[]  data=new Object[r1.getLastCellNum()];
					 //Log.info(r.getLastCellNum());
				if(r.getCell(1).toString().equals("Yes")){
				   for(int j=3;j<=r1.getLastCellNum()-1;j++)
					 {
					   String strCellValue;
					   try {
						   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
						   {
							   int value=(int)r.getCell(j).getNumericCellValue();
							   strCellValue=String.valueOf(value);
							   Log.info("The Value is in Int Format and Value is:"+strCellValue);
							   
						   }
						   
						   else {
							   strCellValue=r.getCell(j).toString();
							   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
						   }
						   }
						   catch(java.lang.NullPointerException e)
						   {
							   strCellValue="";
							   Log.info("The Value of this cell is: "+strCellValue);
						   }
					   data[j-3] = strCellValue;
					  
					    //data[i-1][j] = ;
					 
					 }
				   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
				   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
				   listOfLists[rowindex][0]=data;
				   rowindex=rowindex+1;
				 }
				 //Log.info(data.toString());
				 workbook.close();
				 }
				 return listOfLists;
				 
			}
	
	@DataProvider(name="PreMaster", parallel=true)
	public static Object[][] PreMastReader() throws IOException
			{
			
				FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
				//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
				 XSSFWorkbook workbook = new XSSFWorkbook(file);
				 XSSFSheet sheet = workbook.getSheet("PremiseMaster");
				 XSSFRow r1=sheet.getRow(0);
				 XSSFRow r;
				 int rowindex=0;
				 int totalrowrequired=0;
				 for(int k=1;k<=sheet.getLastRowNum();k++){
				 XSSFRow counter=sheet.getRow(k);
				 if(counter.getCell(1).toString().equals("Yes"))
				 {
					 totalrowrequired=totalrowrequired+1;
				
				 }
				 
				 }
				 Log.info("Total Data Set for EOL Order will be"+totalrowrequired);
				 Object[][] listOfLists = new Object[totalrowrequired][1];
				//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
				 //Log.info("Total number of Columns" +r.getLastCellNum());
				 //Log.info("Total number of Columns" +sheet.getLastRowNum());
				 for(int i=2;i<=sheet.getLastRowNum();i++)
				 { 
					 r=sheet.getRow(i);
					 Object[]  data=new Object[r1.getLastCellNum()];
					 //Log.info(r.getLastCellNum());
				if(r.getCell(1).toString().equals("Yes")){
				   for(int j=3;j<=r1.getLastCellNum()-1;j++)
					 {
					   String strCellValue;
					   try {
						   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
						   {
							   int value=(int)r.getCell(j).getNumericCellValue();
							   strCellValue=String.valueOf(value);
							   Log.info("The Value is in Int Format and Value is:"+strCellValue);
							   
						   }
						   
						   else {
							   strCellValue=r.getCell(j).toString();
							   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
						   }
						   }
						   catch(java.lang.NullPointerException e)
						   {
							   strCellValue="";
							   Log.info("The Value of this cell is: "+strCellValue);
						   }
					   data[j-3] = strCellValue;
					  
					    //data[i-1][j] = ;
					 
					 }
				   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
				   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
				   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
				   listOfLists[rowindex][0]=data;
				   rowindex=rowindex+1;
				 }
				 //Log.info(data.toString());
				 workbook.close();
				 }
				 return listOfLists;
				 
			}
	
	@DataProvider(name="NewOrderOffnet", parallel=true)
	public static Object[][] NewOrderOffnet() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Newoffnet");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="PartialDelivery", parallel=true)
	public static Object[][] PartialDelivery() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("PartialDelivery");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	@DataProvider(name="Xtrac", parallel=true)
	public static Object[][] SiebelXtrac() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("Xtrac");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Xtrac will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	public static Object[][] XNGData() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//NewInputData.xlsx"));
		//FileInputStream file = new FileInputStream(new File("C:\\Users\\ASharma19-adm\\git\\Spark\\SparkAutomation\\src\\Data\\EtherNetP2PNewOrder.xlsx"));
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("XNG");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++)
		 {
			 XSSFRow counter=sheet.getRow(k);
			 if(counter.getCell(1).toString().equals("Yes"))
			 {
				 totalrowrequired=totalrowrequired+1;
			 }
		 
		 }
		 Log.info("Total Data Set for Ethernet P2P will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes"))
		{
		   for(int j=3;j<=r1.getLastCellNum();j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
				   }
				   else 
				   {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   /*System.out.println(r.getCell(0).toString());
		   System.out.println(r.getCell(1).toString());
		   System.out.println(r.getCell(2).toString());
		   data[r1.getLastCellNum()-3]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-2]=r.getCell(1).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(2).toString();*/
		   System.out.println(r.getCell(0).toString());
		   System.out.println(r.getCell(1).toString());
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
	
	
	@DataProvider(name="SiebelSAPData", parallel=true)
	public static Object[][] SiebelSAP() throws IOException
	{
	
		FileInputStream file = new FileInputStream(new File("src//Data//InputData.xlsx"));
		
		 XSSFWorkbook workbook = new XSSFWorkbook(file);
		 XSSFSheet sheet = workbook.getSheet("ERO");
		 XSSFRow r1=sheet.getRow(0);
		 XSSFRow r;
		 int rowindex=0;
		 int totalrowrequired=0;
		 for(int k=1;k<=sheet.getLastRowNum();k++){
		 XSSFRow counter=sheet.getRow(k);
		 if(counter.getCell(1).toString().equals("Yes"))
		 {
			 totalrowrequired=totalrowrequired+1;
		
		 }
		 
		 }
		 Log.info("Total Data Set for Xtrac will be"+totalrowrequired);
		 Object[][] listOfLists = new Object[totalrowrequired][1];
		//Object[][] data= new Object[sheet.getLastRowNum()][r.getLastCellNum()];  
		 //Log.info("Total number of Columns" +r.getLastCellNum());
		 //Log.info("Total number of Columns" +sheet.getLastRowNum());
		 for(int i=2;i<=sheet.getLastRowNum();i++)
		 { 
			 r=sheet.getRow(i);
			 Object[]  data=new Object[r1.getLastCellNum()];
			 //Log.info(r.getLastCellNum());
		if(r.getCell(1).toString().equals("Yes")){
		   for(int j=3;j<=r1.getLastCellNum()-1;j++)
			 {
			   String strCellValue;
			   try {
				   if(r.getCell(j).getCellType()==Cell.CELL_TYPE_NUMERIC)
				   {
					   int value=(int)r.getCell(j).getNumericCellValue();
					   strCellValue=String.valueOf(value);
					   Log.info("The Value is in Int Format and Value is:"+strCellValue);
					   
				   }
				   
				   else {
					   strCellValue=r.getCell(j).toString();
					   Log.info("The Value of this cell is in String Format and Value is : "+strCellValue);
				   }
				   }
				   catch(java.lang.NullPointerException e)
				   {
					   strCellValue="";
					   Log.info("The Value of this cell is: "+strCellValue);
				   }
			   data[j-3] = strCellValue;
			  
			    //data[i-1][j] = ;
			 
			 }
		   data[r1.getLastCellNum()-2]=r.getCell(0).toString();
		   data[r1.getLastCellNum()-1]=r.getCell(1).toString();
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-2]);
		   Log.info("The Value of this cell is: "+data[r1.getLastCellNum()-1]);
		   listOfLists[rowindex][0]=data;
		   rowindex=rowindex+1;
		 }
		 //Log.info(data.toString());
		 workbook.close();
		 }
		 return listOfLists;
		 
	}
		
	public static void main(String []args) throws Exception {
		Object[][] finaldata=Mode();
		 System.out.println("Number of Quote"+finaldata.length);
		 for(int i=0;i<=finaldata.length-1;i++)
		 {
			 Object[] data2= (Object[]) finaldata[i][0];
			 System.out.println("number of product in Quote:" +i+"***"+data2.length);
			 System.out.println("number of product in Quote:" +i+"***"+data2[182].toString());
				
			 for(int j=0;j<=data2.length-1;j++) {
		//Object[] data3=(Object[]) data2[0];
				// Object[] data4=(Object[]) data2[j];
		// System.out.println("Length of data"+data3.length);
		// System.out.println("Number of congiguration Attribute for Fist Product"+data3.length);
		// System.out.println("Number of congiguration Attribute for Second Product"+data4.length);
		// System.out.println("Product name for First Product"+data3[2].toString());
				 //System.out.println("Product name for Second Product"+data4[2].toString());	
		}
		 }
}
}