/*README

 * Sorting lab uppgift 5 (Del 1/2)
 * Implementering av insertionsort algoritm och jämföra sorteringar av stora mängder data med mergesort. Algoritmens tidskomplexitet är O(N^2).
   Main läser in antal N som ska sorteras och läser från System.in vilket har dirigerats till en lokal text fil vid tester.
 *
 * Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
 *
 * */


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class U5InsertionSort {


    /* Tar in en int array och kör två loopar. En yttre som startar på index 1 och itererar till sista indexplats och sedan en
    inre loop som jämför med de objekt som ligger på lägre index om de är mindre och byter isåfall plats. Vilket gör att allt till vänster om yttre loopens index plats är sorterat.  */

    public static void sort (int[] arr) {


        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--) {
                swap (arr, j, (j-1));
            }
        }
    }
    // Insertionsort för mergesort där funktionen tar emot två index lo och hi och kör insertionsort mellan de platser i arrayen.
    public static void sortMerge (int[] arr, int lo, int hi) {


        for (int i = lo +1; i <= hi; i++) {
            for (int j = i; j > lo && less(arr[j], arr[j-1]); j--) {
                swap (arr, j, (j-1));
            }

        }
    }


    public static boolean less (int a, int b)
    {
        return (a < b);
    }

    // Byter plats i arrayen genom att använda en temporär variabel.
    public static void swap (int[] array, int a, int b) {
        int temp = array [a];
        array[a] = array[b];
        array[b] = temp;
    }


    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }




    /* Läser in från en extern text fil med hjälp av BufferedReader som skapar ett objekt reader som
       sedan läser in datan från textfilen och sparar de i en intern buffer som läses från så länge det finns någon data.
       Med hjälp av att skapa objekt av System.nanoTime kan beräkningar göras på hur lång tid som krävs för just sort algoritmen att exekveras.*/
    public static void main(String[] args) throws IOException {


        int N = Integer.parseInt(args[0]);
        int[] a = new int [N];
        BufferedReader reader =new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < N; i ++) {
            a[i] = Integer.parseInt(reader.readLine());
        }


        long startTime = System.nanoTime();
        sort(a);
        long stopTime = System.nanoTime();
        System.out.println("Tid elapse: " + ((stopTime - startTime) * 0.000000001) + "s");

        // show(a);






    }
}
