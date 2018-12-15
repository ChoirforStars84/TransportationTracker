package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;


public class JDBCSavedRouteDAO implements SavedRouteDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public void saveRoute(User user, boolean isPrivate, String startPt, String endPt) {
		String sqlInsertRoute = "INSERT INTO saved_routes(start_pt, end_pt, private) VALUES (?,?,?);";
		jdbcTemplate.update(sqlInsertRoute, startPt, endPt, isPrivate);
		String userId = user.g
	}
	
	public void saveRoute(User user, boolean isPrivate, String startPt, String endPt, String wayPtOne);
	
	public void saveRoute(User user, boolean isPrivate, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public List<SavedRoute> getAllSavedRoutesByUser(User user);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteAllSavedRoutesUser(User user);

}
