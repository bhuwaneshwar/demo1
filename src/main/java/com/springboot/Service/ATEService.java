package com.springboot.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.BasicManagedEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.springboot.Model.ATEPojo;
import com.springboot.Model.DevicePojo;

@Service
public class ATEService {	
	
	@Value("${virtualATEListURL}")
	private String virtualATEListURL;
	
	@Value("${contentType}")
	private String contentType;
	
	@Value("${accept}")
	private String accept;
	
	@Value("${appKey}")
	private String appKey;
	
	@Value("${userAgent}")
	private String userAgent;
	
	
	public HashMap getDeviceType(){
		HashMap<String, String> hm = new HashMap();
		hm.put("TMP", "Heat Sensor");
		hm.put("SMK", "Smoke Sensor");
		return hm;
	}
	
	/*
	 * This service return list of ATE Device
	 */
	public List<String> getATEList(){
		
		List<String> ateList = new ArrayList<>();
		try {
		    DefaultHttpClient httpClient = new DefaultHttpClient();
	        HttpPost postRequest = new HttpPost(virtualATEListURL);
	        postRequest.addHeader("Content-Type", contentType);
	        postRequest.addHeader("accept", accept);
	        postRequest.addHeader("appKey", appKey);
	        postRequest.addHeader("User-Agent", userAgent);
	        HttpResponse response = httpClient.execute(postRequest);
	         
	        int statusCode = response.getStatusLine().getStatusCode();
	        //System.out.println("In getATEList statusCode="+statusCode);
	        if (statusCode != 200)
	            throw new RuntimeException("Failed with HTTP error code : " + statusCode);
	        
	        BasicManagedEntity httpEntity = (BasicManagedEntity) response.getEntity();
	        String apiOutput = EntityUtils.toString((org.apache.http.HttpEntity) httpEntity);
	        JSONObject obj = new JSONObject(apiOutput);
	        
	        JSONArray jsonarray = new JSONArray(obj.get("rows").toString());
	        for (int i = 0; i < jsonarray.length(); i++) {
	        	
	            JSONObject jsonobject = jsonarray.getJSONObject(i);
	            String name = jsonobject.getString("name");
	            ateList.add(name);
	        }
	    }
		 catch(Exception e){
			 e.printStackTrace();
		 }
		return ateList;
	}
	
	
	/*
	 * This service return device attribute
	 */
	public DevicePojo getDeviceAttribute(String ateName,  String sensorIMEI){
		
		JSONArray jsonarray = getDeviceDetailsByList(ateName);
		DevicePojo device = new DevicePojo();
		for (int i = 0; i < jsonarray.length(); i++) {
		 try {
			  JSONObject jsonobject = jsonarray.getJSONObject(i);
	
			  if (sensorIMEI.equals(jsonobject.getString("SensorIMEI"))) {
	
			   int battery = jsonobject.getInt("BatteryLevel");
			   String connectedStatus = jsonobject.getString("ConnectionStatus");
			   String deviceType = jsonobject.getString("HW_Type");
			   String zoneDescription = jsonobject.getString("ZoneDescription");
	
			   int tamperedValue = jsonobject.getInt("TamperedStatus");
			   if (tamperedValue == 1)
			    device.setTampered(true);
			   else
			    device.setTampered(false);
	
			   int fireAlarmValue = jsonobject.getInt("FireAlarmStatusCode");
			   if (fireAlarmValue == 1)
			    device.setFireAlarmStatusCode(true);
			   else
			    device.setFireAlarmStatusCode(false);
	
			   JSONObject googleLocation = (JSONObject) jsonobject.get("location");
			   double latitude = (double) googleLocation.get("latitude");
			   double longitude = (double) googleLocation.get("longitude");
			   double elevation = (double) googleLocation.get("latitude");
	
			   device.setLatitude(latitude);
			   device.setLongitude(longitude);
			   device.setElevation(elevation);
	
			   device.setBattery(battery + "%");
	
			   if (connectedStatus != null)
			    device.setConnectedStatus(connectedStatus);
			   else
			    device.setConnectedStatus("");
	
			   if (deviceType != null)
			    device.setDeviceType(getDeviceType().get(deviceType).toString());
			   else
			    device.setDeviceType("");
	
			   if (zoneDescription != null)
			    device.setLocation(zoneDescription);
			   else
			    device.setLocation("");
			  }
		 } catch (Exception e) {
		  e.printStackTrace();
		 }
		}
		return device;
	}
	
	
	/*
	 * This service return device list with details as json array
	 */
	public JSONArray  getDeviceDetailsByList(String ateName){
		JSONArray jsonarray = null;
		try {
			 String url = "http://85.105.27.127:8086/Thingworx/Things/" + ateName + "/Services/GetNBIoTDeviceList";
			 DefaultHttpClient httpClient = new DefaultHttpClient();
			 HttpPost postRequest = new HttpPost(url);
			 postRequest.addHeader("Content-Type", contentType);
	         postRequest.addHeader("accept", accept);
	         postRequest.addHeader("appKey", appKey);
	         postRequest.addHeader("User-Agent", userAgent);
			 HttpResponse response = httpClient.execute(postRequest);
	
			 int statusCode = response.getStatusLine().getStatusCode();
			 //System.out.println("statusCode="+statusCode);
			 if (statusCode != 200) {
			  throw new RuntimeException("Failed with HTTP error code : " + statusCode);
			 }
	
			 BasicManagedEntity httpEntity = (BasicManagedEntity) response.getEntity();
			 String apiOutput = EntityUtils.toString((org.apache.http.HttpEntity) httpEntity);
	
			 JSONObject obj = new JSONObject(apiOutput);
	
			 //System.out.println("rows ===" + obj.get("rows"));
	
			 jsonarray = new JSONArray(obj.get("rows").toString());
		} catch (Exception e) {
		 e.printStackTrace();
		}
		return jsonarray;
	}
	
	
	/*
	 * This service return ate device list with other details
	 */
	public List<ATEPojo> getATEDeviceList(String ateName){
		
		List<ATEPojo> deviceList = new ArrayList();
		try {
	        JSONArray jsonarray = getDeviceDetailsByList(ateName);
	        for (int i = 0; i < jsonarray.length(); i++) {
	        	
	        	try{
		            JSONObject jsonobject = jsonarray.getJSONObject(i);
		            String zoneDescription = jsonobject.getString("ZoneDescription");
		            String sensorIMEI = jsonobject.getString("SensorIMEI");
		            String deviceType = jsonobject.getString("HW_Type");
		            //System.out.println("Device Details="+zoneDescription+":sensorIMEI="+sensorIMEI+":deviceType="+deviceType);
		            
		            ATEPojo ate1 = new ATEPojo();
		            
		            if(zoneDescription != null)
		            	ate1.setLocation(zoneDescription);
		            else
		            	ate1.setLocation("");
		            
		            if(sensorIMEI != null)
		            	ate1.setSensorIMEI(sensorIMEI);
		            else
		            	ate1.setSensorIMEI("");
		            
		            if(deviceType != null)
		            	ate1.setSensorType(getDeviceType().get(deviceType).toString());
		            else
		            	ate1.setSensorType("");
		            
		    		deviceList.add(ate1);
	        	}
	        	catch(Exception e){
	        		e.printStackTrace();
	        	}
	        }
	    }
	    catch(Exception e){
		  e.printStackTrace();
	    }
		return deviceList;
	}
	
}
