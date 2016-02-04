package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		size = 0;
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null); //use sentinel nodes
		head.next = tail;
		tail.prev = head;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		// TODO: Implement this method
//		if (element == null) {
//			return false;
//		}
//		LLNode<E> node = new LLNode<E>(element);
//		node.prev = tail.prev;
//		tail.prev.next = node;
//		tail.prev = node;
//		node.next = tail;
//		size++;
// 		return true;
		add(this.size(), element);
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		// TODO: Implement this method.
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException("Index out of bound!");
		}
		LLNode<E> node = head.next;
		int i = 0;
		while (i < index) {
			node = node.next;
			i++;
		}
		return node.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method
		if (index < 0 || index > this.size()) {
			throw new IndexOutOfBoundsException("Index out of bound!!");
		}
		if (element == null) { throw new NullPointerException("Cannot insert null value!"); }
		LLNode<E> node = head.next;
		int i = 0;
		while (i < index) {
			node = node.next;
			i++;
		}
		// then add the element prior to the node
		LLNode<E> newnode = new LLNode<E>(element);
		newnode.prev = node.prev;
		node.prev.next = newnode;
		node.prev = newnode;
		newnode.next = node;
		size++;
	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Index out of bound!!!");
		}
		int i = 0;
		LLNode<E> node = head.next;
		while (i < index) {
			node = node.next;
			i++;
		}
		E value = node.data;
		node.prev.next = node.next;
		node.next.prev = node.prev;
		node.next = null;
		node.prev = null;
		size--;
		return value;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if (index < 0 || index >= this.size()) {
			throw new IndexOutOfBoundsException("Out of bound!!!!");
		}
		if (element == null) {
			throw new NullPointerException("Cannot set data to null!");
		}
		int i = 0;
		LLNode<E> node = head.next;
		while (i < index) {
			node = node.next;
			i++;
		}
		E value = node.data;
		node.data = element;
		return value;
	}   
	
//	public static void main(String[] args) {
//		MyLinkedList<String> shortList = new MyLinkedList<String>();
//		shortList.add("A");
//		shortList.add("B");
//		System.out.println(shortList.get(1));
//	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
