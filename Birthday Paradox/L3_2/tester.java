import java.util.*;

public class tester   // Class to perform multiple test cases.
{
 static String spaces(int a)  // Function to create spaces required for table formatting.
 {
  String str="";
  for(int i = 0; i<a; i++)
   str+=" ";
  return str;
 }
	
 public static void main(String[] args) 
 {
  float stastics1[]=new float[50];  // Stores number of people N.
  int stastics2[]=new int[50];     // Stores probability statistics.
  int i=0;
  
  System.out.println("\t\t\t\t\t\t  ***************  A-DRS BIRTHDAY PARADOX EXPERIMENT ***************");
  System.out.println(" ");
  System.out.println("1) User is requested to enter the number of people taking part in the experiment");
  System.out.println("2) To stop the experiment enter the number of people as 0");
  System.out.println("3) On termination a summary table will give statsics of the test cases performed");
  System.out.println(" ");
    
  while(true)
  {
   birthday B=new birthday();   // Instance of class birthday.
   int N;
   float temp;
   Scanner in=new Scanner(System.in);
   System.out.print("--------------------------------------------------------------------------------------------");
   System.out.println("------------------------------------------------------------------------------------------");
   System.out.println("Enter the number of people: ");
   N=in.nextInt();
   if(N==0)          // If N=0 complete processing
	 break;   
   System.out.println("Conclusions after performing 1000 trials for "+N+" number of people");	 
   System.out.println(" > Trials performed: 1000");
   temp =B.simulate(N,1000);
   System.out.println(" > Number of trials where birthdays matched: "+B.match_Y);
   System.out.println(" > Number of trials where birthdays did not match: "+B.match_N);
   System.out.printf(" > Probability of two people having the same birthday: %.4f\n",temp); 
   stastics2[i]=N;
   stastics1[i]=temp;
   i++;
   System.out.println(" "); 
  }
  System.out.print("--------------------------------------------------------------------------------------------");
  System.out.println("------------------------------------------------------------------------------------------");
  System.out.println("\t\t\t\t\t\t\t\t***** SUMMARY *****");
  System.out.println(" ");
  System.out.println("\t+-----------+--------------+-------------+");
  System.out.println("\t|"+spaces(1)+"Test Case"+spaces(1)+"|"+spaces(1)+"No of People"+spaces(1)+"|"+spaces(1)+"Probability"+spaces(1)+"|");
  System.out.println("\t+-----------+--------------+-------------+");
  for(int j=0;j<i;j++)
   System.out.println("\t|"+spaces(4)+" "+(j+1)+" "+spaces(4)+"|"+spaces(4)+" "+stastics2[j]+"\t"+spaces(3)+"|"+spaces(3)+" "+stastics1[j]+"\t |");	  
  System.out.println("\t+-----------+--------------+-------------+");
 }
 
}
