package Shooter;

public class Schot
{
    public int schotnummer;
    public double schotresultaat;
    public Coordinate coordinaten;
    public String vergelijking_vorig_schot;
    public double huidige_seriegemiddelde;
    public double totale_score;

    public Schot(int schotnummer, double schotresultaat, Coordinate coordinaten, String vergelijking_vorig_schot, double huidige_seriegemiddelde, double totale_score)
    {
        this.schotnummer = schotnummer;
        this.schotresultaat = schotresultaat;
        this.coordinaten = coordinaten;
        this.vergelijking_vorig_schot = vergelijking_vorig_schot;
        this.huidige_seriegemiddelde = huidige_seriegemiddelde;
        this.totale_score = totale_score;
    }
}
