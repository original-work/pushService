/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.tcpserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import org.apache.log4j.Logger;


public class TCPClient {
	final Logger log = Logger.getLogger(TCPClient.class);
	int port = 2345; // �˿ں�
	String host = "localhost"; // ��������ַ
	Socket socket; // �ͻ����׽���

	public TCPClient() {
		try {
			socket = new Socket(InetAddress.getByName(host), port); // ʵ�����װ���

			DataInputStream in = new DataInputStream(socket.getInputStream()); // �õ�������
			
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream()); // �õ������

			out.write("ni shi tiancai".getBytes());
			out.flush();
			
			
			byte[] buffer = new byte[256]; // ������
			in.read(buffer); // �������ݵ�������
			System.out.println(new String(buffer)); // �����Ϣ
			System.out.println("Connect Success!");
			socket.close(); // �ر��׽���
		} catch (IOException ex) {
			log.error(ex.toString()); // ���������Ϣ
		}
	}

	public static void main(String[] args) {
		new TCPClient();
	}
}
