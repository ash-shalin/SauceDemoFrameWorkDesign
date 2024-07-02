package SauceDemo.Framework;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAlone {
	public static void main(String[] args) {
		 
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.saucedemo.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
 
		// Invalid Login
		driver.findElement(By.id("user-name")).sendKeys("agent_alan");
		driver.findElement(By.id("password")).sendKeys("Cobra007");
		driver.findElement(By.id("login-button")).click();
		String errorMessage = driver.findElement(By.xpath("//h3")).getText();
		Assert.assertEquals(errorMessage, "Epic sadface: Username and password do not match any user in this service");
 
		// Valid Login
		System.out.println(driver.findElement(By.id("login_credentials")).getText());
		System.out.println(driver.findElement(By.className("login_password")).getText());
		driver.findElement(By.id("user-name")).clear();
		driver.findElement(By.id("user-name")).sendKeys("standard_user");
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys("secret_sauce");
		driver.findElement(By.id("login-button")).click();
 
		
		// Product Page
		String[] toAddProduct = {"Sauce Labs Backpack","Sauce Labs Fleece Jacket","Sauce Labs Bike Light"};
		int k=0;
		List<WebElement> requiredProducts = driver.findElements(By.xpath("//*[contains(@id,'title_link')]"));
		for (int i = 0; i < requiredProducts.size(); i++) {
			String productsName = requiredProducts.get(i).getText();
			List<String> toAddProductList = Arrays.asList(toAddProduct);
			if (toAddProductList.contains(productsName)) {
				k++;
				driver.findElements(By.xpath("//*[@id='inventory_container']//button")).get(i).click();
			}
			if (k == toAddProduct.length) {
				break;
			}
		}
		driver.findElement(By.xpath("//div[@id='shopping_cart_container']/a")).click();
		//driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
		//driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();
 
		
		// Cart Page
		String toRemoveproduct = "Sauce Labs Bike Light";
		int j = 0;
		List<WebElement> products = driver.findElements(By.xpath("//*[contains(@id,'title_link')]"));
		for (int z = 0; z < products.size(); z++) {
			String productName = products.get(z).getText();
			if (productName.contains(toRemoveproduct)) {
				j++;
				driver.findElements(By.xpath("//*[@id='cart_contents_container']//button")).get(z).click();
			}
			if (j == toRemoveproduct.length()) {
				break;
			}
		}
		
		driver.findElement(By.id("continue-shopping")).isDisplayed();
		driver.findElement(By.id("checkout")).click();
 
		
		//Checkout Page
		driver.findElement(By.id("first-name")).sendKeys("Alan");
		driver.findElement(By.id("last-name")).sendKeys("K George");
		driver.findElement(By.id("postal-code")).sendKeys("686580");
		driver.findElement(By.id("continue")).click();
		
		
		//Checkout Confirmation Page
		driver.findElement(By.id("cancel")).isDisplayed();
		driver.findElement(By.id("finish")).click();
		
		
		//Checkout Completion Page   
		String successMessage = driver.findElement(By.xpath("//h2")).getText();
		Assert.assertEquals(successMessage, "Thank you for your order!");
		driver.findElement(By.xpath("//*[@id='checkout_complete_container']//img")).isDisplayed();
		driver.findElement(By.id("back-to-products")).isDisplayed();
		
		
		driver.close();
 
	}
}
