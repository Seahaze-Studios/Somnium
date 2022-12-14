package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.GameBackground;
import graphics.particle.effect.GlowEffect;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import util.Vector2f;

import java.util.ArrayList;

/* ===========================================
DEBUG - TESTING CLASS
EXPERIMENTATION ONLY
NOT USED IN GAME
==============================================
 */
public class TestState extends AdvancedGameState {
    public final int id;
    private static GameBackground b;

    private ArrayList<GlowEffect> bg;
    public TestState(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        bg = new ArrayList<GlowEffect>();

    }
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        for(int i = 0; i < 30; i++){
            bg.add(new GlowEffect(new Vector2f((float) (Math.random()* Main.width()), (float) (Math.random()*Main.height())),new Vector2f(0,0), new Color(1f,1f,1f)));
        }
    }

    /*@Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        b = new GameBackground();
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        b.update();
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        g.setColor(new Color(1f, 1f, 1f));
        g.fillRect(50, 50, 600, 200);
        g.fillRect(50, 330, 600, 200);
        b.render(g);
        g.drawString("gabagroumd", 0, 0);
    }

    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        for(int j = 0; j < bg.size(); j++){
            bg.get(j).motion();
        }
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        for(int i = 0; i < bg.size(); i++){
            bg.get(i).render();//g);
        }
        g.drawString("test state",0 ,0);
    }*/
}
