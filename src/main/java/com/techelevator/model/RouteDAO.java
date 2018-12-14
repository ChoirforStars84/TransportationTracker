package com.techelevator.model;

import java.util.List;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public interface RouteDAO {
	
	public Route getRouteByNumber(String routeNumber);
	
	public Route getRouteByName(String routeName);
	
	public List<Route> getAllRoutes();
	
	public List<String> getAllRouteNumsWithNamesOnly();
	
	public List<Route> getAllRoutesAtStop(String stopName);
	
	public Route mapSqlRowToRoute(SqlRowSet results);

}
