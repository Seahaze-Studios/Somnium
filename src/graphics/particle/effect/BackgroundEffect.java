package graphics.particle.effect;

import core.Main;
import graphics.particle.ImageParticle;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import util.Vector2f;

public class BackgroundEffect extends ImageParticle {


    public BackgroundEffect(Vector2f pos, Vector2f speed, Image image) {
        super(pos, speed, image);
        setSpeed(new Vector2f(9*(float) (Math.random()-0.5), 9*(float) (Math.random()-0.5)));
    }

    public void render(){
        //GL11.glBlendFunc(GL11.GL_DST_ALPHA,GL11.GL_DST_COLOR);
        super.render();
    }

    public void update() {
        if(pos.getX() < -image.getWidth()){
            this.setX(Main.getScreenWidth());
        }
        if(pos.getX()>Main.getScreenWidth()){
            this.setX(-image.getWidth());
        }
        if(pos.getX() < -image.getHeight()){
            this.setX(Main.getScreenHeight());
        }
        if(pos.getY()>Main.getScreenHeight()){
            this.setY(-image.getHeight());
        }
        move();
    }
}
