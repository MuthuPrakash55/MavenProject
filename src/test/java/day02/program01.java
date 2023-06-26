package day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class program01 {
	WebDriver driver;
	@Parameters({"name","des"})
	@Test
	public void testcase(String name,String des) {
		
		driver=new ChromeDriver();
		driver.get("https://leafground.com/input.xhtml");
		driver.findElement(By.id("j_idt88:name")).sendKeys(name);
		driver.findElement(By.id("j_idt88:j_idt101")).sendKeys(des);	}
}
