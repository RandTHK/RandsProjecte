/*@author Rand Kadir, Arian Ghandehari-Saati
  @version 31.10.2024
  Main Methode von der Aufgabe des 1. Praktikums von SE
 */

import kunden.Mietvertrag;
import vermietung.Auto;
import vermietung.AutoVermietung;

public class AutoMain {
    public static void main(String[] arg){
        //die Objekte werden so deklariert und initialisiert wie in der Aufgabe gefordert
        Auto Auto1 = new Auto(20,"AC-P 1234");
        Auto Auto2 = new Auto(30, "DN-T 2345");
        Auto Auto3 = new Auto(123, "K-TZ 9876");

        AutoVermietung Autovermietung1 = new AutoVermietung("Koeln","Minipreis");
        AutoVermietung Autovermietung2 = new AutoVermietung("Bonn", "Maxipreis");

        Mietvertrag Vertrag1 = new Mietvertrag(2,3, Autovermietung1, Auto1);
        Mietvertrag Vertrag2 = new Mietvertrag(51,3,Autovermietung1, Auto3);
        Mietvertrag Vertrag3 = new Mietvertrag(4,4,Autovermietung2, Auto2);
        Mietvertrag Vertrag4 = new Mietvertrag(10,22,Autovermietung2, Auto2);

        Auto1.addMietVertrag(Vertrag1);
        Auto3.addMietVertrag(Vertrag2);
        Auto2.addMietVertrag(Vertrag3);
        Auto2.addMietVertrag(Vertrag4);

        Autovermietung1.addMietvertrag(Vertrag1);
        Autovermietung1.addMietvertrag(Vertrag2);
        Autovermietung2.addMietvertrag(Vertrag3);
        Autovermietung2.addMietvertrag(Vertrag4);
        //ausrechnung der Gebühren
        int help1, help2;
        help1 = Autovermietung1.summeAllerVertraege();
        help2 = Autovermietung2.summeAllerVertraege();
        //konsolen nachricht für das Ergebnis der summeAllerVertraege() methode
        System.out.println("die Firma MiniPreis hat "+help1 +"€ an Geld in den Verträgen");
        System.out.println("die Firma MaxiPreis hat "+help2 +"€ an Geld in den Verträgen");
                            //Autovermietung1/2.getName() könnte man hier auch nutzen aber wir möchten eine schnellere Laufzeit
    }
}
