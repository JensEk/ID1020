public class DoubleLink<Item> implements DoubleLinkIterable <Item>  {

    // Deklarerar generiska first och last Noder för kunna ha koll på båda ändarna av stacken.
    private Node<Item> first;
    private Node<Item> last;
    private int sizeofstack;


    // Generisk Node klass med pekar på både next och prev node för att göra en cirkulär lista och ha koll på noder på båda sidor.
    private class Node<Item> {

        private Item item;
        private Node<Item> next;
        private Node<Item> prev;

    }


    public boolean isEmpty() {

        return first == null;
    }

    /* Om listan är tom så initieras first till en ny Node och sätter next/prev pekare till sig själv och sätter last att ref till first.
     Om listan inte är tom så initieras last till en ny Node innehållande det nya item.
     Last->next sätts till gamla last och kopplas ihop prev med first.
     Sedan sätts first->next till nya last för att binda ihop.
     Genom last->next->prev så binds gamla last ihop med nya last.
     Listan är cirkulär så det är 2 vardera pekare som kopplas ihop för både first och last.
      */
    public void enqueue (Item item) {


        if (isEmpty()) {

            first = new Node();
            first.item = item;
            first.next = first;
            first.prev = first;
            last = first;

            sizeofstack++;
        }

        else {

            last = new Node();
            last.item = item;
            last.next = first.next;
            last.prev = first;
            first.next = last;
            last.next.prev = last;

            sizeofstack++;

        }
    }



    // Eftersom queue följer FIFO så returneras det item som first pekar mot och sedan flyttas first pekaren till first->prev vilket är näst längst fram och kopplas ihop med last.

    public Item dequeue() {


            Item item = first.item;
            first = first.prev;
            first.next = last;
            last.prev = first;

            sizeofstack--;
            if (sizeofstack == 0) {
                first = null;
                last = null;
            }


            return item;
    }


    // Metod för att skapa och returnera en string och lägga till de items som poppas från stacken där en Node kopia görs på last noden och därifrån itererar vidare
    public String toString() {

        StringBuilder str = new StringBuilder("[");

        if (isEmpty()) {
            return "Tom lista";
        }

        if (sizeofstack == 1)
            str.append(first.item + "]");

        else {

            Node index = last;

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


        // deklarerar en privat node som ska användas för itereringen och kopiera ref.
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

        // Itererar bakåt enligt FIFO kö för next och framåt för prev där den går till last. Med hjälp av den nya ref noden ItrNode som pekar till first.

        public Item next()
        {

            ItrNode = ItrNode.prev;
            Item item = ItrNode.item;
            System.out.println("Pekar på: " + item);
            return item;
        }

        public Item prev()
        {
            ItrNode = ItrNode.next;
            Item item = ItrNode.item;
            System.out.println(item);
            return item;
        }
    }





}
