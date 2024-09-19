package PageObject;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement usermail;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(xpath="//input[@type='submit']")
	WebElement submitbutton;
	
	@FindBy(css="div[aria-label='Incorrect email or password.']")
	WebElement errorMessage;
	
	//div[aria-label='Incorrect email or password.']
	
	//login into application
	public ProductCatalogue loginApplication(String email,String pwd) {
		usermail.sendKeys(email);
		password.sendKeys(pwd);
		submitbutton.click();
		ProductCatalogue pc=new ProductCatalogue(driver);
		return pc;
	}
	
	//goto the url
	public void Goto() {
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String getErorMsg() {
		waitofWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}


}
