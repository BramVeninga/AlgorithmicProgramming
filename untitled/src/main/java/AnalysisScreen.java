import Datasets.JsonLoader;
import Shooter.Shooter;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;

import Datastructures.MyArrayList;

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
        setTitle("📊 Shooter Analysis - " + file.getName());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        jsonLoader = new JsonLoader();

        shooterDataList = jsonLoader.loadData(file.getPath());

        // Sample data - replace with actual parsed file data
        //        shooterDataList = new ArrayList<>(List.of(
        //                new ShooterData("A123", 25),
        //                new ShooterData("B456", 40),
        //                new ShooterData("C789", 15),
        //                new ShooterData("D111", 30)
        //        ));

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
        checkShooterButton = new JButton("✅ Check Shooter ID");
        analyzeShooterButton = new JButton("📊 Analyze Shooter");
        JButton backButton = new JButton("🔙 Back");

        sortByShooterButton.addActionListener(e -> {
            //            shooterDataList.sort(Comparator.comparing(ShooterData::getShooterId));
            //            populateTable(shooterDataList);
        });

        sortByShotsButton.addActionListener(e -> {
            //            shooterDataList.sort(Comparator.comparingInt(ShooterData::getTotalShots).reversed());
            //            populateTable(shooterDataList);
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
        //        String shooterId = searchField.getText().trim();
        //        isShooterIdValid = false;
        //
        //        if (shooterId.isEmpty()) {
        //            JOptionPane.showMessageDialog(this, "❌ Please enter a Shooter ID.");
        //            return;
        //        }
        //
        //        boolean found = shooterDataList.stream()
        //                .anyMatch(data -> data.getShooterId().equalsIgnoreCase(shooterId));
        //
        //        if (found) {
        //            isShooterIdValid = true;
        //            JOptionPane.showMessageDialog(this, "✅ Shooter ID is valid!");
        //        } else {
        //            JOptionPane.showMessageDialog(this, "❌ Shooter ID not found.");
        //        }
    }

    private void analyzeShooter(ActionEvent e) {
        if (!isShooterIdValid) {
            JOptionPane.showMessageDialog(this, "⚠️ Please validate the Shooter ID first.");
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