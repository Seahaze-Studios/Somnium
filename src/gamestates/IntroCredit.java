// added a comment

package gamestates;

import core.Fonts;
import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.particle.effect.GlowEffect;
import managers.SoundManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Path;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.DrawUtilities;
import util.Vector2f;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static org.lwjgl.opengl.GL11.GL_RENDERER;

public class IntroCredit extends AdvancedGameState {

    private final int id;
    private Image logo;
    private Image titleLogo;
    private StateBasedGame sbg;

    private int counter = 0;
    private int counter2 = 100;
    private int fade = 255;

    class PathBundle {
        public Path path;
        public Color color;
        public PathBundle(Path path, Color color) {
            this.path = path;
            this.color = color;
        }
    }

    private ArrayList<PathBundle> paths = new ArrayList<>();
    private ArrayList<GlowEffect> glows = new ArrayList<>();
    private Random R = new Random();

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
        titleLogo = new Image("res/ui/start/logo.png").getScaledCopy(0.66f);
    }

    @Override // Begin file loading upon entering the gamestate
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        counter = 0;
        counter2 = 100 * Main.config.FRAMES_PER_SECOND / 60;
        for (var i = 0; i < 10; i++) {
            int x = R.nextInt(0, Main.width());
            int y = R.nextInt(0, Main.height());
            int direction = x >= Main.width() / 2 ? 0 : 1; // 0 left, 1 right
            int firstX = direction == 1 ? x + R.nextInt(400, 800) : x - R.nextInt(400, 800);
            int firstY = direction == 1 ? y + R.nextInt(200, 400) : x - R.nextInt(200, 400);
            int secondX = direction == 1 ? firstX + R.nextInt(400, 800) : firstX - R.nextInt(400, 800);
            int secondY = direction == 1 ? firstY + R.nextInt(200, 400) : firstY - R.nextInt(200, 400);
            int thirdX = direction == 1 ? secondX + R.nextInt(400, 800) : secondX - R.nextInt(400, 800);
            int thirdY = direction == 1 ? secondY + R.nextInt(200, 400) : secondY - R.nextInt(200, 400);
            Color color = new Color(R.nextInt(0, 255), R.nextInt(0, 255), R.nextInt(0, 255));
            paths.add(new PathBundle(new Path(x, y), color));
            paths.get(i).path.curveTo(thirdX, thirdY, firstX, firstY, secondX, secondY, 40);
        }

        for (var i = 0; i < 10000; i++) {
            glows.add(new GlowEffect(new Vector2f(R.nextInt(0, Main.width()), R.nextInt(0, Main.height())), new Vector2f(R.nextInt(-2, 2), R.nextInt(-2, 2)), Color.white));
        }
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
//            g.setColor(paths.get(((counter - 300) / 60)).color);
//            g.draw(paths.get(((counter - 300) / 60)).path);
            if (R.nextInt(0, 15) == 4) glows.get(R.nextInt(0, glows.size() - 1)).inMotion = true;
            glows.stream().filter(p -> p.inMotion).forEach(GlowEffect::motion);
        }
        if (counter > 860 * Main.config.FRAMES_PER_SECOND / 60) {
            //titleLogo.setImageColor(1, 1, 1, 1 * ((float) counter / (100 * Main.config.FRAMES_PER_SECOND / 60)));
            titleLogo.setImageColor(1f, 1f, 1f, 1 * ((float) (counter - 860) / (2f * Main.config.FRAMES_PER_SECOND / 60)));
            titleLogo.drawCentered(Main.width() / 2, Main.height() / 2);
        }
        if (counter > 960 * Main.config.FRAMES_PER_SECOND / 60) {
            g.setColor(new Color(255, 255, 255, (float) (counter - 960) / 20));
            DrawUtilities.drawStringCentered(g, "Press any key to start", Main.width() / 2, Main.height() / 2 + 200);
        }
        //if (counter > 960) sbg.enterState(Main.TITLE_ID);

        logo.drawCentered(Main.getScreenWidth() / 2, Main.getScreenHeight() / 2);
        super.render(gc, sbg, g);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_F1) sbg.enterState(Main.TITLE_ID, new FadeOutTransition(), new FadeInTransition());
        if (counter > 960 * Main.config.FRAMES_PER_SECOND / 60) sbg.enterState(Main.TITLE_ID, new FadeOutTransition(), new FadeInTransition());
    }
}
