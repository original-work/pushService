/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.notificationjson;

public class Alert {
	private String title;
	private String subtitle;
	private String body;

	
	public Alert(String title,String subtitle,String body){
		this.title=title;
		this.subtitle=subtitle;
		this.body=body;
	}
	
	
	public void setTitle(String title) {
		this.title=title;
	}
	public String getTitle() {
		return title;
	}
	
	public void setSubtitle(String subtitle) {
		this.subtitle=subtitle;
	}
	public String getSubtitle() {
		return subtitle;
	}
	
	public void setBody(String body) {
		this.body=body;
	}
	public String getBody() {
		return body;
	}

}