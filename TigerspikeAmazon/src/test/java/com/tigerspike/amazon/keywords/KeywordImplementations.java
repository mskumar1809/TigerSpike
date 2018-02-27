package com.tigerspike.amazon.keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import org.testng.Assert;

import com.tigerspike.amazon.pages.AmazonLandingPage;;

@RobotKeywords

public class KeywordImplementations {
	static WebDriver driver;
	AmazonLandingPage objLandingPage;

	

	@RobotKeyword("Set Up")
	//@ArgumentNames({"BROWSER"})
	public void setUp() {
		
//	if(Browser.equals("chrome")) {
//	 driver = new ChromeDriver();
//	
//	}
	// else if(Browser.equals("firefox")||Browser.equals("ff")) {
		System.setProperty("webdriver.gecko.driver", "/Users/sampathkumar/Documents/geckodriver");
		 driver = new FirefoxDriver(); 
	// }
	 
	 objLandingPage = new AmazonLandingPage(driver);
	}
	
	@RobotKeyword("I am at the Amazon Home Screen")
	@ArgumentNames({ "URL"})
	public void iAmAtTheAmazonHomeScreen(String url) {
		driver.get(url);
		Assert.assertTrue(driver.getCurrentUrl().contains(url));
		maximizeBrowser();

	}
	
	public void maximizeBrowser() {
		driver.manage().window().maximize();
	}



	@RobotKeyword("Tear Down")
	public void tearDown() {
		driver.quit();
	}

}
