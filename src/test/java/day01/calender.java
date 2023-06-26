package day01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class calender {

	public static void main(String[] args) {
		WebDriver driver;
		driver=new ChromeDriver();
		driver.get("https://www.redbus.in/");
		
		driver.findElement(By.id("onward_cal")).click();
		String year="2029";
		String month="Feb";
		while(true) {
			String yearmonth=driver.findElement(By.xpath("//td[@class=\"monthTitle\"]")).getText();
			String arr[]=yearmonth.split(" ");
			String mon=arr[0];
			String yr=arr[1];
			if(month.equals(mon)&&year.equals(yr)) {
				break;
			}
			else {
				driver.findElement(By.xpath("//td[@class=\"next\"]")).click();
			}
			
		}
		driver.findElement(By.xpath("//td[text()='6']")).click();
	}

}
