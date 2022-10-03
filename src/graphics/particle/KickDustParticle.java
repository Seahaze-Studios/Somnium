package graphics.particle;

import entities.Entity;
import gamestates.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import util.Vector2f;

public abstract class KickDustParticle extends Particle {
    protected Vector2f pos;
    protected Vector2f speed;
    protected GameContainer gc;
    protected Graphics g;
    protected int lifetime;

    public KickDustParticle(Vector2f pos) {
        super(pos);
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        this.pos = pos;
        this.lifetime = 0;
    }

    public KickDustParticle(Vector2f pos, Vector2f speed) {
        super(pos, speed);
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        this.pos = pos;
        this.speed = speed;
        this.lifetime = 0;
    }

    public KickDustParticle(int x, int y) {
        super(x, y);
        this.gc = Game.getGc();
        this.g = gc.getGraphics();
        this.pos.x = x;
        this.pos.y = y;
        this.lifetime = 0;
    }

    public void applyForce(Vector2f f){speed.add(f);}

    public void move() {
        pos.add(speed);
    }

    public abstract void render();

    public void collide(Entity e) {
        pos.add(e.getSpeed());
    }

    public Vector2f getPos() {
        return pos;
    }

    public Vector2f getSpeed() {
        return speed;
    }

    public void setPos(Vector2f pos) {
        this.pos = pos;
    }

    public void setPos(float x, float y) {
        this.pos.set(x, y);
    }

    public void setSpeed(Vector2f speed) {
        this.speed = speed;
    }

    public void setX(float x) {
        this.pos.set(x, this.pos.y);
    }

    public void setY(float y) {
        this.pos.set(this.pos.x, y);
    }
}
