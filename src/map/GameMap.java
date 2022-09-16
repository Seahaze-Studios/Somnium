package map;

import map.tile.Tile;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;
import util.Vector2f;

import java.awt.*;
import java.util.ArrayList;

public class GameMap extends TiledMap {

    private ArrayList<Tile> tiles;
    public Vector2f plrPos;

    private Color color;

    public Color getColor() {
        return color;
    }

    public GameMap(String ref) throws SlickException {
        super(ref);
        plrPos = new Vector2f();
        tiles = new ArrayList<>();
        color = Color.black;
    }
    public GameMap(String ref, Color clr) throws SlickException {
        super(ref);
        plrPos = new Vector2f();
        tiles = new ArrayList<>();
        color = clr;
    }

    public ArrayList<Tile> getTileList()    {
        return tiles;
    }

}
