public interface GList<T>    // interface für ArrayInteger List, Methoden geben vor was in der Klasse sein soll
{
    int getLength();
    int insertLast(T value);
    T getFirst();
    int deleteFirst();
    boolean search(T value);
    void print( );
    T getLast();
}
