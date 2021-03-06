/* log_reader class works on each individual entry from the log file. HTTP log file entries are further understood by dividing them into their respective fields 
   and comparing it with CLF(Common Log Format) also called NCSA-CLF.*/

public class log_reader
{
 char input[];     // Character array to store each individual entry.
 int cnt;          // Array identifier.
 
 public void entry(String ip,int number,resource_requested R,status_code S,file_type F,date_tracker D,hour_tracker H) 	
 {
  input=ip.toCharArray();
  cnt=0;
  System.out.println(" ");
  System.out.println(" ");
  System.out.print("****************************************************************************");
  System.out.print(" ENTRY "+number+" ");
  System.out.print("****************************************************************************");
  System.out.println(" ");
  System.out.println(" ");
  
  System.out.print(" Client IP address: ");
  do                            // IP address of remote host(client) which made the request to the server. 
  {
   System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!=' ');
  System.out.println(" ");

  System.out.print(" Client RFC 1413 identity: ");
  do
  {
   System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!=' ');
  System.out.println(" ");
 
  System.out.print(" User ID: ");
  do
  {
   System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!=' ');
  System.out.println(" ");
 
  System.out.print(" Date Time Timezone:");
  D.accept(input,cnt+2);
  H.accept(input,cnt+2);
  while(input[cnt]!=']')          // Date and time details of when server finished processing the request.
  {
   System.out.print(input[cnt]);
   cnt++;
  }
  System.out.print("]");
  cnt+=3;
  System.out.println(" ");
 
  System.out.println(" Request Line: ");  // Request Line generated by client as part of HTTP request message.
  System.out.print("    Method: ");     
  do	                             // Request line has three sub-fields Method,URL and HTTP version.
  {
   System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!=' ');
  cnt+=1;
  System.out.println(" ");
  R.accept(input,cnt);            // Function to identify the resource requested.
  System.out.print("    URL: ");
  do	 
  {
   System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!=' ');
  
  char checker[]=new char[3];   // Identifying file extension from the URL.
  checker[0]=input[cnt-3];
  checker[1]=input[cnt-2];
  checker[2]=input[cnt-1];
  String checks= new String(checker,0,3);  // Function to check the file type.
  F.accept(checks);
   
  cnt+=1;
  System.out.println(" ");
  System.out.print("    Version: ");
  do
  {
   if(input[cnt]!='"')	
    System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!='"');
  cnt+=2;
  System.out.println(" ");
  
  System.out.print(" HTTP status code: ");
  S.accept(input[cnt]);    // Function to process the status code returned to client as part of the status line in HTTP response message.
  do
  {
   System.out.print(input[cnt]);
  cnt++;
  }while(input[cnt]!=' ');
  System.out.println(" ");

  System.out.print(" Object size: ");  // Object size returned to client in bytes excluding the response headers.
  do
  {
   System.out.print(input[cnt]);
  cnt++;
  }while(input[cnt]!=' ');
  System.out.print(" bytes");
  cnt+=2;
  System.out.println(" ");
  
  System.out.println(" HTTP request headers: ");
  System.out.print("    Referrer: ");
  do	 
  {
   if(input[cnt]!='"')	 
    System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!='"');
  cnt+=3;
  System.out.println(" ");
  System.out.print("    User agent: "); 
  do	  
  {
   if(input[cnt]!='"' && input[cnt]!=' ')	 
    System.out.print(input[cnt]);
   cnt++;
  }while(input[cnt]!='"');
  System.out.println(" ");
 }
}
