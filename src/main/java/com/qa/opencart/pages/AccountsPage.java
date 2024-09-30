package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	private By logoutLink = By.xpath("//a[@class='list-group-item'][normalize-space()='Logout']");
	private By searchTextbox = By.xpath("//input[@placeholder='Search']");
	private By searchBtn = By.xpath("//button[@class='btn btn-default btn-lg']");
	
	private By registrationSuccessMessage = By.xpath("//h1[normalize-space()='Your Account Has Been Created!']");
	
	private By logoutLinkOnRegister = By.linkText("Logout");
	private By continueLogoutBtn  = By.linkText("Continue");
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	public boolean isLogoutLinkExist() {
		return ele.isElementDisplayed(logoutLink);
	}
	
	public String getAccountsPageTitle() {
		return ele.waitForTitleContainsAndReturn(AppConstants.FRACTION_ACCOUNT_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIMEOUT);
	}
	
	public ResultsPage doSearch(String searchText) {
		
		 WebElement searchBox = ele.waitForElementVisible(searchTextbox,AppConstants.DEFAULT_SHORT_TIMEOUT);
		 searchBox.clear();
		 searchBox.sendKeys(searchText);
		 
		ele.doClick(searchBtn);
		return new ResultsPage(driver);
		
	}
	
	public boolean isRegistrationSuccessful() {
		ele.waitForElementVisible(registrationSuccessMessage, AppConstants.DEFAULT_SHORT_TIMEOUT);
		return ele.isElementDisplayed(registrationSuccessMessage);
	}
	
	public void logoutFromRegisterPage() {
		ele.waitForElementVisible(logoutLinkOnRegister, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
	}
	

}
