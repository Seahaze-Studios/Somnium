package gamestates;

import gamestates.types.AdvancedGameState;
import graphics.GameBackground;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends AdvancedGameState {
    public final int id;
    private static GameBackground b;

    public TestState(int id){
        this.id = id;
    }
    public int getID()
    {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
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
        g.setColor(new Color(1f,1f,1f));
        g.fillRect(50,50,600,200);
        g.fillRect(50,330,600,200);
        b.render(g);
        g.drawString("gabagroumd",0,0);
    }
}
