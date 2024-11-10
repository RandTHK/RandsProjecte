import java.util.*;
public class Mensch
{
    String name; double groesse; int alter;
    public Mensch(String pName, double pGroesse, int pAlter)
    {
        name = pName;
        groesse = pGroesse;
        alter = pAlter;
    }
    public void print()
    {
        System.out.println(name +" "+groesse +" " +alter);
        System.out.println(" ");
    }
}
