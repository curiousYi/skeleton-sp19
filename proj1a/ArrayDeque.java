public class ArrayDeque<Item> {
    private Item[] store;
    private int cutOff; //when to double
    private int multiple = 2; //how much we expand stuff by
    private int lastIndex;
    private int frontIndex;
    private int storeSize;

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
        this.store =  (Item[]) new Object[8];

        //should always be pointing at an empty slot the index
        this.lastIndex = 3;
        this.frontIndex = 3; //we are putting it in the middle
        this.cutOff = 3 * store.length / 4;
    }


    private Item[] copyArrayOver(Item[] oldArray, Item[] newArray) {
        //Note frontIndex and lastIndex will always be empty based on doubling and not doubling!
        if(frontIndex <= lastIndex) {
            for (int i = frontIndex + 1; i < lastIndex; i++) {
                newArray[i] = oldArray[i];
            }
        } else {
            //mapping everything to the middle
            int newStart = newArray.length / 4;
            int newEnd = newArray.length * 3 / 4;

            //checking to see whether the front overloaded and it leaked out on to the tail end
            for(int i = frontIndex; i < oldArray.length; i++) {
                newArray[newStart] = oldArray[i];
                newStart++;
            }

            for(int i = 0; i < lastIndex; i++) {
                newArray[newStart] = oldArray[i];
                newStart++;
            }
        }

        return newArray;
        //following scenario
        //normal frontIndex <= lastIndex
        // lastIndex > frontIndex
    }

    private Item[] resize(boolean isDoubling) {
        Item[] newStore;

        if(isDoubling) {
            newStore = (Item[]) new Object[store.length * multiple];
        } else {
            newStore = (Item[]) new Object[store.length / multiple];
        }

       return copyArrayOver(store, newStore);
    }

    public Item get (int index) {
        //first we need to check whether the index is even valid
        return store[index];
    }

    public int size () {
        return this.store.length;
    }

    public void runResizeLogic() {
        if(this.size() != 8 && this.storeSize <= 1/4 * this.size()) {
            this.store = resize(false);
        } else if (this.storeSize >= 3/4 * this.size()) {
            this.store = resize(true);
        }
    }

    //key observation we just need to maintain the order don't worry about other crap
    public void addFirst(Item thing) {
        this.store[frontIndex] = thing;
        this.storeSize++;


        //figure out location of frontIndex
        if(frontIndex == 0) {
            this.runResizeLogic();
            frontIndex = this.size() - 1;
        } else {
            frontIndex--;
        }
    }

    public void addLast(Item thing){
        this.store[lastIndex] = thing;
        this.storeSize++;

        //figure out location of frontIndex
        if(lastIndex == this.size()-1) {
            this.runResizeLogic();
            lastIndex = 0;
        } else {
            lastIndex++;
        }
    }

    public boolean isEmpty() {
        return frontIndex == lastIndex;
    }

    public void printDeque() {
        String thing = "";
//        Node<Item> tracker = sentinel.next;
//
//        for(int i =0; i < size; i++) {
//            thing += tracker.item.toString() + " ";
//            tracker = tracker.next;
//        }

        System.out.println(thing);
    }

    //TO-DO
//    public Item removeFirst() {
//        if(sentinel.next == sentinel) {
//            return null;
//        }
//
//        Node<Item> tempNode = sentinel.next;
//
//        sentinel.next = sentinel.next.next;
//        sentinel.next.next.prev = sentinel;
//
//        tempNode.next = null;
//        tempNode.prev = null;
//        size = size - 1;
//
//        return tempNode.item;
//    }

    //TO-DO
//    public Item removeLast() {
//        if(last == sentinel) {
//            return null;
//        }
//
//        Node<Item> tempNode = last;
//
//        last = last.prev;
//        last.next = sentinel;
//        sentinel.prev = last;
//
//        tempNode.next = null;
//        tempNode.prev = null;
//        size = size - 1;
//
//        return tempNode.item;
//    }

    public static void main(String[] args) {
        System.out.println("Running tests.\n");
    }
}