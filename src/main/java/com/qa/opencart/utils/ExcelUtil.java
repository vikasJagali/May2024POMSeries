package com.qa.opencart.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil {
	
	private static final String TEST_DATA_SHEET_PATH ="src/test/resources/TestData/OpenCartTestData.xlsx";
	private static Workbook book;
	private static Sheet sheet;
	private static Object[][] data;

	public static Object[][] getExcelData(String sheetName){
		
		try {
			FileInputStream fis = new FileInputStream(TEST_DATA_SHEET_PATH);
			book = WorkbookFactory.create(fis);
			sheet = book.getSheet(sheetName);
			
			int numberOfRows = sheet.getLastRowNum();
			int numberOfColumns = sheet.getRow(0).getLastCellNum();
			
			 data = new Object[numberOfRows][numberOfColumns];	
			 
			 System.out.println(numberOfRows);
			 System.out.println(numberOfColumns);
			 
			for(int i=0; i<numberOfRows;i++) {
				for(int j=0; j<numberOfColumns;j++) {
					data[i][j] = sheet.getRow(i+1).getCell(j).toString();
				}
			}
			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (EncryptedDocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return data;
		
	}
}
