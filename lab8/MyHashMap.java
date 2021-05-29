import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

public class MyHashMap<K, V> implements Map61B<K, V> {
    int size = 0;
    HashSet<K> keys = new HashSet<K>();
    V[] buckets;

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

    /**
     * Inserts the key-value pair of KEY and VALUE into this dictionary,
     * replacing the previous value associated to KEY, if any.
     */
    public void put(K key, V val) {
        keys.add(key);

//        if (list != null) {
//            Entry lookup = list.get(key);
//            if (lookup == null) {
//                list = new Entry(key, val, list);
//                size = size + 1;
//            } else {
//                lookup.val = val;
//            }
//        } else {
//            list = new Entry(key, val, list);
//            size = size + 1;
//        }
    }

    /**
     * Returns true if and only if this dictionary contains KEY as the
     * key of some key-value pair.
     */
    public boolean containsKey(K key) {
//        if (list == null) {
//            return false;
//        }
//        return list.get(key) != null;
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
