package Datasets;

import Datastructures.MyArray;
import Shooter.Coordinate;
import Shooter.Schot;
import Shooter.Shooter;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class JsonLoader implements DataLoader<MyArray<Shooter>>
{
    public JsonLoader()
    {
    }

    public MyArray<Shooter> loadData(String path)
    {
        MyArray<Shooter> array = new MyArray<>();

        try
        {
            String content = new String(Files.readAllBytes(Paths.get(path)));
            JSONArray jsonArray = new JSONArray(content);

            for (int i = 0; i < jsonArray.length(); i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);

                int shooterId = object.getInt("schutter_ID");
                ArrayList<Schot> shots = new ArrayList<>();

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

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return array;
    }

    public static void main(String[] args) {
        JsonLoader jsonLoader = new JsonLoader();

        System.out.println(jsonLoader.loadData("C:/Users/timde/OneDrive/Documenten/GitHub/Algorithm-Programming/exampledataset/schutters.json").get(3).schoten.get(2).schotresultaat);
    }
}
