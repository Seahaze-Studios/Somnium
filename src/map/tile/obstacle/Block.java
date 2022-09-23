package map.tile.obstacle;

import core.Constants;
import org.newdawn.slick.geom.Rectangle;

public class Block extends Obstacle {
    public Block(int x, int y)  {
        super(x, y);
        this.hitbox = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
}
