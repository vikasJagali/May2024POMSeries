package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class ResultsPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	private By searchHeading =  By.xpath("//h2[normalize-space()='Products meeting the search criteria']");

	public ResultsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public boolean verifySearchHeading() {
		ele.waitForElementVisible(searchHeading, AppConstants.DEFAULT_SHORT_TIMEOUT);
		return ele.isElementDisplayed(searchHeading);
	}
	
	public ProductInfoPage selectProduct(String productName) {
		ele.waitForElementVisible(By.linkText(productName), AppConstants.DEFAULT_SHORT_TIMEOUT);
		ele.doClick(By.linkText(productName), 0);
		
		return new ProductInfoPage(driver);
	}
	
}
