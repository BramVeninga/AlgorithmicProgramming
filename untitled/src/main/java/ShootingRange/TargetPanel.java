package ShootingRange;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TargetPanel extends JPanel
{
    private Image targetImage;
    private final List<Point> shots = new ArrayList<>();
    private int imgX, imgY, imgWidth, imgHeight; // Afbeelding positie en grootte

    public TargetPanel()
    {
        try
        {
            targetImage = ImageIO.read(new File("src/main/java/rescources/10_m_Air_Rifle_target.svg.png")); // Zorg dat de afbeelding hier staat!
        }
        catch (IOException e)
        {
            System.err.println("⚠ Kan afbeelding niet laden! Controleer bestandslocatie.");
            e.printStackTrace();
        }


    }

    // **Voeg een schot toe en converteer naar correcte schaal**
    public void addShot(double relativeX, double relativeY)
    {
        if (targetImage == null) return;

        // **Zet relatieve coördinaten om naar echte paneel-coördinaten**
        int absoluteX = imgX + (int) (relativeX * imgWidth);
        int absoluteY = imgY + (int) (relativeY * imgHeight);

        shots.add(new Point(absoluteX, absoluteY));
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        if (targetImage != null)
        {
            // **Bereken schaal en positie van de afbeelding binnen het paneel**
            double panelRatio = (double) getWidth() / getHeight();
            double imageRatio = (double) targetImage.getWidth(null) / targetImage.getHeight(null);

            if (panelRatio > imageRatio)
            {
                imgHeight = getHeight();
                imgWidth = (int) (imgHeight * imageRatio);
            }
            else
            {
                imgWidth = getWidth();
                imgHeight = (int) (imgWidth / imageRatio);
            }

            imgX = (getWidth() - imgWidth) / 2;
            imgY = (getHeight() - imgHeight) / 2;

            // **Teken de afbeelding gecentreerd**
            g.drawImage(targetImage, imgX, imgY, imgWidth, imgHeight, this);
        }

        // **Schoten correct tekenen**
        g.setColor(Color.RED);
        for (Point shot : shots)
        {
            g.fillOval(shot.x - 3, shot.y - 3, 6, 6); // Klein rood stipje
        }
    }
}
