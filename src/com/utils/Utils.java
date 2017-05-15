/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.utils;

public class Utils {
	public static String byte2hex(byte [] buffer){  
	    String h = "";  
	      
	    for(int i = 0; i < buffer.length; i++){  
	        String temp = Integer.toHexString(buffer[i] & 0xFF);  
	        if(temp.length() == 1){  
	            temp = "0" + temp;  
	        }  
	        h = h + " "+ temp;  
	    }  
	      
	    return h;  
	      
	}

	public static byte intToByte(int x) {  
	    return (byte) x;  
	}  
	  
	public static int byteToInt(byte b) {  
	    //Java 总是把 byte 当做有符处理；我们可以通过将其和 0xFF 进行二进制与得到它的无符值  
	    return b & 0xFF;  
	}

	public static String byteArrayToStr(byte[] byteArray) {
	    if (byteArray == null) {
	        return null;
	    }
	    String str = new String(byteArray);
	    return str;
	}

	 /**
	 * 从一个byte[]数组中截取一部分
	 * @param src
	 * @param begin
	 * @param count
	 * @return
	 */
	public static byte[] subBytes(byte[] src, int begin, int count) {
	    byte[] bs = new byte[count];
	    for (int i=begin;i<begin+count; i++) bs[i-begin] = src[i];
	    return bs;
	}

}
