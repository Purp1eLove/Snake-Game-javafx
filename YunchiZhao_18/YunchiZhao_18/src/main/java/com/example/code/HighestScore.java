package com.example.code;
import com.example.controller.Game_controller;
import java.io.*;

/**
 * HighestScore is a class implement methods to compare user's score
 * and the highest score in the past games, and write and read the highest
 * score from file: snake-game-best-score.txt by java IO.
 * <p>
 *     The HighestScore include two methods about store and read the best score.
 *     These methods ara:
 *     <ul>
 *         <li>WriteBestScoreInTheFile
 *         <li>ReadBestScorefromTheFile
 *     </ul>
 * <p>
 *     Some important points to consider that are that the the ReadBestScorefromTheFile
 *     only called when current score is more that highest store in the file.
 *     Also, the score should not be stored if users quit the game midway.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class HighestScore {

    /**
     * Store current score as best score in file.
     * <p>
     *     Store in snake-game-best-score.
     *     Only called when current score is better.
     *     {@link Game_controller} Used in gameOver method.
     */
    public static void WriteBestScoreInTheFile() {
        if (Game_controller.totalScore >= Game_controller.bestScore) {
            try {
                FileOutputStream fos = new FileOutputStream(
                        StaticResourcesConfig.File_High);
                OutputStreamWriter osw = new OutputStreamWriter(
                        fos, "UTF-8");
                osw.write(Game_controller.bestScore + "");
                osw.flush();
                osw.close();
            } catch (IOException e) {
            }
        }
    }

    /**
     * Read the highest score from file.
     * <p>
     *     Read from snake-game-best-score.
     *     {@link Game_controller} Used to read score and store in bestscore.
     */
    public static int ReadBestScorefromTheFile() {
        File file = new File(StaticResourcesConfig.File_High);
        if(!file.exists()){
            try {
                if(file.createNewFile()){
                }
            }catch (IOException ioe){
                ioe.printStackTrace();
            }
        }
        try {
            InputStreamReader isr = new InputStreamReader(
                    new FileInputStream(
                            StaticResourcesConfig.File_High), "UTF-8");
            BufferedReader br = new BufferedReader(isr);
            String str = "";
            int c;
            while ((c = br.read()) != -1) {
                if (Character.isDigit(c)) {
                    str += (char) c;
                }
            }
            if (str.equals("")) {
                str = "0";
            }
            br.close();
            return Integer.parseInt(str);
        } catch (IOException e) {
        }
        return 0;
    }
}
