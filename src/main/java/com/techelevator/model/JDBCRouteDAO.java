package com.techelevator.model;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


public class JDBCRouteDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public JDBCRouteDAO(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public Stop getStopByName(String stopName) {
		Stop stop = new Stop();
		String stopNameAllCaps = stopName.toUpperCase();
		String sqlSearchStop = "SELECT * FROM bus_stops WHERE name = '?';";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchStop, stopNameAllCaps);
		while (results.next()) {
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
		}
		return null;
		
	}

}
