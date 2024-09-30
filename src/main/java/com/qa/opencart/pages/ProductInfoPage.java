package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {
	
	WebDriver driver;
	ElementUtil ele;
	Map<String, String> productMap ; 
	
	private By productHeading = By.xpath("//h1[normalize-space()='MacBook Pro']");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By priceMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");
	private By imageCount = By.xpath("//ul[@class='thumbnails']//li");
	
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
		
	}
	
	public String getProductHeading(String pHeading) {
		 return ele.waitForElementVisible(By.linkText(pHeading), AppConstants.DEFAULT_SHORT_TIMEOUT).getText();
	}
	
	private void getProductMetaData() {
		List<WebElement> MetaData = ele.getElements(productMetaData);
		
		for(WebElement ele : MetaData) {
			String[] metaText = ele.getText().split(":");
			productMap.put(metaText[0].trim(), metaText[1].trim());
		}
	}
		
	private void getPriceData() {
			List<WebElement> metaList = ele.getElements(priceMetaData);
			
			String price = metaList.get(0).getText().trim();
			String extPrice = metaList.get(1).getText().split(":")[1].trim();
			productMap.put("productPrice", price);
			productMap.put("extPrice", extPrice);
			
		}
		
		public Map<String,String> getProductData() {
			productMap = new HashMap<String,String>();      //no order maintained
		//	productMap = new LinkedHashMap<String,String>();   LinkedHashMap Maintains the insertion order while pritning
			//productMap = new TreeMap<String,String>();        TreeMap maintains alphabetical order
			
			productMap.put("productHeading", getProductHeading("MacBook Pro"));
			getProductMetaData();
			getPriceData();
			System.out.println("==========================================================");
			System.out.println(productMap);
			System.out.println("==========================================================");
			
	
			return productMap;
		}
		
		public int getImageCount() {
			return ele.waitForElementsPresence(imageCount, AppConstants.DEFAULT_SHORT_TIMEOUT).size();
			
		}
	}
	

