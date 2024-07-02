package page.objects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.DriverUtils;

public class ProductPage extends DriverUtils {
	WebDriver driver;

	@FindBy(xpath = "//*[contains(@id,'title_link')]")
	List<WebElement> requiredProducts;

	@FindBy(xpath = "//div[@id='shopping_cart_container']/a")
	WebElement cartButton;

	@FindBy(xpath = "//*[@id='inventory_container']//button")
	List<WebElement> addToCartButton;

	public ProductPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void addProduct() {
		String[] toAddProduct = { "Sauce Labs Backpack", "Sauce Labs Fleece Jacket", "Sauce Labs Bike Light" };
		int k = 0;
		// List<WebElement> requiredProducts =
		// driver.findElements(By.xpath("//*[contains(@id,'title_link')]"));

		for (int i = 0; i < requiredProducts.size(); i++) {
			String productsName = requiredProducts.get(i).getText();
			List<String> toAddProductList = Arrays.asList(toAddProduct);
			if (toAddProductList.contains(productsName)) {
				k++;
				addToCartButton.get(i).click();
				
			}
			if (k == toAddProduct.length) {
				break;
			}
		}

	}

	public void goToCartPage() {
		clickOn(cartButton);
		//cartButton.click();
	}

}
