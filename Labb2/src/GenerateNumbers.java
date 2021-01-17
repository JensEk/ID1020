/*README

 * RandomNumbers till uppgift 5 och 6.
 * Program för att generera N antal integers mellan valt min och max värde och spara de till en lokal text fil som sedan kan läsas in.
 *
 * Skapad av: Jens Ekenblad
 *
 * */

import java.io.*;
import java.util.Random;

public class GenerateNumbers {

    /* Printwriter gör det möjligt att skriva till en fil och så skickas argument in för antal element, minsta och största int genom terminalen och sedan så genereras Random classen slumpmässiga int där imellan. */
    public static void main(String[] args) throws IOException {

        Random rand = new Random();
        PrintWriter writer = new PrintWriter(new File("RandomNumbers.txt"));

        int min = Integer.parseInt(args[1]);
        int max = Integer.parseInt(args[2]);
        int N = Integer.parseInt(args[0]);
        for(int i = 0; i < N; i++) {
            writer.println(rand.nextInt(max - min) + min);
        }
        writer.flush();
        writer.close();





    }

}
