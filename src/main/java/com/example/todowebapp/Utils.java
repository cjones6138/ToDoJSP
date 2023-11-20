package com.example.todowebapp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Utils {
    public static void addToDB(String str) throws Exception {
        Connection conn = getConnection();

        PreparedStatement posted = conn.prepareStatement(
                "INSERT INTO todo(tasks) VALUES ('" + str + "')"
        );
        posted.executeUpdate();
    }
    public static void removeFromDB(String str) throws Exception{
        Connection conn = getConnection();

        PreparedStatement remove = conn.prepareStatement(
                "DELETE FROM todo WHERE id='" + Integer.parseInt(str) + "'"
        );
        remove.executeUpdate();
    }
    public static ResultSet displayDB() throws Exception{
        Connection conn = getConnection();

        PreparedStatement statement = conn.prepareStatement(
                "SELECT id, tasks "
                        + "FROM todo "
                        + "ORDER BY id DESC ");
        ResultSet result = statement.executeQuery();

        return result;
    }
    public static void createTable() throws Exception {
        try {
            Connection conn = getConnection();
            PreparedStatement create = conn.prepareStatement(
                    "CREATE TABLE IF NOT EXISTS ToDo("
                            + "id int NOT NULL AUTO_INCREMENT, "
                            + "tasks varchar(255), "
                            + "PRIMARY KEY(id))");
            create.executeUpdate();
        } catch(Exception e) {System.out.println(e);}
    }
    public static Connection getConnection() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            String url = "jdbc:mysql://localhost:3306/todo_list";
            String username = "cjones";
            String password = "password123";

            Connection conn = DriverManager.getConnection(url, username, password);
            return conn;
        } catch(Exception e) {System.out.println(e);}

        return null;

    }


}
