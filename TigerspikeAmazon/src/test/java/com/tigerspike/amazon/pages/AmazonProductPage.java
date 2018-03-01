package com.tigerspike.amazon.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonProductPage {
	private WebDriver driver;
	private static final By NoCoverangeButton = By.xpath("//button[@id='siNoCoverage-announce']");
	private static final By ProceedToCheckoutButton = By.xpath("//a[@id=\"hlb-ptc-btn-native\"]");
	private static final By AddToCart = By.xpath("//*[@id=\"add-to-cart-button\"]");

	public AmazonProductPage(WebDriver driver) {
		this.driver = driver;

	}

	public void addProductToCart() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(AddToCart).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000); // Forced step to handle the Insurance Skip step
		WebDriverWait wait = new WebDriverWait(driver, 10);
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(NoCoverangeButton)));
			driver.findElement(NoCoverangeButton).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(ProceedToCheckoutButton)));
		}
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(ProceedToCheckoutButton)));
		assertTrue(driver.getTitle().contains("Shopping Cart"), "Page Title does not have Shopping Cart");
		System.out.println("Successfully added the product to cart");
	}

}
