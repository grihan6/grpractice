package com.api;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class apilove {

	public static void main(String[] args) {

		System.out.println("hi");
		
		HttpClient httpClient = new DefaultHttpClient();
	    try {
	      
	      HttpGet httpGetRequest = new HttpGet("http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=1");
	 
	      // Execute HTTP request
	      HttpResponse httpResponse = httpClient.execute(httpGetRequest);
	 
	      System.out.println("----------------------------------------");
	      System.out.println(httpResponse.getStatusLine());
	      System.out.println("----------------------------------------");
	 
	      // Get hold of the response entity
	      HttpEntity entity = httpResponse.getEntity();
	      
	      byte[] buffer = new byte[1024];
	      if (entity != null) {
	        InputStream inputStream = entity.getContent();
	        try {
	          int bytesRead = 0;
	          BufferedInputStream bis = new BufferedInputStream(inputStream);
	          while ((bytesRead = bis.read(buffer)) != -1) {
	            String chunk = new String(buffer, 0, bytesRead);
	            System.out.println(chunk);
	          }
	        } catch (IOException ioException) {
	          ioException.printStackTrace();
	        } catch (RuntimeException runtimeException) {
	          httpGetRequest.abort();
	          runtimeException.printStackTrace();
	        } finally {
	          try {
	            inputStream.close();
	          } catch (Exception ignore) {
	          }
	        }
	      }
	    } catch (ClientProtocolException e) {
	      e.printStackTrace();
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      httpClient.getConnectionManager().shutdown();
	    }
		
		
	}

}
