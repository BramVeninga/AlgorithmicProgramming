package Datastructures;

public class MyArray<T>
{
    private T[] array;
    private int size = 0;
    private static final int INITIAL_CAPACITY = 10;

    // Constructor
    public MyArray()
    {
        array = (T[]) new Object[INITIAL_CAPACITY]; // Generics workaround
    }

    // Element toevoegen aan het einde
    public void add(T element)
    {
        ensureCapacity();
        array[size++] = element;
    }

    // Element toevoegen op een specifieke index
    public void add(int index, T element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        ensureCapacity();
        for (int i = size; i > index; i--)
        {
            array[i] = array[i - 1];
        }
        array[index] = element;
        size++;
    }

    // Element verwijderen op een specifieke index
    public T remove(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        T removedElement = array[index];
        for (int i = index; i < size - 1; i++)
        {
            array[i] = array[i + 1];
        }
        array[--size] = null; // Vermijd geheugenlekken
        return removedElement;
    }

    // Element ophalen
    public T get(int index)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        return array[index];
    }

    // Element instellen op een specifieke index
    public void set(int index, T element)
    {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        array[index] = element;
    }

    // Controleert of de lijst leeg is
    public boolean isEmpty()
    {
        return size == 0;
    }

    // Geeft de huidige grootte van de lijst terug
    public int size()
    {
        return size;
    }

    // Alle elementen verwijderen
    public void clear()
    {
        for (int i = 0; i < size; i++)
        {
            array[i] = null;
        }
        size = 0;
    }

    // Vergroot de capaciteit als de array vol is
    private void ensureCapacity()
    {
        if (size == array.length)
        {
            int newCapacity = array.length * 2;
            T[] newArray = (T[]) new Object[newCapacity];
            for (int i = 0; i < size; i++)
            {
                newArray[i] = array[i];
            }
            array = newArray;
        }
    }

    public T[] toJavaArray() {
        T[] array = (T[]) new Object[this.size()];
        for (int i = 0; i < this.size(); i++) {
            array[i] = this.get(i);
        }
        return array;
    }
}

