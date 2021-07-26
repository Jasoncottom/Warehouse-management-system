package Main;

import java.sql.*;
public class DBcon {
    public ResultSet Db (String sql){
        String driverName = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/Warehouse management?useSSL=false&characterEncoding=UTF-8";
        String userName = "root";
        String userPwd = "6666";
        Connection dbConn = null;
        Statement stmt = null;
        ResultSet rs=null;
        {
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt =dbConn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                rs=stmt.executeQuery(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return rs;

        }
    }


    public int Dbin (String sql){
        String driverName = "com.mysql.jdbc.Driver";
        String dbURL = "jdbc:mysql://localhost:3306/Warehouse management?useSSL=false&characterEncoding=UTF-8";
        String userName = "root";
        String userPwd = "6666";
        Connection dbConn = null;
        Statement stmt = null;
        int in = 0;
        {
            try {
                Class.forName(driverName);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                stmt =dbConn.createStatement();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            try {
                in=stmt.executeUpdate(sql);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            return in;

        }
    }
}
