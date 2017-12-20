package com.lms.view;

import com.lms.model.DeleteBook;
import com.lms.model.AddCD;
import com.lms.model.DeleteCD;
import com.lms.model.ViewCD;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddCDPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ModelTable CDTable;

    private String Title,Author,Isbn,Composer,TrackingId;

    private Box vMainBox,hBox1,hBox2,hBox3,hBox4,hBox5,hBox6,hBox7;
    private JLabel jlTitle,jlAuthor,jlIsbn,jlComposer,jlTrackingId;
    private JTextField jtTitle,jtAuthor,jtIsbn,jtComposer,jtTrackingId;
    private JButton bAdd,bClear,bUpdate,bDelete;
    private JScrollPane jsCDs;
    private JTable tCDs;

    String[] CDColumns = {"TITLE","AUTHOR","ISBN","COMPOSER","TRACKINGID"};
    String[][] CDData  = {{"","","","",""}};

    public AddCDPanel(){
        super(new FlowLayout());
        initBoxes();
        addBoxes();
        setBackground(new Color(194,230,248));
    }

    private void initBoxes(){

        vMainBox = Box.createVerticalBox();

        hBox1           = Box.createHorizontalBox();
        hBox2           = Box.createHorizontalBox();
        hBox3           = Box.createHorizontalBox();
        hBox4           = Box.createHorizontalBox();
        hBox5           = Box.createHorizontalBox();
        hBox6           = Box.createHorizontalBox();
        hBox7           = Box.createHorizontalBox();

        jlTitle         = new JLabel("Title : ");
        jlAuthor        = new JLabel("Author : ");
        jlIsbn          = new JLabel("ISBN : ");
        jlComposer      = new JLabel("Composer : ");
        jlTrackingId    = new JLabel("Tracking Id : ");

        CDTable = new ModelTable(CDData,CDColumns);
        tCDs    = new JTable(CDTable);
        tCDs.setPreferredScrollableViewportSize(new Dimension(328,350));
        tCDs.setFillsViewportHeight(true);
        tCDs.setAutoCreateRowSorter(true);
        tCDs.getTableHeader().setReorderingAllowed(false);
        tCDs.getColumnModel().getColumn(0).setPreferredWidth(200);
        tCDs.getColumnModel().getColumn(1).setPreferredWidth(150);
        tCDs.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jsCDs = new JScrollPane(tCDs);
        jsCDs.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsCDs.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jtTitle         = new JTextField(15);
        jtAuthor        = new JTextField(15);
        jtIsbn          = new JTextField(15);
        jtComposer      = new JTextField(15);
        jtTrackingId    = new JTextField(15);

        jtTitle.setHorizontalAlignment(JTextField.LEFT);
        jtAuthor.setHorizontalAlignment(JTextField.LEFT);
        jtIsbn.setHorizontalAlignment(JTextField.LEFT);
        jtComposer.setHorizontalAlignment(JTextField.LEFT);
        jtTrackingId.setHorizontalAlignment(JTextField.LEFT);

        bClear         = new JButton(" CLEAR ");
        bAdd           = new JButton(" ADD ");
        bUpdate        = new JButton(" UPDATE ");
        bDelete        = new JButton(" DELETE ");

        bClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtAuthor.setText("");
                jtIsbn.setText("");
                jtTitle.setText("");
                jtComposer.setText("");
                jtTrackingId.setText("");
            }
        });

        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initTextField();
                AddCD CD = new AddCD();
                try {
                    CD.InsertOraDB(Title,Author,Isbn,Composer,TrackingId);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageBox Alert = new MessageBox();
                String removeRow = (String) tCDs.getValueAt(tCDs.getSelectedRow(), 2);
                DeleteCD rowDel = new DeleteCD();
                if(tCDs.getSelectedRow() != -1 ){
                    String message = "Entry Deleted ";
                    try {
                        rowDel.DeleteRowDB(tCDs,removeRow);
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    Alert.updateMsg(message,"Delete Operation");
                }
                else{
                    String message = "Select Row to be Deleted ";
                    Alert.updateMsg(message,"Delete Operation");

                }

            }
        });

        bUpdate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ViewCD CD = new ViewCD();
                try{
                    CD.SelectOraDB(tCDs);
                }catch (SQLException e2){
                    e2.printStackTrace();
                }
            }
        });

    }

    private void initTextField(){
            Title         = jtTitle.getText();
            Author        = jtAuthor.getText();
            Isbn          = jtIsbn.getText();
            Composer      = jtComposer.getText();
            TrackingId    = jtTrackingId.getText();
        }


    private void addBoxes(){

        hBox1.add(jlTitle);
        hBox1.add(Box.createHorizontalStrut(15));
        hBox1.add(jtTitle);
        hBox2.add(jlAuthor);
        hBox2.add(Box.createHorizontalStrut(15));
        hBox2.add(jtAuthor);
        hBox3.add(jlIsbn);
        hBox3.add(Box.createHorizontalStrut(15));
        hBox3.add(jtIsbn);
        hBox4.add(jlComposer);
        hBox4.add(Box.createHorizontalStrut(15));
        hBox4.add(jtComposer);
        hBox5.add(jlTrackingId);
        hBox5.add(Box.createHorizontalStrut(15));
        hBox5.add(jtTrackingId);
        hBox6.add(bUpdate);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bClear);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bDelete);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bAdd);
        hBox7.add(jsCDs);

        vMainBox.add(hBox1);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox2);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox3);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox4);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox5);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox6);
        vMainBox.add(Box.createVerticalStrut(10));
        vMainBox.add(hBox7);
        add(vMainBox);
    }

}
