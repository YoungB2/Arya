package aryaHorde;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Defines the main menu mode of the application 
 * @author Bryan Young
 */
public class Menu extends BasicGameState implements GameConstants{

	/**The graphical resource for the play button*/
	Image playButton;
	/**The graphical resource for the exit button*/
	Image exitButton;
	/**The state ID for this game state*/
	private int ID;
	
	/**The near x-axis coordinate of the play button*/
	private int playButtonX1;
	/**The upper y-axis coordinate of the play button*/
	private int playButtonY1;
	/**The far x-axis coordinate of the play button*/
	private int playButtonX2;
	/**The lower y-axis coordinate of the play button*/
	private int playButtonY2;
	
	/**The near x-axis coordinate of the exit button*/
	private int exitButtonX1;
	/**The upper y-axis coordinate of the exit button*/
	private int exitButtonY1;
	/**The far x-axis coordinate of the exit button*/
	private int exitButtonX2;
	/**The lower y-axis coordinate of the exit button*/
	private int exitButtonY2;
		
	/**
	 * The constructor for this game state
	 * @param state		The state ID
	 */
	public Menu(int state) {
		ID = state;
	}
	
	/**
	 * Initializes the variables and objects needed for the start of the game in this state
	 * @param container		The GameContainer for the game
	 * @param game			The StateBasedGame for this game
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		playButton = new Image("res/bucky/playNow.png");
		exitButton = new Image("res/bucky/exitGame.png");
		
		playButtonX1 = CENTERED_X - (playButton.getWidth() / 2);
		playButtonY1 = (container.getHeight() / 3) - (playButton.getHeight() /2);
		playButtonX2 = playButtonX1 + playButton.getWidth();
		playButtonY2 = playButtonY1 + playButton.getHeight();
		
		exitButtonX1 = CENTERED_X - (exitButton.getWidth() / 2);
		exitButtonY1 = ((HEIGHT / 3) * 2) - (exitButton.getHeight() / 2);
		exitButtonX2 = exitButtonX1 + exitButton.getWidth();
		exitButtonY2 = exitButtonY1 + exitButton.getHeight();		
	}

	
	/**
	 * Renders the graphical components of the menu game state
	 * @param container		The GameContainer of the Menu game state
	 * @param game			The StateBasedGame
	 * @param g				The graphics context to render graphical compontents to
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		g.drawImage(playButton, playButtonX1, playButtonY1);
		g.drawImage(exitButton, exitButtonX1, exitButtonY1);
	}

	/**
	 * Detects user mouse input to change the state of the game for the menu game state
	 * @param container		The GameContainer for this state
	 * @param game			The StateBasedGame for this state
	 * @param delta			The rate of change for the update of this state
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		int mouseX = Mouse.getX();
		int mouseY = HEIGHT - Mouse.getY();	//Flip mouse y axis to the same as the graphics rendering origin
		
		Input input = container.getInput();
		
		if((mouseX > playButtonX1 && mouseX < playButtonX2) 
				&& (mouseY > playButtonY1 && mouseY < playButtonY2)) {
			if (input.isMouseButtonDown(0)) { 
				game.enterState(PLAY); 
			}
		}
		
		if((mouseX > exitButtonX1 && mouseX < exitButtonX2) 
				&& (mouseY > exitButtonY1 && mouseY < exitButtonY2)) {
			if (input.isMouseButtonDown(0)) { 
				System.exit(0); 
			}
		}
	}

	/**
	 * Gets the ID of this state
	 * @return ID		The {@link #ID} of this state
	 */
	@Override
	public int getID() {
		return ID;
	}

}
