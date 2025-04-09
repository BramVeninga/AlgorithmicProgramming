package SearchAlgorithms;

import java.util.List;

public class BinarySearch
{
    // Generieke methode voor binaire zoekopdracht
    public static <T extends Comparable<T>> int binarySearch(List<T> list, T target)
    {
        int left = 0, right = list.size() - 1;

        while (left <= right)
        {
            int mid = left + (right - left) / 2;
            T midValue = list.get(mid);

            int comparison = midValue.compareTo(target);

            if (comparison == 0)
            {
                return mid; // Element gevonden, index retourneren
            }
            else if (comparison < 0)
            {
                left = mid + 1; // Zoek in de rechterhelft
            }
            else
            {
                right = mid - 1; // Zoek in de linkerhelft
            }
        }
        return -1; // Element niet gevonden
    }
}