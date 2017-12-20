package com.lms.view;

import com.lms.model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.SQLException;

public class AddBookPanel extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ModelTable bookTable;

    private String Title,Author,Isbn,TrackingId;

    private Box vMainBox,hBox1,hBox2,hBox3,hBox4,hBox5,hBox6;
    private JLabel jlTitle,jlAuthor,jlIsbn,jlTrackingId;
    private JTextField jtTitle,jtAuthor,jtIsbn,jtTrackingId;
    private JButton bAdd,bClear,bUpdate,bDelete;
    private JScrollPane jsBook;
    private JTable tBooks;

    String[] bookColumns = {"TITLE","AUTHOR","ISBN","TRACKINGID"};
    String[][] bookData  = {{"","","",""}};

    public AddBookPanel(){
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

        jlTitle         = new JLabel("Title : ");
        jlAuthor        = new JLabel("Author : ");
        jlIsbn          = new JLabel("ISBN : ");
        jlTrackingId    = new JLabel("Tracking Id : ");

        bookTable = new ModelTable(bookData,bookColumns);
        tBooks    = new JTable(bookTable);
        tBooks.setPreferredScrollableViewportSize(new Dimension(328,350));
        tBooks.setFillsViewportHeight(true);
        tBooks.setAutoCreateRowSorter(true);
        tBooks.getTableHeader().setReorderingAllowed(false);
        tBooks.getColumnModel().getColumn(0).setPreferredWidth(200);
        tBooks.getColumnModel().getColumn(1).setPreferredWidth(150);
        tBooks.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jsBook = new JScrollPane(tBooks);
        jsBook.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jsBook.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jtTitle         = new JTextField(15);
        jtAuthor        = new JTextField(15);
        jtIsbn          = new JTextField(15);
        jtTrackingId    = new JTextField(15);


        jlTitle.setHorizontalAlignment(jlTitle.LEFT);
        jlAuthor.setHorizontalAlignment(jlAuthor.LEFT);
        jlIsbn.setHorizontalAlignment(jlIsbn.LEFT);
        jlTrackingId.setHorizontalAlignment(jlTrackingId.LEFT);

        jtTitle.setHorizontalAlignment((JTextField.LEFT));
        jtAuthor.setHorizontalAlignment(JTextField.LEFT);
        jtIsbn.setHorizontalAlignment(JTextField.LEFT);
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
                jtTrackingId.setText("");
            }
        });
        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
             initTextField();
             AddBook Book = new AddBook();
                try {
                    Book.InsertOraDB(Title,Author,Isbn,TrackingId);
                } catch (SQLException e1) {
                    e1.getMessage();
                }

            }
        });

        bDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MessageBox Alert = new MessageBox();
                String removeRow = (String) tBooks.getValueAt(tBooks.getSelectedRow(), 2);
                DeleteBook rowDel = new DeleteBook();
                if(tBooks.getSelectedRow() != -1 ){
                    String message = "Entry Deleted ";
                    try {
                        rowDel.DeleteRowDB(tBooks,removeRow);
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
                ViewBook Book = new ViewBook();
                try{
                    Book.SelectOraDB(tBooks);
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
        hBox4.add(jlTrackingId);
        hBox4.add(Box.createHorizontalStrut(15));
        hBox4.add(jtTrackingId);
        hBox5.add(Box.createHorizontalStrut(15));
        hBox5.add(bUpdate);
        hBox5.add(Box.createHorizontalStrut(10));
        hBox5.add(bClear);
        hBox5.add(Box.createHorizontalStrut(10));
        hBox5.add(bDelete);
        hBox5.add(Box.createHorizontalStrut(10));
        hBox5.add(bAdd);
        hBox6.add(jsBook);

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

        add(vMainBox);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    }
}
