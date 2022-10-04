package map.tile.cosmetic;

import map.tile.Tile;
import org.newdawn.slick.Animation;

public abstract class Cosmetic extends Tile {
    protected Animation animation;

    public Cosmetic(int x, int y) {
        super(x, y);
    }
}
