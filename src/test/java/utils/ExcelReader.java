package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {
    public static void readTestData(String filePath, String sheetName) {
        try {
            // 1. Open the Excel file
            FileInputStream file = new FileInputStream(filePath);

            // 2. Open the workbook (the whole .xlsx file)
            Workbook workbook = new XSSFWorkbook(file);

            // 3. Open the sheet by name
            Sheet sheet = workbook.getSheet(sheetName);

            // 4. Loop through every row
            for (Row row : sheet) {
                // 5. Loop through every cell in the row
                for (Cell cell : row) {
                    // print each cell's value (as text)
                    System.out.print(cell.toString() + "\t");
                }
                System.out.println();   // new line after each row
            }

            // 6. Close everything
            workbook.close();
            file.close();

        } catch (IOException e) {
            System.out.println("Error reading Excel: " + e.getMessage());
        }
    }
}
