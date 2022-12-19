package com.flipkart.qa.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public String path;
	public static FileInputStream fis = null;
	public static FileOutputStream fileOut = null;
	public static XSSFWorkbook workbook = null;
	public static XSSFSheet sheet = null;
	// public Workbook workbook=null;
	// public org.apache.poi.ss.usermodel.Sheet sheet = null;
	private XSSFRow row = null;
	private XSSFCell cell = null;

	/**
	 * Initialize Excel Reader Constructor - Read Excel File using FileINputStream
	 * 
	 * @param path
	 */
	public ExcelUtils(String path) {
		this.path = path;
		try {
			fis = new FileInputStream(path);
			workbook = new XSSFWorkbook(fis);
			sheet = workbook.getSheetAt(0);
			fis.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	/**
	 * This Method returns number of columns in a sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getColumnCount(String sheetName) {
		try {
			sheet = workbook.getSheet(sheetName);
			row = sheet.getRow(0);
			if (row == null)
				return -1;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row.getLastCellNum();
	}

	/**
	 * This method returns the row count in a sheet
	 * 
	 * @param sheetName
	 * @return
	 */
	public int getRowCount(String sheetName) {
		int index = workbook.getSheetIndex(sheetName);
		if (index == -1)
			return 0;
		else {
			sheet = workbook.getSheetAt(index);
			int number = sheet.getLastRowNum() + 1;
			return number;
		}

	}

	/**
	 * This Method return data from excel in Hashmap
	 * 
	 * @param SheetName
	 * @return
	 */
	public static HashMap<String, String> getExcelData(String SheetName) {
		HashMap<String, String> data = new HashMap<String, String>();

		try {
			// FileInputStream fis = new FileInputStream(filePath);
			// XSSFWorkbook wb = new XSSFWorkbook(fis);
			XSSFSheet st = workbook.getSheet(SheetName);
			XSSFRow row = st.getRow(0);
			XSSFRow row1 = st.getRow(1);
			int lastColumn = row.getLastCellNum();
			XSSFCell cell;
			XSSFCell cell1;
			String header = "";
			String value = "";
			for (int i = 0; i < lastColumn; i++) {
				cell = row.getCell(i);
				cell1 = row1.getCell(i);
				header = cell.getStringCellValue();
				value = cell1.getStringCellValue();
				data.put(header, value);
			}
		} catch (Exception e) {
			ReportUtils.log.fail(e);
		}

		return data;
	}

	/**
	 * This method returns individual cell data
	 * 
	 * @param sheetName
	 * @param colNum
	 * @param rowNum
	 * @return
	 */
	public String getCellData(String sheetName, int colNum, int rowNum) {
		try {
			int index = workbook.getSheetIndex(sheetName);
			if (index == -1)
				return "";
			row = sheet.getRow(rowNum - 1);
			sheet = workbook.getSheetAt(index);
			cell = row.getCell(colNum);
		} catch (Exception e) {

			e.printStackTrace();
		}
		return cell.getStringCellValue();
	}

}