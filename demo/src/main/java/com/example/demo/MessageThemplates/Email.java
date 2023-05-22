package com.example.demo.MessageThemplates;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.example.demo.Controller.TextCorrector;

@Getter
@Setter
public class Email extends Message
{
    private String Address;
    private String Subject;
    private ArrayList<String> QuarantineList = new ArrayList<>();

    /*
    * In this function we get message as string and put it in email format and make json out of it
    * and send it to show it and send json to save it in the Output.csv file
    */
    public String EmailToJson()
    {
        return "\"{ " +
                "Header: " + getHeader() + ", " +
                "Address: " + Address + ", " +
                "Subject: " + Subject + ", " +
                "Text: " +  getText() + ", " +
                "Quarantine" + QuarantineList +
                " }\"";
    }

    /*
    * In URLs function we:
    * 1. Check if Email.Text contains URLs in it. If yes:
    *   2. We put all the URLs in the QuarantineList
    *   3. We replace the URLs with the <URL Quarantined>
    * */

    public void URLs()
    {
        String Text = getText();
        String regexString = "\\b(https://|www[.])[A-Za-z0-9+&@#/%?=~_()|!:,.;]*[-A-Za-z0-9+&@#/%=~_()|]";
        Pattern pattern = Pattern.compile(regexString,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(Text);
        int start, end;

        while (matcher.find())
        {
        QuarantineList.add(Text.substring(start = matcher.start(0), end = matcher.end(0)));
        Text = Text.substring(0, start) + "<URL Quarantined>" + Text.substring(end);
        matcher = pattern.matcher(Text);
        }

        setText(Text);
    }


    public void SubjectTextChecker(String text)
    {

        if(text.charAt(0) == '\"')
        {
            int count = 0;
            for (int i = 1; i < text.length(); i++)
            {
                System.out.println(text.charAt(i));
                System.out.println(count %2);
                if (text.charAt(i) == '\"'){ count++; }
                else if (text.charAt(i) == ',' && count % 2 == 1)
                {
                    this.Subject = TextCorrector(text.substring(0, i));
                    this.setText(TextCorrector(text.substring(i + 1)));
                    // ans = new String[]{text.substring(0, i), text.substring(i + 1)};
                    break;
                }
            }
        }
        else
        {
            String[] ans = text.split(",");
            this.Subject = TextCorrector(ans[0]);
            this.setText(TextCorrector(ans[1]));
        }
    }
}
