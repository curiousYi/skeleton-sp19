
public class LinkedListDeque<Item> {
    private Node<Item> sentinel;
    private int size;
    private Node<Item> last;

    public LinkedListDeque() {
        sentinel = new Node("asdfsdf");
        last = sentinel;
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        LinkedListDeque newLL = new LinkedListDeque();
    }

    public Item get (int count) {
        Node<Item> start = sentinel.next;
        while(count != 0) {
            start = start.next;
            count--;
        }
        return start.item;
    }

    public int size () {
        return size;
    }

    public class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> prev;

        public Node(Item i) {
            item = i;
        }

    }

    public void addFirst(Item thing) {
        Node<Item> tempNode = sentinel.next;
        Node<Item> newFirst = new Node(thing);

        newFirst.next = tempNode;
        newFirst.prev = sentinel;

        sentinel.next = newFirst;
        tempNode.prev = newFirst;

        if(last == tempNode) {
            last = newFirst;
        }

        size = size + 1;
    }

    public void addLast(Item thing){
        Node<Item> tempNode = last;
        Node<Item> newLast = new Node(thing);

        newLast.next = sentinel;
        newLast.prev = last;

        sentinel.prev = newLast;
        tempNode.next = newLast;

        last = newLast;
        size = size + 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        String thing = "";
        Node<Item> tracker = sentinel.next;

        for(int i =0; i < size; i++) {
            thing += tracker.item.toString() + " ";
            tracker = tracker.next;
        }

        System.out.println(thing);
    }

    public Item removeFirst() {
        if(sentinel.next == sentinel) {
            return null;
        }

        Node<Item> tempNode = sentinel.next;

        sentinel.next = sentinel.next.next;
        sentinel.next.next.prev = sentinel;

        tempNode.next = null;
        tempNode.prev = null;
        size = size - 1;

        return tempNode.item;
    }

    public Item removeLast() {
        if(last == sentinel) {
            return null;
        }

        Node<Item> tempNode = last;

        last = last.prev;
        last.next = sentinel;
        sentinel.prev = last;

        tempNode.next = null;
        tempNode.prev = null;
        size = size - 1;

        return tempNode.item;
    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
    }
}