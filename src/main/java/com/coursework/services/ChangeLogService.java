package com.coursework.services;

import com.coursework.models.ChangeLog;
import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChangeLogService {
    private static final String URL = "jdbc:mysql://localhost:3306/coursework";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "7310";

    public List<ChangeLog> list(){
        List<ChangeLog> logs = new ArrayList<>();

        String query = "select * from ChangeLog";

        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)
        ) {
            while (resultSet.next()) {
                Long ChangeLog_ID = resultSet.getLong("ChangeLog_ID");
                String TableName = resultSet.getString("TableName");
                Long RecordID = resultSet.getLong("RecordID");
                String ChangeType = resultSet.getString("ChangeType");

                logs.add(new ChangeLog(ChangeLog_ID, TableName, RecordID, ChangeType));
            }

        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }

        return logs;
    }

}
