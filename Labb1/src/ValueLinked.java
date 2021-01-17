public class ValueLinked {

    // Deklarering av noderna first och check och en int för sizeofstack
    private Node first;
    private Node check;
    private int sizeofstack;


    //  Node klassen
    private class Node {

        private int item;
        private Node next;

    }


    public boolean isEmpty() {

        return first == null;
    }

    /* Metoden tar emot en int och ska pusha den på stacken efter storleksordning. Två stycken node referenser används för att peka på first som pekar på det minsta och check som alltid börjar att peka på first men sedan
    itererar uppåt i stacken för att kontrollera mot det nya item som ska pushas på stacken.
     Om listan är tom så initieras en ny node som first pekar mot och dess next till null. Sedan sätts check noden att peka på first.
     Om det nya item är mindre än det som first ref till så sätts newNode->next till first och så refereras first och check till den nya noden.
     Om item skulle vara större än first så itererar check noden vidare i stacken tills den antingen når null eller att check->next pekar på en "större" node.
     Isåfall newNode->next till den större noden och den "gamla" näst största noden pekas mot newNode sedan avslutas med att check ref tillbaka mot first.*/

    public void enqueue(int item) {


        if (isEmpty()) {

            first = new Node();
            first.item = item;
            first.next = null;
            check = first;

            sizeofstack++;
            return;
        }

        Node newNode = new Node();
        newNode.item = item;

        if (item < first.item) {

            newNode.next = first;
            first = newNode;
            check = first;

            sizeofstack++;
            return;

        }
        else {

            while (check.next != null) {
                if (item < check.next.item){
                        break;
            }
                check = check.next;
            }
            newNode.next = check.next;
            check.next = newNode;
            check = first;

            sizeofstack++;
            return;
        }



        }
    /* Metoden tar emot en int med den index plats som ska returneras och tas bort ur stacken, där first = index 1
       Skapad på 3 fall beroende på storlek av stack där första index platsen startar på 1.
       (K = 1) Om det bara finns en node så sätts first att peka mot null annars sätts first att peka mot det näst senaste noden.
       Vid övriga index så skapas en kNode som pekar mot first i stacken och sedan itereras kNode vidare i stacken tills pekar till noden före vårt valda index.
       Där säts kNode->next att peka på vår indexplats->next och sedan tar garbage collectorn hand om beröd node. */

        public void remove (int k) {

            if (isEmpty())
                System.out.println("Tom lista");

            if (k == 1) {

                if (first.next == null)
                    first = null;
                else
                    first = first.next;
                sizeofstack--;
            }


            int j = 2;
            Node kNode = first;
            while (j < k) {

                kNode = kNode.next;
                j++;
            }
            if (j == k) {
                kNode.next = kNode.next.next;
                sizeofstack--;
            }


        }


    // Metod för att skapa och returnera en string med det som finns på stacken där en Node kopia görs på first noden och därifrån itererar vidare.
    public String toString() {

        StringBuilder str = new StringBuilder("[");

        if (isEmpty()) {
            return "Tom lista";
        }

        if (sizeofstack == 1)
            str.append(first.item + "]");

        else {

            Node index = first;

            while (index.next != null) {
                str.append(index.item + ",");
                index = index.next;
            }
            str.append(index.item + "]");


        }

        return str.toString();
    }

}




