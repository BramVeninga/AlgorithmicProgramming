import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class AnalysisScreen extends JFrame
{
    private JTable shooterTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;
    private JButton checkShooterButton;
    private JButton analyzeShooterButton;
    private boolean isShooterIdValid = false;

    private List<ShooterData> shooterDataList;

    public AnalysisScreen(File file)
    {
        setTitle("📊 Shooter.Shooter Analysis - " + file.getName());
        setSize(700, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Sample data - replace with actual parsed file data
        shooterDataList = new ArrayList<>(List.of(
                new ShooterData("A123", 25),
                new ShooterData("B456", 40),
                new ShooterData("C789", 15),
                new ShooterData("D111", 30)
        ));

        // Table Setup
        String[] columns = {"Shooter.Shooter ID", "Total Shots Fired"};
        tableModel = new DefaultTableModel(columns, 0);
        shooterTable = new JTable(tableModel);
        populateTable(shooterDataList);
        add(new JScrollPane(shooterTable), BorderLayout.CENTER);

        // Controls Panel
        JPanel controlsPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel topRow = new JPanel(new FlowLayout());
        JPanel bottomRow = new JPanel(new FlowLayout());

        JButton sortByShooterButton = new JButton("Sort by Shooter.Shooter ID");
        JButton sortByShotsButton = new JButton("Sort by Shots Fired");
        searchField = new JTextField(10);
        checkShooterButton = new JButton("✅ Check Shooter.Shooter ID");
        analyzeShooterButton = new JButton("📊 Analyze Shooter.Shooter");
        JButton backButton = new JButton("🔙 Back");

        sortByShooterButton.addActionListener(e ->
        {
            shooterDataList.sort(Comparator.comparing(ShooterData::getShooterId));
            populateTable(shooterDataList);
        });

        sortByShotsButton.addActionListener(e ->
        {
            shooterDataList.sort(Comparator.comparingInt(ShooterData::getTotalShots).reversed());
            populateTable(shooterDataList);
        });

        checkShooterButton.addActionListener(this::checkShooterId);
        analyzeShooterButton.addActionListener(this::analyzeShooter);
        backButton.addActionListener(e ->
        {
            new StartScherm().setVisible(true);
            dispose();
        });

        topRow.add(sortByShooterButton);
        topRow.add(sortByShotsButton);

        bottomRow.add(new JLabel("Shooter.Shooter ID:"));
        bottomRow.add(searchField);
        bottomRow.add(checkShooterButton);
        bottomRow.add(analyzeShooterButton);
        bottomRow.add(backButton);

        controlsPanel.add(topRow);
        controlsPanel.add(bottomRow);
        add(controlsPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void populateTable(List<ShooterData> dataList)
    {
        tableModel.setRowCount(0);
        for (ShooterData data : dataList)
        {
            tableModel.addRow(new Object[]{data.getShooterId(), data.getTotalShots()});
        }
    }

    private void checkShooterId(ActionEvent e)
    {
        String shooterId = searchField.getText().trim();
        isShooterIdValid = false;

        if (shooterId.isEmpty())
        {
            JOptionPane.showMessageDialog(this, "❌ Please enter a Shooter.Shooter ID.");
            return;
        }

        boolean found = shooterDataList.stream()
                .anyMatch(data -> data.getShooterId().equalsIgnoreCase(shooterId));

        if (found)
        {
            isShooterIdValid = true;
            JOptionPane.showMessageDialog(this, "✅ Shooter.Shooter ID is valid!");
        }
        else
        {
            JOptionPane.showMessageDialog(this, "❌ Shooter.Shooter ID not found.");
        }
    }

    private void analyzeShooter(ActionEvent e)
    {
        if (!isShooterIdValid)
        {
            JOptionPane.showMessageDialog(this, "⚠️ Please validate the Shooter.Shooter ID first.");
            return;
        }

        String shooterId = searchField.getText().trim();
        new ShooterDetailScreen(shooterId).setVisible(true);
        dispose();
    }

    // Sample data class
    static class ShooterData
    {
        private final String shooterId;
        private final int totalShots;

        public ShooterData(String shooterId, int totalShots)
        {
            this.shooterId = shooterId;
            this.totalShots = totalShots;
        }

        public String getShooterId()
        {
            return shooterId;
        }

        public int getTotalShots()
        {
            return totalShots;
        }
    }
}
