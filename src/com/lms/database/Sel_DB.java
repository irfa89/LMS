package com.lms.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Sel_DB {

    static final String oracleUser = "HR";
    static final String oraclePwd = "password";
    static String mySQLUser = "root";
    static String mySQLPwd = "root";
    static String mySQLConn = "jdbc:mysql://localhost:3306/world";
    static final String oracleConn = "jdbc:oracle:thin:@localhost:1521:xe";

    public static Connection getConnection(Type_DB DBType) throws SQLException {
        switch (DBType) {
            case Oracle_DB:
                return DriverManager.getConnection(oracleConn, oracleUser, oraclePwd);
            case MySQL_DB:
                return DriverManager.getConnection(mySQLConn, mySQLUser, mySQLPwd);
            default:
                return null;
        }
    }

    public static void ErrorMessage(SQLException ex) {
        System.err.println("Error :"+ex.getMessage());
        System.err.println("Error :"+ex.getErrorCode());
    }
}
