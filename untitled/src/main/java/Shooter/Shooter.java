package Shooter;

import Datastructures.SinglyLinkedList;

public class Shooter
{
    private int schutter_ID;
    private SinglyLinkedList<Schot> schoten;

    public Shooter(int schutter_ID, SinglyLinkedList<Schot> schoten)
    {
        this.schutter_ID = schutter_ID;
        this.schoten = schoten;
    }

    public int getSchutter_ID()
    {
        return schutter_ID;
    }

    public void setSchutter_ID(int schutter_ID)
    {
        this.schutter_ID = schutter_ID;
    }

    public SinglyLinkedList<Schot> getSchoten()
    {
        return schoten;
    }

    public void setSchoten(SinglyLinkedList<Schot> schoten)
    {
        this.schoten = schoten;
    }

    public int getTotalShots()
    {
        return schoten.size();
    }
}
