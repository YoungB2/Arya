package aryaHorde;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

/**
 * A bullet object which creates a graphical bullet and tracks it's location and other other data
 * @author Bryan Young
 */
public class BasicBullet implements GameConstants{
	
	/**The x position of the bullet in relation to the camera window*/
	public float xPos;
	/**The y position of the bullet in relation to the camera window*/
	public float yPos;
	/**The first x position of the bullet*/
	public float initialX;
	/**The first y position of the bullet*/
	public float initialY;
	
	/**The scalar y direction of the bullet, either one or negative one*/
	private int yDirection;
	/**The scalar x direction of the bullet, either one or negative one*/
	private int xDirection;
	/**The change in x direction between the points x2 and x1*/
	private float deltaX;
	/**The change in y direction between the points y2 and y1*/
	private float deltaY;
	/**The value of change in y over change in x of the bullet*/
	private float slope;
	
	/**
	 * The constructor for a BasicBullet object
	 * @param x1	The first point x coordinate
	 * @param y1	The first point y coordinate
	 * @param x2	The second point x coordinate
	 * @param y2	The second point y coordinate
	 */
	public BasicBullet(float x1, float y1, float x2, float y2) {
		deltaX = x2 - x1;
		deltaY = y2 - y1;
		slope = deltaX / deltaY;
		xPos = x1;
		yPos = y1;
		initialX = x1;
		initialY = y1;
		
		if (x2 > (CENTERED_X)) {
			xDirection = 1;
		} else {
			xDirection = -1;
		}
		
		if (y2 > CENTERED_Y) {
			yDirection = 1;
		} else { 
			yDirection = -1;
		}
	}
	
	/**
	 * Updates the position of the bullet
	 * @param delta		The rate of change to update the bullet's position by
	 */
	public void update(int delta) {
		if (xDirection > 0) {
			xPos += delta * .5f;
		} else {
			xPos -= delta * .5f;
		}
		
		if (yDirection > 0) {
			yPos += (delta * slope);
		} else
			yPos -= (delta * slope);
	}
	
	/**
	 * Draws the bullet to the graphics context
	 * @param g		The graphics context to draw to
	 */
	public void draw(Graphics g) {
		g.draw(new Circle(xPos, yPos, 3));
	}
}
