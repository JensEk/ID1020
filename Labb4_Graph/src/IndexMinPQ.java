/*README
Priority queue som implementeras med en min binary heap och en array med tillhörande keys(weights) till DijkstraUndirectedSP algoritmen för higher grade i graph labben.
Skapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class IndexMinPQ<Key extends Comparable<Key>> implements Iterable<Integer> {
    private int maxN;        // maximum number of elements on PQ
    private int n;           // Antalet element i queue
    private int[] pq;        // pq innehåller motsvarande hörn till dess prio så index 1 är prio 1.
    private int[] qp;        // qp innehåller motsvarande prio till varders hörn så index 1 är hörn 1.
    private Key[] keys;      // keys innehåller total weight från startpunkt


    /* Tar emot antalet hörn som finns i grafen och initierar pq och qp array för den storleken.
    Där pq innehåller vilka hörn som hör till vilken prioritets plats och qp innehåller det omvända alltså vilken prioritet varje hörn har där hörn 1 har indexplats 1 i qp. */

    public IndexMinPQ(int maxN) {
        if (maxN < 0) throw new IllegalArgumentException();
        this.maxN = maxN;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++)
            qp[i] = -1;
    }


    public boolean isEmpty() {
        return n == 0;
    }

    // Kollar om ett hörn existerar i priority queuen genom att kolla om qp har uppdaterats med dess prioritet
    public boolean contains(int i) {
        return qp[i] != -1;
    }

    // Tar emot ett hörn och dess avstånd till starthörnet. qp innehåller prioriteringen för varje hörnplats medans pq har koll på vilket hörn som hör till respektive prioritetsordning, alltså index1 är högst prioritering. Keys innehåller totala weighten från source. n vilken prioritet som är aktuell.
    public void insert(int i, Key key) {

        if (contains(i)) throw new IllegalArgumentException("index is already in the priority queue");
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }
    // Returnerar det objekt som har högst prioritet. Uppdaterar sedan kö med exch som flyttar fram nästa objekt och sätter sedan qp på dess plats till -1 igen för att ta bort det ur kö.
    public int delMin() {
        if (n == 0) throw new NoSuchElementException("Priority queue underflow");
        int min = pq[1];
        exch(1, n--);
        sink(1);
        qp[min] = -1;
        keys[min] = null;
        pq[n+1] = -1;
        return min;
    }



    // Funktion för att uppdatera och minska key(weight) till ett visst hörn.
    public void decreaseKey(int i, Key key) {

        if (!contains(i)) throw new NoSuchElementException("index is not in the priority queue");
        if (keys[i].compareTo(key) == 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
        if (keys[i].compareTo(key) < 0)
            throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
        keys[i] = key;
        swim(qp[i]);
    }


    // Jämförelse av pq hörnens weight.
    private boolean greater(int i, int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }

    // Byter plats i prioritets kön.
    private void exch(int i, int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }


    // swim och sink hjälper till om heap order blir förändrad där ett hörns key(weight) i pq blir större/mindre än vad motsvarande borde ha enligt prioritetskön för en min heap.

    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= n) {
            int j = 2*k;
            if (j < n && greater(j, j+1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }


    // Iterator klass för pq
    public Iterator<Integer> iterator() { return new HeapIterator(); }

    private class HeapIterator implements Iterator<Integer> {

        private IndexMinPQ<Key> copy;


        public HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i], keys[pq[i]]);
        }

        public boolean hasNext()  { return !copy.isEmpty();                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Integer next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }



}