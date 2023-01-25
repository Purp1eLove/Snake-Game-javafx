package com.example.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.code.*;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.util.Objects;
/**
 * This is the first screen of this game.
 * <p>
 *     there are two buttons, one is used to go to next screen,
 *     the other is used to quit.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since master
 */
public class Start_controller extends Application {
    public  Button button_setting;
    public Button button_quit;
    private Stage primaryStage;
    private AnchorPane root;
    private Image logo = new Image(Objects.requireNonNull(getClass().getResourceAsStream(StaticResourcesConfig.GAME_LOGO)));
    public Stage getPrimaryStage() {
        return primaryStage;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public static boolean first_time = true;

    public static void setFirst_time(boolean first_time) {
        Start_controller.first_time = first_time;
    }

    @Override
    public void start(Stage primaryStage) {
        if(first_time){
            MusicPlayer.getMusicPlay(StaticResourcesConfig.MUSIC_BGM_MP_3);
        }
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Snakee Yipee");
        initRootLayout();
    }

    /**
     * Initial and show the screen.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(
                    Start_controller.class.getResource(
                            StaticResourcesConfig.Start_VIEW_PATH));
            root = (AnchorPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.getIcons().add(logo);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If user click this button, then go to the setting screen.
     *
     * @param actionEvent click the button: button_setting
     */
    public void GotoSetting(ActionEvent actionEvent) {
        Stage stage = (Stage)button_setting.getScene().getWindow();
        ViewAlter.GotoSetting(stage);
    }

    /**
     * If user click this button, then quit.
     *
     * @param actionEvent click the button: button_quit
     */
    public void Quit(ActionEvent actionEvent) {
        Stage stage = (Stage)button_quit.getScene().getWindow();
        stage.close();
        System.exit(0);
    }
}
