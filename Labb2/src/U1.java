/*README

* Sorting lab uppgift 1
* Implementering av insertionsort algoritm som ska sortera integers i stigande ordning. Algoritmens tidskomplexitet i worst case är O(N^2).
*
* Skapad av: Jens Ekenblad med inspiration från https://algs4.cs.princeton.edu/code/
*
* */


import java.util.Scanner;
public class U1 {


        /* Tar in en int array och kör två loopar. En yttre som startar på index 1 och itererar till sista indexplats och sedan en inre loop som jämför med de objekt som ligger på lägre index om de är mindre och byter isåfall plats. */

        public static void sort (int[] arr) {


            for (int i = 1; i < arr.length; i++) {
                for (int j = i; j > 0 && less(arr[j], arr[j-1]); j--) {
                    swap (arr, j, (j-1));
                }
                show(arr);
            }
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
            sort(a);


        }

}
