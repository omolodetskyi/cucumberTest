package tests;


import org.testng.annotations.Test;


import org.testng.annotations.Test;

import pageObjects.LoginPage;
import pageObjects.SecureAreaPage;
import pageObjects.WelcomePage;


import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class LoginTest_wrongCreds extends general.TestBase{
	WebDriver driver;
	WelcomePage welcomePage;
	LoginPage loginPage;
	SecureAreaPage secureAreaPage;
	
  @Test(description="negative check for Login page")
  @Parameters({"userName","password","errorMessage"})
  public void loginWithWrongCreds(String userName,String password, String errorMessage) {
	  openWelcomePage();
	  checkWelcomePageTitle();
	  clickFormAuthenticationLink();
	  checkLoginTitle();
	  enterUserNamePassword(userName, password);
	  checkErrorMessage(errorMessage);
  }
 
  private void openWelcomePage(){
	  welcomePage.openPage();
	 
  }
 
  private void checkWelcomePageTitle(){
	  String actualWelcomeTitle=welcomePage.getTitle();
	  String expectedWelcomeTitle="Available Examples";
	  Assert.assertEquals(actualWelcomeTitle, expectedWelcomeTitle); 
  }
 
  private void clickFormAuthenticationLink(){
	  loginPage=welcomePage.clickFormAuthenticationLink();
  }
  
  private void checkLoginTitle(){
	  String actualLoginTitle=loginPage.getTitle();
	  String expectedLoginTitle="Login Page";
	  Assert.assertEquals(actualLoginTitle, expectedLoginTitle);
  }
 
 
 private void enterUserNamePassword(String userName, String password){
	
	  secureAreaPage=loginPage.enterUserNamePassword(userName, password);
 }

 private void checkErrorMessage(String errorMessage){
	 String actualLoginPageMsg=loginPage.getMessage();
	  String expectedLoginPageMsg=errorMessage;
	  Assert.assertEquals(actualLoginPageMsg.contains(expectedLoginPageMsg),true); 
 }
  @BeforeTest
  @Parameters({"testName","browser"})
  public void beforeTest(String testName, String browser) throws MalformedURLException {
	
	  driver=createWebDriver(browser); 
	  welcomePage=new WelcomePage(driver);
  }
  @AfterTest
  public void closeBrowser(){
	cleanUp(driver);  
  }

}
