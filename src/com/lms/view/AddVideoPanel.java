package com.lms.view;

import com.lms.model.AddVideo;
import com.lms.model.DeleteVideo;
import com.lms.model.ViewVideo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddVideoPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    private ModelTable VideoTable;

    private String Title,Author,Isbn,Director,TrackingId;

    private Box vMainBox,hBox1,hBox2,hBox3,hBox4,hBox5,hBox6,hBox7;
    private JLabel jlTitle,jlAuthor,jlIsbn,jlDirector,jlTrackingId;
    private JTextField jtTitle,jtAuthor,jtIsbn,jtDirector,jtTrackingId;
    private JButton bAdd,bClear,bUpdate,bDelete;
    private JScrollPane jsVideos;
    private JTable tVideos;

    String[] VideoColumns = {"TITLE","AUTHOR","ISBN","DIRECTOR","TRACKINGID"};
    String[][] VideoData  = {{"","","","",""}};

    public AddVideoPanel(){
        super(new FlowLayout());
        initBoxes();
        addBoxes();
        setBackground(new Color(194,230,248));
    }

    private void initBoxes(){

        vMainBox = Box.createVerticalBox();

        hBox1 = Box.createHorizontalBox();
        hBox2 = Box.createHorizontalBox();
        hBox3 = Box.createHorizontalBox();
        hBox4 = Box.createHorizontalBox();
        hBox5 = Box.createHorizontalBox();
        hBox6 = Box.createHorizontalBox();
        hBox7 = Box.createHorizontalBox();

        jlTitle         = new JLabel("Title : ");
        jlAuthor        = new JLabel("Author : ");
        jlIsbn          = new JLabel("ISBN : ");
        jlDirector      = new JLabel("Director : ");
        jlTrackingId    = new JLabel("Tracking Id : ");

        VideoTable = new ModelTable(VideoData,VideoColumns);
        tVideos    = new JTable(VideoTable);
        tVideos.setPreferredScrollableViewportSize(new Dimension(328,350));
        tVideos.setFillsViewportHeight(true);
        tVideos.setAutoCreateRowSorter(true);
        tVideos.getTableHeader().setReorderingAllowed(false);
        tVideos.getColumnModel().getColumn(0).setPreferredWidth(200);
        tVideos.getColumnModel().getColumn(1).setPreferredWidth(150);
        tVideos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jsVideos = new JScrollPane(tVideos);
        jsVideos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsVideos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jtTitle         = new JTextField(15);
        jtAuthor        = new JTextField(15);
        jtIsbn          = new JTextField(15);
        jtDirector      = new JTextField(15);
        jtTrackingId    = new JTextField(15);

        jtTitle.setHorizontalAlignment(JTextField.LEFT);
        jtAuthor.setHorizontalAlignment(JTextField.LEFT);
        jtIsbn.setHorizontalAlignment(JTextField.LEFT);
        jtDirector.setHorizontalAlignment(JTextField.LEFT);
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
                jtDirector.setText("");
                jtTrackingId.setText("");
            }
        });

        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                initTextField();
                AddVideo Video = new AddVideo();
                try {
                    Video.InsertOraDB(Title,Author,Isbn,Director,TrackingId);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }

            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageBox Alert = new MessageBox();
                String removeRow = (String) tVideos.getValueAt(tVideos.getSelectedRow(), 2);
                DeleteVideo rowDel = new DeleteVideo();
                if(tVideos.getSelectedRow() != -1 ){
                    String message = "Entry Deleted ";
                    try {
                        rowDel.DeleteRowDB(tVideos,removeRow);
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
                ViewVideo Video = new ViewVideo();
                try{
                    Video.SelectOraDB(tVideos);
                }catch(SQLException e2){
                    e2.printStackTrace();
                }
            }
        });

    }

    private void initTextField(){
        Title         = jtTitle.getText();
        Author        = jtAuthor.getText();
        Isbn          = jtIsbn.getText();
        Director      = jtDirector.getText();
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
        hBox4.add(jlDirector);
        hBox4.add(Box.createHorizontalStrut(15));
        hBox4.add(jtDirector);
        hBox5.add(jlTrackingId);
        hBox5.add(Box.createHorizontalStrut(15));
        hBox5.add(jtTrackingId);
        hBox6.add(Box.createHorizontalStrut(15));
        hBox6.add(bUpdate);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bClear);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bDelete);
        hBox6.add(Box.createHorizontalStrut(10));
        hBox6.add(bAdd);
        hBox7.add(jsVideos);

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
