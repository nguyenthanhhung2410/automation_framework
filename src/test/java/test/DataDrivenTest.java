package test;

import static org.testng.Assert.assertEquals;

import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;
import io.appium.java_client.android.AndroidDriver;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import calculatorPOM.MainPage;
import ultilities.ExcelUtils;
import ultilities.ResultLog;

public class DataDrivenTest {
	
	public static AndroidDriver driver = null;
	public static ResultLog logger = new ResultLog("DataDrivenTest - ");
	private String dataPath = "test-data/";
	
	@DataProvider (name="calculatorProvider")
	public Object[][] calculator(Method method) throws Exception{
	 	ExcelUtils.setExcelFile(dataPath+"calculatorData.xlsx","Sheet1");
	 	Object[][] testArrays = null;
	 	testArrays = ExcelUtils.getTableArray(dataPath+"calculatorData.xlsx", "Sheet1", method.getName(), 3);
	 	return (testArrays);
	}
	
	@DataProvider (name="dataProviderNSet")
	public Object[][] dataProviderNSet(Method method) throws Exception{
		Object[][] testArrays = null;
	 	testArrays = ExcelUtils.getTableArray(dataPath+"calculatorData.xlsx", "Sheet1", method.getName());
	 	return (testArrays);
	}
	
	@BeforeClass (alwaysRun=true)
	public void classSetUp() throws Exception {
		// code that will be invoked when this class is instantiated
		try {
			DesiredCapabilities capabilities = new DesiredCapabilities();
		    capabilities.setCapability("appium-version", "1.9");
		    capabilities.setCapability("platformName", "Android");
		    capabilities.setCapability("platformVersion", "8.1");
		    capabilities.setCapability("deviceName", "Nexus 5X");
		    capabilities.setCapability("appPackage", "com.android.calculator2");
		    capabilities.setCapability("appActivity", "com.android.calculator2.Calculator");

		    try {
		        driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"), capabilities);
		    } catch (MalformedURLException e) {
		        e.printStackTrace();
		    }

		    driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
	
	@Test (dataProvider = "calculatorProvider")
	public void testPlus(String num1, String num2, String result) throws Exception{
		MainPage.clear(driver);
		MainPage.clickNumber(driver, num1);
		MainPage.plus(driver);
		MainPage.clickNumber(driver, num2);
		MainPage.equal(driver);
		assertEquals(MainPage.result(driver), Integer.parseInt(result));
	}
	
	@Test (dataProvider = "calculatorProvider")
	public void testMinus(String num1, String num2, String result) throws Exception{
		MainPage.clear(driver);
		MainPage.clickNumber(driver, num1);
		MainPage.minus(driver);
		MainPage.clickNumber(driver, num2);
		MainPage.equal(driver);
		assertEquals(MainPage.result(driver), Integer.parseInt(result));
	}
	
	@Test (dataProvider = "calculatorProvider")
	public void testMultiply(String num1, String num2, String result) throws Exception{
		MainPage.clear(driver);
		MainPage.clickNumber(driver, num1);
		MainPage.multiply(driver);
		MainPage.clickNumber(driver, num2);
		MainPage.equal(driver);
		assertEquals(MainPage.result(driver), Integer.parseInt(result));
	}
	
	@Test (dataProvider = "calculatorProvider")
	public void testDivide(String num1, String num2, String result) throws Exception{
		MainPage.clear(driver);
		MainPage.clickNumber(driver, num1);
		MainPage.divide(driver);
		MainPage.clickNumber(driver, num2);
		MainPage.equal(driver);
		assertEquals(MainPage.result(driver), Integer.parseInt(result));
	}
	
	@Test (dataProvider = "dataProviderNSet")
	public void testMinusNSet(int... args) throws Exception{
		MainPage.clear(driver);
		MainPage.clickNumber(driver, Integer.toString(args[0]));
		for (int i=1;i<args.length-1;i++){
			MainPage.minus(driver);
			MainPage.clickNumber(driver, Integer.toString(args[i]));
		}
		MainPage.equal(driver);
		assertEquals(MainPage.result(driver), args[args.length-1]);
	}
	
	@AfterClass(alwaysRun=true)
	public void cleanUp() throws Exception {
		try{
			driver.closeApp();
			logger.info("***CleanUp AfterClass***");
		}catch(Exception e){
			e.printStackTrace();
			logger.error(e.toString());
		}
	}
}
