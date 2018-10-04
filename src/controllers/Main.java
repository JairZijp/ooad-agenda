/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {

    private static Stage thePrimaryStage;
    private static Scene theScene;
    private static int StageWidth = 1000;
    private static int StageHeight = 600;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //load the landing-view
        Parent root = FXMLLoader.load(getClass().getResource("../views/Login.fxml"));

        // set the properties of the stage
        primaryStage.setTitle("OOAD");
        Scene newScene = new Scene(root, StageWidth, StageHeight);
        theScene = newScene;
        primaryStage.setScene(newScene);
        primaryStage.show();
        thePrimaryStage = primaryStage;
    }

    public static void GoToScreen(String name) throws IOException {
        // get the current sizes so the scene can be the same size again
        StageWidth = (int) theScene.getWidth();
        StageHeight = (int) theScene.getHeight();

        //load the new scene in
        Parent root = FXMLLoader.load(Main.class.getResource("../views/" + name));

        // store the scene to use its properties later again
        Scene newScene = new Scene(root, StageWidth, StageHeight);
        theScene = newScene;

        // set the properties of the stage
        thePrimaryStage.setTitle("OOAD");
        thePrimaryStage.setScene(newScene);
        thePrimaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}

