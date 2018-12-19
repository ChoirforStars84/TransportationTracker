package com.techelevator.model;

public interface UserDAO {

	public void saveUser(String userName, String password, String phoneNumber);

	public boolean searchForUsernameAndPassword(String userName, String password);

	public void updatePassword(String userName, String password);

	public User getUserByUserName(String userName);
	
	public void savePhoneNumber(String phoneNumber, User user);
	
	public boolean verifyNumber(String phoneNumber);

}
