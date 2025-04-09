package Shooter;

public class Schot
{
    private int schotnummer;
    private double schotresultaat;
    private Coordinate coordinaten;
    private String vergelijking_vorig_schot;
    private double huidige_seriegemiddelde;
    private double totale_score;

    public Schot(int schotnummer, double schotresultaat, Coordinate coordinaten, String vergelijking_vorig_schot, double huidige_seriegemiddelde, double totale_score)
    {
        this.schotnummer = schotnummer;
        this.schotresultaat = schotresultaat;
        this.coordinaten = coordinaten;
        this.vergelijking_vorig_schot = vergelijking_vorig_schot;
        this.huidige_seriegemiddelde = huidige_seriegemiddelde;
        this.totale_score = totale_score;
    }

    public int getSchotnummer()
    {
        return schotnummer;
    }

    public void setSchotnummer(int schotnummer)
    {
        this.schotnummer = schotnummer;
    }

    public double getSchotresultaat()
    {
        return schotresultaat;
    }

    public void setSchotresultaat(double schotresultaat)
    {
        this.schotresultaat = schotresultaat;
    }

    public Coordinate getCoordinaten()
    {
        return coordinaten;
    }

    public void setCoordinaten(Coordinate coordinaten)
    {
        this.coordinaten = coordinaten;
    }

    public String getVergelijking_vorig_schot()
    {
        return vergelijking_vorig_schot;
    }

    public void setVergelijking_vorig_schot(String vergelijking_vorig_schot)
    {
        this.vergelijking_vorig_schot = vergelijking_vorig_schot;
    }

    public double getHuidige_seriegemiddelde()
    {
        return huidige_seriegemiddelde;
    }

    public void setHuidige_seriegemiddelde(double huidige_seriegemiddelde)
    {
        this.huidige_seriegemiddelde = huidige_seriegemiddelde;
    }

    public double getTotale_score()
    {
        return totale_score;
    }

    public void setTotale_score(double totale_score)
    {
        this.totale_score = totale_score;
    }
}
