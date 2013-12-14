/** 
Walter Michelin
November 22nd 2013
Dr. DePasquale
Project 3
*/

package edu.tcnj.csc230;

import jsjf.*;
import jsjf.exceptions.*;

public class HTTPLog implements Comparable<HTTPLog>
{
	private String date = "";
	private String name = "";
	private int statusCode = 0;
	private int numBytes = 0;
	private int totalBytes = numBytes;
	private int timesAccessed = 1;
	
	public HTTPLog(String a, String b, int c, int d)
	{
		date = a;
		if(b.endsWith("/"))
		{
			b += "index.html";
		}
		name = b;
		statusCode = c;
		numBytes = d;
		totalBytes = numBytes;
	}
	
	public HTTPLog()
	{
	}
	
	/**
	*Getters and setters
	*/
	public void setDate(String a)
	{
		date = a;
	}
	
	public void setName(String a)
	{
		name = a;
		if(name == "/")
		{
			name = "/index.html";
		}
	}
	
	public void setStatusCode(int a)
	{
		statusCode = a;
	}
	
	public void setNumBytes(int a)
	{
		numBytes = a;
	}
	
	public String getDate()
	{
		return date;
	}
	
	public String getName()
	{
		return name;
	}
	
	public int getStatusCode()
	{
		return statusCode;
	}
	
	public int getNumBytes()
	{
		return numBytes;
	}
	
	public void setTimesAccessed(int a)
	{
		timesAccessed = a;
	}
	
	public void addBytes(int a)
	{
		totalBytes += a;
	}
	
	public void setTotalBytes(int a)
	{
		totalBytes = a;
	}
	public int getTotalBytes()
	{
		return totalBytes;
	}
	
	public int getTimesAccessed()
	{
		return timesAccessed;
	}
	
	public int compareTo(HTTPLog log)
	{
		String temp = "";
		String temp2 = "";
		temp = this.getName().substring(1);
		temp2 = log.getName().substring(1);
		return(temp.compareTo(temp2));
	}
	
	public String toString()
	{
		String returnVal = "";
		returnVal += "Date: " + date + "\n";
		returnVal += "Resource Name: " + name + "\n"; 
		returnVal += "Status Code: " + Integer.toString(statusCode) + "\n";
		returnVal += "Number of Bytes: " + Integer.toString(numBytes) + "\n";
		returnVal += "Times Accessed: " + Integer.toString(timesAccessed) + "\n";
		returnVal += "Total number of Bytes: " + Integer.toString(totalBytes) + "\n";
		
		return returnVal;
	}
}