/* @author Rand Kadir
 * diese Program erstellt einen Rechner mit plus und minus funktion
 * erster Kontakt mit Graphischen Oberflächen für mich
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Rechner extends JFrame implements ActionListener
{
    JButton plus;
    JButton minus;
    JTextField textField;
    JTextField textField2;
    JTextField textField3;
    JPanel panel;
    String stringer;
    JLabel label;
    public Rechner() 
    {     
        //texte wo die zahlen reinkommen
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(40,40));
        textField2 = new JTextField();
        textField2.setPreferredSize(new Dimension(40,40));
        textField3 = new JTextField();
        textField3.setPreferredSize(new Dimension(40,40));
        textField3.setEditable(false);
        
        
        
        
        
        //rechen operatoren buttons
        plus = new JButton("+");
        minus = new JButton("-");
                
        //das panel wo die sachen reinkommen
        panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(50, 50, 15, 40));
        panel.setLayout(new GridLayout(0, 1));
        panel.add(plus);
        panel.add(minus);
        panel.add(textField);
        panel.add(textField2);
        panel.add(textField3);
        label = new JLabel("");
        
        plus.addActionListener(this);
        minus.addActionListener(this);
        

        this.add(panel, BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        new Rechner();
    }

    public void actionPerformed(ActionEvent e){
        if(e.getSource()==plus){
         int ergebnis = Integer.parseInt(textField.getText()) +  Integer.parseInt(textField2.getText());
         String stringer = String.valueOf(ergebnis);
         textField3.setText(stringer);
        } else if(e.getSource()==minus)
        {
            int ergebnis = Integer.parseInt(textField.getText()) -  Integer.parseInt(textField2.getText());
            String stringer = String.valueOf(ergebnis);
            textField3.setText(stringer);
        }
    }
}
