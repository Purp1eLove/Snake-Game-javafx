package com.example.controller;

import com.example.code.*;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;
/**
 * This is the main class of this game, this is developed in javafx
 * on the basis of the swing project, and
 * show the game screen and implement the control of this game.
 * <p>
 *     There are a series methods to draw the picture of this game.
 *     These methods are:
 *     <ul>
 *         <li>drawShapes</li>
 *         <li>drawSnakes</li>
 *         <li>drawFruits</li>
 *         <li>drawWalls</li>
 *         <li>wallSnakes</li>
 *         <li>snakeMoves</li>
 *     </ul>
 *     These methods draw different parts of screen separately.
 * <p>
 *     Half of the screen show the snake, and half of the screen show basic
 *     information a player need to know for this game.
 *     The information includes:
 *     <ul>
 *         <li>Game title</li>
 *         <li>Best score</li>
 *         <li>Total score</li>
 *         <li>Next score will get</li>
 *         <li>Fruit eaten</li>
 *         <li>How to control</li>
 *     </ul>
 * <p>
 *     This class use a boolean called isGameOver to tell if game is over,
 *     if game is over, game will go to end screen and store scores at end
 *     screen.
 * <p>
 *     Some important points to consider are this class provide all necessary
 *     methods of this game, all variables can be found in
 *     StaticResourcesConfig file, in this cases it is easy to understand and
 *     do improvement on this code.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class Game_controller extends Application {
    private final int[] boardX = new int[484];
    private final int[] boardY = new int[484];
    LinkedList<Position> snake = new LinkedList();
    boolean left = false;
    boolean right = false;
    boolean up = false;
    boolean down = false;
    private int speed = Setting_controller.speed;
    private int m_speed;
    private Image m_lookToRightImage;
    private Image m_lookToLeftImage;
    private Image m_lookToUpImage;
    private Image m_lookToDownImage;
    private Image m_snakeBodyImage;
    private Image m_fruitImage = new Image(Objects.requireNonNull(
            getClass().getResourceAsStream(StaticResourcesConfig.Food[(int)
                    (Math.random() * StaticResourcesConfig.Food.length)])));
    private final Image m_wallImage = new Image(
            Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Wall)));
    private final Image m_logo = new Image(
            Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.GAME_LOGO)));
    private int m_lengthOfSnake = 3;
    private int[] fruitXPos = StaticResourcesConfig.fruitXPos;
    private int[] fruitYPos = StaticResourcesConfig.fruitYPos;
    private int[] wallXPos;
    private int[] wallYPos;
    private Timeline m_timeline = new Timeline();
    private int m_moves = 0;
    public static int totalScore = 0;
    private int m_fruitEaten = 0;
    /**
     * The biggest value of m_score_willget is 99,
     * decrease by time, and the lowest value is 10.
     */
    private int m_score_willget = 99;
    public static int bestScore = HighestScore.ReadBestScorefromTheFile();
    private Random m_random = new Random();
    public static Stage stage = new Stage();
    private int xPos = (int)(0+Math.random()*(24));
    private int yPos = (int)(0+Math.random()*(24));
    public static boolean isGameOver = false;
    // getter and setter

    /**
     * get the snake's position
     *
     * @return {@code LinkedList<Position>}
     * the positions of snake head and bodies.
     */
    public LinkedList<Position> getSnake() {return snake;}
    public static Stage getStage() {
        return stage;
    }
    public static void setStage(Stage stage) {
        Game_controller.stage = stage;
    }
    public static void setIsGameOver(boolean isGameOver) {
        Game_controller.isGameOver = isGameOver;
    }

    private void chooseSnake(){
        if (Setting_controller.getSnake() == 1){
            m_lookToRightImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Snake_Head_Right)));
            m_lookToLeftImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Snake_Head_Left)));
            m_lookToUpImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Snake_Head_Up)));
            m_lookToDownImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Snake_Head_Down)));
            m_snakeBodyImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Snake_Body)));
        }else{
            m_lookToRightImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Blue_Head_Right)));
            m_lookToLeftImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Blue_Head_Left)));
            m_lookToUpImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Blue_Head_Up)));
            m_lookToDownImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Blue_Head_Down)));
            m_snakeBodyImage = new Image(Objects.requireNonNull(
                    getClass().getResourceAsStream(
                            StaticResourcesConfig.Blue_Body)));
        }
    }

    /**
     * This part show information of:
     *     <ul>
     *         <li>Game title</li>
     *         <li>Best score</li>
     *         <li>Total score</li>
     *         <li>Next score will get</li>
     *         <li>Fruit eaten</li>
     *         <li>How to control</li>
     *     </ul>
     *
     * @param gc    GraphicsContext gc = canvas.getGraphicsContext2D();
     */
    private void drawShapes(GraphicsContext gc) {
        if (m_moves == 0) {
            boardX[2] = 40;
          boardX[1] = 60;
            boardX[0] = 80;
            boardY[2] = 100;
            boardY[1] = 100;
            boardY[0] = 100;
            m_score_willget = 99;
            m_timeline.play();
        }
        if (totalScore > bestScore) {
            bestScore = totalScore;
        }
        gc.drawImage(Setting_controller.bg_image, 0, 0);
        gc.setFill(Color.DARKGREY);
        gc.fillRect(20, 20, 520, 520);
        gc.setFill(Color.GREEN);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 26));
        gc.fillText("Snakee Yipee", 565 + 60, 35);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 13));
        gc.fillText("+ " + m_score_willget, 510 + 80, 222);
        gc.setFill(Color.GREEN);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 18));
        gc.fillText("Best Score", 576 + 80, 110);
        gc.fillRect(550 + 80, 120, 140, 30);
        gc.setFill(Color.BLACK);
        gc.fillRect(551 + 80, 121, 138, 28);
        gc.setFill(Color.GREEN);
        gc.fillText(bestScore + "", 550 + 80 + (142 - new Text
                (bestScore + "").getLayoutBounds().getWidth()) / 2, 142);
        gc.fillText("Total Score", 573 + 80, 190);
        gc.fillRect(550 + 80, 200, 140, 30);
        gc.setFill(Color.BLACK);
        gc.fillRect(551 + 80, 201, 138, 28);
        gc.setFill(Color.GREEN);
        gc.fillText(totalScore + "", 550 + 80 + (142 - new Text
                (totalScore + "").getLayoutBounds().getWidth()) / 2, 222);
        gc.fillText("Fruit Eaten", 575 + 80, 270);
        gc.fillRect(550 + 80, 280, 140, 30);
        gc.setFill(Color.BLACK);
        gc.fillRect(551 + 80, 281, 138, 28);
        gc.setFill(Color.GREEN);
        gc.fillText(m_fruitEaten + "", 550 + 80 + (142 - new Text
                (m_fruitEaten + "").getLayoutBounds().getWidth()) / 2, 302);
        gc.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gc.fillText("Controls", 550 + 80, 360);
        gc.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        gc.fillText("Pause / Start : Space", 550 + 80, 385);
        gc.fillText("Move Up : Arrow Up", 550 + 80, 410);
        gc.fillText("Move Down : Arrow Down", 550 + 80, 435);
        gc.fillText("Move Left : Arrow Left", 550 + 80, 460);
        gc.fillText("Move Right : Arrow Right", 550 + 80, 485);
        if (m_moves == 0){
            gc.drawImage(m_lookToRightImage, boardX[0], boardY[0]);
        }

        snake.clear();
    }

    /**
     * Draw different number of walls follow different levels.
     *
     * @param gc    GraphicsContext gc = canvas.getGraphicsContext2D();
     */
    private void drawWalls(GraphicsContext gc){

        if(speed == 1) {

        }else if(speed == 2){
            wallXPos = new int[]{80, 320, 160};
            wallYPos = new int[]{420, 320, 180};
            for(int a = 0;a< wallXPos.length; a++){
                gc.drawImage(m_wallImage, wallXPos[a], wallYPos[a]);
            }

        }else if(speed == 3){
            wallXPos = new int[]{80, 320, 480, 160, 380};
            wallYPos = new int[]{420, 320, 140, 180, 420};
            for(int a = 0;a< wallXPos.length; a++){
                gc.drawImage(m_wallImage, wallXPos[a], wallYPos[a]);
            }
        }
    }

    /**
     * Add a new snake body at the end of snake if snake eat a fruit,
     * set isGameOver equal to true if the snake head and one of the
     * snake bodies at the position.
     * <p>
     *     implement this function by compare objects' positions.
     *
     * @param gc    GraphicsContext gc = canvas.getGraphicsContext2D();
     */
    private void drawSnakes(GraphicsContext gc) {
        for (int i = 0; i < m_lengthOfSnake; i++) {
            if (i == 0 && left) {
                gc.drawImage(m_lookToLeftImage, boardX[i], boardY[i]);
            } else if (i == 0 && right) {
                gc.drawImage(m_lookToRightImage, boardX[i], boardY[i]);
            } else if (i == 0 && up) {
                gc.drawImage(m_lookToUpImage, boardX[i], boardY[i]);
            } else if (i == 0 && down) {
                gc.drawImage(m_lookToDownImage, boardX[i], boardY[i]);
            } else if (i != 0) {
                gc.drawImage(m_snakeBodyImage, boardX[i], boardY[i]);
            }
            snake.add(new Position(boardX[i], boardY[i]));
        }

        if (m_score_willget != 10) {
            m_score_willget--;
        }

        for (int i = 1; i < m_lengthOfSnake; i++) {

            if (boardX[i] == boardX[0] && boardY[i] == boardY[0]) {
                if (right)
                    gc.drawImage(m_lookToRightImage, boardX[1], boardY[1]);
                else if (left)
                    gc.drawImage(m_lookToLeftImage, boardX[1], boardY[1]);
                else if (up)
                    gc.drawImage(m_lookToUpImage, boardX[1], boardY[1]);
                else if (down)
                    gc.drawImage(m_lookToDownImage, boardX[1], boardY[1]);
                setIsGameOver(true);
            }
        }
    }

    /**
     * Compare snake head's position and walls' positions
     * to check if game is over.
     */
    private void wallSnakes() {
        for (int i = 0; i < wallYPos.length; i++) {
            if (wallXPos[i]== boardX[0] && boardY[0]==wallYPos[i]){
                setIsGameOver(true);
            }
        }
    }

    /**
     * if isGameOver is true, this method stop the game and try to store
     * this user's score, go to end screen at the same time.
     */
    private void gameOver() {
        if(isGameOver){
            m_timeline.stop();
            HighestScore.WriteBestScoreInTheFile();
            ViewAlter.GotoEnd(stage);
        }
    }

    /**
     * this method implements two functions.
     * <p>
     *     The first is calculate the score the player will get when the snake
     *     eat next fruit.
     * <p>
     *     The other one is to randomly generate new fruit after the snake eat
     *     one fruit.
     *
     * @param gc    GraphicsContext gc = canvas.getGraphicsContext2D();
     */
    private void drawFruits(GraphicsContext gc) {
       if( fruitXPos[xPos]-15 <= boardX[0] && boardX[0]<=fruitXPos[xPos]+ 15 && fruitYPos[yPos]-15 <= boardY[0] && boardY[0]<=fruitYPos[yPos]+ 15){
            totalScore += m_score_willget;
            m_score_willget = 99;
            m_fruitEaten++;
            m_lengthOfSnake++;
        }

        for (int i = 0; i < snake.size(); i++) {
            if ((fruitXPos[xPos] == boardX[0]) && (fruitYPos[yPos] == boardY[0])) {
                xPos = m_random.nextInt(24);
                yPos = m_random.nextInt(24);
                m_fruitImage = new Image(Objects.requireNonNull(
                        getClass().getResourceAsStream(StaticResourcesConfig.Food[(int)
                                (Math.random() * StaticResourcesConfig.Food.length)])));
                MusicPlayer.eatMusic(StaticResourcesConfig.MUSIC_Eat_MP_3);

            }
        }
        gc.drawImage(m_fruitImage, fruitXPos[xPos], fruitYPos[yPos]);
    }

    /**
     * This method make the snake make right move in specific situations.
     * <p>
     *     The area for snake to move is a 500*500 square, it may be a bit
     *     small for snake to move, on the other hand, the faster the snake eat,
     *     the more scores will get, so the snake can com out from the other
     *     side of this square if the snake move to one side of the square.
     *
     * @param gc    GraphicsContext gc = canvas.getGraphicsContext2D();
     */
    private void snakeMoves(GraphicsContext gc) {
        m_speed =20;

        if (right)
        {
            for (int i = m_lengthOfSnake - 1; i >= 0; i--)
                boardY[i + 1] = boardY[i];

            for (int i = m_lengthOfSnake; i >= 0; i--)
            {
                if (i == 0)
                    boardX[i] = boardX[i] + m_speed;
                else
                    boardX[i] = boardX[i - 1] + m_speed - 20;


                if (boardX[i] > 520)
                    boardX[i] = 20;
            }
        }
        else if (left) {
            for (int i = m_lengthOfSnake - 1; i >= 0; i--)
                boardY[i + 1] = boardY[i];

            for (int i = m_lengthOfSnake; i >= 0; i--)
            {
                if (i == 0)
                    boardX[i] = boardX[i] - m_speed;

                else
                    boardX[i] = boardX[i - 1] - m_speed +20;

                if (boardX[i] < 20)
                    boardX[i] = 520;
            }
        }
        else if (up)
        {
            for (int i = m_lengthOfSnake - 1; i >= 0; i--)
                boardX[i + 1] = boardX[i];

            for (int i = m_lengthOfSnake; i >= 0; i--)
            {
                if (i == 0)
                    boardY[i] = boardY[i] - m_speed;
                else
                    boardY[i] = boardY[i - 1] - m_speed +20;

                if (boardY[i] < 20)
                    boardY[i] = 520;
            }
        }
        else if (down) {
            for (int i = m_lengthOfSnake - 1; i >= 0; i--)
                boardX[i + 1] = boardX[i];

            for (int i = m_lengthOfSnake; i >= 0; i--) {
                if (i == 0)
                    boardY[i] = boardY[i] + m_speed;
                else
                    boardY[i] = boardY[i - 1] + m_speed - 20;

                if (boardY[i] > 520)
                    boardY[i] = 20;
            }
        }
    }

    /**
     * Calculate steps the snake have moved, and do right move after the user
     * press keys.
     * <p>
     *     Snake will move to the corresponding directions of keys,
     *     if the key user press is space, the game will pause till user
     *     press space again.
     *
     * @param scene The scene that this screen shows
     */
    private void snakeControl(Scene scene) {
        scene.addEventFilter(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
            if (null != e.getCode()) {
                switch (e.getCode()) {
                    case SPACE:
                        if (m_timeline.getStatus() ==
                                Timeline.Status.RUNNING && !isGameOver) {
                            m_timeline.stop();
                        }
                        else if (m_timeline.getStatus()
                                != Timeline.Status.RUNNING && !isGameOver) {
                            m_timeline.play();
                        }
                        break;
                    case RIGHT:
                        m_moves++;
                        right = true;
                        if (!left) {
                            right = true;
                        }
                        else {
                            right = false;
                            left = true;
                        }
                        up = false;
                        down = false;
                        break;
                    case LEFT:
                        m_moves++;
                        left = true;
                        if (!right) {
                            left = true;
                        }
                        else {
                            left = false;
                            right = true;
                        }
                        up = false;
                        down = false;
                        break;
                    case UP:
                        m_moves++;
                        up = true;
                        if (!down) {
                            up = true;
                        }
                        else {
                            up = false;
                            down = true;
                        }
                        left = false;
                        right = false;
                        break;
                    case DOWN:
                        m_moves++;
                        down = true;
                        if (!up) {
                            down = true;
                        }
                        else {
                            up = true;
                            down = false;
                        }
                        left = false;
                        right = false;
                        break;
                }
            }
        });
    }
    @Override
    public void start(Stage stage) {

        chooseSnake();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                System.exit(0);
            }
        });
        Game_controller.stage = stage;
        Canvas canvas = new Canvas(870, 560);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        Pane root = new Pane();
        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setTitle("Snakee Yipee 2D");
        stage.getIcons().add(m_logo);
        stage.setScene(scene);
        stage.show();

        m_timeline.getKeyFrames().add(
                new KeyFrame(Duration.seconds(0.1), (ActionEvent event) -> {
            drawShapes(gc);
            drawSnakes(gc);
            drawFruits(gc);
            drawWalls(gc);
            if(speed != 1){
                wallSnakes();
            }
            snakeMoves(gc);
            gameOver();
        }));

        m_timeline.setCycleCount(Timeline.INDEFINITE);
        m_timeline.play();
        snakeControl(scene);
    }
    public static void main(String[] args) {
        launch(args);
    }

}