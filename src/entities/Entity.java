package entities;

import gamestates.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
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
    public enum RenderState {
        WALKING, ATTACKING, DAMAGED, IDLE;
    }
    protected RenderState state;
    protected int frame;

    protected Entity() {
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        pos = new Vector2f(0, 0);
        speed = new Vector2f(0, 0);
        width = 1;
        height = 1;
        init();
    }

    public void init() {
        hitbox = new Rectangle(pos.x - width / 2, pos.y - height / 2, width, height);
    }

    public void update() {
        move();
        Game.entities.forEach(e -> {
           if (e.getHitbox().intersects(this.hitbox)) collide(e);
        });
    }

    public void render() {
        switch (state) {
            case WALKING -> sheet.getSprite(frame, 0).drawCentered(pos.x, pos.y);
            case ATTACKING -> sheet.getSprite(frame, 1).drawCentered(pos.x, pos.y);
            case DAMAGED -> sheet.getSprite(frame, 2).drawCentered(pos.x, pos.y);
            case IDLE -> sheet.getSprite(frame, 3).drawCentered(pos.x, pos.y);
        }
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
