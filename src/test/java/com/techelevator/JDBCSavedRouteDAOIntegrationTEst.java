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

import com.techelevator.model.JDBCSavedRouteDAO;

public class JDBCSavedRouteDAOIntegrationTEst {

	private static SingleConnectionDataSource dataSource;
	private JDBCSavedRouteDAO dao;
	
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
		String sqlInsertFirstRoute = "INSERT INTO saved_routes (start_pt, end_pt) VALUES ('START', 'END')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sqlInsertFirstRoute);
		String sqlInsertSecondRoute = "INSERT INTO saved_routes (start_pt, end_pt, way_pt_one) VALUES ('START', 'END', 'MIDDLE')";
		jdbcTemplate.update(sqlInsertSecondRoute);
		String sqlInsertThirdRoute = "INSERT INTO saved_routes (start_pt, end_pt, way_pt_one, way_pt_two) VALUES ('START', 'END', 'MIDDLE', 'SECOND MIDDLE')";
		jdbcTemplate.update(sqlInsertThirdRoute);
		dao = new JDBCSavedRouteDAO(dataSource);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}
	
	protected DataSource getDataSource() {
		return dataSource;
	}
	
}
