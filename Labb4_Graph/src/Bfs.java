/*README
 * Uppgift 1 Bfs
 * Algoritm som startar i en startpunkt och sedan söker sig ut nivå för nivå genom att använda en queue för besöka och markera nästa hörn som besökt. O(V + E)
 * SKapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/ */

public class Bfs {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;  // Markerar om ett hörn har besökts
    private int[] edgeTo;      // Senast besökta hörn som angränsar
    private int[] distTo;      // Håller koll på antal hörn som besöks för att ge kortast avstånd


    // Skapar en Bfs över en angiven graf och en startpunkt.
    public Bfs(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];

        bfs(G, s);


    }


    /* Börjar med att sätta avstånden från startpunkten till resterande hörn på INFINITY och markerar första punkten som besökt. Genom att använda en queue så besöks alla närliggande hörn
    * och markeras som besökta och läggs till i queuen. När inga fler obesökta hörn finns går den vidare till nästa hörn i queuen och rekursivt upprepar funktionen */
    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {
            int v = q.dequeue();
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }


    public boolean hasPathTo(int v) {

        return marked[v];
    }


    public int distTo(int v) {

        return distTo[v];
    }

    /* Parameter v är slutmålet och backtrackar därifrån genom att pusha till stacken och gå igenom vilken förbindelse som finns tills den når startpunkten där distTo är 0. */
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }



}
