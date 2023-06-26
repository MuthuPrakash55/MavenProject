package day01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class framework1 {
	static WebDriver driver;
	static ExtentReports reports;
	static ExtentTest test;
	
	public static void browser(String browservalue) {
		switch (browservalue) {
		case "chrome":
			driver=new ChromeDriver();
			test.log(LogStatus.INFO,"chrome browser is opened");
			break;
		case "edge":
			driver=new EdgeDriver();
			test.log(LogStatus.INFO,"Edge browser is opened");
			break;
		default:
			break;
		}
	}
	public static void startreport(String filename) {
		reports=new ExtentReports(".\\src\\test\\resources\\"+filename+".html");
	}
	public static void endreport() {
		reports.flush();
	}
	public static void starttest(String testname,String description) {
		test=reports.startTest(testname,description);
	}
	public static void endtest() {
		reports.endTest(test);
	}
	public static String getxpath(String fieldname) throws Exception {
		File file=new File(".\\src\\test\\resources\\datasheet.xlsx");
		FileInputStream stream=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(stream);
		XSSFSheet sh=wb.getSheet("demo");
		DataFormatter data=new DataFormatter();
		
		for(int i=1;i<=sh.getLastRowNum();i++) {
			if(fieldname.equals(data.formatCellValue(sh.getRow(i).getCell(0)))) {
				return(data.formatCellValue(sh.getRow(i).getCell(1)));
			}
		}
		return null;
	}
	public static String getdata(String fieldname) throws Exception {
		File file=new File(".\\src\\test\\resources\\datasheet.xlsx");
		FileInputStream stream=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(stream);
		XSSFSheet sh=wb.getSheet("demo");
		DataFormatter data=new DataFormatter();
		
		for(int i=1;i<=sh.getLastRowNum();i++) {
			if(fieldname.equals(data.formatCellValue(sh.getRow(i).getCell(0)))) {
				return(data.formatCellValue(sh.getRow(i).getCell(2)));
			}
		}
		return null;
	}
	public static void appurl(String url) {
		driver.get(url);
		test.log(LogStatus.INFO, "user open applicatio url "+url);
	}
	public static void typein(String fieldname) throws Exception {
		driver.findElement(By.xpath(getxpath(fieldname))).sendKeys(getdata(fieldname));
		test.log(LogStatus.INFO, "user entered value in "+fieldname+"as"+getdata(fieldname));
	}
	public static void click(String fieldname) throws Exception {
		driver.findElement(By.xpath(getxpath(fieldname))).click();
		test.log(LogStatus.INFO, "user clickeed the "+fieldname);
	}
	public static void screenshot(String filename) throws IOException {
		File des=new File(".\\src\\test\\resources\\"+filename+".png");
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(src, des);
	}
}
