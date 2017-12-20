package com.lms.model;

import com.lms.database.Sel_DB;
import com.lms.database.Type_DB;
import com.lms.view.MessageBox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddCD {

    public void InsertOraDB(String Title, String Author, String Isbn, String Composer,String TrackingId) throws SQLException {
        Connection lms=null;
        PreparedStatement prpStmt=null;
        try{
            lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
            String query     = "INSERT into LMS_CD values (?,?,?,?,?)";
            prpStmt = lms.prepareStatement(query);

            prpStmt.setString(1,Title);
            prpStmt.setString(2,Author);
            prpStmt.setString(3,Isbn);
            prpStmt.setString(4,Composer);
            prpStmt.setString(5,TrackingId);

            int status = prpStmt.executeUpdate();
            MessageBox popupMsg = new MessageBox();

            if(status == 1) {
                String msgSuccess = "CD Details entry added Successfully. ";
                System.out.println(msgSuccess);
                popupMsg.updateMsg(msgSuccess,"CD information");

            }
            else {
                String msgNotSuccess = "Error while updating the CD Details";
                System.err.println(msgNotSuccess);
                popupMsg.updateMsg(msgNotSuccess,"CD information");
            }

        }catch (SQLException ex){
            Sel_DB.ErrorMessage(ex);
        }finally {
            prpStmt.close();
            lms.close();
        }

    }
}
