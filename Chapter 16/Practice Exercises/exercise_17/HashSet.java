package exercise_17;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The hasNext method of the hash set implementation in section 16.4 finds the location of the next element, but when next
 * is called, the same search happens again. Improve the efficiency of these methods so that the next  (or a repeated call
 * to hasNext) uses the position located by a preceding call to hasNext.
 * 
 * @author Mayuresh
 *
 */
public class HashSet
{
	// Instance Variables
	private Node[] buckets;
	private int currentSize;
	
	// Constructors
	/**
	 * Constructs a hash table
	 * @param bucketsLength the length of the buckets array
	 */
	public HashSet(int bucketsLength)
	{
		buckets = new Node[bucketsLength];
		currentSize = 0;
	}
	
	// Methods
	/**
	 * Gets the processed hash code of a given object, which indicates the index
	 * of the array at which it is located
	 * @param x the object to get the hash code of
	 * @return the processed hashcode
	 */
	private int getProcessedHashCode(Object x)
	{
		int h = x.hashCode();
		
		if(h < 0)
		{
			h = -h;
		}
		
		return h % buckets.length;
	}
	
	/**
	 * Tests for set membership
	 * @param x an object
	 * @return true if x is an element of this set
	 */
	public boolean contains(Object x)
	{
		int h = getProcessedHashCode(x);
		Node current = buckets[h];		// Goes to the bucket in which this element should be located in
		
		// While loop loops through all of the links in this bucket
		while(current != null)
		{
			if(current.data.equals(x))
			{
				return true;
			}
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Adds an element to this set
	 * @param x an object
	 * @return true if x is a new object, false if x was already in the set
	 */
	public boolean add(Object x)
	{
		int h = getProcessedHashCode(x);		// Process the index at which this element should be added to
		Node current = buckets[h];				// Set the current node to the node located in the correct array location
		
		// Loop through the array making sure that the element is not already in it
		while(current != null)
		{
			if(current.data.equals(x))
			{
				return false;		// Already in the set
			}
			current = current.next;
		}
		
		// addFirst method of the linked list; adds the new element to the beginning
		Node newNode = new Node();
		newNode.data = x;
		newNode.next = buckets[h];
		buckets[h] = newNode;
		
		currentSize++;
		
		return true;
	}
	
	/**
	 * Removes an object from this set
	 * @param x an object
	 * @return true if x was removed from this set, false if x was not an element of this set
	 */
	public boolean remove(Object x)
	{
		int h = getProcessedHashCode(x);
		Node current = buckets[h];
		Node previous = null;
		
		// Loop through the linked list of the correct location
		while(current != null)
		{
			// If the element is located
			if(current.data.equals(x))
			{
				if(previous == null)
				{
					buckets[h] = current.next;
				}
				else
				{
					previous.next = current.next;
				}
				
				currentSize--;
				return true;
			}
			
			// Otherwise move on
			previous = current;
			current = current.next;
		}
		return false;
	}
	
	/**
	 * Returns an iterator that traverses the element of this set.
	 * @return a hash set iterator
	 */
	public Iterator iterator()
	{
		return new HashSetIterator();
	}
	
	/**
	 * Gets the number of elements in this set
	 * @return the number of elements
	 */
	public int size()
	{
		return this.currentSize;
	}
	
	/**
	 * Gets the string representation of this hash set
	 */
	public String toString()
	{
		String s = "";
		
		// Outer Loop; Go through all the buckets
		OuterLoop: for(int i = 0; i < this.buckets.length; i++)
		{
			s += "[";
			Node currentNode = this.buckets[i];
			
			// Go through each node until the end
			if(currentNode == null)
			{
				s += "], ";
				continue OuterLoop;
			}
			else
			{
				while(currentNode != null)
				{
					// Print data
					if(currentNode.next == null)
					{
						s += currentNode.data;
					}
					else
					{
						s += currentNode.data + ", ";
					}
					
					currentNode = currentNode.next;
				}
				
				s += "]";
			}
			s += ", ";
		}
		return this.getClass().getName() + "[" + s.substring(0, s.length() - 2) + "]";
	}
	
	// Inner Classes
	class Node
	{
		// Instance Variables
		public Object data;
		public Node next;
		
		// Constructors
		
		// Methods
	}
	
	class HashSetIterator implements Iterator
	{
		// Instance Variables
		private int bucketIndex;
		private Node current;
		
		// Constructors
		/**
		 * Constructs a hash set iterator that points to the first element of the hash set
		 */
		public HashSetIterator()
		{
			current = null;
			bucketIndex = -1;
		}
		
		// Methods
		/**
		 * Checks if there is another element in the hash set
		 * @return true if there is another element after the current one
		 */
		public boolean hasNext()
		{
			if(current != null && current.next != null)
			{
				return true;
			}
			
			// This is where the search happens
			for(int b = bucketIndex + 1; b < buckets.length; b++)
			{
				if(buckets[b] != null)
				{
					bucketIndex = b;			// Remember where the next element is located
					return true;
				}
			}
			return false;
		}
		
		/**
		 * Return the next element in the hash set
		 */
		public Object next()
		{
			if(current != null && current.next != null)
			{
				current = current.next;
			}
			else
			{
				current = buckets[bucketIndex];
			}
			return current.data;
		}
		
		/**
		 * Not supporting remove function as of now
		 */
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
}
