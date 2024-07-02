package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import base.DriverUtils;

public class CheckoutCompletionPage extends DriverUtils {

	WebDriver driver;

	@FindBy(xpath = "//h2")
	WebElement message;

	@FindBy(xpath = "//*[@id='checkout_complete_container']//img")
	WebElement successImage;

	@FindBy(id = "back-to-products")
	WebElement backToProducts;

	public CheckoutCompletionPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void orderPlaced() {

		String successMessage = message.getText();
		Assert.assertEquals(successMessage, "Thank you for your order!");
		isElementPresent(driver, successImage);
		isElementPresent(driver, backToProducts);
		
//		successImage.isDisplayed();
//		backToProducts.isDisplayed();

	}

	public String successMessage() {

		return message.getText();
	}

}
