package day01;

public class program01 extends framework1{
	
	public static void main(String[] args) throws Exception {
		startreport("demo");
		starttest("regiester","verify that user is able to fill the form");
		browser("chrome");
		appurl("https://demo.automationtesting.in/Register.html");
		typein("firstname");
		screenshot("firstname");
		typein("lastname");
		click("click");
		endtest();
		endreport();
	}
	}

