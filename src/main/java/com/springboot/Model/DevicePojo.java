package com.springboot.Model;

public class DevicePojo {
	
	private String deviceType;
	private String location;
	private String connectedStatus;
	private boolean tampered;
	private String battery;
	private double latitude;
	private double longitude;
	private boolean fireAlarmStatusCode;
	private double elevation;
	
	
	public boolean isFireAlarmStatusCode() {
		return fireAlarmStatusCode;
	}
	public void setFireAlarmStatusCode(boolean fireAlarmStatusCode) {
		this.fireAlarmStatusCode = fireAlarmStatusCode;
	}
	public String getDeviceType() {
		return deviceType;
	}
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getConnectedStatus() {
		return connectedStatus;
	}
	public void setConnectedStatus(String connectedStatus) {
		this.connectedStatus = connectedStatus;
	}
	public boolean getTampered() {
		return tampered;
	}
	public void setTampered(boolean tampered) {
		this.tampered = tampered;
	}
	public String getBattery() {
		return battery;
	}
	public void setBattery(String battery) {
		this.battery = battery;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double d) {
		this.latitude = d;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double d) {
		this.longitude = d;
	}
	public double getElevation() {
		return elevation;
	}
	public void setElevation(double elevation) {
		this.elevation = elevation;
	}
	
	

}
