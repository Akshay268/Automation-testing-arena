package com.demowebshopproject.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ru.yandex.qatools.ashot.comparison.ImageDiff;
import ru.yandex.qatools.ashot.comparison.ImageDiffer;

public class BaseClass {

	WebDriver driver;

	JavascriptExecutor je;	
	ExtentReportManager extentReportManagerObj;
	Logger logger = LogManager.getLogger(BaseClass.class);
	
	public BaseClass(WebDriver driver) {
		
		this.driver = driver;
	}

	public String getValueFromPropertiesFile(String key) throws IOException {
	Properties prop = new Properties();
	
	FileReader filereader = new FileReader("D:\\test-automation\\workspace\\DemoWebShopProject\\config\\config.properties");
	
	prop.load(filereader);
	
	return prop.getProperty(key);
	
	}
	
	public String takeScreenshot(String testName, WebDriver driver) throws IOException {
		
		  String timeStamp = new SimpleDateFormat("yyyy.mm.DD.hh.mm.ss").format(new Date());
		
		  TakesScreenshot sc = (TakesScreenshot)driver;
	        
	      File src =  sc.getScreenshotAs(OutputType.FILE);
	        
	      String destinationPath = "D:\\test-automation\\workspace\\DemoWebShopProject\\screenshots\\" + "testName" + timeStamp + ".png";
	              
	      File destFile = new File(destinationPath);
	      
	      src.renameTo(destFile);
	      
	      return destinationPath;
	}
	
	public void compareScreenshot(String actualScreenshotpath, String expectedScreenshotPath) throws IOException {
		
		extentReportManagerObj = new ExtentReportManager();
		  
		BufferedImage expectedImage = ImageIO.read(new File(expectedScreenshotPath));
		
		BufferedImage actualImage = ImageIO.read(new File(actualScreenshotpath));
		
		ImageDiffer imageDiff = new ImageDiffer();
		
		ImageDiff diff = imageDiff.makeDiff(expectedImage, actualImage);
		
		if(diff.hasDiff()) {
			
			logger.info("---- Images are different ----");
		} else {
			logger.info("---- Images are same ----");
		}
	}
	
	public void waitTillElementAppears(int duration,WebElement element) {
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(duration) );
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
	public void implicitWait(int duration) {
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(duration));
		
	}

	public void scrollToElement(WebElement element) {
	       
		je = (JavascriptExecutor)driver;
	
		je.executeScript("arguments[0].scrollIntoView();", element);
	}
	
	public void scrollToTopOfThePage() {
		
		je = (JavascriptExecutor)driver;
        je.executeScript("window.scrollTo(0, 0)");
	}
}
