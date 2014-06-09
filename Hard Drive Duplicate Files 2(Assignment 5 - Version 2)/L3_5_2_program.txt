/*This program uses the concept of HashMap utility provided in Java. Each file encountered is 
 * stored within a HashMap comprising a key which is a String(filename) and a value which is
 * a list of Strings(pathnames) of all files that share a common filename.    
 *Once all the files are scanned, any duplicate occurrences are recorded in a separate HashMap dup,
 *so that only duplicate occurrences need to be traversed later.   
 *Additionally, it lists all duplicate files at the end of the program, and gives additional
 * information such as time and size of each file, and allows the user to select which file 
 * he desires to delete, and performs this accordingly.


The user is asked to enter complete path of initial directory or drive.
 */


import java.io.File;
import java.io.IOException;
import java.util.*;


public class drive{

	
	

	public static void main(String args[])throws IOException
	{
		
		Scanner input=new Scanner(System.in);
		
		drive d=new drive();
		
		
		HashMap<String, List<String>> store=new HashMap<String,List<String>>();
		HashMap<String, List<String>> dup=new HashMap<String,List<String>>();
		List<String> list=new ArrayList<String>();
		List<String> te=new ArrayList<String>();
		List<String> t=new ArrayList<String>();

		/*
		 * Specify the directory or root path from which you wish to run this program from. 
		 * */
		 
		String current=new String("C:/Users/Administrator/Documents");	
		 
		System.out.print("Enter Initial path : " );
		current=input.nextLine();
		System.out.println("\nInitial path selected is : " + current + "\n\n");
		

		File file =new File(current);
		File temp=new File(current);
		String[] dir;
		dir=file.list();//returns string array containing names of all files and directories within
		
		int len=dir.length;
		boolean isfile;
		
		for(int i=0;i<len;i++)
		{
			System.out.print(dir[i]);
			temp=new File(current+"/"+dir[i]);
			isfile=temp.isFile();
			if(isfile)
			{
				
				System.out.println("\tIs file");
				if(store.containsKey(temp.getName()))
				{//if filename exists in store
					list=null;
					list=store.get(temp.getName());
					list.add(temp.getAbsolutePath());
					store.put(temp.getName(), list);
					if(dup.containsKey(temp.getName()))
					{//this contains only those filenames which are duplicated
						//te=null;
						//te=dup.get(temp.getName());
						//te.add(temp.getAbsolutePath());
						dup.put(temp.getName(), list);
					}
					else
					{//first instance of duplication
						te=store.get(temp.getName());
						//te.add(temp.getAbsolutePath());
						dup.put(temp.getName(), te);
						
					}
				}
				
				else
				{//not a duplicate, added normally to store
					list=new ArrayList<String>();
					list.add(temp.getAbsolutePath());
					store.put(temp.getName(), list);
				}
				
			}
			isfile=temp.isDirectory();
			if(isfile)
			{
				System.out.println("\tIs directory\n\nCONTENTS OF SUB-DIRECTORY " + temp.getName());
				d.expand(temp,current,store,dup);//expanding sub directory
				System.out.println("\nSUB-DIRECTORY OVER " + temp.getName() + "\n\n");
			}
			
		}
		
		
		int ch=0;
		System.out.println("\nDuplicate Files are : \n");
		Set set=dup.entrySet();//storing set of duplicate files
		Iterator i=set.iterator();
		while(i.hasNext())
		{
			t=null;
			Map.Entry<String,List<String>> map=(Map.Entry<String,List<String>>)i.next();
			t=map.getValue();//stores the list of pathnames
			
				System.out.println("Duplicate Filename : " + map.getKey() + "\n");
				for(int cnt=0;cnt<t.size();cnt++)
				{
					temp=new File(t.get(cnt));
					System.out.println(cnt+" " + t.get(cnt));//file path
					
					System.out.println("\tSize : " + temp.length() + " bytes");//size
					Date dt=new Date(temp.lastModified());//time
					System.out.println("\tLast Modified : " + dt );
					
				}
				
				do
				{
					//user enters file number that he desires to delete
					System.out.print("\nEnter which file you desire to delete by specifying it's number, to escape enter any negative value : ");
					ch=input.nextInt();
					if(ch<0 || ch>t.size())
						break;
					temp=new File(t.get(ch));
					if(temp.exists())
					temp.delete();//deleting file
					
				}
				while(ch>=0 && ch<t.size());
				
				System.out.println("\n");

			
			
		}
		
		
		System.out.println("Program Complete");
		input.close();
	}
		
	
	public void expand(File file, String current,HashMap<String, List<String>> store,HashMap<String, List<String>> dup)throws IOException
	{//recursive function that keeps expanding sub directories

		List<String> list=new ArrayList<String>();
		List<String> te=new ArrayList<String>();
		//list=null;
		//te=null;
		Scanner input=new Scanner(System.in);
		
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
				
				System.out.println("\tIs file");
				if(store.containsKey(temp.getName()))
				{
					list=null;
					list=store.get(temp.getName());
					list.add(temp.getAbsolutePath());
					store.put(temp.getName(), list);
					if(dup.containsKey(temp.getName()))
					{
						//te=null;
						//te=dup.get(temp.getName());
						//te.add(temp.getAbsolutePath());
						dup.put(temp.getName(), list);
					}
					else
					{
						te=store.get(temp.getName());
						//te.add(temp.getAbsolutePath());
						dup.put(temp.getName(), te);
						
					}
				}
				
				else
				{
					list=new ArrayList<String>();
					list.add(temp.getAbsolutePath());
					store.put(temp.getName(), list);
				}
	
				
			}
			isfile=temp.isDirectory();
			if(isfile)
			{
				System.out.println("\tIs directory\n\nCONTENTS OF SUB-DIRECTORY " + temp.getName());
				d.expand(temp,current,store,dup);
				System.out.println("\nSUB-DIRECTORY OVER " + temp.getName() + "\n\n");
				
			}
			
		}

		//bw.close();
		//input.close();
	}
	
	}
