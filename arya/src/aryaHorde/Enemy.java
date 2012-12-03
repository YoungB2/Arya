package aryaHorde;

public abstract class Enemy {
	
	
	float velocityX;
	float velocityY;
	float xPosition;
	float yPosition;
	
	
	
	protected void init(int x, int y) {
		xPosition = x;
		yPosition = y;
	}
	
	protected void initGraphics() {
		
	}
}