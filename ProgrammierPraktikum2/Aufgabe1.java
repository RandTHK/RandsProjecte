/* @author Rand Kadir & Jonas Lux
 * @das Projekt analysiert ob der eingegeben Port registriert ist
 */

import java.util.*;

public class Aufgabe1 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int port;
        boolean weiter = true;
        String stop = "stop";
        String bob = "x";
        String test = "";
        while (weiter) {
            do {                
                System.out.print("enter Port: ");
                port = scan.nextInt();
                if (port < 0 || port > 65536)
                    System.out.println("enter Port between 0 and 65536!");
            } while (port < 0 || port > 65536);
            if (port >= 0 && port <= 1023) {
                System.out.println("wellknown");
                switch (port) {
                    case 21:
                    System.out.println("FTP");
                    break;
                    case 23:
                    System.out.println("Telnet");
                    break;
                    case 25:
                    System.out.println("SMTP");
                    break;
                    case 80:
                    System.out.println("HTTP");
                    break;
                    case 143:
                    System.out.println("IMAP");
                    break;
                    default:
                    System.out.println("sonstiger Dienst");
                    break;
                }
            } else if (port >= 1024 && port <= 49151) {
                System.out.println("registered");
            } else if (port >= 49152 && port <= 65536) {
                System.out.println("registered");
            } else {
                System.out.println("sonstiger Dienst");
            }
            boolean eingabe = false;
            while(eingabe == false)
            {
                scan.nextLine();
                eingabe = false;
                System.out.println("Möchten sie weiter machen? Tippen Sie ein 'x'. Möchten Sie aufhören? Tippen Sie ein 'stop'");
                test = scan.nextLine();
                if(test.compareTo(bob) == 0)
                {
                    eingabe = true;
                } else if(test.compareTo(stop) == 0)
                {
                    weiter = false;
                    eingabe = true;
                } else {
                    System.out.println("Ihre Eingabe war inkorrekt, versuchen Sie es nochmal");
                }
            }
        }
    }
}
