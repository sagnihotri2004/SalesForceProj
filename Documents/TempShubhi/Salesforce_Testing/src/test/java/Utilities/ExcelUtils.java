package Utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;

//import org.apache.poi.hslf.model.Sheet;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
//import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
//import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
//import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	// private static XSSFWorkbook newExcelWBook;
	private static XSSFCell Cell;

	// int Rows = 0;
	// This method is to set the File path and to open the Excel file
	// Pass Excel Path and SheetName as Arguments to this method
	public static void setExcelFile(String Path, String SheetName) throws Exception {
		FileInputStream ExcelFile = new FileInputStream(Path);
		ExcelWBook = new XSSFWorkbook(ExcelFile);
		ExcelWSheet = ExcelWBook.getSheet(SheetName);
	}

	public static int lastrow() {
		int Rows = ExcelWSheet.getLastRowNum();
		return Rows;
	}

	// This method is to read the test data from the Excel cell
	// In this we are passing parameters/arguments as Row Num and Col Num
	public static String getCellData(int RowNum, int ColNum) throws Exception {
		XSSFRow row = ExcelWSheet.getRow(RowNum);
		// Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);
		Cell = row.getCell(ColNum);
		if (null == Cell) {
			return "";
		}
		String CellData = Cell.getStringCellValue();
		return CellData;
	}

	public static void createExcel(String Path, String SheetName) throws Exception {
		String SheetPath = Path + "\\" + SheetName + ".xlsx";
		Workbook wb1 = new XSSFWorkbook();
		// create a new sheet with sheet name Vineet1
		CreationHelper createHelper = wb1.getCreationHelper();
		org.apache.poi.ss.usermodel.Sheet sheet1 = wb1.createSheet("Test_Cases_Result");
		// Create the first Row
		Row row1 = sheet1.createRow((short) 0);
		// inserting first row cell value
		row1.createCell(0).setCellValue(createHelper.createRichTextString("Test Case"));
		row1.createCell(1).setCellValue(createHelper.createRichTextString("Description"));
		row1.createCell(2).setCellValue(createHelper.createRichTextString("Action_Keyword"));
		row1.createCell(3).setCellValue(createHelper.createRichTextString("Page Name"));
		row1.createCell(4).setCellValue(createHelper.createRichTextString("Object Name"));
		row1.createCell(5).setCellValue(createHelper.createRichTextString("Test Data"));
		row1.createCell(6).setCellValue(createHelper.createRichTextString("Test Result"));
		FileOutputStream fileOut1 = new FileOutputStream(SheetPath);
		wb1.write(fileOut1);
		fileOut1.close();

	}

	public static void CreateRow(int rowV) {
		Row row3 = ExcelWSheet.createRow((short) rowV);
	}

	public static void setColumnValue(int RowN, int ColN, String Val, String Flg, String ShtNm) {

		try {
			FileInputStream fis = new FileInputStream(ShtNm);
			Workbook wb2 = new XSSFWorkbook(fis);
			CreationHelper createHelper1 = wb2.getCreationHelper();
			org.apache.poi.ss.usermodel.Sheet sheet = wb2.getSheet("Test_Cases_Result");

			if (Flg.equals("Yes")) {
				// row2 = ExcelWSheet.createRow((short) RowN);
				Row row2 = sheet.createRow(RowN);
				row2.createCell(ColN).setCellValue(createHelper1.createRichTextString(Val));
			} else {
				Row row4 = sheet.getRow(RowN);
				row4.createCell(ColN).setCellValue(createHelper1.createRichTextString(Val));
			}
			FileOutputStream fileOut2 = new FileOutputStream(ShtNm);
			wb2.write(fileOut2);
			fileOut2.close();
		} catch (Exception e) {

		} finally {

		}
	}

	public static int columns() {
		int columns = ExcelWSheet.getRow(0).getPhysicalNumberOfCells();
		return columns;
	}
}
