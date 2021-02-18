/*README
* Uppgift 2 Searching Lab
* Binarysearchtree som skapar en länkad lista där mindre värden sparas till vänster och större till höger
*
* Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
*   */



public class Binarysearchtree <Key extends Comparable<Key>, Value extends Comparable<Value>> {

        private Node root;             // root of BST



        /* Node class där varje node får en Key och ett Value  */
        private class Node {

            private Key key;
            private Value val;
            private Node left, right;
            private int size;



            public Node(Key key, Value val, int size) {
                this.key = key;
                this.val = val;
                this.size = size;
            }
        }



        public boolean isEmpty() {
            return size() == 0;
        }


        public int size() {
            return size(root);
        }

        // Ger hur många löv som är kopplat till vardera node.
        private int size(Node x) {
            if (x == null) return 0;
            else return x.size;
        }


        public boolean contains(Key key) {
            if (key == null) throw new IllegalArgumentException("argument to contains() is null");
            return get(key) != null;
        }

        // Genom att kalla på get funktionen med aktuell key så görs en jämförelse med root.
        public Value get(Key key) {
            return get(root, key);
        }


        /* Genom att rekursivt kalla på sig själv så görs jämförelse med den aktuella key mot den noden som har kallats med, vilket först är rooten och sedan uppdateras argumentet för noden beroende på om key är större eller mindre
        * Mindre värden kopplas till vänster och större till höger i trädet när rätt löv har hittats så returneras dess value. */
        private Value get(Node x, Key key) {
            if (key == null)
                throw new IllegalArgumentException("calls get() with a null key");
            if (x == null)
                return null;

            int cmp = key.compareTo(x.key);

            if (cmp < 0)
                return get(x.left, key);
            else if (cmp > 0)
                return get(x.right, key);
            else
                return x.val;
        }

        // Kallar på put och gör jämförelse med rooten.
        public void put(Key key, Value val) {
            if (key == null)
                throw new IllegalArgumentException("calls put() with a null key");

            root = put(root, key, val);

        }

    /* Genom att rekursivt kalla på sig själv så görs jämförelse med den aktuella key mot den noden som har kallats med, vilket först är rooten och sedan uppdateras argumentet för noden beroende på om key är större eller mindre
     * Mindre värden kopplas till vänster och större till höger i trädet. Först görs en koll om lövet är tomt annars när samma key har hittats så uppdateras dess value istället.  */
        private Node put(Node x, Key key, Value val) {
            if (x == null)
                return new Node(key, val, 1);
            int cmp = key.compareTo(x.key);
            if (cmp < 0)
                x.left  = put(x.left,  key, val);
            else if (cmp > 0)
                x.right = put(x.right, key, val);
            else
                x.val = val;


            x.size = 1 + size(x.left) + size(x.right);
            return x;
        }


}
