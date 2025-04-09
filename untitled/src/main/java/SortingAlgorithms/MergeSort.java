package SortingAlgorithms;

import Datastructures.MyArrayList;
import java.util.Comparator;

public class MergeSort
{
    public static <T> MyArrayList<T> mergeSort(MyArrayList<T> list, Comparator<? super T> comparator)
    {
        if (list.size() <= 1)
        {
            return new MyArrayList<>(list);
        }

        int mid = list.size() / 2;
        MyArrayList<T> left = list.subList(0, mid);
        MyArrayList<T> right = list.subList(mid, list.size());

        MyArrayList<T> sortedLeft = mergeSort(left, comparator);
        MyArrayList<T> sortedRight = mergeSort(right, comparator);

        return merge(sortedLeft, sortedRight, comparator);
    }

    private static <T> MyArrayList<T> merge(MyArrayList<T> left, MyArrayList<T> right, Comparator<? super T> comparator)
    {
        MyArrayList<T> result = new MyArrayList<>();
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
