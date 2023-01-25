package com.example.controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.code.*;
import javafx.stage.WindowEvent;

import java.io.IOException;
/**
 * The end screen of the game.
 * <p>
 *     This screen will show after the end of game,
 *     only have one button to implement basic function to go to next screen.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since master
 */
public class End_controller extends Application {

    public ImageView end;
    public Button end_next;
    private AnchorPane m_root;
    private Stage primaryStage;
    private Scene m_scene;

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Show the End screen.
     *
     * @param primaryStage The stage of this end screen
     * @throws InterruptedException
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Snakee Yipee");


        initRootLayout();
        m_scene.setOnKeyPressed(e -> {
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
            // Load m_root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.End_VIEW_PATH));
            m_root = (AnchorPane) loader.load();
            m_scene = new Scene(m_root);
            primaryStage.setScene(m_scene);
            primaryStage.show();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     *  If user click the button or SPACE on the keyboard,
     *  go to score screen.
     *
     * @param actionEvent   Go to score page if click the NEXT button
     * @throws IOException  if an input or output exception occurred
     * @throws ClassNotFoundException
     */
    public void Gotoscore(ActionEvent actionEvent)
            throws IOException, ClassNotFoundException {
        Player.write_score();
        Stage stage = (Stage)end_next.getScene().getWindow();
        ViewAlter.GotoScore(stage);
    }
}