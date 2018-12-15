package com.techelevator.model;

import java.util.List;

public interface SavedRouteDAO {
	
	//Overloaded method as different routes may have different numbers of waypoints
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt);
	
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt, String wayPtOne);
	
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public List<SavedRoute> getAllSavedRoutesByUser(User user);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteAllSavedRoutesUser(User user);
	
	
	
	

}
