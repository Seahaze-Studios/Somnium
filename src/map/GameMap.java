package map;

import core.Constants;
import map.tile.Tile;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import java.awt.*;
import java.util.ArrayList;

public class GameMap extends TiledMap {

    private ArrayList<Tile> tiles;

    public GameMap(String ref) throws SlickException {
        super(ref);
        tiles = new ArrayList<>();
    }

    public ArrayList<Tile> getTileList()    {
        return tiles;
    }

}
