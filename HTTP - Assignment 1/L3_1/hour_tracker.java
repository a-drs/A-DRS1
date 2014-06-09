/* The 4th field in the HTTP log file entry contains the data, time and timezone when server finished processing the 
   request. It has 'strftime' format.
 */

public class hour_tracker 
{
 int H_cnt;        // Common array identifier. 
 String hours[];   // Storing both data and time(hour)
 int hits[];       // Unique hits
 
 hour_tracker()  // Default constructor.
 {
  hours=new String[300];
  hits=new int[300];
  for(int m=0;m<300;m++)
   hits[m]=0;
  H_cnt=0;
 }
 
 void accept(char input[],int input_cnt)
 {
  int b=0,i;
  int flag;
  char temp[]=new char[50];
  do                 // Selecting only date.
  {
   temp[b++]=input[input_cnt++];
  }while(input[input_cnt]!=':');
  do                 // Selecting only time(hour).
  {
   temp[b++]=input[input_cnt++];
  }while(input[input_cnt]!=':'); 
  String abc=new String(temp,0,b);
  if(H_cnt==0)   // For first entry.
  {
   hours[H_cnt]=abc;
   hits[H_cnt]++;
   H_cnt+=1;
  }
  else
  {
   flag=0;
   for(i=0;i<H_cnt;i++)   // To prevent duplicate copies from being stored.
   {
    if(hours[i].equals(abc))
	{
	 flag=1;
	 hits[i]++;
	 break;
	}
   }
   if(flag==0)
   {
    hours[H_cnt]=abc;
	hits[H_cnt]++;
    H_cnt++;
   }
  }
 }
	 
};
