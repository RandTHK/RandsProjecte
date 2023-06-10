/* @author Rand Kadir
 * @dieses Programm lest die Datei "daten.txt" um die attribute und 
 * variablen zu initialisieren, außerdem kann es auch eine neue Datei schreiben mit den neuen attributen.
 * @version 14.06.2022
 */

import java.util.*;
import java.io.*;

public class HauptProgramm
{
    public static void main(String[] args)
    {
        String name;
        double groesse;
        int alter;
        int anzahl;
        Mensch[] reihe;
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\randk\\OneDrive\\Desktop\\studiumssachen\\PI2 P1\\V4\\daten.txt"));
            String s;
            anzahl = Integer.parseInt(br.readLine());
            reihe = new Mensch[anzahl];
            for(int i=0;i<anzahl;i++)
            {
                name = br.readLine();
                groesse = Double.parseDouble(br.readLine());
                alter = Integer.parseInt(br.readLine());
                reihe[i] = new Mensch(name, groesse, alter);
            }
            for(int i=0;i<anzahl;i++)
            {
                reihe[i].print();
            }
        } catch(Exception ex){
            return;
        }
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\randk\\OneDrive\\Desktop\\studiumssachen\\PI2 P1\\V4\\PI4\\daten2.txt"));
            bw.write(String.valueOf(anzahl)+"\n");
            for(int i=0;i<anzahl;i++)
            {
                bw.write(reihe[i].name +"\n");
                bw.write(reihe[i].groesse +""+"\n");
                bw.write(String.valueOf(reihe[i].alter)+"\n");
            }
            bw.close();
        } catch(Exception ex) {
            return;
        }
        try{
            BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\randk\\OneDrive\\Desktop\\studiumssachen\\PI2 P1\\V4\\PI4\\daten2.txt"));
            String s;
            anzahl = Integer.parseInt(br.readLine());
            reihe = new Mensch[anzahl];
            for(int i=0;i<anzahl;i++)
            {
                name = br.readLine();
                groesse = Double.parseDouble(br.readLine());
                alter = Integer.parseInt(br.readLine());
                reihe[i] = new Mensch(name, groesse, alter);
            }
            for(int i=0;i<anzahl;i++)
            {
                reihe[i].print();
            }
        } catch(Exception ex){
            return;
        }
    }
}
