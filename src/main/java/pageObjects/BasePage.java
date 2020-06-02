package pageObjects;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BasePage {
	protected WebDriver driver;
	protected By pageTitle=By.tagName("h2");
	protected By message=By.id("flash");
	public BasePage(WebDriver driver){
		 this.driver=driver;
	 }
	public String getTitle(){
		return driver.findElement(pageTitle).getText(); 
	 }
	public String getMessage(){
		return driver.findElement(message).getText(); 
	 }
	public void switchToWindowByTitle(String title){
		String currentWindow=driver.getWindowHandle();
		Set<String> allWindows=driver.getWindowHandles();
		Iterator<String> myIterator=allWindows.iterator();
		while(myIterator.hasNext()){
			String nextWindow=myIterator.next().toString();
			if(!nextWindow.equals(currentWindow)){
				driver.switchTo().window(nextWindow);
				if(driver.getTitle().equals(title)){
					break;
				}
			}
		}
		
	}
}
