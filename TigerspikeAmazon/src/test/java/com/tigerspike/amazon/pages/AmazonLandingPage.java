package com.tigerspike.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class AmazonLandingPage {
	
	private WebDriver driver;

	private static final By HoverToSignIn = By.xpath("//span[@class='nav-line-1'][contains(text(),'Hello. Sign in')]");
	private static final By SignInButton = By.xpath("//span[@class='nav-action-inner'][contains(text(),'Sign in')]");

public AmazonLandingPage(WebDriver driver) {
	
	this.driver = driver;
	
	}


public void navigateToLoginScreen() {
	Actions action = new Actions(driver);
	action.moveToElement(driver.findElement(HoverToSignIn)).build().perform();
	driver.findElement(SignInButton).click();
	
}


}
