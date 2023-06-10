/* @author Rand Kadir & Jonas Lux
 * Diese Programm geht eine Liste an Zahlen durch und ersetzt jede Zahl die z1
 * hat mit einem "Bum", außerdem werden alle Zahlen die durch z2 teilbar sind ohne Restwerte
 * auch durch einen "Bum" ersetzt
 */

import java.util.*;

public class Aufgabe3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int z1, z2, t;
        z1 = 0;
        z2 = 0;
        t = 0;
        // Einlesen der Variablen
        do {
            System.out.println("enter z1 (untere Grenze)");
            z1 = scan.nextInt();
            System.out.println("enter z2 (obere Grenze)");
            z2 = scan.nextInt();
            if (z1 > 0)
                System.out.println("choose a z1 > 0");
            if (z1 < z2)
                System.out.println("choose a z2 > z1");
        } while (z1 > 0 && z2 > z2);
        do {
            System.out.println("enter t (Divisor)");
            t = scan.nextInt();
            if (t < 2 || t > 9)
                System.out.println("choose a t between 2 and 9");
        } while (t < 2 || t > 9);
        scan.close();
        // Start der Ausgabe:
        System.out.println("generating output: ");
        while (z1 < z2) {
            // digitFound ist die Bedingung für die "boom" Ausgabe
            boolean digitFound = false;
            // z1 % t == 0 ist wahr wenn z1 durch t teilbar ist - dann wird digitFoun direkt
            // true
            if (z1 % t != 0) {
                int tempInt = z1;
                while (tempInt > 0) {
                    // letzte Ziffer von tempInt (anfangs z1) durch z1 modulu 10 gespeichert
                    int lastDigit = tempInt % 10;
                    // letzte Ziffer von tempInt (anfangs z1) wird weggenommen (/ 10)
                    tempInt = tempInt / 10;
                    if (lastDigit == t) {
                        digitFound = true;
                        break;
                    }
                    ;
                }
            } else {
                digitFound = true;
            }
            if (digitFound) {
                System.out.println("bum");
            } else {
                System.out.println(z1);
            }
            z1++;
        }
    }
}