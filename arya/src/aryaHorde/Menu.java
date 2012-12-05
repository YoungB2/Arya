package aryaHorde;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * Defines the main menu mode of the application 
 * @author Bryan Young
 */
public class Menu extends BasicGameState {

	/**The graphical resource for the play button*/
	Image playButton;
	/**The graphical resource for the exit button*/
	Image exitButton;
	/**The state ID for this game state*/
	private int ID;
		
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
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		int playButtonX1 = (container.getWidth() / 2) - (playButton.getWidth() / 2);
		int playButtonY1 = (container.getHeight() / 3) - (playButton.getHeight() /2);
		int exitButtonX1 = (container.getWidth() / 2) - (exitButton.getWidth() / 2);
		int exitButtonY1 = ((container.getHeight() / 3) * 2) - (exitButton.getHeight() / 2);
		
		g.drawImage(playButton, playButtonX1, playButtonY1);
		g.drawImage(exitButton, exitButtonX1, exitButtonY1);
	}

	/**
	 * Detects user mouse input to change the state of the game
	 * @param container		The GameContainer for this state
	 * @param game			The StateBasedGame for this state
	 * @param delta			The rate of change for the update of this state
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		int mouseX = Mouse.getX();
		int mouseY = Math.abs(container.getHeight() - Mouse.getY());
		
		int playButtonX1 = (container.getWidth() / 2) - (playButton.getWidth() / 2);
		int playButtonY1 = (container.getHeight() / 3) - (playButton.getHeight() /2);
		int playButtonX2 = playButtonX1 + playButton.getWidth();
		int playButtonY2 = playButtonY1 + playButton.getHeight();
		
		int exitButtonX1 = (container.getWidth() / 2) - (exitButton.getWidth() / 2);
		int exitButtonY1 = ((container.getHeight() / 3) * 2) - (exitButton.getHeight() /2);
		int exitButtonX2 = exitButtonX1 + exitButton.getWidth();
		int exitButtonY2 = exitButtonY1 + exitButton.getHeight();
		
		Input input = container.getInput();
		
		if((mouseX > playButtonX1 && mouseX < playButtonX2) 
				&& (mouseY > playButtonY1 && mouseY < playButtonY2)) {
			if (input.isMouseButtonDown(0)) { 
				game.enterState(1); 
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
	 * @return ID		The ID of this state
	 */
	@Override
	public int getID() {
		return ID;
	}

}
