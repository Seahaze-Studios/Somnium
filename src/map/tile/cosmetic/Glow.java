package map.tile.cosmetic;



import core.Configuration;
import core.Main;
import graphics.particle.effect.GlowEffect;
import util.Vector2f;

import org.newdawn.slick.Color;
import java.util.ArrayList;
import java.util.Random;

public class Glow extends Cosmetic{
    private ArrayList<GlowEffect> glows;
    private Color color;
    private Random r;
    private final float scale = 0.25f;

    public Glow(int x, int y, Color c)  {
        super(x,y);
        color = c;
        r = new Random();
        glows = new ArrayList<>();
//        for(int i = 0; i < 10; i++) {
//            glows.add(new GlowEffect(new Vector2f((float) ((Math.random() * getHitbox().getMaxX()) + getHitbox().getX())- 42,
//                                                  (float) ((Math.random() * getHitbox().getMaxY()) + getHitbox().getY())-32),
//                    new Vector2f(r.nextInt(-2, 5), r.nextInt(-2, 5)), color));
//            glows.get(i).resize(Main.config.GLOW_SIZE);
//        }
    }

    public void glowRender()    {
        if(Math.random() < 0.1 && glows.size() < 30) {
            glows.add(new GlowEffect(new Vector2f((float) (r.nextInt((int)this.getHitbox().getX(),(int)this.getHitbox().getMaxX()) - 42),
                                                (float)(r.nextInt((int)this.getHitbox().getY(),(int)this.getHitbox().getMaxY()))-32),
                    new Vector2f(r.nextInt(-2, 5), r.nextInt(-2, 5)), color));
            glows.get(glows.size()-1).resize(Main.config.GLOW_SIZE);
        }
        glows.forEach(GlowEffect::motion);
        for(int i = glows.size()-1; i >0;i--)    {
            if(glows.get(i).getLifetime() > 400)    {
                glows.remove(i);
            }
        }

    }

}
