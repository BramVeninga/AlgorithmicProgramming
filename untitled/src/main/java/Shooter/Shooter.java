package Shooter;

import java.util.ArrayList;

public class Shooter
{
    public int schutter_ID;
    public ArrayList<Schot> schoten;

    public Shooter(int schutter_ID, ArrayList<Schot> schoten)
    {
        this.schutter_ID = schutter_ID;
        this.schoten = schoten;
    }
}
