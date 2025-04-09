import java.util.ArrayList;
import java.util.List;

public class Schietbaan {
    private List<Shot> schoten = new ArrayList<>();
    private int schotNummer = 1;

    public void voegSchotToe(double score, int x, int y) {
        schoten.add(new Shot(schotNummer, score, x, y));
        schotNummer++;
    }

    public List<Shot> getSchoten()
    {
        return schoten;
    }

    public double getGemiddelde()
    {
        if (schoten.isEmpty()) return 0;
        return schoten.stream().mapToDouble(Shot::getScore).average().orElse(0);
    }

    public double getTotaleScore()
    {
        return schoten.stream().mapToDouble(Shot::getScore).sum();
    }

    public int getAantalSchoten()
    {
        return schoten.size();
    }
}
