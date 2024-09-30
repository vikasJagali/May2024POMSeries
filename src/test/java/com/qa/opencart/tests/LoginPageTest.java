package com.qa.opencart.tests;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class LoginPageTest extends BaseTest{
	
	
	@Test
	public void loginPageTitleTest() {
		String title = loginPage.getLoginPageTitle();
		Assert.assertEquals(title, AppConstants.LOGIN_PAGE_TITLE + "dd");
	}
	
	@Test
	public void loginPageUrlTest() {
		String url = loginPage.getLoginPageUrl();
		Assert.assertTrue(url.contains(AppConstants.LOGIN_PAGE_FRACTION_URL));
	}
	
	@Test
	public void forgotPasswordLinkExists() {
		boolean exists = loginPage.isForgotPasswordLinkExist();
		Assert.assertTrue(exists);
	}

	@Test
	public void logoExists() {
		boolean exists = loginPage.isLogoExist();
		Assert.assertTrue(exists);
	}
	
	@Test
	public void loginTest() {
		AccountsPage accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
		
	}
	
}
 
