import java.util.Scanner;

public class U3 {

    public static void main(String[] args) {

        // Testprogram för att kunna enqueu/dequeue och itererar fram/bakåt där stacken printas efter varje förändring för kontroll.


        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);
        DoubleLink stack = new DoubleLink();

        System.out.println("Välj (1)Enqueue  (2)Dequeue  (3)Iterate (4) Exit ");
        int val = scan.nextInt();
        System.out.println();



         while (val != 4)   {

             switch (val) {

                 case 1:
                     System.out.println("Välj ditt item: ");
                     String text = scan2.nextLine();
                     stack.enqueue(text);
                     System.out.println("Queue is: " + stack.toString());
                     break;

                 case 2:
                     System.out.println("Plockades bort: " + stack.dequeue());
                     System.out.println("Queue is: " + stack.toString());
                     break;

                 case 3:

                     DoubleLinkIterator itr = stack.DoubleLinkIterator();
                     int j = 0;
                     System.out.println("Välj 1 för next och 2 för prev och 4 för EXIT");
                     j = scan.nextInt();
                     while (j == 1 || j == 2) {

                         if (j == 1)
                             itr.next();
                         else if (j == 2)
                             itr.prev();

                         j = scan.nextInt();

                     }
                     break;





             }

            val = scan.nextInt();
         }

    }
}







