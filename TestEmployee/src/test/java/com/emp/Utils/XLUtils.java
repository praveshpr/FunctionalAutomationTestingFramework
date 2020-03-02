package com.emp.Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static String xlfile = System.getProperty("user.dir") + "\\TestData\\EmployeeDataSheet.xlsx";
	public static Object[][] data = null;

	public static int getRowCount(String xlfile, String xlsheet) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		int rowcount = ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}

	public static int getCellCount(String xlfile, String xlsheet, int rownum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		int cellcount = row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}

	public static String getCellData(String xlfile, String xlsheet, int rownum, int colnum) throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.getCell(colnum);
		String data;
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
		} catch (Exception e) {
			data = "";
		}
		wb.close();
		fi.close();
		return data;
	}

	public static void setCellData(String xlfile, String xlsheet, int rownum, int colnum, String data)
			throws IOException {
		fi = new FileInputStream(xlfile);
		wb = new XSSFWorkbook(fi);
		ws = wb.getSheet(xlsheet);
		row = ws.getRow(rownum);
		cell = row.createCell(colnum);
		cell.setCellValue(data);
		fo = new FileOutputStream(xlfile);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}

	/*
	 * public static Object[][] getTestData(String xlsheet) throws IOException {
	 * 
	 * try { fi=new FileInputStream(xlfile); wb=new XSSFWorkbook(fi);
	 * ws=wb.getSheet(xlsheet); data = new
	 * Object[ws.getLastRowNum()][ws.getRow(0).getLastCellNum()-2];
	 * 
	 * for(int i=0; i< ws.getLastRowNum(); i++ ) {
	 * 
	 * if(ws.getRow(i).getCell(0).toString().equalsIgnoreCase("Y")){ for(int
	 * k=2,count=0 ; k< ws.getRow(0).getLastCellNum(); k++,count++) {
	 * System.out.println("value of k" + k + "        i      " + i);
	 * data[i][count] = ws.getRow(i).getCell(k).toString();
	 * 
	 * System.out.println(data[i][count]); } }}
	 * 
	 * } catch(Exception e) { e.printStackTrace(); } return
	 * removeNullFromArray(data); }
	 * 
	 * public static Object[][] removeNullFromArray(Object[][] array){ for(int i
	 * = 0; i < array.length; i++) { Object[] inner = array[i]; List<Object>
	 * list = new ArrayList<Object>(inner.length); for(int j = 0; j <
	 * inner.length; j++){ if(inner[j] != null){ list.add(inner[j]); } }
	 * array[i] = list.toArray(new Object[list.size()]); } return array; }
	 */

	public static String[][] getTestData(String sheetName) {

		String[][] testData = null;
		try {
			File file = new File(xlfile); // creating a new file instance
			FileInputStream fis = new FileInputStream(file); // obtaining bytes
																// from the file
			// creating Workbook instance that refers to .xlsx file
			XSSFWorkbook wb = new XSSFWorkbook(fis);
			//creating sheet object to recieve object
			XSSFSheet sheet = wb.getSheet(sheetName); 
			Iterator<Row> itr = sheet.iterator(); // iterating over excel file
			ArrayList<String> allData = new ArrayList<String>();
			int columnCount = 0;
			while (itr.hasNext()) {
				Row row = itr.next();
				columnCount = row.getLastCellNum();
				Cell cell1 = row.getCell(0);
				if (cell1.getStringCellValue().equalsIgnoreCase("Y")) {
					//iterating over each column
					Iterator<Cell> cellIterator = row.cellIterator(); 
					int count = 0;
					while (cellIterator.hasNext()) {
						Cell cell = cellIterator.next();
						if (count == 0 || count == 1) {
							count++;
							continue;
						}
						String cellValue = cell.getStringCellValue();
						allData.add(cellValue);
					}
				}
			}
			columnCount = columnCount - 2;
			System.out.println(allData + "=====" + columnCount);
			int rowCount = allData.size() / columnCount;
			testData = new String[rowCount][columnCount];
			int listIndex = 0;
			for (int i = 0; i < rowCount; i++) {
				for (int j = 0; j < columnCount; j++) {
					testData[i][j] = allData.get(listIndex++);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return testData;
	}

}
