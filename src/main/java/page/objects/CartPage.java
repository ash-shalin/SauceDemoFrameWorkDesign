package page.objects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.DriverUtils;

public class CartPage extends DriverUtils {
	WebDriver driver;

	@FindBy(xpath = "//*[contains(@id,'title_link')]")
	List<WebElement> products;

	@FindBy(xpath = "//*[@id='cart_contents_container']//button")
	List<WebElement> removeButton;
	
	@FindBy(id="checkout")
	WebElement checkoutButton;
	
	@FindBy(id="continue-shopping")
	WebElement continueShoppingButton;
	
	

	public CartPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void removeProductFromCart() {
		String toRemoveproduct = "Sauce Labs Bike Light";
		int j = 0;

		for (int z = 0; z < products.size(); z++) {
			String productName = products.get(z).getText();
			if (productName.contains(toRemoveproduct)) {
				j++;
				removeButton.get(z).click();
			}
			if (j == toRemoveproduct.length()) {
				break;
			}
		}

	}

	public void goToCheckoutPage() {
		
		isElementPresent(driver, continueShoppingButton);
		//driver.findElement(By.id("continue-shopping")).isDisplayed();
		clickOn(checkoutButton);
	}

}
