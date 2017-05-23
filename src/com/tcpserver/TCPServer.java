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
	int port = 2345; // 端口号
	ServerSocket serverSocket; // 服务器套接字

	public TCPServer() {
		try {
			serverSocket = new ServerSocket(port); // 实例化套接字
			System.out.println("start server at port " + port); // 在命令窗口输出提示信息

			while (true) { // 一直等待客户端连接
				Socket client = serverSocket.accept(); // 等待连接
				System.out.println("Connect: " + client.getInetAddress()); // 输出客户机地址
				DataInputStream in = new DataInputStream(
						client.getInputStream()); // 得到输入流
				
				byte[] buffer = new byte[256]; // 缓冲区
				in.read(buffer); // 读入数据到缓冲区
				System.out.println(new String(buffer)); // 输出信息
				
				
				
				
				
//				DataOutputStream out = new DataOutputStream(
//						client.getOutputStream()); // 得到输出流
//
//				byte[] message = "TTTTT"
//						.getBytes(); // 输出信息字符数组
//				out.write(message, 0, message.length); // 输出信息
//				out.flush();
			}
		} catch (IOException ex) {
			log.error(ex.toString()); // 输出错误信息
		}
	}

	public static void main(String[] args) {
		new TCPServer();
	}

}
