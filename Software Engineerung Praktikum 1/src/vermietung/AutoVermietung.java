package vermietung;/*@author Rand Kadir, Arian Ghandehari-Saati
  @version 31.10.2024
  die vermietung.AutoVermietung von der Aufgabe des 1. Praktikum des SE, besitzt die wichtige Methode summeAllerVertraege()
 */
import java.util.*;
import kunden.*;
import vermietung.*;
public class AutoVermietung {
    private String name, anschrift;
    private ArrayList<Mietvertrag> vertraegeVermietung;
    public AutoVermietung(String pName, String pAnschrift){
        name = pName;
        anschrift = pAnschrift;
        vertraegeVermietung = new ArrayList<Mietvertrag>();
    }
    public int summeAllerVertraege(){
        int summe = 0;
        int anzahlWoche = 0;
        int help1, help2;
        for(int i=0;i<vertraegeVermietung.size();i++)
        {
            help1 = vertraegeVermietung.get(i).getBeginnKW();
            help2 = vertraegeVermietung.get(i).getEndeKW(); // variablen, da die mehrmals aufgerufen werden
            if(help1 > help2) //falls man die 52. KW überschreitet einen fall einbauen
            {
                anzahlWoche = 52 - help1 + help2; //formel um die anzahl der wochen auszurechnen, falls die 52. KW überschritten wird
            } else {

                    anzahlWoche = help2 - help1;

            }
            summe = summe + (vertraegeVermietung.get(i).getAuto().getGebuehrProWoche() * anzahlWoche); // die gebuehren mal die wochen rechnen, da man die summen
            // von allen verträge wird die summe gerechnet deswegen muss man die summe addieren mit dem neuen vertrag
        }
        return summe; //ausgabe
    }
    public void addMietvertrag(Mietvertrag pVertrag) //add methode
    {
        vertraegeVermietung.add(pVertrag);
    }
    public boolean delMietvertrag(Mietvertrag pVertrag){
        boolean istDa = false;
        for(int i=0;i<vertraegeVermietung.size();i++) // man durchsucht die liste auf das selbe Objekt was zu löschen gibt
        {
            if(vertraegeVermietung.get(i).equals(pVertrag))
            {
                vertraegeVermietung.remove(i);
                istDa = true;
            }
        }
        return istDa;
    }
    //getter und setter methoden (ArrayList setten ist nicht dabei; unsicher ob dies benötigt wird)
    public String getName(){return name;}
    public String getAnschrift(){return anschrift;}
    public ArrayList<Mietvertrag> getVertraegeVermietung(){return vertraegeVermietung;}
    public void setName(String pName){name = pName;}
    public void setAnschrift(String pAnschrift){anschrift = pAnschrift;}

}
