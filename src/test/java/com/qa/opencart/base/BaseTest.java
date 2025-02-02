package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.ResultsPage;

public class BaseTest {
	
	DriverFactory df ;
	public WebDriver driver;
	protected LoginPage loginPage;
	protected AccountsPage accountsPage;
	protected RegisterPage registerPage;
	protected ResultsPage resultsPage;
	protected ProductInfoPage productInfoPage;
	
	protected Properties prop;
	protected SoftAssert softAssert;
	
	
	@Parameters({"browser"})
	@BeforeTest
	public void setup(@Optional("chrome") String browserName) {
		System.out.println("setup called successfully");
		df = new DriverFactory();
		
		prop = df.initProperties();
		
		if(browserName!=null) {
			prop.setProperty("browser", browserName);
		}
		
		System.out.println("browser from properties file: " + prop.getProperty("browser"));
		
		driver = df.initDriver(prop);
		
		System.out.println("diver in base test: " +driver);
		
		loginPage = new LoginPage(driver);
		softAssert = new SoftAssert();
	}
	
	@AfterTest
	public void teardown() {
		driver.quit();
	}

}
