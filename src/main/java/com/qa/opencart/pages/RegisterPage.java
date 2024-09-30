package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil ele;
	
	private By registerHeading = By.xpath("//h1[normalize-space()='Register Account']");
	private By firstName = By.xpath("//input[@id='input-firstname']");
	private By lastName = By.xpath("//input[@id='input-lastname']");
	private By email = By.xpath("//input[@id='input-email']");
	private By telephone = By.xpath("//input[@id='input-telephone']");
	private By password = By.xpath("//input[@id='input-password']");
	private By confirPpassword = By.xpath("//input[@id='input-confirm']");
	private By privacyPolicy = By.xpath("//input[@name='agree']");
	private By continueBtn = By.xpath("//input[@value='Continue']");
	
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}
	
	
	public boolean verifyUserOnRegestrationPage(){
		return ele.isElementDisplayed(registerHeading);
	}
	
	public String getPageTitle() {
		return ele.waitForTitleContainsAndReturn(AppConstants.FRACTION_REGISTER_PAGE_TITLE,AppConstants.DEFAULT_SHORT_TIMEOUT);
	}
	
	
	public AccountsPage doRegisteration(String firstName, String lastName, String telephone,String password, String confirmPassword) {
		ele.waitForElementVisible(this.firstName, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(firstName);
		ele.doSendKeys(this.lastName, lastName);
		ele.doSendKeys(this.email, ele.getRandomEmail());
		ele.doSendKeys(this.telephone, telephone);
		ele.doSendKeys(this.password, password);
		ele.doSendKeys(this.confirPpassword, confirmPassword);
		ele.doClick(privacyPolicy);
		ele.doClick(continueBtn);
		
		return new AccountsPage(driver);
		
		
	}
	

}
