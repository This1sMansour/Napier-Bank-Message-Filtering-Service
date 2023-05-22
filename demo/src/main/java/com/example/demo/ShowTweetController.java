package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowTweetController
{
    @FXML
    private Label TweetHashtag;

    @FXML
    private Label TweetHeader;

    @FXML
    private Label TweetId;

    @FXML
    private Label TweetMentions;

    @FXML
    private Label TweetText;


    public void setValue(ArrayList<String> tweet)
    {
        TweetHeader.setText(tweet.get(0));
        TweetId.setText(tweet.get(1));
        TweetText.setText(tweet.get(2));
        TweetHashtag.setText(tweet.get(3));
        TweetMentions.setText(tweet.get(4));


    }


    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }
    /*
        switch ( Controller.readInputFile() )
        {
            case 'S' ->
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowSms.fxml"));
                Parent root = loader.load();

                ShowSmsController smsShowControl = loader.getController();
                //smsShowControl.setValue();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            case 'E' ->
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowEmail.fxml"));
                Parent root = loader.load();

                ShowEmailController emailShowControl = loader.getController();
                emailShowControl.setValue();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            case 'I' ->
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowSir.fxml"));
                Parent root = loader.load();
                ShowSirController sirShowControl = loader.getController();
                sirShowControl.setValue();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }

            case 'T' -> setValue();


            case 'M' ->
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                Parent root = loader.load();
                MenuController menuController = loader.getController();
                menuController.setTrendingList();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();

            }
        }
    }*/
}
