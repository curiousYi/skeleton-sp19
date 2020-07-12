/** Performs some basic linked list tests. */
public class ArrayDequeTest {

	/*
		(arr, 10)
		10^10 1's will be added
	 */
	public static long addFirstTest(ArrayDeque arr, double power) {
		double count = Math.pow(10, power);
		long startTime = System.currentTimeMillis();

		for(int i=1; i <= count; i++) {
			arr.addFirst(1);
		}

		long endTime = System.currentTimeMillis();

		return endTime - startTime;
	}

	public static void checkInitialSizeTest() {
		ArrayDeque<String> arr = new ArrayDeque();

		boolean passed = arr.size() == 8;
		System.out.println("Initial Size is 8 Test:");
		printTestStatus(passed);
	}

	//TO-DO for addLast but it's basically the same

	public static void checkProportionalAddFirstTest() {
		//check whether the array is proportional to the actual number of items
		ArrayDeque<String> arr = new ArrayDeque();

		//could make this math.rand but it might be less robust
		double count = Math.pow(10, 2);

		int initialSize = arr.size();

		for(int i=1; i <= count; i++) {
			arr.addFirst("foo");
		}

		int sizeAfterAddition = arr.size();
		boolean passed;

		if(initialSize != 0 && sizeAfterAddition != 0 && initialSize != sizeAfterAddition && (sizeAfterAddition % initialSize == 0)) {
			passed = true;
		} else {
			passed = false;
		}

		//TO-DO replicate removeFirst and notice the symmetry

		System.out.println("Proportional Sizing Test:");
		printTestStatus(passed);
	}

	//check starting size of array is 8

	//Check whether it does methods in O(n) time
		//add

	//Optional
		//get
		//remove
		//size
	//Verify for something else
	/* Prints a nice message based on whether a test passed.
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
//	public static void addIsEmptySizeTest() {
//		System.out.println("Running add/isEmpty/Size test.");
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//
//		LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
//
//		boolean passed = checkEmpty(true, lld1.isEmpty());
//
//		lld1.addFirst("front");
//
//		// The && operator is the same as "and" in Python.
//		// It's a binary operator that returns true if both arguments true, and false otherwise.
//		passed = checkSize(1, lld1.size()) && passed;
//		passed = checkEmpty(false, lld1.isEmpty()) && passed;
//
//		lld1.addLast("middle");
//		passed = checkSize(2, lld1.size()) && passed;
//
//		lld1.addLast("back");
//		passed = checkSize(3, lld1.size()) && passed;
//
//		System.out.println("Printing out deque: ");
//		lld1.printDeque();
//
//		printTestStatus(passed);
//
//	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
//	public static void addRemoveTest() {
//
//		System.out.println("Running add/remove test.");
//
//		System.out.println("Make sure to uncomment the lines below (and delete this print statement).");
//		LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
//		// should be empty
//		boolean passed = checkEmpty(true, lld1.isEmpty());
//
//		lld1.addFirst(10);
//		// should not be empty
//		passed = checkEmpty(false, lld1.isEmpty()) && passed;
//
//		lld1.removeFirst();
//		// should be empty
//		passed = checkEmpty(true, lld1.isEmpty()) && passed;
//
//		printTestStatus(passed);
//	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		checkInitialSizeTest();
		checkProportionalAddFirstTest();
	}
} 