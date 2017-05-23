/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.tcpserver;

import java.io.DataInputStream;
//import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

public class TCPServer {
	final Logger log = Logger.getLogger(TCPServer.class);
	int port = 2345; // �˿ں�
	ServerSocket serverSocket; // �������׽���

	public TCPServer() {
		try {
			serverSocket = new ServerSocket(port); // ʵ�����׽���
			System.out.println("start server at port " + port); // ������������ʾ��Ϣ

			while (true) { // һֱ�ȴ��ͻ�������
				Socket client = serverSocket.accept(); // �ȴ�����
				System.out.println("Connect: " + client.getInetAddress()); // ����ͻ�����ַ
				DataInputStream in = new DataInputStream(
						client.getInputStream()); // �õ�������
				
				byte[] buffer = new byte[256]; // ������
				in.read(buffer); // �������ݵ�������
				System.out.println(new String(buffer)); // �����Ϣ
				
				
				
				
				
//				DataOutputStream out = new DataOutputStream(
//						client.getOutputStream()); // �õ������
//
//				byte[] message = "TTTTT"
//						.getBytes(); // �����Ϣ�ַ�����
//				out.write(message, 0, message.length); // �����Ϣ
//				out.flush();
			}
		} catch (IOException ex) {
			log.error(ex.toString()); // ���������Ϣ
		}
	}

	public static void main(String[] args) {
		new TCPServer();
	}

}
