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

import com.db.api.JdbcCURD;

public class UDPServer{
	final static Logger log = Logger.getLogger(UDPServer.class);

	private DatagramPacket pack;
	private JdbcCURD curd;
	
	public UDPServer(){
		try{
			init();
			while(true){
				try {
					byte[] bufferIn=new byte[1024];
					pack=new DatagramPacket(bufferIn, bufferIn.length);
					receive(pack);
					new Thread(new Process(pack,curd)).start(); 
				}catch (IOException e) {
					e.printStackTrace();
				}
//				Thread.sleep(1 * 1000);  
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
        	log.error(e.toString());
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
        	log.error(e.toString());
        }  
    }  
    /** 
     * ��ʼ������ 
     * @throws SocketException 
     */  
    public void init(){  
        try {  
            socketAddress = new InetSocketAddress("localhost", 12345);  
            datagramSocket = new DatagramSocket(socketAddress);
            curd=new JdbcCURD();
            curd.init();
//            datagramSocket.setSoTimeout(5 * 1000);
            log.info("server has started");
        } catch (Exception e) {  
            datagramSocket = null;
            log.info("server start fail");
            log.error(e.toString());
        }  
    }  
    private static InetSocketAddress socketAddress = null; // �����������ַ  
    private static DatagramSocket datagramSocket = null; // ���Ӷ���  
	
	public static void main(String[] args){
		new UDPServer();
	}
	
}
