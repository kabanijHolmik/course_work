package com.coursework.controller;

import com.coursework.models.Item;
import com.coursework.services.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/")
    public String items(Model model,
                        @RequestParam(name = "sortBy", defaultValue = "Product_id") String sortBy,
                        @RequestParam(name = "sortOrder", defaultValue = "ASC") String sortOrder,
                        @RequestParam(name = "searchId", required = false) Integer searchId) {
        List<Item> items;

        if (searchId != null) {
            // Обработка запроса поиска по ID
            Item foundItem = itemService.getItemById(searchId);
            if (foundItem != null) {
                items = Collections.singletonList(foundItem);
            } else {
                items = Collections.emptyList();
            }
        } else {
            // Обработка запроса отображения всех элементов
            items = itemService.list(sortBy, sortOrder);
        }

        model.addAttribute("items", items);
        model.addAttribute("sortOrder", sortOrder);

        System.out.println("Number of items: " + items.size());
        return "items";
    }


    @GetMapping("/item/{id}")
    public String itemInfo(@PathVariable int id, Model model){
        model.addAttribute("item", itemService.getItemById(id));

        return "item-info";
    }
    @GetMapping("/add-item")
    public String showAddItemForm(Model model) {
        model.addAttribute("item", new Item());
        return "add-item";
    }

    @PostMapping("/save-item")
    public String saveItem(@ModelAttribute Item item) {
        itemService.saveItem(item);
        return "redirect:/";

    }

    @PostMapping("/item/update")
    public String updateItem(@RequestBody Map<Object, Object> data) {
        System.out.println(data.get("name"));

        String id = (String) data.get("id");
        String code = (String)data.get("code");
        String price = (String)data.get("price");
        String name = (String)data.get("name");
        String category = (String)data.get("category");
        String unit = (String)data.get("unit");
        String location = (String)data.get("location");
        String status = (String)data.get("status");
        String note = (String)data.get("note");
        String country = (String)data.get("country");
        String supplier = (String)data.get("supplier");
        String receiptDate = (String)data.get("receiptDate");
        String saleDate = (String) data.get("saleDate");
        Item item = new Item(Integer.parseInt(id), Integer.parseInt(code), Float.parseFloat(price), name, category, null, unit, location, status, note, country, supplier, Date.valueOf(receiptDate), Date.valueOf(saleDate));
        System.out.println(item.toString());
        itemService.updateItem(item);
        return "redirect:/";
    }

    @PostMapping("/item/delete/{id}")
    public String deleteItem(@PathVariable int id){
        itemService.deleteItem(id);
        return "redirect:/";
    }
}
