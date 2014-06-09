import java.io.*;
import java.util.*;

public class print    // The base class used for processing and printing summary
{
  public static void main(String [] args) 
  {
   String fileName;                                // Name of log file to be read.
   String output_file="output.txt";                // Output file storing the statistics for further processing
   String line = null;                             // Single line(entry) accessed at a time.
   log_reader R1=new log_reader();                 // Instances of various classes used.
   resource_requested R2=new resource_requested();	
   status_code R3=new status_code();
   file_type R4=new file_type();
   date_tracker R5=new date_tracker();
   hour_tracker R6=new hour_tracker();
   int flag=0;     // Maintains entry numbers.                  
  
   System.out.println("\t\t\t\t\t\t\t***************  A-DRS WEB ANALYSER ***************");
   System.out.println(" ");
   Scanner in=new Scanner(System.in);
   System.out.println("Enter the name of the log file: ");
   fileName=in.nextLine();
   
   try 
   {
    // FileReader reads text files in the default encoding.
    FileReader fileReader = new FileReader(fileName);
    // Always wrap FileReader in BufferedReader.
    BufferedReader bufferedReader = new BufferedReader(fileReader);
    // FileWriter wites to text files in  default encoding.
    FileWriter fileWriter = new FileWriter(output_file);
    // Always wrap FileWriter in BufferedWriter.
    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        
    
    while((line = bufferedReader.readLine()) != null) // End of file given by null.
    {
      R1.entry(line,flag+1,R2,R3,R4,R5,R6);  // Processing/
      flag++;
    }
    
    System.out.println(" ");     // Prininting the summary after processing.
    System.out.println(" ");
    System.out.println(" ");
    System.out.print("--------------------------------------------------------------------------------------------");
    System.out.println("------------------------------------------------------------------------------------------");
    System.out.println("\t\t\t\t\t\t\t\t\t   SUMMARY  ");
    System.out.println(" Log file name: "+fileName+" ");
    System.out.println(" ");
    System.out.println(" No of requests made by client: "+flag+" ");
    System.out.println(" ");
    System.out.println(" Servers frequently accessed by client: ");
    for(int k=0;k<R2.S_cnt;k++)
    {
     System.out.println("  "+(k+1)+") Hits: "+R2.hits[k]+"\t\t"+R2.site[k]);	 
     bufferedWriter.write(Integer.toString(R2.hits[k],10));
     bufferedWriter.newLine();
     bufferedWriter.write(R2.site[k]);
     bufferedWriter.newLine();
    }
    bufferedWriter.newLine();
    System.out.println(" ");
    System.out.println(" Status code statistics returned to client by servers: ");
    System.out.println("  "+R3.count_2xx+"\t\tSucessfull responses (2XX)");
    System.out.println("  "+R3.count_3xx+"\t\tRedirections (3XX)");
    System.out.println("  "+R3.count_4xx+"\t\tClient errors (4XX)");
    System.out.println("  "+R3.count_5xx+"\t\tServer errors (5XX)");
    bufferedWriter.write(Integer.toString(R3.count_2xx,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R3.count_3xx,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R3.count_4xx,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R3.count_5xx,10));
    bufferedWriter.newLine();
    bufferedWriter.newLine();
    System.out.println(" ");
    System.out.println(" File types requested by the client: ");
    System.out.println("  .js\t  Java Script Files\t\t\t"+R4.js+"\t");
    System.out.println("  .css\t  Cascading Style Sheet Files\t\t"+R4.css+"\t");
    System.out.println("  .gif\t  Image Files\t\t\t\t"+R4.gif+"\t");
    System.out.println("  .png\t  Image Files\t\t\t\t"+R4.png+"\t");
    System.out.println("  .jpg\t  Image Files\t\t\t\t"+R4.jpg+"\t");
    System.out.println("  .ico\t  Image Files\t\t\t\t"+R4.ico+"\t");
    System.out.println("  .php\t  Dynamic PHP Script Files\t\t"+R4.php+"\t");
    System.out.println("  .txt\t  Text Files\t\t\t\t"+R4.txt+"\t");
    System.out.println("      \t  Others\t\t\t\t"+R4.other+"\t");
    bufferedWriter.write(Integer.toString(R4.js,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.css,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.gif,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.png,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.jpg,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.ico,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.php,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.txt,10));
    bufferedWriter.newLine();
    bufferedWriter.write(Integer.toString(R4.other,10));
    bufferedWriter.newLine();
    bufferedWriter.newLine();
    System.out.println(" ");
    System.out.println(" Datewise hits: ");
    for(int v=0;v<R5.D_cnt;v++)
    {
     System.out.println("  "+(v+1)+") Hits: "+R5.hits[v]+"\t\t"+R5.dates[v]);	 
     bufferedWriter.write(Integer.toString(R5.hits[v],10));
     bufferedWriter.newLine();
     bufferedWriter.write(R5.dates[v]);
     bufferedWriter.newLine();
    }
    bufferedWriter.newLine();
    System.out.println(" ");
    char get[];
    int q;
    int qq=0;
    System.out.println(" Hourwise hits: ");
    for(int p=0;p<R5.D_cnt;p++)
    {
      q=qq;	
      if(R6.hours[q].contains(R5.dates[p]))
      {
       System.out.println(" ");	  
       System.out.println("   "+(p+1)+") "+R5.dates[p]);	
       bufferedWriter.write(R5.dates[p]);
       bufferedWriter.newLine();
       while(R6.hours[q].contains(R5.dates[p]))
       {
    	get=R6.hours[q].toCharArray();
    	System.out.println("\t"+get[12]+get[13]+":00 hrs\tHits: "+R6.hits[q]);	 
    	bufferedWriter.write(Integer.toString(R6.hits[q]));
    	bufferedWriter.newLine();
    	q++;
        if(q==R6.H_cnt)
         break;	
       }
       qq=q;
       bufferedWriter.write(Integer.toString(R5.hits[p],10));
       bufferedWriter.newLine();
       bufferedWriter.newLine();
       
      }
    }
    System.out.print("--------------------------------------------------------------------------------------------");
    System.out.println("------------------------------------------------------------------------------------------");
    
    bufferedReader.close();			// Close file.
    bufferedWriter.close();
   }
   
   catch(FileNotFoundException ex) 
   {
    System.out.println("Unable to open file ");				
   }
  
   catch(IOException ex) 
   {
    System.out.println("Error reading/writing to file ");					
   }
  
  }
}