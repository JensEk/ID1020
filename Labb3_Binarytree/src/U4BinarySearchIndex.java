/*README
 * Uppgift 4 Searching lab
 * BinarySearchST med modifiering där en arraylist används för att spara vilka indexplatser varje key finns på
 * Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
 *
 *  */

import java.util .*;
public class U4BinarySearchIndex <Key extends Comparable<Key>, Integer> {


        private Key[] keys;
        private ArrayList<Integer> [] vals;
        private int n = 0;



        public U4BinarySearchIndex(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = new ArrayList[capacity];
            for(int i = 0; i < vals.length; i++)
            {
                vals[i] = new ArrayList();
            }
        }




        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }


        public ArrayList<Integer> get(Key key) {

            int i = rank(key);

            if (i < n && keys[i].compareTo(key) == 0)
                return vals[i];
            return null;
        }

    // Genom compareTo så jämförs den aktuella key med det som finns i mitten på arrayen och så uppdateras index variaberna lo,mid och hi så att man bryter ner indexplatserna tills rätt plats för key har hittats i arrayen.
        public int rank(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n - 1;
            while (lo <= hi) {
                int mid = lo + (hi - lo) / 2;
                int cmp = key.compareTo(keys[mid]);
                if (cmp < 0)
                    hi = mid - 1;
                else if (cmp > 0)
                    lo = mid + 1;
                else
                    return mid;
            }
            return lo;
        }

        /* Funktionen tar emot vald key och sedan innehåller argument val den index plats som key är hittad på. Genom att använda javas arraylist så adderas alla index platser för vardera key. */
        public void put(Key key, Integer val) {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");

            int i = rank(key);

            // Om key redan existerar så adderas bara indexplatsen
            if (i < n && keys[i].compareTo(key) == 0) {
                vals[i].add(val);
                return;
            }

            // När en ny key ska läggas till måste hela listan flyttas ett steg och vals som innerhåller indexplatserna måste rensas efter flytten innan något nytt kan flyttas dit.
            for (int j = n; j > i; j--) {
                keys[j] = keys[j-1];
                vals[j].addAll(vals[j-1]);
                vals[j-1].clear();
            }
            keys[i] = key;
            vals[i].add(val);
            n++;

        }





}
