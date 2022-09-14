package map.tile.interactable.utility;

import core.Constants;
import org.newdawn.slick.geom.Rectangle;

public class Goal extends Utility{
    public Goal(int x, int y)  {
        this.hitbox = new Rectangle(x, y, Constants.TILE_SIZE, Constants.TILE_SIZE);
    }
}
