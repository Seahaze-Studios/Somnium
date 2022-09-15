package graphics.particle.effect;

import gamestates.Game;
import graphics.particle.ImageParticle;
import graphics.particle.Particle;
import managers.ImageManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import util.Vector2f;

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
        image.setImageColor(r, g, b, lifetime > 240 ? 1f - (lifetime - 240/240f) : (lifetime/240f));
        image.draw(pos.x, pos.y);
    }

    public void motion() {
        lifetime++;
        render();
    }


}
