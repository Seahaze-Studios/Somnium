package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import util.DrawUtilities;

public class QuitState extends AdvancedGameState {
    public final int id;
    int counter;

    public QuitState(int id)
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
        counter++;
        if (counter == 20) System.exit(0);
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        DrawUtilities.drawStringCentered(g, "Goodbye.", Main.fonts.VariableWidth.P55, Main.width() / 2, Main.height() / 2);
    }
}
