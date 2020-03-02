package com.emp.Pages;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.emp.Tests.BaseTest;

public class EmployeeHomePage extends BaseTest{

	public WebDriver driver;

	public EmployeeHomePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "greetings")
	public static WebElement text_loggedInUser;

	@FindBy(id = "bAdd")
	public static WebElement btn_create;

	@FindBy(css = "input[ng-model*=firstName]")
	public static WebElement textBox_firstName;

	@FindBy(css = "input[ng-model*=lastName]")
	public static WebElement textBox_lastName;

	@FindBy(css = "input[ng-model*=startDate]")
	public static WebElement textBox_startDate;

	@FindBy(css = "input[ng-model*=email]")
	public static WebElement textBox_email;

	@FindBy(css = "button[class=main-button]")
	public static WebElement btn_add;

	@FindBy(css = "ul#employee-list > li")
	public static List<WebElement> list_employees;

	@FindBy(id = "bEdit")
	public static WebElement btn_edit;

	@FindBy(xpath = "//button[text()='Update']")
	public static WebElement btn_update;

	@FindBy(id = "bDelete")
	public static WebElement btn_delete;

	@FindBy(xpath = "//button[text()='Delete']")
	public static WebElement btn_deleteFromEdit;

	public WebElement getEmpToUpdate(String toUpdate) {
		WebElement empToUpd = driver
				.findElement(By.xpath("//*[@id='employee-list']/li[contains(text(),'" + toUpdate + "')]"));
		return empToUpd;
	}

	public WebElement getEmpToDelete(String empDelete) {
		WebElement empToDel = driver
				.findElement(By.xpath("//*[@id='employee-list']/li[contains(text(),'" + empDelete + "')]"));
		return empToDel;
	}

	public String getLoggedInUser() {
		String loggedInUser = null;
		try {
			loggedInUser = text_loggedInUser.getText();
			return loggedInUser;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return loggedInUser;

	}

	public void clickCreate() {
		try {
			btn_create.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterFirstName(String firstName) {
		try {
			textBox_firstName.clear();
			textBox_firstName.sendKeys(firstName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterLastName(String lastName) {
		try {
			textBox_lastName.clear();
			textBox_lastName.sendKeys(lastName);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterStartDate(String date) {
		try {
			textBox_startDate.clear();
			textBox_startDate.sendKeys(date);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void enterEamil(String email) {
		try {
			textBox_email.clear();
			textBox_email.sendKeys(email);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickAdd() {
		try {
			btn_add.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public boolean employeeList(String empName) {
		try {
			for (WebElement empList : list_employees) {
				if (empList.getText().contains(empName))
					break;
			}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	public void clickEdit() {
		try {
			btn_edit.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void selectEmployee(String empName) {
		try {
			for (WebElement empList : list_employees) {
				if (empList.getText().contains(empName)) {
					empList.click();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void clickUpdate() {
		try {
			btn_update.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void clickDelete() {
		try {
			btn_delete.click();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void verifyAddedEmp(String firstName, String lastName) throws IOException {
		boolean isPass = false;
		for (WebElement elists : list_employees) {
			System.out.println(elists.getText());
			if (elists.getText().equals(firstName.trim() + " " + lastName.trim())) {
				isPass = true;
				break;
			}
		}

		if (isPass) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "createEmployeeTest");
			Assert.fail();
		}

	}

	public void verifyUpdatedEmp(String firstName, String lastName) throws IOException {
		boolean isPass = false;
		for (WebElement elists : list_employees) {
			System.out.println(elists.getText());
			if (elists.getText().equals(firstName.trim() + " " + lastName.trim())) {
				isPass = true;
				break;
			}
		}
		if (isPass) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "updateEmployeeTest");
			Assert.fail();
		}

	}
	
	public void verifyEmpDeleted(String empToDelete) throws IOException{
		boolean isPass = true;
		for (WebElement elist : list_employees) {

			if (elist.getText().equals(empToDelete)) {
				isPass = false;
				break;
			}
		}

		if (isPass) {
			Assert.assertTrue(true);
		} else {
			captureScreen(driver, "deleteEmployeeTest");
			Assert.fail();
		}
	}
}