/* @Version 03.11.2021 (Final Version)
 * @author Rand Kadir
 * dieses Programm berechnet den Hash-Wert 4 buchstabigen Wörter
 */

import java.util.*;
public class Aufgabe2
{
    public static void main(String[] args) {
        Scanner scanner = new  Scanner(System.in);
        char[] charArray = new char[4]; // wir wollen ein 4 buchstabiges Wort, deswegen 4 array slots
        char tempChar;
        System.out.println("Geben sie ihre 4 Buchstabiges Wort ein");
        boolean laengenCheck = false; // hier wollen wir sicher stellen das der nutzer gültige angaben gibt, diese sind zeichen von A bis Z und ein 4 buchstabiges Wort
        boolean zeichenCheck = false;
        int anzahlRichtig = 0; 
        int ausgabenWert = 0;
        while(laengenCheck == false || zeichenCheck == false)
        {
            zeichenCheck = false;
            laengenCheck = false; // die boolische werte werden auf false zurückgesetzt, damit die schleife nicht mit werten von der vorherigen schleife arbeitet
            anzahlRichtig = 0; // selbes Prinzip wie bei Kommentar von Zeile 20
            String input = scanner.nextLine(); // hier wird die eingabe eingelesen
            if(input.length() == 4)
            {
                input = input.toUpperCase(); //hier wird die eingabe zu Großbuchstaben gemacht, damit es übersichtlicher für die ascii tabelle ist
                laengenCheck = true;
                for(int i=0;i<4;i++)
                {
                    charArray[i] = input.charAt(i);
                }
                for(int i=0;i<4;i++)
                {
                    if( 65 <= (int) charArray[i] && (int) charArray[i] <= 90) // ascii tabelle benutzt großbuchstaben sind zwischen 60 und 90
                    {
                        anzahlRichtig++; // wenn ein Zeichen des Alphabets gegeben wurde wird diese Variable um 1 addiert
                        // wenn alles richtig war dann hat der Wert 4, in Zeile 42 benutzen wir dann diese information um unsere nächste entscheidung zu treffen
                    } else { // der fall falls es kein alphabetisches zeichen war
                        int posi = i + 1; 
                        System.out.println("ihr "+posi +". Zeichen war kein Buchstabe, versuchen Sie es erneut"); 
                    }
                }
                if(anzahlRichtig == 4) // falls alle Zeichen richtig waren dann können wir mit dem rechnen vom hash-wert weitermachen
                {
                    zeichenCheck = true; 
                } else {
                    zeichenCheck = false;
                }
            } else {
                System.out.println(" Ihre Eingabe hatte nicht exakt 4 Buchstaben, versuchen Sie es erneut");
            }
        }
        if(laengenCheck == true && zeichenCheck == true) // ob diese Verzweigung in der While Schleife oder außerhalb der Whileschleife gemacht wird ist egal
        // aber es kann sein das es außerhalb der Whileschleife mehr Arbeitsspeicher frei hält
        {
                for(int i=0;i<4;i++)
                {
                    ausgabenWert = ausgabenWert + (int) charArray[i];
                }
                ausgabenWert = ausgabenWert % 367; // die Nummer 367 wurde gewählt, weil sie nicht teilbar ist und da der maximale hash wert mit 360 bei "ZZZZ" liegt, war das die nächste Zahl
                // die unteilbar war
                System.out.println("das Hash-Wert vom eingegeben Wort lautet " +ausgabenWert); //hashwert ausgeben per Konsole
        }
    }
}
