package com.emp.Pages;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.emp.Tests.BaseTest;
import com.emp.Utils.ReadConfig;

public class LoginPage extends BaseTest {

	public static WebDriver driver;

	EmployeeHomePage empPage = new EmployeeHomePage(driver);
	ReadConfig readConfig = new ReadConfig();
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "input[type=text]")
	public static WebElement textBox_userName;

	@FindBy(css = "input[type=password]")
	public static WebElement textBox_password;

	@FindBy(css = "button[type=submit]")
	public static WebElement btn_login;

	public void setUserName(String userName) {
		try {

			textBox_userName.clear();
			textBox_userName.sendKeys(userName);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	//To set Password
	//To set pwd
	public void setpassword(String password) {
		try {
			textBox_password.clear();
			textBox_password.sendKeys(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickLoginBtn() {
		try {
			btn_login.click();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void verifyLoggedInUser() throws IOException{
		if (empPage.getLoggedInUser().contains(readConfig.getUsername())) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "loginTest");
			Assert.fail();
		}
	}
}
