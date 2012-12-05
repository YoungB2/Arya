package aryaHorde;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Polygon;

//TODO test this class with a single unit at a given location. 

/**
 * The Wanderer type enemy class.
 * This enemy does as the name implies, and wanders around the map, simply bouncing off of borders
 * @author Bryan Young
 *
 */
public class Wanderer extends Enemy {
	
	private Polygon wandererTop, wandererRight, wandererBottom, wandererLeft;
	
	//TODO implement random spawning on map (not specifically within the field of player vision)
	
	/**
	 * Constructor that initializes the Wanderer enemy at the location
	 * @param x 	The x location of the Wanderer
	 * @param y		The y location of the Wanderer
	 */
	public Wanderer(int x, int y) {
		init(x, y);
		setGraphics();
		velocityX = 1;
		velocityY = 1;
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