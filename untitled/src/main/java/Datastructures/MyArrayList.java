package Datastructures;

public class MyArrayList<T>
{
    private T[] data;
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public MyArrayList()
    {
        data = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(MyArrayList<T> other)
    {
        this();
        for (int i = 0; i < other.size(); i++)
        {
            this.add(other.get(i));
        }
    }

    public MyArrayList<T> subList(int fromIndex, int toIndex)
    {
        if (fromIndex < 0 || toIndex > size || fromIndex > toIndex)
        {
            throw new IndexOutOfBoundsException("fromIndex: " + fromIndex + ", toIndex: " + toIndex + ", size: " + size);
        }

        MyArrayList<T> subList = new MyArrayList<>();
        for (int i = fromIndex; i < toIndex; i++)
        {
            subList.add(get(i));
        }
        return subList;
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

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MyArrayList<?> other = (MyArrayList<?>) obj;

        if (this.size != other.size) return false;

        for (int i = 0; i < size; i++)
        {
            T thisElement = this.data[i];
            Object otherElement = other.data[i];

            if (thisElement == null ? otherElement != null : !thisElement.equals(otherElement))
            {
                return false;
            }
        }

        return true;
    }

}
