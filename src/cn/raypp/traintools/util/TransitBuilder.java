package cn.raypp.traintools.util;

import cn.raypp.traintools.bean.DirectionsResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import org.json.JSONException;
import org.json.JSONObject;

public class TransitBuilder {
	public static final String GET_URL = "http://www.baidu.com/";

	private static String makeUrl(String origin, String destination, int time,
			boolean departure_time) throws UnsupportedEncodingException {
		String url = "http://maps.googleapis.com/maps/api/directions/json?";
		url += "origin=" + URLEncoder.encode(origin, "utf-8");
		url += "&destination=" + URLEncoder.encode(destination, "utf-8");
		if (departure_time) {
			url += "&departure_time=" + time;
		} else {
			url += "&arrival_time=" + time;
		}
		url+= "&region=cn";
		url+= "&mode=transit";
		url+= "&sensor=false";

		return url;
	}

	private static String getJson(String url) throws IOException {
		String json = "";
		
		//System.out.println(url);
		URL getUrl = new URL(url);
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.addRequestProperty("Accept-Language","zh-cn,zh;q=0.5");
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			json += lines+"\n";
		}
		System.out.println(json);
		reader.close();
		connection.disconnect();

		return json;
	}

	public static DirectionsResponse makeDirection(String json) throws JSONException {
		JSONObject jObject = new JSONObject(json);
		return null;
	}

	public static void main(String[] args) throws IOException, JSONException {
		String json = getJson(makeUrl("峨眉站", "北京西站", 1343605500, true));
		DirectionsResponse response = makeDirection(json);
	}
}
