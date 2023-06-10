import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Multiple Choice quiz
 * 
 * @author Rand Kadir 
 * @version 10.06.2023
 */
public class Quiz extends JFrame implements ActionListener
{
    JButton antwortA;
    JButton antwortB;
    JButton antwortC;
    JTextField fragenField;
    JTextField antwortenField;
    JTextField resultat;
    JLabel label;
    JFrame frame;
    int anzahlRichtig;
    int aktuelleFrage;
    char antwort;
    char richtig[] = {'B','C','A','C','A','B','B','C','B','A'};
    int pointer;
    String fragen[] = {
                                "Was ist die Hauptstadt von Italien?",
                                "Was ist die Hauptstadt von den Vereinigten Staaten?",
                                "Was ist die Hauptstadt von Belgien?",
                                "Was ist die Hauptstadt von Frankreich?",
                                "Was ist die Hauptstadt von Dänemark?",
                                "Was ist die Hauptstadt von Spanien?",
                                "Was ist die Hauptstadt von Griechenland?",
                                "Was ist die Hauptstadt von Russland?",
                                "Was ist die Hauptstadt von Irland?",
                                "Was ist die Hauptstadt von Brazilien?",
                          };
    String antworten[] = {
                                    "A:Venice, B:Rom, C:Milan",
                                    "A:Los Angeles, B:Las Vegas, C:New York",
                                    "A:Brussels, B:Antwerp, C:Leuven",
                                    "A:Marseile, B:Bordeaux, C:Paris",
                                    "A:CopenHagen, B:Aarhus, C:Aalborg",
                                    "A:Barcelona, B:Madrid, C:Seville",
                                    "A:Thessaloniki, B:Athens, C:Patras",
                                    "A:Saint Petersburg, B:Yekaterinburg, C:Moskau",
                                    "A:Cork, B:Dublin, C:Waterford",
                                    "A:Brasilia, B:Sao Paulo, C:Rio de Janeiro"
                               };
     public Quiz()
    {
        aktuelleFrage = 1;
        anzahlRichtig = 0;
        pointer = 0;
        
        antwortA = new JButton("A");
        antwortB = new JButton("B");
        antwortC = new JButton("C");
        antwortA.setBounds(50, 175, 75, 75);
        antwortB.setBounds(183, 175, 75,75);
        antwortC.setBounds(316, 175, 75,75);
        //die Fragen
        fragenField = new JTextField("");
        fragenField.setBounds(50,50,340,30);
        fragenField.setEditable(false);
        antwortenField = new JTextField("");
        antwortenField.setBounds(50,100,340,30);
        antwortenField.setEditable(false);
        
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 250);
        frame.setVisible(true);
        frame.add(antwortA);
        frame.add(antwortB);
        frame.add(antwortC);
        frame.add(fragenField);
        frame.add(antwortenField);
        antwortA.addActionListener(this);
        antwortB.addActionListener(this);
        antwortC.addActionListener(this);
        
        naechsteFrage();
    }
    public void actionPerformed(java.awt.event.ActionEvent e) {
        if(e.getSource().equals(antwortA)) 
        {
            antwort = 'A';
            if(antwort == richtig[pointer] ){
                anzahlRichtig++;
            }            
        }
        if(e.getSource().equals(antwortB))
        {
            antwort = 'B';
            if(antwort == richtig[pointer] ){
                anzahlRichtig++;
            }
        }
        if(e.getSource().equals(antwortC))
        {
            antwort = 'C';
            if(antwort == richtig[pointer] ){
                anzahlRichtig++;
            }
        }
        pointer++;
        naechsteFrage();
    }
    public void naechsteFrage()
    {
        if(pointer < 10)
        {
            fragenField.setText(fragen[pointer]);
            antwortenField.setText(antworten[pointer]);
        } else {
            zeigResultat();
        }
    }
    public void zeigResultat(){
        antwortA.setEnabled(false);
        antwortB.setEnabled(false);
        antwortC.setEnabled(false);
        fragenField.setText("Der Test ist vorbei!");
        antwortenField.setText("Sie haben " +anzahlRichtig +" Fragen richtig beantwortet.");
    }
}
