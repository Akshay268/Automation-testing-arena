package com.demowebshopproject.tests;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.demowebshopproject.pageobjects.CartPage;
import com.demowebshopproject.pageobjects.CheckoutPage;
import com.demowebshopproject.pageobjects.HomePage;
import com.demowebshopproject.pageobjects.LoginPage;
import com.demowebshopproject.pageobjects.ProductPage;
import com.demowebshopproject.utils.ExtentReportManager;
import com.demowebshopproject.utils.RetryExecution;
import com.demowebshopproject.utils.BaseClass;

public class LoginTest {

	WebDriver driver;
	
	JavascriptExecutor je;
		
	LoginPage loginPageObj ;

	HomePage homepage ;
	
	ProductPage productPage ;
	
	CartPage cartPage;
	
	CheckoutPage checkoutPageObj;
	
	BaseClass baseClassObj;
	
	ExtentReportManager extentReportManager;
	
	Logger logger = LogManager.getLogger(LoginTest.class);

	@BeforeClass
	@Parameters({"browser"})
	public void setUp(String browser, ITestContext context) throws IOException {
		
		baseClassObj = new BaseClass(driver);

		String URL = baseClassObj.getValueFromPropertiesFile("url");
		String expectedPageTitle = baseClassObj.getValueFromPropertiesFile("expLoginPageTitle");
		if(browser.equalsIgnoreCase("chrome")) {
			
			driver = new ChromeDriver();

		} else if(browser.equalsIgnoreCase("edge")) {
			
			driver = new EdgeDriver();

		} else if(browser.equalsIgnoreCase("firefox")) {
			
			driver = new FirefoxDriver();

		}
		context.setAttribute("WebDriver", driver);
		driver.get(URL);
		
		driver.manage().window().maximize();
		
		//Verify the page title 
		Assert.assertEquals(driver.getTitle(),expectedPageTitle);
		
		//Initialize page objects 
		
		loginPageObj = new LoginPage(driver);
		homepage = new HomePage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPageObj = new CheckoutPage(driver);
	}
	
	// Login to the application 

	@Test(priority = 1)
	public void login() throws Exception {
		
		logger.info("----------------Test case to Login to the application -------------");
		loginPageObj.setemailID(baseClassObj.getValueFromPropertiesFile("email"));
		loginPageObj.setpassword(baseClassObj.getValueFromPropertiesFile("password"));
		loginPageObj.clickLoginBtn();
		
		// Verify logged in to site
		loginPageObj.verifyLoggedInToSite();
	}
	
	// Read the menu items and display the menu item count and menu item
	@Test(priority = 2)
	public void readAndDisplayMenuItems() {
	
		logger.info("----------------Test case to Read the menu items and display the menu item count and menu item -------------");
		homepage.readMenuItems();

	}
	
	@Test(priority=3)
	public void compareScreenshots() throws IOException {
	
		logger.info("----------------Test case to compare screenshots -------------");

	//Scroll down and take screenshot 
	//compare actual and expected screenshot
	
		homepage.scrollToFeaturedProductsSection();
	
	// Scrolling down the page till the featured products title is visible		
    
	// Taking screenshot 
	
		String path = baseClassObj.takeScreenshot("screenshot-",driver);
	
		logger.info("Path of the captured screenshot is : " + path);
	
	// Comparing 2 images 
	// Images are same
		baseClassObj.compareScreenshot("D:\\test-automation\\workspace\\DemoWebShopProject\\screenshotstestName.png", "D:\\test-automation\\workspace\\DemoWebShopProject\\screenshotstestName.png");
	
	//Images are different
		baseClassObj.compareScreenshot("D:\\test-automation\\workspace\\DemoWebShopProject\\screenshot.png", "D:\\test-automation\\workspace\\DemoWebShopProject\\screenshotstestName.png");
	}
	
	// Select the product and customize the product configuration

	@Test(priority = 4)
	public void selectProductAndConfiguration() {
		
		logger.info("----------------Test case to select product and customize the configuration -------------");
		
		homepage.scrollToBuildYourOwnComputerLink();
		homepage.clickBuildYourComputerLink();
				
		productPage.selectFastProcessor();
		productPage.select8GBRAM();
		productPage.select400GBHDD();
		productPage.selectImageViewer();
		productPage.selectOfficeSuite();
		productPage.setproductQuantity("2");
	}
	
	//add product to cart
	@Test(priority = 5)
	public void addProductToCart() {
		
		logger.info("----------------Test case to add the product to cart -------------");
	
		productPage.clickAddToCartBtn();
	}
	
	@Test(priority = 6)
	public void navigateToCartAndCheckout() throws IOException {
		
		logger.info("----------------Test case to navigate to cart page-------------");

		// Mouse hover on cart and click on got to cart
		
		homepage.mouseHoverOnShoppingCartLinkAndClickOnGoToCartBtn();

	    // Select “Country” and click on check box “I agree with the terms of service and I adhere to them unconditionally (read)”.
	      
	    //  Click on "Check out".  
	      
	    cartPage.setDestinationDetailsAndCheckout("2","73","560035");
	}
	
	@Test(priority = 7)
	public void provideShippingAddressAndMethod() {
		
		logger.info("----------------Test case to select shipping address and shipping method-------------");

	      // Select Billing address-->click continue 

	      checkoutPageObj.clickBillingAddressContinueBtn();
	      
	      // Select Shipping address and shipping Method---> click continue 

	      checkoutPageObj.clickShippingAddressContinueBtn();
	      
	      checkoutPageObj.clickShippingMethodContinueBtn();
	}
	
	@Test(priority = 8)
	public void providePaymentMethodAndInfo() {
		
		logger.info("----------------Test case to provide payment method and details-------------");

		 //Select Payment Method--> Cash on Delivery 

		checkoutPageObj.clickPaymentMethodContinueBtn();

	      //Payment information--->Click on continue 
	      
		checkoutPageObj.clickPaymentInformationContinueBtn();
	}
	
	
	@Test(priority = 9)
	public void confirmOrderAndVeirfyOrderDetails() throws IOException {
	
		logger.info("----------------Test case to confirm the order and verify the order details-------------");

    //Confirm Order--->Continue 
    
		checkoutPageObj.clickConfirmOrderBtn();
    
    //Verify the order confirmation message "Your order has been successfully processed" 

		checkoutPageObj.verifyOrderConfirmationMessage();
    
    // verify order details
    
		checkoutPageObj.verifyOrderDetail();
	}
	
	@Test (priority=10,retryAnalyzer = RetryExecution.class)
	public void verifyTestFailure() {
		
		Assert.assertEquals(false, true);
		
	}
	
	@AfterClass
	public void teardown() {
		
		driver.quit();
	}
}
