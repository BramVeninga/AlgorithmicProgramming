package Analyse;

import javax.swing.*;
import java.awt.*;

public class AnalyseScore extends JFrame
{
    private JButton analyseButton;

    public AnalyseScore()
    {
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Main panel with padding
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
//        mainPanel.setLayout(new GridLayout(0, 2, 10, 10)); // Grid layout with spacing
        add(mainPanel, BorderLayout.CENTER);

        // Analyze button
        analyseButton = new JButton("📈 Analyze");
//        analyzeButton.addActionListener(this::analyseScore);
        mainPanel.add(analyseButton);
    }
}
