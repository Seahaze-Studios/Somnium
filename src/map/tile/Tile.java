package map.tile;

import org.newdawn.slick.geom.Shape;

public abstract class Tile {
    protected Shape hitbox;

    public Shape getHitbox()    {
        return hitbox;
    }
}
