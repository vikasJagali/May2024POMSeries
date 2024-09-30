package com.qa.opencart.tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductInfoPageTest  extends BaseTest{
	@BeforeClass
	public void productInfoSetup() {
		accountsPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void productInfoTest() {
		
		resultsPage = accountsPage.doSearch("MacBook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		
		Map<String, String> productMap = productInfoPage.getProductData();
		
		softAssert.assertEquals(productMap.get("Brand"), "Apple");
		softAssert.assertEquals(productMap.get("Product Code"), "Product 18");
		softAssert.assertEquals(productMap.get("Reward Points"),"800");
		softAssert.assertEquals(productMap.get("Availability"),"In Stock");
		softAssert.assertEquals(productMap.get("productPrice"),"$2,000.00");
		softAssert.assertEquals(productMap.get("extPrice"),"$2,000.00");
		
		softAssert.assertAll();
		
	}
	
	@DataProvider
	public Object[][] imageCountDataProvider() {
		return new Object[][] {
			{"Macbook","MacBook Pro",4},
			{"Samsung","Samsung Galaxy Tab 10.1",7},
			{"iphone","iPhone",6},
			
		};
	}
	
	@Test(dataProvider="imageCountDataProvider")
	public void iamgeCountTest(String searchKey, String productHeading, int imageCount) {
		resultsPage = accountsPage.doSearch(searchKey);
		productInfoPage = resultsPage.selectProduct(productHeading);
		int actualImageCount = productInfoPage.getImageCount();
		Assert.assertEquals(actualImageCount, imageCount);
	}

}
