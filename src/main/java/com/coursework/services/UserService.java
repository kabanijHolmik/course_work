package com.coursework.services;

import com.coursework.models.Item;
import com.coursework.models.User;
import org.springframework.stereotype.Service;


import java.util.List;
import java.sql.*;
import java.util.ArrayList;

import static com.coursework.MD5Utils.generateMD5;

@Service
public class UserService {
    private static final String URL = "jdbc:mysql://localhost:3306/coursework";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7310";

    public List<User> list(){
        List<User> users = new ArrayList<>();

        String query = "select * from Users";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String password = resultSet.getString("password_hash");
                String login = resultSet.getString("username");

                users.add(new User(login, password, id));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return users;
    }

    public void saveUser(User user){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()
        ){
            statement.executeUpdate("insert into Users(username, password_hash) values('"+ user.getLogin()+"', md5('"+ user.getPassword()+"'))");

        }
        catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());
        }
    }

    public void deleteUser(int id){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()
        ){
            statement.executeUpdate("delete from Users where id ="+ id);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User user){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement()){
            statement.executeUpdate("Update Users set username = '"+ user.getLogin() +"', password_hash = '" + user.getPassword()+"' where id = " + user.getId());
        } catch (SQLException e) {
            System.err.println("Ошибка при подключении к БД: " + e.getMessage());

        }

    }

    public User getUserById(int id) {

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

        ){
            ResultSet resultSet = statement.executeQuery("select * from Users where id = " + id);

            if (resultSet.next()){
                String password = resultSet.getString("password_hash");
                String login = resultSet.getString("username");

                User user = new User(login, password, id);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }
    public static User getUserByUsername(String username) {

        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

        ){
            ResultSet resultSet = statement.executeQuery("select * from Users where username = '" + username + "'");

            if (resultSet.next()){
                String password = resultSet.getString("password_hash");
                int id = resultSet.getInt("id");

                User user = new User(username, password, id);
                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return null;
    }

    public Boolean checkUser(User user){
        user.setPassword(generateMD5(user.getPassword()));
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

        ){
            ResultSet resultSet = statement.executeQuery("select * from Users where username = '" + user.getLogin() + "';");

            if (resultSet.next()){
                String password = resultSet.getString("password_hash");
                String login = resultSet.getString("username");

                User userCheck = new User(login, password, user.getId());
                return user.equals(userCheck);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public Boolean hasSameUsername(User user){
        try(Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

        ){
            ResultSet resultSet = statement.executeQuery("select count(*) as n from Users where username = '" + user.getLogin() + "';");

            if (resultSet.next()){
                if (resultSet.getInt("n") > 0)
                    return true;
                else return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }
}
