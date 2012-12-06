package aryaHorde;

import org.newdawn.slick.Graphics;

/**
 * An abstract class that defines the common fields and methods of any enemy in the game
 * @author Bryan Young
 */
public abstract class Enemy implements GameConstants {
	
	/**The scalar x direction of the enemy, either one or negative one*/
	protected float velocityX;
	/**The scalar y direction of the enemy, either one or negative one*/
	protected float velocityY;
	/**The x axis location of the enemy in relation to the world map*/
	public float xPosition;
	/**The y axis location of the enemy in relation to the world map*/
	public float yPosition;
	/**The number of points this enemy is worth when killed*/
	public int points;
	
	/**
	 * Inverts the enemy's x velocity, multiplying by negative one
	 */
	public void invertXVelocity() {
		velocityX *= -1;
	}
	
	/**
	 * Inverts the enemy's y velocity, multiplying by negative one
	 */
	public void invertYVelocity() {
		velocityY *= -1;
	}
	
	/**
	 * Initializes the position of the enemy with an x and y coordinate
	 * @param x		The x coordinate of the enemy
	 * @param y		The y coordinate of the enemy
	 */
	protected void init(int x, int y) {
		xPosition = x;
		yPosition = y;
	}
	
	/**
	 * Defines the abstract function to change any graphical components of a sub-classed enemy
	 */
	protected void setGraphics() {			//Prepares unit's polygon
		
	}
	
	/**
	 * Defines the abstract function to draw the enemy's graphical components of a sub-classed enemy
	 * @param g		The graphics context to draw to
	 */
	protected void draw(Graphics g) {
		
	}
	
	/**
	 * Defines the abstract function to update the position and orientation of a sub-classed enemy
	 * @param delta		The rate of change to update the enemy's position by
	 */
	protected void update(int delta) {
		
	}
}