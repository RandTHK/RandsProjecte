/**
 * Dieses Programm verschlüsselt und entschlüsselt wörter.
 * 
 * @author Rand Kadir & Jonas Lux 
 * @version 24.11.2021
 */

import java.util.*;
public class Aufgabe2
{
    public static void main(String[] args)
    {
        System.out.println("Tippen Sie 1 für verschluesseln und 2 für entschluesseln ein");
        Scanner scanner = new Scanner(System.in);
        int userinput;
        do{
             userinput = scanner.nextInt();
             if(userinput != 1 && userinput != 2)
             {
                 System.out.println("das war keine 1 oder 2");
                }
        } while(userinput != 1 && userinput != 2);        
        int n = 0;
        do
        {
            System.out.println("bitte geben Sie eine ganze Zahl zwischen 1 und 25 ein");
            Scanner s2 = new Scanner(System.in);
            n = s2.nextInt();
            if(n >= 1 && n <=25) //Nutzereingabe 
            {
                break;
            } else{
                System.out.println("Das war eine Falsche eingabe");
            }
        } while(n >= 1 && n <= 25);
        System.out.println("Bitte tippen sie Ihr Wort ein");
        String bauer = scanner.nextLine();
        String wort = scanner.nextLine();
        StringBuffer sb = new StringBuffer(wort.toLowerCase());        
        // was ich zu tun habe ist jeden charakter um n plätze verschieben und die dann in einen anderen Buffered String reinzuschieben, das umgekehrte gilt beim entschlüsseln
        char[] charArray = new char[sb.length()];
        StringBuffer schluessel = new StringBuffer("");        
        for(int i=0;i<sb.length();i++) // methode aufrufen so oft t
        {
            charArray[i] = sb.charAt(i);
            for(int k=0;k<n;k++)
            {
                if (userinput == 1) {
                    charArray[i] = (char)((int) charArray[i] + 1);
                    if((int) charArray[i] >= 123)
                    {
                       charArray[i] = 97;
                    }
                }
                if (userinput == 2) {
                    charArray[i] = (char)((int) charArray[i] - 1);
                    if((int) charArray[i] < 97)
                    {
                       charArray[i] = 122;
                    }
                }
                      
            }
            schluessel = schluessel.append(charArray[i]);
        }
        System.out.println(schluessel);
    }
}
