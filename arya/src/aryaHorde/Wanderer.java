package aryaHorde;

import java.util.Random;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;


/**
 * The Wanderer type enemy class.
 * This enemy does as the name implies, and wanders around the map, simply bouncing off of borders
 * @author Bryan Young
 *
 */
public class Wanderer extends Enemy {
	
	private Polygon wandererTop, wandererRight, wandererBottom, wandererLeft;
	
	
	//TODO fix the random spawning of the enemies in the default constructor
	
	/**
	 * Constructor that initializes the Wanderer enemy at the location
	 * @param x 	The x location of the Wanderer
	 * @param y		The y location of the Wanderer
	 */
	public Wanderer() {
		Random xRand = new Random(WIDTH);
		Random yRand = new Random(HEIGHT);
		Random xDirectionalRand = new Random(1);
		Random yDirectionalRand = new Random(1);
		int xInitialVelocity = xDirectionalRand.nextInt(2);
		int yInitialVelocity = yDirectionalRand.nextInt(2);
		
		points = WANDERER_POINTS;
		xPosition = xRand.nextInt(WIDTH) + 1;
		yPosition = yRand.nextInt(HEIGHT) + 1;
		setGraphics();
		
		if(xInitialVelocity > 0) {
			velocityX = 1;
		} else {
			velocityX = -1;
		}
		
		if(yInitialVelocity > 0) {
			velocityY = 1;
		} else {
			velocityY = -1;
		}
	}
	
	public Wanderer(float x1, float y1, float vX, float vY) {
		xPosition = x1;
		yPosition = y1;
		velocityX = vX;
		velocityY = vY;
		points = WANDERER_POINTS;
	}
	
	/**
	 * Updates the location of the Wanderer enemy
	 * @param delta		The rate of change of the location of the wanderer.
	 */
	@Override
	protected void update(int delta) {
		if(velocityX == 1) {
			xPosition += delta * .1f;
		} else {
			xPosition -= delta * .1f;
		}
		
		if(velocityY == 1) {
			yPosition += delta * .1f;
		} else {
			yPosition -= delta * .1f;
		}
	}
	
	/**
	 * Sets the position of the triangular polygons relative to the x and y coordinate center of the enemy
	 */
	@Override
	protected void setGraphics() {
		wandererTop = new Polygon(new float[] {
				xPosition, yPosition,
				xPosition - 5, yPosition - 10,
				xPosition, yPosition - 15});
		
		wandererRight = new Polygon(new float[] {
				xPosition, yPosition,
				xPosition + 10, yPosition - 5,
				xPosition + 15, yPosition});
		
		wandererBottom = new Polygon(new float[] {
				xPosition, yPosition,
				xPosition +5, yPosition + 10,
				xPosition, yPosition + 15});
		
		wandererLeft = new Polygon(new float[] {
				xPosition, yPosition,
				xPosition - 10, yPosition + 5,
				xPosition - 15, yPosition});
		}
	
	/**
	 * Draws the Wanderer to the graphics context
	 * @g 		The graphics context to render to
	 */
	@Override
	protected void draw(Graphics g) {
		this.setGraphics();
		g.draw(wandererTop);
		g.draw(wandererRight);
		g.draw(wandererBottom);
		g.draw(wandererLeft);
	}
	
	
}