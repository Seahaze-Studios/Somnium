package entities.units.player;

import core.Constants;
import entities.units.Unit;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import util.Vector2f;

public class Player extends Unit {

    public Player(int x, int y) throws SlickException {
        super(x,y);
        init();
    }

    public Player() throws SlickException {
        super();
        init();
    }

    public Player(Vector2f pos) throws SlickException {
        super(pos);
        init();
    }

    public void init() throws SlickException {
        super.init();
        width = Constants.TILE_SIZE;
        height = Constants.TILE_SIZE;
        this.sprite = new Image("res/dev/placeholder.png").getScaledCopy(54,54);
    }
}
