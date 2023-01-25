package com.example.code;
import com.example.controller.Game_controller;
import javazoom.jl.player.Player;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
/**
 * MusicPlayer implements the function to start and stop playing music,
 * start playing music at the start of game, and stop playing at the end.
 * <p>
 *     MusicPlayer include two method, one is used to play music,
 *     the other is used to run and stop at the correct time.
 *     These two methods are:
 *     <ul>
 *          <li>getMusicPlay</li>
 *          <li>play</li>
 *     </ul>
 * <p>
 *     The music file used in this class should be mp3 files.
 *     This class import javazoom.jl.player.Player, use player.play() to
 *     start playing music and player.close() to stop playing.
 *
 * @author <a href = "scyyz17@nottingham.ac.uk">Yunchi Zhao</a>
 * @since main
 */
public class MusicPlayer extends Thread
{
	private final String m_filename;
	private Player m_player;
	public MusicPlayer(String filename)
	{
		this.m_filename = filename;
	}


	/**
	 * Play and stop music
	 * <p>
	 *     Music played in a separated new thread.
	 *     In this thread,if Game_controller.isGameOver is false,
	 *     means game is continue,the music keep playing again and again,
	 *     otherwise stop playing.
	 */
	public void play()
	{
		new Thread()
		{
			@Override
			public void run() {
				super.run();
				try {
					while(true){
						m_player = new Player(new BufferedInputStream(
								new FileInputStream(m_filename)));
						m_player.play();
						if(Game_controller.isGameOver) {
							m_player.close();
							m_player = null;
							break;
						}
					}
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

	/**
	 * This method play the sound when snake eating fruit.
	 * <p>
	 *     Play when every time snake eat fruit.
	 *
	 * @param filename the url of eat food sound mp3 file
	 */
	public static void eatMusic(String filename){
		new Thread()
		{
			@Override
			public void run() {
				super.run();
				try {
					Player eat_player  = new Player(new BufferedInputStream(
							new FileInputStream(filename)));
					eat_player.play();
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		}.start();
	}

	/**
	 * Create a new MusicPlayer with the input as file name, after that start
	 * to play music.
	 *
	 * @param filename	the url and name of the music file
	 */
	public static void getMusicPlay(String filename)
	{
		MusicPlayer musicPlayer = new MusicPlayer(filename);
		musicPlayer.play();
	}
}
