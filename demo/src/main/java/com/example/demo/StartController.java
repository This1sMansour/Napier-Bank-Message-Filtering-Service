package com.example.demo;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController
{
    public void StartButton(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }


    /*
    * {
        FXMLLoader loader;
        Parent root = null;

        switch ( Controller.readInputFile() )
        {
            case 'S' ->
            {
                loader = new FXMLLoader(getClass().getResource("ShowSms.fxml"));
                root = loader.load();
                ShowSmsController smsShowControl = loader.getController();
                //smsShowControl.setValue();
            }

            case 'E' ->
            {
                loader = new FXMLLoader(getClass().getResource("ShowEmail.fxml"));
                root = loader.load();
                ShowEmailController emailShowControl = loader.getController();
                emailShowControl.setValue();
            }

            case 'I' ->
            {
                loader = new FXMLLoader(getClass().getResource("ShowSir.fxml"));
                root = loader.load();
                ShowSirController sirShowControl = loader.getController();
                sirShowControl.setValue();
            }

            case 'T' ->
            {
                loader = new FXMLLoader(getClass().getResource("ShowTweet.fxml"));
                root = loader.load();
                ShowTweetController tweetShowControl = loader.getController();
                tweetShowControl.setValue();
            }

            case 'M' ->
            {
                loader = new FXMLLoader(getClass().getResource("Menu.fxml"));
                root = loader.load();
            }



        }

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        assert root != null;
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }*/
}
