import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;
import java.util.LinkedList;


public class MyHashMap<K, V> implements Map61B<K, V> {
    int size = 0;
    HashSet<K> keys = new HashSet<K>();
    LinkedList<Entry>[] buckets;

    /** Returns the value corresponding to KEY or null if no such value exists. */
    public V get(K key) {
//        if (list == null) {
//            return null;
//        }
//        Entry lookup = list.get(key);
//        if (lookup == null) {
//            return null;
//        }
//        return lookup.val;
    }

    @Override
    public int size() {
       return size;
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
//        size = 0;
//        list = null;
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
            Entry prev;

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
