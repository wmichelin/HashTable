/** 
Walter Michelin
November 22nd 2013
Dr. DePasquale
Project 3
*/

/**
 * OrderedListADT defines the interface to an ordered list collection. Only
 * Comparable elements are stored, kept in the order determined by
 * the inherent relationship among the elements.
 *
 * @author Java Foundations
 * @version 4.0
 */
 
package jsjf;
import edu.tcnj.csc230.*;
import jsjf.exceptions.*;
 
public interface OrderedListADT<T> extends ListADT<T>
{
    /**
     * Adds the specified element to this list at the proper location
     *
     * @param element the element to be added to this list
     */
    public void add(T element);
}
