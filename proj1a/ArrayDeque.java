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


    //TO-DO fix this copyOver
    private Item[] copyArrayOver(Item[] oldArray, Item[] newArray) {
        //Note frontIndex and lastIndex will always be empty based on doubling and not doubling!

        //Handling two scenarios
        //one copying over to a larger array which is the weird frontIndex > or < lastIndex logic

        //copying over a smaller array which is more straightforward since it won't step out of bounds based on our constraint

        if(newArray.length > oldArray.length) {
            int newMiddle = newArray.length / 2 - 1;
            int oldMiddle = oldArray.length / 2 - 1;
            if(this.frontIndex > this.lastIndex && (this.frontIndex > oldMiddle)) {
                int map;
                int lastNewArrayIndex = newMiddle;
                for (int i = oldMiddle; i >=0 ; i--) {
                    map = newMiddle - (oldMiddle - i);
                    newArray[map] = oldArray[i];
                }

                for(int j = oldArray.length - 1; j > this.frontIndex; j--) {
                    map = newMiddle - oldMiddle - 1 - (oldArray.length - 1 - j);
                    newArray[map] = oldArray[j];
                    lastNewArrayIndex = map;
                }

                if(lastNewArrayIndex == 0) {
                    this.frontIndex = newArray.length-1;
                } else {
                    this.frontIndex = lastNewArrayIndex -1;
                }

                //mapping the tail portion
                lastNewArrayIndex = newMiddle;
                for(int k = oldMiddle+1; k < this.lastIndex; k++) {
                    map = newMiddle + 1 - (k - (oldMiddle + 1));
                    newArray[map] = oldArray[k];
                    lastNewArrayIndex = map;
                }
                if(lastNewArrayIndex == newArray.length-1) {
                    this.lastIndex = 0;
                } else {
                    this.lastIndex = lastNewArrayIndex + 1;
                }
            } else if (this.frontIndex > this.lastIndex && (this.frontIndex < oldMiddle)) {
                //means the end spilled over

            } else {
                int map;
                int lastNewArrayIndex = newMiddle;
                for (int i = oldMiddle; i >= 0; i--) {
                    map = newMiddle - (i - oldMiddle);
                    newArray[map] = oldArray[i];
                    lastNewArrayIndex = map;
                }

                if(lastNewArrayIndex == 0) {
                    this.lastIndex = newArray.length - 1;
                } else {
                    this.lastIndex = lastNewArrayIndex - 1;
                }

                for(int i = oldMiddle+1; i <= this.lastIndex; i++) {
                    map = newMiddle + 1 + (i - oldMiddle + 1);
                    newArray[map] = oldArray[i];
                    lastNewArrayIndex = map;
                }

                if(lastNewArrayIndex == newArray.length-1) {
                    this.lastIndex = 0;
                } else {
                    this.lastIndex = lastNewArrayIndex + 1;
                }

            }
            return newArray;
        }



        if(this.frontIndex >= this.lastIndex) {
            if(newArray.length < oldArray.length) {
                System.out.println("Not expecting this newArray < oldArray");
            }
            if(oldArray.length < newArray.length) {


            } else {
                int oldArrayEndPoint = oldArray.length - 1;
                int newArrayEndPoint = newArray.length - 1;
                int countLength = oldArray.length - 1 - this.frontIndex;
                for(int i = 0 ; i <= countLength; i++) {
                    newArray[newArrayEndPoint - i] = oldArray[oldArrayEndPoint - i];
                }
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
        if(this.size() != 8 && this.storeSize < (this.size()/4)) {
            this.store = resize(false);
        } else if (this.storeSize > (this.size() * 3)/4) {
            this.store = resize(true);
        }
    }

    //key observation we just need to maintain the order don't worry about other crap
    public void addFirst(Item thing) {
        this.store[this.frontIndex] = thing;
        this.storeSize++;


        //figure out location of frontIndex
        if(this.frontIndex == 0) {
            this.frontIndex = this.size() - 1;
        } else {
            this.frontIndex--;
        }
        this.runResizeLogic();
    }

    //Untested
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
        return this.frontIndex == this.lastIndex;
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
    public Item removeFirst() {
        if(this.frontIndex == this.size() - 1) {
           this.frontIndex = 0;
        } else {
            this.frontIndex++;
        }
        Item thingToReturn = this.store[this.frontIndex];
        this.runResizeLogic();

        return thingToReturn;
    }

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