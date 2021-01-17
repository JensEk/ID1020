import java.util.*;





public class E1 {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


        System.out.println("Enter a string of paranthesis: ");
        String str = scan.nextLine();

        if (checkBalance(str))
            System.out.println("Its balanced");
        else
            System.out.println("Not balanced");




    }



    // Metod för att returnera true/false beroende på en string är balancerad eller ej.
    public static boolean checkBalance (String str) {

        CheckBalance stack = new CheckBalance();

        // Kontroll för första tecken om det är closing eller längden bara är 1 och isåfall returnera false.

        if (str.length() <= 1 || str.charAt(0) == '}' || str.charAt(0) == ']' || str.charAt(0) == ')')
            return false;


        // Loop som pushar alla opening bracets och kontrollerar closing bracet mot senast på stacken för att se om de matchar annars returnera false. Skulle det finnas kvar något på stacken vilket indikerar oblanserad så returneras false på slutet.

        for (int i = 0; i < str.length(); i++) {

            if (str.charAt(i) == '{' || str.charAt(i) == '[' || str.charAt(i) == '(') {
                stack.push(str.charAt(i));
            }

            if (str.charAt(i) == '}' && stack.pop() != '{')
                return false;

            if (str.charAt(i) == ']' && stack.pop() != '[' )
                return false;

            if (str.charAt(i) == ')' && stack.pop() != '(' )
                return false;

        }
        if (stack.size() > 0)
            return false;
        else
            return true;
    }




}
