package map;

import core.Constants;
import map.tile.Tile;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class GameMap extends TiledMap {

    private ArrayList<Tile> tiles;
    public Vector2f plrPos;

    public GameMap(String ref) throws SlickException {
        super(ref);
        plrPos = new Vector2f();
        tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTileList()    {
        return tiles;
    }

}
