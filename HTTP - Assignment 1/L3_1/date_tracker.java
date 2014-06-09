/* The 4th field in the HTTP log file entry contains the data, time and timezone when server finished processing the 
   request. It has 'strftime' format.
 */

public class date_tracker 
{
 int D_cnt;       // Common array identifier.
 String dates[];  // To store unique dates.
 int hits[];      // Hits per date.
 
 date_tracker()  // Default constructor.
 {
  dates=new String[100];
  hits=new int[100];
  for(int m=0;m<100;m++)
   hits[m]=0;
  D_cnt=0;
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
  String abc=new String(temp,0,b);
  if(D_cnt==0)   // For first entry.
  {
   dates[D_cnt]=abc;
   hits[D_cnt]++;
   D_cnt+=1;
  }
  else
  {
   flag=0;
   for(i=0;i<D_cnt;i++)   // To prevent duplicate copies from being stored.
   {
    if(dates[i].equals(abc))
	{
	 flag=1;
	 hits[i]++;
	 break;
	}
   }
   if(flag==0)
   {
    dates[D_cnt]=abc;
	hits[D_cnt]++;
    D_cnt++;
   }
  }
 }
	 
};
