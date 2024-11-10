package kunden;

import vermietung.Auto;
import vermietung.AutoVermietung;

/*@author Rand Kadir, Arian Ghandehari-Saati
  @version 31.10.2024
  die kunden.Mietvertrag Klasse von der Aufgabe des 1. Praktikum des SE
 */
public class Mietvertrag {
    private int beginnKW, endeKW;
    private AutoVermietung firma;
    private Auto auto;
    public Mietvertrag(int pBeginn,int pEnde, AutoVermietung pFirma, Auto pAuto){
        beginnKW = pBeginn;
        endeKW = pEnde;
        firma = pFirma;
        auto = pAuto;
    }

    //getter und setter methoden
    public int getBeginnKW(){
        return beginnKW;
    }
    public int getEndeKW(){
        return endeKW;
    }
    public Auto getAuto()
    {
        return auto;
    }
    public AutoVermietung getFirma(){return firma;}
    public void setBeginnKW(int pBeginnKW){beginnKW = pBeginnKW;}
    public void setEndeKW(int pEndeKW){endeKW = pEndeKW;}
    public void setAuto(Auto pAuto){auto = pAuto;}
    public void setFirma(AutoVermietung pFirma){firma = pFirma;}

}
