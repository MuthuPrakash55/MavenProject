package day01;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class framework {
		static ExtentReports report;
		static ExtentTest test;
		static WebDriver driver;
	public static void browser(String browservalue) {
		switch (browservalue) {
		case "chrome":
			driver=new ChromeDriver();
			test.log(LogStatus.INFO, "open chrome browser");
			break;
		case "edge":
			driver=new EdgeDriver();
			test.log(LogStatus.INFO, "open Edge browser");
			break;
		case "firefox":
			driver=new FirefoxDriver();
			test.log(LogStatus.INFO, "open Firefox browser");
			break;
		
		}
	}
	public static void startreport(String filename) {
		report=new ExtentReports(".\\src\\test\\resources"+filename+".html");
	}
	public static void endreport() {
		report.flush();
	}
	public static void starttest(String filename,String description) {
		test=report.startTest(filename, description);
	}
	public static void endtest() {
		report.endTest(test);
	}
	public static String getxpath(String fieldname) throws IOException {
		File file=new File(".\\src\\test\\resources\\datasheet.xlsx");
		FileInputStream stream=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(stream);
		XSSFSheet sh=wb.getSheet("test");
		DataFormatter data=new DataFormatter();
		
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(fieldname.equals(data.formatCellValue(sh.getRow(i).getCell(0)))) {
				return (data.formatCellValue(sh.getRow(i).getCell(1)));
			}
		}
	return null;
	}
	public static String getdata(String fieldname) throws IOException {
		File file=new File(".\\src\\test\\resources\\datasheet.xlsx");
		FileInputStream stream=new FileInputStream(file);
		XSSFWorkbook wb=new XSSFWorkbook(stream);
		XSSFSheet sh=wb.getSheet("test");
		DataFormatter data=new DataFormatter();
		
		for(int i=0;i<=sh.getLastRowNum();i++) {
			if(fieldname.equals(data.formatCellValue(sh.getRow(i).getCell(0)))) {
				return (data.formatCellValue(sh.getRow(i).getCell(2)));
			}
		}
	return null;
}
	public static void appurl(String url) {
		driver.get(url);
		test.log(LogStatus.INFO, "open application url"+url);
	}
	public static void typein(String fieldname) throws IOException {
		driver.findElement(By.xpath(getxpath(fieldname))).sendKeys(getdata(fieldname));
		test.log(LogStatus.INFO, "user enter the value in "+fieldname+" as "+getdata(fieldname));
	}
	public static void click(String fieldname) throws IOException {
		driver.findElement(By.xpath(getxpath(fieldname))).click();
		test.log(LogStatus.INFO, "user clicked the "+fieldname+" button");
	}
	public static void windowmax() {
		driver.manage().window().maximize();
		test.log(LogStatus.INFO, "window is maximized");
	}
	public static void windowmin() {
		driver.manage().window().minimize();
		test.log(LogStatus.INFO, "window is minimized");
	}
	public static void windowfullscreen() {
		driver.manage().window().fullscreen();
		test.log(LogStatus.INFO, "fullscreen window is opened ");
	}
	public static void backward() {
		driver.navigate().back();
		test.log(LogStatus.INFO, "page is moved backward");
	}
	public static void forward() {
		driver.navigate().forward();
		test.log(LogStatus.INFO, "page is moved forward");
	}
	public static void refresh() {
		driver.navigate().refresh();
		test.log(LogStatus.INFO, "page is refreshed");
	}
}
