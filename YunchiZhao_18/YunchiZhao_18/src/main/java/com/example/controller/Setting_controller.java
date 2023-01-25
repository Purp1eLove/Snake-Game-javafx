package com.example.controller;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.code.*;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
/**
 * Setting_controller show the score screen.
 * <p>
 *     This page contain the function to change background, and change
 *     the difficulty level of game. There is a text field
 *     at the bottom of this screen to allow user enter their name.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class Setting_controller extends Application implements Initializable {
    public Button Start;
    public ToggleGroup bg;
    public RadioButton radio1;
    public ImageView sky;
    public ImageView fruits;
    @FXML
    public Slider mySpeed;
    @FXML
    public Label myspeed;
    public static int speed;
    public static int snake = 1;
    public static Image bg_image = new Image(StaticResourcesConfig.Backgroud_1);
    @FXML
    public TextField Name;
    public static String myName;
    public RadioButton radio2;
    public Label ifName;
    public Button help;
    public RadioButton snake1;
    public RadioButton snake2;
    private static Stage primaryStage;
    private AnchorPane root;
    public Button getStart() {return Start;}
    public void setStart(Button start) {
        Start = start;
    }
    public TextField getName() {
        return Name;
    }
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    /**
     * Get the user's choice of snake.
     * <p>
     *     If snake = 1, green snake,
     *     If snake = 2, blue snake.
     *
     * @return {@code 1} if the {@code snake1} is selected;
     *         {@code 2} if the {@code snake2} is selected.
     */
    public static int getSnake() {
        return snake;
    }

    /**
     * Set the value of snake by user's choice.
     *
     * @param snake the choice of user
     */
    public static void setSnake(int snake) {
        Setting_controller.snake = snake;
    }

    /**
     * Set the name of player
     *
     * @param myName the name of player
     */
    public static void setMyName(String myName) {
        Setting_controller.myName = myName;
    }

    /**
     * set textfield
     *
     * @param name the textfield
     */
    public void setName(TextField name) {
        Name = name;
    }

    /**
     * set the level of game
     *
     * @param speed the level of game
     */
    public static void setSpeed(int speed) {
        Setting_controller.speed = speed;
    }
    public static void main(String[] args) {
        launch(args);
    }
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
                            StaticResourcesConfig.Setting_VIEW_PATH));
            root = (AnchorPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * If user click this button, then go to the game screen.
     *
     * @param actionEvent   click the button: Start
     */
    public void ToGame(ActionEvent actionEvent) {
        if(Name.getText() == ""){
            ifName.setText("Please enter your name!");
        }
        else{
            setMyName(Name.getText());
            Stage stage = (Stage)Start.getScene().getWindow();
            stage.close();
            ViewAlter.GotoPlay(stage);
        }
    }
    /**
     * If user click this button, then go to the introduction screen.
     *
     * @param actionEvent   click the button: help
     */
    public void gotoIntroduction1(ActionEvent actionEvent) {
        Stage stage = (Stage)help.getScene().getWindow();
        ViewAlter.GotoIntroduction_1(stage);
    }

    /**
     * Get level from slider and show it on the label
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setSpeed((int) mySpeed.getValue());
        myspeed.setText("Normal");
        mySpeed.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number number, Number t1) {
                speed = (int) mySpeed.getValue();
                if (speed == 1){
                    myspeed.setText("Easy");
                }else if (speed ==2){
                    myspeed.setText("Normal");
                }else{
                    myspeed.setText("Hard");
                }

            }
        });
    }

    /**
     * Choose from 2 different kinds of background.
     * <p>
     *      Two radio buttons are set as toggle button, it is background 1 by default
     *      if user do not make choice.
     *
     * @param actionEvent   choose one of the toggle button
     */
    public void Get_bg(ActionEvent actionEvent) {
        if(!radio1.isSelected() && !radio2.isSelected()){
            bg_image = new Image(StaticResourcesConfig.Backgroud_1);
        }
        else if(radio1.isSelected()){
            bg_image = new Image(StaticResourcesConfig.Backgroud_1);
        }else if (radio2.isSelected()){
            bg_image = new Image(StaticResourcesConfig.Backgroud_2);
        }
    }
    @FXML
    public void get_Name(ActionEvent actionEvent) {
    }
    /**
     * Choose from 2 different kinds of snake.
     * <p>
     *      Two radio buttons are set as toggle button, it is snake 1 by default
     *      if user do not make choice.
     *
     * @param actionEvent   choose one of the toggle button
     */
    public void Get_snake(ActionEvent actionEvent) {
        if(!snake1.isSelected() && !snake2.isSelected()){
            setSnake(1);
        } else if(snake1.isSelected()){
            setSnake(1);
        } else if (snake2.isSelected()){
            setSnake(2);
        }
    }
}
