package com.tigerspike.amazon.pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class AmazonProductCheckOut {
	private WebDriver driver;
	private static final By noCoverangeButton = By.xpath("//button[@id='siNoCoverage-announce']");
	private static final By proceedToCheckoutButton = By.xpath("//a[@id=\"hlb-ptc-btn-native\"]");
	
	
	public AmazonProductCheckOut(WebDriver driver) {
		this.driver = driver;
		
	}
	
	public void addProductToCart() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Thread.sleep(2000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(noCoverangeButton)));
            driver.findElement(noCoverangeButton).click();
        }
        catch (org.openqa.selenium.NoSuchElementException e){
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(proceedToCheckoutButton)));
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(proceedToCheckoutButton)));
        driver.findElement(proceedToCheckoutButton).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        Thread.sleep(2000);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(noCoverangeButton)));
            driver.findElement(noCoverangeButton).click();
        }
        catch (org.openqa.selenium.NoSuchElementException e){
        	wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[@id=\"address-book-entry-0\"]/div[2]/span/a"))));
        }
        
        driver.findElement(By.xpath("//*[@id=\"address-book-entry-0\"]/div[2]/span/a")).click();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//*[@id=\"shippingOptionFormId\"]/div[3]/div/div/span[1]/span/input")).click();
            }
	

}
