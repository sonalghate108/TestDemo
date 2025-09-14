package utilities;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcel {
	
	

	public void ReadData() throws IOException {
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\testData\\data.xlsx");
		
		XSSFWorkbook  workbook = new XSSFWorkbook(file);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int totalrows = sheet.getLastRowNum();
		int totalCells = sheet.getRow(1).getLastCellNum();
		System.out.println(totalrows);
		System.out.println(totalCells);
		
		for (int r = 0; r<totalrows; r++) {
			XSSFRow currentRow = sheet.getRow(r);
			
			for(int c = 0; c< totalCells ;c++) {
				XSSFCell cell = currentRow.getCell(c);
				System.out.println(cell.toString());
			}
			
			
		}
		
		workbook.close();
		file.close();
	}
}
