/*README
* Uppgift 4 Searching lab
* Main program för att hitta alla index för ett specifikt ord. Kallar på U4BinarySearchIndex.java
*
* Skapad av: Jens Ekenblad
*  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;



public class U4index {

    public static void main(String[] args) throws FileNotFoundException {

        File theText = new File("C:\\Users\\JensE\\Programmering\\Labb3\\thetext.txt");
        Scanner reader = new Scanner(theText).useDelimiter("");
        Scanner input = new Scanner(System.in);

        System.out.println("Which word to find? Enter now: ");
        String word = input.nextLine();

        U4BinarySearchIndex<String, Integer> symbolTable = new U4BinarySearchIndex<>(800000);


        String key = null;
        int charCount = 0;
        StringBuilder str = new StringBuilder();

        /* reader läser ett tecken åt gången och kollar mot java funktion isAlphabetic vilken typ av tecken det är och skriver isåfall det till en sträng om det är en bokstav
        * vid alla andra tecken så kollas det om det finns något skrivit i strängen och isåfall sparas den som key med tillhörande index plats som har uppdaterats för varje läst tecken.  */
        while (reader.hasNext()) {

            String read = reader.next();
            char ch = read.charAt(0);

            if (Character.isAlphabetic(ch))
                str.append(ch);

            else if (str.length() > 0) {
                key = str.toString().toLowerCase();
                symbolTable.put(key , (charCount - key.length()));
                str.delete(0, str.length());
            }

            charCount++;

        }

        System.out.println(Arrays.toString(symbolTable.get(word).toArray()));



        }
    }

