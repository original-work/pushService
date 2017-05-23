/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.push;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import org.apache.log4j.Logger;
import com.notificationjson.Aps;

public class PushService {
	final Logger log = Logger.getLogger(PushService.class);
	
	public PushService(Aps aps,String token){
		try {
//			PushNotificationPayload payload=PushNotificationPayload.fromJSON("{\"aps\":{\"alert\":{\"title\":\"I am title\",\"subtitle\":\"I am subtitle\",\"body\":\"I am body from java\"},\"sound\":\"default\",\"badge\":1}}");
			PushNotificationPayload payload=PushNotificationPayload.fromJSON(aps.toString());
//			Push.payload(payload, "ssl/push.p12", "shlj.123", false, "9d7444baab4fb48ed48844244a4b4acf01137bfdb6f5414bd7bebfc7997eba8e");
			Push.payload(payload, "/home/ssl/push.p12", "shlj.123", false, token);
		} catch (Exception e) {
			log.error(e.toString());
		}
	}	
	
	public static void main(String[] args) {
		String token="9d7444baab4fb48ed48844244a4b4acf01137bfdb6f5414bd7bebfc7997eba8e";
		Aps aps=new Aps("I am title","I am subtitle","I am body from java");
		System.out.println(aps.toString());
		new PushService(aps,token);
	}

}