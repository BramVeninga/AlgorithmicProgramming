package Analyse;

import Datastructures.MyArray;
import Datastructures.SinglyLinkedList;
import ShootingRange.Shot;
import ShootingRange.StartScherm;
import Shooter.Shooter;
import Shooter.Schot;
import SortingAlgorithms.BubbleSort;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Comparator;

public class ShooterDetailScreen extends JFrame
{
    private JTable detailTable;
    private DefaultTableModel tableModel;
    private JComboBox<String> sortOptions;
    private JButton backButton;

    private SinglyLinkedList<Schot> shooterShots;

    public ShooterDetailScreen(Shooter shooter)
    {
        setTitle("Shooter Details - " + shooter.getSchutter_ID());
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        shooterShots = shooter.getSchoten();

        // Tabel
        String[] columns = {"Schotnummer", "Schotresultaat", "Vergelijking vorige schot", "Huidige seriegemiddelde", "Totale score"};
        tableModel = new DefaultTableModel(columns, 0);
        detailTable = new JTable(tableModel);
        populateTable(shooterShots);

        add(new JScrollPane(detailTable), BorderLayout.CENTER);

        // Bediening
        JPanel controlPanel = new JPanel(new FlowLayout());

        sortOptions = new JComboBox<>(new String[]{
                "Sorteer op schotnummer",
                "Sorteer op schotresultaat",
                "Sorteer op huidige seriegemiddelde",
                "Sorteer op totale score"
        });

        sortOptions.addActionListener(e -> sortTable());

        backButton = new JButton("Terug");
        backButton.addActionListener(e -> {
            new StartScherm().setVisible(true);
            dispose();
        });

        controlPanel.add(new JLabel("Sorteer op:"));
        controlPanel.add(sortOptions);
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
            tableModel.addRow(new Object[]{
                    shot.getSchotnummer(),
                    shot.getSchotresultaat(),
                    shot.getVergelijking_vorig_schot(),
                    shot.getHuidige_seriegemiddelde(),
                    shot.getTotale_score()
            });
        }
    }

    private void sortTable()
    {
        String selectedSort = (String) sortOptions.getSelectedItem();

        Comparator<Schot> comparator = switch (selectedSort)
        {
            case "Sorteer op schotresultaat" ->
                    Comparator.comparingDouble(Schot::getSchotresultaat).reversed();
            case "Sorteer op huidige seriegemiddelde" ->
                    Comparator.comparingDouble(Schot::getHuidige_seriegemiddelde).reversed();
            case "Sorteer op totale score" ->
                    Comparator.comparingDouble(Schot::getTotale_score).reversed();
            default ->
                    Comparator.comparingInt(Schot::getSchotnummer); // standaard schotnummer
        };

        // Zet linked list om naar array
        MyArray<Schot> myArray = shooterShots.toArray();
        Schot[] array = myArray.toJavaArray();

        // Sorteer met BubbleSort
        BubbleSort.sort(array, comparator);

        // Zet terug naar SinglyLinkedList
        SinglyLinkedList<Schot> sortedList = new SinglyLinkedList<>();
        for (Schot s : array)
        {
            sortedList.add(s);
        }

        shooterShots = sortedList;
        populateTable(shooterShots);
    }
}
