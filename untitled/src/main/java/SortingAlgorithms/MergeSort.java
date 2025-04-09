package SortingAlgorithms;

import java.util.ArrayList;
import java.util.Comparator;

public class MergeSort
{
    public static <T> ArrayList<T> mergeSort(ArrayList<T> list, Comparator<? super T> comparator)
    {
        if (list.size() <= 1)
        {
            return new ArrayList<>(list);
        }

        int mid = list.size() / 2;
        ArrayList<T> left = new ArrayList<>(list.subList(0, mid));
        ArrayList<T> right = new ArrayList<>(list.subList(mid, list.size()));

        ArrayList<T> sortedLeft = mergeSort(left, comparator);
        ArrayList<T> sortedRight = mergeSort(right, comparator);

        return merge(sortedLeft, sortedRight, comparator);
    }

    private static <T> ArrayList<T> merge(ArrayList<T> left, ArrayList<T> right, Comparator<? super T> comparator)
    {
        ArrayList<T> result = new ArrayList<>();
        int i = 0, j = 0;

        while (i < left.size() && j < right.size())
        {
            if (comparator.compare(left.get(i), right.get(j)) <= 0)
            {
                result.add(left.get(i++));
            }
            else
            {
                result.add(right.get(j++));
            }
        }

        while (i < left.size()) result.add(left.get(i++));
        while (j < right.size()) result.add(right.get(j++));

        return result;
    }
}
