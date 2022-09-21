package graphics.particle.effect;

import graphics.particle.ImageParticle;
import managers.ImageManager;
import org.newdawn.slick.Color;
import util.Vector2f;

public class GlowBlackEffect extends ImageParticle {
    protected int lifetime;
    public boolean inMotion;
    protected float r, g, b;

    public GlowBlackEffect(Vector2f pos, Vector2f speed, Color color) {
        super(pos, speed, ImageManager.getImage("glowblack"));
        r = color.getRed() / 255f;
        g = color.getGreen() / 255f;
        b = color.getBlue() / 255f;
    }

    public void render() {
        image.setImageColor(r, g, b, lifetime > 240 ? 1f - ((lifetime - 240)/240f) : (lifetime/240f));
        image.draw(pos.x, pos.y);
    }

    public void motion() {
        lifetime++;
        render();
    }
}
