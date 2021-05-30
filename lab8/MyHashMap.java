import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;


public class MyHashMap<K, V> implements Map61B<K, V> {
    int size = 0;
    int startingBucketSize = 16;
    HashSet<K> keys = new HashSet<K>();
    LinkedList<Entry>[] buckets;
    double loadFactor;

    //TO-DO create the additional constructors
    public MyHashMap() {
        this.loadFactor = 0.75;
        this.buckets = new LinkedList[startingBucketSize];
    }

    public MyHashMap(int initialSize) {
        this.startingBucketSize = initialSize;
        this.loadFactor = 0.75;
        this.buckets = new LinkedList[this.startingBucketSize];
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.startingBucketSize = initialSize;
        this.loadFactor = loadFactor;
        this.buckets = new LinkedList[this.startingBucketSize];
    };

    /** Returns the value corresponding to KEY or null if no such value exists. */
    public V get(K key) {
        if(keys.contains(key) == false) {
            return null;
        }
        int bucketPosition = calcBucketPos(key);
        Entry curr;
        LinkedList<Entry> ll = buckets[bucketPosition];
        curr = ll.get(0);

        //implicity expecting to always get an Entry
        for(int i = 1; i < ll.size(); i++){
            curr = ll.get(i);

            if(curr.key == key) {
                i = ll.size();
            }
        }
        return curr.val;
    }

    @Override
    public int size() {
       return size;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        keys = new HashSet<K>();
        size = 0;

        //TO-DO clear out the buckets janitor-style
        //clear out the buckets
//        size = 0;
//        list = null;
    }

    public void resizeBuckets() {
        LinkedList<Entry>[] oldBuckets = buckets;
        buckets = new LinkedList[buckets.length * 2];
        LinkedList<Entry> llRef;
        Entry ref;
        //map old buckets
        for(int i = 0; i < oldBuckets.length; i++) {
            llRef = oldBuckets[i];
            if(llRef != null) {
                for (int j = 0; j < llRef.size(); j++) {
                    ref = llRef.get(j);
                    put(ref.key, ref.val);
                }
            }
        }
    }

    public void checkAndPossiblyResizeBuckets() {
        if(size+1 >= loadFactor * this.buckets.length){
            resizeBuckets();
        }
    }
    /** determines bucket position **/
    public int calcBucketPos(K key) {
        return key.hashCode() % buckets.length;
    }
    /**
     * Inserts the key-value pair of KEY and VALUE into this dictionary,
     * replacing the previous value associated to KEY, if any.
     */
    public void put(K key, V val) {
        keys.add(key);

        //checkAndResizeBuckets
        checkAndPossiblyResizeBuckets();

        //determine bucket
        int bucketPosition = calcBucketPos(key);

        //check if bucket is empty if empty then do arrayList
        //if not empty put it at the end of the arrayList
        //increment size
        if(buckets[bucketPosition] == null) {
            buckets[bucketPosition] = new LinkedList();

            Entry entry = new Entry(key, val);

            buckets[bucketPosition].add(entry);
            size++;
        } else {
            Entry curr;

            LinkedList<Entry> ll = buckets[bucketPosition];

            for(int i = 0; i < ll.size(); i++){
                curr = ll.get(i);

                if(curr.key == key) {
                    curr.val = val;
                    i = ll.size() * 2;
                } else if ( i == ll.size() -1 ) {
                    ll.add(new Entry(key, val));
                    size++;
                }
            }
        }
    }

    private class Entry {
        K key;
        V val;

        /**
         * Stores KEY as the key in this key-value pair, VAL as the value, and
         * NEXT as the next node in the linked list.
         */
        Entry(K k, V v) {
            this.key = k;
            this.val = v;
        }
    }

    /**
     * Returns true if and only if this dictionary contains KEY as the
     * key of some key-value pair.
     */
    public boolean containsKey(K key) {
        return keys.contains(key);
    }

    @Override
    public Iterator<K> iterator() {
        return keys.iterator();
    }

    /**
     * Keys and values are stored in a linked list of Entry objects.
     * This variable stores the first pair in this linked list.
     */
    private Entry list;



    /** An iterator that iterates over the keys of the dictionary. */
//    private class HashMapIter implements Iterator<K> {
//
//        /**
//         * Create a new ULLMapIter by setting cur to the first node in the
//         * linked list that stores the key-value pairs.
//         */
//        HashMapIter() {
//                   cur = list;
//                              }
//
//        @Override
//        public boolean hasNext() {
//                               return cur != null;
//                                                  }
//
//        @Override
//        public K next() {
//            K ret = cur.key;
//            cur = cur.next;
//            return ret;
//        }
//
//
//        /** Stores the current key-value pair. */
//        private Entry cur;
//
//    }

    @Override
    public V remove(K key) {
                         throw new UnsupportedOperationException();
                                                                   }

    @Override
    public V remove(K key, V value) {
                                  throw new UnsupportedOperationException();
                                                                            }

    @Override
    public Set<K> keySet() {
        return keys;
    }
}
