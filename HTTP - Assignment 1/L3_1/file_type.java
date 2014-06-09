/* The log file for which this program is written has a request line with the URL issued
   by client. Using the contents of the URL we can identify the type of file the user 
   is requesting. */                     

public class file_type 
{
 int js,css;
 int gif,png,jpg,ico;   // Different file extensions.
 int php,txt;
 int other;
 
 file_type()  // Defualt constructor.
 {
  js=0;css=0;gif=0;png=0;
  jpg=0;ico=0;php=0;txt=0;
  other=0;
 }
	
 void accept(String test)
 {
  if(test.equals(".js"))
   js++;
  else if(test.equals("css"))
   css++;
  else if(test.equals("gif"))
    gif++;
  else if(test.equals("png"))
	png++;
  else if(test.equals("jpg"))
	jpg++;
  else if(test.equals("ico"))
	ico++;
  else if(test.equals("php"))
	php++;
  else if(test.equals("txt"))
	txt++;
  else
	other++;    
 }
}
