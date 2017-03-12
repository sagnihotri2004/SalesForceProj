package ExecuteEngine;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
//import java.util.concurrent.TimeUnit;

//import org.apache.poi.sl.usermodel.Sheet;
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;

import Configuration.ActionKeywords;
import Utilities.ExcelUtils;
import junit.framework.Assert;

public class DriverScript {
	// public static int noofRows;
	private static final Logger LOGGER  = LoggerFactory.getLogger(DriverScript.class);

	@DataProvider(name = "Test_Cases_List")
	public static Object[] TestCaseName() {
		LOGGER.info("Inside method data provider");
		String[] tcArry = null;
		try {
			String sTestCaseExePath1 = "C://Users//Antim Shukla//Documents//TempShubhi//Salesforce_Testing//src//test//java//DataEngine//Test Case Execution.xlsx";
			ExcelUtils.setExcelFile(sTestCaseExePath1, "Execution List");
			int iRows1 = ExcelUtils.lastrow();
			tcArry = new String[iRows1];
			// Result = readTestCaseExecutionSheet(Result, Appurl, FolderName,
			// sTestCaseExePath, iRows);
			for (int i1 = 1; i1 <= iRows1; i1++) {
				ExcelUtils.setExcelFile(sTestCaseExePath1, "Execution List");
				if (ExcelUtils.getCellData(i1, 2).equals("Yes")) {
					String Tcn1 = ExcelUtils.getCellData(i1, 1);
					tcArry[i1 - 1] = Tcn1;
				}
			}
		} catch (Exception e) {
			System.out.println("Error:" + e.getMessage());
		}
		Object[] tcArry2 = tcArry;
		return (tcArry2);

	}

	@Test(dataProvider = "Test_Cases_List")
	public void test(String tcname, ITestContext ctx) throws Exception {

		ctx.setAttribute("testName", tcname);

		String Result = "";
		// Declaring the path of the Excel file with the name of the Excel file
		File src = new File("Config.properties");
		// Create  FileInputStream object
		FileInputStream fis = new FileInputStream(src);
		// Create Properties class object to read properties file
		Properties pro = new Properties();
		// Load file so we can use into our script
		pro.load(fis);
		String Appurl = pro.getProperty("url");
		SimpleDateFormat ft = new SimpleDateFormat("dd_MM_YYYY");
		Date dtfolder = new Date();
		System.out.println(ft.format(dtfolder));
		String FolderName = "ExecutionResult_" + ft.format(dtfolder);
		FolderName = FolderName.replace(" ", "_");
		System.out.println(FolderName);
		// String ResFoldPath = "C://Users//Antim
		// Shukla//Documents//TempShubhi//Results//"+FolderName;
		File dir = new File("C://Users//Antim Shukla//Documents//TempShubhi//Results//" + FolderName);
		if (!dir.exists()) {
			dir.mkdir();
			System.out.print(FolderName + " Folder  created");
		}
		String sTestCaseExePath = "C://Users//Antim Shukla//Documents//TempShubhi//Salesforce_Testing//src//test//java//DataEngine//Test Case Execution.xlsx";
		ExcelUtils.setExcelFile(sTestCaseExePath, "Execution List");
		int iRows = ExcelUtils.lastrow();
		Result = readTestCaseExecutionSheet(Result, Appurl, FolderName, sTestCaseExePath, iRows);

	}

	/**
	 * @param Result
	 * @param Appurl
	 * @param FolderName
	 * @param sTestCaseExePath
	 * @param iRows
	 * @return
	 * @throws Exception
	 */
	private static String readTestCaseExecutionSheet(String Result, String Appurl, String FolderName,
			String sTestCaseExePath, int iRows) throws Exception {
		for (int i = 1; i <= iRows; i++) {
			ExcelUtils.setExcelFile(sTestCaseExePath, "Execution List");
			if (ExcelUtils.getCellData(i, 2).equals("Yes")) {
				String sTestRsrPath = "C://Users//Antim Shukla//Documents//TempShubhi//Salesforce_Testing//src//test//java//DataEngine//"
						+ ExcelUtils.getCellData(i, 0) + ".xlsx";
				String sTestRsrResult = ExcelUtils.getCellData(i, 0);
				String Tcn = ExcelUtils.getCellData(i, 1);
				String ResFoldPath = "C://Users//Antim Shukla//Documents//TempShubhi//Results//" + FolderName + "//"
						+ sTestRsrResult;
				File dirFl = new File("C://Users//Antim Shukla//Documents//TempShubhi//Results//" + FolderName + "//"
						+ sTestRsrResult);
				if (!dirFl.exists()) {
					dirFl.mkdir();
					// add code to copy the dataengine file to new result file
					System.out.print(sTestRsrResult + "Result Folder  created");
				}
				String val = getDateTime();
				String sTestRsrEx = ExcelUtils.getCellData(i, 1) + "_" + val;
				ExcelUtils.createExcel(ResFoldPath, sTestRsrEx);
				ExcelUtils.setExcelFile(sTestRsrPath, "Test Steps");
				String sTestRsrExcP = "C://Users//Antim Shukla//Documents//TempShubhi//Results//" + FolderName + "//"
						+ sTestRsrResult + "//" + sTestRsrEx + ".xlsx";
				int iTCRow = getRowNumber(Tcn);
				int inoOfrowRsr = ExcelUtils.lastrow();
				int noOfColu = ExcelUtils.columns();
				noOfColu = noOfColu + 1;
				// String [][] arryRes =new String[inoOfrowRsr][noOfColu];
				int gI = 1;
				Assert.assertTrue(true);
				for (int iRow = iTCRow; iRow <= inoOfrowRsr; iRow++) {
					Assert.assertTrue(true);
					ExcelUtils.setExcelFile(sTestRsrPath, "Test Steps");
					// Storing the value of excel cell in sActionKeyword string
					// variable
					String sActionKeyword = ExcelUtils.getCellData(iRow, 2);
					if (!ExcelUtils.getCellData(iRow, 2).equals("TCEnd")) {
						// Comparing the value of Excel cell with all the
						// project keywords
						switch (sActionKeyword) {

						case "openBrowser":
							Result = ActionKeywords.openBrowser(Appurl);
							// storeArray(inoOfrowRsr,noOfColu,ExcelUtils.getCellData(iRow,
							// 1),ExcelUtils.getCellData(iRow,
							// 2),ExcelUtils.getCellData(iRow,
							// 3),ExcelUtils.getCellData(iRow,
							// 4),ExcelUtils.getCellData(iRow,
							// 5),ExcelUtils.getCellData(iRow, 6));
							break;
						case "navigate":
							Result = ActionKeywords.openBrowser(Appurl);
							break;
						case "TCStart":
							Result = ActionKeywords.openBrowser(Appurl);
							break;
						case "TCEnd":
							Result = ActionKeywords.openBrowser(Appurl);
							break;
						default:

						}
					} else {
						Result = "";
						storeResult(gI, iRow, Result, sTestRsrExcP, sTestRsrPath);

						break;

					}
					storeResult(gI, iRow, Result, sTestRsrExcP, sTestRsrPath);
					gI++;
				}

			}
		}
		return Result;
	}

	// ExcelUtils.setExcelFile(sTestRsrPath, "Test Steps");

	public static int getRowNumber(String testcaseName) {
		int row = 0;
		int noofRows = ExcelUtils.lastrow();
		try {
			for (int i = 0; i <= noofRows; i++) {
				String sTestCaseName = ExcelUtils.getCellData(i, 0);
				if (sTestCaseName.equalsIgnoreCase(testcaseName)) {
					row = i;
					break;
				}
			}

		} catch (Exception e) {
			System.out.println("Row number not found");
		} finally {
			// abc
		}
		return row;
	}

	public static void storeResult(int Crr, int MastRR, String sResult, String ShName, String MShName) {
		try {
			String CreateRowFlag = "";
			String dt;
			// String dt = "";
			int iCol = ExcelUtils.columns();
			for (int ig = 0; ig <= iCol; ig++) {

				if (ig == 0) {
					CreateRowFlag = "Yes";
				} else {
					CreateRowFlag = "No";
				}
				// ExcelUtils.setExcelFile(ShName,"Test_Cases_Result");
				if (ig == iCol) {
					ExcelUtils.setColumnValue(Crr, iCol, sResult, CreateRowFlag, ShName);
				} else {
					dt = ExcelUtils.getCellData(MastRR, ig);
					ExcelUtils.setColumnValue(Crr, ig, dt, CreateRowFlag, ShName);
				}
				ExcelUtils.setExcelFile(MShName, "Test Steps");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static String[][] storeArray(String Arr[][]) {
		return Arr;
	}

	public static String getDateTime() {
		String date1 = null;
		try {
			// Create object of SimpleDateFormat class and decide the format
			DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			// get current date time with Date()
			Date date = new Date();
			// Now format the date
			date1 = dateFormat.format(date);
			// Print the Date
			date1 = date1.replace("/", "");
			date1 = date1.replace(":", "");
			date1 = date1.replace(" ", "");
			System.out.println("Current date and time is " + date1);

		} catch (Exception e) {
			System.out.println("Date and Time not calculated");
		} finally {
			// abc
		}
		return date1;

	}
}
