/*README
* Uppgift 3 Searching lab
* Main program för att kolla fördelningen av hashkoderna. Kallar på Binarysearchtree.java
* Skapad av: Jens Ekenblad  */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class U3 {

    public static void main(String[] args) throws FileNotFoundException {
        int minlen = 3;
        File theText = new File("C:\\Users\\JensE\\Programmering\\Labb3\\thetext.txt");
        Scanner reader = new Scanner(theText);
        Scanner reader2 = new Scanner(theText);
        Scanner reader3 = new Scanner(theText);
        Scanner input = new Scanner(System.in);
        System.out.println("How many words do you want to read? Enter now: ");
        int n = input.nextInt();

        System.out.println("How big hashtable? Enter a prime number: ");
        int nprime = input.nextInt();

        Binarysearchtree<Integer, String> bst = new Binarysearchtree<>();
        Binarysearchtree<String, Integer> bst2 = new Binarysearchtree<>();


        // Första 3 är för test av dubbla keys till samma hashcode.
        int bstIter = 0;
        int key;
        String word;

        // Övriga variabler är till för kontroll av fördelning i hash tabellen.
        int bstIter2 = 0;
        int keyhash;
        String word2;

        int index;
        int hash[] = new int[nprime];


        /* key sparar det lästa ordet med dess hashkod från java hashCode funktion, sedan görs en koll om nyckel redan finns annars så sparas den i en symbol table struktur.
        * Två olika scanners läser samtidigt och gör en jämförelse i slutet om olika ord är skrivna till samma hashkod och printar isåfall ut de båda två. */
        while(reader.hasNextLine() && bstIter < n)
        {
            key = reader.next().hashCode();
            word = reader2.next();

            if(word.length() < minlen)
                continue;


            if(!bst.contains(key)) {
                bst.put(key, word);
                continue;
            }


            if(!bst.get(key).equals(word)) {

                System.out.println("Dubbla hashkoder: ");
                System.out.println(word + " " + word.hashCode());
                System.out.println (bst.get(key) + " " + key);
                System.out.println();

            }


            bstIter++;
        }


        int distinktaord = 0;

        // Kontroll för hur jämn fördelning det är på hashen genom att bara spara in nya words och sedan håller hash arrayen koll på fördelning av varje hashkod.
        while(reader3.hasNextLine() && bstIter2 < n)
        {

            word2 = reader3.next();

           StringBuilder str = new StringBuilder("");
           for(int i = 0; i < word2.length(); i ++) {

               if(Character.isAlphabetic(word2.charAt(i)))
                   str.append(word2.charAt(i));
           }

           word2 = str.toString();
           str.delete(0, str.length());
           keyhash = word2.hashCode();

            if (word2.length() < minlen)
                continue;


            if (!bst2.contains(word2)) {
                bst2.put(word2, keyhash);
                index = (keyhash & 0x7fffffff) % nprime;
                hash[index] += 1;
                distinktaord += 1;
                continue;
            }

            bstIter2++;
        }


        for(int i = 0; i < hash.length; i ++) {
            System.out.println("Size of hash " + i + " is: " + hash[i]);
        }
        System.out.println("Antal distinkta ord i hashen: " + distinktaord);

    }
}

