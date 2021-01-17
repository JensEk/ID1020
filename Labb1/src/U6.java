import java.util.Scanner;

public class U6 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        ValueLinked stack = new ValueLinked();

        System.out.println("Välj (1)Enqueue  (2)Dequeue kth  (3)EXIT ");
        int val = scan.nextInt();
        System.out.println();


        while (val != 3) {

        switch (val) {

            case 1:
                System.out.println("Enqueue: ");
                int siffra = scan2.nextInt();
                stack.enqueue(siffra);
                System.out.println("List is: " + stack.toString());

                break;

            case 2:
                System.out.println("Välj k: ");
                int k = scan2.nextInt();
                stack.remove(k);
                System.out.println("List is: " + stack.toString());
                break;



        }
        val = scan.nextInt();
    }
}


}
