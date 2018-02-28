package com.tigerspike.amazon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonSearchPage {
	
	private WebDriver driver;

	private static final By AllCategories = By.xpath("//a[@id='nav-link-shopall']");
	private static final By Laptops = By.xpath("//a[@class='nav_a'][contains(text(),'Laptops')]");
	

	public AmazonSearchPage(WebDriver driver) {
		this.driver = driver;
		
	}

	public void selectCategory(String category) {
		driver.findElement(AllCategories).click();
		driver.findElement(Laptops).click();
		
	}

}
