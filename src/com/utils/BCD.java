/*  HISTORY:     NAME               DATE          REASON	 */
/*  ------     -------              ----          ------	 */
/*            WangXinXin          05/15/2017      Original	 */
package com.utils;

public class BCD {

//    public static byte[] DecimalToBCD(long num) {
//        int digits = 0;
//
//        long temp = num;
//        while (temp != 0) {
//            digits++;
//            temp /= 10;
//        }
//
//        int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
//
//        byte bcd[] = new byte[byteLen];
//
//        for (int i = 0; i < digits; i++) {
//            byte tmp = (byte) (num % 10);
//
//            if (i % 2 == 0) {
//                bcd[i / 2] = tmp;
//            } else {
//                bcd[i / 2] |= (byte) (tmp << 4);
//            }
//
//            num /= 10;
//        }
//
//        for (int i = 0; i < byteLen / 2; i++) {
//            byte tmp = bcd[i];
//            bcd[i] = bcd[byteLen - i - 1];
//            bcd[byteLen - i - 1] = tmp;
//        }
//
//        return bcd;
//    }
//
//    public static long BCDToDecimal(byte[] bcd) {
//        return Long.valueOf(BCD.BCDtoString(bcd));
//    }
    
    public static String BCDtoString(byte bcd,int len) {
        StringBuffer sb = new StringBuffer();
        
        byte high = (byte) (bcd & 0xf0);
        high >>>= (byte) 4;    
        high = (byte) (high & 0x0f);
        byte low = (byte) (bcd & 0x0f);
        
        sb.append(low);
        if(len>1){
        	sb.append(high);
        }
        return sb.toString();
    }
    
    public static String BCDtoString(byte[] bcd,int len) {
        StringBuffer sb = new StringBuffer();
    
        for (int i = 0; i < len/2+1; i++) {
            sb.append(BCDtoString(bcd[i],len-i*2));
        }
    
        return sb.toString();
    }
    
    public static void main(String[] args) {
    	byte[] byteArray = new byte[] {-127, 16, 57, -119, 54, -7};
    	String newVal = BCD.BCDtoString(byteArray,11);
        System.out.print(String.format("Testing: %s\n", newVal));
        String one=BCDtoString(byteArray[0],2);
        System.out.print(String.format("one: %s\n", one));
        String two=BCDtoString(byteArray[1],2);
        System.out.print(String.format("two: %s\n", two));
        String three=BCDtoString(byteArray[2],2);
        System.out.print(String.format("three: %s\n", three));
        String four=BCDtoString(byteArray[3],2);
        System.out.print(String.format("four: %s\n", four));
        String five=BCDtoString(byteArray[4],2);
        System.out.print(String.format("five: %s\n", five));
        String six=BCDtoString(byteArray[5],1);
        System.out.print(String.format("six: %s\n", six));
        
    }

}