package com.example.code;
/**
 * Position is a class used to store object's position in javafx screen.
 * <p>
 *     Store the coordinate location of objects such as snake head, foods.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since new
 */
public class Position {
    /**
     * Private Static variable to hold single instance
     */
    private static Position m_position = new Position();

    /**
     * Private Constructor
     */
    private Position (){}

    /**
     *Instantiate class and return a single instance of it
     *
     * @return Position m_position
     */
    public static Position getInstance() {
        return m_position;
    }
    public int m_Xposition;
    public int m_Yposition;

    /**
     * The position in javafx screen
     *
     * @param m_Xposition the m_Xposition coordinate
     * @param m_Yposition the m_Yposition coordinate
     */
    public Position(int m_Xposition, int m_Yposition) {
        this.m_Xposition = m_Xposition;
        this.m_Yposition = m_Yposition;
    }

}