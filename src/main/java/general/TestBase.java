package general;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class TestBase {
	public static final String USERNAME = "someName"; //should be replaced with correct name
	public static final String AUTOMATE_KEY = "someKey"; //should be replaced with correct key
	public static final String URL = "https://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub";
	public static final String nodeURL="http://192.168.1.57:5555/wd/hub";
	WebDriver driver;
	public WebDriver createWebDriver(String browser) throws MalformedURLException{
		switch (browser){
		case "chrome":
			driver=this.chromeWebDriver();
		    break;
		case "firefox":
			driver=this.firefoxWebDriver();
			break;
		case "browserStack":
			driver=this.browserStack();
			break;
		case "grid":
			driver=this.grid();
			break;
		default:
			driver=this.chromeWebDriver();
			break;
		}
		return driver;
	}
	private WebDriver chromeWebDriver(){
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver");
		ChromeOptions options=new ChromeOptions();
		options.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver=new ChromeDriver(options);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	private WebDriver firefoxWebDriver(){
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver");
		WebDriver driver=new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		return driver;
	}
	private WebDriver browserStack() throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
	    caps.setCapability("browser", "edge");
	    caps.setCapability("browser_version", "83.0 beta");
	    caps.setCapability("os", "Windows");
	    caps.setCapability("os_version", "10");
	    caps.setCapability("resolution", "1280x1024");
	    caps.setCapability("name", "Login Test");
        WebDriver driver = new RemoteWebDriver(new URL(URL), caps);
		return driver;
		
	}
	private WebDriver grid() throws MalformedURLException{
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability("browserName", "chrome");
		caps.setCapability("platformName","windows");
		WebDriver driver=new RemoteWebDriver(new URL(nodeURL), caps);
		return driver;
	}
	public void cleanUp(WebDriver driver){
		driver.close();
	}   
}
