package com.practice.ObjectRepository;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class data {
	
	WebDriver driver;
	
	public data(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text() = 'Amazon Pay']")
	WebElement AmazonText;
	
	public WebElement AmazonText() {
		return AmazonText;
	}

}
