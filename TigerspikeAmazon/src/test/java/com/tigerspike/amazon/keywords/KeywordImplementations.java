package com.tigerspike.amazon.keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.robotframework.javalib.annotation.ArgumentNames;
import org.robotframework.javalib.annotation.RobotKeyword;
import org.robotframework.javalib.annotation.RobotKeywords;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.Assert;

import com.tigerspike.amazon.pages.AmazonLandingPage;
import com.tigerspike.amazon.pages.AmazonLoginPage;
import com.tigerspike.amazon.pages.AmazonSearchPage;


@RobotKeywords

public class KeywordImplementations {
	static WebDriver driver;
	AmazonLandingPage objLandingPage;
	AmazonLoginPage objLoginPage;
	AmazonSearchPage objSearchPage;
	

	@RobotKeyword("Set Up")
	@ArgumentNames({"BROWSER"})
	public void setUp(String Browser) {
		
	if(Browser.equals("chrome")) {
	WebDriverManager.chromedriver().setup();
	 driver = new ChromeDriver();
	
	}
	 else if(Browser.equals("firefox")||Browser.equals("ff")) {
		WebDriverManager.firefoxdriver().setup();
		 driver = new FirefoxDriver(); 
	}
	 
	 objLandingPage = new AmazonLandingPage(driver);
	 objLoginPage = new AmazonLoginPage(driver);
	 objSearchPage = new AmazonSearchPage(driver);
	 
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
	
	@RobotKeyword("I log into Amazon")
	@ArgumentNames({ "EMAIL", "PASSWORD"})
	public void iLogIntoAmazon(String Email, String Password) throws InterruptedException {
		objLandingPage.navigateToLoginScreen();
		objLoginPage.loginWithValidCredentials(Email, Password);
	}

	@RobotKeyword("I select the category")
	@ArgumentNames({"CATEGORY"})
	public void iSelectTheCategory(String Category) {
		objSearchPage.selectCategory(Category);
		
	}
	
	@RobotKeyword("I select top five brands")
	public void iSelectTopFiveBrands() throws InterruptedException {
		objSearchPage.selectTopFiveBrands();
	}
	
	@RobotKeyword("I select desired product from results")
	public void iSelectDesiredProductFromResults() {
		objSearchPage.selectDesiredProduct();
	}

	@RobotKeyword("I add the product to the cart")
	public void iAddTheProductToTheCart() throws InterruptedException {
		objSearchPage.addProductToCart();
	}
	
	@RobotKeyword("Tear Down")
	public void tearDown() {
		driver.quit();
	}

}
