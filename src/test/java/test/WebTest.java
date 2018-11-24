package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

//user define
import automationTools.DriverFactory;
import ultilities.ResultLog;

public class WebTest extends DriverFactory {
	public static ResultLog logger = new ResultLog("WebTest - ");
	WebDriver driver;

	public void provideInto(WebDriver driver, String label, String text) {
		driver.findElement(By.xpath("//*[text()='" + label + "']/following::input[1]")).sendKeys(text);
	}
	
	public void provideInto(WebDriver driver, String label, org.openqa.selenium.Keys key) {
		driver.findElement(By.xpath("//*[text()='" + label + "']/following::input[1]")).sendKeys(key);
	}
	
	//@Test
	public void test_buy_an_item() throws Exception {
		String browserType = System.getProperty("browser","chrome").toLowerCase();
		String remote = System.getProperty("remote","false").toLowerCase();
		String version = System.getProperty("version","any").toLowerCase();
		String seleniumHub = System.getProperty("seleniumHub","none").toLowerCase();
		driver = getDriver(browserType, remote, version, seleniumHub);
		WebDriverWait wait = new WebDriverWait(driver, 10);
		driver.get("http://store.demoqa.com/");
		logger.info("http://store.demoqa.com/");
		Reporter.log("http://store.demoqa.com/");
		driver.findElement(By.name("s")).sendKeys("iPhone 5");
		driver.findElement(By.name("s")).sendKeys(Keys.ENTER);
		logger.info("Search for iPhone 5");
		Reporter.log("Search for iPhone 5");
		Thread.sleep(7000);
		String price1 = driver.findElement(By.xpath("//*[@id='grid_view_products_page_container']/div/div[1]/div[2]/div/p/span")).getText();
		driver.findElement(By.xpath("//*[@id='grid_view_products_page_container']/div/div[1]/div[3]/form/div/div[1]/span/input")).click();
		logger.info("The price of iPhone 5 is: " + price1);
		Reporter.log("The price of iPhone 5 is: " + price1);
		logger.info("Add the iPhone5 to cart");
		Reporter.log("Add the iPhone5 to cart");
		Thread.sleep(4000);
		String count = driver.findElement(By.xpath("//*/em[1]")).getText().trim();
		Assert.assertTrue(count.equals("1"));
		logger.info("Verify the item in cart should be 1");
		Reporter.log("Verify the item in cart should be 1");
		driver.findElement(By.xpath("//*[@id='header_cart']/a")).click();
		Thread.sleep(5000);
		String price2 = driver.findElement(By.xpath("//*[@id='checkout_page_container']/div[1]/table/tbody/tr[2]/td[5]/span/span")).getText() + "1";
		logger.info("Verify the total price should be " + price1);
		Reporter.log("Verify the total price should be " + price1);
		Assert.assertTrue(price1.equals(price2),"Expect '" + price1 + "' but found '" + price2 +"'");
	}

}
