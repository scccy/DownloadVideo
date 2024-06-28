package com.scccy.videobase.pojo;

import org.springframework.beans.factory.annotation.Value;

/**

* <p>Title: Global</p>  

* <p>Description: 全局变量</p>  

* @author QingFeng  

* @date 2020年8月14日  

*/  
public class Global {
//	下载视频保存地址
	public static String SAVE_FILE;
//	上传服务器地址
	public static String UPLOAD_PATH;
	public static String USER_SESSION_KEY="user_login_session";
	
	public static String AJAX_SUCCESS ="000001";
	
	public static String AJAX_URI_ERROR ="999998";
	
	public static String AJAX_LOGIN_ERR ="999997";
	
	public static String AJAX_LOGIN_SUCCESS_MESSAGE ="登录成功";
	
	public static String AJAX_LOGIN_ERR_MESSAGE ="您的账号或密码输入错误,请重新输入";
	
	public static String AJAX_URI_ERROR_MESSAGE ="您的参数不完整,请检查提交参数";

	public static String AJAX_ADD_USER_ERR ="999996";

	public static String AJAX_ADD_USER_ERR_MESSAGE ="添加用户失败,用户已存在";

	public static String AJAX_ADD_USER_SUCCESS_MESSAGE ="用户添加成功";
	
	
	public static String AJAX_OPTION_SUCCESS ="操作成功";
	

	
	public static String AJAX_NAV_NO_RULE ="333333";
	
	public static String AJAX_NAV_NO_RULE_MESSAGE ="未对外开放";
	
	public static String DOWN_TYPE ="http";
	
	public static String A2_LINK ="http://localhost:6800/jsonrpc";
	
	public static String A2_TOKEN="123456";
	
	public static String DOWN_PATH="/app/resources";

	
	public static String APP_TOKEN="123456";
	
	
	public static String BILI_COOKIES ="";
	
	public static boolean BILI_MEMBER =false;
	
	public static String BILI_BITSTREAM ="64";
	
	public static boolean OPEN_PROCESSHI_STORY = false;
	
	public static String tiktokCookie="";

	public static String Douyin_Cookie="";

	public static String ANALY_SIS_SERVER="https://spirit.lifeer.xyz";
	
	public static String WALL_PAPER_ID ="431960";
	
	public static String YOUTUBE_COOKIES="";

	public static String TWITTER_COOKIES="";

	public static enum platform{
		bilibili,
		douyin,
		tiktok,
		youtube,
		instagram,
		twitter;
	}

}
