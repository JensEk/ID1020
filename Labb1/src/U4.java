import java.util.Scanner;

public class U4 {

    public static void main(String[] args) {

        // testprogram för att kunna inserta/remova i båda ändar av stacken och även en iterator.

        Scanner scan = new Scanner(System.in);
        Scanner scan2 = new Scanner(System.in);

        CircleLinked stack = new CircleLinked();

        System.out.println("Välj (1)InsertFront  (2)InsertBack  (3)RemoveFront (4)RemoveBack  (5)Iterera  (6)EXIT ");
        int val = scan.nextInt();
        System.out.println();


        while (val != 6) {

            switch (val) {

                case 1:
                    System.out.println("InsertFront: ");
                    String textF = scan2.nextLine();
                    stack.insertFront(textF);
                    System.out.println("List is: " + stack.toString());
                    break;

                case 2:
                    System.out.println("InsertBack: ");
                    String textB = scan2.nextLine();
                    stack.insertBack(textB);
                    System.out.println("List is: " + stack.toString());
                    break;


                case 3:
                    System.out.println("Plockades bort: " + stack.removeFront());
                    System.out.println("List is: " + stack.toString());
                    break;


                case 4:
                    System.out.println("Plockades bort: " + stack.removeBack());
                    System.out.println("List is: " + stack.toString());
                    break;

                case 5:
                    DoubleLinkIterator itr = stack.DoubleLinkIterator();
                    System.out.println("Tryck 1 för next och 2 för EXIT");
                    int i = scan.nextInt();
                    while (i == 1) {
                        itr.next();
                        i = scan.nextInt();
                    }
                    break;
            }
            val = scan.nextInt();
        }
    }
}