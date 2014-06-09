/*This program uses a general hashed file concept. Each file encountered is 
 * stored at a location C:/store, in a series of files. the hashed key is the first letter 
 * of each filename, depending on which it is added to the file having its first letter as file name.
 * if that file name is found to already be existing within that file, it is ignored and user is asked
 * whether or not he wishes to delete the duplicate, and the operation is performed accordingly.
 *   */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class cop
{
	String[] copy = new String[50];//list of all duplicate file names
	int count;
	
	cop()
	{
		count=0;
		for(int i=0;i<50;i++)
			copy[i]=new String("");
	}
}

public class drive{

	
	
	public String path(String line)
	{
		int index=line.indexOf(' ');
		String str=line.substring(index);
		return str;
	}
	
	public void delete()
	{	
		//method is called at the start of program execution
		String[] dir;
		int len;
		int i;
		String current=new String("C:/store");//this location stores all hash files
		File remove=new File(current);
		File temp=null;
		if(remove.isDirectory())
		{
			dir=remove.list();
			len=dir.length;
			for(i=0;i<len;i++)
			{
				temp=new File(current+"/"+dir[i]);
				temp.delete();//deletes all hash files that store already encountered file names
			}
		}
		else
			remove.mkdir();//if directory not present, it is created
	}
	
	
	
	public static void main(String args[])throws IOException
	{
		int ch=2;
		Scanner input=new Scanner(System.in);
		cop c=new cop();
		drive d=new drive();
		int flag=0;
		d.delete();
		
		
		/*
		 * Specify the directory or root path from which you wish to run this program from. 
		 * */
		 
		String current=new String("C:/Users/Administrator/Documents");	
		 
		System.out.print("Enter Initial path : " );
		current=input.nextLine();
		System.out.println("\nInitial path selected is : " + current + "\n\n");
		
		File store=null;
		BufferedWriter bw=null;
		BufferedReader br=null;
		File file =new File(current);
		File temp=new File(current);
		String[] dir;
		dir=file.list();//returns string array containing names of all files and directories within
		String line;
		int len=dir.length;
		boolean isfile;
		
		for(int i=0;i<len;i++)
		{
			System.out.print(dir[i]);
			temp=new File(current+"/"+dir[i]);
			isfile=temp.isFile();
			if(isfile)
			{
				flag=0;
				store=null;
				bw=null;
				br=null;
				System.out.println("\tIs file");
				store=new File("C:/store/"+temp.getName().charAt(0)+".txt");
				if(store.exists() && store.canRead())
				{
				br = new BufferedReader(new FileReader(store));
				
				while((line=br.readLine())!=null)
				{
					if(temp.getName().equals(line))
					{
						flag=1;
						break;
					}
					
						
				}
				br.close();
				}
				if(flag==1)
				{//if duplicate
					System.out.println("\tDuplicate - " + temp.getName());
					System.out.println("\tPath- " + temp.getAbsolutePath());
					Date dt=new Date(temp.lastModified());
					System.out.println("\tLast Modified- " + dt);
					System.out.println("\tFile Size - " + temp.length() + " bytes");
					c.copy[c.count++]=temp.getName();
					
					System.out.println("Do you wish to delete this duplicate ? 1. Yes 2. No");
					ch=input.nextInt();
					if(ch==1)
					{
						temp.delete();
					}
				}
				else
				{
				bw = new BufferedWriter(new FileWriter(store,true));	
				
				bw.append(temp.getName());
				bw.newLine();
				bw.close();
				}
			}
			isfile=temp.isDirectory();
			if(isfile)
			{
				System.out.println("\tIs directory\n\nCONTENTS OF SUB-DIRECTORY " + temp.getName());
				d.expand(temp,current,c);
				System.out.println("\nSUB-DIRECTORY OVER " + temp.getName() + "\n\n");
			}
		}
		/*if(isfile)
			System.out.println("Directory");
		else
			System.out.println("Not directory");*/
		System.out.println("\n\nDuplicate files are : \n\n");
		for(int i=0;i<c.count;i++)
		{
			System.out.println(c.copy[i]);
		}
		
		input.close();
	}
		
	
	public void expand(File file, String current,cop c)throws IOException
	{//recursive function that keeps expanding sub directories

		int ch=2;
		Scanner input=new Scanner(System.in);
		String line;
		int flag=0;
		File store=null;
		BufferedWriter bw=null;
		BufferedReader br=null;
		//store=new File("C:/somdeep/store.txt");
		//bw = new BufferedWriter(new FileWriter(store));
		drive d=new drive();
		String[] dir;
		dir=file.list();
		int len=dir.length;
		boolean isfile;
		File temp;
		current=current+"/"+file.getName();
		for(int i=0;i<len;i++)
		{
			System.out.print(dir[i]);
			temp=new File(current+"/"+dir[i]);
			isfile=temp.isFile();
			if(isfile)
			{
				flag=0;
				store=null;
				bw=null;
				br=null;
				System.out.println("\tIs file");
				store=new File("C:/store/"+temp.getName().charAt(0)+".txt");
				if(store.exists() && store.canRead())
				{
					br = new BufferedReader(new FileReader(store));
				
				while((line=br.readLine())!=null)
				{
					if(temp.getName().equals(line))
					{
						flag=1;
						break;
					}
					
						
				}
				br.close();
				}
				if(flag==1)
				{
					System.out.println("\tDuplicate - " + temp.getName());
					System.out.println("\tPath- " + temp.getAbsolutePath());
					Date dt=new Date(temp.lastModified());
					System.out.println("\tLast Modified- " + dt);
					System.out.println("\tFile Size - " + temp.length() + " bytes");
					c.copy[c.count++]=temp.getName();
					System.out.println("Do you wish to delete this duplicate ? 1. Yes 2. No");
					ch=input.nextInt();
					if(ch==1)
					{
						temp.delete();
					}
				}
				else
				{
				bw = new BufferedWriter(new FileWriter(store,true));	
				bw.append(temp.getName());
				
				bw.newLine();
				bw.close();
				}
			}
			isfile=temp.isDirectory();
			if(isfile)
			{
				System.out.println("\tIs directory\n\nCONTENTS OF SUB-DIRECTORY " + temp.getName());
				d.expand(temp,current,c);
				System.out.println("\nSUB-DIRECTORY OVER " + temp.getName() + "\n\n");
				
			}
			
		}

		//bw.close();
		//input.close();
	}
	
	}
