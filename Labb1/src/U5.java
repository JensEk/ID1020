import java.util.Scanner;

public class U5 {

    public static void main(String[] args) {


        // Testprogram för att enqueue och ta bort vald indexplats där det som finns kvar på stacken printas ut.

        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        GeneralizedQueue stack = new GeneralizedQueue();

        System.out.println("Välj (1)Enqueue  (2)Dequeue kth  (3)EXIT ");
        int val = scan.nextInt();
        System.out.println();


        while (val != 3) {

            switch (val) {

                case 1:
                    System.out.println("Enqueue: ");
                    String text = scan2.nextLine();
                    stack.enqueue(text);
                    System.out.println("List is: " + stack.toString());
                    break;

                case 2:
                    System.out.println("Välj k: ");
                    int k = scan.nextInt();
                    stack.dequeue(k);
                    System.out.println("List is: " + stack.toString());
                    break;



            }
            val = scan.nextInt();
        }
    }
}
