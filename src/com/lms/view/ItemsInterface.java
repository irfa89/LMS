package com.lms.view;

import javax.swing.*;
import java.sql.SQLException;

public class ItemsInterface extends JFrame{

    private static final long serialVersionUID = 1L;

    private AddBookPanel  addBookPanel;
    private AddCDPanel    addCDPanel;
    private AddVideoPanel addVideoPanel;
    private BarChartPanel barChartPanel;
    private JTabbedPane   jTabPane;
    private String        Spaces;

    public ItemsInterface(String title) throws SQLException {

        super(title);

        jTabPane        = new JTabbedPane();
        addBookPanel    = new AddBookPanel();
        addCDPanel      = new AddCDPanel();
        addVideoPanel   = new AddVideoPanel();
        barChartPanel   = new BarChartPanel();

        Spaces = "     ";

        jTabPane.add(Spaces+" BOOK "+Spaces,addBookPanel);
        jTabPane.add(Spaces+" CD "+Spaces,addCDPanel);
        jTabPane.add(Spaces+" VIDEO "+Spaces,addVideoPanel);
        jTabPane.add(Spaces+ " NOI "+Spaces,barChartPanel);
        add(jTabPane);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400,675);
        setResizable(false);

    }

    public AddBookPanel getAddBookPanel() {

        return addBookPanel;
    }

    public AddCDPanel getAddCDPanel() {

        return addCDPanel;
    }

    public AddVideoPanel getAddVideoPanel() {

        return addVideoPanel;
    }

    public BarChartPanel getBarChartPanel() {
        return barChartPanel;
    }

    public JTabbedPane getjTabPane() {

        return jTabPane;
    }

    public String getSpaces() {

        return Spaces;
    }
}
