Steps
1) Folder 'L3_1' is the java project which needs to be imported into the workspace. It contains the following java files: 
>> date_tracker.java
>> file_type.java
>> hour_tracker.java
>> log_reader.java
>> print.java
>> resource_requested.java
>> status_code.java

2) The folder 'L3_1' also conatins the text file 'weblog.txt' which is the input log file to be read.

3) On executing print.java, the program will first prompt the user to enter the name of the log file. On pressing enter 
the processing will begin and the code will divide up the log file into various entries and print each of these entries 
and list their details. After finishing the processing a summary will be displayed for the input log file.

4) This summary will also be stored in a text file- 'output.txt' which will be used by the next part of the solution.There
 are two text files uploaded- 'java_output_1.txt' and 'java_output_2.txt' which show the sample output which is produced 
by the java code. 

5) For the second part of the solution we have a php page which acts as a menu and allows the user to be directed to 
various bar graphs and pie charts which further elaborate the summary obtained from the java program that was previously
 run. Hence the file 'output.txt'needs to be present.

6) We have used wampserver2.4 which allows us to run the required php pages. In wampserver2.4 a directory called 'www' is
 present in a file 'wamp' in C drive. All php pages need to be present in 'www' folder or any of its sub-directories, so
 that they can be run from the browser. The folder 'l3' which is uploaded with the solution needs to be stored in this
 directory 'www' by the user.

7) The folder 'l3' conatins various files that are needed to run bootstrap and the high charts along with php html pages
 and the file 'output.txt'. The user is requested to type the following URL in his/her browser to begin the second part
 of the solution- "localhost/l3/output_summary.php". The page itself is self explanatory and provides links for various 
graphical options. Multiple screen-shots have been uploaded allowing the user to better understand how to run the solution.