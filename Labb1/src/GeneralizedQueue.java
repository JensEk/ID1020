public class GeneralizedQueue<Item> {

    // Deklarerar en generisk first pekare och en int för stacksize.
    private Node<Item> first;
    private int sizeofstack;


    // Generiska Node klassen
    private class Node<Item> {

        private Item item;
        private Node<Item> next;

    }


    public boolean isEmpty() {

        return first == null;
    }


    /* Metod för att fyll på enligt LIFO med generisk parameter där om stacken är tom så initieras en ny node som first pekar mot och sedan sätts first->next mot null
       Om stacken inte är tom så skapas newNode som pekar mot first noden och sedan sätts first att peka mot den nya noden istället */
    public void enqueue (Item item) {
        if (isEmpty()) {

            first = new Node();
            first.item = item;
            first.next = null;


            sizeofstack++;
        }
        else {

            Node<Item> newNode= new Node();
            newNode.item = item;
            newNode.next = first;
            first = newNode;


            sizeofstack++;

        }
    }




    /* Metoden tar emot en int med den index plats som ska returneras och tas bort ur stacken, där first = index 1
       Skapad på 3 fall beroende på storlek av stack där första index platsen startar på 1.
       (K = 1) Om det bara finns en node så sätts first att peka mot null annars sätts first att peka mot det näst senaste noden.
       Vid övriga index så skapas en kNode som pekar mot first i stacken och sedan itereras kNode vidare i stacken tills pekar till noden före vårt valda index.
       Där säts kNode->next att peka på vår indexplats->next och sedan tar garbage collectorn hand om beröd node. */

    public void dequeue(int k) {

        if(isEmpty())
            System.out.println("Tom lista");

        if (k == 1) {

            if(first.next == null)
                first = null;
            else
                first = first.next;
            sizeofstack--;
        }


        int j = 2;
        int i = 0;
        Node<Item> kNode = first;
        while( j < k) {

            kNode = kNode.next;
            j++;
        }
        if ( j == k ) {
            kNode.next = kNode.next.next;
            sizeofstack--;
        }



    }

    // Metod för att skapa och returnera en string med de noden som finns kvar på stacken där en Node kopia görs på first noden och därifrån itererar vidare tills den når null som next.
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
