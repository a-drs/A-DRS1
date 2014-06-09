#include<iostream.h>
#include<string.h>
#include<conio.h>
#include<fstream.h>
#include<math.h>
#include<stdlib.h>
#include<stdio.h>



typedef  struct rec{
  int vs;
  char str[10];
  int lines;
  int flag;


}rec;

fstream f1,f2,f3,f4,f5;

class files
{
public:


	void file()
	{
		//cout<<"\nIn file.";
		ifstream f1;
		ofstream f2;
		f1.open("revert.txt");


		if(!f1)
		{
		     //cout<<"\ncreating."<<endl;
		     f2.open("revert.txt");
		     f2.close();
		}
		f1.close();
	}


};


int main(int argc,char * argv[])
{


  int lc=0;
  rec r,s;
  int go_back;
  char line[12],temp[12],str1[12];
  clrscr();

  files f;

  //getch();
  if(argc!=2)
  {
	cout<<"\nIncorrect arguments, run from command line again\n";
	getch();
	return 0;

  }
 if(!strcmp(argv[1],"test.txt"))
 {
 f.file();
  cout<<"\ninside versioning";
  getch();
  f1.open("test.txt", ios::in);
  while(!f1.eof())
  {
     f1.getline(line,10);  //this will give us line count, last string as well
     lc++;
  }
  f1.close();

  r.lines=0;
  r.flag= 0;
  r.vs=0;

  f2.open("revert.txt",ios::in | ios::binary);
  while(!f2.eof())
  {
    f2.read((char *)&r,sizeof(rec));        //get the last rec in revert file

  }
  f2.close();



  if(r.lines<lc) //a new line has been appended
  {


      s.vs=r.vs+1; //next version
      strcpy(s.str,line);
      s.lines=lc; //new line appended
      s.flag=1;

      f2.open("revert.txt",ios::binary | ios::app);
      f2.write((char *)&s, sizeof(rec));
      cout<<"\nversion "<<s.vs<<" . 00 ";
      f2.close();
      f1.close();

  }
  else
  {
	 if(r.lines>lc) //a line has been deleted
	 {
	    s.vs=r.vs+1; //next version
	    f3.open("l_line.txt",ios::in);
	    f3.getline(temp,10);
      strcpy(s.str,temp);
      f3.close();
      f3.open("l_line.txt",ios::trunc);
      f3.close();

      s.lines=lc; //new line appended
      s.flag=-1;

      f2.open("revert.txt",ios::binary | ios::app);
      f2.write((char *)&s, sizeof(rec));
      cout<<"\nversion "<<s.vs<<".00";
      f2.close();
      f1.close();

	 }
	 else
	 {
	  // no change
	 }
  }

     f3.open("l_line.txt",ios::out);
    f3<<line;  //save the last line in the main file
    //might be required if for next version.
    f3.close();
  }
  else
  {
    f2.open("revert.txt",ios::in|ios::binary);
    while(!f2.eof())
    {
      f2.read((char *)&r, sizeof(rec));

    }
    f2.close();

    go_back=atoi(argv[1]);
    cout<<"\nversion::"<<go_back<<"\n";
    int k=r.vs;       //back tracking
    int lines=r.lines;
    f4.open("version.txt",ios::out);
    f1.open("test.txt",ios::in);

	while(!f1.eof())      //copy of the main file
	{
	 f1.getline(temp,10);
	 f4<<temp<<"\n";
	}
	f1.close();

    f2.open("revert.txt",ios::binary |ios::in);

    while(r.vs>go_back)
    {
	 if(r.flag==1) //rec must not be present in earlier version
	 {
	    f4.close();
	    f4.open("version.txt",ios::in);
	    f5.open("temp.txt",ios::out);
	    for(int i=0;i<r.lines-1;i++)
	    {
	      str1[0]=0;
	      f4.getline(str1,10);
	      f5<<str1<<"\n";

	    }
	    //change_contents();
	  f4.close();
	  f5.close();
	   f4.open("version.txt",ios::trunc);
	   f4.close();

	    f5.open("temp.txt",ios::in);
	    f4.open("version.txt",ios::out);

	    while(!f5.eof())
	    {
		f5.getline(str1,10);
		f4<<str1<<"\n";
	    }
	     f4.close();
	  f5.close();
	  f5.open("temp.txt",ios::trunc);
	   f5.close();

	 }
	 else//flag is -1
	 { //therefore rec needs to be appended
	   f4.seekg(0,ios::end);
	   f4<<r.str;
	 }
	k--;

	f2.seekg(sizeof(rec)*(k-1),ios::beg);
	f2.read((char *)&r,sizeof(rec));
	//read rec

    }

      f4.close();

      //display version.txt

      f4.open("version.txt",ios::in);
      while(!f4.eof())
      {
	  f4.getline(line,10);
	  cout<<line<<"\n";
      }

  }
  return 0;
}
/*
void change_contents()
{
fstream f5;
char str1[12];

 f4.open("version.txt",ios::in);
	f4.getline(str1,10);
      while(!f4.eof())
      {

	    f5<<str1;
	    f4.getline(str1,10);
      }

} */
