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
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	action.moveToElement(driver.findElement(HoverToSignIn)).build().perform();
	driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	driver.findElement(SignInButton).click();
	assertTrue(driver.getTitle().contains("Sign In"));
	}

public void navigateToLandingPage(String url) {
	driver.get(url);
	Assert.assertTrue(driver.getCurrentUrl().contains(url));
	maximizeBrowser();
	}

public void maximizeBrowser() {
	driver.manage().window().maximize();
	}

}
