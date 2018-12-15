package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCSavedRouteDAO implements SavedRouteDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt) {
		String sqlInsertRoute = "INSERT INTO saved_routes(start_pt, end_pt, private) VALUES (?,?,?);";
		jdbcTemplate.update(sqlInsertRoute, startPt, endPt, isPrivate);
		Long savedRouteId = null;;
		String sqlQueryRoute = "SELECT id FROM saved_routes WHERE start_pt = ? AND end_pt = ?;";
		SqlRowSet idResults = jdbcTemplate.queryForRowSet(sqlQueryRoute, startPt, endPt);
		while(idResults.next()) {
			savedRouteId = idResults.getLong("id");
		}
		Long userId = user.getUserId();
		String sqlInsertRoutesUsers = "INSERT INTO routes_users(route_id, user_id, permissions) VALUES (?,?,?);";
		jdbcTemplate.update(sqlInsertRoutesUsers, savedRouteId, userId, permissions);
	}
	
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt, String wayPtOne) {
		String sqlInsertRoute = "INSERT INTO saved_routes(start_pt, end_pt, way_pt_one, private) VALUES (?,?,?,?);";
		jdbcTemplate.update(sqlInsertRoute, startPt, endPt, wayPtOne, isPrivate);
		Long savedRouteId = null;;
		String sqlQueryRoute = "SELECT id FROM saved_routes WHERE start_pt = ? AND end_pt = ? AND way_pt_one = ?;";
		SqlRowSet idResults = jdbcTemplate.queryForRowSet(sqlQueryRoute, startPt, endPt, wayPtOne);
		while(idResults.next()) {
			savedRouteId = idResults.getLong("id");
		}
		Long userId = user.getUserId();
		String sqlInsertRoutesUsers = "INSERT INTO routes_users(route_id, user_id, permissions) VALUES (?,?,?);";
		jdbcTemplate.update(sqlInsertRoutesUsers, savedRouteId, userId, permissions);
	}
	
	public void saveRoute(User user, boolean isPrivate, String permissions, String startPt, String endPt, String wayPtOne, String wayPtTwo) {
		String sqlInsertRoute = "INSERT INTO saved_routes(start_pt, end_pt, way_pt_one, way_pt_two, private) VALUES (?,?,?,?,?);";
		jdbcTemplate.update(sqlInsertRoute, startPt, endPt, wayPtOne, isPrivate);
		Long savedRouteId = null;;
		String sqlQueryRoute = "SELECT id FROM saved_routes WHERE start_pt = ? AND end_pt = ? AND way_pt_one = ? AND way_pt_two = ?;";
		SqlRowSet idResults = jdbcTemplate.queryForRowSet(sqlQueryRoute, startPt, endPt, wayPtOne, wayPtTwo);
		while(idResults.next()) {
			savedRouteId = idResults.getLong("id");
		}
		Long userId = user.getUserId();
		String sqlInsertRoutesUsers = "INSERT INTO routes_users(route_id, user_id, permissions) VALUES (?,?,?);";
		jdbcTemplate.update(sqlInsertRoutesUsers, savedRouteId, userId, permissions);
	}
	
	public List<SavedRoute> getAllSavedRoutesByUser(User user);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne);
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo);
	
	public void deleteAllSavedRoutesUser(User user);

}
