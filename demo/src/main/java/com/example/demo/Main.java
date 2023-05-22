package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application
{
    public static void main(String[] args)
    {
        // First we make the system ready by checking the input and textWord file are available
        // Then we clear or make a new output file
        Controller controller = new Controller();
        controller.Checker();

        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException
    {
        FXMLLoader fxmlLoader =
                new FXMLLoader(Main.class.getResource(
                        "Start.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        Image image = new Image("E:\\University\\Edinburgh Napier University - year 3 & 4\\Term 5\\" +
                "SET09102 2022-3 TR1 001 - Software Engineering\\Coursework 22-23\\1\\40617066 Mansour Sami\\" +
                "demo\\icon.png");
        stage.getIcons().add(image);

        stage.setTitle("Napier Bank Messaging System");

        stage.setScene(scene);
        stage.show();
    }


}
