package com.coursework.services;

import com.coursework.models.Item;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {
    private static final String URL = "jdbc:mysql://localhost:3306/coursework";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7310";

    public List<Item> list(String sortBy, String sortOrder) {
        List<Item> items = new ArrayList<>();

        String query = "select * from Products left join Stock using(product_id) left join Dates using(Product_id) left join Prices using(Product_id) left join Manufacturers using(Product_code)";

        if (sortBy == null || sortBy.isEmpty()) {
            sortBy = "Product_id"; // Сортировка по умолчанию по полю "id"
        }

        query += " order by " + sortBy;

        if (sortOrder != null && !sortOrder.isEmpty() && ("ASC".equalsIgnoreCase(sortOrder) || "DESC".equalsIgnoreCase(sortOrder))) {
            query += " " + sortOrder;
        } else {
            sortOrder = "ASC"; // Порядок сортировки по умолчанию - возрастающий
            query += " " + sortOrder;
        }

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Product_id");
                int code = resultSet.getInt("Product_code");
                String name = resultSet.getString("Product_name");
                String category = resultSet.getString("Product_category");
                Blob photo = resultSet.getBlob("Product_photo");
                String location = resultSet.getString("Product_location");
                String unit = resultSet.getString("Product_units");
                String status = resultSet.getString("Product_status");
                String note = resultSet.getString("Product_notes");
                Date receiptDate = resultSet.getDate("Receipt_date");
                Date saleDate = resultSet.getDate("Sale_date");
                Float price = resultSet.getFloat("Product_price");
                String country = resultSet.getString("Product_manufacturer_country");
                String supplier = resultSet.getString("Supplier");

                items.add(new Item(id, code, price, name, category, photo, unit, location, status, note, country, supplier, receiptDate, saleDate));
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }

        return items;
    }


    public void saveItem(Item item){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()
        ){
            System.out.println(item.getPhoto());
            statement.executeUpdate("insert into Products(Product_name, Product_category, Product_code) values('"+ item.getName() +"', '"+ item.getCategory()+"',"+ item.getCode()+")");

            try(ResultSet resultSet = statement.executeQuery("Select max(Product_ID) as Product_ID from Products")) {
                if (resultSet.next()){
                    item.setId(resultSet.getInt(1));
                }
            }
            statement.executeUpdate("insert into Prices values("+ item.getId()+", "+ item.getPrice() +")");
            statement.executeUpdate("insert into Dates values("+ item.getId()+", '"+ item.getReceiptDay() +"', '"+ item.getSaleDate()+"')");
            statement.executeUpdate("INSERT INTO Stock VALUES (" + item.getId() + ", '" + item.getLocation() + "', '" + item.getUnit() + "', '" + item.getStatus() + "', '" + item.getNote() + "')");
            statement.executeUpdate("insert into Manufacturers values("+ item.getCode()+", '"+ item.getCountry() + "', '"+ item.getSupplier()+"')");
        }
        catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }
    }

    public void deleteItem(int id){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()
        ){
            statement.executeUpdate("delete from Products where Product_id ="+ id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateItem(Item item){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Statement statement = connection.createStatement();){
            System.out.println(item.getNote());
            statement.executeUpdate("Update Products set Product_code =" + item.getCode() + ", Product_name = '" + item.getName() + "', Product_category = '" + item.getCategory() +"' where Product_ID = " + item.getId());
            statement.executeUpdate("Update Prices set Product_price =" + item.getPrice() + " where Product_ID = " + item.getId());
            statement.executeUpdate("Update Stock set Product_units = '" + item.getUnit() + "', Product_status = '" + item.getStatus() + "', Product_notes = '" + item.getNote() +"', Product_location = '" + item.getLocation() +"' where Product_ID = " + item.getId());
            statement.executeUpdate("Update Manufacturers set Product_manufacturer_country = '" + item.getCountry() + "', Supplier = '" + item.getSupplier() + "' where Product_code = " + item.getCode());
            statement.executeUpdate("Update Products set Product_code =" + item.getCode() + ", Product_name = '" + item.getName() + "', Product_category = '" + item.getCategory() +"' where Product_ID = " + item.getId());
            statement.executeUpdate("Update Dates set Sale_date ='" + item.getSaleDate() + "', Receipt_date = '" + item.getReceiptDay() + "' where Product_ID = " + item.getId());



        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());

        }

    }

    public Item getItemById(int id) {

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

        ){
                ResultSet resultSet = statement.executeQuery("select * from Products left join Stock using(product_id) left join Dates using(Product_id) left join Prices using(Product_id) left join Manufacturers using(Product_code) where Product_id = " + id);

                if (resultSet.next()){
                    int code = resultSet.getInt("Product_code");
                    String name = resultSet.getString("Product_name");
                    String category = resultSet.getString("Product_category");
                    Blob photo = resultSet.getBlob("Product_photo");
                    String location = resultSet.getString("Product_location");
                    String unit = resultSet.getString("Product_units");
                    String status = resultSet.getString("Product_status");
                    String note = resultSet.getString("Product_notes");
                    Date receiptDate = resultSet.getDate("Receipt_date");
                    Date saleDate = resultSet.getDate("Sale_date");
                    Float price = resultSet.getFloat("Product_price");
                    String country = resultSet.getString("Product_manufacturer_country");
                    String supplier = resultSet.getString("Supplier");

                    Item item = new Item(id, code, price, name, category, photo, unit, location, status, note, country, supplier, receiptDate, saleDate);
                    return item;
                }




        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

}
