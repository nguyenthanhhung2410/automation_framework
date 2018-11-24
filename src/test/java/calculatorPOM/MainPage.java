package calculatorPOM;
import io.appium.java_client.android.AndroidDriver;
import ultilities.ResultLog;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;

public class MainPage {
	public static ResultLog logger = new ResultLog("MainPage - ");
	// Declare Page Objects
	private static By PLUS_BTN 		= By.id("com.android.calculator2:id/op_add");
	private static By MINUS_BTN 	= By.id("com.android.calculator2:id/op_sub");
	private static By MULTIPLY_BTN 	= By.id("com.android.calculator2:id/op_mul");
	private static By DIVIDE_BTN 	= By.id("com.android.calculator2:id/op_div");
	private static By CLEAR_BTN 	= By.id("com.android.calculator2:id/op_clr");
	private static By EQUAL_BTN 	= By.id("com.android.calculator2:id/eq");
	private static By ADVANCE_BTN 	= By.id("com.android.calculator2:id/pad_advanced");
	private static By RESULT_TXT 	= By.id("com.android.calculator2:id/result");
	private static By POINT_BTN 	= By.id("com.android.calculator2:id/dec_point");
	private static By NUM_BTN(String number) {
		return By.id("com.android.calculator2:id/digit_"+number);
	}

	//Declare Keywords
	public static void openAdvancePage(AndroidDriver driver) {
		driver.findElement(ADVANCE_BTN).click();
	}
	
	public static void clickNumber(AndroidDriver driver, String number) throws Exception {
		logger.info("Click number "+number);
		driver.findElement(NUM_BTN(number)).click();
	}
	
	public static void plus(AndroidDriver driver) throws Exception {
		logger.info("Click + ");
		driver.findElement(PLUS_BTN).click();
	}
	
	public static void minus(AndroidDriver driver) throws Exception {
		logger.info("Click - ");
		driver.findElement(MINUS_BTN).click();
	}
	
	public static void multiply(AndroidDriver driver) throws Exception {
		logger.info("Click * ");
		driver.findElement(MULTIPLY_BTN).click();
	}
	
	public static void divide(AndroidDriver driver) throws Exception {
		logger.info("Click : ");
		driver.findElement(DIVIDE_BTN).click();
	}
	
	public static void equal(AndroidDriver driver) throws Exception {
		logger.info("Click = ");
		driver.findElement(EQUAL_BTN).click();
	}
	
	public static void clear(AndroidDriver driver) throws Exception {
		logger.info("Click Clear if exists");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
		if (driver.findElements(CLEAR_BTN).size() > 0) {
			driver.findElement(CLEAR_BTN).click();
		}
	}
	
	public static int result(AndroidDriver driver) throws Exception {
		return Integer.parseInt(driver.findElement(RESULT_TXT).getText());
	}
}