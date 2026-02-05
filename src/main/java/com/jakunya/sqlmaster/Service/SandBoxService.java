package com.jakunya.sqlmaster.Service;

import org.springframework.stereotype.Service;

import java.sql.*;
import java.util.*;

@Service
public class SandBoxService {

    List<Map<String, Object>> listQuery(String initScript, String query) throws SQLException {
        String url = "jdbc:h2:mem:" + UUID.randomUUID();
        try(Connection connection = DriverManager.getConnection(url,"sa","");) {
            Statement stmt = connection.createStatement();
            stmt.execute(initScript);
            ResultSet res = stmt.executeQuery(query);
            ResultSetMetaData metaData = res.getMetaData();
            int columnCount = metaData.getColumnCount();
            List<Map<String, Object>> result = new ArrayList<>();
            while (res.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i =1; i <= columnCount; i++){
                    row.put(metaData.getColumnLabel(i), res.getObject(i));
                }
                result.add(row);
            }
            return result;
        }
    }

    boolean compareResults(String initScript, String userQuery, String correctQuery) {
        try {
            List<Map<String, Object>> userList = listQuery(initScript, userQuery);
            List<Map<String, Object>> correctList = listQuery(initScript, correctQuery);
            return correctList.equals(userList);
        } catch (SQLException e) {
            return false;
        }
    }
}
