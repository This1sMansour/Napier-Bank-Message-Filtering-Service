package com.example.demo;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowSmsController
{
    @FXML
    private Label SmsHeader;

    @FXML
    private Label SmsNumber;

    @FXML
    private Label SmsText;


    public void setValue(ArrayList<String> sms)
    {

        //SMS sms = Controller.getSmsD();
        String string;
        string = sms.get(0);

        SmsHeader.setText(string);

        string = sms.get(1);
        SmsNumber.setText(string);

        string = sms.get(2);
        SmsText.setText(string);

        SmsText.setWrapText(true);
        SmsText.setTextAlignment(TextAlignment.JUSTIFY);
    }

    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }
}
        /*
        switch ( Controller.readInputFile() )
        {
            case 'S' -> setValue();
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
            case 'T' ->
            {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ShowTweet.fxml"));
                Parent root = loader.load();
                ShowTweetController tweetShowControl = loader.getController();
                tweetShowControl.setValue();

                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
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
    }
}
*/