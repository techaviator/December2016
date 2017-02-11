package genericLibraries;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	XSSFWorkbook wb = null;
	//Get excel from a computer location and place it in workbook
	public  ExcelReader(String path) throws IOException
	{
		FileInputStream fis =null;
		
		
		//System.out.println(path);
		
		 fis = new FileInputStream(path);
		
		 wb=new XSSFWorkbook(fis);
		if(fis!=null)
		{
			System.out.println("connection successfull");
		}
		
		fis.close();
	}
	
	//Method to set a sheet using sheetname
	public XSSFSheet getSheetName(String sheetName)
	{
		XSSFSheet sheet = wb.getSheet(sheetName);
		return sheet;
	}
	
	//Read data from the sheet using sheet info , row info and cell info
	public String readXlData(String sheetName, int rowIndex, int cellIndex)
	{
		XSSFSheet sheet = getSheetName(sheetName);
		String cellText = null;
		XSSFCell cell = sheet.getRow(rowIndex).getCell(cellIndex);
		if(cell.getCellType()==Cell.CELL_TYPE_STRING)
		{
			cellText=cell.getStringCellValue();
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			double numericCellValue = cell.getNumericCellValue();
			cellText= String.valueOf(numericCellValue);
		}
		else if(cell.getCellType()==Cell.CELL_TYPE_BLANK)
		{
			cellText="";
		}
		return cellText;
	}
	
	//Get number of rows
	public int getRowCount(String sheetName)
	{
		XSSFSheet sheet = getSheetName(sheetName);
		int lastRowNum = sheet.getLastRowNum();
		return lastRowNum+1;
	}
	//get number of Cells
	
	public int getCellCount(String sheetName)
	{
		XSSFSheet sheet = getSheetName(sheetName);
		return sheet.getRow(0).getLastCellNum();
	}
	
	public int getCellCount(String sheetName, int rowIndex)
	{
		XSSFSheet sheet = getSheetName(sheetName);
		return sheet.getRow(rowIndex).getLastCellNum();
	}
	
	
	//method to write data back to excel
	public void writeData2Excel(String sheetName, int rowIndex, int cellIndex, String value)
	{
		XSSFSheet sheet = getSheetName(sheetName);
		XSSFRow row = sheet.getRow(rowIndex);
		if(row==null)
		{
			row=sheet.createRow(rowIndex);
		}
		XSSFCell cell = row.getCell(cellIndex);
		if(cell==null)
		{
			cell=row.createCell(cellIndex);
		}
		cell.setCellValue(value);
		
			
	}
	
	public void saveXldata(String path) throws IOException
	{
		FileOutputStream fout = new FileOutputStream(path);
		wb.write(fout);
		fout.close();
		wb.close();
	}

}
