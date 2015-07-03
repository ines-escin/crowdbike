package com.software.project.entities;

public class BikePosition {
	
	private String imei;
  	private String lat;  
  	private String lng;
  	
	public BikePosition(String imei, String lat, String lng) {
		this.imei = imei;
		this.lat = lat;
		this.lng = lng;
	}
	public String getImei() {
		return imei;
	}
	public void setImei(String imei) {
		this.imei = imei;
	}
	public String getLat() {
		return lat;
	}
	public void setLat(String lat) {
		this.lat = lat;
	}
	public String getLng() {
		return lng;
	}
	public void setLng(String lng) {
		this.lng = lng;
	}
  	
}
