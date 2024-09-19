package PageObject;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponent.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> productname = driver.findElements(By.cssSelector(".card-body"));
	@FindBy(css=".card-body")
	List<WebElement> productnames;
	
	By productBy=By.cssSelector(".card-body");
	By toastappear=By.cssSelector("#toast-container");
	By spinner=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		waitforElementToAppear(productBy);
		return productnames;
	}
	
	public WebElement getProductName(String productName) {
		WebElement pro = productnames.stream()
				.filter(product -> product.findElement(By.tagName("h5")).getText().equals(productName)).findFirst()
				.orElse(null);
		return pro;
	}
	
	public void getproduct(String productName) {
		WebElement pro=getProductName(productName);
		pro.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		waitforElementToAppear(toastappear);
		waitinvisibleOfElement(spinner);
	}
	
	


}
