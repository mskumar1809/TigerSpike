package com.tigerspike.amazon.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonLoginPage {

	private WebDriver driver;

	private static final By Email = By.xpath("//input[@id='ap_email']");
	private static final By Continue = By.xpath("//input[@id='continue']");
	private static final By Password = By.xpath("//input[@id='ap_password']");
	private static final By Login = By.xpath("//input[@id='signInSubmit']");
	private static final By CustomUser = By.xpath("//span[@class='nav-shortened-name']");

	public AmazonLoginPage(WebDriver driver) {

		this.driver = driver;

	}

	public void loginWithValidCredentials(String email2, String password2) {

		driver.findElement(Email).sendKeys(email2);
		driver.findElement(Continue).click();
		driver.findElement(Password).sendKeys(password2);
		driver.findElement(Login).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		assertTrue(driver.findElement(CustomUser).getText().contains("Sampath"));
		System.out.println("Successfully Logged in with given Credentials");
	}

}
