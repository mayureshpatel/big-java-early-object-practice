package exercise_06;

import java.util.NoSuchElementException;

import exercise_01.MyListIterator;

/**
 * Add an instance varible currentSize to our implementation of the LinkedList class.
 * Modify the add, addLast, and remove methods of both the linked list and the list
 * iterator to update the currentSize variable so that it contains the correct size.
 * Change the size method of Exercise 4 so that it simply returns the value of this
 * instance variable.
 * 
 * @author Mayuresh
 *
 */
public class MyLinkedList
{
	// Instance Variables
	private Node first;
	private int currentSize;
	
	// Constructors
	/**
	 * Constructs an empty linked list
	 */
	public MyLinkedList()
	{
		this.currentSize = 0;
		this.first = null;
	}
	
	// Methods
	/**
	 * Returns the first element in the linked list
	 * @return the first element in the linked list
	 */
	public Object getFirst()
	{
		if(this.first == null)
		{
			throw new NoSuchElementException();
		}
		
		return this.first.data;
	}
	
	/**
	 * Removes the first element in the linked list
	 * @return the removed element
	 */
	public Object removeFirst()
	{
		if(this.first == null)
		{
			throw new NoSuchElementException();
		}
		
		Object element = this.first.data;
		this.first = first.next;
		
		this.currentSize--;
		
		return element;
	}
	
	/**
	 * Adds an element to the front of the linked list
	 * @param element the element to add
	 */
	public void addFirst(Object element)
	{
		Node newNode = new Node();
		
		newNode.data = element;
		newNode.next = first;
		this.first = newNode;
		
		this.currentSize++;
	}
	
	/**
	 * Returns an iterator for iterating through this list
	 * @return an iterator for iterating through this list
	 */
	public MyListIterator listIterator()
	{
		return new MyLinkedListIterator();
	}
	
	/**
	 * Returns the string representation of this linked list
	 */
	public String toString()
	{
		String s = "";
		
		MyListIterator iter = this.listIterator();
		
		while(iter.hasNext())
		{
			Object obj = iter.next();
			s = s + "[" + obj + "]";
		}
		
		return this.getClass().getName() + ": " + s;
	}
	
	/**
	 * Gets the size of the linked list
	 * @return the size of the linked list
	 */
	public int size()
	{
		return this.currentSize;
	}
	
	// Inner Classes
	/**
	 * This class contains the data that is in 
	 * @author Mayuresh
	 *
	 */
	class Node
	{
		// Instance Variables
		public Object data;
		public Node next;
	}
	
	/**
	 * Iterator class that is used to traverse the linked list
	 * @author Mayuresh
	 *
	 */
	class MyLinkedListIterator implements MyListIterator
	{
		// Instance Variables
		private Node position;
		private Node previous;
		private boolean isAfterNext;
		
		// Constructors
		/**
		 * Constructs an iterator that points to the front
		 */
		public MyLinkedListIterator()
		{
			this.position = null;
			this.previous = null;
			this.isAfterNext = false;
		}
		
		// Methods
		/**
		 * Moves the iterator past the next element
		 * @return the traversed element
		 */
		public Object next()
		{
			if(!hasNext())
			{
				throw new NoSuchElementException();
			}
			this.previous = this.position;
			this.isAfterNext = true;
			
			if(this.position == null)
			{
				this.position = first;
			}
			else
			{
				this.position = this.position.next;
			}
			
			return this.position.data;
		}
		
		/**
		 * Tests if there is an element after the iterator position.
		 * @return true if there is an element after the iterator position
		 */
		public boolean hasNext()
		{
			if(this.position == null)
			{
				return first != null;
			}
			else
			{
				return this.position.next != null;
			}
		}
		
		/**
		 * Adds an element before the iterator position and moves the iterator past the inserted element
		 * @param element the element to add
		 */
		public void add(Object element)
		{
			if(this.position == null)
			{
				addFirst(element);
				position = first;
			}
			else
			{
				Node newNode = new Node();
				
				newNode.data = element;
				newNode.next = this.position.next;
				this.position.next = newNode;
				this.position = newNode;
			}
			
			currentSize++;
			this.isAfterNext = false;
		}
		
		/**
		 * Removes the last traversed element. This method may only be called after a call to the next method
		 */
		public void remove()
		{
			if(!this.isAfterNext)
			{
				throw new NoSuchElementException();
			}
			
			if(this.position == first)
			{
				removeFirst();
			}
			else
			{
				this.previous.next = this.position.next;
			}
			
			this.position = this.previous;
			this.isAfterNext = false;
			
			currentSize--;
		}
		
		/**
		 * Sets the last traversed element to a different value.
		 * @param element the element to set
		 */
		public void set(Object element)
		{
			if(!this.isAfterNext)
			{
				throw new NoSuchElementException();
			}
			
			this.position.data = element;
		}
	}
}
