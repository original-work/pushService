/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.udpserver;

import java.net.DatagramPacket;
import java.util.Arrays;

import org.apache.log4j.Logger;
import com.db.api.JdbcCURD;
import com.notificationjson.Aps;
import com.push.PushService;
import com.utils.*;

public class Process implements Runnable {
	final Logger log = Logger.getLogger(Process.class);
	
	private DatagramPacket packet;

	
	public Process(DatagramPacket packet){  
        this.packet = packet;
	}
	
	public void run(){
		try {
			process(packet);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
	public void process(DatagramPacket packet){
		byte[] data= packet.getData();
		int len=Utils.byteToInt(data[20]);
		System.out.print(String.format("len is: %d\n", len));
		if (log.isInfoEnabled()) {
			log.info("len is: ".concat(Integer.toString(len)));
		}
		for(int i=24;i<(24+len/2+1);i++){
			System.out.print(String.format("data[%d] is: 0x%02x\n", i, data[i]));
			if (log.isInfoEnabled()) {
				log.info("data[".concat(Integer.toString(i)).concat("] is ").concat(Byte.toString(data[i])));
			}
		}
		byte[] mdn_bytes=Utils.subBytes(data,24,len/2+1);
		String mdn=BCD.BCDtoString(mdn_bytes,len);
		System.out.print(String.format("mdn is: %s\n", mdn));
		if (log.isInfoEnabled()) {
			log.info("mdn is: ".concat(mdn));
		}
		
		JdbcCURD curd=new JdbcCURD();
        String sql = null;
        sql="select * from push_service where mdn=".concat(mdn);
        System.out.print(String.format("sql is: %s\n", sql));
        if (log.isInfoEnabled()) {
			log.info("sql is ".concat(sql));
		}
        String token=curd.QueryMdn(sql);
        System.out.print(String.format("token is: %s\n", token));
        if (log.isInfoEnabled()) {
			log.info("token is ".concat(token));
		}
        
        Aps aps=new Aps("I am title","I am subtitle","I am body from java");
		System.out.println(aps.toString());
		if (log.isInfoEnabled()) {
			log.info("aps is ".concat(aps.toString()));
		}
		new PushService(aps,token);
		
		
		
		byte[] bt = new byte[packet.getLength()];  
        System.arraycopy(packet.getData(), 0, bt, 0, packet.getLength());  
        System.out.println(packet.getAddress().getHostAddress() + ": " + packet.getPort() + ": " + Arrays.toString(bt));
        if (log.isInfoEnabled()) {
			log.info(packet.getAddress().getHostAddress() + ": " + packet.getPort() + ": " + Arrays.toString(bt));
		}
//        try {
//			Thread.sleep(5 * 1000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} // 5秒才返回，标识服务端在处理数据  
//        // 设置回复的数据，原数据返回，以便客户端知道是那个客户端发送的数据  
//        packet.setData(bt);  
//		UDPServer.response(packet); 
		

	}
	
	
}
