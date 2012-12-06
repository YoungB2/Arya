package aryaHorde;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class GameOver extends BasicGameState implements GameConstants {

	private int ID;
	
	private Image background;
	
	public GameOver(int state) {
		ID = state;
	}
	@Override
	public void init(GameContainer gameContainer, StateBasedGame game)
			throws SlickException {
		background = new Image(GAME_OVER_RES);
	}

	@Override
	public void render(GameContainer gameContainer, StateBasedGame game, Graphics g)
			throws SlickException {
		background.draw(0, 0);
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
	}

	@Override
	public int getID() {
		return ID;
	}

}
