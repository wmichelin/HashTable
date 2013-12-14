/** 
Walter Michelin
November 22nd 2013
Dr. DePasquale
Project 3
*/


/**
 * LinkedOrderedList represents a singly linked implementation of an 
 * ordered list.
 *
 * @author Java Foundations
 * @version 4.0
 */
 
package jsjf;
import edu.tcnj.csc230.*;
import jsjf.exceptions.*;


public class LinkedOrderedList<E> extends LinkedList<E> 
         implements OrderedListADT<E>
{
    /**
     * Creates an empty list.
     */
    public LinkedOrderedList()
    {
        super();
    }

    /**
     * Adds the specified element to this list at the location determined by
	 * the element's natural ordering. Throws a NonComparableElementException 
	 * if the element is not comparable.
     *
     * @param element the element to be added to this list
     * @throws NonComparableElementException if the element is not comparable
	 */
    public void add(E element)
    {
        if (!(element instanceof Comparable))
            throw new NonComparableElementException("LinkedOrderedList");
		
		Comparable<E> comparableElement = (Comparable<E>)element;

        LinearNode<E> current = head;
		LinearNode<E> previous = null;
        LinearNode<E> newNode  = new LinearNode<E>(element);
        boolean found = false;
      
        if (isEmpty())  // list is empty
        {
            head = newNode;
            tail = newNode;
        }
		else if (comparableElement.compareTo(head.getElement()) <= 0)  
			// element goes in front
		{
            newNode.setNext(head);
            head = newNode;
		}
		else if (comparableElement.compareTo(tail.getElement()) >= 0)  
			// element goes at tail
		{
            tail.setNext(newNode);
            tail = newNode;
		}
        else  // element goes in the middle
        {
            while ((comparableElement.compareTo(current.getElement()) > 0))
			{
				previous = current;
                current = current.getNext();
			}
         
            newNode.setNext(current);
            previous.setNext(newNode);
        }
      
        count++;
		modCount++;
    }
}
