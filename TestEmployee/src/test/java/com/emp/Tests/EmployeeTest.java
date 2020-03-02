package com.emp.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.emp.Pages.EmployeeHomePage;
import com.emp.Pages.LoginPage;
import com.emp.Utils.XLUtils;

public class EmployeeTest extends BaseTest {

	LoginPage loginPage;
	EmployeeHomePage empPage;
	

	@BeforeTest
	public void loginTest() throws IOException {
		BaseTest.initialization();
		logger.info("****Login test started****");
		loginPage = new LoginPage(driver);
		empPage = new EmployeeHomePage(driver);
		loginPage.setUserName(readConfig.getUsername());
		loginPage.setpassword(readConfig.getPassword());
		loginPage.clickLoginBtn();
		loginPage.verifyLoggedInUser();
		logger.info("****Login test finished successfully****");

	}

	@Test(dataProvider = "getCreateEmpData")
	public void createEmployeeTest(String firstName, String lastName, String date, String email) throws IOException {
		logger.info("****createEmployee test started****");
		empPage = new EmployeeHomePage(driver);
		empPage.clickCreate();
		empPage.enterFirstName(firstName.trim());
		empPage.enterLastName(lastName.trim());
		empPage.enterStartDate(date.trim());
		empPage.enterEamil(email.trim());
		empPage.clickAdd();
		empPage.verifyAddedEmp(firstName, lastName);
		logger.info("****createEmployee test finished successfully****");
	}

	@Test(dataProvider = "getUpdateEmpData", priority = 1)
	public void updateEmployeeTest(String toUpdate, String firstName, String lastName) throws IOException {
		logger.info("****updateEmployee test started****");
		empPage = new EmployeeHomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement updatedata = empPage.getEmpToUpdate(toUpdate);
		js.executeScript("arguments[0].scrollIntoView(true);", updatedata);
		updatedata.click();
		empPage.clickEdit();
		empPage.enterFirstName(firstName.trim());
		empPage.enterLastName(lastName.trim());
		empPage.clickUpdate();
		empPage.verifyUpdatedEmp(firstName, lastName);
		logger.info("****updateEmployee test finished successfully****");
	}

	@Test(dataProvider = "getDeleteEmpData", priority = 2)
	public void deleteEmployeeTest(String empToDelete) throws InterruptedException, IOException {
		logger.info("****deleteEmployee test started****");
		empPage = new EmployeeHomePage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement elpToDelete = empPage.getEmpToDelete(empToDelete);
		js.executeScript("arguments[0].scrollIntoView(true);", elpToDelete);
		elpToDelete.click();
		empPage.clickDelete();
		Alert alert = driver.switchTo().alert();
		System.out.println(alert.getText());
		alert.accept();
		driver.switchTo().defaultContent();
		Thread.sleep(2000);
		driver.navigate().back();
		driver.navigate().forward();
		empPage.verifyEmpDeleted(empToDelete);
		logger.info("****deleteEmployee test finished successfully****");
	}

	
	
}
