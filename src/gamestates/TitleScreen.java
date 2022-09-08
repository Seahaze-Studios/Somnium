package gamestates;

import gamestates.types.AdvancedGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class TitleScreen extends AdvancedGameState {

    public final int id;

    public TitleScreen(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    } // R

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {

    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }
}
