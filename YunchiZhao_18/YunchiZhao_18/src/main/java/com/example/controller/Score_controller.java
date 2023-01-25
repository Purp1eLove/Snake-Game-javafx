package com.example.controller;

import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import com.example.code.*;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static com.example.controller.Game_controller.setIsGameOver;

/**
 * Score_controller show the score screen.
 * <p>
 *     Use a treetable to show the rank, name, and scores of players.
 *     Provide buttons to play again or quit.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class Score_controller extends Application implements Initializable {

    public Button play_again;
    public Button quit;
    public TreeTableView<Score> table;
    public TreeTableColumn<Score, Integer> Rank;
    public TreeTableColumn<Score, String> Player;
    public TreeTableColumn<Score, Integer> Score;
    private AnchorPane root;
    private Stage primaryStage;

    public TreeTableView<com.example.code.Score> getTable() {
        return table;
    }
    public void setTable(TreeTableView<com.example.code.Score> table) {
        this.table = table;
    }
    public TreeTableColumn<com.example.code.Score, Integer> getRank() {
        return Rank;
    }
    public void setRank(TreeTableColumn<com.example.code.Score, Integer> rank) {
        Rank = rank;
    }
    public TreeTableColumn<com.example.code.Score, String> getPlayer() {
        return Player;
    }
    public void setPlayer(TreeTableColumn<com.example.code.Score, String> player) {
        Player = player;
    }
    public TreeTableColumn<com.example.code.Score, Integer> getScore() {
        return Score;
    }
    public void setScore(TreeTableColumn<com.example.code.Score, Integer> score) {
        Score = score;
    }
    public static void main(String[] args) {
        launch(args);
    }
    public Stage getPrimaryStage() {
        return primaryStage;
    }


    @Override
    public void start(Stage primaryStage) throws IOException, ClassNotFoundException {
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
                            StaticResourcesConfig.Score_VIEW_PATH));
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
     * If user click this button, then go back to the first screen.
     *
     * @param actionEvent click the button: play_again
     */
    public void ToStart(ActionEvent actionEvent) {
        Start_controller.setFirst_time(false);
        setIsGameOver(false);
        Stage stage = (Stage)play_again.getScene().getWindow();
        ViewAlter.GotoStart(stage);
    }

    /**
     * If user click this button, then quit the game.
     *
     * @param actionEvent   click the button: quit
     */
    public void Quit(ActionEvent actionEvent) {
        Stage stage = (Stage)quit.getScene().getWindow();
        System.exit(0);
    }

    /**
     * This method used to show information about players.
     * The information includes:
     *     <ul>
     *         <li>Rank</li>
     *         <li>Name</li>
     *         <li>Score</li>
     *     </ul>
     *  <p>
     *      Firstly, read players' names and scores as arraylist players.
     *      Secondly, take player from arraylist players one by one, and add
     *      rank for them, add all these into a TreeItem as score.
     *      After that show TreeItem score on the screen.
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TreeItem<Score> rootItem =
                new TreeItem<>(new Score(1, "YcZ", 0));
        Integer rank = 1;
        ArrayList<Player> players;
        FileInputStream fis= null;
        try {
            fis = new FileInputStream(StaticResourcesConfig.Score_File);
            ObjectInputStream ois=new ObjectInputStream(fis);
            players= (ArrayList<Player>) ois.readObject();
            ois.close();
            fis.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Score> list = FXCollections.observableArrayList();
        for(Player player:players){
            TreeItem<Score> myScore =
                    new TreeItem<>(new Score(rank, player.getPlayer_Name(),
                            player.getPlayer_Score()));
            list.add(
                    new Score(rank, player.getPlayer_Name(),
                            player.getPlayer_Score())
            );
            rootItem.getChildren().add(myScore);
            rank++;
            table.setRoot(rootItem);
            table.setShowRoot(false);

            Rank.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper(
                            param.getValue().getValue().getRank()));
            Player.setCellValueFactory(param ->
                    new ReadOnlyStringWrapper(
                            param.getValue().getValue().getPlayer_Name()));
            Score.setCellValueFactory(param ->
                    new ReadOnlyObjectWrapper(
                            param.getValue().getValue().getPlayer_Score()));
        }

    }
}
