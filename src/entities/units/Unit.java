package entities.units;

import entities.Entity;
import org.newdawn.slick.SlickException;
import util.Vector2f;

public class Unit extends Entity {
    public Unit() throws SlickException {
        super();
    }
    public Unit(float x, float y) throws SlickException {
        super(x,y);
    }
    public Unit(Vector2f pos) throws SlickException {
        super(pos);
    }
}
