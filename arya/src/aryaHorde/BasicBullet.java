package aryaHorde;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Circle;

public class BasicBullet {
	
	public float xPos;
	public float yPos;
	
	private int yVelocity;
	private int xVelocity;
	private float slope;
	
	public BasicBullet(float x1, float y1, float x2, float y2) {
		slope = (y2 - y1) / (x2 - x1);
		xPos = x1;
		yPos = y1;
		
		if (Math.abs(x2) > Math.abs(x1)) {
			xVelocity = 1;
		} else { 
			xVelocity = -1; 
		}
		
		if (Math.abs(y2) > Math.abs(y1)) {
			yVelocity = 1;
		} else {
			yVelocity = -1;
		}
	}
	
	public void update(int delta) {
		xPos += (delta * .25f) * xVelocity;
		yPos += ((delta * .25f) * slope) * yVelocity;
	}
	
	public void draw(Graphics g) {
		g.draw(new Circle(xPos, yPos, 3));
	}
}
