package com.bilyoner.Stack;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AmazonSearchTest {

	public static final String USERNAME = "canberk1";
	public static final String AUTOMATE_KEY = "uBpCL9WpYURyKUxu5yqd";
	public static final String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY
			+ "@hub.browserstack.com/wd/hub";
	public static final String keyword="james cameron";
	public static final String searchBox = ".//*[@id='twotabsearchtextbox']";
	public static final String searchButton =".//input[@value='Go']";
	public static final String searchAssertion = ".//*[@id='s-result-count']/span/span";
	WebDriver driver;
	WebDriverWait wait;

	@BeforeClass
	@org.testng.annotations.Parameters(value = { "browser", "version",
			"platform" })
	public void setup(String browser, String version, String platform) {
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browser", browser);
		caps.setCapability("browser_version", version);
		caps.setCapability("os", platform);
		caps.setCapability("os_version", "7");
		caps.setCapability("browserstack.debug", "true");

		try {
			driver = new RemoteWebDriver(new URL(URL), caps);
			wait = new WebDriverWait(driver, 20);
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// driver = new FirefoxDriver();
		driver.get("http://www.amazon.com");

	}

	public WebElement findElement(String xpath) {

		return wait.until(ExpectedConditions.elementToBeClickable(By
				.xpath(xpath)));
	}
	
	public void ClickElement(String xpath){
		findElement(xpath).click();
	}
	
	public void FillElement(String xpath,String text){
		findElement(xpath).sendKeys(text);
	}
	
	public void AssertTrue(String element,String assertion){
		
		Assert.assertTrue(findElement(element).getText().contains(assertion));
	}

	@Test
	public void searchProduct() {

		FillElement(searchBox, keyword);
		ClickElement(searchButton);
		AssertTrue(searchAssertion, keyword);

	}

	@AfterClass
	public void cleanUp() {
		driver.quit();
	}

}
