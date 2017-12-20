package com.lms.database;

import java.sql.*;

public class TestDBConnection {
    public static void main(String[] args) throws SQLException{

        Connection lms = null;
        try {

            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);

            System.out.println("Connection established to Oracle Database ");

        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }finally {
            lms.close();
        }
    }

}
