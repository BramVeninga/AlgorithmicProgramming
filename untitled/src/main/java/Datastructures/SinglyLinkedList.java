package Datastructures;

public class SinglyLinkedList<T>
{
    private Node<T> head;
    private int size = 0;

    // Interne klasse Node
    private static class Node<T>
    {
        T data;
        Node<T> next;

        Node(T data)
        {
            this.data = data;
            this.next = null;
        }
    }

    // Toevoegen aan het einde van de lijst
    public void add(T element)
    {
        Node<T> newNode = new Node<>(element);
        if (head == null)
        {
            head = newNode;
        }
        else
        {
            Node<T> current = head;
            while (current.next != null)
            {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Toevoegen op een specifieke index
    public void add(int index, T element)
    {
        if (index < 0 || index > size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        Node<T> newNode = new Node<>(element);
        if (index == 0)
        {
            newNode.next = head;
            head = newNode;
        } else
        {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            newNode.next = current.next;
            current.next = newNode;
        }
        size++;
    }

    // Verwijderen van een element op een bepaalde index
    public T remove(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        Node<T> removedNode;
        if (index == 0)
        {
            removedNode = head;
            head = head.next;
        } else
        {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++)
            {
                current = current.next;
            }
            removedNode = current.next;
            current.next = current.next.next;
        }
        size--;
        return removedNode.data;
    }

    // Verwijderen van een element (eerste voorkomen)
    public boolean remove(T element)
    {
        if (head == null) return false;

        if (head.data.equals(element))
        {
            head = head.next;
            size--;
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(element))
        {
            current = current.next;
        }

        if (current.next == null) return false;

        current.next = current.next.next;
        size--;
        return true;
    }

    // Ophalen van een element op een bepaalde index
    public T get(int index) {
        if (index < 0 || index >= size)
        {
            throw new IndexOutOfBoundsException("Index buiten bereik");
        }
        Node<T> current = head;
        for (int i = 0; i < index; i++)
        {
            current = current.next;
        }
        return current.data;
    }

    // Geeft de grootte van de lijst terug
    public int size()
    {
        return size;
    }

    // Controleert of de lijst leeg is
    public boolean isEmpty()
    {
        return size == 0;
    }

}
