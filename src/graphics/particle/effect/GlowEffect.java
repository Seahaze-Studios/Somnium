package graphics.particle.effect;

import gamestates.Game;
import graphics.particle.ImageParticle;
import graphics.particle.Particle;
import managers.ImageManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import util.Vector2f;

import static core.Constants.SCALING_FACTOR;

public class GlowEffect extends ImageParticle {
    protected int lifetime;
    public boolean inMotion;
    protected float r, g, b;

    public GlowEffect(Vector2f pos, Vector2f speed, Color color) {
        super(pos, speed, ImageManager.getImage("glow"));
        r = color.getRed() / 255f;
        g = color.getGreen() / 255f;
        b = color.getBlue() / 255f;
    }

    public void render() {
        image.setImageColor(r, g, b, lifetime > (240 * (1/SCALING_FACTOR())) ? 1f - ((lifetime - (240 * (1/SCALING_FACTOR())))/(240 * (1/SCALING_FACTOR()))) : (lifetime/(240 * (1/SCALING_FACTOR()))));
        image.draw(pos.x, pos.y);
    }

    public void motion() {
        lifetime++;
        render();
    }

    public void setColor(int r, int g, int b) {
        this.r = r / 255f;
        this.g = g / 255f;
        this.b = b / 255f;
    }

    public void setColor(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public void setColor(Color color) {
        r = color.getRed() / 255f;
        g = color.getGreen() / 255f;
        b = color.getBlue() / 255f;
    }

    public int getLifetime()    {
        return lifetime;
    }
}
