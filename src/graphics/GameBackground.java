package graphics;

import core.Main;
import graphics.particle.ImageParticle;
import graphics.particle.effect.BackgroundEffect;
import graphics.particle.effect.MultiplyEffect;
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

import static core.Constants.SCALING_FACTOR;
import static org.newdawn.slick.Graphics.*;

public class GameBackground {
    private Image image;
    private ArrayList<ImageParticle> particles = new ArrayList<>();

    public GameBackground() {
        image = ImageManager.getImage("spot");
        image.setImageColor(0f,0.5f,1f);
        for(int i = 0; i < 99; i++){
            particles.add(new MultiplyEffect(new Vector2f((float) (Math.random()* Main.width() * (SCALING_FACTOR())), (float) (Math.random()* Main.height() * (SCALING_FACTOR()))),new Vector2f(0,0),image.getScaledCopy((float) (1+Math.random()))));
        }

    }
    public void update() {
        particles.forEach(ImageParticle::update);
    }
    public void render(Graphics g){
        particles.forEach(ImageParticle::render);
        g.setDrawMode(MODE_ADD);
        g.setColor(new Color(0.3f,0.0f,0.5f));
        g.setDrawMode(MODE_NORMAL);
    }


}
