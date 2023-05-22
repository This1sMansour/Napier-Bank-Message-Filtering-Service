package com.example.demo.MessageThemplates;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SMS extends Message
{
    private String InternationalPhoneNumber;

    // In this function we get String input that is the Message, and then we make it to the format of the SMS
    // Then we make json out of the sms and at the same time send sms to show it
    public String SmsToJson()
    {

        // SMS to JSON
        return   "\"{ " +
                "Header: " + getHeader() + ", " +
                "InternationalPhoneNumber: " + InternationalPhoneNumber + ", " +
                "Text: " +  getText() +
                " }\"";
    }

}
