/*README
Higher grade uppgift för Graph labb.
Main program som använder sig av DjikstraUndirectedSP.java, Edge.java och EdgeWeightedGraph.java
Skapad av: Jens Ekenblad

 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HigherGrade {

    public static void main(String[] args) throws FileNotFoundException {

        File theDatabase = new File("C:\\Users\\JensE\\Programmering\\Labb4\\NYC.txt");
        Scanner reader = new Scanner(theDatabase);
        Scanner dest = new Scanner(System.in);


        int start;
        int stop;
        int weight;

        /* Börjar med att skapa ett weighted graph objekt utav storleken av antal hörn i NYC dokumentet.
        * Sedan läses varje rad in med tre variabler som tar emot start, slut och weight därimellan de två hörnen och skapar ett nytt Edge objekt av dem.
        * Därefter läggs de till i graph klassen. */

        EdgeWeightedGraph Graph = new EdgeWeightedGraph(264346);

        while (reader.hasNextLine()) {

            start = reader.nextInt();
            stop = reader.nextInt();
            weight = reader.nextInt();

            Edge edge = new Edge (start,stop,weight);
            Graph.addEdge(edge);


        }

        System.out.println("Välj start: ");
        int a = dest.nextInt();
        System.out.println("Välj slut: ");
        int b = dest.nextInt();
        System.out.println("Välj genom: ");
        int c = dest.nextInt();

        // Två objekt av Dijkstra skapas där dijk innehåller startpunkten och dijk2 skapas av mitten hörnet.

        DijkstraUndirectedSP dijk = new DijkstraUndirectedSP(Graph,a);
        DijkstraUndirectedSP dijk2 = new DijkstraUndirectedSP(Graph,c);

        if(dijk.hasPathTo(b)) {
            int size = 0;
            if(dijk.hasPathTo(c) && dijk2.hasPathTo(b)) {
                System.out.println("Shortest A -> C is: ");
                for(Edge e : dijk.pathTo(c)) {
                    size += e.weight();
                    System.out.print(e + " "); }

                System.out.println();
                System.out.println("Shortest C -> B is: ");
                for(Edge e : dijk2.pathTo(b)) {
                    size += e.weight();
                    System.out.print(e + " "); }
            }
            System.out.println();
            System.out.println("Total distance time A to B through C is : " + size);


        }
        else
            System.out.println("No path between A - B through C");


    }

}
