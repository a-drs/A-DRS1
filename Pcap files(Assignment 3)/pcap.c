#include <stdio.h>
#include <pcap.h>
#include <stdlib.h>
#include <netinet/ip.h>
#include <arpa/inet.h>
#include <net/if_arp.h>
#include <netinet/tcp.h>



int main(int argc, char *argv[]) 
{ 

   int flag,packet_length; 	
  unsigned int nos_of_packets=0;   // number of packets
  unsigned long bytes=0; //total bytes 
 
   
 
  struct pcap_pkthdr header; // pcap header
  const u_char *packet; // this will have the actual packet contents 
  
  //pass file through command line
  if (argc < 2) { 
    printf("\nprovide the file name");; 
    exit(1); 
  } 
  
    

    pcap_t *handle; //handle for a file
    char errbuf[PCAP_ERRBUF_SIZE]; 
    handle = pcap_open_offline(argv[1], errbuf);   //open the file 
 
    if (handle == NULL) { 
		printf("error");
     
    }
 
    while (packet = pcap_next(handle,&header)) 
    { 
 
      u_char *packet_ptr = (u_char *)packet; 
      
      //ethernetframe
      int ether_frame_type = ((int)(packet_ptr[12]) << 8) | (int)packet_ptr[13]; 
      int ether_offset = 0; 
	
 
      if (ether_frame_type == 0x0800) //ip packet
	{ 
                ether_offset = 14;
		printf("\nIP packet:\n"); 
	}
      else if (ether_frame_type == 0x0806) //arp packet
		{
		    printf("\nARP packet");
                    ether_offset = 14;
		flag=1;

                } 
      else if(ether_frame_type == 0x0835)  //rarp packet
		printf("\nRARP packet");
	  else
          printf("Unknown ethernet type, %04X,\n", ether_frame_type); 
 
      

       if(flag)
	{
		packet_ptr +=ether_offset;

		struct arphdr *arp_hdr=(struct arphdr *)packet_ptr;
		packet_length=46;

		
		printf("\nFormat of Hardware Address : %d",(int)(ntohs(arp_hdr->ar_hrd)));
		printf("\nFormat of Protocol Address : %d",(int)(ntohs(arp_hdr->ar_pro)));
		printf("\nARP Operation : %d",(int)(ntohs(arp_hdr->ar_op)));
		
		printf("\nLength of Hardware Address : %d",(int)(0x0FF00 & (ntohs(arp_hdr->ar_hln)))>>8);

		printf("\nLength of Protocol Address : %d",(int)(0x0FF00 & (ntohs(arp_hdr->ar_pln)))>>8);



		flag=0;
	} 
	else
	{

      packet_ptr += ether_offset;  //skip past the Ethernet II header 
      struct ip *ip_hdr = (struct ip *)packet_ptr; //point to an IP header structure 
      	
 
     char temp=* packet_ptr;
     
      int size_ip=(int)temp & 0x0f;
	size_ip=size_ip*4;

       packet_length = ntohs(ip_hdr->ip_len);
       int upper_layer;	
        temp=* packet_ptr; 
	printf("\nversion : %d\n",(u_char)((temp & 0x0f0)>>4));
	printf("Type of service: %d\n",(int)(0x000F & ntohs(ip_hdr->ip_tos)));
       printf("packet length: %d (bytes)\n",packet_length);
	printf("IP header length :%d\n",size_ip);
	
        //not very uselful info
	printf("packet ID: %d\n",(int)ntohs(ip_hdr->ip_id));
	printf("packet offset: %d\n",(int) ntohs(ip_hdr->ip_off));
	

	upper_layer=(int)((0xFF00  & ntohs(ip_hdr->ip_p)) >> 8);
	printf("Upper Layer Protocol: %d\n",(int)((0xFF00  & ntohs(ip_hdr->ip_p)) >> 8));
	
	
	//dest src ips

	
	printf("source IP : %d.%d.%d.%d\n",(int)(ip_hdr->ip_src.s_addr&0xFF),(int)((ip_hdr->ip_src.s_addr&0xFF00)>>8),
        (int)((ip_hdr->ip_src.s_addr&0xFF0000)>>16),
           (int)((ip_hdr->ip_src.s_addr&0xFF000000)>>24));

	
	printf("destination IP : %d.%d.%d.%d\n",(int)(ip_hdr->ip_dst.s_addr&0xFF),(int)((ip_hdr->ip_dst.s_addr&0xFF00)>>8),
        (int)((ip_hdr->ip_dst.s_addr&0xFF0000)>>16),
           (int)((ip_hdr->ip_dst.s_addr&0xFF000000)>>24));
		
	
	if(upper_layer==6)  //tcp segment
	{
		packet_ptr+=size_ip;
		struct tcphdr * tcp_hdr = (struct tcphdr *)packet_ptr;
		printf("\n\tdumping TCP segment:\n");
		
		printf("\tSource port number : %d \n", (int)ntohs( tcp_hdr-> source));
	
		printf("\tDestination port number : %d \n", (int)ntohs( tcp_hdr-> dest));

		printf("\tSequence number (HEX): %x\n",(unsigned int)ntohl( tcp_hdr-> seq));//neg acks,seq
		printf("\tACK number (HEX): %x \n",(unsigned int) ntohl( tcp_hdr->ack));  //problem here

		printf("\tReceiver Window Size : %d \n", (int)ntohs( tcp_hdr-> window));
		printf("\tcheckSum : %d \n", (int)ntohs( tcp_hdr-> check));
		printf("\tUrgent pointer : %d \n", (int)ntohs( tcp_hdr-> urg_ptr));
	}	
	
 
	}      
       
      bytes += packet_length; 
      nos_of_packets++; 
 
    }  
 pcap_close(handle);  //close the pcap file 
   
    bytes /= 0x1e6;
	 
  printf("\nNumber of packets read : %d packets and total size : %u Mbs\n", nos_of_packets, bytes);
 
  
     

  return 0;
} 

