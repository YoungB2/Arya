package aryaHorde;

import java.util.Collection;

//import org.lwjgl.input.Mouse;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

public class Play extends BasicGameState implements GameConstants {

	Animation player, movingUp, movingDown, movingLeft, movingRight;
	Image worldMap;
	boolean quitGame = false;
	//how long each image will last (200 = 2/10 of a second)
	int[] duration = {200, 200};
	float cameraX = 0;
	float cameraY = 0;
	float playerX = WIDTH/2;
	float playerY = HEIGHT/2;
	float bottomCollisionShift;
	float sideCollisionShift;
	float sideCollision;
	float bottomCollision;
	Collection<BasicBullet> playerProjectiles;
	
	public Play(int state) {
		
	}
	
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		worldMap = new Image("res/background/oneLifeBG.png");
		Image[] walkUp = {new Image("res/bucky/buckysBack.png"), 
				new Image("res/bucky/buckysBack.png")};
		Image[] walkDown = {new Image("res/bucky/buckysFront.png"), 
				new Image("res/bucky/buckysFront.png")};
		Image[] walkLeft = {new Image("res/bucky/buckysLeft.png"), 
				new Image("res/bucky/buckysLeft.png")};
		Image[] walkRight = {new Image("res/bucky/buckysRight.png"), 
				new Image("res/bucky/buckysRight.png")};
		
		movingUp = new Animation(walkUp, duration, false);
		movingDown = new Animation(walkDown, duration, false);
		movingLeft = new Animation(walkLeft, duration, false);
		movingRight = new Animation(walkRight, duration, false);
		
		player = movingDown;
		
		sideCollisionShift = (-1 * worldMap.getWidth() + CENTERED_X * 2 + movingRight.getWidth());
		bottomCollisionShift = (-1 * worldMap.getHeight() + CENTERED_Y * 2 + movingDown.getHeight());
		sideCollision = (CENTERED_X * 2) - movingRight.getWidth();
		bottomCollision = (CENTERED_Y * 2) - movingDown.getHeight();
		
		
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		worldMap.draw(cameraX, cameraY);
		player.draw(playerX, playerY);
		g.drawString("Camera is at x: " + cameraX 
				+ " y: " + cameraY, 20, HEIGHT - 20);
		g.drawString("Player is at x: " +  playerX 
				+ " y: " + playerY, 20, HEIGHT - 40);
		
		/* if(playerProjectiles != null) {
			for (BasicBullet bullet : playerProjectiles) {
				bullet.draw(g);
			}
		} 
		*/
		
		if(quitGame) {
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 130);
			g.drawString("Quit Game (Q)", 250, 160);
			if(!quitGame) {
				g.clear();
			}
		}
	}

	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		Input input = container.getInput();
		//int posX = Mouse.getX();
		//int posY = Mouse.getY();
		
		if(input.isKeyDown(Input.KEY_W)) {						//Move up
			player = movingUp;
			
			if (cameraY < 0) {									//Track camera up
				//move camera
				cameraY += delta * .1f;
				if (cameraY < bottomCollisionShift) {			//Lock camera, move player up (top border)
					cameraY -= delta * .1f;
					playerY -= delta * .1f;
				} else if (playerY > CENTERED_Y ) {				//lock camera, move player up. (lower border)
					playerY -= delta * .1f;
					cameraY -= delta * .1f;
				}
			} else {											
				//move player
				playerY -= delta * .1f;
				if (playerY <= 0) { 
					playerY += delta * .1f; 
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_A)) {
			player = movingLeft;
			
			if (cameraX < 0) {									//Move camera, track right
				cameraX += delta *.1f;
				if(playerX > CENTERED_X) {						//lock camera, move player left (near side)
					playerX -= delta * .1f;
					cameraX -= delta * .1f;
				}
			} else {											//Move player
				playerX -= delta * .1f;
				if (playerX <= 0) {
					playerX += delta * .1f;
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_S)) {						//Move down
			
			player = movingDown;
			
			if (cameraY > bottomCollisionShift) {
				cameraY -= delta * .1f;
				if (cameraY < bottomCollisionShift - playerY) {
					cameraY += delta * .1f;
					playerY -= delta * .1f;
				} else if (playerY < CENTERED_Y) {
					playerY += delta * .1f;
					cameraY += delta * .1f;
				} 
			} else {
				//move player
				playerY += delta * .1f;
				if (playerY >= bottomCollision) {
					playerY -= delta * .1f;
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_D)) {
			player = movingRight;
			
			if (cameraX > sideCollisionShift) {							//Track camera left
				cameraX -= delta *.1f;
				if(playerX < CENTERED_X) {								//Lock camera, move player left
					playerX += delta * .1f;
					cameraX += delta * .1f;
				} 
			} else {													//Move Player
				playerX += delta * .1f;
				if (playerX >= sideCollision) {							//Lock player
					playerX -= delta * .1f;
				}
			}
		}
		
		/*if (playerProjectiles != null) {
			for(BasicBullet bullet : playerProjectiles) {
				bullet.update(delta);
			}
		}
		
		if(input.isMouseButtonDown(0)) {
			playerProjectiles.add(new BasicBullet(playerX, playerY, Math.abs(posX - WIDTH), Math.abs(posY - HEIGHT)));
		}
		*/
	}

	@Override
	public int getID() {
		return 1;
	}

}
