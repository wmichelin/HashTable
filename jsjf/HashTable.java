/** 
Walter Michelin
November 22nd 2013
Dr. DePasquale
Project 3
*/

package jsjf;

import java.lang.*;
import java.util.*;
import edu.tcnj.csc230.*;
import jsjf.exceptions.*;

public class HashTable
{
	private int size = 0;
	private LinkedOrderedList<HashElement>[] myArray = new LinkedOrderedList[0];
	private ArrayList<HTTPLog> topTen = new ArrayList();
	private HTTPLog last = new HTTPLog();
		
	static class topTenComparator implements Comparator<HTTPLog>
	{
		public int compare(HTTPLog lhs, HTTPLog rhs)
		{
			return(rhs.getTimesAccessed() - lhs.getTimesAccessed());
		}
	}
	
	
	public HashTable(int a)
	{
		size = a;
		myArray = new LinkedOrderedList[size];
		for(int i = 0; i < size; i++)
		{
			myArray[i] = new LinkedOrderedList();
		}
	}
	
	public HTTPLog getLast()
	{
		return last;
	}
	
	public boolean contains(String key)
	{
		boolean returnVal = false;
		int index = hash(key);

		if(!myArray[index].isEmpty())
		{
			for(HashElement e: myArray[index])
			{
				if(key.equals(e.getKey()))
				{
					returnVal = true;
				}
			}
		}
		
		return returnVal;
	}
	
	
	public HTTPLog getLog(String key)
	{
		int index = hash(key);
		HTTPLog returnVal = new HTTPLog();
		
		if(!myArray[index].isEmpty())
		{
			for(HashElement e: myArray[index])
			{
				if(key.equals(e.getKey()))
				{
					returnVal = e.getLog();
				}
			}
		}
		
		if(returnVal.getName().equals(""))
			throw new ElementNotFoundException("no log present");	
		else
			return returnVal;
	}
	

	
	public String topTen()
	{
		String returnVal = "";
		int counter = topTen.size();
		Collections.sort(topTen, new topTenComparator());
		while(topTen.size() > 10)
		{
			topTen.remove(counter - 1);
			counter --;
		}
		for(HTTPLog e: topTen)
		{
			returnVal += e.getName() + " was accessed ";
			returnVal += e.getTimesAccessed() + " time(s)";
			returnVal += "\n";
		}
		return returnVal;
	}
	
	public void add(HTTPLog log)
	{
		HashElement tempElem = new HashElement(log.getName(), log);
		int index = hash(log.getName());
		if(!myArray[index].isEmpty())
		{
			if(!myArray[index].contains(tempElem))
			{
				topTen.add(tempElem.getLog());
				last = tempElem.getLog();
				myArray[index].add(tempElem);
			}
		}
		else
		{
			topTen.add(tempElem.getLog());
			last = tempElem.getLog();
			myArray[index].add(tempElem);
		}
		
		
		
	}
	
	public int hash(String key)
	{
		return(Math.abs(key.hashCode())%75);
	}
	
	public String toString()
	{
		String returnVal = "";
		for(int i = 0; i < size; i++)
		{
			returnVal += "BUCKET: " + i + "\n";
			returnVal += myArray[i] + "\n";
		}
		return returnVal;
	}
	
	private class HashElement implements Comparable<HashElement>
	{
		String key;
		HTTPLog log;
		
		public HashElement(String a, HTTPLog b)
		{

			key = a;
			if(a.endsWith("/"))
				key += "index.html";
			log = b;
		}
		
		public HTTPLog getLog()
		{
			return log;
		}
		
		public String getKey()
		{
			return key;
		}
		
		public void setLog(HTTPLog a)
		{
			log = a;
		} 
		
		public void setKey(String a)
		{
			key = a;
		}
		
		public int compareTo(HashElement a)
		{
		
			String tempKey = a.getKey();
			return(key.compareTo(tempKey));
		}
		
		public String toString()
		{
			return getLog().toString();
		}
		
	}
	
}