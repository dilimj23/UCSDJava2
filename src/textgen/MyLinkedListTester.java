/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		assertEquals("Remove: now the 21 will have prev node head ", list1.head, list1.head.next.prev);
		try {
			list1.remove(-1);
			fail("Out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			list1.remove(list1.size());
			fail("Out of bounds!");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// TODO: Add more tests here
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
        // TODO: implement this test
		// test with longer list
		boolean added = longerList.add(11);
		assertEquals("Add to end: check if returned true ", true, added);
		assertEquals("Add to end: check new element is at the end ", (Integer)11, longerList.get(LONG_LIST_LENGTH));
		assertEquals("Add to end: check the size is correct ", 11, longerList.size());
		try {
			boolean added2 = longerList.add(null);
			fail("Null cannot be inserted!");
		}
		catch (NullPointerException e) {
			
		}
		
		// test with empty list
		boolean added3 = emptyList.add(1);
		assertEquals("Add to end: check add to empty list ", true, added3);
		assertEquals("Add to end: check the new element is at the end ", (Integer)1, emptyList.get(0));
		assertEquals("Add to end: check the size after add to empty list ", 1, emptyList.size());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		// TODO: implement this test
		assertEquals("Size of empty list ", 0, emptyList.size());
		assertEquals("Size of short list ", 2, shortList.size());
		assertEquals("Size of longer list ", 10, longerList.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        // TODO: implement this test
		// Test add to short list, first out of bounds then contents
		try {
			shortList.add(-1, "C");
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		try {
			shortList.add(3, "C");
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		shortList.add(0, "C");
		assertEquals("Add at Index: add to the head ", "C", shortList.get(0));
		assertEquals("Add at Index: add to the head then the old head is at 1 ", "A", shortList.get(1));
		assertEquals("Add at Index: size should change ", 3, shortList.size());
		shortList.add(1, "D");
		assertEquals("Add at Index: add to middle ", "D", shortList.get(1));
		assertEquals("Add at Index: add to middle then previous no change ", "C", shortList.get(0));
		assertEquals("Add at Index: add to middle then next should move ", "A", shortList.get(2));
		assertEquals("Add at Index: size should change ", 4, shortList.size());
		shortList.add(4, "E");
		assertEquals("Add at Index: add to end ", "E", shortList.get(4));
		assertEquals("Add at Index: add to end then previous no change ", "B", shortList.get(3));
		assertEquals("Add at Index: size should change ", 5, shortList.size());
		
		// Test add to empty test
		try {
			emptyList.add(-1, 1);
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		try {
			emptyList.add(1, 1);
			fail("Check out of bound");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		emptyList.add(0, 1);
		assertEquals("Add at Index: add to empty list ", (Integer)1, emptyList.get(0));
		assertEquals("Add at Index: size ", 1, emptyList.size());
		
		
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
	    // TODO: implement this test
		// Test empty list
		try {
			emptyList.set(0, 1);
			fail("Check out of bound!");
		} catch (IndexOutOfBoundsException e) {
			
		}
		
		// Test shortList
		try {
			shortList.set(0, null);
			fail("Cannot set null value");
		}
		catch (NullPointerException e) {
			
		}
		
		shortList.set(0, "C");
	    assertEquals("Set at first place ", "C", shortList.get(0));
	    assertEquals("Set then the next should be same ", "B", shortList.get(1));
	    assertEquals("Check size for set ", 2, shortList.size());
	}
	
	
	// TODO: Optionally add more test methods.
	
}
