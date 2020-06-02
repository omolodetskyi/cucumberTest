package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage{

	private By message=By.cssSelector("#flash");
	private By userNameField=By.cssSelector("#username");
	private By passwordField=By.cssSelector("#password");
	private By loginButton=By.cssSelector(".radius");
	 public LoginPage(WebDriver driver){
		 super(driver);
	 }
	 
	 public SecureAreaPage enterUserNamePassword(String username,String password){
	   driver.findElement(userNameField).sendKeys(username);
	   driver.findElement(passwordField).sendKeys(password);
	   driver.findElement(loginButton).click();
	   return new SecureAreaPage(driver);
	 }
}
