package aryaHorde;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Defines the resources of the player and implements methods and fields for changing 
 * @author youngb2
 *
 */
public class Player implements GameConstants {
	
	private Image playerUp, playerDown, playerLeft, playerRight;
	private Image[] walkUp, walkDown, walkLeft, walkRight;
	private Animation movingUp, movingDown, movingLeft, movingRight; 
	public Animation activeAnimation;
	
	private int[] duration = new int[] {200, 200};
	
	public int playerX;
	public int playerY;
	
	public Player() throws SlickException {
		playerX = WIDTH / 2;
		playerY = HEIGHT / 2;
		initGraphics();
	}
	
	private void initGraphics() throws SlickException {
		playerUp = new Image(PLAYER_UP_RES);
		playerDown = new Image(PLAYER_DOWN_RES);
		playerLeft = new Image(PLAYER_LEFT_RES);
		playerRight = new Image(PLAYER_RIGHT_RES);
		
		walkUp = new Image[] {playerUp, playerUp};
		walkDown = new Image[] {playerDown, playerDown};
		walkLeft = new Image[] {playerLeft, playerLeft};
		walkRight = new Image[] {playerRight, playerRight};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		
		activeAnimation = movingUp;
	}
	
	//TODO implement boolean collision statement
	public boolean collision(float x, float y) {
		if(x > playerX 
				&& x < playerX + activeAnimation.getWidth()
				&& y > playerY
				&& y < playerY + activeAnimation.getHeight()) {
			return true;
		}
		return false;
	}
	
	public void render(Graphics g) {
		g.drawAnimation(activeAnimation, playerX, playerY);
	}
	
	public void movePlayerUp(int delta) {
		playerY -= delta * .1f;
	}
	
	public void movePlayerDown(int delta) {
		playerY += delta * .1f;
	}
	
	public void movePlayerLeft(int delta) {
		playerX -= delta * .1f;
	}
	
	public void movePlayerRight(int delta) {
		playerX += delta * .1f;
	}
	
	public void setPlayerUp() {
		activeAnimation = movingUp;
	}
	
	public void setPlayerDown() {
		activeAnimation = movingDown;
	}
	
	public void setPlayerLeft() {
		activeAnimation = movingLeft;
	}
	
	public void setPlayerRight() {
		activeAnimation = movingRight;
	}
	
	public float getCenterX() {
		return playerX + activeAnimation.getWidth() / 2;
	}
	
	public float getCenterY() {
		return playerY + activeAnimation.getHeight() / 2;
	}
	
	public float getRightSpriteWidth() {
		return movingRight.getWidth();
	}
	
	public float getDownSpriteHeight() {
		return movingDown.getHeight();
	}
}
