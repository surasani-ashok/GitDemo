package com.practice.MavenPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.practice.ObjectRepository.data;

public class Project2 {
	
	WebDriver driver = null;
	data dt = new data(driver);
	
	@Parameters({"Chrome","Url"})
	@Test(dependsOnMethods= {"testCase2"},groups={"Sample"})
	public void testCase1(String Chrome, String url) {
		
		WebDriver driver = null;
		
		System.setProperty("webdriver.chrome.driver", Chrome);
		driver = new ChromeDriver();
		data dt = new data(driver);
		
		driver.get(url);
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		dt.AmazonText().click();
		
		String newTitle = driver.getTitle();
		System.out.println(newTitle);
		
		driver.navigate().back();
		
		String navigateTitle = driver.getTitle();
		System.out.println(navigateTitle);
		
		Assert.assertEquals(title, navigateTitle);
		
		driver.close();
	}
	
	@Test(groups={"Sample"})
	public void testCase2() {
		
		WebDriver driver = null;
		
		String Url = "https://www.techlistic.com/";
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\assurasa\\Desktop\\Udemy\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		
		driver.get(Url);
		driver.manage().window().maximize();
		
		driver.findElement(By.xpath("//ul[@class = 'tabs']/li[2]/a")).click();
		
		String newTitle = driver.getTitle();
		System.out.println(newTitle);
		
		driver.navigate().back();
		driver.findElement(By.xpath("//ul[@class = 'tabs']/li[3]/a")).click();
		
		String navigateTitle = driver.getTitle();
		System.out.println(navigateTitle);
		
		driver.close();
	}
	
	@Parameters({"Url"})
	@Test(enabled=false)
	public void testCase3(String url) {
		
		WebDriver driver = null;
		
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\assurasa\\Desktop\\Udemy\\Selenium\\chromedriver.exe");
		driver = new ChromeDriver();
		data dt = new data(driver);
		
		driver.get(url);
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		System.out.println(title);
		
		dt.AmazonText().click();
		
		String newTitle = driver.getTitle();
		System.out.println(newTitle);
		
		String expectedTitle = "Amazon Pay";
		Assert.assertEquals(newTitle, expectedTitle);
		
		driver.navigate().back();
		
		driver.close();
	}
	
}
