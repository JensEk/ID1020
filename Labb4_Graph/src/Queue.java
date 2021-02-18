/*README
 * Queue class som används till Bfs.
 * SKapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/ */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // Node för att hålla koll på första objeket i kön
    private Node<Item> last;     // Node för att hålla koll på sista objeket i kön
    private int n;               // Antalet noder i queuen


    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    // Skapar en tom queue.
    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }


    public boolean isEmpty() {
        return first == null;
    }


    // Enqueue lägger till det aktuella objeket sist i queuen och flyttar last pekaren till den.
    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    // Returnerar det som ligger först i queuen och flyttar pekaren first bakåt ett steg.
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }



    // Iterator class för queue
    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

}
