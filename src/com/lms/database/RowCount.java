package com.lms.database;

import com.sun.org.apache.regexp.internal.RE;

import java.sql.*;

public class RowCount {

//    Connection lms;
//    Statement st;
//    ResultSet rs;
//
//    public void RowCount(){
//        Connection lms  = null;
//        Statement st    = null;
//        ResultSet rs    = null;
//    }

    public int bookCount() throws SQLException {
        Connection lms = null;
        Statement st= null;
        ResultSet rs  = null;
            int count =0;
        try {
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query = "SELECT COUNT(*) AS ROWCOUNT FROM LMS_BOOK";
            st = lms.createStatement();
            rs =st.executeQuery(query);
            rs.next();
            count = rs.getInt("ROWCOUNT");

        }catch (SQLException e){
            Sel_DB.ErrorMessage(e);
        }finally {
            rs.close();
            lms.close();
        }
        return count;
    }

    public int CdCount() throws SQLException {
        Connection lms = null;
        Statement st= null;
        ResultSet rs  = null;
        int count =0;
        try {
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query = "SELECT COUNT(*) AS ROWCOUNT FROM LMS_CD";
            st = lms.createStatement();
            rs =st.executeQuery(query);
            rs.next();
            count = rs.getInt("ROWCOUNT");

        }catch (SQLException e){
            Sel_DB.ErrorMessage(e);
        }finally {
            rs.close();
            lms.close();
        }
        return count;
    }

    public int videoCount() throws SQLException {
        Connection lms = null;
        Statement st= null;
        ResultSet rs  = null;
        int count =0;
        try {
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query = "SELECT COUNT(*) AS ROWCOUNT FROM LMS_VIDEO";
            st = lms.createStatement();
            rs =st.executeQuery(query);
            rs.next();
            count = rs.getInt("ROWCOUNT");

        }catch (SQLException e){
            Sel_DB.ErrorMessage(e);
        }finally {
            rs.close();
            lms.close();
        }
        return count;
    }
}
