

import java.util.Scanner;

public class U2 {





    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        StackList stack = new StackList();

        // första delen är för iterativ version där en string returnas och printas med nuvarande storlek av stacken före och efter.

        System.out.println("Ange din text för Iterativ reverse: ");
        String text = scan.nextLine();

        for (int i = 0; i <= text.length() -1; i++)
            stack.push(text.charAt(i));

        System.out.println("Current stack size: " + stack.size() + '\n');
        System.out.println(revIter(stack));
        System.out.println("Current stack size: " + stack.size() + '\n');

        



        // Andra delen görs separat för en rekursiv version med en ny text inmatning som pushas och printas med nuvarande storlek av stacken före och efter.

        System.out.println("Ange din text för Rekursiv reverse: ");
        String text2 = scan.nextLine();

        for (int i = 0; i <= text2.length() -1; i++)
            stack.push(text2.charAt(i));
        System.out.println("Current stack size: " + stack.size() + '\n');
        revRec(stack);
        System.out.println("");
        System.out.println("Current stack size: " + stack.size());




    }
    // Iterativ metod som så länge som stacken inte är tom så returneras hela stacken med hjälp av en stringbuilder.
    public static String revIter (StackList stack) {

        StringBuilder string = new StringBuilder();

        while(!stack.isEmpty()) {
            string.append("[" + stack.pop() + "]" + ",");

        }
        return string.toString();
    }

    // Kollar boolean isEmpty om det finns något på stacken och returnerar senaste char på stacken och kallar sedan igen på funktionen.
    public static void revRec (StackList stack) {

        while (!stack.isEmpty()) {
            System.out.print(StringBuilder(stack.pop()));
            revRec(stack);
        }
    }

    // Separat stringbuilder för den rekursiva metoden för att få [x] och ,

    public static String StringBuilder (char c) {

        StringBuilder string = new StringBuilder();

        string.append("[" + c + "]" + ",");

        return string.toString();

    }

}




