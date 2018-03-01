package com.tigerspike.amazon.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonSearchPage {
	
	private WebDriver driver;
	private static final By AllCategories = By.xpath("//a[@id='nav-link-shopall']");
	private static final By Laptops = By.xpath("//a[@class='nav_a'][contains(text(),'Laptops')]");
	private static final By Brandslist = By.xpath("//html//ul[19]/div[1]");
	private static final By SeeMoreBrands = By.xpath("//*[@id='leftNav']/ul[19]/li/span/a/span");
	private static final By SeeMoreBrands2 = By.xpath("//html//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-base']/li[2]/span[1]/a[1]/span[1]");
	private static final By DesiredProduct = By.xpath("//*[@id='result_0']/div/div[3]/div[1]/a/h2");

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

		
		for(int i=0;i<=TopFiveBrands.size()-1;i++) {
			String Brand = TopFiveBrands.get(i);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
}	
	

