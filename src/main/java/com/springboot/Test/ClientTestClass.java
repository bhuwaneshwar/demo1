package com.springboot.Test;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class ClientTestClass {
	public static void main(String[] args) {
		 try {
		    DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost postRequest = new HttpPost("http://85.105.27.127:8086/Thingworx/Things/FireTrackHothinkDetectorMasterServices/Services/GetVirtualATEList");
	        postRequest.addHeader("Content-Type", "application/json");
	        postRequest.addHeader("accept", "application/json");
	        postRequest.addHeader("appKey", "92ed5615-2c66-4a4a-9b76-daf883256d06");
	        postRequest.addHeader("User-Agent", "Super Agent/0.0.1");
	          
	        HttpResponse response = httpClient.execute(postRequest);
	         
	        int statusCode = response.getStatusLine().getStatusCode();
	        System.out.println("statusCode="+statusCode);
	        if (statusCode != 200) {
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        }
	        
	        BasicManagedEntity httpEntity = (BasicManagedEntity) response.getEntity();
	        String apiOutput = EntityUtils.toString((org.apache.http.HttpEntity) httpEntity);
	         
	        JSONObject obj = new JSONObject(apiOutput);
	        
	        System.out.println("rows ==="+obj.get("rows"));
	        
	        JSONArray jsonarray = new JSONArray(obj.get("rows").toString());
	        for (int i = 0; i < jsonarray.length(); i++) {
	        	
	            JSONObject jsonobject = jsonarray.getJSONObject(i);
	            String name = jsonobject.getString("name");
	            System.out.println("name="+name);
	            
	        }
	        
	    }
		 catch(Exception e){
			 e.printStackTrace();
		 }

		 
	}
}
