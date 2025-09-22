package org.yaxche;

    import org.apache.poi.ss.usermodel.*;
    import org.apache.poi.xssf.usermodel.XSSFWorkbook;

    import jakarta.enterprise.context.ApplicationScoped;
    import java.io.FileInputStream;
    import java.io.IOException;
    import java.util.ArrayList;
    import java.util.List;

    @ApplicationScoped
    public class SpreadsheetService {

        public List<List<String>> readExcelFile(String filePath) throws IOException {
            List<List<String>> data = new ArrayList<>();
            try (FileInputStream fis = new FileInputStream(filePath);
                 Workbook workbook = new XSSFWorkbook(fis)) {

                Sheet sheet = workbook.getSheetAt(0); // Get the first sheet

                for (Row row : sheet) {
                    List<String> rowData = new ArrayList<>();
                    for (Cell cell : row) {
                        rowData.add(getCellValueAsString(cell));
                    }
                    data.add(rowData);
                }
            }
            return data;
        }

        private String getCellValueAsString(Cell cell) {
            if (cell == null) {
                return "";
            }
            return switch (cell.getCellType()) {
                case STRING -> cell.getStringCellValue();
                case NUMERIC -> String.valueOf(cell.getNumericCellValue());
                case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
                //case FORMULA -> cell.getCellFormula(); // Or evaluate the formula
                case FORMULA -> cell.getStringCellValue();
                default -> "";
            };
        }
    }