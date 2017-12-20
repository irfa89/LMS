package com.lms.model;

import com.lms.database.*;
import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.sql.*;

public class ViewCD {

    public void SelectOraDB(JTable TableName) throws SQLException {

        Connection lms = null;
        PreparedStatement prpStmt=null;
        Statement st = null;
        ResultSet rs = null;

        try{
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query     = "SELECT * FROM LMS_CD";
            prpStmt = lms.prepareStatement(query);
            rs = prpStmt.executeQuery(query);
            TableName.setModel(DbUtils.resultSetToTableModel(rs));
        }catch (SQLException ex){

            Sel_DB.ErrorMessage(ex);

        }finally {

            rs.close();
            prpStmt.close();
            lms.close();
    }
    }
}
