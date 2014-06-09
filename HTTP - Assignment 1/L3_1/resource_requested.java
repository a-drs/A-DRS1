/* The URL as part of the request line is processed and the server i.e resource requested is identified by this class */

public class resource_requested
{
 int S_cnt;       // Common array identifier.
 String site[];   // Array to store site names from URL.
 int hits[];      // Hits i.e number of unique requests.

 resource_requested()  // Default constructor.
 {
  site=new String[100];
  hits=new int[100];
  for(int m=0;m<100;m++)
   hits[m]=0;
  S_cnt=0;
 }

 void accept(char name[],int name_cnt)  // The entry and current array identifier value as parameters.
 {
  int b=0,i;
  int flag;
  char temp[]=new char[50];
  do                 // Selecting only base site name from URL.
  {
   temp[b++]=name[name_cnt++];
  }while(name[name_cnt]!='/');
  String abc=new String(temp,0,b);
  if(S_cnt==0)   // For first entry.
  {
   site[S_cnt]=abc;
   hits[S_cnt]++;
   S_cnt+=1;
  }
  else
  {
   flag=0;
   for(i=0;i<S_cnt;i++)   // To prevent duplicate copies from being stored.
   {
    if(site[i].equals(abc))
    {
     flag=1;
     hits[i]++;
     break;
    }
   }
   if(flag==0)
   {
    site[S_cnt]=abc;
    hits[S_cnt]++;
    S_cnt++;
   }
  }
 }
};

