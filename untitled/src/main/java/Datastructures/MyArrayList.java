package Datastructures;

public class MyArrayList<T>
{
    private T[] data;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList()
    {
        // Java laat geen directe creatie toe van generieke arrays, dus via Object[] en cast
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public void add(T element)
    {
        ensureCapacity();
        data[size++] = element;
    }

    public T get(int index)
    {
        checkIndex(index);
        return data[index];
    }

    public void set(int index, T element)
    {
        checkIndex(index);
        data[index] = element;
    }

    public T remove(int index)
    {
        checkIndex(index);
        T removedElement = data[index];
        for (int i = index; i < size - 1; i++)
        {
            data[i] = data[i + 1];
        }
        data[--size] = null;
        return removedElement;
    }

    public int size()
    {
        return size;
    }

    private void ensureCapacity()
    {
        if (size == data.length)
        {
            int newCapacity = data.length * 2;
            @SuppressWarnings("unchecked")
            T[] newData = (T[]) new Object[newCapacity];
            System.arraycopy(data, 0, newData, 0, data.length);
            data = newData;
        }
    }

    private void checkIndex(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            data[i] = null;
        }
        size = 0;
    }

    public boolean contains(T element)
    {
        for (int i = 0; i < size; i++)
        {
            if ((data[i] == null && element == null) || (data[i] != null && data[i].equals(element)))
            {
                return true;
            }
        }
        return false;
    }
}

