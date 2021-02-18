/*README
 *Uppgift 1 Bfs
  Main program för Bfs sökning av en graf. Använder sig av BinarySearchST.java, Graph.java och Bfs.java klasserna.
  SKapad av: Jens Ekenblad. */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Scanner;

public class U1bfd {


        public static void main(String[] args) throws FileNotFoundException {

            File theDatabase = new File("C:\\Users\\JensE\\Programmering\\Labb4\\contiguous-usa.txt");
            Scanner reader = new Scanner(theDatabase);
            Scanner reader2 = new Scanner(theDatabase);
            Scanner dest = new Scanner(System.in);

            /* Symboltable används för att spara tillhörande stat till en indexplats. Programmet kör igenom textfilen två gånger.
             * Första gången läses en stat in om den inte existerar tillsammans med index plats.
             * Andra gången så läses rad för rad av med vilka stater som har förbindelser och adderas till Graph objektet med hjälp av deras indexplats.  */

            BinarySearchST<String, Integer> graph = new BinarySearchST(200);

            String name;
            String start;
            String stop;
            int index = 0;

            while (reader.hasNextLine()) {
                name = reader.next();


                if (!graph.contains(name)) {
                    graph.put(name, index);
                    index += 1;
                }


            }

            Graph G = new Graph(index);

            while (reader2.hasNextLine()) {

                start = reader2.next();
                stop = reader2.next();


                G.addEdge((graph.get(start)), graph.get(stop));

            }


            System.out.println("Välj start: ");
            String a = dest.nextLine();
            System.out.println("Välj slut: ");
            String b = dest.nextLine();

            int sa = graph.get(a);
            int sb = graph.get(b);


            Bfs bfs = new Bfs (G, sa);
            Iterator itr = graph.iterator();
            String nameOf = graph.min();

            /* Kollar om det finns edge mellan a och b och printar ut den kortaste vägen därimellan.  */

            if (bfs.hasPathTo(sb)) {
                System.out.println(a + " till " + b + ":");

                for (int x : bfs.pathTo(sb)) {

                    if (x == sa)
                        System.out.print(a);
                    else {

                        while (true) {
                            if (graph.get(nameOf) == x) {
                                System.out.print("-" + nameOf);
                                break;
                            }
                            nameOf = (String) itr.next();
                        }
                        nameOf = graph.min();
                        itr = graph.iterator();
                    }

                }
                System.out.println();
            }
            else
                System.out.println("Finns ingen väg mellan : " + a + ", " + b);

        }

}
