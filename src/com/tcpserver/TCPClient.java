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
	int port = 2345; // 端口号
	String host = "localhost"; // 服务器地址
	Socket socket; // 客户端套接字

	public TCPClient() {
		try {
			socket = new Socket(InetAddress.getByName(host), port); // 实例化套按字

			DataInputStream in = new DataInputStream(socket.getInputStream()); // 得到输入流
			
			DataOutputStream out = new DataOutputStream(
					socket.getOutputStream()); // 得到输出流

			out.write("ni shi tiancai".getBytes());
			out.flush();
			
			
			byte[] buffer = new byte[256]; // 缓冲区
			in.read(buffer); // 读入数据到缓冲区
			System.out.println(new String(buffer)); // 输出信息
			System.out.println("Connect Success!");
			socket.close(); // 关闭套接字
		} catch (IOException ex) {
			log.error(ex.toString()); // 输出错误信息
		}
	}

	public static void main(String[] args) {
		new TCPClient();
	}
}
