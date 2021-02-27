public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> foo = new LinkedListDeque<Character>();

        for(int i = 0; i < word.length(); i++) {
            foo.addLast(word.charAt(i));
        }
        return foo;
    }
}