package com.lms.model;
import java.sql.*;
import com.lms.database.*;
import com.lms.view.MessageBox;

public class AddBook {

    public void InsertOraDB(String Title,String Author,String Isbn,String TrackingId) throws SQLException{
        Connection lms=null;
        PreparedStatement prpStmt=null;
        try{
        lms = Sel_DB.getConnection(Type_DB.Oracle_DB);
        String query     = "INSERT into LMS_Book values (?,?,?,?)";
        prpStmt = lms.prepareStatement(query);

            prpStmt.setString(1,Title);
            prpStmt.setString(2,Author);
            prpStmt.setString(3,Isbn);
            prpStmt.setString(4,TrackingId);

            int status = prpStmt.executeUpdate();
            MessageBox popupMsg = new MessageBox();

            if(status == 1) {
                String msgSuccess = "Books Details entry added Successfully. ";
                System.out.println(msgSuccess);
                popupMsg.updateMsg(msgSuccess,"Book information");
            }
            else {
                String msgNotSuccess = "Error while updating the Book Details";
                System.err.println(msgNotSuccess);
                popupMsg.updateMsg(msgNotSuccess,"Book information");
            }

    }catch (SQLException ex){
        Sel_DB.ErrorMessage(ex);
        }finally {
            prpStmt.close();
            lms.close();
        }

    }

}

