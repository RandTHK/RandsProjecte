/* @author Rand Kadir
 * @Version 29.10.2021
 * Dieses Programm zeigt den Buchstaben 2 Stellen hinten im Alphabet von der Benutzereingabe.
 */
import java.util.*;
public class Aufgabe4
{
    public static void main(String[] arg)
    {
        Scanner scanner = new Scanner(System.in); //Scanner für die Benutzereingabe
        String input; 
        char buchstabe = '1' ;
        char newBuchstabe ='1';
        boolean istBuchstabe = false; 
        while(istBuchstabe == false) // solange  der Benutzer einen  Fehler macht, wiederholt sich das Programm bis was gesucht wird eingegeben wird (ein buchstabe im alphabet)
        {
            System.out.println("Bitte geben Sie ein Buchstaben ein");
            input = scanner.nextLine(); // Benutzereingabe
            input = input.toUpperCase();
            if(input.length() == 1) //hier schauen wir ob der Benutzer mehr als ein Zeichen eintippt, wenn es falsch ist geht das Programm zu Zeile 45
            {
                buchstabe = input.charAt(0); 
                if(!( 65 <= (int) buchstabe && (int) buchstabe <= 90)) // hier wird mit der Ascii Tabelle gearbeitet, Großbuchstaben sind auf der Ascii Tabelle 65-90 im Dezimal System
                {
                    System.out.println("Sie haben kein Buchstabe eingegeben, versuchen sie es erneut");
                }
                if( 89 == (int) buchstabe) //die sonderfälle sind Y und Z, weil kein Buchstabe dahinter kommt 
                {
                    newBuchstabe = 'A';
                    istBuchstabe = true;
                }
                if( 90 == (int) buchstabe)
                {
                    newBuchstabe = 'B';
                    istBuchstabe = true;
                }
                if( 65 <= (int) buchstabe && (int) buchstabe <= 88)
                {
                    newBuchstabe = buchstabe; // neuer char, damit ich in der Konsole beide chars richtig benutzen kann
                    newBuchstabe++;
                    newBuchstabe++; //hier wird der Buchstabe um 2 Plätze nach hinten geschoben in der Tabelle
                    istBuchstabe = true;
                }
            }   else { 
                System.out.println("Die Eingabe ist mehr als ein Zeichen, versuchen sie es erneut");
            }
        }
        System.out.println("Der Buchstabe 2 Plätze hinten im Alphabet von "+buchstabe +" lautet "+newBuchstabe);
    }
}
