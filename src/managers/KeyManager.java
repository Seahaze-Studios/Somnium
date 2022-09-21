package managers;

import core.Constants;
import core.Main;
import entities.units.player.Player;
import gamestates.Game;
import map.GameMap;
import map.tile.Tile;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Shape;
import util.Vector2f;

import java.util.HashSet;
import java.util.List;
import java.util.function.Predicate;

import static core.Constants.SCALING_FACTOR;

public final class KeyManager implements Predicate<Integer> {
	private static final float PLAYER_ACCELERATION = 1f;
	public static final List<Integer> KEY_DOWN_LIST = List.of(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);

	private KeyManager() { throw new IllegalStateException("Utility class"); }

	private Player playerL;
	private Player playerR;

	private MapManager mapMan;
	private final Input input;
	//private final GameMap map;
	
	public KeyManager(Input input, Game game) {
		this.input = input;
		playerL = game.getPlayerL();
		playerR = game.getPlayerR();
		this.mapMan = game.getMapMan();
		//this.map = new GameMap("lol");
		/*this.player = game.getPlayer();
		this.map = game.getOverworld();*/
	}
	
	public boolean test(Integer i) {
		return input.isKeyDown(i);
	}

	public void keyDown(int key) {
		boolean temp1 = false;
		boolean temp2 = false;
		switch(key)	{
			case Input.KEY_W ->	{
				for(int i = 0; i < Constants.PLAYER_MAX_SPEED; i++)	{
					if(!temp1) {
						temp1 = playerMove(MapManager.mapL, playerL, new Vector2f(0,-1 * SCALING_FACTOR()),true);
//						playerL.move(new Vector2f(0, -1));
//						if(playerL.collides(MapManager.mapL))	{
//							playerL.move(new Vector2f(0, 1));
//							temp1 = true;
//							break;
//						}
					}
					if(!temp2) {
						temp2 = playerMove(MapManager.mapR, playerR, new Vector2f(0,-1 * SCALING_FACTOR()),false);
//						playerR.move(new Vector2f(0, -1));
//						if(playerR.collides(MapManager.mapR))	{
//							playerR.move(new Vector2f(0, 1));
//							temp2 = true;
//							break;
//						}
					}
				}

			}
			case Input.KEY_A ->	{
				for(int i = 0; i < Constants.PLAYER_MAX_SPEED; i++)	{
					if(!temp1) {
						temp1 = playerMove(MapManager.mapL, playerL, new Vector2f(-1 * SCALING_FACTOR(),0),true);
//						playerL.move(new Vector2f(-1, 0));
//						if(playerL.collides(MapManager.mapL))	{
//							playerL.move(new Vector2f(1, 0));
//							temp1 = true;
//						}

					}
					if(!temp2) {
						temp2 = playerMove(MapManager.mapR, playerR, new Vector2f(1 * SCALING_FACTOR(),0),false);
//						playerR.move(new Vector2f(1, 0));
//						if(playerR.collides(MapManager.mapR))	{
//							playerR.move(new Vector2f(-1, 0));
//							temp2 = true;
//						}
					}
				}
			}
			case Input.KEY_S -> {
				for(int i = 0; i < Constants.PLAYER_MAX_SPEED; i++)	{
					if(!temp1) {
						temp1 = playerMove(MapManager.mapL, playerL, new Vector2f(0,1 * SCALING_FACTOR()),true);
//						playerL.move(new Vector2f(0, 1));
//						if(playerL.collides(MapManager.mapL))	{
//							playerL.move(new Vector2f(0, -1));
//							temp1 = true;
						}
					if(!temp2) {
						temp2 = playerMove(MapManager.mapR, playerR, new Vector2f(0,1 * SCALING_FACTOR()),false);
//						playerR.move(new Vector2f(0, 1));
//						if(playerR.collides(MapManager.mapR))	{
//							playerR.move(new Vector2f(0, -1));
//							temp2 = true;
//						}
					}
				}
			}

			case Input.KEY_D -> {
				for(int i = 0; i < Constants.PLAYER_MAX_SPEED; i++)	{
					if(!temp1) {
						temp1 = playerMove(MapManager.mapL, playerL, new Vector2f(1 * SCALING_FACTOR(),0),true);
//						playerL.move(new Vector2f(1, 0));
////						if(playerL.collides(MapManager.mapL))	{
////							playerL.move(new Vector2f(-1, 0));
////							temp1 = true;
////						}

					}
					if(!temp2) {
						temp2 = playerMove(MapManager.mapR, playerR, new Vector2f(-1 * SCALING_FACTOR(),0),false);
//						playerR.move(new Vector2f(-1, 0));
//						if(playerR.collides(MapManager.mapR))	{
//							playerR.move(new Vector2f(1, 0));
//							temp2 = true;
//						}
					}
				}
			}
		}
	}

	private boolean playerMove(GameMap gm, Player plr, Vector2f disp, boolean left)	{
		plr.move(disp);
		if(plr.collides(gm) || ((left)?plr.getPos().getX()+(plr.getHitbox().getWidth()/2) > Constants.MAP_WIDTH*Constants.TILE_SIZE:
				plr.getPos().getX()-(plr.getHitbox().getWidth()/2) < Main.getScreenWidth() - Constants.MAP_WIDTH*Constants.TILE_SIZE))	{
			plr.move(disp.negate());
			return true;
		}
		return false;
	}

	/*public void keyDown(int key) {
		boolean temp = false;
		switch (key) {
			case Input.KEY_W -> {
				for (Shape[] sa : map.getHitboxes()) for (Shape s : sa) if(s != null) {
					player.accelerateY(PLAYER_ACCELERATION);
					map.updateHitboxes(0, player.getSpeedY() * 0.5f);
					if (player.getHitBox().intersects(s)) temp = true;
					//if(temp){player.accelerateY(-PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY() * -0.5f);}
					player.accelerateY(-PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY() * -0.5f);
				}
				if(!temp)	{player.accelerateY(PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY()*0.5f);}

				//player.accelerateY(PLAYER_ACCELERATION);
				//map.updateHitboxes(0, player.getSpeedY()*0.5f);
			}
			case Input.KEY_A -> {
				for (Shape[] sa : map.getHitboxes()) for (Shape s : sa) if(s != null)	{
					player.accelerateX(-PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX(), 0);
					if (player.getHitBox().intersects(s)) temp = true;
					//if(temp){player.accelerateX(PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX()*-1, 0);}
					player.accelerateX(PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX()*-1, 0);
				}
				if(!temp)	{player.accelerateX(-PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX(), 0);}

				//player.accelerateX(-PLAYER_ACCELERATION);
				//map.updateHitboxes(player.getSpeedX(), 0);
			}
			case Input.KEY_S -> {
				for (Shape[] sa : map.getHitboxes()) for (Shape s : sa) if(s != null)	{
					player.accelerateY(-PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY()*-0.5f);
					if (player.getHitBox().intersects(s))	temp = true;
					//if(temp){player.accelerateY(PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY() * 0.5f);}
					player.accelerateY(PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY() * 0.5f);
				}
				if(!temp)	{player.accelerateY(-PLAYER_ACCELERATION);map.updateHitboxes(0, player.getSpeedY()*-0.5f);}

				//player.accelerateY(-PLAYER_ACCELERATION);
				//map.updateHitboxes(0, player.getSpeedY()*-0.5f);
			}
			case Input.KEY_D -> {
				for (Shape[] sa : map.getHitboxes()) for (Shape s : sa) if(s != null)	{
					player.accelerateX(PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX()*-1, 0);
					if (player.getHitBox().intersects(s))	temp = true;
					//if(temp){player.accelerateX(-PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX(), 0);}
					player.accelerateX(-PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX(), 0);
				}
				if(!temp)	{player.accelerateX(PLAYER_ACCELERATION);map.updateHitboxes(player.getSpeedX()*-1, 0);}

				//player.accelerateX(PLAYER_ACCELERATION);
				//map.updateHitboxes(player.getSpeedX()*-1, 0);
			}
		}
	}*/

	/*public boolean amogus(List<Integer> keys) {
		if (new HashSet<>(keys).containsAll(AMOGUS_LIST)) return true;
		return false;
	}*/
}