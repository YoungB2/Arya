package aryaHorde;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.lwjgl.input.Mouse;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * The play state of the game
 * @author Bryan Young
 *
 */
public class Play extends BasicGameState implements GameConstants {

	/**Tracks the ID value of this game state*/
	int ID;
	
	/**The graphical components of the player*/
	private Animation player, movingUp, movingDown, movingLeft, movingRight;
	private Image worldMap;
	
	//private int worldHeight;
	//private int worldWidth;
	
	private Random xRand;
	private Random yRand;
	private Random initXVelocityRand;
	private Random initYVelocityRand;
	private float initX;
	private float initY;
	private float initXVelocity;
	private float initYVelocity;
	
	/**Tracks graphical debug information for the location of the mouse*/
	String mouse;
	
	/**Tracks whether or not the game has been paused*/
	private boolean quitGame = false;
	
	//how long each image will last (200 = 2/10 of a second)
	private int[] duration = {200, 200};
	/**Tracks the score of the player during the game*/
	private int score;
	
	//Camera and player tracking variables
	/**Tracks the camera's x position*/
	private float cameraX = 0;
	
	/**Tracks the camera's y position*/
	private float cameraY = 0;
	
	/**Track's the player's x position within the camera view*/
	private float playerX = WIDTH/2;
	
	/**Track's the player's y position within the camera view*/
	private float playerY = HEIGHT/2;
	
	//Border collision detection variables
	/**Tracks the camera collision with the lower portion of the world image*/
	private float bottomCollisionShift;
	
	/**Tracks the camera collision with the far side portion of the world image*/
	private float sideCollisionShift;
	
	/**Tracks the player's collision with the far side border of the world*/
	private float sideCollision;
	
	/**Tracks the player's collision with the lower border of the world*/
	private float bottomCollision;
	
	/**Tracks the number of enemies alive on the board*/
	private int livingEnemies;
	
	//Game objects
	/**An array list to track the active BasicBullets in the game*/
	private CopyOnWriteArrayList<BasicBullet> playerProjectiles;
	
	/**An array list to track the active enemy Wanderers in the game*/
	private CopyOnWriteArrayList<Wanderer> wanderers;
	
	/**
	 * The constructor for the Play game state
	 * @param state		The ID value passed down from the StateController
	 */
	public Play(int state) {
		ID = state;
	}
	
	/**
	 * Initializes the variables and objects needed for the start of the game in this state
	 * @param container		The GameContainer for the game
	 * @param game			The StateBasedGame for this game
	 * @throws SlickException
	 */
	@Override
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		
		score = 0;
		
		worldMap = new Image(FIRST_MAP_RES);
		//worldWidth = worldMap.getWidth();
		//worldHeight = worldMap.getHeight();
		
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
		
		livingEnemies = 0;
		
		playerProjectiles = new CopyOnWriteArrayList<BasicBullet>();
		wanderers = new CopyOnWriteArrayList<Wanderer>();
		
		for (int i = livingEnemies; i < MAX_ENEMIES; i++) {
			initWanderer();
		} 
		
	}

	/**
	 * Initializes a Wanderer type enemy
	 */
	private void initWanderer() {
		initXVelocityRand = new Random();
		initYVelocityRand = new Random();
		xRand = new Random();
		yRand = new Random();
		initXVelocity = initXVelocityRand.nextInt(2);
		initYVelocity = initYVelocityRand.nextInt(2);
		initX = xRand.nextInt(WIDTH);
		initY = yRand.nextInt(HEIGHT);
		wanderers.add(new Wanderer(initX, initY, initXVelocity, initYVelocity));
		livingEnemies++;
	}
	
	/**
	 * Renders all graphical components of this game state.
	 * @param container		The GameContainer for this state
	 * @param game			The StateBasedGame for this state
	 * @param g				The graphical context to render the graphics to
	 * @throws SlickException
	 */
	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
		worldMap.draw(cameraX, cameraY);
		g.drawString("Camera is at x: " + cameraX 
				+ " y: " + cameraY, 20, HEIGHT - 20);
		g.drawString("Player is at x: " +  playerX 
				+ " y: " + playerY, 20, HEIGHT - 40);
		g.drawString(mouse, 20, HEIGHT - 60);
		g.drawString("SCORE: " + score, 10, 30);
		player.draw(playerX, playerY);
		
		if(quitGame) {
			g.drawString("Resume (R)", 250, 100);
			g.drawString("Main Menu (M)", 250, 130);
			g.drawString("Quit Game (Q)", 250, 160);
			if(!quitGame) {
				g.clear();
			}
		}
		
		if(playerProjectiles != null) {
			for (BasicBullet bullet : playerProjectiles) {
				bullet.draw(g);
			}
		} 
		 
		for(Wanderer wanderer : wanderers) {
			wanderer.draw(g);
		}
	}

	/**
	 * Updates the graphical entities of the Play state of the game.
	 * Reads user input for the game and changes game objects based on input.
	 * Manages collision detection and object termination
	 * Changes score
	 * @param container		The GameContainer for this state
	 * @param game			The StateBasedGame for this state
	 * @param delta			The rate of change for the update of this state
	 * @throws SlickException
	 */
	@Override
	public void update(GameContainer container, StateBasedGame game, int delta)
			throws SlickException {
		
		Input input = container.getInput();
		int posX = Mouse.getX();
		int posY = HEIGHT - Mouse.getY();
		int bulletID = 0;
		int wandererID = 0;
		
		if(livingEnemies < MAX_ENEMIES) {
			initWanderer();
		}
		
		
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
			} else {											//Move player towards top border, collide with border
				playerY -= delta * .1f;
				if (playerY <= 0) { 
					playerY += delta * .1f; 
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_A)) {						//Move left
			player = movingLeft;
			
			if (cameraX < 0) {									//Track camera left
				cameraX += delta *.1f;
				if(playerX > CENTERED_X) {						//lock camera, move player left (far side)
					playerX -= delta * .1f;
					cameraX -= delta * .1f;
				}
			} else {											//Move player towards left border, collide with border
				playerX -= delta * .1f;
				if (playerX <= 0) {
					playerX += delta * .1f;
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_S)) {						//Move down
			
			player = movingDown;
			
			if (cameraY > bottomCollisionShift) {				//Track camera down
				cameraY -= delta * .1f;
				if (cameraY < bottomCollisionShift - playerY) {	//Lock camera, move player down (towards bottom)
					cameraY += delta * .1f;
					playerY -= delta * .1f;
				} else if (playerY < CENTERED_Y) {				//Lock camera, move player down (top)
					playerY += delta * .1f;
					cameraY += delta * .1f;
				} 
			} else {											//Move player down, collide with bottom
				playerY += delta * .1f;
				if (playerY >= bottomCollision) {
					playerY -= delta * .1f;
				}
			}
		}
		
		if(input.isKeyDown(Input.KEY_D)) {						//Move right
			player = movingRight;
			
			if (cameraX > sideCollisionShift) {					//Track camera right
				cameraX -= delta *.1f;
				if(playerX < CENTERED_X) {						//Lock camera, move player right (near side)
					playerX += delta * .1f;
					cameraX += delta * .1f;
				} 
			} else {											//Move player right, collide with far side border
				playerX += delta * .1f;
				if (playerX >= sideCollision) {							
					playerX -= delta * .1f;
				}
			}
		}
		
		mouse = "The cursor is at x: " + posX + " and y: " + posY;
		
		//Update projectiles
		if (playerProjectiles != null) {
			for(BasicBullet bullet : playerProjectiles) {
				bullet.update(delta);
			}
		}
		
		//Terminate projectile at camera borders
		if (playerProjectiles != null) {
			for(BasicBullet bullet : playerProjectiles) {
				if (bullet.xPos < 0 
						|| bullet.yPos < 0 
						|| bullet.xPos > WIDTH 
						|| bullet.yPos > HEIGHT) {
					
					bulletID = playerProjectiles.indexOf(bullet);
					playerProjectiles.remove(bulletID);
				}
			}
		} 
		
		
		if(input.isMouseButtonDown(0)) {
			//TODO Fix this pain in my neck, get the bullets to draw all the way
			if(playerProjectiles != null) {
				playerProjectiles.add(new BasicBullet(
						playerX + (player.getWidth() / 2), 			//x1
						playerY + (player.getHeight() / 2),			//y1
						posX,					 					//x2
						posY));										//y2
			}
		}
		
		//Update wanderers
		for(Wanderer wanderer : wanderers) {

			wanderer.update(delta);
			
			if(wanderer.xPosition < 15 || wanderer.xPosition > WIDTH - 15) {		//Collision with X borders
				wanderer.invertXVelocity();
			}
			if(wanderer.yPosition < 15 || wanderer.yPosition > HEIGHT - 15) {		//Collision with Y borders
				wanderer.invertYVelocity();
			}
			
			if(wanderer.xPosition < 0 || wanderer.xPosition > WIDTH 
					|| wanderer.yPosition < 0 || wanderer.yPosition > HEIGHT) {		//Check for escapees
				
				wandererID = wanderers.indexOf(wanderer);
				
				if(wandererID != -1) {												//Make sure it exists, then kill
					wanderers.remove(wandererID);
					livingEnemies--;
				}
			}
			
			for(BasicBullet bullet : playerProjectiles) {							//Collision with bullets
				if(bullet.xPos > wanderer.xPosition - 15 
						&& bullet.xPos < wanderer.xPosition + 15
						&& bullet.yPos > wanderer.yPosition - 15 
						&& bullet.yPos < wanderer.yPosition +15) {
					
					score += wanderer.points;										//Increase points
					
					wandererID = wanderers.indexOf(wanderer);						
					
					if(wandererID != -1) {											//Make sure it exists, then kill
						wanderers.remove(wandererID);
						livingEnemies--;
					}
					
					bulletID = playerProjectiles.indexOf(bullet);					//Terminate expended bullet
					if(bulletID != -1) {
						playerProjectiles.remove(bulletID);
					}
				}
			}
		}
	}

	/**
	 * Gets the ID of this game state
	 * @return ID		The ID of this game state
	 */
	@Override
	public int getID() {
		return ID;
	}

}
