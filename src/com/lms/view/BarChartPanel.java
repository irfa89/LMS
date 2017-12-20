package com.lms.view;

import com.lms.database.RowCount;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class BarChartPanel extends JPanel {

    private static final long serialVersionUID = 1L;

    protected int countBook,countCD,countVideo;
    private Box vMainBox,hBox1,hBox2;
    private JButton bRefresh;

        RowCount count = new RowCount();

        JFreeChart barChart = ChartFactory.createBarChart(
                "      LIBRARY ITEMS   ",
                " CATEGORY OF ITEMS ",
                " NUMBER OF ITEMS ",
                createDataset(),
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);


    private CategoryDataset createDataset() throws SQLException {
        final String book = "BOOK";
        final String cd = "CD";
        final String video = "Video";
        final String libItems = " ";

        DefaultCategoryDataset dataset = new DefaultCategoryDataset( );

        dataset.addValue(count.bookCount(), book,libItems);
        dataset.addValue(count.CdCount() ,cd,libItems);
        dataset.addValue(count.videoCount(),video,libItems);

        return dataset;
    }

    BarChartPanel() throws SQLException {
        super(new FlowLayout());
        initBoxes();
        addBoxes();
        barChart.setBackgroundPaint(Color.WHITE);
        chartPanel.setPreferredSize(new java.awt.Dimension(370, 600));
        setBackground(new Color(194,230,248));
    }

    protected void initBoxes() {

        vMainBox     = Box.createVerticalBox();

        hBox1        = Box.createHorizontalBox();
        hBox2        = Box.createHorizontalBox();

        bRefresh     = new JButton("REFRESH");

        bRefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    countBook = count.bookCount();
                    countCD = count.CdCount();
                    countVideo = count.videoCount();

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void addBoxes(){

        hBox1.add(chartPanel);
       // hBox1.add(Box.createHorizontalStrut(15));
       // hBox2.add(bRefresh);

        vMainBox.add(hBox1);
        //vMainBox.add(Box.createVerticalStrut(15));
        //vMainBox.add(hBox2);
        add(vMainBox);

    }

}
