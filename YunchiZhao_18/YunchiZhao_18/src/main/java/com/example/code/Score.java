package com.example.code;
/**
 * This class is a plus version of Player, to show the ranking of players,
 * add rank information to arraylist myPlayers.
 * <p>
 *     The class Player only have the names and scores of players, to show rank
 *     at the last javafx screen, this class add rank.
 * <p>
 *     Rank if different from names and scores, it can be changed after
 *     each time of sort.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class Score {
    /**
     * Private Static variable to hold single instance
     */
    private static Score m_score = new Score();

    /**
     * Private Constructor
     */
    private Score (){}

    /**
     *Instantiate class and return a single instance of it
     *
     * @return Score m_score
     */
    public static Score getInstance() {
        return m_score;
    }
    public String Player_Name;
    public Integer Player_Score;
    public Integer Rank;

    /**
     * Get the name of player
     *
     * @return {@code Player_Name} the name of player
     */
    public String getPlayer_Name() {
        return Player_Name;
    }
    /**
     * Get the rank of player
     *
     * @return {@code Rank} the rank of player
     */
    public int getRank() {
        return Rank;
    }
    /**
     * Get the score of player
     *
     * @return {@code Player_Score} the score of player
     */
    public int getPlayer_Score() {
        return Player_Score;
    }

    public Score(int Rank, String Player_Name, int Player_Score) {
        this.Rank = Rank;
        this.Player_Name = Player_Name;
        this.Player_Score = Player_Score;
    }

}
