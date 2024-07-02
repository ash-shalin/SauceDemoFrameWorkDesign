package page.objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import base.DriverUtils;
import base.SeleniumHelper;

public class CheckoutOverviewPage extends DriverUtils {

	WebDriver driver;
	@FindBy(id = "cancel")
	WebElement cancelButton;

	@FindBy(id = "finish")
	WebElement finishButton;

	public CheckoutOverviewPage(WebDriver driver) {
		//super(driver);
		this.driver = driver;

		PageFactory.initElements(driver, this);
	}

	public void confirmCheckoutDetails() {

		//cancelButton.isDisplayed();
		SeleniumHelper.waitForElement(cancelButton);
		isElementPresent(driver, cancelButton);
		clickOn(finishButton);
	}
}
