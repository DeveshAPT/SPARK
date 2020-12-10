package Reporter;

import java.util.HashMap;
import java.util.Map;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import ScriptHelper.ServiceOrders;



public class ExtentTestManager {
	
	
	static Map extentTestMap = new HashMap();
    static ExtentReports extent = ExtentManager.getReporter();
    //static ServiceOrders orders=new ServiceOrders();
   
    public static synchronized ExtentTest getTest() {
    	
        return (ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId()));
    }
 
    public static synchronized void endTest() 
    {
        extent.endTest((ExtentTest)extentTestMap.get((int) (long) (Thread.currentThread().getId())));
    }
 
    public static synchronized ExtentTest startTest(String testName, String desc) {
    	
    	ExtentTest test = extent.startTest(testName, desc);
        extentTestMap.put((int) (long) (Thread.currentThread().getId()), test);
        return test;
    }
   
   // Report To Excel 	 
 public static String CurrentDate()
	{
    	
    	Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return (formatter.format(date));

	}

    public static void  ReportToExcel(String testName,String result) throws IOException 
    {
		String Stamp=CurrentDate().replace("/", "_");
    	String path = "ExtentReports//ExecutionResult"+Stamp+".xlsx";
		File file = new File(path);
		if (!file.exists()) {
			try {
				file.createNewFile();
				createFile(path);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		AppendData(path,testName, result);
	}

    public static void createFile(String Path) throws IOException 
    {
    	
		FileOutputStream fos = new FileOutputStream(Path);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("ExecutionSummary");
		Row row = sheet.createRow(0);
		Cell cell0 = row.createCell(0);
		cell0.setCellValue("TestJourney");

		Cell cell1 = row.createCell(1);
		cell1.setCellValue("ServiceOrder");
		
		Cell cell2 = row.createCell(2);
		cell2.setCellValue("SubServiceOrder");
		
		Cell cell3 = row.createCell(3);
		cell3.setCellValue("Result");

		Cell cell4 = row.createCell(4);
		cell4.setCellValue("Date");
		
		workbook.write(fos);
		fos.flush();
		fos.close();
	}

    public static void AppendData(String Path,String testName, String result) throws FileNotFoundException {
		try {
			
			FileInputStream fis = new FileInputStream(new File(Path));
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			//String cururl = CurrentUrl();
			//cururl = cururl.substring(0, 20);

			// XSSFSheet sheet = workbook.
			int i = workbook.getNumberOfSheets();
			System.out.println(i);
			XSSFSheet sheet = workbook.getSheetAt(0);
			int num = sheet.getLastRowNum();
			Row row = sheet.createRow(++num);
			
			row.createCell(0).setCellValue(testName);
			row.createCell(1).setCellValue(ServiceOrders.getPrimaryServiceOrder());
			row.createCell(2).setCellValue(ServiceOrders.getSubServiceOrder());
			row.createCell(3).setCellValue(result);
			//row.createCell(1).setCellValue(ServiceOrder.get());
			row.createCell(4).setCellValue(CurrentDate());
		
			fis.close();
			FileOutputStream fos = new FileOutputStream(Path);
			workbook.write(fos);
			fos.close();
		} catch (Exception e) 
		{
			System.out.println(e.getMessage());
		}
	}
    
}
