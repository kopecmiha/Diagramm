package com.company;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.ChartFactory;

import org.jfree.ui.RectangleInsets;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;

class Dataset
{
    public static PieDataset createPieDataset(double[] expenses, String[] sections)
    {
        DefaultPieDataset dataset = new DefaultPieDataset();
        for (int i = 0; i < sections.length; i++)
            if (sections[i] != "None") {
                dataset.setValue(sections[i], new Double(expenses[i]));
            }
        System.out.println(dataset);
        return dataset;
    }
}

public class PieChartDemo extends ApplicationFrame
{
    private static final long serialVersionUID = 1L;

    int i = 0;
    private PieDataset  dataset = null;
    JFreeChart  chart = null;

    public PieChartDemo(String title) {
        super(title);
        setContentPane(createInputPanel());

    }

    public JPanel createInputPanel()
    {

        String[] SECTIONS = {"None","None","None","None","None","None","None","None","None","None"};
        double[] EXPENSES = {0,0,0,0,0,0,0,0,0,0};

        JPanel panel = new JPanel();

        JButton Button_Create = new JButton("Создать");
        panel.add(Button_Create);

        JButton Button_Input = new JButton("Ввод");
        panel.add(Button_Input);

        JButton Button_Clear = new JButton("Очистить");
        panel.add(Button_Clear);



        JPanel panel3 = new JPanel();
        panel3.add(new JLabel("Параметр"));
        panel3.setPreferredSize(new Dimension(600, 50));
        panel.add(panel3);
        JTextField paraField = new JTextField();
        paraField.setPreferredSize(new Dimension(100, 20));
        panel3.add(paraField);



        JPanel panel4 = new JPanel();
        panel4.add(new JLabel("Значение"));
        JTextField numberField = new JTextField();
        numberField.setPreferredSize(new Dimension(100, 20));
        panel4.add(numberField);
        panel4.setPreferredSize(new Dimension(600, 50));
        panel.add(panel4);

        JPanel panel2 = new JPanel();
        createdia(EXPENSES, SECTIONS, panel2);
        panel.add(panel2);

        panel.setPreferredSize(new Dimension(600, 650));

        Button_Input.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SECTIONS[i] = paraField.getText();
                paraField.setText("");
                EXPENSES[i] = Integer.parseInt(numberField.getText());
                numberField.setText("");
                i = i + 1;
                    }
        });

        Button_Clear.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < 10; i++){
                    SECTIONS[i] = "None";
                    EXPENSES[i] = 0;
                }
                createdia(EXPENSES, SECTIONS, panel2);
            }
        });

        Button_Create.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                createdia(EXPENSES, SECTIONS, panel2);
            }
        });

        return panel;
    }

    public static void main(String[] args)
    {
        PieChartDemo window = new PieChartDemo("My Diagram");
        window.pack();
        RefineryUtilities.centerFrameOnScreen(window);
        window.setVisible(true);
    }

    public void createdia(double[] EXPENSES,String[] SECTIONS, JPanel panel2)
    {
        dataset = Dataset.createPieDataset(EXPENSES, SECTIONS);
        chart = ChartFactory.createRingChart("Диаграмма", dataset, true, true, true);
        chart.setPadding(new RectangleInsets(4, 8, 2, 2));
        ChartPanel panel2_2 = new ChartPanel(chart);
        panel2_2.setPreferredSize(new Dimension(400, 400));
        panel2.removeAll();
        panel2.revalidate();
        panel2.add(panel2_2);
    }
}