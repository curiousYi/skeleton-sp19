public class ArrayDeque<Item> {
    private Item[] store;
    private int size;
    private int capacity;
    private int cutOff; //when to double
    private int multiple = 2; //how much we expand stuff by
    private int lastIndex;
    private int frontIndex;

    /*
        High-level how this array will work:
        -It will be circular. Pointers start out in the middle.
        -Once it reaches 3/4 capacity it will double.
        -Once it reaches 1/4 capacity it will reduce in size by half.
        -TO-DO
            -figure out if my totally arbitrary optimization makes sense.
     */

    public ArrayDeque() {
        //https://stackoverflow.com/questions/2927391/whats-the-reason-i-cant-create-generic-array-types-in-java
        Item[] store =  (Item[]) new Object[8];
        lastIndex = 3;
        frontIndex = 3; //we are putting it in the middle
        
    }

    public Item get (int count) {
        return store[0];
    }

    public int size () {
        return store.length;
    }

    public void addFirst(Item thing) {
        store[frontIndex] = thing;
        if(frontIndex == 0) {
            //check if we need to expand the array
            frontIndex =
        } else {
            frontIndex -= 1;
        }
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