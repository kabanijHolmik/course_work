package com.coursework.controller;

import com.coursework.models.Item;
import com.coursework.services.ItemService;

import com.fasterxml.jackson.core.JsonGenerator;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
@RequestMapping("/data")
public class DataController {

    private final ItemService databaseService;

    public DataController(ItemService databaseService) {
        this.databaseService = databaseService;
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportData() throws IOException {
        List<Item> data = databaseService.list(); // Получите данные из базы данных (замените YourDataEntity на соответствующий класс сущности)

        String json = convertDataToJson(data); // Преобразуйте данные в формат JSON

        byte[] jsonBytes = json.getBytes();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setContentDispositionFormData("attachment", "data.json");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(jsonBytes.length)
                .body(jsonBytes);
    }


    @PostMapping("/import")
    public ResponseEntity<String> importData(@RequestParam("file") MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            List<Item> items = convertJsonToData(inputStream); // Преобразуйте содержимое файла в список объектов Item
            if (items == null) {
                return ResponseEntity.badRequest().body("Invalid JSON format");
            }

            for (Item item:
                    items) {
                databaseService.saveItem(item);
            }

            return ResponseEntity.ok("Data imported successfully");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error importing data");
        }
    }

    private String convertDataToJson(List<Item> data) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd")); // Установите желаемый формат даты
        objectMapper.enable(JsonGenerator.Feature.ESCAPE_NON_ASCII); // Включите экранирование символов, не входящих в ASCII

        try {
            StringWriter stringWriter = new StringWriter();
            objectMapper.writeValue(stringWriter, data);
            return stringWriter.toString();
        } catch (IOException e) {
            // Обработка ошибки, если возникла проблема с записью в StringWriter
            e.printStackTrace();
        }

        return null;
    }

    private List<Item> convertJsonToData(InputStream inputStream) {
        List<Item> itemList = new ArrayList<>();

        try {
            GsonBuilder gsonBuilder = new GsonBuilder();
            gsonBuilder.setDateFormat("yyyy-MM-dd"); // Установите формат даты в JSON
            Gson gson = gsonBuilder.create();

            Reader reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            itemList = gson.fromJson(reader, new TypeToken<List<Item>>() {}.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            // Обработка ошибок парсинга JSON
        }

        return itemList;
    }
}