package com.api;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Class that solves below problem
 * 
 * Call http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=1
 * To go to next page of response, have to increment the page number in the url. As long as the "more" field returns true, have more data available.
 * The response is a JSON object which has a response key which is an array of more JSON objects. 
 * Each of them has a key called flags and within flags there is a key called hd.
 * Print out how many response objects have flags:hd set to true and how many have hd set to false.
 * 
 * @author grihan
 *
 */
public class apilove {

	// Original URL fetches 10 records per page
	// This has be modified to fetch 50 records per page
	public static final String VIKIURL = "http://api.viki.io/v4/videos.json?app=100250a&per_page=50&page=";
	
	public static void main(String[] args) {

		// Initiate variables
    	int pagectr=1, ctrHDtrue=0, ctrHDfalse=0;
    	String responseString = "", url="";
    	boolean flagHasMore = true;
    	JSONObject jsonObj = new JSONObject();
    	JSONArray jsonArr = new JSONArray();
    	apiUtil objUtil = new apiUtil();
    	
    	// Run till response has "more": true
    	while(flagHasMore){

			url = VIKIURL + pagectr;
			
			responseString = objUtil.hitGetAPI(url);
			jsonObj = new JSONObject(responseString);
			jsonArr = jsonObj.getJSONArray("response");
			  
			for(int i=0; i<jsonArr.length(); i++){
				if(objUtil.fetchValueOfHD(jsonArr, i).equalsIgnoreCase("true")){
					ctrHDtrue++;
				}else{
					ctrHDfalse++;
				}
			  }
			  
			  pagectr++;
			  flagHasMore = objUtil.fetchValueOfMore(jsonObj);
	    	}
		      
	    System.out.println("Number of hd:true = "+ ctrHDtrue + " || Number of hd:false = " + ctrHDfalse);

	}

}
