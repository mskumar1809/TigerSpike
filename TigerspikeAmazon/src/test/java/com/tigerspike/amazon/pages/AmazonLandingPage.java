package com.tigerspike.amazon.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

public class AmazonLandingPage {
	
	private WebDriver driver;

	private static final By HoverToSignIn = By.xpath("//span[@class='nav-line-1'][contains(text(),'Hello. Sign in')]");
	private static final By SignInButton = By.xpath("//span[@class='nav-action-inner'][contains(text(),'Sign in')]");

public AmazonLandingPage(WebDriver driver) {
	this.driver = driver;
	}

public void navigateToLoginScreen() throws InterruptedException {
	Actions action = new Actions(driver);
	Thread.sleep(4000);
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	action.moveToElement(driver.findElement(HoverToSignIn)).build().perform();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	Thread.sleep(4000);
	driver.findElement(SignInButton).click();
	assertTrue(driver.getTitle().contains("Sign In"));
	System.out.println("Navigated to Login Screen");
	}

public void navigateToLandingPage(String url) {
	driver.get(url);
	assertTrue(driver.getCurrentUrl().contains(url));
	System.out.println("Successfully Launched Amazon Website");
	maximizeBrowser();
	}

public void maximizeBrowser() {
	driver.manage().window().maximize();
	}

}
