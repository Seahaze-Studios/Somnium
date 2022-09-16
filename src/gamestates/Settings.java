package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;
import util.DrawUtilities;

public class Settings extends AdvancedGameState {
    public final int id;
    private RoundedRectangle box;

    public Settings(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    }

    @Override
    public void init(GameContainer gameContainer, StateBasedGame stateBasedGame) throws SlickException {
        box = new RoundedRectangle(0, 0, Main.width() - 100, Main.height() - 100, 20);
        box.setCenterX(Main.width() / 2);
        box.setCenterY(Main.height() / 2);
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {

    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        g.setColor(new Color(255, 255, 255));
        g.draw(box);

    }
}
