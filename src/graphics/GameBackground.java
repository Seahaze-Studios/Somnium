package graphics;

import core.Main;
import graphics.particle.effect.BackgroundEffect;
import managers.ImageManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleSystem;
import util.Vector2f;

import java.util.ArrayList;

import static org.newdawn.slick.Graphics.*;

public class GameBackground {
    private static Image image;
    private static ArrayList<BackgroundEffect> particles = new ArrayList<BackgroundEffect>();

    public GameBackground() {
        //image = ImageManager.getImage("glow");  //this doesn't work.
        image = ImageManager.getImage("spot");
        image.setImageColor(0f,0.5f,1f);
        for(int i = 0; i < 35; i++){
            particles.add(new BackgroundEffect(new Vector2f((float) (Math.random()* Main.getScreenWidth()), (float) (Math.random()* Main.getScreenHeight())),new Vector2f(0,0),image));
        }

    }
    public static void update(){
       particles.forEach(p -> p.update());
    }
    public static void render(Graphics g){

        particles.forEach(p -> p.render());
        //GL11.glBlendFunc(GL11.GL_DST_COLOR,GL11.GL_SRC_COLOR);
        g.setDrawMode(MODE_ADD);
        g.setColor(new Color(0.3f,0.0f,0.5f));
        g.fillRect(0,0,Main.getScreenWidth(),Main.getScreenHeight());
        g.setDrawMode(MODE_NORMAL);

    }


}
