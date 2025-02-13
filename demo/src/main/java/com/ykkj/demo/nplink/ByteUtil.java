package com.ykkj.demo.nplink;

public class ByteUtil {
	
	public static String bytes2HexString(byte[] b) {  
		return bytes2HexString(b,true);
	}
	
	public static String bytes2HexString(byte[] b,boolean isBlank) {  
	    StringBuilder ret = new StringBuilder();  
	    for (int i = 0; i < b.length; i++) {  
	        String hex = Integer.toHexString(b[ i ] & 0xFF);  
	    if (hex.length() == 1) {  
	        hex = '0' + hex;  
	    } 
	    if(ret.length()>0&&isBlank)
	    {
	    	ret.append(" ");
	    }
	    ret.append(hex.toUpperCase());  
	  }  
	  return ret.toString();  
	}  
	
	public static String bytes2HexString(byte b) {  
	    String hex = Integer.toHexString(b & 0xFF);  
	    if (hex.length() == 1) {  
	        hex = '0' + hex;  
	    }  
	  
	   
	  return hex.toUpperCase();  
	} 
	
	public static byte[] hexStringToBytes(String hexString) {   
	    if (hexString == null || hexString.equals("")) {   
	        return null;   
	    }  
	    
	    hexString = hexString.replaceAll(" ", "").toUpperCase();   
	    int length = hexString.length() / 2;   
	    char[] hexChars = hexString.toCharArray();   
	    byte[] d = new byte[length];   
	    for (int i = 0; i < length; i++) {   
	        int pos = i * 2;   
	        d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));   
	    }   
	    return d;   
	}   
	
	public static int bytes2int(byte b) {  
	    return (b & 0xFF);  
	   
	}  
	public static int getHexStringIntValue(String hexString)
	{
		return getByteIntValue(hexStringToBytes(hexString)); 
	}
	public static int getByteIntValue(byte[] value)
	{
		int ret=0;
		for(int i=0;i<value.length;i++)
		{
			ret+=Math.pow(256, value.length-i-1)*(value[i]& 0xFF);
		}
		return ret;
	}
	/**  
	 * Convert char to byte  
	 * @param c char  
	 * @return byte  
	 */  
	 private static byte charToByte(char c) {   
	    return (byte) "0123456789ABCDEF".indexOf(c);   
	}  
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
