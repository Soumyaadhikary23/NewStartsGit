package PageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CartPage extends AbstractComponent {
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartWrap h3")
	List<WebElement> cartProduct;
	
	@FindBy(css=".totalRow button")
	WebElement checkoutButton;
	
	public boolean cartProductMatch(String product) {
		boolean match = cartProduct.stream().anyMatch(cartP -> cartP.getText().equals(product));
		return match;
	}
	
	public CheckoutPage checkout() {
		checkoutButton.click();
		return new CheckoutPage(driver);
	}
}
