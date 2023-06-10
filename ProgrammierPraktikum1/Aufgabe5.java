/* @Version 03.11.2021 (Final Version)
 * @author Rand Kadir
 * Dieses Programm fügt 2 wörter zusammen, die der Nutzer eingegeben hat. 
 */
import java.util.*;
public class Aufgabe5
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean richtig1 = false;
        boolean richtig2 = false; //so lange unsere Aufforderungen nicht alle Erfüllt werden, wiederholt sich das Programmm
        while(richtig1 == false || richtig2 == false)
        {
            System.out.println("Bitte geben Sie ihr 1. Wort ein");
            String s1 = scanner.nextLine();
            System.out.println("Bitte geben Sie ihr 2. Wort ein");
            String s2 = scanner.nextLine(); 
            int zaehler = 0;
            String temp = "";
            char charTemp;
            for(int i = 0;i<s1.length();i++) // das Programm schaut nach ob jedes Zeichen ein Zeichen des Alphabets ist
            {
                temp = s1.toUpperCase();
                charTemp = temp.charAt(i);
                if( 65 <= (int) charTemp && (int) charTemp <= 90)
                {
                    zaehler++;
                }
            }
            if(zaehler == s1.length()) // wenn alle richtige Zeichen der anzahl an existierenden Zeichen übereinstimmen, stellen wir den boolean richtig1 auf true
            {
                richtig1 = true; 
            } else { 
                System.out.println("Ihr 1. Wort hatte Sonderzeichen oder Zahlen, versuchen Sie es erneut");
            }
            zaehler = 0;
            for(int i = 0;i<s2.length();i++) // von hier bis Zeile 52 ist der selbe prozess wie Zeile 22 bis 36, nur mit dem 2. Wort
            {
                temp = s2.toUpperCase();
                charTemp = temp.charAt(i);
                if( 65 <= (int) charTemp && (int) charTemp <= 90)
                {
                    zaehler++;
                }
            }
            if(zaehler == s2.length())
            {
                richtig2 = true; 
            } else { 
                System.out.println("Ihr 2. Wort hatte Sonderzeichen oder Zahlen, versuchen Sie es erneut");
            }
            if(richtig1 == true && richtig2 == true) // wenn beide Eingaben vom Nutzer das ist was das Programm sucht, kann mein Programm das machen was wir ursprünglich wollten
            {
                String s3 = s1 + " " + s2;
                System.out.println(s3);
            }
        }
    }
}
