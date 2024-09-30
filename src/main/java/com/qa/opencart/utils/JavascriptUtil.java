package com.qa.opencart.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtil {

	private JavascriptExecutor js;

	public JavascriptUtil(WebDriver driver) {
		js = (JavascriptExecutor) driver;
	}

	public WebElement highlightElement(WebElement element) {
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		return element;
	}

}
