package com.example.demo.MessageThemplates;

import com.example.demo.Controller;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
public class Tweet extends Message
{
    private String Id;
    private Set<String> Hashtags = new HashSet<>();
    private Set<String> Mentions = new HashSet<>();

    public String TweetToJson()
    {
        return "\"{ " +
                "Header: " + getHeader() + ", " +
                "ID: " + Id + ", " +
                "Text: " +  getText() + ", " +
                "Hashtag: " +  Hashtags + ", " +
                "Mentions: " +  Mentions + ", " +
                " }\"";
    }


    public void HashtagMention()
    {
        String text = getText() + " .", subString;
        char C;
        int len = text.length() - 2, i = 0, j;

        while (len > i)
        {
            if ((C = text.charAt(i)) == '#')
            {
                j = i + 1;
                while (text.charAt(j) != ' '){ j++;}
                if (i < j - 1 )
                {
                    Hashtags.add((subString = text.substring(i,j)));
                    Controller.addTrendingList(subString);
                }
                i = j;

            }

            else if (C == '@')
            {
                j = i + 1;
                while (text.charAt(j) != ' '){ j++;}
                if (i < j - 1 )
                {
                    Mentions.add((subString = text.substring(i,j)));
                    Controller.addMentionList(subString);
                }
            }

            else { while (text.charAt(i) != ' '){ i++;} }

            i++;
        }


    }
}
