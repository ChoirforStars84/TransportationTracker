package com.techelevator.model;

public class Stop {
	
	private String internalId;
	private String name;
	private float externalId;
	private String direction;
	private double latitude;
	private double longitude;
	private String timePoint;
	private String newZone;
	private float numRoutesServed;
	private String routes;
	private String mode;
	private String shelter;
	private String publicStop;
	public String getInternalId() {
		return internalId;
	}
	public void setInternalId(String internalId) {
		this.internalId = internalId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getExternalId() {
		return externalId;
	}
	public void setExternalId(float externalId) {
		this.externalId = externalId;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public String getTimePoint() {
		return timePoint;
	}
	public void setTimePoint(String timePoint) {
		this.timePoint = timePoint;
	}
	public String getNewZone() {
		return newZone;
	}
	public void setNewZone(String newZone) {
		this.newZone = newZone;
	}
	public float getNumRoutesServed() {
		return numRoutesServed;
	}
	public void setNumRoutesServed(float numRoutesReserved) {
		this.numRoutesServed = numRoutesReserved;
	}
	public String getRoutes() {
		return routes;
	}
	public void setRoutes(String routes) {
		this.routes = routes;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public String getShelter() {
		return shelter;
	}
	public void setShelter(String shelter) {
		this.shelter = shelter;
	}
	public String getPublicStop() {
		return publicStop;
	}
	public void setPublicStop(String publicStop) {
		this.publicStop = publicStop;
	}
	

	
	

}
