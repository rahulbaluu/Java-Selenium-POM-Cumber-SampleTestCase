package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ExcelUtils {
	private static Workbook workbook;
	private static Sheet sheet;

	public static void setExcelFile(String excelPath, String sheetName) throws IOException {
		FileInputStream inputStream = new FileInputStream(excelPath);
		workbook = new XSSFWorkbook(inputStream);
		sheet = workbook.getSheet(sheetName);
	}

	public static String getCellData(int rowNum, int colNum) {
		Cell cell = sheet.getRow(rowNum).getCell(colNum);
		return cell.toString();
	}

	public static int getRowCount() {
		return sheet.getPhysicalNumberOfRows();
	}

	public static void closeWorkbook() throws IOException {
		workbook.close();
	}

	public static Map<String, String> getRowData(String sheetName, int rowNumber) throws IOException {
		FileInputStream file = new FileInputStream("src/test/resources/testExcel/SignupRegistrationDetail.xlsx");
		Workbook workbook = new XSSFWorkbook(file);
		Sheet sheet = workbook.getSheet(sheetName);

		Map<String, String> rowData = new HashMap<>();
		Row headerRow = sheet.getRow(0);
		Row dataRow = sheet.getRow(rowNumber);

		for (int i = 0; i < headerRow.getLastCellNum(); i++) {
			Cell headerCell = headerRow.getCell(i);
			Cell valueCell = dataRow.getCell(i);

			String key = headerCell.getStringCellValue();
			DataFormatter formatter = new DataFormatter();
			String value = (valueCell != null) ? formatter.formatCellValue(valueCell) : "";
			rowData.put(key, value);
		}
		workbook.close();
		file.close();
		return rowData;
	}
}
