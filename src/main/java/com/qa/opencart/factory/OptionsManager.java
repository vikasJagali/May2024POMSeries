package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co = new ChromeOptions();

	public OptionsManager(Properties prop) {
		this.prop = prop;
	}

	public ChromeOptions getBrowserOptions() {

		if (Boolean.parseBoolean(prop.getProperty("headless").trim()))
			co.addArguments("--headless");

		if (Boolean.parseBoolean(prop.getProperty("incognito").trim()))
			co.addArguments("--incognito");

		return co;
	}

}
