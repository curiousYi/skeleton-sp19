import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class MyTrieSet implements TrieSet61B {

    private Node root;
    private int keys;
    // R-way trie node
    private static class Node {
        private Object val;
        private Boolean terminated;
        private Map<String, Node> children = new HashMap<String, Node>();
        private List<String> wordsThatAreChildrenOfThisNode = new ArrayList<String>();
    }

    public MyTrieSet() {
        root = new Node();
    }
    public void clear() {
        root = new Node();
        keys = 0;
    }

    public void add(String str) {
        addRecursive(root, str, str);
    }

    //This is for recursing on add
    private void addRecursive(Node pointerNode, String str, String originalWord) {
        if(str.length() < 1) {
            keys++;
            return;
        }
        String firstLetter = Character.toString(str.charAt(0));

        Node currentNode = pointerNode.children.get(firstLetter);
        if(currentNode == null) {
            currentNode = new Node();
            currentNode.val = firstLetter;
            pointerNode.children.put(firstLetter, currentNode);
            currentNode.wordsThatAreChildrenOfThisNode.add(originalWord);
        } else if (currentNode.wordsThatAreChildrenOfThisNode.contains(originalWord) == false) {
            currentNode.wordsThatAreChildrenOfThisNode.add(originalWord);
        }

        if(str.length() == 1) {
            currentNode.terminated = true;
        }

        addRecursive(currentNode, str.substring(1, str.length()), originalWord);
    }

    public boolean contains(String str) {
        if(str.length() < 1) {
            return false;
        }

        return contains(root, str);
    }

    private boolean contains(Node n, String str) {
        String firstLetter = Character.toString(str.charAt(0));
        Node next = n.children.get(firstLetter);

        if(next == null) {
            return false;
        }

        if(str.length() == 1) {
            return true;
        }

        return contains(next, str.substring(1, str.length()));
    }



    public List<String> keysWithPrefix(String prefix) {
        return keysWithPrefix(root, prefix);
    }

    private List<String> keysWithPrefix(Node currentNode, String prefix) {
        if(prefix.length() < 1) {
            return currentNode.wordsThatAreChildrenOfThisNode;
        }
        String firstLetter = Character.toString(prefix.charAt(0));

        currentNode = currentNode.children.get(firstLetter);
        if(currentNode == null) {
            return null;
        }

        return keysWithPrefix(currentNode, prefix.substring(1, prefix.length()));
    }

    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
