import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class StartScherm extends JFrame {
    private JComboBox<String> schutterSelectie;
    private JComboBox<Integer> aantalSchotenSelectie;
    private JComboBox<String> positieSelectie;
    private List<String> schuttersLijst = new ArrayList<>();
    private JButton bestandKiezenButton;
    private JButton schietenButton;
    private File selectedFile;

    public StartScherm() {
        setTitle("Start Schietsessie");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(0, 2)); // Fix voor dynamische groei

        // **Schutter-selectie**
        add(new JLabel("Schutter:"));
        schutterSelectie = new JComboBox<>();
        schutterSelectie.addItem("Nieuwe schutter toevoegen...");
        schutterSelectie.addActionListener(e -> checkNieuweSchutter());
        add(schutterSelectie);

        // **Aantal schoten-selectie**
        add(new JLabel("Aantal schoten:"));
        aantalSchotenSelectie = new JComboBox<>(new Integer[]{10, 20, 30, 40, 50, 60});
        add(aantalSchotenSelectie);

        // **Positie-selectie**
        add(new JLabel("Positie:"));
        positieSelectie = new JComboBox<>(new String[]{"Staand", "Opgelegd"});
        add(positieSelectie);

        // **Bestand kiezen knop**
        bestandKiezenButton = new JButton("Bestand kiezen");
        bestandKiezenButton.addActionListener(this::bestandKiezen);
        add(bestandKiezenButton);

        // **Schieten-knop**
        schietenButton = new JButton("Schieten");
        schietenButton.addActionListener(this::openSchietbaanGUI);
        add(schietenButton);
    }

    private void checkNieuweSchutter() {
        if ("Nieuwe schutter toevoegen...".equals(schutterSelectie.getSelectedItem())) {
            String nieuweNaam = JOptionPane.showInputDialog(this, "Voer naam van nieuwe schutter in:");
            if (nieuweNaam != null && !nieuweNaam.trim().isEmpty()) {
                schuttersLijst.add(nieuweNaam);
                schutterSelectie.insertItemAt(nieuweNaam, 0);
                schutterSelectie.setSelectedIndex(0);
            }
        }
    }

    private void bestandKiezen(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();
        int choice = fileChooser.showOpenDialog(this);
        if (choice == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile();
            if (selectedFile != null) {
                JOptionPane.showMessageDialog(this, "Geselecteerd bestand: " + selectedFile.getName());

                // Open the analysisScreen
                AnalysisScreen analysisScreen = new AnalysisScreen(selectedFile);
                analysisScreen.setVisible(true);

                this.dispose();
            }
            else
            {
                JOptionPane.showMessageDialog(this, "Error: no file selected", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else
        {
            JOptionPane.showMessageDialog(this, "No file was chosen.");
        }
    }

    private void openSchietbaanGUI(ActionEvent e) {
        String schutter = (String) schutterSelectie.getSelectedItem();
        int aantalSchoten = (Integer) aantalSchotenSelectie.getSelectedItem();
        String positie = (String) positieSelectie.getSelectedItem();
        new SchietbaanGUI(schutter, aantalSchoten, positie).setVisible(true);
        dispose();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StartScherm().setVisible(true));
    }
}