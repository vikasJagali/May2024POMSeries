package com.qa.opencart.tests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void registerSetup() {
		
		//accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		registerPage = loginPage.doRegistration();
	}
	
	@Test
	public void averifyRegisterPageDisplayed() {
		Assert.assertTrue(registerPage.verifyUserOnRegestrationPage());
	}
	
	@Test
	public void registerPageTitleTest() {
		registerPage.getPageTitle();
	
		Assert.assertEquals(registerPage.getPageTitle(), AppConstants.FRACTION_REGISTER_PAGE_TITLE);
	}
	
	@DataProvider
	public Object[][] getRegisterData() {
		return ExcelUtil.getExcelData(AppConstants.REGISTER_SHEET_NAME);
	}
	
	@Test(dataProvider="getRegisterData")
	public void registrationSuccesssfulTest(String fName, String lName, String telephone, String password) {
		accountsPage = registerPage.doRegisteration(fName,lName, telephone,
				password, password);
		
		Assert.assertTrue(accountsPage.isRegistrationSuccessful());
		//boolean isRegisterSuccess = accountsPage.isRegistrationSuccessful();
		//System.out.println("is success: " +isRegisterSuccess);
		
		accountsPage.logoutFromRegisterPage();
		loginPage.doRegistration();		
		
	}
	
	

}
