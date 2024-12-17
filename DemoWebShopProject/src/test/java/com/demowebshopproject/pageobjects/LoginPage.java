package com.demowebshopproject.pageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	@FindBy(id="Email") WebElement email;
	
	@FindBy(id="Password") WebElement password;
	
	@FindBy(xpath="//a[@href='/logout']") WebElement logoutLink;
	
	@FindBy(xpath = "//input[@type='submit' and @value='Log in']") WebElement loginBtn;
	
	public LoginPage(WebDriver driver) {
		
		super(driver);
	}
	
	
	public void setemailID(String emailID) {
				
		email.sendKeys(emailID);
	}
	
	public void setpassword(String passwd) {
		
		password.sendKeys(passwd);
	}
	
	public void clickLoginBtn() {
		
		loginBtn.click();
	}
	
	public void verifyLoggedInToSite( ) {
		
		assertTrue(logoutLink.isDisplayed(),"Log out link is not displayed");
	}
}
