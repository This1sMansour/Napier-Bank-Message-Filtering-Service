package com.example.demo;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowEmailController
{
    @FXML
    private Label EmailAddress;

    @FXML
    private Label EmailHeader;

    @FXML
    private Label EmailSubject;

    @FXML
    private Label EmailText;

    @FXML
    private Label EmailQuarantine;

    public void setValue(ArrayList<String> email)
    {
        // Email email = Controller.getEmailD();
        EmailHeader.setText(email.get(0));
        EmailAddress.setText(email.get(1));
        EmailSubject.setText(email.get(2));
        EmailText.setText(email.get(3));
        EmailQuarantine.setText(email.get(4));

        EmailText.setWrapText(true);
        EmailText.setTextAlignment(TextAlignment.JUSTIFY);
        EmailQuarantine.setWrapText(true);
        EmailQuarantine.setTextAlignment(TextAlignment.JUSTIFY);
    }


    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }
}
