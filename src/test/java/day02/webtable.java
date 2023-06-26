package day02;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class webtable {
	static WebDriver driver;
	public static void main(String[] args) {
		driver=new ChromeDriver();
		driver.get("https://letcode.in/advancedtable");
		clientname("University of Abertay Dundee");
		clientname("Aston University");
	}
	public static int rownumber(String name) {
		List<WebElement> webtable=driver.findElements(By.xpath("//table[@id=\"advancedtable\"]//td[2]"));
		int count=0;
		for(WebElement names:webtable) {
			if(name.equals(names.getText().trim())) {
				return ++count;
			}
			 count++;
		}
		return 0;
	}
	public static void clientname(String name) {
		int row=rownumber(name);
		List<WebElement> names1=driver.findElements(By.xpath("//table[@id=\"advancedtable\"]//tr["+row+"]//td"));
		List<String>list=new ArrayList<>();
		for(WebElement names2:names1) {
			list.add(names2.getText());
		}
		System.out.println(row);
	System.out.println(list);
	driver.findElement(By.xpath("//a[@class=\"paginate_button next\"]")).click();
	}
}
