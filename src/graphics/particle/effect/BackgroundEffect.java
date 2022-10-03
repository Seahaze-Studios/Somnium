package graphics.particle.effect;

<<<<<<< Updated upstream
<<<<<<< Updated upstream
import core.Main;
import graphics.particle.ImageParticle;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Image;
import util.Vector2f;

public class BackgroundEffect extends ImageParticle {


    public BackgroundEffect(Vector2f pos, Vector2f speed, Image image) {
        super(pos, speed, image);
        setSpeed(new Vector2f(3*(float) (Math.random()-0.5), 3*(float) (Math.random()-0.5)));
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
        //applyForce(new Vector2f(1f,1f));
        move();
=======
=======
>>>>>>> Stashed changes
import graphics.particle.ImageParticle;
import managers.ImageManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import util.Vector2f;

import static org.newdawn.slick.Graphics.MODE_COLOR_MULTIPLY;
import static org.newdawn.slick.Graphics.MODE_NORMAL;

public class BackgroundEffect extends ImageParticle {
    protected int lifetime;
    public boolean inMotion;
    protected float r, g, b;

    public BackgroundEffect(Vector2f pos, Vector2f speed) throws SlickException {
        super(pos, speed, ImageManager.getImage("wglow"));
        if(ImageManager.getImage("wglow") == null) {
            System.out.println("i hate you");
        }



        //r = color.getRed() / 255f;
        //g = color.getGreen() / 255f;
        //b = color.getBlue() / 255f;
    }

    public void render(Graphics g) {
        g.setDrawMode(MODE_COLOR_MULTIPLY);
        g.drawImage(image,pos.x, pos.y);
        g.setDrawMode(MODE_NORMAL);
    }

    public void update() {
        lifetime++;
        render();
<<<<<<< Updated upstream
>>>>>>> Stashed changes
=======
>>>>>>> Stashed changes
    }
}
