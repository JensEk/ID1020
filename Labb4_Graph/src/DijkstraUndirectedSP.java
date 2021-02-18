/*README
DijkstraUndirectedSP klass för att hitta kortaste vägen mellan två hörn för higher grade till Graph lab. Använder sig av Edge.java, EdgeWeightedGraph.java och IndexMinPQ.java.
Priority queue implementeras med hjälp av en minimum heap vilket har en tidskomplexitet på O(logV)  där V är antal hörn, för att hantera heapen.
Sedan gäller O(E) för att ändra edges key (weight) vilket ger en total tidskomplexitet för algoritmen på O(ElogV)

Skapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/
 */

public class DijkstraUndirectedSP {

    private int[] distTo;          // Array för att hålla avstånden mellan varje hörn och starthörnet.
    private Edge[] edgeTo;            // Array med vilken som är den senaste och kortaste edgen till ett hörn
    private IndexMinPQ<Integer> pq;    // Priority queue


    /* Tar emot en graph och ett start hörn och sätter sedan avståndet till resterande hörn till max.
    * Genom att använda en priority queue där kortaste avståndet till startpunkten prioriteras och sätts först i queuen så väljs nästa hörn beroende på det.
    * Totalt avstånden mäts från start hörnet och uppdateras om en ny edge hittas som är kortare.*/
    public DijkstraUndirectedSP(EdgeWeightedGraph G, int s) {
        for (Edge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        distTo = new int[G.V()];
        edgeTo = new Edge[G.V()];


        for (int v = 0; v < G.V(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;


        pq = new IndexMinPQ<Integer>(G.V());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.adj(v))
                relax(e, v);
        }

    }

    // Hämtar motsvarande hörn i en edge och uppdaterar avståndet till det hörnet om det finns en kortare väg genom att addera avståndet fram till det kopplade hörnet + edge mellan dessa två, sedan uppdateras pq listan.
    private void relax(Edge e, int v) {
        int w = e.other(v);
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) pq.decreaseKey(w, distTo[w]);
            else                pq.insert(w, distTo[w]);
        }
    }


    public int distTo(int v) {
        return distTo[v];
    }


    // Returnerar true om det finns en väg till v genom att dess avstånd har uppdaterats tidigare.
    public boolean hasPathTo(int v) {

        return distTo[v] < Integer.MAX_VALUE;
    }

    // Returnerar ett iterabelt objekt path innehållandes den kortaste vägen från starthörn till valt sluthörn.
    public Iterable<Edge> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Edge> path = new Stack<Edge>();
        int x = v;
        for (Edge e = edgeTo[v]; e != null; e = edgeTo[x]) {
            path.push(e);
            x = e.other(x);
        }
        return path;
    }




}
