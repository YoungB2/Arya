package aryaHorde;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Manages the game state for when the game is over
 * @author Bryan Young
 *
 */
public class GameOver extends BasicGameState implements GameConstants {

	/**The state ID for this game state*/
	private int ID;
	
	/**Stores the background's graphical resource*/
	private Image background;
	/**Stores the exit button's graphical resource*/
	private Image exitButton;
	
	/**The near x-axis coordinate of the exit button*/
	private int exitButtonX1;
	/**The upper y-axis coordinate of the exit button*/
	private int exitButtonY1;
	/**The far x-axis coordinate of the exit button*/
	private int exitButtonX2;
	/**The lower y-axis coordinate of the exit button*/
	private int exitButtonY2;
	
	/**
	 * Constructs the game state and sets the state ID variable
	 * @param state		The integer value for the state
	 */
	public GameOver(int state) {
		ID = state;
	}
	
	/**
	 * Initializes the member fields and graphical components
	 * @param container			The GameContainer of this game state
	 * @param game				The StateBasedGame 
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		background = new Image(GAME_OVER_RES);
		exitButton = new Image(EXIT_BUTTON_RES);
		
		exitButtonX1 = CENTERED_X - (exitButton.getWidth() / 2);
		exitButtonY1 = ((HEIGHT / 3) * 2) - (exitButton.getHeight() / 2) + 32;
		exitButtonX2 = exitButtonX1 + exitButton.getWidth();
		exitButtonY2 = exitButtonY1 + exitButton.getHeight();
	}

	/**
	 * Renders the graphical components
	 * @param container			The GameContainer of this game state
	 * @param game				The StateBasedGame
	 * @param g					The graphics context to render the graphical components to
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		background.draw(0, 35, (float)0.72);
		g.drawImage(exitButton, exitButtonX1, exitButtonY1);
	}

	/**
	 * Responds to user input, updates this state's components
	 * @param container		The GameContainer for this game state
	 * @param game			The StateBasedGame
	 * @param delta			The rate of change for the update of this state
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		int mouseX = Mouse.getX();
		int mouseY = HEIGHT - Mouse.getY();
		
		if((mouseX > exitButtonX1 && mouseX < exitButtonX2) 
				&& (mouseY > exitButtonY1 && mouseY < exitButtonY2)) {
			if (input.isMouseButtonDown(0)) { 
				System.exit(0); 
			}
		}
	}

	/**
	 * Gets the ID of this game state
	 * @return ID		The {@link #ID} of this game state
	 */
	@Override
	public int getID() {
		return ID;
	}

}
