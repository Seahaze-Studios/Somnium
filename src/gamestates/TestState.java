package gamestates;

import gamestates.types.AdvancedGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TestState extends AdvancedGameState {
    public final int id;

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

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
    }
}
