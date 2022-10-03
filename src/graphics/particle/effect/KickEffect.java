package graphics.particle.effect;

import graphics.particle.ImageParticle;
import graphics.particle.Particle;
import managers.ImageManager;
import org.newdawn.slick.Image;
import util.Vector2f;

import java.awt.*;

public class KickEffect extends Particle {
    private Polygon poly;
    public KickEffect(Vector2f pos, Vector2f speed) {
        super(pos, speed);
        poly = new Polygon();
        poly.addPoint((int) (Math.random()*9), (int) (Math.random()*9));
        applyForce(new Vector2f((float) (Math.random()-0.5),2f));
    }
    public void update() {
        super.move();
        applyForce(new Vector2f(0,-1));

    }
    public void render() {

        gc.getGraphics().fillOval(this.getPos().getX(),this.getPos().getY(), 6, 6, (int) ((Math.random()*3)+5));
    }
}
