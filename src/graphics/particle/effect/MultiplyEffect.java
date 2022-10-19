package graphics.particle.effect;

import core.Main;
import graphics.particle.ImageParticle;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import util.Vector2f;

import static org.lwjgl.opengl.GL11.*;

public class MultiplyEffect extends ImageParticle {


    public MultiplyEffect(Vector2f pos, Vector2f speed, Image image) {
        super(pos, speed, image);
        image.setImageColor(205,255,50);
        setSpeed(new Vector2f(3*(float) (Math.random()-0.5), 3*(float) (Math.random()-0.5)));
    }

    public void render(){
        GL11.glEnable(GL_BLEND);
        GL11.glBlendFunc(GL_DST_COLOR,GL_ONE_MINUS_SRC_ALPHA);
        super.render();
        GL11.glDisable(GL_BLEND);
    }

    public void update() {
        if(pos.getX() < -image.getWidth()){
            this.setX(Main.width());
        }
        if(pos.getX()>Main.width()){
            this.setX(-image.getWidth());
        }
        if(pos.getX() < -image.getHeight()){
            this.setX(Main.height());
        }
        if(pos.getY()>Main.height()){
            this.setY(-image.getHeight());
        }
        //applyForce(new Vector2f(1f,1f));
        move();
    }
}
