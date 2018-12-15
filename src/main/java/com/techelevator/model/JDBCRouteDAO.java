package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

@Repository
public class JDBCRouteDAO implements RouteDAO {
	
	private JdbcTemplate jdbcTemplate;

	@Autowired
	public JDBCRouteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Route getRouteByNumber(String routeNumber) {
		Route route = new Route();
		String routeNumberCaps = routeNumber.toUpperCase();
		String sqlGetRoute = "SELECT * FROM bus_lines WHERE number = '?';";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRoute, routeNumberCaps);
		if(results.next()) {
			route = mapSqlRowToRoute(results);
		}
		return route;
	}

	public Route getRouteByName(String routeName) {
		Route route = new Route();
		String routeNameCaps = routeName.toUpperCase();
		String sqlGetRoute = "SELECT * FROM bus_lines WHERE name = '?';";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRoute, routeNameCaps);
		if(results.next()) {
			route = mapSqlRowToRoute(results);
		}
		return route;
	}

	public List<Route> getAllRoutes() {
		List<Route> allRoutes = new ArrayList<Route>();
		Route route = new Route();
		String sqlGetAllRoutes = "SELECT * FROM bus_lines;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllRoutes);
		while(results.next()) {
			route = mapSqlRowToRoute(results);
			allRoutes.add(route);
		}
		return allRoutes;
	}

	public List<String> getAllRouteNumsWithNamesOnly() {
		List<String> allNumsNames = new ArrayList<String>();
		String numAndName = null;
		String sqlGetAllRoutes = "SELECT (number || name) AS num_and_name FROM bus_lines;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetAllRoutes);
		while(results.next()) {
			numAndName = results.getString("num_and_name");
			allNumsNames.add(numAndName);
		}
		return allNumsNames;
	}

	public List<Route> getAllRoutesAtStop(String stopName) {
		List<Route> routesAtStop = new ArrayList<Route>();
		String stopNameCaps = stopName.toUpperCase();
		String sqlGetRoutesAtStop = "SELECT * FROM bus_lines JOIN stops_lines ON stops_lines.bus_line = bus_lines.number "
								+ "JOIN bus_stops ON bus_stops.internalid = stops_lines.stop_id "
								+ "WHERE bus_stops.name = '?' "
								+ "ORDER BY bus_stops.number;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlGetRoutesAtStop, stopNameCaps);
		while (results.next()) {
			Route holderRoute = mapSqlRowToRoute(results);
			routesAtStop.add(holderRoute);
		}
		return routesAtStop;
	}

	public Route mapSqlRowToRoute(SqlRowSet results) {
		Route route = new Route();
		route.setRouteNumber(results.getString("number"));
		route.setRouteName(results.getString("name"));
		route.setRouteColor(results.getString("rtclr"));
		route.setRtdd(results.getString("rtdd"));
		route.setRtpiDataFeed(results.getString("rtpidatafeed"));
		return route;
	}

	
}
