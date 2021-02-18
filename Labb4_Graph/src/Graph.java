/*README
Uppgift 1 och 2 Graph klass som använder sig av en Bag klass för att spara vilka kopplingar som finns till vardera hörn.
Skapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/
 */


import java.util.Iterator;
import java.util.NoSuchElementException;

public class Graph {

    // Deklarering av Bag array.
    private final int v;
    private Bag<Integer>[] number;


    // Klass för att initiera graph med antal hörn som argument.
    public Graph(int v) {

        this.v = v;
        number =(Bag<Integer>[]) new Bag[v];

        for (int i = 0; i < v; i++) {
            number[i] = new Bag<>();
        }
    }

    public Iterable<Integer> adj(int v) {
        return number[v];
    }

    // Metod för att kopppla ihop två hörn med varandra för two way graph
    public void addEdge (int v, int w) {
        number[v].add(w);
        number[w].add(v);

    }

    // Metod för att koppla ihop två hörn med directed graph.
    public void addEdgeDi (int v, int w) {
        number[v].add(w);

    }

    public int V() {
        return v;
    }


    // Bag klass som Graph använder sig av för att spara vilka hörn som det finns koppling till från ett specifikt hörn.
    public class Bag<Item> implements Iterable<Item> {
        private Node<Item> first;    // Node som pekar till första objeket.
        private int n;               // Antal noder i en bag.


        private class Node<Item> {
            private Item item;
            private Node<Item> next;
        }

        // Skapar en tom bag
        public Bag() {
            first = null;
            n = 0;
        }


        public int size() {
            return n;
        }

        // add lägger till ett nytt objekt och sätter first pekaren att peka på det.
        public void add(Item item) {
            Node<Item> oldfirst = first;
            first = new Node<Item>();
            first.item = item;
            first.next = oldfirst;
            n++;
        }

        // Iterator klass för bag
        public Iterator<Item> iterator() {
            return new LinkedIterator(first);
        }

        private class LinkedIterator implements Iterator<Item> {
            private Node<Item> current;

            public LinkedIterator(Node<Item> first) {
                current = first;
            }

            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Item next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Item item = current.item;
                current = current.next;
                return item;
            }
        }

    }
}
