package com.example.code;
import com.example.controller.Game_controller;
import com.example.controller.Setting_controller;
import java.io.*;
import java.util.ArrayList;
/**
 * Players class implements Serializable, Comparable,
 * this class contain a user's name and score, and an array list that
 * contain all players' information, this class implements some
 * methods using Serialization to write and read from a ser file.
 * <p>
 *     A Player contains:
 *     <ul>
 *         <li>Player_Name</li>
 *         <li>Player_Score</li>
 *         <li>myPlayers</li>
 *     </ul>
 * <p>
 *      A read methods read players already exist from file to an
 *      array list called myPlayers, and a write method to store all players
 *      information from arraylist myPlayers to file.
 *      <ul>
 *          <li>write_score</li>
 *          <li>read_score</li>
 *      </ul>
 * <p>
 *     A compare method allow to compare players' scores
 *     <ul>
 *         <li>compareTo()</li>
 *     </ul>
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since master
 */
public class Player implements Serializable, Comparable<Player> {
    /**
     * Private Static variable to hold single instance
     */
    private static Player m_player = new Player();

    /**
     * Private Constructor
     */
    private Player (){}

    /**
     *Instantiate class and return a single instance of it
     *
     * @return Player m_player
     */
    public static Player getInstance() {
        return m_player;
    }
    @Serial
    private static final long serialVersionUID = -644401545949000276L;
    public String Player_Name;
    public Integer Player_Score;
    /**
     * the static arraylist myPlayers: used to store all players' information.
     */
    public static ArrayList<Player> myPlayers = new ArrayList<Player>();

    /**
     * Get the name of player
     *
     * @return {@code Player_Name} the name of player
     */
    public String getPlayer_Name() {
        return Player_Name;
    }

    /**
     * Get the score of player
     *
     * @return {@code Player_Score} the score of player
     */
    public int getPlayer_Score(){
        return Player_Score;
    }
    public Player(String Player_Name, int Player_Score) {
        this.Player_Name = Player_Name;
        this.Player_Score = Player_Score;
    }

    /**
     * Using Serialization to store all players from myPlayers to
     * StaticResourcesConfig.Score_File.
     * <p>
     *     Need to do unSerialization first, and add new player at the end of
     *     the arraylist, sort the arraylist by scores before Serialization.
     *
     * @throws IOException  If an input or output exception occurred
     * @throws ClassNotFoundException   If PlayerSorter class not found
     */
    public static void write_score() throws IOException, ClassNotFoundException {
        File file = new File(StaticResourcesConfig.Score_File);
        if(file.exists()){
            read_score();
        }
        Player p = new Player(
                Setting_controller.myName, Game_controller.totalScore);
        myPlayers.add(p);
        PlayerSorter sorter = new PlayerSorter(myPlayers);
        myPlayers = sorter.GetSortedPlayerByScore();
        ObjectOutputStream oos = null;
        oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(myPlayers);
        oos.close();
    }

    /**
     * Using unSerialization to get all player exist and store them in
     * arraylist.
     *
     * @throws IOException  If an input or output exception occurred
     * @throws ClassNotFoundException   If PlayerSorter class not found
     */
    public static void read_score() throws IOException, ClassNotFoundException {
        ObjectInputStream objectInputStream =
                new ObjectInputStream( new FileInputStream(
                        StaticResourcesConfig.Score_File) );
        myPlayers = (ArrayList<Player>) objectInputStream.readObject();
        objectInputStream.close();
    }

    @Override
    public int compareTo (Player p) {
        return (Integer.compare(
                this.getPlayer_Score(), p.getPlayer_Score()));
    }
}
