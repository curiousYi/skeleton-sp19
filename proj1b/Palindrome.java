public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> foo = new LinkedListDeque<Character>();

        for(int i = 0; i < word.length(); i++) {
            foo.addLast(word.charAt(i));
        }
        return foo;
    }

    public String unDeque(Deque<Character> deque) {
        String actual = "";
        for (int i = 0; i < deque.size() ;i++) {
            actual += deque.removeFirst();
        }

        return actual;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> foo = wordToDeque(word);

        if(word.length() <= 1) {
            return true;
        }

        if(foo.removeFirst() != foo.removeLast()) {
            return false;
        }

        return isPalindrome(unDeque(foo));
    }
}