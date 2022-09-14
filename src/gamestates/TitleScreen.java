package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import managers.ImageManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

public class TitleScreen extends AdvancedGameState {

    public final int id;
    private int counter = 0;
    private int fade = 255;
    private Image logo;

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
        logo = ImageManager.getImage("logo");
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        counter++;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        if (counter > 560 * Main.config.FRAMES_PER_SECOND / 60) {
            logo.setImageColor(1, 1, 1, (counter - 560 <= 40 ? (((float) counter - 560)/40) : (((960 - (float) counter)/40))));
            logo.getScaledCopy(0.66f).drawCentered(Main.width() / 2, Main.height() / 2);
        }
    }
}
