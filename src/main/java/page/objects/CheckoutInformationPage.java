package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.DriverUtils;

public class CheckoutInformationPage extends DriverUtils {

	WebDriver driver;
	@FindBy(id = "first-name")
	WebElement firstName;

	@FindBy(id = "last-name")
	WebElement lastName;

	@FindBy(id = "postal-code")
	WebElement postalCode;

	@FindBy(id = "continue")
	WebElement continueButton;

	public CheckoutInformationPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void submitCheckoutDetails() {

		sendtext(firstName, "Ashik");
		sendtext(lastName, "Shalin");
		sendtext(postalCode, "678765");
		clickOn(continueButton);

	}

}
