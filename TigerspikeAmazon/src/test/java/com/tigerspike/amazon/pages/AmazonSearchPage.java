package com.tigerspike.amazon.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AmazonSearchPage {
	
	private WebDriver driver;
	private static final By AllCategories = By.xpath("//a[@id='nav-link-shopall']");
	private static final By Laptops = By.xpath("//a[@class='nav_a'][contains(text(),'Laptops')]");
	private static final By Brandslist = By.xpath("//html//ul[19]/div[1]");
	private static final By SeeMoreBrands = By.xpath("//*[@id='leftNav']/ul[19]/li/span/a/span");
	private static final By SeeMoreBrands2 = By.xpath("//html//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-base']/li[2]/span[1]/a[1]/span[1]");
	private static final By DesiredProduct = By.xpath("//*[@id='result_0']/div/div[3]/div[1]/a/h2");
	private static final By noCoverangeButton = By.xpath("//button[@id='siNoCoverage-announce']");
	private static final By 	proceedToCheckoutButton = By.xpath("//a[@id=\"hlb-ptc-btn-native\"]");
	public AmazonSearchPage(WebDriver driver) {
		this.driver = driver;
		
	}

	public void selectCategory(String category) {
		driver.findElement(AllCategories).click();
		driver.findElement(Laptops).click();	
	}
	
	public void selectTopFiveBrands() throws InterruptedException {
		List<String> TopFiveBrands = new ArrayList<String>();
		String BrandList = driver.findElement(Brandslist).getText();
		String [] BrandListArray = BrandList.split("\n");
		
		for(int i=0; i<5; i++){
			TopFiveBrands.add(BrandListArray[i]);
		}
		System.out.println(TopFiveBrands);
		WebElement myelement = driver.findElement(SeeMoreBrands);
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].click();", myelement); 

		
		for(int i =0;i<=TopFiveBrands.size()-1;i++) {
			String Brand = TopFiveBrands.get(i);
			Thread.sleep(4000);
			driver.findElement(By.xpath("//span[@class='refinementLink'][contains(text(), '"+Brand+"' )]")).click();
			WebElement myelement2 = driver.findElement(SeeMoreBrands2);
			if(i == 4) {
				break;
			}
			jse2.executeScript("arguments[0].click();", myelement2);	
		}
		
		
	}
	public void selectDesiredProduct() {
		driver.findElement(DesiredProduct).click();
	    ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
	    driver.switchTo().window(tabs2.get(1));
	    
	}

	public void addProductToCart() throws InterruptedException {
		Thread.sleep(8000);
		driver.findElement(By.xpath("//*[@id=\"add-to-cart-button\"]")).click();
		Thread.sleep(5000);
		WebDriverWait wait = new WebDriverWait(driver, 10);
        try {
            wait.until(ExpectedConditions.visibilityOf(driver.findElement(noCoverangeButton)));
            driver.findElement(noCoverangeButton).click();
        }
        catch (org.openqa.selenium.TimeoutException e){
            driver.findElement(By.xpath("//*[@id=\"a-popover-6\"]/div/div[1]/button")).click();
        }
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(proceedToCheckoutButton)));
        driver.findElement(proceedToCheckoutButton).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"address-book-entry-0\"]/div[2]/span/a")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@id=\"shippingOptionFormId\"]/div[3]/div/div/span[1]/span/input")).click();
            }
	
		
	}	
	

