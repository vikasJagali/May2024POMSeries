package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();

	Properties prop;
	public static String isHighlight;

	public WebDriver initDriver(Properties prop) {

		String browserName = prop.getProperty("browser");
		System.out.println("Browsre naem is: " + browserName);

		isHighlight = prop.getProperty("highlight").trim();
		OptionsManager options = new OptionsManager(prop);

		switch (browserName) {
		case "chrome":
			// driver = new ChromeDriver(options.getBrowserOptions());
			tlDriver.set(new ChromeDriver(options.getBrowserOptions()));
			break;
		case "safari":
			System.out.println("inside safari switch case");
			driver = new SafariDriver();
			break;
		case "firefox":
			break;
		default:
			System.out.println(AppError.INVALID_BROWSER_MESSAGE + browserName);
			throw new BrowserException(AppError.INVALID_BROWSER_MESSAGE + browserName);

		}
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		getDriver().get(prop.getProperty("url"));

		return getDriver();
	}
	
	public static WebDriver getDriver(){
		return tlDriver.get();
	}

	public Properties initProperties() {
		prop = new Properties();
		FileInputStream fis = null;

		String env = System.getProperty("env");
		System.out.println("qa read env: " + env);

		// fis = new FileInputStream("src/test/resources/config/config.properties");
		try {

			System.out.println();

			if (env == null) {

				fis = new FileInputStream("src/test/resources/config/config.properties");

			} else {

				switch (env.toLowerCase().trim()) {

				case "qa":
					System.out.println("qa config switch case");
					fis = new FileInputStream("src/test/resources/config/config.properties");
					break;

				case "dev":
					fis = new FileInputStream("src/test/resources/config/dev.config.properties");
					break;

				case "stage":
					fis = new FileInputStream("src/test/resources/config/stage.config.properties");
					break;

				case "uat":
					fis = new FileInputStream("src/test/resources/config/uat.config.properties");
					break;

				case "prod":
					fis = new FileInputStream("src/test/resources/config/prod.config.properties");
					break;

				default:
					System.out.println("Please pass the right environment name");
					throw new FrameworkException("INVALID ENVIRONMENT NAME");
				}

			}

			prop.load(fis);
		} catch (FileNotFoundException e) {
			System.out.println(e.getStackTrace());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}

	public static  String getScreenshot(String methodName) {

		TakesScreenshot ts = (TakesScreenshot) getDriver();
		File srcFile = ts.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") + "/screenshot/" + methodName + "_" + System.currentTimeMillis() +".png";

		File destination = new File(path);

		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return path;
	}

}
