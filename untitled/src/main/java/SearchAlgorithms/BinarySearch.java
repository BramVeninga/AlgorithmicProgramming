package SearchAlgorithms;

import java.util.List;

public class BinarySearch {
    // Generieke methode voor binaire zoekopdracht
    public static <T extends Comparable<T>> int binarySearch(List<T> list, T target) {
        int left = 0, right = list.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            T midValue = list.get(mid);

            int comparison = midValue.compareTo(target);

            if (comparison == 0) {
                return mid; // Element gevonden, index retourneren
            } else if (comparison < 0) {
                left = mid + 1; // Zoek in de rechterhelft
            } else {
                right = mid - 1; // Zoek in de linkerhelft
            }
        }
        return -1; // Element niet gevonden
    }

    public static void main(String[] args) {
        List<Integer> intList = List.of(1, 3, 5, 7, 9, 11, 13);
        int intTarget = 9;
        System.out.println("Integer gezocht: " + intTarget + ", gevonden op index: " + binarySearch(intList, intTarget));

        List<String> stringList = List.of("apple", "banana", "cherry", "date", "fig", "grape");
        String stringTarget = "cherry";
        System.out.println("String gezocht: " + stringTarget + ", gevonden op index: " + binarySearch(stringList, stringTarget));
    }
}