/*README
Edge klass till higher grade för Graph labb för att skapa en sträcka mellan två hörn.
Skapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/
 */


public class Edge implements Comparable<Edge> {

        // Deklarering av variabler
        private final int v;
        private final int w;
        private final int weight;

        // Initiering av en edge som tar emot två hörn och en weight därimellan.
        public Edge(int v, int w, int weight) {
            if (v < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");
            if (w < 0) throw new IllegalArgumentException("vertex index must be a nonnegative integer");

            this.v = v;
            this.w = w;
            this.weight = weight;
        }


        public int weight() {
            return weight;
        }

        // Returnerar ena kopplingen
        public int either() {
            return v;
        }

        // Returnerar beroende på vilket argument som skickas in det motsvarande hörnet
        public int other(int vertex) {
            if      (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }

        // Jämför två edge och dess weight vilken som är kortast.
        @Override
        public int compareTo(Edge that) {
            return Integer.compare(this.weight, that.weight);
        }

        // Metod för att manipulera string med bestämt format.
        public String toString() {
            return String.format("%d-%d |W:%d|", v, w, weight);
        }


}
