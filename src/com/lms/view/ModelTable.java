package com.lms.view;

import javax.swing.table.DefaultTableModel;

public class ModelTable extends DefaultTableModel{

    private static final long serialVersionUID = 1L;

    public ModelTable(String[][] data, String[] columns) {

        super(data,columns);
    }

    @Override
    public boolean isCellEditable(int rows, int columns) {

        return false;
    }

}
