package account_operations;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	
	public int getRowCount(String excelPath, String sheetName) throws IOException {
		
		fi= new FileInputStream(excelPath);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		int rowcount= ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowcount;
	}
	
	public int getCellCount(String excelPath, String sheetName, int rownum) throws IOException {
		
		fi= new FileInputStream(excelPath);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		row= ws.getRow(rownum);
		int cellcount= row.getLastCellNum();
		wb.close();
		fi.close();
		return cellcount;
	}
	
	public String getCellData(String excelPath, String sheetName, int rownum, int colnum) throws IOException {
		
		fi= new FileInputStream(excelPath);
		wb= new XSSFWorkbook(fi);
		ws= wb.getSheet(sheetName);
		row= ws.getRow(rownum);
		cell= row.getCell(colnum);
		String data;
		
		try {
			DataFormatter formatter = new DataFormatter();
			String cellData = formatter.formatCellValue(cell);
			return cellData;
			
		} catch (Exception exp) {
			data="";
			System.out.println(exp.getMessage());
			System.out.println(exp.getCause());
			exp.printStackTrace();
		}
		wb.close();
		fi.close();
		return data;
	}

}
