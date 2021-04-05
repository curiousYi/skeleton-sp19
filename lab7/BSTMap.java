import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/* The BST code is from Sedgewick */
public class BSTMap<K extends Comparable, V> implements Map61B<K, V> {

    //Note need a new Set;

    public void clear() {
        keySet = null;
        root = null;
    };

    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        if(keySet == null) {
            return false;
        }
        return keySet.contains(key);
    };

    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        if(root == null) {
            return null;
        }

        return root.get(key);
    }

    private Node root;
    private Set<K> keySet;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int size;

        public Node(K key, V val, int size){
            this.key = key;
            this.val = val;
            this.size = size;
        }

        public V get(K otherKey) {
            if(key.equals(otherKey)) {
                return val;
            }

            if(key.compareTo(otherKey) > 0 && left != null ) {
                return left.get(otherKey);
            }
            if(key.compareTo(otherKey) < 0 && right != null ) {
                return right.get(otherKey);
            }

            return null;

        }
    }

    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.size;
    }

    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        if(root == null) {
            keySet = new HashSet<K>();
            root = new Node(key, value, 1);
        } else {
            insert(root, key, value);
        }
        keySet.add(key);
    }

    public Node insert(Node n, K key, V value) {
        if(n == null) {
            return new Node(key, value, 1);
        }

        if(n.key.compareTo(key) > 0 ) {
            n.left = insert(n.left, key, value);
        } else if (n.key.compareTo(key) < 0) {
            n.right = insert(n.right, key, value);
        }
        n.size = n.size + 1;
        return n;
    }

    @Override
    public Iterator<K> iterator() { return new BSTMapIter(); }

    private class BSTMapIter implements Iterator<K> {
        public BSTMapIter() { cur = root; }

        @Override
        public boolean hasNext() { return cur != null; }

        @Override
        public K next() {
            K ret = cur.key;
            cur = cur.right;
            return ret;
        }

        private Node cur;
    }

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
        throw new UnsupportedOperationException();
    }
}
