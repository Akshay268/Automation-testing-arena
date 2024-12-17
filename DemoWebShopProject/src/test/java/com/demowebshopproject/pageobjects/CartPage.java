package com.demowebshopproject.pageobjects;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class CartPage extends BasePage{

	
    @FindBy(xpath = "//div[@class=\\\"details\\\"]//a[contains(text(),'Build your own expensive computer')]")
    WebElement buildYourComputerLink;
    
	@FindBy(id = "topcartlink") WebElement shoppingCartLink;

	@FindBy(xpath = "//input[@value='Go to cart']") WebElement goToCartBtn;

	@FindBy(id = "CountryId") WebElement countryDropdown;
	
	@FindBy(id = "StateProvinceId") WebElement stateDropdown;

	@FindBy(id = "ZipPostalCode") WebElement postalCodeField;

	@FindBy(id = "termsofservice") WebElement agreeTermsCheeckbox;

	@FindBy(id = "checkout") WebElement checkoutBtn;

	public CartPage(WebDriver driver) {
		
		super(driver);
	}
	
	public void setDestinationDetailsAndCheckout(String countryVal, String stateVal,String postalcodeVal) throws IOException {
		
	       Select selectCountry = new Select(countryDropdown);
	       Select selectState = new Select(stateDropdown);
	       String expectedCheckoutPageTitle = baseClassObj.getValueFromPropertiesFile("expCheckoutPageTitle");

	       baseClassObj.scrollToElement(countryDropdown);
	       selectCountry.selectByValue(countryVal);
	       baseClassObj.implicitWait(5);

	       selectState.selectByValue(stateVal);
	       baseClassObj.implicitWait(2);

	       postalCodeField.clear();
	       postalCodeField.sendKeys(postalcodeVal);
	       
	       agreeTermsCheeckbox.click();
	       checkoutBtn.click();
	       
	       // Verify navigated to checkout page
	       
	       Assert.assertEquals(driver.getTitle(), expectedCheckoutPageTitle);
	}
}
