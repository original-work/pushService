/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.push;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import com.notificationjson.Aps;

public class PushService {
	
	public PushService(Aps aps,String token){
		try {
//			PushNotificationPayload payload=PushNotificationPayload.fromJSON("{\"aps\":{\"alert\":{\"title\":\"I am title\",\"subtitle\":\"I am subtitle\",\"body\":\"I am body from java\"},\"sound\":\"default\",\"badge\":1}}");
			PushNotificationPayload payload=PushNotificationPayload.fromJSON(aps.toString());
//			Push.payload(payload, "ssl/push.p12", "shlj.123", false, "9d7444baab4fb48ed48844244a4b4acf01137bfdb6f5414bd7bebfc7997eba8e");
			Push.payload(payload, "ssl/push.p12", "shlj.123", false, token);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args) {
		String token="9d7444baab4fb48ed48844244a4b4acf01137bfdb6f5414bd7bebfc7997eba8e";
		Aps aps=new Aps("I am title","I am subtitle","I am body from java");
		System.out.println(aps.toString());
		new PushService(aps,token);
	}

}