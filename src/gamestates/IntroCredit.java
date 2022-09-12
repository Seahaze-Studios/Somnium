// added a comment

package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import managers.SoundManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import static org.lwjgl.opengl.GL11.GL_RENDERER;

public class IntroCredit extends AdvancedGameState {

    private final int id;
    private Image logo;
    private StateBasedGame sbg;

    private int counter = 0;
    private int counter2 = 100;
    private int fade = 255;

    public IntroCredit(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    } // Returns the ID code for this game state

    // Initializer, first time
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        gc.setShowFPS(false);
        logo = new Image("res/ui/start/seahazestudios.png").getScaledCopy(700, 700);
        this.sbg = sbg;
        Main.sbg = sbg;
        String test = GL11.glGetString(GL_RENDERER);
        System.out.println(test);
        logo = new Image("res/ui/start/logo.png");
    }

    @Override // Begin file loading upon entering the gamestate
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        counter = 0;
        counter2 = 100 * Main.config.FRAMES_PER_SECOND / 60;
    }

    @Override // Update, runs consistently
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        counter++;
    }

    @Override // Render, all visuals
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.setBackground(Color.white);
        logo.setImageColor(1, 1, 1, 1 * ((float) counter / (100 * Main.config.FRAMES_PER_SECOND / 60)));
        if (counter > 200 * Main.config.FRAMES_PER_SECOND / 60) logo.setImageColor(1, 1, 1, 1 * ((float) --counter2 / (100 * Main.config.FRAMES_PER_SECOND / 60)));
        if (counter == 300) SoundManager.overrideBackgroundMusic(new Sound("res/audio/music/title.wav"));
        if (counter > 300 * Main.config.FRAMES_PER_SECOND / 60) {
            //sbg.enterState(Main.LOADING_ID, new FadeOutTransition(), new FadeInTransition());
            fade -= 3;
            g.setBackground(new Color(fade, fade, fade));
        }
        if (counter > 500) {
            logo.drawCentered(Main.width() / 2, Main.height() / 2);
        }

        logo.drawCentered(Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        super.render(gc, sbg, g);
    }
}
