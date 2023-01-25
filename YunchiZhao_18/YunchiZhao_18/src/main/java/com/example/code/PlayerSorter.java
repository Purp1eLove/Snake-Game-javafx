package com.example.code;
import java.util.ArrayList;
import java.util.Collections;
/**
 * PlayerSorter is a class used to sort an arraylist of players by m_player's
 * scores.
 * <p>
 *     This class contain a GetSortedPlayerByScore() method, this method can
 *     sort the scores from high to low.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class PlayerSorter {
    private ArrayList m_player = new ArrayList<>();
    public PlayerSorter(ArrayList m_player) {
        this.m_player = m_player;
    }

    /**
     * This method use Collections.sort to get m_player sorted from low scores to
     * high scores. After that use Collections.reverse to sorted from
     * high score to low scores
     *
     * @return {@code m_player} sorted from high score to low scores
     */
    public ArrayList GetSortedPlayerByScore() {
        Collections.sort(m_player);
        Collections.reverse(m_player);
        return m_player;
    }
}
