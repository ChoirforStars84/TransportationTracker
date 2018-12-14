package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface StopDAO {
	
	public Stop getStopByName(String stopName);
	
	public List<Stop> getAllStopsOnRoute(String busLine);
	
	public Stop mapSqlRowToStop(SqlRowSet results);
	
	public List<Stop> getAllStops();

}
