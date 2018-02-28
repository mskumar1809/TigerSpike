package com.tigerspike.amazon.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Testing {

	public static void main(String[] args) throws InterruptedException {
		
		
		WebDriver driver = new ChromeDriver();
		By Brandslist = By.xpath("//html//ul[19]/div[1]");
		By SeeMoreBrands = By.xpath("//*[@id='leftNav']/ul[19]/li/span/a/span");
		
			
		
		List<String> TopFiveBrands = new ArrayList<String>();

		driver.get("https://www.amazon.in/s/ref=sr_ex_p_89_0?rh=n%3A976392031%2Cn%3A%21976393031%2Cn%3A1375424031&bbn=1375424031&ie=UTF8&qid=1519799890&ajr=2");
		String BrandList = driver.findElement(Brandslist).getText();
		String [] BrandListArray = BrandList.split("\n");
		
		for(int i=0; i<5; i++){
			TopFiveBrands.add(BrandListArray[i]);
		}
		System.out.println(TopFiveBrands);
		WebElement myelement = driver.findElement(SeeMoreBrands);
		JavascriptExecutor jse2 = (JavascriptExecutor)driver;
		jse2.executeScript("arguments[0].scrollIntoView()", myelement); 
		driver.findElement(SeeMoreBrands).click();
		
		for(int i =0;i<=TopFiveBrands.size()-1;i++) {
			String Brand = TopFiveBrands.get(i);
			System.out.println(TopFiveBrands.size());
			System.out.println(i + Brand);
			driver.findElement(By.xpath("//span[@class='refinementLink'][contains(text(), '"+Brand+"' )]")).click();
			Thread.sleep(3000);
			WebElement myelement2 = driver.findElement(By.xpath("//html//ul[@class='a-unordered-list a-nostyle a-vertical a-spacing-base']/li[2]/span[1]/a[1]/span[1]"));
			if(i == 4) {
				break;
			}
			jse2.executeScript("arguments[0].click();", myelement2);	
		}
		
		System.out.println("Came out Successfully");
		driver.quit();
	
	}

}
