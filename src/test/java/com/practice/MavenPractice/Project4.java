package com.practice.MavenPractice;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

public class Project4 {
	
	@Test(groups= {"Sample"})
	public void testCase() {
		
		String url = "https://www.google.com";
		String search = "techlistic";
		WebDriver driver = null;
		
		System.setProperty("webdriver.gecko.driver", "C:\\Users\\assurasa\\Desktop\\Udemy\\Selenium\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		
		driver.get(url);
		
		driver.findElement(By.name("q")).sendKeys(search);
		driver.findElement(By.name("q")).click();
		
		WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.className("erkvQe")));
	    
	    WebElement list = driver.findElement(By.className("erkvQe"));
	    List<WebElement> rows = list.findElements(By.tagName("li"));
	    
	    for (WebElement elem : rows) {
	        System.out.println(elem.getText());
	    }
	    
	    driver.close();
	
	}

}
