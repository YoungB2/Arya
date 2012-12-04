package aryaHorde;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/** 
 * Core class. State based game controller, acts as the main entry point for the game.
 * 
 * @author youngb2
 * @version 1.0
 */
public class StateController extends StateBasedGame implements GameConstants {
	
	public StateController(String name) {
		super(name);
		this.addState(new Menu(MENU));
		this.addState(new Play(PLAY));
	}
	
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
