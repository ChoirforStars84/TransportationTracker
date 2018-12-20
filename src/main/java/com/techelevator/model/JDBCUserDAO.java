package com.techelevator.model;

import javax.sql.DataSource;

import org.bouncycastle.util.encoders.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.model.User;
import com.techelevator.security.PasswordHasher;

@Component
public class JDBCUserDAO implements UserDAO {

	private JdbcTemplate jdbcTemplate;
	private PasswordHasher hashMaster;

	@Autowired
	public JDBCUserDAO(DataSource dataSource, PasswordHasher hashMaster) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
		this.hashMaster = hashMaster;
	}
	
	@Override
	public User saveUser(String userName, String password, String phoneNumber) {
		byte[] salt = hashMaster.generateRandomSalt();
		String hashedPassword = hashMaster.computeHash(password, salt);
		String saltString = new String(Base64.encode(salt));
		
		jdbcTemplate.update("INSERT INTO app_user(user_name, password, salt, phone_number) VALUES (?, ?, ?, ?)",
				userName, hashedPassword, saltString, phoneNumber);
		return null;
		
		//change this to return user with the new user id -- see addReservation() in jdbcreservationdao from capstone 2
	}

	@Override
	public boolean searchForUsernameAndPassword(String userName, String password) {
		String sqlSearchForUser = "SELECT * "+
							      "FROM app_user "+
							      "WHERE UPPER(user_name) = ? ";
		
		SqlRowSet user = jdbcTemplate.queryForRowSet(sqlSearchForUser, userName.toUpperCase());
		if(user.next()) {
			String dbSalt = user.getString("salt");
			String dbHashedPassword = user.getString("password");
			String givenPassword = hashMaster.computeHash(password, Base64.decode(dbSalt));
			return dbHashedPassword.equals(givenPassword);
		} else {
			return false;
		}
	}

	@Override
	public void updatePassword(String userName, String password) {
		jdbcTemplate.update("UPDATE app_user SET password = ? WHERE user_name = ?", password, userName);
	}

	@Override
	public User getUserByUserName(String userName) {
		String sqlSearchForUsername ="SELECT * "+
		"FROM app_user "+
		"WHERE UPPER(user_name) = ? ";

		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlSearchForUsername, userName.toUpperCase()); 
		User thisUser = null;
		while(results.next()) {
			thisUser = mapSqlRowToUser(results);
		}

		return thisUser;
	}
	
	public void savePhoneNumber(String phoneNumber, User user) {
		Long userId = user.getUserId();
		String sqlUpdateNumber = "UPDATE app_user SET phone_number = ? WHERE id = ?";
		jdbcTemplate.update(sqlUpdateNumber, phoneNumber, userId);
	}
	
	public boolean verifyNumber(String phoneNumber) {
		boolean numExists = false;
   		String sqlQueryForNumber = "SELECT * FROM app_user WHERE phone_number = ?";
   		SqlRowSet results = jdbcTemplate.queryForRowSet(sqlQueryForNumber, phoneNumber);
   		if(results.next()) {
   			numExists = true;
   		} else {
   			numExists = false;
   		}
   		return numExists;
   }
   
	public User mapSqlRowToUser(SqlRowSet results) {
		User currentUser = new User();
		currentUser.setUserId(results.getLong("id"));
		currentUser.setUserName(results.getString("user_name"));
		currentUser.setPassword(results.getString("password"));
		currentUser.setPhoneNumber(results.getString("phone_number"));
		return currentUser;
	}

}
