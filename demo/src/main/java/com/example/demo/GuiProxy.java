package com.example.demo;

import com.example.demo.MessageThemplates.Email;
import com.example.demo.MessageThemplates.SIR;
import com.example.demo.MessageThemplates.SMS;
import com.example.demo.MessageThemplates.Tweet;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class GuiProxy
{
    public void NextGui(Stage stage) throws IOException
    //  B = Start, S = SMS, E = E-mail, I = SIR, T = Tweet, M = Menu
    {
        FXMLLoader loader;
        Parent root = null;

        char nextGui;
        do
        {
            nextGui = Controller.readInputFile();
        }
        while (nextGui == 'F');

        switch (nextGui)
        {
            case 'S' ->
            {
                SMS sms = Controller.getSmsD();
                ArrayList<String> Sms = new ArrayList<>();
                Sms.add(sms.getHeader());
                Sms.add(sms.getInternationalPhoneNumber());
                Sms.add(sms.getText());


                loader = new FXMLLoader(GuiProxy.class.getResource("ShowSms.fxml"));
                root = loader.load();

                ShowSmsController smsShowControl = loader.getController();
                smsShowControl.setValue(Sms);


            }

            case 'E' ->
            {
                Email email = Controller.getEmailD();
                ArrayList<String> Email = new ArrayList<>();
                Email.add(email.getHeader());
                Email.add(email.getAddress());
                Email.add(email.getSubject());
                Email.add(email.getText());
                Email.add(String.valueOf(email.getQuarantineList()));

                loader = new FXMLLoader(GuiProxy.class.getResource("ShowEmail.fxml"));
                root = loader.load();

                ShowEmailController emailShowControl = loader.getController();
                emailShowControl.setValue(Email);
            }
            case 'I' ->
            {
                SIR sir = Controller.getSirD();
                ArrayList<String> Sir = new ArrayList<>();
                Sir.add(sir.getHeader());
                Sir.add(sir.getAddress());
                Sir.add(sir.getSubject());
                Sir.add(sir.getSortCode());
                Sir.add(sir.getNatureOfIncident());
                Sir.add(sir.getText());
                Sir.add(String.valueOf(sir.getQuarantineList()));

                loader = new FXMLLoader(GuiProxy.class.getResource("ShowSir.fxml"));
                root = loader.load();

                ShowSirController sirShowControl = loader.getController();
                sirShowControl.setValue(Sir);
            }
            case 'T' ->
            {
                Tweet tweet = Controller.getTweetD();
                ArrayList<String> Tweet = new ArrayList<>();
                Tweet.add(tweet.getHeader());
                Tweet.add(tweet.getId());
                Tweet.add(tweet.getText());
                Tweet.add(tweet.getHashtags().toString());
                Tweet.add(tweet.getMentions().toString());

                loader = new FXMLLoader(GuiProxy.class.getResource("ShowTweet.fxml"));
                root = loader.load();

                ShowTweetController tweetShowControl = loader.getController();
                tweetShowControl.setValue(Tweet);
            }
            case 'M' ->
            {
                loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                root = loader.load();
                MenuController menuController = loader.getController();
                menuController.setTrendingList();
            }
        }
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
}
