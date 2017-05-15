/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.notificationjson;


public class Aps {
	private Alert alert;
	private String sound;
	private String badge;
	
	public Aps(String title,String subtitle,String body){
		this.alert=new Alert(title,subtitle,body);
		this.sound="default";
		this.badge="1";
	}
	
	public String toString() {
		return "{\"aps\":{\"alert\":{\"title\":\""+alert.getTitle()+"\",\"subtitle\":\""+alert.getSubtitle()+"\",\"body\":\""+alert.getBody()+"\"},\"sound\":\""+sound+"\",\"badge\":"+badge+"}}";
	}
}