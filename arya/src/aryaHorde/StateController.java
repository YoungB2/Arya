package aryaHorde;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/** 
 * Core class
 * State based game controller, acts as the main entry point for the game.
 * 
 * @author Bryan Young
 * @version 1.0
 * @see StateBasedGame
 */
public class StateController extends StateBasedGame implements GameConstants {
	
	/**
	 * The constructor for the StateController given the name of the game
	 * @param name		The String name of the game
	 */
	public StateController(String name) {
		super(name);
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
	}
	
	/**
	 * Initializes the list of states for the game
	 * @param gc		The game container for the game
	 * @throws SlickException
	 */
	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MENU).init(gc, this);
		this.getState(PLAY).init(gc, this);
		
		this.enterState(MENU);
	}
	
	/**
	 * The entry point for the program, starts and runs the start menu
	 * @param args		the arguments passed to the program
	 */
	public static void main(String[] args) {
		AppGameContainer appgc;
		
		try {
			appgc = new AppGameContainer(new StateController(GAME_NAME));
			appgc.setDisplayMode(WIDTH, HEIGHT, false);
			appgc.setTargetFrameRate(FRAME_RATE);
			appgc.start();
		} catch(SlickException e) {
			e.printStackTrace();
		}
	}
	
}
