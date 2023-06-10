/* @Verions 03.11.2021 (Final Version)
 * @author Rand Kadir
 * Dieses Programm besitzt eine Lichterkette namens Flags und man kann mit der Nutzereingabe schauen an welche Postionen die Lampen leuchten
 */
import java.util.*;
public class Aufgabe3
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        //short flags = 0b0100100100010101;   // 16 bit binär Zahl um die Leuchterketten zu erstellen
        short flags = 15;   
        boolean istZahl = false; // schauen ob der Nutzer eine Zahl eintippt die zwischen 0 bis 15 ist
        byte k = 0;
        System.out.println("Sie finden jetzt heraus welche Lampe an der 16 Stelligen Leuchterkette leuchtet.");
        while(istZahl == false)
        {
            System.out.println("Bitte geben sie eine Zahl zwischen 0 und 15 an, diese Zahl ist die Position in der Leuchterkette");
            k = scanner.nextByte();
            if( 0 <= (int) k && (int) k <= 15)
            {
                istZahl = true;
            } else {
                System.out.println("Die Zahl war nicht zwischen 0 und 15, versuchen Sie es erneut");
            }
        }                 
        byte b;
        b = (byte) ((flags>>k) & 0x01); // es wird verglichen ob die Zahl an der 1. Position eine 1 ist oder nicht, indem man alle andere Zahlen wegstreicht hinter der 1. Zahl
        if(b == 1) // 1 heißt die Lampe leuchtet
        {
            System.out.println("Die Lampe ist an stelle "+k +" am leuchten");
        }
        if(b == 0) // 0 heißt die Lampe leuchtet nicht
        {
            System.out.println("Die Lampe ist an stelle "+k +" nicht am leuchten") ;
        }
    }              
}
