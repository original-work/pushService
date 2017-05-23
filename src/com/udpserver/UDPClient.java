/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import org.apache.log4j.Logger;



public class UDPClient {
	final static Logger log = Logger.getLogger(UDPClient.class);
	private DatagramSocket sock;
	private DatagramPacket pack;
	private InetSocketAddress server;
	
	public UDPClient(){
		System.out.println("Client");
		try {
			sock=new DatagramSocket();
			server=new InetSocketAddress("localhost", 12345);
			byte[] data="prova".getBytes();
			pack=new DatagramPacket(data,data.length,server);
			sock.send(pack);
			sock.receive(pack);
			String res=new String(pack.getData());
			System.out.println(res);
		} catch (IOException e) {
			log.error(e.toString());
		}
	}
	
	
	public static void main(String[] args){
		new UDPClient();
	}
}
