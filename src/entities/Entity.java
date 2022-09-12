package entities;

import gamestates.Game;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import util.Vector2f;

import java.io.Serializable;

public abstract class Entity implements Serializable {
    protected Vector2f pos;
    protected Vector2f speed;
    protected Image sprite;
    protected SpriteSheet sheet;
    protected transient Rectangle hitbox;
    protected float width;
    protected float height;
    protected GameContainer gc;
    protected Graphics g;
    protected int frame;

    protected Entity() throws SlickException {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = new Vector2f(0, 0);
        speed = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }
    protected Entity(float x, float y) throws SlickException {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = new Vector2f(x, y);
        speed = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }

    public void init() throws SlickException {
        hitbox = new Rectangle(pos.x - width / 2, pos.y - height / 2, width, height);
    }

    public void update() {
        move();
        Game.entities.forEach(e -> {
           if (e.getHitbox().intersects(this.hitbox)) collide(e);
        });
    }

    public void render() {
        frame++;
    }

    public void applyForce(Vector2f f){speed.add(f);}

    public void move() {
        pos.add(speed);

    }

    public void collide(Entity e) {
        pos.add(e.getSpeed());
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getSpeed() {
        return speed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y) {
        this.pos.set(x, y);
    }

    public void setX(float x) {
        this.pos.set(x, this.pos.y);
    }

    public void setY(float y) {
        this.pos.set(this.pos.x, y);
    }
}
