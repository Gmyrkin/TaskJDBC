package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    public static Connection getConnection () {
        String url = "jdbc:mysql://localhost:3306/exmple?serverTimezone=UTC";
        String username = "root";
        String password = "ЫгкаЦфму1";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
//          Statement statement = connection.createStatement();
//          ResultSet resultSetSet = statement.executeQuery( "SELECT * FROM user");
//          while (resultSetSet.next()){
//                System.out.println(resultSetSet.getString("name"));
//            }
            return connection;

        } catch (SQLException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        return null;
    }
}
