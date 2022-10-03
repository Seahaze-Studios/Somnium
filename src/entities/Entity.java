package entities;

import gamestates.Game;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Rectangle;
import util.Vector2f;

import java.io.Serializable;
import java.util.List;

public abstract class Entity implements Serializable {
    protected Vector2f pos; // x
    protected Vector2f speed; // dx/dt
    protected Vector2f acceleration; // d^2x/dt^2
    protected Vector2f jerk; // d^3x/dt^3
    protected List<Vector2f> taylorSeries;
    protected Image sprite;
    protected SpriteSheet sheet;
    protected transient Rectangle hitbox;
    protected float width;
    protected float height;
    protected GameContainer gc;
    protected Graphics g;
    protected int frame;
    protected Direction dir;

    protected Entity() throws SlickException {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = new Vector2f(0, 0);
        speed = new Vector2f(0, 0);
        acceleration = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }
    protected Entity(float x, float y) throws SlickException {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = new Vector2f(x, y);
        speed = new Vector2f(0, 0);
        acceleration = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }

    protected Entity(Vector2f position) throws SlickException {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = position;
        speed = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }


    public void init() throws SlickException {
        hitbox = new Rectangle(pos.x - width / 2, pos.y - height / 2, width, height);
        dir = Direction.NONE;
    }

    public void update() {
        move();
        Game.entities.forEach(e -> {
           if (e.getHitbox().intersects(this.hitbox)) collide(e);
        });
    }

    public void render() {
        sprite.drawCentered(pos.getX(), pos.getY());
    }

    public void applyForce(Vector2f f){speed.add(f);}

    public void move() {
        accelerate();
        move(speed);
    }

    public void accelerate() {
        jerk();
        speed.add(acceleration);
    }

    public void jerk() {
        acceleration.add(jerk);
    }

    public void accelerate(Vector2f dv) {
        speed.add(dv);
    }

    public void move(Vector2f disp)  {
        pos.add(disp);
        hitbox.setX(hitbox.getX() + disp.x);
        hitbox.setY(hitbox.getY() + disp.y);
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

    public Direction getDir() {
        return dir;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y) {
        this.pos.set(x, y);
    }

    public void setHitbox(float x, float y) {
        this.hitbox.setX(x);
        this.hitbox.setY(y);
    }

    public void setX(float x) {
        this.pos.set(x, this.pos.y);
    }

    public void setY(float y) {
        this.pos.set(this.pos.x, y);
    }

    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }

    public void setDir(Direction newDir)    {
        this.dir = newDir;
    }
}
