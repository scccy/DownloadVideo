package com.scccy.videoDownloader.untils;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import org.apache.commons.lang3.RandomStringUtils;

import java.io.IOException;

@Slf4j
public class Aria2Util {

	private static OkHttpClient client = OkHttpClientFactory.createClient();

	public static String sendMessage(String url, JSONObject post) {
		// 创建请求体
		RequestBody body = RequestBody.create(
				post.toString(),
				MediaType.get("application/json; charset=utf-8")
		);

		// 创建请求
		Request request = new Request.Builder()
				.url(url)
				.post(body)
				.build();

		try (Response response = client.newCall(request).execute()) {
			int statusCode = response.code();
			String result = response.body() != null ? response.body().string() : null;

			log.info("Aria2 statusCode = " + statusCode);
			log.info("Aria2 result = " + result);

			if (statusCode == 200) {
				return result;
			}
		} catch (IOException e) {
			log.info("Aria2 无法连接---------"+e.getMessage()+"----");
			e.printStackTrace();
		}

		return null;
	}


	private static JSONObject createBaseParameter(String method, String token) {
		JSONObject obj = new JSONObject();
		obj.put("id", RandomStringUtils.randomNumeric(16));
		obj.put("jsonrpc", "2.0");
		obj.put("method", method);

		JSONArray params = new JSONArray();
		if (token != null) {
			params.add("token:" + token);
		}
		obj.put("params", params);
		return obj;
	}

	private static JSONObject createDownloadConfig(String downpath, String downclass, String userAgent, String referer, String cookie) {
		JSONObject config = new JSONObject();
		config.put("dir", downpath);
		config.put("out", downclass);
		config.put("User-Agent", userAgent);
		config.put("referer", referer);
		if (cookie != null) {
			config.put("cookie", cookie);
		}
		return config;
	}

	public static JSONObject createBiliParameter(String downlink, String downpath, String downclass, String token) {
		JSONObject obj = createBaseParameter("aria2.addUri", token);

		JSONArray params = obj.getJSONArray("params");
		JSONArray downLinkArray = new JSONArray();
		downLinkArray.add(downlink);
		params.add(downLinkArray);

		JSONObject config = createDownloadConfig(
				downpath,
				downclass,
				"Mozilla/5.0 BiliDroid/7.42.0 (bbcallen@gmail.com)",
				"https://www.bilibili.com",
				null
		);
		params.add(config);

		return obj;
	}

	public static JSONObject createDyParameter(String downlink, String downpath, String downclass, String token, String cookie) {
		JSONObject obj = createBaseParameter("aria2.addUri", token);

		JSONArray params = obj.getJSONArray("params");
		JSONArray downLinkArray = new JSONArray();
		downLinkArray.add(downlink);
		params.add(downLinkArray);

		JSONObject config = createDownloadConfig(
				downpath,
				downclass,
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/104.0.0.0 Safari/537.36",
				"*",
				cookie
		);
		params.add(config);

		return obj;
	}

	public static JSONObject createParameter(String downlink, String downpath, String downclass, String token) {
		JSONObject obj = createBaseParameter("aria2.addUri", token);

		JSONArray params = obj.getJSONArray("params");
		JSONArray downLinkArray = new JSONArray();
		downLinkArray.add(downlink);
		params.add(downLinkArray);

		JSONObject config = createDownloadConfig(
				downpath,
				downclass,
				null,
				"*",
				null
		);
		params.add(config);

		return obj;
	}

	public static JSONObject createTaskStatus(String taskid, String token) {
		JSONObject obj = createBaseParameter("aria2.tellStatus", token);

		JSONArray params = obj.getJSONArray("params");
		params.add(taskid);

		JSONArray fields = new JSONArray();
		fields.add("status");
		fields.add("totalLength");
		fields.add("completedLength");
		params.add(fields);

		JSONObject config = new JSONObject();
		config.put("referer", "*");
		params.add(config);

		return obj;
	}
	public static void main(String[] args) {
		JSONObject createparameter = Aria2Util.createParameter("https://pan.mdreamworld.cn/api/raw/?path=/环境安装包/AdobeAIRInstaller.exe", "D:\\aria2\\down\\xxxx", "AdobeAIRInstaller.exe","123456");
		Object sendMessage = Aria2Util.sendMessage("http://localhost:6800/jsonrpc", createparameter);
		System.out.println(sendMessage);
	}

}
