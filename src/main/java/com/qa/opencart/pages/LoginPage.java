package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;

public class LoginPage {
	private WebDriver driver;
	private ElementUtil ele;

	private By userName = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgottenPaswordlink = By.linkText("Forgotten Password");
	private By logo = By.xpath("//img[@title='naveenopencart']");
	private By myAccount = By.xpath("//h2[normalize-space()='My Account']");
	
	private By registerBtn = By.xpath("//a[@class='list-group-item'][normalize-space()='Register']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		ele = new ElementUtil(driver);
	}

	public String getLoginPageTitle() {

		String title = ele.waitForTitleContainsAndReturn(AppConstants.LOGIN_PAGE_TITLE,
				AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Page title : " + title);
		return title;
	}

	public String getLoginPageUrl() {

		String url = ele.waitForURLContainsAndReturn(AppConstants.LOGIN_PAGE_FRACTION_URL,
				AppConstants.DEFAULT_SHORT_TIMEOUT);
		System.out.println("Url of current page: " + url);
		return url;
	}

	public boolean isForgotPasswordLinkExist() {

		return ele.isElementDisplayed(forgottenPaswordlink);
	}

	public boolean isLogoExist() {

		return ele.isElementDisplayed(logo);
	}

	public AccountsPage doLogin(String username, String password) {
		
		ele.waitForElementVisible(userName, AppConstants.DEFAULT_SHORT_TIMEOUT).sendKeys(username);
		ele.doSendKeys(this.password, password);
		ele.doClick(loginBtn);
		return new AccountsPage(driver);

	}
	
	public RegisterPage doRegistration() {
		ele.waitForElementVisible(registerBtn, AppConstants.DEFAULT_SHORT_TIMEOUT).click();
		return new RegisterPage(driver);
	}
	
	public WebDriver getDriverFromLoginPage() {
		return driver;
	}
	
	

}
