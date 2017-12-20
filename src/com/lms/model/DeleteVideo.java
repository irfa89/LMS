package com.lms.model;


import com.lms.database.Sel_DB;
import com.lms.database.Type_DB;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteVideo {

    public void DeleteRowDB(JTable TableName, String columnValue) throws SQLException {

        Connection lms = null;
        Statement st = null;

        try{
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query     = "DELETE FROM LMS_VIDEO WHERE ISBN ='"+columnValue+"'";

            st=lms.createStatement();
            st.executeUpdate(query);

        }catch (SQLException ex){

            Sel_DB.ErrorMessage(ex);

        }finally {

            st.close();
            lms.close();
        }
    }
}
