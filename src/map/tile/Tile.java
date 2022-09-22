package map.tile;

import core.Constants;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

public abstract class Tile {
    protected Shape hitbox;

    public Tile(int x, int y)  {
        this.hitbox = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
    public Shape getHitbox()    {
        return hitbox;
    }
}
