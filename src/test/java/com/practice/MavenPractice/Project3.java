package com.practice.MavenPractice;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class Project3 {
	
	@Test(timeOut=100000, dataProvider="getData",groups={"Sample"}) 
	public void testCase1(String Url, String firstName, String lastName, String date) throws IOException {
		
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream("C:\\Users\\assurasa\\eclipse-workspace\\java-selenium\\Techlistic\\data.properties");
		prop.load(fis);

		WebDriver driver = null;
		System.setProperty("webdriver.chrome.driver", prop.getProperty("browser"));
		driver = new ChromeDriver();
		
		driver.get(Url);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//input[@name = 'firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@name = 'lastname']")).sendKeys(lastName);
		
		driver.findElement(By.xpath("//input[@value = 'Male']")).click();
		
		driver.findElement(By.xpath("(//div[@class='control-group'])[5]/input[6]")).click();
		driver.findElement(By.cssSelector("input#datepicker")).sendKeys(date);
		
		driver.findElement(By.id("profession-0")).click();
		driver.findElement(By.id("profession-1")).click();
		
		driver.findElement(By.id("tool-2")).click();
		
		Select s;
		
		s = new Select(driver.findElement(By.id("continents")));
		s.selectByVisibleText("Asia");
		
		s = new Select(driver.findElement(By.id("selenium_commands")));
		List<WebElement> strings= s.getOptions();
		for(WebElement string: strings) {
			string.click();
			driver.findElement(By.id("selenium_commands")).sendKeys(Keys.CONTROL);
		}
		
		driver.findElement(By.xpath("(//div[@class='control-group'])[10]/input")).sendKeys("C:\\Users\\assurasa\\Downloads\\Capture.png");
		
		driver.findElement(By.cssSelector("button#submit")).click();
		
		driver.close();
	}
	
	@DataProvider
	public Object[][] getData() {
		
		Object[][] data = new Object[1][4];
		data[0][0] = "https://www.techlistic.com/p/selenium-practice-form.html";
		data[0][1] = "Ashok";
		data[0][2] = "Reddy";
		data[0][3] = "10-AUG-2020";
		
		return data;
		
	}

}
