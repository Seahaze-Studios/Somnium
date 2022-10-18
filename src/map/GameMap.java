package map;

import map.tile.Tile;
import map.tile.cosmetic.Glow;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import util.Vector2f;

import java.util.ArrayList;

public class GameMap extends TiledMap {

    private ArrayList<Tile> tiles;
    private ArrayList<Tile> cosmeticTiles;
    public Vector2f plrPos;

    private Color color;
    private boolean left;

    public Color getColor() {
        return color;
    }

    public GameMap(String ref) throws SlickException {
        super(ref);
        plrPos = new Vector2f(0,0);
        tiles = new ArrayList<>();
        cosmeticTiles = new ArrayList<>();
        color = Color.black;
    }
    public GameMap(String ref, Color clr) throws SlickException {
        super(ref);
        plrPos = new Vector2f();
        tiles = new ArrayList<>();
        cosmeticTiles = new ArrayList<>();
        color = clr;
    }

    public ArrayList<Tile> getTileList()    {
        return tiles;
    }

    public ArrayList<Tile> getCosmeticTiles() {
        return cosmeticTiles;
    }

    public void render(int x, int y, Graphics g)    {
        super.render(x,y,0);
        g.setColor(new Color(color.r,color.g, color.b, 0.95f));
        tiles.forEach(t -> {if((t instanceof Obstacle)  )g.fill(t.getHitbox());});
    }

    public void cosmeticRender(int x, int y, Graphics g)    {
        cosmeticTiles.forEach(t -> {
            if(t instanceof Glow) {((Glow) t).glowRender();}
        });
    }

    public void imageRender(int x, int y, Graphics g)   {
        if(this.getLayerCount() > 2)    {
            this.render(x,y,2);
        }
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }
}
