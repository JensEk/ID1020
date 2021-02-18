/*README
* Symboltable klass till uppgift 1 och 2 i Graph labb.
* SKapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/ */

import java.util.Iterator;

    public class BinarySearchST<Key extends Comparable<Key>, Value> {

        private Key[] keys;
        private Value[] vals;
        private int n = 0;


        // Konstruktör för att skapa arrays beroende på val av storlek från main programmet.
        public BinarySearchST(int capacity) {
            keys = (Key[]) new Comparable[capacity];
            vals = (Value[]) new Object[capacity];
        }



        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }


        // Funktionen returnerar tillhörande value som hör till den key som skickas in som argument. Genom att kalla på rank funktionen så hämtas korrekt indexplats
        public Value get(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to get() is null");

            if (isEmpty())
                return null;

            int i = rank(key);
            if (i < n && keys[i].compareTo(key) == 0)
                return vals[i];
            return null;
        }



        // Genom compareTo så jämförs den aktuella key med det som finns i mitten på arrayen och så uppdateras index variaberna lo,mid och hi så att man bryter ner indexplatserna tills rätt plats för key har hittats i arrayen.
        public int rank(Key key) {
            if (key == null)
                throw new IllegalArgumentException("argument to rank() is null");

            int lo = 0, hi = n-1;
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


        /* Put sparar in den aktuella key i arrayen och dess value som har skickats in som argument */
        public void put(Key key, Value val)  {
            if (key == null)
                throw new IllegalArgumentException("first argument to put() is null");

            int i = rank(key);

            // Om key redan existerar i arrayen så uppdateras bara value.
            if (i < n && keys[i].compareTo(key) == 0) {
                vals[i] = val;
                return;
            }


            // När en ny key ska läggas till så måste alla element i arrayen uppdateras och flyttas upp en indexplats.
            for (int j = n; j > i; j--)  {
                keys[j] = keys[j-1];
                vals[j] = vals[j-1];
            }
            keys[i] = key;
            vals[i] = val;
            n++;

        }

        public int size() {
            return n;
        }


        public boolean isEmpty() {
            return size() == 0;
        }

        public Key min() {
            return keys[0];
        }

        /* Nedanstående kod är för att kunna iterera genom key arrayen där en kopia currentKey kopierar det som finns i arrayen.  */

        public Iterator<Key> iterator()
        {
            return new keyIterator(keys[0]);
        }

        private class keyIterator implements Iterator<Key>
        {
            Key currentKey;
            int start;

            private keyIterator(Key lo)
            {
                start = 0;
                currentKey = lo;
            }


            public boolean hasNext()
            {
                return keys[start + 1] != null;
            }


            public Key next()
            {
                if(hasNext())
                {
                    Key key = currentKey;
                    currentKey = keys[++start];
                    return key;
                }
                else
                    return currentKey;
            }
        }

}
