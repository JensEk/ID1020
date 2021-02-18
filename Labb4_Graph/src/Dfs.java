/*README
* Uppgift 1 Dfs
* Algoritm som startar i en startpunkt och sedan letar sig utåt vidare bland kopplade hörn och markerar de som besökta. Tillsammans med en stack så läggs varje hörn som är besökt där och när till slut inga
* mer obesökta hörn finns kopplade så poppas från stacken tills ett hörn hittas med nya obesökta kopplingar. O(V + E)
* SKapad av: Jens Ekenblad med inspiration av https://algs4.cs.princeton.edu/code/ */

public class Dfs {

    private boolean[] marked;    // Markerar om ett hörn har besökts
    private int[] edgeTo;        // Senast besökta hörn som angränsar
    private final int s;         // Start hörn


    // Skapar en dfs över den inmatade grafen tillsammans med en startpunkt
    public Dfs(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.V()];
        marked = new boolean[G.V()];
        dfs(G, s);
    }

    // Tar emot en startposition och sedan går igenom tillhörande bag med vilka kopplingar som finns och kallar på sig rekursivt vidare i grafen och markerar besökta med sant tills alla är besökta.
    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }


    public boolean hasPathTo(int v) {

        return marked[v];
    }

    // Parameter v är slutmålet och backtrackar därifrån genom att pusha till stacken och gå igenom vilka förbindelser som finns tills den når startpunkten.
    public Iterable<Integer> pathTo(int v) {

        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}

