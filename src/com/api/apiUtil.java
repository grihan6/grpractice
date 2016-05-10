package com.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class to host API Utils
 * 
 * @author grihan
 *
 */
public class apiUtil {

	/**
	 * fetchValueOfMore
	 * 	:: Function to know if there exists more records
	 * 
	 * @param jsonObj
	 * @return
	 */
	public boolean fetchValueOfMore(JSONObject jsonObj){
		boolean flagHasMore=true;
		if(jsonObj.get("more").toString().equalsIgnoreCase("false")){
			flagHasMore=false;
		}
		return flagHasMore; 
	}
	
	/**
	 * fetchValueOfHD
	 * 	:: Function to fetch value of key "hd"
	 * 
	 * @param jsonArr
	 * @param counter
	 * @return String
	 */
	public String fetchValueOfHD(JSONArray jsonArr, int counter){
		return jsonArr.getJSONObject(counter).getJSONObject("flags").get("hd").toString();
	}
	
	/**
	 * hitGetAPI
	 * 	:: Function to hit GET API
	 * 
	 * @param url
	 * @return String
	 */
	public String hitGetAPI(String url){
		
		String response="";
		CloseableHttpClient httpclient= HttpClientBuilder.create().build();
		HttpGet httpGetRequest = new HttpGet(url);
		
		try {
			  // Execute HTTP request
			  HttpResponse httpResponse = httpclient.execute(httpGetRequest);
 
			  System.out.println("----------------------------------------");
			  System.out.println("GET " + url);
			  System.out.println(httpResponse.getStatusLine());
			  System.out.println("----------------------------------------");
			  
			  // Received Response
			  response = EntityUtils.toString(httpResponse.getEntity());
			  
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				httpclient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return response;
	}
}
