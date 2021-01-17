

public class CheckBalance {

    // Deklarera en node first och en int för stack size.
    private Node first;
    private int sizeofstack;

    // Nodeklass skapas
    private class Node {

        private char item;
        private Node next;

    }

    public int size () {

        return sizeofstack;
    }

    // newNode initieras och sätts att peka mot first och sedans flyttas first ref till den nya noden enligt lifo.
    public void push (char item) {


        Node newNode = new Node();
        newNode.item = item;
        newNode.next = first;
        first = newNode;
        sizeofstack++;


    }
    // returneras det item som first pekar mot och flyttar pekaren till first->next.
    public char pop() {

        char item = first.item;
        first = first.next;
        sizeofstack--;

        return item;
    }

}
