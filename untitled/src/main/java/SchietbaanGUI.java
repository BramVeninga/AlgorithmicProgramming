import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SchietbaanGUI extends JFrame
{
    private Schietbaan schietbaan = new Schietbaan();
    private JLabel schotNummerLabel, scoreLabel, totaalLabel, gemiddeldLabel, resterendLabel, schutterLabel, positieLabel;
    private DefaultTableModel tableModel;
    private TargetPanel targetPanel;
    private int maxSchoten;

    public SchietbaanGUI(String schutter, int aantalSchoten, String positie)
    {
        this.maxSchoten = aantalSchoten;

        setTitle("10m Schietsport - " + schutter);
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // ** Linker Paneel **
        JPanel leftPanel = new JPanel(new GridLayout(4, 1));

        // ** Schutter Info **
        JPanel schutterInfoPanel = new JPanel(new GridLayout(3, 2));
        schutterInfoPanel.add(new JLabel("Schutter:"));
        schutterLabel = new JLabel(schutter);
        schutterInfoPanel.add(schutterLabel);
        schutterInfoPanel.add(new JLabel("Positie:"));
        positieLabel = new JLabel(positie);
        schutterInfoPanel.add(positieLabel);
        schutterInfoPanel.add(new JLabel("Resterend:"));
        resterendLabel = new JLabel(String.valueOf(maxSchoten));
        schutterInfoPanel.add(resterendLabel);
        leftPanel.add(schutterInfoPanel);

        // ** Shooter.Schot Info **
        JPanel schotInfoPanel = new JPanel(new GridLayout(2, 2));
        schotInfoPanel.add(new JLabel("Actueel Shooter.Schot:"));
        schotNummerLabel = new JLabel("1");
        schotInfoPanel.add(schotNummerLabel);
        schotInfoPanel.add(new JLabel("Punten:"));
        scoreLabel = new JLabel("0.0");
        schotInfoPanel.add(scoreLabel);
        leftPanel.add(schotInfoPanel);

        // ** Schoten Tabel **
        String[] columnNames = {"Nr", "Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        JTable schotTable = new JTable(tableModel);
        leftPanel.add(new JScrollPane(schotTable));

        // ** Totaal Info **
        JPanel totaalPanel = new JPanel(new GridLayout(2, 2));
        totaalPanel.add(new JLabel("Totaal:"));
        totaalLabel = new JLabel("0.0");
        totaalPanel.add(totaalLabel);
        totaalPanel.add(new JLabel("Gemiddelde:"));
        gemiddeldLabel = new JLabel("0.0");
        totaalPanel.add(gemiddeldLabel);
        leftPanel.add(totaalPanel);

        add(leftPanel, BorderLayout.WEST);

        // ** Targetpaneel **
        targetPanel = new TargetPanel();
        add(targetPanel, BorderLayout.CENTER);

        // ** Knoppen **
        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Shooter.Schot lossen");
        JButton analyzeButton = new JButton("Score Analyseren");
        startButton.addActionListener(this::handleNewShot);
        buttonPanel.add(startButton);
        buttonPanel.add(analyzeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleNewShot(ActionEvent e)
    {
        if (schietbaan.getAantalSchoten() >= maxSchoten)
        {
            JOptionPane.showMessageDialog(this, "Je hebt het maximum aantal schoten bereikt!");
            return;
        }

        double score = Math.round((Math.random() * 10.9) * 10) / 10.0;

        // Coördinaten omrekenen binnen het doelwit
        int maxOffset = 180; // Afhankelijk van de doelwitgrootte
        int x = (int) (Math.random() * maxOffset * 2) - maxOffset;
        int y = (int) (Math.random() * maxOffset * 2) - maxOffset;

        schietbaan.voegSchotToe(score, x, y);
        System.out.println(score + " " + x + " " + y);
        schotNummerLabel.setText(String.valueOf(schietbaan.getAantalSchoten() + 1));
        scoreLabel.setText(String.valueOf(score));
        totaalLabel.setText(String.valueOf(schietbaan.getTotaleScore()));
        gemiddeldLabel.setText(String.format("%.2f", schietbaan.getGemiddelde()));
        resterendLabel.setText(String.valueOf(maxSchoten - schietbaan.getAantalSchoten()));

        tableModel.addRow(new Object[]{schietbaan.getAantalSchoten(), score});
        targetPanel.addShot(x, y);
    }
}
