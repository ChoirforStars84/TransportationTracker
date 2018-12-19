package com.techelevator.model;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import java.util.concurrent.ThreadLocalRandom;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC849c5d9cb707893f69f97b4fde619bdd";
    public static final String AUTH_TOKEN = "03568f45394683648dcfbbb03782410d";
    public static final String SITE_HOST_NUMBER = "+19093452288";
    
    
    public static void sendReminder(String formattedNumber, String startPoint) {
    		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(formattedNumber), // to
                        new PhoneNumber(SITE_HOST_NUMBER), // from
                        "This is your BCTT reminder that your bus or light rail will be arriving in approximately 30 minutes at " + startPoint + ". Enjoy your ride!")
                .create();
        System.out.println(message.getSid());
    }
    
    public static void sendVerificationCode(String formattedNumber, String verificationCode) {
    		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message
                .creator(new PhoneNumber(formattedNumber), // to
                        new PhoneNumber(SITE_HOST_NUMBER), // from
                        "Your BCTT verification code is " + verificationCode + ". Please follow the directions on the verification page to reset your account info.")
                .create();
        System.out.println(message.getSid());
    }
    
    public static int generateTLRNumber() {
		int randomInt = ThreadLocalRandom.current().nextInt(1, 100000);
		return randomInt;
    }
    
    public static String randomNumToString(int randomInt) {
    		String parseString = Integer.toString(randomInt);
    		String randomString = "";
    		switch (parseString.length()) {
    		case 1 : randomString = "0000" + parseString;
    		case 2 : randomString = "000" + parseString;
    		case 3 : randomString = "00" + parseString;
    		case 4 : randomString = "0" + parseString;
    		case 5 : randomString = parseString;
    		}
    		return randomString;
    }
    
    
    
    
    

    /*public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+14122989259"), // to
                        new PhoneNumber(SITE_HOST_NUMBER), // from
                        "This is your BCTT reminder that")
                .create();

        System.out.println(message.getSid());
    }*/
    

}