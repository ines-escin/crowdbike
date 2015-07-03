package com.software.project.entities;

public class VehiclePosition {
	
	private String imei;
  	private String lat;  
  	private String lng;
  	
	public VehiclePosition(String imei, String lat, String lng) {
		super();
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

	public VehiclePosition() {
		super();
	}

  	
  	
}
