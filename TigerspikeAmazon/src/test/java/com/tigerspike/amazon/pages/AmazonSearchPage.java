package com.tigerspike.amazon.pages;

import static org.testng.Assert.assertTrue;

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
	private static final By SeeMoreBrands2 = By.xpath(
			"//html//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-base']/li[2]/span[1]/a[1]/span[1]");
	private static final By DesiredProduct = By.xpath("//*[@id='result_0']/div/div[3]/div[1]/a/h2");
	private static final By AssertionText = By.xpath("//span[@class='a-color-state a-text-bold']");

	public AmazonSearchPage(WebDriver driver) {
		this.driver = driver;
	}

	public void selectCategory(String category) {
		driver.findElement(AllCategories).click();
		assertTrue(driver.getTitle().contains("Departments :"), "Didn't Navigate to All Categories");
		driver.findElement(Laptops).click();
		assertTrue(driver.getTitle().contains("Laptop Prices in India:"), "Didn't Navigate to Laptops Page");
		System.out.println("Successfully Selected and Navigated to Laptops Category Page");
	}

	public void selectTopFiveBrands() throws InterruptedException {
		List<String> TopFiveBrands = new ArrayList<String>();
		String BrandList = driver.findElement(Brandslist).getText();
		String[] BrandListArray = BrandList.split("\n");

		for (int i = 0; i < 5; i++) {
			TopFiveBrands.add(BrandListArray[i]);
		}
		WebElement myelement = driver.findElement(SeeMoreBrands);
		JavascriptExecutor jse2 = (JavascriptExecutor) driver;
		jse2.executeScript("arguments[0].click();", myelement);

		for (int i = 0; i <= TopFiveBrands.size() - 1; i++) {
			String Brand = TopFiveBrands.get(i);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//span[@class='refinementLink'][contains(text(), '" + Brand + "' )]")).click();
			WebElement myelement2 = driver.findElement(SeeMoreBrands2);
			if (i == 4) {
				break;
			}
			jse2.executeScript("arguments[0].click();", myelement2);
		}
		assertTrue(driver.getTitle().contains("Brands: 5 selected"));
		assertTrue(driver.findElement(AssertionText).getText().contains("5 selected"));
		System.out.println("Successfully Selected top 5 brands of Apple, Microsoft, HP, Lenovo, Dell");
	}

	public void selectDesiredProduct() throws InterruptedException {
		driver.findElement(DesiredProduct).click();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		ArrayList<String> tabs2 = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(tabs2.get(1));
		Thread.sleep(3000);// Forced step to handle switching of tabs in Firefox
							// In Chrome the execution works fine
							// without this step.
		assertTrue(driver.getTitle().contains("Apple"));
		System.out.println("Successfully Selected Desired Product");

	}
}
