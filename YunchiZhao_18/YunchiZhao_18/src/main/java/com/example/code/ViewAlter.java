package com.example.code;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.controller.*;
/**
 * This class contain methods to jump to different screens.
 * <p>
 *     This class contains similar methods, need pass the stage of current
 *     screen, and open new screen on the stage.
 *     These methods are:
 *     <ul>
 *         <li>GotoSetting
 *         <li>GotoPlay
 *         <li>GotoStart
 *         <li>GotoScore
 *         <li>GotoEnd
 *     </ul>
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since master
 */
public class ViewAlter extends Application {
    /**
     * Method to go to setting page.
     *
     * {@link Setting_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoSetting(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Setting_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }
    /**
     * Method to go to game page.
     *
     * {@link Game_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoPlay(Stage stage) {
        Game_controller game = new Game_controller();
        game.start(Game_controller.stage);
    }
    /**
     * Method to go to Start page.
     *
     * {@link Start_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoStart(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Start_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }
    /**
     * Method to go to Score page.
     *
     * {@link Score_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoScore(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Score_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }
    /**
     * Method to go to End page.
     *
     * {@link End_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoEnd(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.End_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }

    /**
     * Method to go to the first introduction page.
     *
     * {@link Introduction1_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoIntroduction_1(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Intro_1_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }
    /**
     * Method to go to the second introduction page.
     *
     * {@link Introduction2_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoIntroduction_2(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Intro_2_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }
    /**
     * Method to go to the third introduction page.
     *
     * {@link Introduction3_controller}
     *
     * @param stage The stage of current screen.
     */
    public static void GotoIntroduction_3(Stage stage) {
        try {
            AnchorPane root;
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Start_controller.class.getResource(
                    StaticResourcesConfig.Intro_3_VIEW_PATH));
            root = (AnchorPane) loader.load();

            // Show the scene containing the root layout.
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

        } catch (Exception ex) {

        }
    }

    public static void main(String[] args) {
        launch(args);
    }



    @Override
    public void start(Stage stage) throws Exception {

    }
}

