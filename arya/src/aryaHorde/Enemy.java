package aryaHorde;

import org.newdawn.slick.Graphics;

public abstract class Enemy {
	
	
	float velocityX;
	float velocityY;
	float xPosition;
	float yPosition;
	
	
	
	protected void init(int x, int y) {
		xPosition = x;
		yPosition = y;
	}
	
	protected void initGraphics() {			//Prepares unit's polygon
		
	}
	
	protected void draw(Graphics g) {
		
	}
	
	protected void update(int delta) {
		
	}
}