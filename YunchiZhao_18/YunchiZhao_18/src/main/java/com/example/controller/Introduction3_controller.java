package com.example.controller;

import com.example.code.StaticResourcesConfig;
import com.example.code.ViewAlter;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
/**
 * The third introduction screen of the game.
 * <p>
 *     This screen will introduce different levels of this game.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since master
 */
public class Introduction3_controller extends Application {
    public Button previous;
    public Button back;



    public static void main(String[] args) {
        launch(args);
    }
    private AnchorPane root;
    private Stage primaryStage;
    private Scene scene;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Snakee Yipee");


        initRootLayout();
        scene.setOnKeyPressed(e -> {
            if (null != e.getCode()) {
                if(e.getCode()== KeyCode.SPACE){
                    ViewAlter.GotoScore(primaryStage);
                }
            }
        });
    }
    /**
     * Initial and show the screen.
     * <p>
     *     Load the fxml file first.
     *     Initial and show the screen.
     */
    private void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Intro_3_VIEW_PATH));
            root = (AnchorPane) loader.load();
            scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     *  If user click the button go back to setting screen.
     *
     * @param actionEvent   Go to next page if click the next button
     */
    public void gotoSetting(ActionEvent actionEvent) {
        Stage stage = (Stage)back.getScene().getWindow();
        ViewAlter.GotoSetting(stage);
    }

    /**
     *  If user click the button, go to previous screen.
     *
     * @param actionEvent   Go to next page if click the previous button
     */
    public void gotoIntroduction2(ActionEvent actionEvent) {
        Stage stage = (Stage)previous.getScene().getWindow();
        ViewAlter.GotoIntroduction_2(stage);
    }
}
