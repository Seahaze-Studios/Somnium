// added a comment

package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import static org.lwjgl.opengl.GL11.GL_RENDERER;

public class IntroCredit extends AdvancedGameState {

    private final int id;
    private Image logo;
    private StateBasedGame sbg;

    private int counter = 0;
    private int counter2 = 100;

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
        if (counter > 300 * Main.config.FRAMES_PER_SECOND / 60) sbg.enterState(Main.GAME_ID);
        logo.drawCentered(Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        super.render(gc, sbg, g);
    }
}
