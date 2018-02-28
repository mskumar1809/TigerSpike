package com.tigerspike.amazon.pages;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;

	public class AmazonLoginPage {
		
		private WebDriver driver;

		private static final By Email = By.xpath("//input[@id='ap_email']");
		private static final By Continue = By.xpath("//input[@id='continue']");
		private static final By Password = By.xpath("//input[@id='ap_password']");
		private static final By Login = By.xpath("//input[@id='signInSubmit']");

	public AmazonLoginPage(WebDriver driver) {
		
		this.driver = driver;
		
		}

	public void loginWithValidCredentials(String email2, String password2) {
		
		driver.findElement(Email).sendKeys(email2);
		driver.findElement(Continue).click();	
		driver.findElement(Password).sendKeys(password2);
		driver.findElement(Login).click();
	}


	}
