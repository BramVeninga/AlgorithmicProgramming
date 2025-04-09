package Analyse;

import Datasets.JsonLoader;
import Shooter.Shooter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.Comparator;

import Datastructures.MyArrayList;
import ShootingRange.ShooterDetailScreen;
import ShootingRange.StartScherm;
import SortingAlgorithms.MergeSort;

public class AnalysisScreen extends JFrame {
    private JTable shooterTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton checkShooterButton;
    private JButton analyzeShooterButton;
    private boolean isShooterIdValid = false;

    private MyArrayList<Shooter> shooterDataList;
    private JsonLoader jsonLoader;

    public AnalysisScreen(File file) {
        setTitle("Shooter Analysis - " + file.getName());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        jsonLoader = new JsonLoader();

        shooterDataList = jsonLoader.loadData(file.getPath());

        // Table Setup
        String[] columns = {"Shooter ID", "Total Shots Fired"};
        tableModel = new DefaultTableModel(columns, 0);
        shooterTable = new JTable(tableModel);
        populateTable(shooterDataList);
        add(new JScrollPane(shooterTable), BorderLayout.CENTER);

        // Controls Panel
        JPanel controlsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel topRow = new JPanel(new FlowLayout());
        JPanel bottomRow = new JPanel(new FlowLayout());

        JButton sortByShooterButton = new JButton("Sort by Shooter ID");
        JButton sortByShotsButton = new JButton("Sort by Shots Fired");
        searchField = new JTextField(10);
        checkShooterButton = new JButton("Check Shooter ID");
        analyzeShooterButton = new JButton("Analyze Shooter");
        JButton backButton = new JButton("Back");

        sortByShooterButton.addActionListener(e -> {
            MyArrayList<Shooter> sortedById = MergeSort.mergeSort(shooterDataList, Comparator.comparingInt(s -> s.schutter_ID));
            populateTable(sortedById);
        });

        sortByShotsButton.addActionListener(e -> {
            MyArrayList<Shooter> sortedById = MergeSort.mergeSort(shooterDataList, Comparator.comparingInt(s -> s.getTotalShots()));
            populateTable(sortedById);
        });

        checkShooterButton.addActionListener(this::checkShooterId);
        analyzeShooterButton.addActionListener(this::analyzeShooter);
        backButton.addActionListener(e -> {
            new StartScherm().setVisible(true);
            dispose();
        });

        topRow.add(sortByShooterButton);
        topRow.add(sortByShotsButton);

        bottomRow.add(new JLabel("Shooter ID:"));
        bottomRow.add(searchField);
        bottomRow.add(checkShooterButton);
        bottomRow.add(analyzeShooterButton);
        bottomRow.add(backButton);

        controlsPanel.add(topRow);
        controlsPanel.add(bottomRow);
        add(controlsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void populateTable(MyArrayList<Shooter> dataList) {
        tableModel.setRowCount(0);
        for (int i = 0; i < dataList.size(); i++)
        {
            tableModel.addRow(new Object[]{dataList.get(i).getSchutter_ID(), dataList.get(i).getTotalShots()});
        }
    }

    private void checkShooterId(ActionEvent e) {
          String shooterId = searchField.getText().trim();
          isShooterIdValid = false;

          if (shooterId.isEmpty()) {
              JOptionPane.showMessageDialog(this, "Please enter a Shooter ID.");

              return;
          }

          boolean found = false;

          for (int i = 0; i < shooterDataList.size(); i++)
          {
              String compareTo = String.valueOf(shooterDataList.get(i).schutter_ID);

              if (compareTo.equals(shooterId))
              {
                  found = true;
              }
          }

          if (found) {
              isShooterIdValid = true;
              JOptionPane.showMessageDialog(this, "Shooter ID is valid!");
          } else {
              JOptionPane.showMessageDialog(this, "Shooter ID not found.");
          }
    }

    private void analyzeShooter(ActionEvent e) {
        if (!isShooterIdValid) {
            JOptionPane.showMessageDialog(this, "Please validate the Shooter ID first.");
            return;
        }

        String shooterId = searchField.getText().trim();
        new ShooterDetailScreen(shooterId).setVisible(true);
        dispose();
    }

    // Sample data class
    static class ShooterData {
        private final String shooterId;
        private final int totalShots;

        public ShooterData(String shooterId, int totalShots) {
            this.shooterId = shooterId;
            this.totalShots = totalShots;
        }

        public String getShooterId() {
            return shooterId;
        }

        public int getTotalShots() {
            return totalShots;
        }
    }
}