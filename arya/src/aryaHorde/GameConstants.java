package aryaHorde;

/**
 * Defines constants used throughout the entire game
 * @author Bryan Young
 *
 */
public interface GameConstants {
	/**Defines the String name of the game*/
	public static final String GAME_NAME = "Project Arya";
	
	/**Defines the width of the game window*/
	public static final int WIDTH = 1024;
	/**Defines the height of the game window*/
	public static final int HEIGHT = 720;
	/**Defines the center of the game window on the x axis*/
	public static final int CENTERED_X = WIDTH / 2;
	/**Defines the center of the game window on the y axis*/
	public static final int CENTERED_Y = HEIGHT / 2;
	/**Defines the desired frame rate for the game to run at*/
	public static final int FRAME_RATE = 60;
	
	//State IDs
	/**Defines the state ID for the primary game menu*/
	public static final int MENU = 0;
	/**Defines the state ID for the main game mode*/
	public static final int PLAY = 1;
}
