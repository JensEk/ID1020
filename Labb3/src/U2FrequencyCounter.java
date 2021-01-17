/* README

Uppgift 2 Searching Lab
Main program som kallar på BinarySearchST.java och Binarysearchtree.java
Skapad av: Jens Ekenblad

 */

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

public class U2FrequencyCounter {



    public static void main(String[] args) throws FileNotFoundException
    {
        int minlen = 2;
        File theText = new File("C:\\Users\\JensE\\Programmering\\Labb3\\thetext.txt");
        Scanner reader = new Scanner(theText);
        Scanner reader2 = new Scanner(theText);
        Scanner input = new Scanner(System.in);
        System.out.println("How many words do you want to read? Enter now: ");
        int n = input.nextInt();

        /* Skapar ett objekt av binary search symbolTable klassen och sparar sedan den lästa key i en array med ett tillhörande value i en annan array som ökas med 1 varje gång samma key läses.
        * */
        BinarySearchST<String, Integer> symbolTable = new BinarySearchST<>(n);
        int symbolIterations = 0;

        long startTimeST = System.nanoTime();
        while(reader.hasNextLine() && symbolIterations < n)
        {
            String key = reader.next();
            if(key.length() < minlen)
                continue;

            if(!symbolTable.contains(key))
                symbolTable.put(key,1);
            else
                symbolTable.put(key,symbolTable.get(key) + 1);

            symbolIterations++;
        }
        long stopTimeST = System.nanoTime();

        // Iterator funktion för att hitta vilken key som har det finns mest av.
        Iterator symbolItr = symbolTable.iterator();

        String maxKey = symbolTable.min();
        int maxValue = symbolTable.get(maxKey);
        String current;
        for(int i = 0; i < n - 1; i++)
        {
            current = (String) symbolItr.next();
            if(maxValue < symbolTable.get(current))
            {
                maxKey = current;
                maxValue = symbolTable.get(current);
            }
        }






        /* Eftersom allt sparas i binära träd så skapas en första root bstMost med string 'O' för att få en någorlunda balanserad start på trädet.
        Sedan så görs en jämförelse och bstMost kopierar den key med högst värde, alltså det ordet som nämnts flest gånger */

        Binarysearchtree<String, Integer> bst = new Binarysearchtree<>();
        int bstIterations = 0;
        String bstMost = "O";
        bst.put(bstMost, 0);

        long startTimeBST = System.nanoTime();
        while(reader2.hasNextLine() && bstIterations < n)
        {
            String key = reader2.next();
            if(key.length() < minlen)
                continue;

            if(!bst.contains(key))
                bst.put(key,1);
            else
                bst.put(key,bst.get(key) + 1);

            if(bst.get(key) > bst.get(bstMost))
                bstMost = key;

            bstIterations++;
        }
        long stopTimeBST = System.nanoTime();



        System.out.println("Tid för ST: " + ((stopTimeST - startTimeST) * 0.000000001));
        System.out.println("Tid för BST: " + ((stopTimeBST - startTimeBST) * 0.000000001));

        System.out.println("Vanligaste ordet i ST : " + maxKey + " " + maxValue);
        System.out.println("Vanligaste ordet i BST : " + bstMost + " " + bst.get(bstMost));



    }
}