package com.ua.verdysh.controller;

import com.ua.verdysh.model.LawyerProfile;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TableCreator {
    private static final String FILE_NAME = "lawyers.xls";

    public void createTable(Map<String, List<LawyerProfile>> profiles) {
        try (Workbook workbook = new HSSFWorkbook()) {
            for (Map.Entry<String, List<LawyerProfile>> entry : profiles.entrySet()) {
                createSheet(workbook, entry.getKey(), entry.getValue());
            }
            writeToFile(workbook);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void createSheet(Workbook workbook, String sheetName, List<LawyerProfile> profiles) {
        Sheet sheet = workbook.createSheet(sheetName);
        for (int count = 0; count < profiles.size(); count++) {
            LawyerProfile profile = profiles.get(count);
            Row row = sheet.createRow(count);

            List<String> profileFields = Arrays.asList(profile.getUrl(), profile.getFullName(), profile.getJobPosition(),
                    profile.getDescription(), profile.getPhoto(), profile.getMail());

            profileFields.forEach(v -> createCell(row, profileFields.indexOf(v), v));
        }
    }

    private void createCell(Row row, int number, String text) {
        Cell cell = row.createCell(number);
        cell.setCellValue(text);
    }

    private void writeToFile(Workbook workbook) throws IOException {
        try (FileOutputStream fos = new FileOutputStream(FILE_NAME)) {
            workbook.write(fos);
        }
    }
}
