package graphics.particle;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import util.Vector2f;

public class SpritesheetParticle extends Particle {
    // This class is likely going to not be used because of the Animation class but hey I made it just in case
    protected SpriteSheet sheet;

    public SpritesheetParticle(Vector2f pos, SpriteSheet sheet) {
        super(pos);
        this.sheet = sheet;
    }

    public SpritesheetParticle(Vector2f pos, Vector2f speed, SpriteSheet sheet) {
        super(pos, speed);
        this.sheet = sheet;
    }

    public SpritesheetParticle(int x, int y, SpriteSheet sheet) {
        super(x, y);
        this.sheet = sheet;
    }

    public void render() {

    }
}
