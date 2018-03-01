package com.tigerspike.amazon.pages;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonCheckoutPage {
	private WebDriver driver;
	private static final By NoCoverageButton = By.xpath("//button[@id='siNoCoverage-announce']");
	private static final By ProceedToCheckoutButton = By.xpath("//a[@id=\"hlb-ptc-btn-native\"]");
	private static final By DeliveryAddress = By.xpath("//*[@id=\"address-book-entry-0\"]/div[2]/span/a");
	private static final By ShippingOptions = By
			.xpath("//*[@id=\"shippingOptionFormId\"]/div[3]/div/div/span[1]/span/input");
	
	private static final By PaymentAssertion = By.xpath("//*[@id='checkoutDisplayPage']/div[1]/div[2]/div[2]/div[2]/h1");
	public AmazonCheckoutPage(WebDriver driver) {
		this.driver = driver;

	}

	public void productCheckout() throws InterruptedException {
		driver.findElement(ProceedToCheckoutButton).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		Thread.sleep(2000); // Forced step to handle the Warranty Skip step
		try {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(NoCoverageButton)));
			driver.findElement(NoCoverageButton).click();
		} catch (org.openqa.selenium.NoSuchElementException e) {
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(DeliveryAddress)));
		}
		assertTrue(driver.findElement(DeliveryAddress).isDisplayed(),
				"Delivery Address screen didn't load after checkout");
		System.out.println("Successfully proceeded with Checkout");
	}

	public void choosingDeliveryAddress() {
		driver.findElement(DeliveryAddress).click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.SECONDS);
		assertTrue(driver.getTitle().contains("Shipping Options"));
		System.out.println("Successfully selected the desired Delivery Address");
	}

	public void navigateToPayment() {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		driver.findElement(ShippingOptions).click();
		System.out.println("Successfully selected the Shipping options and navigated to payment page");
	}
	
	public void assertPaymentPage() {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertTrue(driver.findElement(PaymentAssertion).getText().contains("payment"));
		System.out.println("Successfully asserted payment page");
	}
}
