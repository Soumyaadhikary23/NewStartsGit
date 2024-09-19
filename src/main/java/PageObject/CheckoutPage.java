package PageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".form-group .input")
	WebElement countryBox;
	
	@FindBy(css=".list-group-item")
	List<WebElement> countriesName;
	
	@FindBy(css=".action__submit")
	WebElement submit;
	
	public void entrycountryName(String counttry) {
		countryBox.sendKeys(counttry);
	}
	
	public void selectCountry() {
		
		countriesName.stream().filter(country -> country.getText().equalsIgnoreCase("India")).findFirst()
				.ifPresent(country -> country.click());
	}
	
	public orderConformPage submitClick() {
		submit.click();
		return new orderConformPage(driver);
	}
}
