

public class StackList {


    // Deklarerar en Node för att hålla koll på first och en int för storlek av stacken.
    private Node first;
    private int sizeofstack;


    // Node klassen
    private class Node {

        private char item;
        private Node next;

    }


    public boolean isEmpty() {

        return first == null;
    }

    public int size () {

        return sizeofstack;
    }

    // Skapar en tillfällig node oldfirst som ref till first sedans initieras first till en ny node och sätter dess first->next till gamla first.
    public void push (char item) {

        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;
        sizeofstack++;
    }

    // Returnerar det item som first pekar på och flyttar first till nästa node
    public char pop() {

        char item = first.item;
        first = first.next;
        sizeofstack--;

        return item;
    }







}