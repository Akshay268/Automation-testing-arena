package com.demowebshopproject.pageobjects;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy(xpath = "//div[@class='details']//a[contains(text(),'Build your own expensive computer')]")
    WebElement buildYourComputerLink;
    
	@FindBy(id = "topcartlink") WebElement shoppingCartLink;

	@FindBy(xpath = "//input[@value='Go to cart']") WebElement goToCartBtn;
	
	@FindBy(xpath = "//ul[@class='top-menu']/li") List<WebElement> menuItemList;
	
	@FindBy(xpath = "//strong[contains(text(),'Featured products')]") WebElement featuredProductsSection;
	
	@FindBy(xpath = "//ul[@class='top-menu']/li/ul/li") List<WebElement> submenuItemList;
	
	Logger logger = LogManager.getLogger(HomePage.class);

	public HomePage(WebDriver driver) {
		
		super(driver);
	}
    
    public void clickBuildYourComputerLink() {
    
    	buildYourComputerLink.click();
    }
    
    public void visitCartPage() {
    
    	shoppingCartLink.click();
    }
    
    public void scrollToBuildYourOwnComputerLink( ) {
    	
    	baseClassObj.scrollToElement(buildYourComputerLink);
    }
    
    public void scrollToFeaturedProductsSection( ) {
    	
    	baseClassObj.scrollToElement(featuredProductsSection);
    }
    
    
    public void mouseHoverOnLink( WebElement element) {
    	
    	baseClassObj.implicitWait(3);
    	
    	Actions action = new Actions(driver);
                  
    	action.moveToElement(shoppingCartLink).build().perform();
        
    	baseClassObj.implicitWait(5);
    }
    
    public void mouseHoverOnShoppingCartLinkAndClickOnGoToCartBtn() {
    	  
    	mouseHoverOnLink(shoppingCartLink);	
    	baseClassObj.waitTillElementAppears(3,goToCartBtn);
    	goToCartBtn.click();
    }
    
    public void readMenuItems() {
    	
    	List<WebElement> menuItems = menuItemList;
    	logger.info("Total number of menu items are : " + menuItemList.size() + "\nMenu items are : " );
    	for (WebElement menuitem : menuItemList) {
    		
    		logger.info(( menuItems.indexOf(menuitem) + 1 ) + " --> " + menuitem.getText());
    	}
    }
    
}
