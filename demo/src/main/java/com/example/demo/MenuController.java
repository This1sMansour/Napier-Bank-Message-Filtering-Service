package com.example.demo;

import com.example.demo.MessageThemplates.Email;
import com.example.demo.MessageThemplates.SIR;
import com.example.demo.MessageThemplates.SMS;
import com.example.demo.MessageThemplates.Tweet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;

import static com.example.demo.Controller.TextCorrector;
import static com.example.demo.Controller.TextToSpeak;

public class MenuController
{

    @FXML
    private Label TrendingList;
    @FXML
    private Label MentionList;
    @FXML
    private Label SirList;


    public void setTrendingList()
    {
        TrendingList.setText(Controller.TrendingListToString());
        TrendingList.setWrapText(true);
        TrendingList.setTextAlignment(TextAlignment.JUSTIFY);

        SirList.setText(Controller.SirListToString());
        SirList.setWrapText(true);
        SirList.setTextAlignment(TextAlignment.JUSTIFY);

        MentionList.setText(Controller.MentionsToString());
        MentionList.setWrapText(true);
        MentionList.setTextAlignment(TextAlignment.JUSTIFY);
    }






// region  Testing Input


// region  Testing Input SMS

    @FXML
    private TextField HeaderInputSms;
    @FXML
    private TextField PhoneNumberInputSms;
    @FXML
    private TextArea TextInputSms;

    public void TestingInputSms(ActionEvent event) throws IOException
    {
        SMS sms = new SMS();

        sms.setHeader(HeaderInputSms.getText());
        HeaderInputSms.clear();
        sms.setInternationalPhoneNumber(PhoneNumberInputSms.getText());
        PhoneNumberInputSms.clear();
        sms.setText(TextToSpeak(TextCorrector(TextInputSms.getText())));
        TextInputSms.clear();

        Controller.saveJson(sms.getHeader(), sms.SmsToJson());
        Controller.setSmsD(sms);

        Controller.setTesting(1);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }
// endregion   Testing Input SMS

// region  Testing Input E-mail

    @FXML
    private TextField HeaderInputEmail;
    @FXML
    private TextField AddressInputEmail;
    @FXML
    private TextField SubjectInputEmail;
    @FXML
    private TextArea TextInputEmail;

    public void TestingInputEmail(ActionEvent event) throws IOException
    {
        Email email = new Email();
        email.setHeader(HeaderInputEmail.getText());
        HeaderInputEmail.clear();
        email.setAddress(AddressInputEmail.getText());
        AddressInputEmail.clear();
        email.setSubject(SubjectInputEmail.getText());
        SubjectInputEmail.clear();
        email.setText(TextInputEmail.getText());
        TextInputEmail.clear();

        email.URLs();

        Controller.saveJson(email.getHeader(), email.EmailToJson());
        Controller.setEmailD(email);

        Controller.setTesting(2);


        GuiProxy GP = new GuiProxy();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GP.NextGui(stage);


    }

// endregion   Testing Input E-mail

// region  Testing Input SIR

    @FXML
    private TextField HeaderInputSIR;
    @FXML
    private TextField AddressInputSIR;
    @FXML
    private TextField SubjectInputSIR;
    @FXML
    private TextField SortCodeInputSIR;
    @FXML
    private TextField NatureOfIncidentInputSIR;
    @FXML
    private TextArea TextInputSIR;

    public void TestingInputSIR(ActionEvent event) throws IOException
    {
        SIR sir = new SIR();

        sir.setHeader(HeaderInputSIR.getText());
        HeaderInputSIR.clear();
        sir.setAddress(AddressInputSIR.getText());
        AddressInputSIR.clear();
        sir.setSubject(SubjectInputSIR.getText());
        SubjectInputSIR.clear();
        sir.setSortCode(SortCodeInputSIR.getText());
        SortCodeInputSIR.clear();
        sir.setNatureOfIncident(NatureOfIncidentInputSIR.getText());
        NatureOfIncidentInputSIR.clear();
        sir.setText(TextInputSIR.getText());
        TextInputSIR.clear();

        Controller.addSirList(sir.getSortCode() + sir.getNatureOfIncident());

        sir.URLs();
        Controller.saveJson(sir.getHeader(), sir.SirToJson());
        Controller.setSirD(sir);

        Controller.setTesting(3);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);


    }

// endregion   Testing Input SIR

// region  Testing Input Tweet

    @FXML
    private TextField HeaderInputTweet;
    @FXML
    private TextField IdInputTweet;
    @FXML
    private TextField SubjectInputTweet;
    @FXML
    private TextArea TextInputTweet;

    public void TestingInputTweet(ActionEvent event) throws IOException
    {
        Tweet tweet = new Tweet();

        tweet.setHeader(HeaderInputTweet.getText());
        HeaderInputTweet.clear();
        tweet.setId(IdInputTweet.getText());
        IdInputTweet.clear();
        tweet.setId(SubjectInputTweet.getText());
        SubjectInputTweet.clear();
        tweet.setText(TextToSpeak(TextInputTweet.getText()));
        TextInputTweet.clear();

        tweet.HashtagMention();
        Controller.saveJson(tweet.getHeader(), tweet.TweetToJson());
        Controller.setTweetD(tweet);

        Controller.setTesting(4);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);

    }


}
