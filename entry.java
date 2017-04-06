package inventory;

import java.util.Formatter;
import java.util.Scanner;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;
public class entry{
	public static item[] entryList=new item[200];
	public static int n=0;

	public static void main(String[] args) {
		String selector, name;
		Scanner stdin=new Scanner(System.in);
		String file="/home/wmc/Documents/code/java/project6/database.txt";
		readIn(file);
		System.out.print("Command: ");
		selector=stdin.next();
		name=stdin.next();
		selector=selector.toLowerCase();
		switch(selector)
		{
			case "e": enter(name);
				break;
			case "f": print(find(name));
				break;
		}

	}

	public static void readIn(String filename){
		try 
		{
			FileReader fr=new FileReader(filename);
			BufferedReader br= new BufferedReader(fr);
			String[] lines=new String[200];
			for (int i=0;i<200;++i)
			{
				lines[i]=br.readLine()+ " ";
				lines[i]+=br.readLine()+ " ";
				lines[i]+=br.readLine();
				if (lines[i]==null)
				{
					break;
				}
				System.out.println(lines[i]);
				process(lines[i], i);
				lines[i]=br.readLine();
			}
			br.close();
			return;	
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}

	public static void enter(String name)
	{
		Scanner stdin=new Scanner(System.in);
		int qty, index;
		String notes;
		System.out.printf("Enter quantity: ");
		qty=stdin.nextInt();
		System.out.printf("Enter notes: ");
		notes=stdin.next();
		index=find(name);
		if (index != -9) //found entry
		{
			entryList[index].qnty=qty;
		}
		else
		{
			entryList[++n].name=name;
			entryList[n].qnty+=qty;
			entryList[n].notes=notes;
		}
		return;
	}

	public static void WriteInventory(String FileName) throws Exception{
		Formatter out=new Formatter(FileName);
		for (int x=0; x < n; ++x)
		{
			out.format("%s %d %s%n", entryList[x].name, entryList[x].qnty, entryList[x].notes);
		}
	}

	public static int find(String to_Search) //just a placeholder. Delete when the real one exists
	{
		for (int i = 0; i < n;++i){
		if (to_Search.equals(entryList[i].name)){
		return i;}
		}
		return -9;
	}
	public static void list()
	{ 
		for (int i = 0; i < n; i++)
			print(i);
	}
public static void print(int flamingo)
{ System.out.println(entryList[flamingo].name + entryList[flamingo].qnty + entryList[flamingo].notes);
}

public static void process (String line, int place)
{
	int where, where2;
	where2=line.indexOf(" ");
	System.out.println(line);
	entryList[place].name=line.substring(0,where2);
	where=where2;
	where2=line.indexOf(" ", where+1);
	entryList[place].qnty=Integer.parseInt(line.substring(where, where2));
	where=where2;
	where2=line.indexOf(" ", where+1);
	entryList[place].notes=line.substring(where, where2);
}

}
