package Datasets;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ImportDataset
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Kies het bestandsformaat (json/csv/txt):");
        String format = scanner.nextLine().trim().toLowerCase();
        System.out.println("Voer filepath in:");
        String filePath = scanner.nextLine().trim();

        List<JSONObject> dataset = loadData(filePath, format);

        if (dataset == null)
        {
            System.out.println("Kon dataset niet laden.");
            return;
        }

        // Zoek een object op basis van een sleutel-waarde paar
        System.out.println("Voer de zoek sleutel in:");
        String zoekKey = scanner.nextLine().trim();
        System.out.println("Voer de zoek waarde in:");
        String zoekWaarde = scanner.nextLine().trim();

        JSONObject resultaat = sequentialSearch(dataset, zoekKey, zoekWaarde);
        if (resultaat != null)
        {
            System.out.println("Gevonden: " + resultaat.toString(2));
        }
        else
        {
            System.out.println("Niet gevonden.");
        }
    }

    public static List<JSONObject> loadData(String filePath, String format)
    {
        switch (format)
        {
            case "json":
                return loadJSONData(filePath);
            case "csv":
                return loadCSVData(filePath);
            case "txt":
                return loadTXTData(filePath);
            default:
                System.out.println("Ongeldig formaat.");
                return null;
        }
    }

    public static List<JSONObject> loadJSONData(String filePath)
    {
        try
        {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONArray jsonArray = new JSONArray(content);
            List<JSONObject> list = new ArrayList<>();

            for (int i = 0; i < jsonArray.length(); i++)
            {
                list.add(jsonArray.getJSONObject(i));
            }
            System.out.println();
            return list;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static List<JSONObject> loadCSVData(String filePath)
    {
        List<JSONObject> list = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            if (lines.isEmpty()) return list;

            String[] headers = lines.get(0).split(",");

            for (int i = 1; i < lines.size(); i++)
            {
                String[] values = lines.get(i).split(",");

                // Controleer of de rij evenveel kolommen heeft als de header
                if (values.length != headers.length)
                {
                    System.out.println("Ongeldige CSV-regel op regel " + (i + 1) + ": " + lines.get(i));
                    continue; // Sla de rij over en ga naar de volgende
                }

                JSONObject obj = new JSONObject();
                for (int j = 0; j < headers.length; j++)
                {
                    obj.put(headers[j], values[j].trim()); // Verwijder onnodige spaties
                }
                list.add(obj);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return list;
    }

    public static List<JSONObject> loadTXTData(String filePath)
    {
        List<JSONObject> list = new ArrayList<>();
        try
        {
            List<String> lines = Files.readAllLines(Paths.get(filePath));
            for (String line : lines)
            {
                JSONObject obj = new JSONObject();
                obj.put("text", line);
                list.add(obj);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return list;
    }

    public static JSONObject sequentialSearch(List<JSONObject> dataset, String key, String value)
    {
        for (JSONObject obj : dataset)
        {
            if (obj.has(key) && obj.get(key).toString().equals(value))
            {
                return obj;
            }
        }
        return null;
    }
}

