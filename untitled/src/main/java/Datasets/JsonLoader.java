package Datasets;

import Datastructures.MyArrayList;
import Datastructures.SinglyLinkedList;
import Shooter.Coordinate;
import Shooter.Schot;
import Shooter.Shooter;
import SortingAlgorithms.MergeSort;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;

public class JsonLoader implements DataLoader<MyArrayList<Shooter>>
{
    public JsonLoader()
    {
    }

    public MyArrayList<Shooter> loadData(String path)
    {
        MyArrayList<Shooter> array = new MyArrayList<>();

        try
        {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);

                int shooterId = object.getInt("schutter_ID");
                SinglyLinkedList<Schot> shots = new SinglyLinkedList<>();

                for (int j = 0; j < object.getJSONArray("schoten").length(); j++)
                {
                    JSONObject shotObject = object.getJSONArray("schoten").getJSONObject(j);

                    int shotNumber = shotObject.getInt("schotnummer");
                    double shotResult = shotObject.getDouble("schotresultaat");

                    JSONObject coordinateObject = shotObject.getJSONObject("coordinaten");
                    Coordinate coordinates = new Coordinate(coordinateObject.getDouble("X"), coordinateObject.getDouble("Y"));

                    String comparisonPreviousShot = shotObject.getString("vergelijking_vorig_schot");
                    double currentSerieAvarage = shotObject.getDouble("huidige_seriegemiddelde");
                    double totalScore = shotObject.getDouble("totale_score");

                    Schot schot = new Schot(shotNumber, shotResult, coordinates, comparisonPreviousShot, currentSerieAvarage, totalScore);
                    shots.add(schot);
                }

                Shooter shooter = new Shooter(shooterId, shots);
                array.add(shooter);
            }

        } catch (Exception e)
        {
            e.printStackTrace();
        }

        // Sorteer de array voor je hem teruggeeft
        return array;
    }
}
