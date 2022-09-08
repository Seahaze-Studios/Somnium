package graphics;

import managers.ImageManager;
import org.newdawn.slick.Image;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.particles.ParticleSystem;
import util.Vector2f;

import java.util.ArrayList;

public class GameBackground {
    private Image image;
    private ArrayList<Vector2f> particles = new ArrayList<Vector2f>();
    //private static ParticleSystem p;
    public GameBackground(){
        //image = ImageManager.getImage("glow");
        //image.setImageColor(0f,1f,1f);
        //p = new ParticleSystem(image, 50);
    }
    public static void update(){
        //p.update(2);
    }
    public static void render(){
        //p.render(0,0);
    }


}
