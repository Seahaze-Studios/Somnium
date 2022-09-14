// added a comment

package gamestates;

import core.Fonts;
import core.Main;
import gamestates.types.AdvancedGameState;
import managers.SoundManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.DrawUtilities;

import java.awt.*;
import java.io.IOException;

import static org.lwjgl.opengl.GL11.GL_RENDERER;

public class IntroCredit extends AdvancedGameState {

    private final int id;
    private Image logo;
    private Image titleLogo;
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
        try {
            Main.fonts = new Fonts();
            gc.setDefaultFont(new TrueTypeFont(Main.fonts.generator.deriveFont(java.awt.Font.PLAIN, 20), true));
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }
        logo = new Image("res/ui/start/seahazestudios.png").getScaledCopy(700, 700);
        this.sbg = sbg;
        Main.sbg = sbg;
        String test = GL11.glGetString(GL_RENDERER);
        System.out.println(test);
        titleLogo = new Image("res/ui/start/logo.png");
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
        if (counter == 300) SoundManager.playBackgroundMusic("title");
        if (counter > 300 * Main.config.FRAMES_PER_SECOND / 60) {
            //sbg.enterState(Main.LOADING_ID, new FadeOutTransition(), new FadeInTransition());
            fade -= 3;
            g.setBackground(new Color(fade, fade, fade));
        }
        if (counter > 860 * Main.config.FRAMES_PER_SECOND / 60) {
            titleLogo.setImageColor(1, 1, 1, (counter - 860 <= 40 ? (((float) counter - 860)/40) : (((960 - (float) counter)/40))));
            titleLogo.getScaledCopy(0.66f).drawCentered(Main.width() / 2, Main.height() / 2);
        }
        if (counter > 960 * Main.config.FRAMES_PER_SECOND / 60) {
            g.setColor(new Color(255, 255, 255, (float) (counter - 960) / 20));
            DrawUtilities.drawStringCentered(g, "Press any key to start", Main.width() / 2, Main.height() / 2 + 200);
        }
        //if (counter > 960) sbg.enterState(Main.TITLE_ID);

        logo.drawCentered(Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        super.render(gc, sbg, g);
    }
}
