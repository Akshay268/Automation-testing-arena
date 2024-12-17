package com.demowebshopproject.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.demowebshopproject.utils.BaseClass;

public class BasePage {

	WebDriver driver;
	BaseClass baseClassObj;
	
	public BasePage(WebDriver driver) {
		
		this.driver = driver;
		PageFactory.initElements(driver, this);
		baseClassObj = new BaseClass(driver);
	}
	
	
}
