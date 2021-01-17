public class CircleLinked<Item> implements DoubleLinkIterable <Item> {


    // Deklarerar en generisk first pekare och en int för stacksize.
    private Node<Item> first;
    private int sizeofstack;


    // Genreriska Node klassen
    private class Node<Item> {

        private Item item;
        private Node<Item> next;

    }


    public boolean isEmpty() {

        return first == null;
    }


    // Separatat metoder för att inserta först eller sist i listan där de tar emot en generisk parameter.

    /* Om stacken är tom så initieras en ny node med first pekar till den och sätter first->next till sig själv.
       Annars skapas en newNode med sin ->next till det som first pekar mot.
       Sedans sätts first->next att peka mot den nya noden och så sätts first att peka på newNode för att flytta fram first pekaren */

    public void insertFront (Item item) {
        if (isEmpty()) {

            first = new Node();
            first.item = item;
            first.next = first;

            sizeofstack++;
        }
        else {

            Node<Item> newNode = new Node();
            newNode.item = item;
            newNode.next = first.next;
            first.next = newNode;
            first = newNode;

            sizeofstack++;

        }
    }

    /* Om listan är tom så initieras en ny node och first att peka till den med dess next till sig själv.
       Annars för att sätta in där bak används att listan är cirkulär och sätter newNode-> att peka på det som first->next pekar på vilket är den sista noden i stacken.
        Sedan länkas first ihop med den sista noden genom att peka på newNode. */

    public void insertBack (Item item) {
        if (isEmpty()) {

            first = new Node();
            first.item = item;
            first.next = first;

            sizeofstack++;
        }
        else {

            Node<Item> newNode = new Node();
            newNode.item = item;
            newNode.next = first.next;
            first.next = newNode;

            sizeofstack++;


        }
    }




    /* Hämtar item från first pekaren och skapar sedan en newNode som sätts att ref till first->next vilket är den sista noden i stacken och sen itererar vidare genom stacken tills att newNode->next inte pekar till first noden.
       När väl newNode är på noden före first så sätts newNode->next att peka mot first->next vilket är den sista noden i stacken och så sätts first att ref till newNode ist vilket gör att garbage collectorn kmr ta hand om gamla first. */

    public Item removeFront() {


        Item item = first.item;
        Node <Item> newNode = first.next;
        while(newNode.next != first) {
            newNode = newNode.next;
        }
        newNode.next = first.next;
        first = newNode;
        sizeofstack--;
        return item;

    }

    // För att ta bort sist i stacken så ändras first.next att peka två noder bort vilket blir den näst sista noden i stacken och sedan tar garbage collectorn hand om den gamla sista noden.
    public Item removeBack() {


        Item item = first.next.item;
        first.next = first.next.next;
        sizeofstack--;
        return item;
    }

    // Metod för att skapa och returnera en string och lägga till de items som poppas från stacken där en Node kopia görs på first noden och därifrån itererar vidare
    public String toString() {

        StringBuilder str = new StringBuilder("[");

        if (isEmpty()) {
            return "Tom lista";
        }

        if (sizeofstack == 1)
            str.append(first.item + "]");

        else {

            Node index = first.next;

            while (index != first) {
                str.append(index.item + ",");
                index = index.next;
            }
            str.append(index.item + "]");


        }

        return str.toString();
    }


    // Nedanstående är kod för iteratorn


    // Skapar iterable metoden som returnerar ett Iterator objekt av vår generiska node lista.
    public DoubleLinkIterator<Item> DoubleLinkIterator()
    {
        return new DoubleIterator(first);
    }


    // Iterator klass med de metoder som tillhör iterator interfacet
    private class DoubleIterator implements DoubleLinkIterator<Item>
    {

        // deklarerar en privat node för att  kunna kopiera ref.
        private Node<Item> ItrNode;


        // Metod som sätter ref för den privata ItrNode variabeln och tar emot en generisk node som parameter.
        public DoubleIterator(Node<Item> first)
        {
            if(isEmpty()) {
                System.out.println("Empty stack");
                ItrNode = null;
            }


            ItrNode = first;
        }


        // Itererar framåt med hjälp av den nya ref noden ItrNode
        public Item next()
        {

            ItrNode = ItrNode.next;
            Item item = ItrNode.item;
            System.out.println("Pekar på: " + item);
            return item;
        }

        // prev används ej men måste deklareras pga interface

        public Item prev() {
            return null;
        }

    }







}

