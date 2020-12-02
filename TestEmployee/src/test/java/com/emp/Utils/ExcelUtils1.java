package com.emp.Utils;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormatter;

public class ExcelUtils1 {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	public static String path = "D:\\Data\\eclipse Workspace\\TestEmployee\\TestEmployee\\TestData\\EmployeeDataSheet.xlsx";
	public static String sheetName = "Sheet1";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		getDataFromXL(path, sheetName, "Transfer", "Data2");

	}

	public static void setExcelFile(String path, String sheetName) throws Exception {

		try {

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(path);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet(sheetName);

		} catch (Exception e) {

			throw (e);

		}

	}

	public static String getDataFromXL(String path, String sheetName, String testName, String columnName)
			throws Exception {
		setExcelFile(path, sheetName);
		Iterator<Row> rows = ExcelWSheet.iterator();
		Row fristRow = rows.next();
		Iterator<Cell> columnsOfFirstRow = fristRow.cellIterator();
		ArrayList<Object> a = new ArrayList<Object>();
		ArrayList<String> columnKey = new ArrayList<String>();
		HashMap<String, Object> data = new HashMap<String, Object>();
		int k = 0;
		int column = 0;
		int column2 = 0;
		//adding all column
		while (columnsOfFirstRow.hasNext()) {
			Cell key = columnsOfFirstRow.next();
			columnKey.add(key.getStringCellValue());
		}
		columnsOfFirstRow = fristRow.cellIterator();
		while (columnsOfFirstRow.hasNext()) {
			Cell value = columnsOfFirstRow.next();
			if (value.getStringCellValue().equalsIgnoreCase("TestName")) {
				column = k;

			}

			if (value.getStringCellValue().equalsIgnoreCase("RunTest")) {
				column2 = k;

			}

			k++;
		}
		System.out.println(column);

		while (rows.hasNext()) {
			Row r = rows.next();
			if (r.getCell(column).getStringCellValue().equalsIgnoreCase(testName)
					&& r.getCell(column2).getStringCellValue().equalsIgnoreCase("Y")) {

				Iterator<Cell> cv = r.cellIterator();
				while (cv.hasNext()) {
					Cell cvalue = cv.next();
					if (cvalue.getCellType() == CellType.STRING) {
						System.out.println(cvalue.getStringCellValue());
						a.add(cvalue.getStringCellValue());

					}

					if (cvalue.getCellType() == CellType.NUMERIC) {
						System.out.println(cvalue.getNumericCellValue());
						a.add(cvalue.getNumericCellValue());
					}

				}

				

				for (int i = 0; i < columnKey.size(); i++) {
					data.put(columnKey.get(i), a.get(i));

				}

				System.out.println(data);
				System.out.println(data.get(columnName));
				//return data.get(columnName).toString();

				break;
			} else {
				System.out.println("Set run test to y for " + testName);
			}

		}
		return data.get(columnName).toString();

	}

}
