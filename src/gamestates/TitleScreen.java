package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.ui.button.Button;
import managers.ImageManager;
import managers.SoundManager;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

public class TitleScreen extends AdvancedGameState {

    public final int id;
    private int counter = 0;
    private int fade = 255;
    private Image logo;
    private Image lightarc;
    private Button playButton;
    private Button quitButton;
    private Button settingsButton;

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
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        logo = ImageManager.getImage("logo");
        lightarc = ImageManager.getImage("lightarc").getScaledCopy(0.75f);
        playButton = new Button(Main.width() / 4 * 3, Main.height() / 4 * 3, "Play", () -> sbg.enterState(Main.GAME_ID, new FadeOutTransition(), new FadeInTransition()));
        settingsButton = new Button(Main.width() / 4 * 3, Main.height() / 4 * 3 + 50, "Settings", () -> Main.sbg.enterState(Main.SETTINGS_ID, new FadeOutTransition(), new FadeInTransition()));
        quitButton = new Button(Main.width() / 4 * 3, Main.height() / 4 * 3 + 100, "Quit", () -> sbg.enterState(Main.QUIT_ID, new FadeOutTransition(), new FadeInTransition()));
        if (!SoundManager.isMusicPlaying()) SoundManager.playBackgroundMusic("title");
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        counter++;
        playButton.update(gc);
        settingsButton.update(gc);
        quitButton.update(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        lightarc.setImageColor(1, 1, 1, 1 * ((float) counter / (100 * Main.config.FRAMES_PER_SECOND / 60)));
        lightarc.drawCentered(Main.width() / 2, Main.height() / 2);
        logo.getScaledCopy(0.33f).drawCentered(Main.width() / 4, Main.height() / 4);
        playButton.render(gc);
        settingsButton.render(gc);
        quitButton.render(gc);
    }
}
