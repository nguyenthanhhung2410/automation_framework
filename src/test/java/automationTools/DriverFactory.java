package automationTools;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;

public class DriverFactory {
	public  static WebDriver driver = null;
	public  static WebDriver getDriver(String browserType, String remote, String version, String seleniumHub) throws Exception {
//		System.setProperty("webdriver.gecko.driver", "geckodriver");
//     	System.setProperty("webdriver.chrome.driver", "chromedriver");
//		String browserType = System.getProperty("browser","chrome").toLowerCase();
//		String remote = System.getProperty("remote","false").toLowerCase();
//		String version = System.getProperty("version","any").toLowerCase();
//		String seleniumHub = System.getProperty("seleniumHub","none").toLowerCase();
		
		if (remote.equals("true")) {
			URL SeleniumGridURL = null;
			try {
				SeleniumGridURL = new URL(seleniumHub);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DesiredCapabilities capabilities = new DesiredCapabilities();
			switch (browserType) {
			case "chrome":
				capabilities = initChrome();
				break;
			case "firefox":
				capabilities = initFirefox();
				break;
			case "ie":
				capabilities = initIE();
				break;
			default:
				capabilities = initChrome();
				break;
			}
	        capabilities.setBrowserName(browserType);
//	        capabilities.setPlatform(Platform.WINDOWS);
	        //capabilities.setVersion(version);
	        driver = new RemoteWebDriver(SeleniumGridURL, capabilities);	
		}
		else {
			if (browserType.equals("chrome")) {
				if(driver == null) driver = new ChromeDriver();
			}
			else {
				if(driver == null) driver = new FirefoxDriver();
			}
		}

		return driver;
	}
	@AfterMethod
	public void cleanUp() {
		if(driver != null){
			System.out.println("FACTORY QUIT DRIVER");
			driver.quit();
			driver = null;
		}
	}
	
	//Init Browser
	public static DesiredCapabilities initChrome() throws Exception {
        DesiredCapabilities capabilities = DesiredCapabilities.chrome();
        capabilities.setCapability("chrome.switches", Arrays.asList("--no-default-browser-check"));
        capabilities.setCapability("chrome.switches", Arrays.asList("--disable-extensions"));
        HashMap<String, String> chromePreferences = new HashMap<String, String>();
        chromePreferences.put("profile.password_manager_enabled", "false");
        capabilities.setCapability("chrome.prefs", chromePreferences);
        return capabilities;
	}
	
	public static DesiredCapabilities initFirefox() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities().firefox();
        ProfilesIni profilesIni = new ProfilesIni();
        FirefoxProfile profile = profilesIni.getProfile("John");
        profile.setPreference("media.navigator.streams.fake", true);
        profile.setPreference("media.navigator.permission.disabled", true);
        profile.setPreference("media.navigator.enabled", true);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);
        return capabilities;
	}
	
	public static DesiredCapabilities initIE() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability("requireWindowFocus", true);
        capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        return capabilities;
	}
	
	public static DesiredCapabilities initEdge() throws Exception {
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ForSeleniumServer.ENSURING_CLEAN_SESSION, true);
		//capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
        capabilities.setCapability("requireWindowFocus", true);
        //capabilities.setCapability(InternetExplorerDriver.NATIVE_EVENTS, true);
        return capabilities;
	}
}
