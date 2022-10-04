package map.tile.cosmetic;


import core.Constants;
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

    public Glow(int x, int y, Color c)  {
        super(x,y);
        color = c;
        r = new Random();
        glows = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            glows.add(new GlowEffect(new Vector2f((float) ((Math.random() * getHitbox().getMaxX()) + getHitbox().getX())- 42,
                                                  (float) ((Math.random() * getHitbox().getMaxY()) + getHitbox().getY())-42),
                    new Vector2f(r.nextInt(-2, 2), r.nextInt(-2, 2)), color));
            glows.get(i).resize(0.3f);
        }
    }

    public void glowRender()    {
        if(Math.random() < 0.3) {
            glows.add(new GlowEffect(new Vector2f((float) (r.nextInt((int)this.getHitbox().getX(),(int)this.getHitbox().getMaxX()) - 42),
                                                (float)(r.nextInt((int)this.getHitbox().getY(),(int)this.getHitbox().getMaxY()))-42),
                    new Vector2f(r.nextInt(-2, 2), r.nextInt(-2, 2)), color));
            glows.get(glows.size()-1).resize(0.3f);
        }
        glows.forEach(GlowEffect::motion);

    }

}
