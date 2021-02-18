/*README
 * Stack class som används till hela Graph labben.
 * SKapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/ */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<Integer> implements Iterable<Integer> {
    private Node<Integer> first;     // Toppen av stacken
    private int n;                // Storlek på stacken


    private static class Node<Integer> {
        private Integer item;
        private Node<Integer> next;
    }

    // Skapar en stack
    public Stack() {
        first = null;
        n = 0;
    }

    // Metod för att pusha nytt objekt till stacken och sätta first till det nya objektet.
    public void push(Integer item) {
        Node<Integer> oldfirst = first;
        first = new Node<Integer>();
        first.item = item;
        first.next = oldfirst;
        n++;
    }


    // Iterator för stack classen
    public Iterator<Integer> iterator() {
        return new LinkedIterator(first);
    }


    private class LinkedIterator implements Iterator<Integer> {
        private Node<Integer> current;

        public LinkedIterator(Node<Integer> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }


        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            Integer item = current.item;
            current = current.next;
            return item;
        }
    }
}