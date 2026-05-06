package controller;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

/**
 * Class to manage the sound effects and the music of the game
 * 
 * @author Scarselli Ilaria, matricola 1918975
 *
 */
public class Sound {
	
	Clip clip;
	URL soundURL[] = new URL[20];
	
	/**
	 * Class constructor to insert in the array all the audio tracks used
	 */
	public Sound() {
		
		soundURL[0] = getClass().getResource("/sound/fanfare.wav");
		soundURL[1] = getClass().getResource("/sound/ending.wav");
		soundURL[2] = getClass().getResource("/sound/bomb_explodes.wav");
		soundURL[3] = getClass().getResource("/sound/bomberman_dies.wav");
		soundURL[4] = getClass().getResource("/sound/enemy_dies.wav");
		soundURL[5] = getClass().getResource("/sound/item_get.wav");
		soundURL[6] = getClass().getResource("/sound/pause.wav");
		soundURL[7] = getClass().getResource("/sound/place_bomb.wav");
		soundURL[8] = getClass().getResource("/sound/stage_clear.wav");
		soundURL[9] = getClass().getResource("/sound/stage_intro.wav");
		soundURL[10] = getClass().getResource("/sound/title_screen_cursor.wav");
		soundURL[11] = getClass().getResource("/sound/title_screen_select.wav");
		soundURL[12] = getClass().getResource("/sound/gameover.wav");
		soundURL[13] = getClass().getResource("/sound/hitmonster.wav");
		soundURL[14] = getClass().getResource("/sound/levelup.wav");
		soundURL[15] = getClass().getResource("/sound/level.wav");
		soundURL[16] = getClass().getResource("/sound/stairs.wav");
		soundURL[17] = getClass().getResource("/sound/walking.wav");
	}
	
	/**
	 * Sets the track to play (this method just selects and store it)
	 * @param i		the index of the track
	 */
	public void setFile(int i) {
		
		try {
			
			AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
			clip = AudioSystem.getClip();
			clip.open(ais);
			
		} catch(Exception e) {
			
		}
		
	}
	
	/**
	 * Plays the track selected with the setFile method
	 * @see		Sound#setFile(int i)
	 */
	public void play() {
		clip.start();
	}
	
	/**
	 * Loops the track selected with the setFile method
	 * @see		Sound#setFile(int i)
	 */
	public void loop() {
		clip.loop(Clip.LOOP_CONTINUOUSLY);
	}
	
	/**
	 * Stops the track selected with the setFile method
	 * @see		Sound#setFile(int i)
	 */
	public void stop() {
		clip.stop();
	}
	
}
