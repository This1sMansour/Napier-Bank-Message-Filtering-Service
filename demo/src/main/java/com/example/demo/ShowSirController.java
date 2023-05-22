package com.example.demo;



import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ShowSirController
{
    @FXML
    private Label SirAddress;

    @FXML
    private Label SirHeader;

    @FXML
    private Label SirNatureIncident;

    @FXML
    private Label SirSortCode;

    @FXML
    private Label SirSubject;

    @FXML
    private Label SirText;

    @FXML
    private Label SirQuarantine;
    public void setValue(ArrayList<String> sir)
    {
        SirHeader.setText(sir.get(0));
        SirAddress.setText(sir.get(1));
        SirSubject.setText(sir.get(2));
        SirSortCode.setText(sir.get(3));
        SirNatureIncident.setText(sir.get(4));
        SirText.setText(sir.get(5));
        SirQuarantine.setText(sir.get(6));

        SirText.setWrapText(true);
        SirText.setTextAlignment(TextAlignment.JUSTIFY);
        SirQuarantine.setWrapText(true);
        SirQuarantine.setTextAlignment(TextAlignment.JUSTIFY);
    }

    public void Next(ActionEvent event) throws IOException
    {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        GuiProxy GP = new GuiProxy();
        GP.NextGui(stage);
    }
}
