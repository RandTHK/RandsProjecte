import java.util.*;
/**
 * PraktikumsVersuch 3
 * 
 * @author (RandKadir) 
 * @version (02.06.2022)
 */
public class HauptProgramm
{
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Für eine Double Liste bitte die 1, Für eine Int Liste die 2");
        int eingabe = scanner.nextInt();
        if(eingabe == 1)
        {
            System.out.println("Geben Sie die laenge der Liste an");
            eingabe = scanner.nextInt();
            ArrayGList list = new ArrayGList<Double>(eingabe);
            duesen(scanner, list);
        } else if(eingabe == 2)
        {
            System.out.println("Geben Sie die laenge der List an");
            eingabe = scanner.nextInt();
            ArrayGList list = new ArrayGList<Integer>(eingabe);
            duesen(scanner, list);
        }
    }
    public static<T> void duesen(Scanner scanner, ArrayGList<T> list)
    {
        int eingabe;
        T einfuegen;
        loop: while(true)
        {
            System.out.println("");
            System.out.println("Um die laenge der Liste zu sehen, drücken Sie die 1");
            System.out.println("Um einen Wert in die Liste ein zu fügen , drücken Sie die 2");
            System.out.println("Um den Ersten Wert in der Liste zu sehen, drücken Sie die 3");
            System.out.println("Um den Ersten Wert zu löschen, drücken Sie die 4");
            System.out.println("Um nach einem Wert zu suchen, drücken Sie die 5");
            System.out.println("Um die Liste wieder zu geben, drücken Sie die 6");
            System.out.println("Um das Programm zu beenden, drücken Sie die 7");
            System.out.println("Um das Letzte Objekt zu sehen, drücken Sie die 8");
            eingabe = scanner.nextInt();
            switch(eingabe) {
                case 1:
                    System.out.println(list.getLength());
                break;
                
                case 2:
                    System.out.println("Geben Sie den Wert ein");
                    einfuegen = (T)(Object)scanner.nextLine();
                    einfuegen = (T)(Object)scanner.nextLine();
                    if(list.insertLast((T)(Object)einfuegen) == -1)
                    {
                        System.out.println("Die Liste ist voll");
                    }
                    break;
                case 3:
                    System.out.println(list.getFirst());
                    break;
                case 4:
                    int esse = list.deleteFirst();
                    if(esse > 0)
                    {
                        System.out.println("Der Wert " +esse +" wurde gelöscht");
                    } else if(esse == -1) {
                        System.out.println("Am Anfang der Liste gab es keinen Wert");                        
                    } else if(esse == -9999)
                    {
                        System.out.println("Die Liste ist Leer");
                    }
                    break;
                case 5:
                    System.out.println("Geben Sie den Wert an den Sie suchen möchten");
                    einfuegen = (T)(Object)scanner.nextLine();
                    einfuegen = (T)(Object)scanner.nextLine();
                    if(list.search(einfuegen) == true)
                    {
                        System.out.println("Der Wert ist in der Liste vorhanden");
                    } else {
                        System.out.println("Der Wert ist in der Liste nicht vorhanden");
                    }
                    break;
                case 6:
                    list.print();
                    break;
                case 7:
                    break loop;
                case 8:
                
                    System.out.println(list.getLast());
            }
        }
    }
}
