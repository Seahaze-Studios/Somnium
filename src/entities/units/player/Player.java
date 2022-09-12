package entities.units.player;

import entities.units.Unit;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import util.Vector2f;

public class Player extends Unit {

    public Player(int x, int y) {
        super(x,y);
    }
    public Player() {
        super();
    }

    public void init() throws SlickException {
        super.init();
        this.sprite = new Image("res/dev/placeholder.png");
    }
}
