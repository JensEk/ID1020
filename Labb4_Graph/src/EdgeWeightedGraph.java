/*README
EdgeWeightedGraph klass till higher grade för Graph labb. Använder sig av Edge.java.
Skapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class EdgeWeightedGraph {


    private final int V;
    private Bag<Edge>[] adj;


    // Initierar en graph med antalet hörn som argument
    public EdgeWeightedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;

        adj = (Bag<Edge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Edge>();
        }
    }


    // Returnerar antalet hörn i grafen.
    public int V() {
        return V;
    }

    // Tar emot en edge med dess kopplingar och weight och adderar det till en bag array för vardera hörn.
    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);

        adj[v].add(e);
        adj[w].add(e);
    }


    public Iterable<Edge> adj(int v) {
        return adj[v];
    }



    // Returnerar ett iterabelt listobjekt med alla edge i grafen.
    public Iterable<Edge> edges() {
        Bag<Edge> list = new Bag<Edge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (Edge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // Check för att inte lägga till en edge dubbelt.
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    // Bag class till EdgeWeightedGraph som sparar alla edges för varje hörn.
    public class Bag<Edge> implements Iterable<Edge> {
        private Node<Edge> first;    // Node pekare till första objeket i bag.
        private int n;               // Antalet objekt i bag

        // Node klassen
        private class Node<Edge> {
            private Edge item;
            private Node<Edge> next;
        }

        // Skapar en tom bag
        public Bag() {
            first = null;
            n = 0;
        }


        public int size() {
            return n;
        }

        // Lägger till nya objeketet och sätter first pekaren att peka till den.
        public void add(Edge item) {
            Node<Edge> oldfirst = first;
            first = new Node<Edge>();
            first.item = item;
            first.next = oldfirst;
            n++;
        }

        // Iterator klass för bag klassen

        public Iterator<Edge> iterator() {
            return new LinkedIterator(first);
        }


        private class LinkedIterator implements Iterator<Edge> {
            private Node<Edge> current;

            public LinkedIterator(Node<Edge> first) {
                current = first;
            }

            public boolean hasNext() {
                return current != null;
            }

            public void remove() {
                throw new UnsupportedOperationException();
            }

            public Edge next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                Edge item = current.item;
                current = current.next;
                return item;
            }
        }

    }


}

