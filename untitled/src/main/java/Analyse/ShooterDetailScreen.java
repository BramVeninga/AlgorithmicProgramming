package Analyse;

import Datastructures.SinglyLinkedList;
import ShootingRange.StartScherm;
import Shooter.Shooter;
import Shooter.Schot;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ShooterDetailScreen extends JFrame
{
    private JTable detailTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortOptions;
    private JComboBox<String> algorithmOptions;
    private JButton backButton;

    private SinglyLinkedList<Schot> shooterShots;

    public ShooterDetailScreen(Shooter shooter)
    {
        setTitle(" Shooter.Shooter Details - " + shooter.getSchutter_ID());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Dummy data for one shooter – replace with actual parsed data
        shooterShots = shooter.getSchoten();

        // Table
        String[] columns = {"Schotresultaat", "Vergelijking vorige schot", "Huidige seriegemiddelde", "totale score"};
        tableModel = new DefaultTableModel(columns, 0);
        detailTable = new JTable(tableModel);
        populateTable(shooterShots);

        add(new JScrollPane(detailTable), BorderLayout.CENTER);

        // Control Panel
        JPanel controlPanel = new JPanel(new FlowLayout());

        sortOptions = new JComboBox<>(new String[]{
                "Sorteer op schotresultaat",
                "Sorteer op huidige seriegemiddelde",
                "Sorteer op totale score"
        });

        algorithmOptions = new JComboBox<>(new String[]{
                "SortingAlgorithms.BubbleSort",
                "SortingAlgorithms.QuickSort"
        });

        sortOptions.addActionListener(e -> sortTable());
        algorithmOptions.addActionListener(e -> sortTable());

        backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Go back to previous screen or main menu
            new StartScherm().setVisible(true);
            dispose();
        });

        controlPanel.add(new JLabel("Sort:"));
        controlPanel.add(sortOptions);
        controlPanel.add(new JLabel("Algorithm:"));
        controlPanel.add(algorithmOptions);
        controlPanel.add(backButton);

        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void populateTable(SinglyLinkedList<Schot> shots)
    {
        tableModel.setRowCount(0);
        for (int i = 0; i < shots.size(); i++)
        {
            Schot shot = shots.get(i);
            tableModel.addRow(new Object[]{shot.getSchotresultaat(), shot.getVergelijking_vorig_schot(), shot.getHuidige_seriegemiddelde(), shot.getTotale_score()});
        }
    }

    private void sortTable()
    {
//        String selectedSort = (String) sortOptions.getSelectedItem();
//        String selectedAlgorithm = (String) algorithmOptions.getSelectedItem();
//
//        if (selectedSort != null && selectedAlgorithm != null) {
//            // Define a comparator based on selected sort option
//            Comparator<ShooterShot> comparator = switch (selectedSort) {
//                case "Sorteer op schotresultaat" -> Comparator.comparingDouble(ShooterShot::getShootingResult).reversed();
//                case "Sorteer op huidige seriegemiddelde" -> Comparator.comparingDouble(ShooterShot::getAverageShot).reversed();
//                case "Sorteer op totale score" -> Comparator.comparingDouble(ShooterShot::getTotalScore).reversed();
//                default -> null;
//            };
//
//            if (comparator != null) {
//                // Convert List to array
//                ShooterShot[] shotArray = shooterShots.toArray(new ShooterShot[0]);
//
//                // Sort with selected algorithm
//                if ("SortingAlgorithms.BubbleSort".equals(selectedAlgorithm)) {
//                    shotArray = SortingAlgorithms.BubbleSort.sort(shotArray);
//                } else if ("SortingAlgorithms.QuickSort".equals(selectedAlgorithm)) {
////                    shotArray = SortingAlgorithms.MergeSort.sort(shotArray);
//                }
//
//                // Sort with comparator
//                Arrays.sort(shotArray, comparator);
//
//                // Convert back to List
//                shooterShots = new ArrayList<>(Arrays.asList(shotArray));
//                populateTable(shooterShots);
//            }
//        }
    }
}
