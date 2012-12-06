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
	
	/**Stores the image graphical data for the player facing a direction*/
	private Image playerUp, playerDown, playerLeft, playerRight;
	/**Stores the array of graphical data for a direction of player motion*/
	private Image[] walkUp, walkDown, walkLeft, walkRight;
	/**Stores the animation for a direction of player motion*/
	private Animation movingUp, movingDown, movingLeft, movingRight; 
	/**Stores the animation for the player's current direction of motion*/
	public Animation activeAnimation;
	
	/**The duration of time for each frame of the animation (200 = 2/10 of a second)*/
	private int[] duration = new int[] {200, 200};
	
	/**Stores the player's current x position*/
	public int playerX;
	/**Stores the player's current y position*/
	public int playerY;
	
	/**
	 * Constructs the default player
	 * @throws SlickException
	 */
	public Player() throws SlickException {
		playerX = WIDTH / 2;
		playerY = HEIGHT / 2;
		initGraphics();
	}
	
	/**
	 * Initializes the graphical components of the player
	 * @throws SlickException
	 */
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
	
	/**
	 * Detects if the enemy object is within the bounds of the player
	 * @param x		The x position of the center of the enemy to test collision with
	 * @param y		The y position of the center of the enemy to test collision with
	 * @return		true of false
	 */
	public boolean collision(float x, float y) {
		if(x > playerX 
				&& x < playerX + activeAnimation.getWidth()
				&& y > playerY
				&& y < playerY + activeAnimation.getHeight()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Renders the animation for the current direction that the player is facing
	 * @param g		The graphics context to render the graphical components to
	 */
	public void render(Graphics g) {
		g.drawAnimation(activeAnimation, playerX, playerY);
	}
	
	/**
	 * Updates the player's y position up on the game screen
	 * @param delta		The rate of change of the update
	 */
	public void movePlayerUp(int delta) {
		playerY -= delta * .1f;
	}
	
	/**
	 * Updates the player's y position down on the game screen
	 * @param delta		The rate of change of the update
	 */
	public void movePlayerDown(int delta) {
		playerY += delta * .1f;
	}
	
	/**
	 * Updates the player's x position left on the game screen
	 * @param delta
	 */
	public void movePlayerLeft(int delta) {
		playerX -= delta * .1f;
	}
	
	/**
	 * Updates the player's x position right on the game screen
	 * @param delta
	 */
	public void movePlayerRight(int delta) {
		playerX += delta * .1f;
	}
	
	/**
	 * Sets the players current animation to moving up
	 */
	public void setPlayerUp() {
		activeAnimation = movingUp;
	}
	
	/**
	 * Sets the player's current animation to moving down
	 */
	public void setPlayerDown() {
		activeAnimation = movingDown;
	}
	
	/**
	 * Sets the player's current animation to moving left
	 */
	public void setPlayerLeft() {
		activeAnimation = movingLeft;
	}
	
	/**
	 * Sets the player's current animation to moving right
	 */
	public void setPlayerRight() {
		activeAnimation = movingRight;
	}
	
	/**
	 * Gets the player's current center point on the x axis
	 * @return
	 */
	public float getCenterX() {
		return playerX + activeAnimation.getWidth() / 2;
	}
	
	/**
	 * Gets the player's current center point on the y axis
	 * @return
	 */
	public float getCenterY() {
		return playerY + activeAnimation.getHeight() / 2;
	}
	
	/**
	 * Gets the width of the sprite for the player moving right
	 * @return		The width of the right moving sprite
	 */
	public float getRightSpriteWidth() {
		return movingRight.getWidth();
	}
	
	/**
	 * Gets the height of the sprite for the player moving down
	 * @return		The height of the down moving sprite
	 */
	public float getDownSpriteHeight() {
		return movingDown.getHeight();
	}
}
