package com.coursework.services;
import com.coursework.models.Cart;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class CartService {
    private static final String URL = "jdbc:mysql://localhost:3306/coursework";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7310";


    public List<Cart> list() {
        List<Cart> items = new ArrayList<>();

        String query = "SELECT * FROM Cart";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("Product_id");
                int code = resultSet.getInt("Product_code");
                String name = resultSet.getString("Product_name");
                String category = resultSet.getString("Product_category");
                String location = resultSet.getString("Product_location");
                String unit = resultSet.getString("Product_units");
                String status = resultSet.getString("Product_status");
                String note = resultSet.getString("Product_notes");
                Date receiptDate = resultSet.getDate("Receipt_date");
                Date saleDate = resultSet.getDate("Sale_date");
                Float price = resultSet.getFloat("Product_price");
                String country = resultSet.getString("Product_manufacturer_country");
                String supplier = resultSet.getString("Supplier");

                items.add(new Cart(id, code, price, name, category, unit, location, status, note, country, supplier, receiptDate, saleDate));
            }

        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }

        return items;
    }

    public void restoreItem(int id){
        String query = "SELECT * FROM Cart Where Product_ID = " + id;
        int code;
        String name;
        String category;
        String location;
        String unit;
        String status;
        String note;
        Date receiptDate;
        Date saleDate;
        Float price;
        String country;
        String supplier;
        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                code = resultSet.getInt("Product_code");
                name = resultSet.getString("Product_name");
                category = resultSet.getString("Product_category");
                location = resultSet.getString("Product_location");
                unit = resultSet.getString("Product_units");
                status = resultSet.getString("Product_status");
                note = resultSet.getString("Product_notes");
                receiptDate = resultSet.getDate("Receipt_date");
                saleDate = resultSet.getDate("Sale_date");
                price = resultSet.getFloat("Product_price");
                country = resultSet.getString("Product_manufacturer_country");
                supplier = resultSet.getString("Supplier");

                statement.executeUpdate("insert into Products(Product_ID, Product_name, Product_category, Product_code) values("+id +", '"+ name +"', '"+category+"',"+ code+")");
                statement.executeUpdate("insert into Prices values("+ id+", "+ price +")");
                statement.executeUpdate("insert into Dates values("+ id+", '"+ receiptDate +"', '"+ saleDate+"')");
                statement.executeUpdate("INSERT INTO Stock VALUES (" + id + ", '" + location + "', '" + unit + "', '" + status + "', '" + note + "')");
                statement.executeUpdate("insert into Manufacturers values("+ code+", '"+ country + "', '"+ supplier+"')");
            }



        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }


        deleteItem(id);
    }

    public void deleteItem(int id){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()
        ){
            statement.executeUpdate("delete from Cart where Product_id ="+ id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
