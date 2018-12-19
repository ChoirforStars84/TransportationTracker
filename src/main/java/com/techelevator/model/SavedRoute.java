package com.techelevator.model;

public class SavedRoute {
	
	Long id;
	Long userId;
	public String startPt;
	public String endPt;
	String wayPtOne;
	String wayPtTwo;
	boolean isPrivate;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
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
	
	public String getName() {
		String name;
		if(wayPtOne.equals(null) && wayPtTwo.equals(null)) {
			name = startPt + " to " + endPt;
		} else if(wayPtTwo.equals(null)) {
			name = startPt + " to " + endPt + ", transfer at " + wayPtOne;
		} else {
			name = startPt + " to " + endPt + ", transfer at " + wayPtOne + " and " + wayPtTwo;
		}
		return name;
	}
	
	

}
