package com.scccy.videoDownloader.untils;


import jakarta.servlet.http.HttpServletRequest;

/**

* <p>Title: StringUtil</p>  

* <p>Description:字符串工具类 </p>  

* @author QingFeng  

* @date 2020年8月14日  

*/  
public class StringUtil {
	
	/**  
	
	 * <p>Title: isString</p>  
	
	 * <p>Description:判断是否是有效的字符串 </p>  
	
	 * @param str
	 * @return  
	
	 */  
	public static boolean isString(String str) {
		if( null == str  ||str.trim().equals("")) {
			return false;
		}
		return true;
	}
	
	/**
	 *处理 特殊字符串 并返回名称
	 * @param `name`
	 * @return
	 */
	 public static String getFileName(String obj,String aid) {
		
		 try {
			 if(obj.trim().equals("") || obj.length() == 0) {
				 return aid;
			 }
//			 String reSub = "[^\\u4e00-\\u9fa5^a-z^A-Z^0-9^#]";
			 String reSub = "[^A-Za-z0-9\\u4e00-\\u9fa5]";
		     if (obj.length() > 64) {
		         obj = obj.substring(0, 64);
		     }
		     return obj.replaceAll(reSub, "_");
		} catch (Exception e) {
			return aid;
		}
	 }
	 
		public static String getRemoteAddr(HttpServletRequest request){
			String remoteAddr = request.getHeader("X-Real-IP");
	        if (isString(remoteAddr)) {
	        	remoteAddr = request.getHeader("X-Forwarded-For");
	        }else if (isString(remoteAddr)) {
	        	remoteAddr = request.getHeader("Proxy-Client-IP");
	        }else if (isString(remoteAddr)) {
	        	remoteAddr = request.getHeader("WL-Proxy-Client-IP");
	        }
	        return remoteAddr != null ? remoteAddr : request.getRemoteAddr();
		}
	 
	 
	 
	 public static void main(String[] args) {
		 System.out.println(StringUtil.getFileName("反恐精英1.6 #动画制作 #游戏 #反恐精英 #童年回忆", "123"));
	}
}
