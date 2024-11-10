import java.util.*;
public class ArrayGList<T> implements GList<T>
{
    private T[] array;
    int length; 
    public ArrayGList(int capacity)
    {
        length = -1;
        array = (T[]) new Object[capacity];
        for(int i=0;i<capacity;i++)
        {
            array[i] = (T)(Object)0;
        }
    }

    public int getLength()
    {
        return array.length;
    }

    public int insertLast(T value) // inserted einen Wert an der Stelle wo die erste 0 ist in der Liste
    {
        int platzierung = length + 1;        
        if(platzierung <= array.length)
        {
            if(array[platzierung] != (T)(Object)0)
            {
                return -1;
            } else {
                array[platzierung] = value;
                length++;
                return 0;
            }
        } else {
            return -1;
        }
    }

    public T getFirst() // fuer eine leere Liste kommt eine -9999, für eine leere erste stelle eine -1 
    {
        int rueckgabe;
        if(array[0] == (T)(Object)0)
        {
            return (T)"-9999";
        } else {
            return array[0];
        }
    }

    public int deleteFirst() //erklärt sich selbst
    {
        if(array[0] == (T)(Object)0)
        {
            return -1;
        } else {
            for(int i=0;i<length;i++)
            {
                if(!(i == array.length))
                {
                    array[i] = array[i+1];
                }
            }
            array[length] = (T)(Object)0;
            length--;
            return 0;
        }
    }

    public boolean search(T value) //Such-funktion
    {
        boolean istDa = false;
        for(int i=0;i<array.length;i++)
        {
            if(array[i].equals(value))
            {
                istDa = true;
            }
        }
        return istDa;
    }

    public void print() // spuckt den wert in der konsole aus
    {
        for(int i=0;i<array.length;i++)
        {
            System.out.println(array[i]);
        }
    }
    
    public T getLast()
    {
        if(array[length] == (T)(Object)0)
        {
            return (T)"-9999";
        }  else {
            return array[length];
        }        
    }
}
