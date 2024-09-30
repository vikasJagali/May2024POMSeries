package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.AccountsPage;

public class AccountsPageTest extends BaseTest {

	@BeforeClass
	public void accSetup() {
		 accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void accountPageTitleTest() {
		
		Assert.assertEquals(accountsPage.getAccountsPageTitle(), AppConstants.FRACTION_ACCOUNT_PAGE_TITLE);
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accountsPage.isLogoutLinkExist());
		
	}
	
	@Test
	public void searchProductTest() {
		resultsPage = accountsPage.doSearch("Macbook");
		Assert.assertTrue(resultsPage.verifySearchHeading());
	}
	
	@Test(dataProvider = "data")
	public void prductHeadingTest(String searchKey, String heading) {
		resultsPage = accountsPage.doSearch(searchKey);
		
		productInfoPage = resultsPage.selectProduct(heading);
		String productHeading = productInfoPage.getProductHeading(heading);
		Assert.assertEquals(productHeading, heading);
	}
	
	@DataProvider
	public Object [][] data(){
		
		return new Object[][] {
			{"MacBook","MacBook Pro"},
			{"imac","iMac"},
			{"samsung","Samsung Galaxy Tab 10.1"}
		};
		
	}
	
	
}


