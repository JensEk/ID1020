/*README

 * Sorting lab uppgift 6
 * Implementering av mergesort algoritm och jämföra sorteringar av stora mängder data med cutoffs där insertionsort kallas på istället för mindre arrayer.
   Main läser in antal N som ska sorteras och läser från System.in vilket har dirigerats till en lokal text fil vid tester.
 *
 * Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
 *
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class U6 {

    // Tar emot en array och skapar en kopia på den och kallar på funktionen sort med första och sista index.
    public static void copySort (int[] a) {

        int[] aux = new int [a.length];
        sort (a, aux, 0, a.length -1);

    }

    /* Tar emot två arrays med första och sista index platser och rekursivt kallar på sig själv tills den har brutit ner arrayen
      och dess index platser tills att hi och lo pekar på samma objekt och börjar då att kalla på merge. Villkor för cutoff är satt till differensen av index hi - lo för att utföra insertionsort. */

    private static void sort (int[] a, int[] aux, int lo, int hi) {

        if (hi <= lo)
            return;

       if (hi - lo <= 10) {
            U5InsertionSort.sortMerge(a, lo, hi);
            }

        else if (hi - lo > 10) {
        int mid = lo + (hi - lo) / 2;
        sort(a, aux, lo, mid);
        sort(a, aux, mid + 1, hi);
        merge(a, aux, lo, mid, hi); }

    }


    /* Tar emot två arrays, a som är den ursprungliga och aux som används för att kopiera och flytta element. Börjar med att kopiera över till aux för de index lo och hi vi skickar med in.
     * Med hjälp av att j sätts till mid +1 så får vi en pekar till vardera sub array som sedan ska jämföras med varandra och skrivs sedan in i a.
     * Med hjäp av de två första if statesment så fångas de element upp som blivit ersatta i a-arrayen och när ena subarrayen är färdig med merge.*/
    private static void merge (int[] a, int[] aux, int lo, int mid, int hi) {

        for (int k = lo; k <= hi; k++) {
            aux[k] = a[k];
        }

        int i = lo;
        int j = mid + 1;

        for (int k = lo; k <= hi; k++) {

            if (i > mid)
                a[k] = aux[j++];

            else if (j > hi)
                a[k] = aux[i++];

            else if (Less(aux[j], aux[i]))
                a[k] = aux[j++];

            else
                a[k] = aux[i++];

        }
    }

    private static boolean Less (int a, int b) {
        return a < b;
    }

    // Skriver ut varje objekt i arrayen.
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }
    }




    /* Läser in från en extern text fil med stora mängder tal med hjälp av BufferedReader som skapar ett objekt reader som
       sedan läser in datan från textfilen och sparar de i en intern buffer som läses från så länge det finns någon data.
       Med hjälp av att skapa objekt av System.nanoTime kan beräkningar göras på hur lång tid som krävs för just copySort algoritmen att exekveras. */

    public static void main(String[] args) throws IOException {
        int N = Integer.parseInt(args[0]);
        int[] a = new int [N];
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i ++) {
            a[i]  = Integer.parseInt(reader.readLine());
        }

        long startTime = System.nanoTime();
        U6.copySort(a);
        long stopTime = System.nanoTime();
        //show(a);
        System.out.println("Tid elapse: " + ((stopTime - startTime) * 0.000000001) + "s");
    }
}
