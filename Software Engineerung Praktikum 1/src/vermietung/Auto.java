package vermietung;/*@author Rand Kadir, Arian Ghandehari-Saati
  @version 31.10.2024
  die vermietung.Auto Klasse von der Aufgabe des 1. Praktikum des SE
 */

import java.util.*;
import vermietung.*;
import kunden.*;
public class Auto {
    private int gebuehrProWoche;
    private String kennzeichen;
    private ArrayList<Mietvertrag> vertraegeAuto;
    public Auto(){ //default konstruktor
        gebuehrProWoche = 0;
        kennzeichen = "";
    }
    public Auto(int pGebuehrProWoche, String pKennzeichen){ //parameter konstruktor
        gebuehrProWoche = pGebuehrProWoche;
        kennzeichen = pKennzeichen;
        vertraegeAuto = new ArrayList<Mietvertrag>();
    }
    public void addMietVertrag(Mietvertrag pVertrag)
    {
        vertraegeAuto.add(pVertrag);
    }
    public boolean delMietVertrag(Mietvertrag pVertrag) //delete methode mit return ob das zu suchende Objekt gefunden und gelöscht wurde
    {
        boolean istDa = false;
        for(int i=0; i<vertraegeAuto.size();i++)
        {
            if(vertraegeAuto.get(i).equals(pVertrag))
            {
                vertraegeAuto.remove(i);
                istDa = true;
            }
        }
        return istDa;
    }
    //getter und setter methoden (ArrayList setten ist nicht dabei; unsicher ob dies benötigt wird)
    public int getGebuehrProWoche(){
        return gebuehrProWoche;
    }
    public String getKennzeichen(){
        return kennzeichen;
    }
    public ArrayList<Mietvertrag> getVertraegeAuto() {return vertraegeAuto;}
    public void setGebuehrProWoche(int pGebuehren) {gebuehrProWoche = pGebuehren;}
    public void setKennzeichen(String pKennzeichen) {kennzeichen = pKennzeichen;}
    public void setVertraegeAuto(ArrayList<Mietvertrag> pVertraegeAuto){vertraegeAuto = pVertraegeAuto;}

}
