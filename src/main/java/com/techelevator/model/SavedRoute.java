package com.techelevator.model;

public class SavedRoute {
	
	Long id;
	String startPt;
	String endPt;
	String wayPtOne;
	String wayPtTwo;
	boolean isPrivate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStartPt() {
		return startPt;
	}
	public void setStartPt(String startPt) {
		this.startPt = startPt;
	}
	public String getEndPt() {
		return endPt;
	}
	public void setEndPt(String endPt) {
		this.endPt = endPt;
	}
	public String getWayPtOne() {
		return wayPtOne;
	}
	public void setWayPtOne(String wayPtOne) {
		this.wayPtOne = wayPtOne;
	}
	public String getWayPtTwo() {
		return wayPtTwo;
	}
	public void setWayPtTwo(String wayPtTwo) {
		this.wayPtTwo = wayPtTwo;
	}
	public boolean isPrivate() {
		return isPrivate;
	}
	public void setPrivate(boolean isPrivate) {
		this.isPrivate = isPrivate;
	}
	
	

}
