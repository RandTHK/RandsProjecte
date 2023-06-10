
/* @author Rand Kadir
 * @Version 03.11.2021 (Final Version)
 * Generiert 10 Zahlen mithilfe der formel (a*z+b) mod m, wo a, b und m von dem Nutzer eingetippt wird, z ist eine Zufallszahl
 */

import java.util.Scanner;
import java.util.Random;
public class Aufgabe1   
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Scanner für die Nutzereingabe
        System.out.println("Sie rechnen gerade (a*z+b) mod m, deklarieren sie jetzt ihre variablen");
        System.out.println("Geben sie Ihr a ein");
        int a = scanner.nextInt();
        System.out.println("Geben sie Ihr b ein");
        int b = scanner.nextInt();
        boolean istMRichtig = false;
        int m =  0;
        
        while(istMRichtig == false)
        {   System.out.println("Geben sie Ihr m ein");
            m = scanner.nextInt();
            if(m == 0)
            {
                System.out.println("Man kann nicht durch 0 teilen, bitte geben sie einen anderen Wert an");
            } else {
                istMRichtig = true;
            }
        }
        Random random = new Random();
        int z = random.nextInt(1000 + 1);
        int[] myIntArray = new int[10];
        myIntArray[0] = (a*z+b)%m;
                // anzahl der Zahlen die wir in der Liste eintippen werden
        boolean weiterMachen = true;          // boolean um zu schauen ob der Nutzer mehr Listen erzeugen möchte
        String debug = scanner.nextLine();    // ohne diesen debug String lest der PC 2 eingaben aufeinmal, das wollen wir nicht
        while(weiterMachen==true)
        {
            System.out.println("Zum erzeugen einer neuen Liste x eingeben, zum beenden des Programs, stop eingeben"); 
            String eingabe = scanner.nextLine();
            String test = "x";  //die Eingaben die der Nutzer geben muss um weiter zu machen, dies wird in der if verzweigung in Zeile 28 abgerufen
            String stopTest = "stop";  // das selbe wie der String test, wird in Zeile 38 benutzt

            if(eingabe.equals(test) == true) //wenn der nutzer X eingibt wird eine reihe an Zahlen produziert
            {                
                for (int i = 1; i < 10; i++) // folgender vorgang wird 10 mal wiederholt
                {                      
                    z = myIntArray[i-1];     // unser Zufallsgenerator nimmt eine Zahl zwischen 1-1000
                    myIntArray[i] = (a*z+b)%m;
                    System.out.println(myIntArray[i]);
                }         
            } else {
                if(eingabe.equals(stopTest) == true) // wenn der nutzer stop eingibt dann hört das program auf mithilfe der umänderung von weiterMachen auf
                {
                    weiterMachen = false;
                } else {
                    System.out.println("Sie haben eine ungültige Eingabe eingegeben, versuchen sie es erneut"); // wenn es weder x oder stop ist dann wiederholt sich die Schleife wieder in Z22
                }
            }
        }
    }

}
