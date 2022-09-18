package map;

import map.tile.Tile;
import map.tile.interactable.utility.Goal;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import util.Vector2f;

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
        plrPos = new Vector2f(0,0);
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

    public void render(int x, int y, Graphics g)    {
        super.render(x,y);
        g.setColor(new Color(color.r,color.g, color.b, 0.95f));
        tiles.forEach(t -> {if(!(t instanceof Goal)  )g.fill(t.getHitbox());});
    }


}
