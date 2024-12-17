package com.demowebshopproject.pageobjects;

import static org.testng.Assert.assertTrue;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutPage extends BasePage {

	@FindBy(xpath = "//div[@id='billing-buttons-container']/input[@title='Continue']") WebElement billingAddressContinueBtn;

	@FindBy(xpath = "//div[@id='shipping-buttons-container']/input[@title='Continue']") WebElement shippingAddressContinueBtn;
	
	@FindBy(id = "shippingoption_1") WebElement shippingOptionRadioBtn;

	@FindBy(xpath = "//div[@id='shipping-method-buttons-container']/input[@value='Continue']") WebElement shippingMethodContinueBtn;

	@FindBy(xpath = "//input[@value='Payments.CashOnDelivery']") WebElement paymentMethodRadioBtn;

	@FindBy(xpath = "//div[@id='payment-method-buttons-container']/input[@value='Continue']") WebElement paymentMethodContinueBtn;

	@FindBy(xpath = "//p[contains(text(),'You will pay by COD')]") WebElement paymentInfoType;

	@FindBy(xpath = "//div[@id='payment-info-buttons-container']/input[@value='Continue']") WebElement paymentInfoContinueBtn;
	
	@FindBy(xpath = "//div[@id='confirm-order-buttons-container']/input[@value='Confirm']") WebElement confirmOrderBtn;
	
	@FindBy(xpath = "//strong[contains(text(),'Your order has been successfully processed!')]") WebElement orderSuccessfulMessage;
	
	@FindBy(xpath = "//li[contains(text(),'Order number:')]") WebElement orderNumber;
	
	@FindBy(xpath = "//a[contains(text(),'Click here for order details')]") WebElement orderDetailLink;

	@FindBy(xpath = "//h1[contains(text(),'Order information')]") WebElement orderInfoHeading;
	
	@FindBy(xpath = "//a[contains(text(),'Build your own expensive computer')]") WebElement productName;
	
	@FindBy(xpath = "//div[@class='order-number']") WebElement ordernumberConfirmationPage;
	
	@FindBy(xpath = "//div[@class='order-details']") WebElement orderDetailsConfirmationPage;
	Logger logger = LogManager.getLogger(CheckoutPage.class);

	public CheckoutPage(WebDriver driver) {
		
		super(driver);
	}
	
    
    public void clickBillingAddressContinueBtn() {
    	
    	billingAddressContinueBtn.click();
    }
    
    public void clickShippingAddressContinueBtn() {
    	
    	baseClassObj.scrollToElement(shippingAddressContinueBtn);
    	shippingAddressContinueBtn.click();
    	baseClassObj.implicitWait(3);
    }
        
    public void clickShippingMethodContinueBtn() {
    	
    	shippingOptionRadioBtn.click();
    	baseClassObj.scrollToElement(shippingMethodContinueBtn);

    	shippingMethodContinueBtn.click();
    	baseClassObj.implicitWait(3);
    }   
    
    public void clickPaymentMethodContinueBtn() {
    	
    	paymentMethodRadioBtn.click();
    	baseClassObj.implicitWait(3);
    	baseClassObj.scrollToElement(paymentMethodContinueBtn);
    	paymentMethodContinueBtn.click();
    	baseClassObj.implicitWait(3);
    }

    public void clickPaymentInformationContinueBtn() {
    	    	
    	assertTrue(paymentInfoType.isDisplayed(), "Payment info method text is different");
    	paymentInfoContinueBtn.click();
    	baseClassObj.implicitWait(3);
    }
    
    public void clickConfirmOrderBtn() {
    	
    	baseClassObj.scrollToElement(confirmOrderBtn);
    	confirmOrderBtn.click();
    	baseClassObj.implicitWait(3);
    }      

    public void verifyOrderConfirmationMessage() {
    	
    	assertTrue(orderSuccessfulMessage.isDisplayed(), "Successful order message is not displayed");
    	
    	if (orderNumber.isDisplayed()) {
    		
    	 	logger.info("order number is generated and the order number is : " + orderNumber.getText());   
    	} else {
    	 	logger.info("Order number is not generated");   
    	}
    } 
    
    public void verifyOrderDetail() throws IOException {
    	
  	   String expectedOrderDetailsPageTitle = baseClassObj.getValueFromPropertiesFile("expOrderDetailsPageTitle");
       
    	orderDetailLink.click();
    	
    	try {
	    	Assert.assertEquals(driver.getTitle(), expectedOrderDetailsPageTitle);
	    	
	    	Assert.assertTrue(orderInfoHeading.isDisplayed());
	    	
	    	Assert.assertTrue(ordernumberConfirmationPage.isDisplayed());
	
	    	Assert.assertTrue(orderDetailsConfirmationPage.isDisplayed());
	
	    	Assert.assertTrue(productName.isDisplayed());
    	} catch (Exception e) {
    		
    		e.printStackTrace();
    	}
    }
}
