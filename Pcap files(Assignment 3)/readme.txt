Steps:

1)It is necessary to install pcap.h in fedora

2) The sequence of steps for this is as follows : 
>>yum install libpcap
>>yum install libpcap-devel

sometimes the compiler does not find the location of pcap files so use the following commands

>>whereis pcap
returns {path}

compile :
 >>gcc -lpcap -I{path} pcap.c

our path was /usr/include/
to execute
>>./a.out tcp.pcap


in the program pcap.c the following things have been done:
1.  it reads the pcap files and extracts ethernet frame-> finds upper layer protocol (ip,arp,rarp)
2. then for an IP packet we are printing most of the stuff, also finding the next protocol used(UDP, TCP)
3. Further if  it is a TCP segment then we are printing src,dest port numbers etc.
4. A sample of these outputs is given in the files pcap-op-arp-storm and pcap-op-tcp


PREFERRED PLATFORM: Linux.

