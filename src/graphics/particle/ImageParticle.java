package graphics.particle;

import gamestates.Game;
import org.newdawn.slick.Image;
import util.Vector2f;

public class ImageParticle extends Particle {
    protected Image image;

    public ImageParticle(Vector2f pos, Image image) {
        super(pos);
        this.image = image;
    }

    public ImageParticle(Vector2f pos, Vector2f speed, Image image) {
        super(pos, speed);
        this.image = image;
    }

    public ImageParticle(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public void render() {
        image.draw(this.pos.x, this.pos.y);
    }
}
