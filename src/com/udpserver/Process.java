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
	private JdbcCURD curd;

	
	public Process(DatagramPacket packet,JdbcCURD curd){  
        this.packet = packet;
        this.curd=curd;
	}
	
	public void run(){
		try {
			process(packet);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}
    
	public void process(DatagramPacket packet){
		byte[] data= packet.getData();
		int len=Utils.byteToInt(data[20]);
		if (log.isInfoEnabled()) {
			log.debug("len is: ".concat(Integer.toString(len)));
		}
		for(int i=24;i<(24+len/2+1);i++){
//			System.out.print(String.format("data[%d] is: 0x%02x\n", i, data[i]));
			if (log.isInfoEnabled()) {
				log.debug("data[".concat(Integer.toString(i)).concat("] is ").concat(Byte.toString(data[i])));
			}
		}
		byte[] mdn_bytes=Utils.subBytes(data,24,len/2+1);
		String mdn=BCD.BCDtoString(mdn_bytes,len);
		if (log.isInfoEnabled()) {
			log.info("mdn is: ".concat(mdn));
		}
		
        String sql = null;
        sql="select * from push_service where mdn=".concat(mdn);
        if (log.isInfoEnabled()) {
			log.debug("sql is ".concat(sql));
		}
        String token=curd.QueryMdn(sql);
        if (log.isInfoEnabled()) {
			log.info("token is "+token);
		}
        if(token!=null){
	        Aps aps=new Aps("您有来电","","查看");
			if (log.isInfoEnabled()) {
				log.debug("aps is ".concat(aps.toString()));
			}
			new PushService(aps,token);
			
			byte[] bt = new byte[packet.getLength()];  
	        System.arraycopy(packet.getData(), 0, bt, 0, packet.getLength());
	        if (log.isInfoEnabled()) {
				log.info(packet.getAddress().getHostAddress() + ": " + packet.getPort() + ": " + Arrays.toString(bt));
			}
        }		

	}
	
	
}
