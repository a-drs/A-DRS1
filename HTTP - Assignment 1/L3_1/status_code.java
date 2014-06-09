 /* The log file for which this program is written has a single user requesting pages from
    multiple servers. The response for a request can be understood from the status code 
    that is returned by the server as part of the response message. */                     

public class status_code
{
 int count_2xx;  // Successful response.
 int count_3xx;  // Redirection.
 int count_4xx;  // Client error.
 int count_5xx;  // Server error.

 status_code()  // Default constructor.
 {
  count_2xx=0;  
  count_3xx=0;  
  count_4xx=0;  
  count_5xx=0;  
 }
 
 void accept(char test)
 {
  if(test=='2')
   count_2xx++;
  if(test=='3')
   count_3xx++;
  if(test=='4')
   count_4xx++;
  if(test=='5')
   count_5xx++;
 }
}
