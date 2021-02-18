/*README

 * Sorting lab uppgift 3
 * Implementering av insertionsort algoritm som ska sortera integers i stigande ordning och med metoden inversionCount beräkna vilka element som behövs byta och var i arrayen de befinner sig.
   Tidskomplexitet för inversionCount är den samma som inversionsort algoritmen, worst case en komplexitet på O(N^2).
 *
 * Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
 *
 * */

import java.util.Scanner;

public class U3 {

    /* Tar in en int array och kör två loopar. En yttre som startar på index 1 och itererar till sista indexplats och sedan en inre loop som jämför med de objekt som ligger på lägre index om de är mindre och byter isåfall plats. Vilket gör att allt till vänster om yttre loopens index plats är sorterat.
     * Count räknar antal gånger som funktionen swap kallas på och ett byte sker. */

    public static void sort (int[] arr) {

        int count = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--) {
                swap (arr, j, (j-1));
                count++;
            }
            show(arr);
        }
        System.out.println("Antal swaps: " + count);
    }



    public static boolean less (int a, int b)
    {
        return (a < b);
    }

    // Byter plats i arrayen genom att använda en temporär variabel med det elementet som är mindre.
    public static void swap (int[] array, int a, int b) {
        int temp = array [a];
        array[a] = array[b];
        array[b] = temp;
    }

    // Printar ut hela arrayen
    private static void show(int[] a) {
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }

    /* Funktion för att jämföra alla objekt med varandra och se vilka som är mindre än valt index i yttre loopen och som behövs byta.  */
    public static void inversionCount (int[] arr) {
        StringBuilder str = new StringBuilder();
        int inv = 0;
        for(int i = 0; i < arr.length; i++) {
            for(int j = i+1; j < arr.length; j++) {
                if (!less(arr[i], arr[j])) {
                    str.append("[" + i + "," + arr[i] + "]" + "," + "[" + j + "," + arr[j] + "]" + "   ");
                    inv++;
                }
            }
        }
        System.out.println(str.toString());
        System.out.println("Antal inversions: " + inv);
    }


    public static void main(String[] args)  {


        System.out.println("Ange antal N att sortera:");
        Scanner scan = new Scanner (System.in);
        int N = scan.nextInt();
        int[] a = new int[N];

        System.out.println("Ange siffror: ");
        for (int i = 0; i < N; i ++) {
            a[i] = scan.nextInt();
        }
        System.out.print("Osorterad lista är: ");

        show(a);
        inversionCount(a);
        sort(a);


    }

}
