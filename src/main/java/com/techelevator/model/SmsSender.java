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
    
    public static String generateTLRNumber() {
		int randomInt = ThreadLocalRandom.current().nextInt(10000, 100000);
		String randomString = Integer.toString(randomInt);
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