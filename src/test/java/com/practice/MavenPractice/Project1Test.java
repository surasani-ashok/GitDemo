package com.practice.MavenPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Project1Test {
	
	public String Url = "https://www.godaddy.com/";
	public ExtentReports extent;
	private static Logger log = LogManager.getLogger(Project1Test.class.getName());
	
	@BeforeTest
	public void config() {
		
		log.error("Before Class test Started");
		
		String path = System.getProperty("user.dir")+"\\reports\\index.html";
		ExtentSparkReporter reporter = new ExtentSparkReporter(path);
		reporter.config().setReportName("WebAutomationResults");
		reporter.config().setDocumentTitle("Test Results");
		
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Ashok");
		
		log.debug("Before Class test Completed");
	}
	
	@Test
	public void invokeBrowser() throws IOException {
		
		log.debug("Main test Started");
		
		ExtentTest test = extent.createTest("Project1Test");
		WebDriver driver = null;
		String browserDriver = null;
		String driverPath = null;
		
		FileInputStream fis = new FileInputStream("C:\\Users\\assurasa\\Desktop\\Udemy\\Selenium\\DataDrivenExcel.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets = workbook.getNumberOfSheets();
		
		ArrayList<String> values = new ArrayList<String>();
		
		for(int i = 0; i < sheets; i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase("browserData")) {
				XSSFSheet sheet = workbook.getSheetAt(i);
				Iterator<Row> rows = sheet.iterator();
				
				rows.next();
				Row secondRow = rows.next();
				Iterator<Cell> cell= secondRow.cellIterator();
				
				while(cell.hasNext()) {
					browserDriver = cell.next().getStringCellValue();
					driverPath = cell.next().getStringCellValue();
				}
				
				//Duplicate of above code with values into arrays
				cell= secondRow.cellIterator();
				
				while(cell.hasNext()) {
					
					Cell value = cell.next();
					
					if(value.getCellType()==CellType.STRING) {
						values.add(value.getStringCellValue());
					} else {
						values.add(NumberToTextConverter.toText(value.getNumericCellValue()));
					}
					
				}
			}
			
			workbook.close();
			
		}
		

		System.setProperty(browserDriver, driverPath);
		//Override
		System.setProperty(values.get(0), values.get(1));
		driver = new ChromeDriver();
		driver.manage().window().maximize();
				
		driver.get(Url);
		
		String title = driver.getTitle();
		System.out.println(title);
		
		String currentUrl = driver.getCurrentUrl();
		System.out.println(currentUrl);
		
		driver.getPageSource();
		
		driver.close();
		test.fail("Result not as expected");
		extent.flush();
		
		log.debug("Main test Completed");
		
	}
	
	
}
