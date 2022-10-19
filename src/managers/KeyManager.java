package managers;

import core.Constants;
import core.Main;
import entities.Direction;
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
	private static final float PLAYER_ACCELERATION = 5f;
	public static final List<Integer> KEY_DOWN_LIST = List.of(Input.KEY_W, Input.KEY_S, Input.KEY_A, Input.KEY_D);

	private KeyManager() { throw new IllegalStateException("Utility class"); }

	private Player playerL;
	private Player playerR;

	private MapManager mapMan;
	private final Input input;
	
	public KeyManager(Input input, Game game) {
		this.input = input;
		playerL = game.getPlayerL();
		playerR = game.getPlayerR();
		this.mapMan = game.getMapMan();
	}
	
	public boolean test(Integer i) {
		return input.isKeyDown(i);
	}

	public void keyDown(int key) {
		switch (key) {
			case Input.KEY_W -> {
				playerMove(MapManager.mapL, playerL, new Vector2f(0, -PLAYER_ACCELERATION * SCALING_FACTOR()), true);
				playerMove(MapManager.mapR, playerR, new Vector2f(0, -PLAYER_ACCELERATION * SCALING_FACTOR()), false);
				playerL.setDir(Direction.UP);
				playerR.setDir(Direction.UP);
			}
			case Input.KEY_A -> {
				playerMove(MapManager.mapL, playerL, new Vector2f(-PLAYER_ACCELERATION * SCALING_FACTOR(), 0), true);
				playerMove(MapManager.mapR, playerR, new Vector2f(PLAYER_ACCELERATION * SCALING_FACTOR(), 0), false);
				playerL.setDir(Direction.LEFT);
				playerR.setDir(Direction.RIGHT);
			}
			case Input.KEY_S -> {
				playerMove(MapManager.mapL, playerL, new Vector2f(0, PLAYER_ACCELERATION * SCALING_FACTOR()), true);
				playerMove(MapManager.mapR, playerR, new Vector2f(0, PLAYER_ACCELERATION * SCALING_FACTOR()), false);
				playerL.setDir(Direction.DOWN);
				playerR.setDir(Direction.DOWN);
			}
			case Input.KEY_D -> {
				playerMove(MapManager.mapL, playerL, new Vector2f(PLAYER_ACCELERATION * SCALING_FACTOR(), 0), true);
				playerMove(MapManager.mapR, playerR, new Vector2f(-PLAYER_ACCELERATION * SCALING_FACTOR(), 0), false);
				playerL.setDir(Direction.RIGHT);
				playerR.setDir(Direction.LEFT);
			}
		}
	}

	private boolean playerMove(GameMap gm, Player plr, Vector2f disp, boolean left)	{
		plr.move(gm, disp, 0);
		if(plr.collides(gm) || ((left)?plr.getPos().getX()+(plr.getHitbox().getWidth()/2) > Main.width()/2:
				plr.getPos().getX()-(plr.getHitbox().getWidth()/2) < Main.width()/2))	{
			plr.move(gm, disp.negate(), 0);
			return true;
		}
		return false;
	}
}