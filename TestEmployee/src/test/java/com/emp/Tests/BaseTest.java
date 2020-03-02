package com.emp.Tests;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import com.emp.Utils.ReadConfig;
import com.emp.Utils.XLUtils;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
	// public static Properties prop;
	public static Logger logger;
	static ReadConfig readConfig = new ReadConfig();
	public static String browserName;
	public static String baseURL = readConfig.getApplicationURL();
	XLUtils utils = new XLUtils();

	public static void initialization() {
		logger = Logger.getLogger("EmployeeTechAssignment");
		PropertyConfigurator.configure("log4j.properties");
		browserName = readConfig.getBrowser();
	
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-features=VizDisplayCompositor");
			driver = new ChromeDriver(options);

		} else if (browserName.equalsIgnoreCase("FF")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}

		else if (browserName.equalsIgnoreCase("ie")) {
			DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();

			ieCapabilities.setCapability("nativeEvents", false);
			ieCapabilities.setCapability("unexpectedAlertBehaviour", "accept");
			ieCapabilities.setCapability("ignoreProtectedModeSettings", true);
			ieCapabilities.setCapability("disable-popup-blocking", true);
			ieCapabilities.setCapability("enablePersistentHover", true);
			WebDriverManager.iedriver().arch32().setup();
			driver = new InternetExplorerDriver(ieCapabilities);
		}

		else {
			logger.info("Provide proper browser name in config properties");
		}

		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.get(baseURL);
		logger.info("Launched Application successfully");
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
		logger.info("Browse closed successfully ");
	}

	// call when want to take screenshot
	public void captureScreen(WebDriver driver, String tname) throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		File target = new File(System.getProperty("user.dir") + "/Screenshots/" + tname + ".png");
		FileUtils.copyFile(source, target);
		logger.info("Screenshot taken successfully ");
	}

	@DataProvider
	public Object[][] getCreateEmpData() throws IOException {
		Object data[][] = utils.getTestData("CreateEmployee");
		for (int i = 0; i < data.length; i++) {
			for (int j = 0; j < data[i].length; j++)
				System.out.println("****************" + data[i][j]);
		}
		return data;

	}

	@DataProvider
	public Object[][] getUpdateEmpData() throws IOException {
		Object data[][] = utils.getTestData("UpdateEmployee");
		System.out.println(data);
		return data;

	}

	@DataProvider
	public Object[][] getDeleteEmpData() throws IOException {
		System.out.println("kFFFFFFFFFFF");
		Object data[][] = utils.getTestData("DeleteEmployee");
		System.out.println(data);
		return data;

	}

}
