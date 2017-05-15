/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.udpserver;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

import org.apache.log4j.Logger;

public class UDPServer{
	final Logger log = Logger.getLogger(UDPServer.class);

	private DatagramPacket pack;
	
	public UDPServer(){
		try{
			init();
			while(true){
				try {
					byte[] bufferIn=new byte[1024];
					pack=new DatagramPacket(bufferIn, bufferIn.length);
					receive(pack);
					new Thread(new Process(pack)).start(); 
				}catch (IOException e) {
					e.printStackTrace();
				}
				Thread.sleep(1 * 1000);  
			}
		}catch (Exception e) {  
            e.printStackTrace();  
        }
	}
	
	/** 
     * �������ݰ����÷���������߳����� 
     * @return 
     * @throws Exception  
     * @throws IOException 
     */  
    public static DatagramPacket receive(DatagramPacket packet) throws Exception {  
        try {  
            datagramSocket.receive(packet);  
            return packet;  
        } catch (Exception e) {  
            throw e;  
        }  
    }  
    /** 
     * ����Ӧ�����͸������ 
     * @param bt 
     * @throws IOException 
     */  
    public static void response(DatagramPacket packet) {  
        try {  
            datagramSocket.send(packet);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
    /** 
     * ��ʼ������ 
     * @throws SocketException 
     */  
    public static void init(){  
        try {  
            socketAddress = new InetSocketAddress("localhost", 12345);  
            datagramSocket = new DatagramSocket(socketAddress);  
//            datagramSocket.setSoTimeout(5 * 1000);  
            System.out.println("server has started");  
        } catch (Exception e) {  
            datagramSocket = null;  
            System.err.println("server start fail");  
            e.printStackTrace();  
        }  
    }  
    private static InetSocketAddress socketAddress = null; // �����������ַ  
    private static DatagramSocket datagramSocket = null; // ���Ӷ���  
	
	public static void main(String[] args){
		new UDPServer();
	}
	
}
