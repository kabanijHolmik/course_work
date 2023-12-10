package com.coursework.controller;

import com.coursework.models.Item;
import com.coursework.services.ItemService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;
import java.util.List;

@RestController
public class ReportController {
    private final ItemService databaseService;

    public ReportController(ItemService databaseService) {
        this.databaseService = databaseService;
    }

    @PostMapping("/api/generate-report")
    public void generateReport(HttpServletResponse response) throws IOException {
        // Получение данных из базы данных
        List<Item> items = databaseService.list();

        // Создание документа Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Отчет");


        int rowNum = 1;
        for (Item item : items) {
            Row excelRow = sheet.createRow(rowNum++);
            Cell cell0 = excelRow.createCell(0);
            cell0.setCellValue(item.getId());
            Cell cell1 = excelRow.createCell(1);
            cell1.setCellValue(item.getCode());
            Cell cell2 = excelRow.createCell(2);
            cell2.setCellValue(item.getPrice());
            Cell cell3 = excelRow.createCell(3);
            cell3.setCellValue(item.getName());
            Cell cell4 = excelRow.createCell(4);
            cell4.setCellValue(item.getCategory());
            Cell cell5 = excelRow.createCell(5);
            cell5.setCellValue(item.getUnit());
            Cell cell6 = excelRow.createCell(6);
            cell6.setCellValue(item.getLocation());
            Cell cell7 = excelRow.createCell(7);
            cell7.setCellValue(item.getStatus());
            Cell cell8 = excelRow.createCell(8);
            cell8.setCellValue(item.getNote());
            Cell cell9 = excelRow.createCell(9);
            cell9.setCellValue(item.getCountry());
            Cell cell10 = excelRow.createCell(10);
            cell10.setCellValue(item.getSupplier());
            Cell cell11 = excelRow.createCell(11);
            cell11.setCellValue(item.getReceiptDay());
            Cell cell12 = excelRow.createCell(12);
            cell12.setCellValue(item.getSaleDate());
        }

        // Генерация временного файла для сохранения отчета
        String tempFilePath = "report.xlsx";

        // Сохранение документа Excel во временный файл
        try (OutputStream outputStream = response.getOutputStream()) {
            workbook.write(outputStream);
        }

        // Отправка сгенерированного отчета в ответе
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=report.xlsx");
        response.flushBuffer();
    }

}