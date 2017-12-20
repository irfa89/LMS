package com.lms.controller;

import com.lms.view.ItemsInterface;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class LibraryItems implements ActionListener {

    private static final long serialVersionUID = 1L;

    private ItemsInterface addScreen;

    public LibraryItems() throws SQLException {
        addScreen = new ItemsInterface(" LIBRARY - QUICK VIEW ");
        addScreen.getjTabPane();
        addScreen.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
