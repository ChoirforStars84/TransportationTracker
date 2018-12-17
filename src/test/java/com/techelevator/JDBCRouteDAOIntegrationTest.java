package com.techelevator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.techelevator.model.JDBCRouteDAO;
import com.techelevator.model.Route;

public class JDBCRouteDAOIntegrationTest {
	
	private static SingleConnectionDataSource dataSource;
	private JDBCRouteDAO dao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/capstone");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}

	@Before
	public void setup() {
		String sqlInsertRoute = "INSERT INTO bus_lines (number, name, rtclr, rtdd, rtpidatafeed) "
							+ "VALUES ('TEST', 'TECH ELEVATOR', '#000000', 'TEST', 'Light Rail')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertRoute);
		dao = new JDBCRouteDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
	@Test
	public void get_route_by_number() {
		Route testRoute = createTestRoute();
		Route actualRoute = dao.getRouteByNumber("TEST");
		assertNotNull(actualRoute);
		assertRoutesAreEqual(testRoute, actualRoute);
	}
	
	@Test
	public void get_route_by_name() {
		Route testRoute = createTestRoute();
		Route actualRoute = dao.getRouteByName("TECH ELEVATOR");
		assertNotNull(actualRoute);
		assertRoutesAreEqual(testRoute, actualRoute);
	}
	
	@Test
	public void get_all_routes() {
		List<Route> testAllRoutes = new ArrayList<Route>();
		testAllRoutes = dao.getAllRoutes();
		assertNotNull(testAllRoutes);
		assertEquals(101, testAllRoutes.size());
	}
	
	@Test
	public void get_all_routes_at_stop() {
		Route testRoute = createTestRoute();
		List<Route> testRoutesAtStop = new ArrayList<Route>();
		String sqlTestFirstInsert = "INSERT INTO stops_lines (stop_id, bus_line) VALUES ('STOPNUM', 'TEST')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlTestFirstInsert);
		String sqlTestSecondInsert = "INSERT INTO bus_stops (internalid, name, externalid, direction, lat, long, time_point, newzone, no_rts_served, routes, mode, public_shelter, public_stop) VALUES ('STOPNUM', 'TEST STOP', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K')";
		jdbcTemplate.update(sqlTestSecondInsert);
		testRoutesAtStop = dao.getAllRoutesAtStop("Test Stop");
		assertNotNull(testRoutesAtStop);
		assertEquals(1, testRoutesAtStop.size());
		assertRoutesAreEqual(testRoute, testRoutesAtStop.get(0));
	}
		
	private Route createTestRoute() {
		Route testRoute = new Route();
		testRoute.setRouteNumber("TEST");
		testRoute.setRouteName("TECH ELEVATOR");
		testRoute.setRouteColor("#000000");
		testRoute.setRtdd("TEST");
		testRoute.setRtpiDataFeed("Light Rail");
		return testRoute;
	}
	
	private void assertRoutesAreEqual(Route expected, Route actual) {
		assertEquals(expected.getRouteNumber(), actual.getRouteNumber());
		assertEquals(expected.getRouteName(), actual.getRouteName());
		assertEquals(expected.getRouteColor(), actual.getRouteColor());
		assertEquals(expected.getRtdd(), actual.getRtdd());
		assertEquals(expected.getRtpiDataFeed(), actual.getRtpiDataFeed());
	}
		
	
	
}
