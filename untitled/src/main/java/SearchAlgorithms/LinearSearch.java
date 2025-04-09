package SearchAlgorithms;

public class LinearSearch
{
    // Generieke methode voor Linear Search
    public static <T> int linearSearch(T[] array, T target)
    {
        for (int i = 0; i < array.length; i++)
        {
            if (array[i].equals(target))
            {
                return i; // Index van het gevonden element retourneren
            }
        }
        return -1; // Element niet gevonden
    }
}
