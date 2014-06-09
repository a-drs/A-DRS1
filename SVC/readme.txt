Steps:

1) 3 main files are required , svc1.cpp and two text files.
	->test.txt is the main file and it has been populated with a word
	->revert.txt is empty and will be used by the program.


2)Compile and run program once in TC. It'll request to run from command prompt as arguments are needed.
This step is necessary to create .exe file.

3) in command prompt(TC)
>svc1 test.txt  // to create a new version of the file
	This is a step that has to be run after each change is made,i.e., each time new version is created.

>svc1 n   // any earlier version number line svc1 2, svc1 4
	Now, one versioning is done, use this command to access different versions.
also once an earlier version is referenced a file called version.txt will have the relevant contents. This will be auto-created
in the background.

4)for each new version append or delete a word below the existing file(test.txt) and save , followed by svc1 test.txt


PREFERRED PLATFORM : C++,Turbo c++ , on Windows was used for this program.