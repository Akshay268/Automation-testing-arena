package com.demowebshopproject.pageobjects;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductPage extends BasePage{

	//WebDriver driver;
	
	@FindBy(id="Email") WebElement email;
	
	@FindBy(id="Password") WebElement password;
	
	@FindBy(xpath = "//label[contains(text(),'Fast')]") WebElement processorFastRadiobutton;
	
	@FindBy(xpath = "//label[contains(text(),'Medium')]") WebElement processorMediumRadiobutton;
	
	@FindBy(xpath = "//label[contains(text(),'Slow')]") WebElement processorSlowRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'8GB')]") WebElement ram8GBRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'4GB')]") WebElement ram4GBRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'2GB')]") WebElement ram2GBRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'400')]") WebElement hdd400GBRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'320')]") WebElement hdd320GBRadiobutton;

	@FindBy(xpath = "//label[contains(text(),'Image Viewer')]") WebElement softwareImageViewerCheckbox;

	@FindBy(xpath = "//label[contains(text(),'Office Suite')]") WebElement softwareOfficeSuiteCheckbox;
	
	@FindBy(xpath = "//label[contains(text(),'Other Office Suite')]") WebElement softwareOtherOfficeSuiteCheckbox;

	@FindBy(xpath = "//input[@class='qty-input']") WebElement productQuantity;

	@FindBy(xpath = "//div[@class='add-to-cart-panel']/input[contains(@value,'Add to cart')]") WebElement addToCartBtn;

	@FindBy(xpath = "//p[contains(text(),'The product has been added to your ')]") WebElement successNotification;
	
	public ProductPage(WebDriver driver) {
		
		super(driver);
	}
	
    // Select Processor

	public void selectFastProcessor() {
				
		processorFastRadiobutton.click();
	}
	
	public void selectMediumProcessor() {
		
		processorMediumRadiobutton.click();
	}
	
	public void selectSlowProcessor() {
		
		processorSlowRadiobutton.click();
	}
	
	// Select RAM
	
	
	public void select2GBRAM() {
		
		ram2GBRadiobutton.click();
	}
	
	public void select4GBRAM() {
		
		ram4GBRadiobutton.click();
	}
	
	public void select8GBRAM() {
		
		ram8GBRadiobutton.click();
	}
	
	// Select HDD
	
	public void select400GBHDD() {
		
		hdd400GBRadiobutton.click();
	}
	
	public void select320GBHDD() {
		
		hdd320GBRadiobutton.click();
	}
	
	// Select Software
	
	public void selectImageViewer() {
		
		softwareImageViewerCheckbox.click();
	}
	
	public void selectOfficeSuite() {
		
		softwareOfficeSuiteCheckbox.click();
	}
	
	public void selectOtherOfficeSuite() {
		
		softwareOtherOfficeSuiteCheckbox.click();
	}
	
	// Change Quantity to 2
	
	public void setproductQuantity(String quantity) {
		
		productQuantity.clear();
		
		productQuantity.sendKeys(quantity);
		
	}
	
	// Click add to cart button
	
	public void clickAddToCartBtn() {
		
		baseClassObj.implicitWait(3);

		addToCartBtn.click();
		
		baseClassObj.scrollToTopOfThePage();

		// Verify the success notification on clicking add to cart to button
		
		try {
			assertTrue(successNotification.isDisplayed());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
