/** 
Walter Michelin
November 22nd 2013
Dr. DePasquale
Project 3
*/

package edu.tcnj.csc230;

import jsjf.*;
import jsjf.exceptions.*;
import java.util.Scanner;
import java.net.*;
import java.io.*;
import java.lang.*;


/** 
*The Processor class reads input from the provided URL
*The Processor class also handles user input for desired commands, provided they are valid
*/

public class Processor
{
	
	/**
	*the process method creates a hash table and fills it
	*based on key of HTTPLog object
	*/
	public void process()
	{
		HashTable table = new HashTable(75);
		String inLine = "";
		String sub = "";
		String date = "";
		String name = "";
		int statusCode = 0;
		int numBytes = 0;
		int frontIndex = 0;
		int backIndex = 0;
		
		Scanner sc = new Scanner("");
		Scanner sc2 = new Scanner("");
		
		HTTPLog log = new HTTPLog(); 
				
		try
		{
			sc = new Scanner(new URL(
				"http://s3.amazonaws.com/depasquale/datasets/access_log").openStream());
		}
		catch(IOException e)
		{
			System.err.println("error");
		}
		
		while(sc.hasNextLine())
		{
			inLine = sc.nextLine();
			frontIndex = inLine.indexOf("[")+1;
			backIndex = inLine.indexOf("]");
			date = inLine.substring(frontIndex, backIndex);

			frontIndex = inLine.indexOf("\"")+1;
			backIndex = inLine.indexOf("\"", frontIndex);
			sub = inLine.substring(frontIndex, backIndex);
		
			frontIndex = sub.indexOf("/");
		
			if(sub.contains("?"))
			{
				name = sub.substring(frontIndex, sub.indexOf("?"));	
			}
			else
			{
				name = sub.substring(frontIndex, sub.indexOf("HTTP")-1);
			}
		
			if(name.endsWith("/"))
				name += "index.html";
			frontIndex = backIndex+1;
		
			sub = inLine.substring(frontIndex);
			sc2 = new Scanner(sub);
			statusCode = sc2.nextInt();
			if(sc2.hasNextInt())
				numBytes = sc2.nextInt();
			else
				numBytes = 0;
			
			if(!table.contains(name))
			{
				log = new HTTPLog(date, name, statusCode, numBytes);
				table.add(log);
			}
			else
			{
				table.getLog(name).setDate(date);
				table.getLog(name).setStatusCode(statusCode);
				table.getLog(name).addBytes(numBytes);
				table.getLog(name).setTimesAccessed(table.getLog(name).getTimesAccessed() + 1);
			}

		}
		
		boolean running = true;
		int num = 0;
		String in = "";
		String command = "";
		String resourceName = "";
		HTTPLog temp = new HTTPLog();
		Scanner input = new Scanner(System.in);
		Scanner inputCheck = new Scanner(in);
		/**
		*Loop to handle user input after HashTable is filled with HTTPLog objects
		*/
		
		while(running)
		{
			boolean checker = true;
			num = 0;
			in = "";
			System.out.println("Enter a valid command: ");
			in = input.nextLine();
			inputCheck = new Scanner(in);
			command = inputCheck.next();
			
			if(command.equals("DETAIL"))
			{
				in = inputCheck.next();
				try
				{
					temp = table.getLog(in);
				}
				catch(ElementNotFoundException e)
				{
					System.err.println("Element not found");
				}
				System.out.println();
				System.out.println(temp);
			}
			if(command.equals("SERVED"))
			{
				checker = true;
				in = inputCheck.next();
				try
				{
					temp = table.getLog(in);
				}
				catch(ElementNotFoundException e)
				{
					checker = false;
					System.out.println("Element was not in collection!");
				}
				if(checker)
				{
					System.out.println();
					System.out.println("SERVED RESOURCE: " + in);
					System.out.println("DATE OF LAST REQUEST: " + temp.getDate());
					System.out.println("TIMES REQUESTED: " + temp.getTimesAccessed());
				}
			
			}
			else if(command.equals("TOPTEN"))
			{
				System.out.println();
				System.out.println(table.topTen());
			}
			
			else if(command.equals("LAST"))
			{
				System.out.println();
				System.out.println("LAST REQUESTED RESOURCE: ");
				System.out.println(table.getLast());
			}
			
			else if(command.equals("QUIT"))
			{
				running = false;
			}
			
			else
			{
				System.out.println("PLEASE ENTER A VALID COMMAND");
			}
	
			
		}
		
	}
}