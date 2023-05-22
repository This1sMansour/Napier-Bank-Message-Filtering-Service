package com.example.demo.MessageThemplates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SIR extends Email
{
    private String SortCode;
    private String NatureOfIncident;


    public String SirToJson()
    {
        return "\"{ " +
                "Header: " + getHeader() + ", " +
                "Address: " + getAddress() + ", " +
                "Subject: " + getSubject() + ", " +
                "Sort code: " + SortCode + ", " +
                "Nature of incident: " + NatureOfIncident + ", " +
                "Text: " +  getText() + ", " +
                "Quarantine" + getQuarantineList() +
                " }\"";
    }
}
