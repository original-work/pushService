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
	    //Java ���ǰ� byte �����з��������ǿ���ͨ������� 0xFF ���ж�������õ������޷�ֵ  
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
	 * ��һ��byte[]�����н�ȡһ����
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
