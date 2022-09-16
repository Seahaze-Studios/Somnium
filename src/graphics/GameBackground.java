package graphics;

import graphics.particle.effect.BackgroundEffect;
import managers.ImageManager;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleSystem;
import util.Vector2f;

import java.util.ArrayList;

public class GameBackground {
    private Image image;
    private ArrayList<BackgroundEffect> particles = new ArrayList<BackgroundEffect>();

    public GameBackground(){
        image = ImageManager.getImage("glow");
        image.setImageColor(0f,1f,1f);

    }
    public static void update(){
       //particles.forEach(p -> p.update(g);
    }
    public static void render(Graphics g){
        //p.render();
        //g.fillRect();
    }


}
