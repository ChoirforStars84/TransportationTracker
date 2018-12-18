package com.techelevator.twilio;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsSender {
    // Find your Account Sid and Auth Token at twilio.com/console
    public static final String ACCOUNT_SID = "AC849c5d9cb707893f69f97b4fde619bdd";
    public static final String AUTH_TOKEN = "03568f45394683648dcfbbb03782410d";
    public static final String SITE_HOST_NUMBER = "+19093452288";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message
                .creator(new PhoneNumber("+14122989259"), // to
                        new PhoneNumber(SITE_HOST_NUMBER), // from
                        "Testing SmsSender")
                .create();

        System.out.println(message.getSid());
    }
}