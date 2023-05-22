package com.example.demo;


import com.example.demo.MessageThemplates.Email;
import com.example.demo.MessageThemplates.SIR;
import com.example.demo.MessageThemplates.SMS;
import com.example.demo.MessageThemplates.Tweet;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Controller
{
    private static BufferedReader inputReader;

    private static SMS SmsD;
    public static SMS getSmsD(){ return SmsD;}
    public static void setSmsD(SMS sms) { SmsD = sms; }

    private static Email EmailD;
    public static Email getEmailD(){ return EmailD;}
    public static void setEmailD(Email email) { EmailD = email; }

    private static SIR SirD;
    public static SIR getSirD(){ return SirD;}
    public static void setSirD(SIR sir) { SirD = sir; }

    private static Tweet TweetD;
    public static Tweet getTweetD() { return TweetD; }
    public static void setTweetD(Tweet tweet) { TweetD = tweet; }

    private static final String FilePath = "E:\\University\\Edinburgh Napier University - year 3 & 4\\Term 5\\" +
            "SET09102 2022-3 TR1 001 - Software Engineering\\Coursework 22-23\\1\\40617066 Mansour Sami\\demo\\" +
            "src\\main\\java\\Files";
    private static final String InputCsvFilePath = "\\Input.csv";
    private static final  String OutputCsvFilePath = "\\Output.csv";
    private static final String textWordsFilePath = "\\textWords.csv";

    //ArrayList<String> SeenHeaders = new ArrayList<>();
    private static final HashMap<String, String> DictTextSpeakAbbreviation = new HashMap<>();

    private static final HashMap<String, Integer> TrendingList = new HashMap<>();

    private static final HashSet<String> Mentions = new HashSet<>();

    private static final ArrayList<String> SirList = new ArrayList<>();

    public static void addMentionList(String mention)
    {
        Mentions.add(mention);
    }

    public static String MentionsToString() { return String.valueOf(Mentions); }
    public static String SirListToString(){ return String.valueOf(SirList); }



    //private FileWriter OutputFile; // =  new FileWriter(OutputCsvFilePath,false)


    /*  In Checker function
    *   First we check for the existence of Input.csv and textWord.csv
    *   Then we make a dictionary from textWord for text to speak abbreviation
    * */
    public void Checker()
    {
        try {
            //  Checking if Input.csv and textWords.csv exists
            if (new File(FilePath + InputCsvFilePath ).exists() &&
                    new File(FilePath + textWordsFilePath ).exists())
            {
                //  Clearing/Making output file
                FileWriter OutputFile = new FileWriter(FilePath + OutputCsvFilePath,false);
                OutputFile.close();

                //  Reading the textWord.csv file and putting the keys and the values in the
                //  DictTextSpeakAbbreviation that is a dictionary
                BufferedReader textWordReader = new BufferedReader(new FileReader(FilePath + textWordsFilePath));
                String line; String[] KeyValue;
                while((line = textWordReader.readLine()) != null)
                {
                    KeyValue = line.split(",");
                    DictTextSpeakAbbreviation.put(KeyValue[0], KeyValue[1]);
                }
                textWordReader.close();

                inputReader = new BufferedReader(new FileReader(FilePath + InputCsvFilePath));
            }
            else
            {
                System.out.println("Input.csv is missing. /TextWord.csv is missing/ " +
                        "Input.csv and TextWord.csv are missing.");
            }

        }
        catch (IOException e) // In the case of System error.
        {
            System.out.println("Error Occurrence.");
            throw new RuntimeException(e);
        }

    }


    private static int Testing = 0;
    public static void setTesting(int newTesting) { Testing = newTesting; }

    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }


    public static char readInputFile() throws IOException
    {
        String line;
        if((line = inputReader.readLine()) != null)
        {
            if (line.equals("\n")) { return 'F'; }

            String code = line.substring(1, 10);

            if ( code.matches("-?\\d+(\\.\\d+)?"))
            {
                switch (line.charAt(0)) {
                    case 'S' -> {
                        SMS sms = new SMS();
                        String[] data = line.split(",", 3);
                        sms.setHeader(data[0]);
                        sms.setInternationalPhoneNumber(data[1]);
                        sms.setText(TextToSpeak(TextCorrector(data[2])));
                        saveJson(sms.getHeader(), sms.SmsToJson());

                        SmsD = sms;
                        return 'S';
                    }
                    case 'E' -> {
                        Email email = new Email();
                        String[] data = line.split(",", 3);
                        email.setHeader(data[0]);
                        email.setAddress(data[1]);

                        if (data[2].startsWith("SIR ")) {
                            //  it is Sir
                            SIR sir = new SIR();

                            sir.setHeader(email.getHeader());
                            sir.setAddress(email.getAddress());

                            data = data[2].split(",", 4);
                            sir.setSubject(data[0]);
                            sir.setSortCode(data[1]);
                            sir.setNatureOfIncident(data[2]);
                            sir.setText(TextCorrector(data[3]));

                            SirList.add(sir.getSortCode() + sir.getNatureOfIncident());

                            sir.URLs();
                            saveJson(sir.getHeader(), sir.SirToJson());

                            SirD = sir;
                            return 'I';
                        } else {
                            //  it is email
                            email.SubjectTextChecker(data[2]);

                            email.URLs();
                            saveJson(email.getHeader(), email.EmailToJson());

                            EmailD = email;
                            return 'E';
                        }

                    }

                    case 'T' -> {
                        Tweet tweet = new Tweet();
                        String[] data = line.split(",", 3);
                        tweet.setHeader(data[0]);
                        tweet.setId(data[1]);
                        tweet.setText(TextToSpeak(TextCorrector(data[2])));
                        tweet.HashtagMention();

                        saveJson(tweet.getHeader(), tweet.TweetToJson());

                        TweetD = tweet;
                        return 'T';
                    }

                    default -> {
                        return 'F';
                    }
                }
            } return 'F';
        }

        else if (Testing > 0)
        {
            switch (Testing)
            {
                // 1 = SMS
                case 1 -> { Testing = 0; return 'S'; }

                // 2 = Email
                case 2 -> { Testing = 0; return 'E'; }

                // 3 = SIR
                case 3 -> { Testing = 0; return 'I'; }

                // 4 = Tweet
                case 4 -> { Testing = 0; return 'T'; }
            }
        }

        return 'M';
    }

    public static void saveJson(String Header, String Json)
    {
        try {
            FileWriter OutputFile = new FileWriter(FilePath + OutputCsvFilePath,true);
            OutputFile.write(Header + ',' + Json+ '\n' );
            OutputFile.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public static String TextToSpeak(String text)
    {
        int i = 0, j;
        char C; String LongFormat;
        text += '.';


        while(i < text.length())
        //  while we had not reached the end of the string we continue
        //  the length of text can change
        {
            if (Character.isUpperCase(text.charAt(i)))
            //  Checking if in the index i we have an upper case character
            {
                j = i + 1;
                while ((C = text.charAt(j)) != ' ' && C != ',' && C != '.' && (Character.isUpperCase(C) || C == '/'))
                //
                {
                    j ++ ;
                }
                if (i < j && Character.isUpperCase(text.charAt(j - 1))
                        && (LongFormat = DictTextSpeakAbbreviation.get(text.substring(i,j))) != null)
                //  Checking if it is a textSpeak
                {
                    text = text.substring(0, j) + "<" + LongFormat + ">" + text.substring(j);
                    i = j + 1 + LongFormat.length();
                }
                else
                {
                    i = j;
                    while (i < text.length() && text.charAt(i) != ' '){ i++; }
                }

            }
            else
            //  If there is not one we pass until we reach the next word
            { while (i < text.length() && text.charAt(i) != ' '){ i++; } }

            i++;

        }
        return text.substring(0,text.length() - 1);
    }


    public static String TextCorrector(String Text)
    {
        if (Text.charAt(0) == '\"')
        {
            Text = Text.substring(1,Text.length() - 2);
            if (Text.contains("\""))
            {
                StringBuilder ansText = new StringBuilder(); char c;
                for (int i = 0; i < Text.length(); i++)
                {
                    if ((c = Text.charAt(i)) == '\"') { i++; }
                    ansText.append(c);
                }

                return ansText.toString();
            }
        }
        return Text;
    }


    public static void addSirList(String sir)
    {
        SirList.add(sir);
    }

    public static void addTrendingList(String word)
    {
        TrendingList.put(word, TrendingList.getOrDefault(word, 0) + 1);
    }

    public static String TrendingListToString()
    {
        List<Map.Entry<String, Integer>> list = new LinkedList<>(TrendingList.entrySet());


        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()) == 0
                ? o2.getKey().compareTo(o1.getKey())
                : o2.getValue().compareTo(o1.getValue()));
        Map<String, Integer> sortedMapDesc = list.stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> b, LinkedHashMap::new));
        String[] ans = {""};
        sortedMapDesc.forEach((key, value) ->
                ans[0] += key + " = " + value + ", ");
        return ans[0].substring(0, ans[0].length() - 2);
    }



}
