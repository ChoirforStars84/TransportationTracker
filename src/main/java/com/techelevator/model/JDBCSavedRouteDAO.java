package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCSavedRouteDAO implements SavedRouteDAO{
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCSavedRouteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public SavedRoute saveRoute(User user, String startPt, String endPt) {
		Long userId = user.getUserId();
		String sqlInsertRoute = "INSERT INTO saved_routes(user_id, start_pt, end_pt) VALUES (?,?,?)";
		jdbcTemplate.update(sqlInsertRoute, userId, startPt, endPt);
		Long savedRouteId = null;
		String sqlQueryRoute = "SELECT id FROM saved_routes WHERE start_pt = ? AND end_pt = ?;";
		SqlRowSet idResults = jdbcTemplate.queryForRowSet(sqlQueryRoute, startPt, endPt);
		while(idResults.next()) {
			savedRouteId = idResults.getLong("id");
		}
		SavedRoute newSavedRoute = new SavedRoute();
		newSavedRoute.setId(savedRouteId);
		return newSavedRoute;
	}
/*	
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
	*/
	public List<SavedRoute> getAllSavedRoutesByUser(User user) {
		List<SavedRoute> allSavedRoutesByUser = new ArrayList<SavedRoute>();
		Long userId = user.getUserId();
		SavedRoute holder = new SavedRoute();
		String sqlGetRoutes = "SELECT * FROM saved_routes "
							+ "WHERE user_id = ? ORDER BY saved_routes.id;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRoutes, userId);
		while(results.next()) {
			holder = mapSqlRowToSavedRoute(results);
			allSavedRoutesByUser.add(holder);
		}
		return allSavedRoutesByUser;
	}
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt) {
		SavedRoute routeByName = new SavedRoute();
		Long userId = user.getUserId();
		String sqlGetRoute = "SELECT * FROM saved_routes JOIN routes_users ON routes_users.route_id = saved_routes.id "
							+ "WHERE routes_users.user_id = ? AND saved_routes.start_pt = ? AND saved_routes.end_pt = ?;";
		SqlRowSet routeResults = jdbcTemplate.queryForRowSet(sqlGetRoute, userId, startPt, endPt);
		while (routeResults.next()) {
			routeByName = mapSqlRowToSavedRoute(routeResults);
		}
		return routeByName;
	}
	/*
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne) {
		SavedRoute routeByName = new SavedRoute();
		Long userId = user.getUserId();
		String sqlGetRoute = "SELECT * FROM saved_routes JOIN routes_users ON routes_users.route_id = saved_routes.id "
							+ "WHERE routes_users.user_id = ? AND saved_routes.start_pt = ? AND saved_routes.end_pt = ? AND way_pt_one = ?;";
		SqlRowSet routeResults = jdbcTemplate.queryForRowSet(sqlGetRoute, userId, startPt, endPt, wayPtOne);
		while (routeResults.next()) {
			routeByName = mapSqlRowToSavedRoute(routeResults);
		}
		return routeByName;
	}
	
	public SavedRoute getSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo) {
			SavedRoute routeByName = new SavedRoute();
			Long userId = user.getUserId();
			String sqlGetRoute = "SELECT * FROM saved_routes JOIN routes_users ON routes_users.route_id = saved_routes.id "
								+ "WHERE routes_users.user_id = ? AND saved_routes.start_pt = ? AND saved_routes.end_pt = ? AND way_pt_one = ? AND way_pt_two = ?;";
			SqlRowSet routeResults = jdbcTemplate.queryForRowSet(sqlGetRoute, userId, startPt, endPt, wayPtOne, wayPtTwo);
			while (routeResults.next()) {
				routeByName = mapSqlRowToSavedRoute(routeResults);
			}
			return routeByName;
		}
	
	*/
	public void deleteSavedRouteByName(User user, String startPt, String endPt) {
		SavedRoute routeToDelete = getSavedRouteByName(user, startPt, endPt);
		Long userId = user.getUserId();
		Long routeId = routeToDelete.getId();
		String sqlFirstDelete = "DELETE FROM routes_users WHERE route_id = ? AND user_id = ?;";
		jdbcTemplate.update(sqlFirstDelete, userId, routeId);
		String sqlSecondDelete = "DELETE FROM saved_routes WHERE id = ?;";
		jdbcTemplate.update(sqlSecondDelete, routeId);
	}
	/*
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne) {
		SavedRoute routeToDelete = getSavedRouteByName(user, startPt, endPt, wayPtOne);
		Long userId = user.getUserId();
		Long routeId = routeToDelete.getId();
		String sqlFirstDelete = "DELETE FROM routes_users WHERE route_id = ? AND user_id = ?;";
		jdbcTemplate.update(sqlFirstDelete, userId, routeId);
		String sqlSecondDelete = "DELETE FROM saved_routes WHERE id = ?;";
		jdbcTemplate.update(sqlSecondDelete, routeId);
	}
	
	public void deleteSavedRouteByName(User user, String startPt, String endPt, String wayPtOne, String wayPtTwo) {
		SavedRoute routeToDelete = getSavedRouteByName(user, startPt, endPt, wayPtOne, wayPtTwo);
		Long userId = user.getUserId();
		Long routeId = routeToDelete.getId();
		String sqlFirstDelete = "DELETE FROM routes_users WHERE route_id = ? AND user_id = ?;";
		jdbcTemplate.update(sqlFirstDelete, userId, routeId);
		String sqlSecondDelete = "DELETE FROM saved_routes WHERE id = ?;";
		jdbcTemplate.update(sqlSecondDelete, routeId);
	}
	*/
	public void deleteAllSavedRoutesUser(User user) {
		Long userId = user.getUserId();
		List<SavedRoute> userRoutes = getAllSavedRoutesByUser(user);
		String sqlFirstDelete = "DELETE FROM routes_users WHERE user_id = ?;";
		jdbcTemplate.update(sqlFirstDelete, userId);
		for(SavedRoute userRoute : userRoutes) {
			Long routeId = userRoute.getId();
			String sqlSecondDelete = "DELETE FROM saved_routes WHERE route_id = ?;";
			jdbcTemplate.update(sqlSecondDelete, routeId);
		}
	}
	
	public SavedRoute getSavedRouteById(Long routeId) {
	
		String sqlListRoute = "SELECT * FROM saved_routes WHERE id = ?";
		SqlRowSet sqlRows = jdbcTemplate.queryForRowSet(sqlListRoute, routeId);
		SavedRoute currentSavedRoute = null;
		while (sqlRows.next()) {
			currentSavedRoute = mapSqlRowToSavedRoute(sqlRows);
			
		}
		return currentSavedRoute;
	}
	
	public SavedRoute mapSqlRowToSavedRoute(SqlRowSet results) {
		SavedRoute savedRoute = new SavedRoute();
		savedRoute.setId(results.getLong("id"));
		savedRoute.setUserId(results.getLong("user_id"));
		savedRoute.setStartPt(results.getString("start_pt"));
		savedRoute.setEndPt(results.getString("end_pt"));
/*		if(results.getString("way_pt_one") != null || !results.getString("way_pt_one").isEmpty()) {
			savedRoute.setWayPtOne(results.getString("way_pt_one"));
		}
		if(!(results.getString("way_pt_two") != null) || !results.getString("way_pt_two").isEmpty()) {
			savedRoute.setWayPtTwo(results.getString("way_pt_two"));
		} */
		return savedRoute;
	}


}
