#include <stdio.h> 
#include <stdlib.h>
#include <unistd.h>
#include <string.h>
#include <sys/types.h> 
#include <sys/socket.h> 
#include <netinet/in.h> 
#include <netdb.h>



#define PORT 50502
#define MAXDATASIZE 100


#define MIN_LEN  5            
#define GT_LEN   8            
#define MDN_LEN  10           
#define MSU_MAX_LEN 300   
#define UINT8 unsigned char 

typedef struct
{
			UINT8   d_dn_len;
			UINT8   d_dn_pro;
			UINT8   d_dn_res[2];
			UINT8   d_dn[16];   //tldn号码，这个是采用bcd方式进行存储
			UINT8   d_chd_len;
			UINT8   d_chd_pro;
			UINT8   d_chd_res[2];
			UINT8   d_chd_num[16];   //原来的号码
			UINT8   d_res[40];
} NIF_TLDN_ALLOC;


bool StrToBCD(const char *Src,unsigned char *Des,int iDesLen)
{
	if (NULL == Src)
	{
		return false;
	}
	if (NULL == Des)
	{
		return false;
	}
	if (0 == iDesLen)
	{
		return false;
	}
	int iSrcLen = strlen(Src);
	if(iSrcLen==0)
	{
		memset(Des,0xFF,8);
		return true;
	}
	if (iSrcLen > iDesLen * 2)
	{
		return false;
	}
	unsigned char chTemp = 0;
	int i;
	for (i = 0; i < iSrcLen; i++)
	{
		if (i % 2 == 0)
		{
			chTemp = (Src[i]-'0'); // << 4) & 0xF0;
		}
		else
		{
			chTemp = chTemp | (((Src[i]-'0')<<4) & 0xF0);
			Des[i / 2] = chTemp;
		}
	}
	if (i % 2 != 0)
	{
		Des[i / 2] = (chTemp|0xf0);
	}
	return true;
}


//char buf[]=  "1111";


int main(int argc, char *argv[]) 
{ 

	int sockfd, num; 
	struct hostent *he;
	struct sockaddr_in server,peer;
	char buf[256];
	char mdn[]="18019398639";
	NIF_TLDN_ALLOC st;
	memset((void*)(&st),0,sizeof(st));
	StrToBCD(mdn,st.d_chd_num,sizeof(st.d_chd_num));
	st.d_chd_len=11;
	memcpy(buf,(void*)(&st),sizeof(st));

	printf("sizeof(unsigned short)==%d\n",sizeof(unsigned short));

	if(argc !=3) 
	{
		printf("Usage: %s <IP PORT>\n",argv[0]); 
		exit(1); 
	} 


	if((he=gethostbyname(argv[1]))==NULL)
	{
		printf("gethostbyname()error\n"); 
		exit(1); 
	} 

	if((sockfd=socket(AF_INET, SOCK_DGRAM,0))==-1)
	{
		printf("socket() error\n"); 
		exit(1); 
	} 
	printf("argv[0] is %s argv[1] is %s argv[2] is %s\n",argv[0],argv[1],argv[2]);
	int port=atoi(argv[2]);
	printf("port is %u\n",port);
	bzero(&server,sizeof(server));
	server.sin_family = AF_INET; 
	server.sin_port = htons(port);
	server.sin_addr= *((struct in_addr *)he->h_addr);

	//while(1)
	{
		sendto(sockfd, buf,sizeof(buf),0,(struct sockaddr *)&server,sizeof(server)); 
	}

	
	socklen_t  addrlen;

	addrlen=sizeof(server);




	while (1) 
	{
		if((num=recvfrom(sockfd,buf,MAXDATASIZE,0,(struct sockaddr *)&peer,&addrlen))== -1)
		{
			printf("recvfrom() error\n"); 
			exit(1); 
		} 

		if (addrlen != sizeof(server) ||memcmp((const void *)&server, (const void *)&peer,addrlen) != 0) 
		{
			printf("Receive message from otherserver.\n");
			continue;
		}

		buf[num]='\0';
		printf("Server Message:%s\n",buf);
		break;
	}
	close(sockfd);
}


