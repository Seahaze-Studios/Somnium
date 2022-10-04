package graphics.particle.effect;

import graphics.particle.ImageParticle;
import graphics.particle.Particle;
import managers.ImageManager;
import org.newdawn.slick.Image;
import util.Vector2f;
import org.newdawn.slick.Color;
import java.awt.*;

public class KickEffect extends Particle {

    private int age;

    public KickEffect(Vector2f pos, Vector2f speed) {
        super(pos, speed);
        applyForce(new Vector2f((float) (Math.random() - 0.5), 2f));
        age = 0;
        System.out.println("3e3");
    }

    public void update() {
        super.move();
        applyForce(new Vector2f(0, -1));
        age++;

    }

    public void render() {

        gc.getGraphics().fillOval(this.getPos().getX(), this.getPos().getY(), 6, 6, (int) ((Math.random() * 3) + 5));
    }

    public void render(Color color) {
        gc.getGraphics().setColor(color);
        gc.getGraphics().fillOval(this.getPos().getX(), this.getPos().getY(), 6, 6, (int) ((Math.random() * 3) + 5));
    }

    public int getAge() {
        return age;
    }
}