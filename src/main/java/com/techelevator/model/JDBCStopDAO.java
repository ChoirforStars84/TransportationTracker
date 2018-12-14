package com.techelevator.model;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCStopDAO implements StopDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCStopDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Stop getStopByName(String stopName) {
		Stop stop = new Stop();
		String stopNameAllCaps = stopName.toUpperCase();
		String sqlSearchStop = "SELECT * FROM bus_stops WHERE name = '?';";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchStop, stopNameAllCaps);
		if(results.next()) {
			stop = mapSqlRowToStop(results);
		}
		return stop;
	}
	
	public List<Stop> getAllStopsOnRoute(String busLine) {
		List<Stop> stopsOnRoute = new ArrayList<Stop>();
		Stop theStop = null;
		String sqlStopListSearch = "SELECT * FROM bus_stops "
								+ "JOIN stops_lines ON stop_id.stops_lines = internalid.bus_stops "
								+ "WHERE bus_line.stops_lines = '?' "
								+ "ORDER BY stop_id.stops_lines;";
		SqlRowSet allStopsResults = jdbcTemplate.queryForRowSet(sqlStopListSearch, busLine);
		while(allStopsResults.next()) {
			theStop = mapSqlRowToStop(allStopsResults);
			stopsOnRoute.add(theStop);
		}
		return stopsOnRoute;
	}
	
	public Stop mapSqlRowToStop(SqlRowSet results) {
		Stop stop = new Stop();
		stop.setInternalId(results.getString("internalid").toLowerCase());
		stop.setName(results.getString("name"));
		stop.setExternalId(results.getFloat("externalid"));
		stop.setDirection(results.getString("direction"));
		stop.setLatitude(results.getDouble("lat"));
		stop.setLongitude(results.getDouble("long"));
		stop.setTimePoint(results.getString("time_point"));
		stop.setNewZone(results.getString("newzone"));
		stop.setNumRoutesServed(results.getFloat("no_rts_served"));
		stop.setRoutes(results.getString("routes"));
		stop.setMode(results.getString("mode"));
		stop.setShelter(results.getString("public_shelter"));
		stop.setPublicStop(results.getString("public_stop"));
		return stop;
	}

}
