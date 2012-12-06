package aryaHorde;

/**
 * Defines constants used throughout the entire game
 * @author Bryan Young
 *
 */
public interface GameConstants {
	
	/**Defines the String name of the game*/
	public static final String GAME_NAME = "Horde Survival";
	
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
	
	/**Defines the maximum number of enemies in the game world*/
	public static final int MAX_ENEMIES = 0;
	
	
	//Resource links
	/**Defines the dynamic location of the play button*/
	public static final String PLAY_BUTTON_RES = "/res/bucky/playNow.png";
	
	/**Defines the dynamic location of the exit button*/
	public static final String EXIT_BUTTON_RES = "/res/bucky/exitGame.png";
	
	/**Defines the dynamic location of the first game map*/
	public static final String FIRST_MAP_RES = "/res/background/oneLifeBG.png";
	
	/**Defines the dynamic location of the game over image*/
	public static final String GAME_OVER_RES = "/res/background/GameOver.png";
	
	/**Defines the dynamic location of the player's up motion sprite*/
	public static final String PLAYER_UP_RES = "/res/bucky/buckysBack.png";
	
	/**Defines the dynamic location of the player's down motion sprite*/
	public static final String PLAYER_DOWN_RES = "/res/bucky/buckysFront.png";
	
	/**Defines the dynamic location of the player's left motion sprite*/
	public static final String PLAYER_LEFT_RES = "/res/bucky/buckysLeft.png";
	
	/**Defines the dynamic location of the player's right motion sprite*/
	public static final String PLAYER_RIGHT_RES = "/res/bucky/buckysRight.png";
	
	
	//State IDs
	/**Defines the state ID for the primary game menu*/
	public static final int MENU = 0;
	
	/**Defines the state ID for the main game mode*/
	public static final int PLAY = 1;
	
	/**Defines the state ID for when the game is over*/
	public static final int GAME_OVER = 2;
	
	
	//Enemy default point values
	/**Defines the point value for the Wanderer type enemy*/
	public static final int WANDERER_POINTS = 25;
}
