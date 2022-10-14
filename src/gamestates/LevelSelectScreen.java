package gamestates;

import core.Constants;
import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.particle.effect.GlowEffect;
import graphics.ui.button.Button;
import managers.ImageManager;
import managers.SoundManager;
import org.newdawn.slick.*;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Transform;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.DrawUtilities;
import util.RomanNumber;
import util.Vector2f;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import static core.Constants.SCALING_FACTOR;

public class LevelSelectScreen extends AdvancedGameState {
    private StateBasedGame sbg;

    public final int id;
    private int counter = 0;
    private int level = 1;
    private boolean generated = false;
    private HashMap<Integer, Shape> shapes;
    private ArrayList<IntroCredit.PathBundle> paths = new ArrayList<>();
    private ArrayList<GlowEffect> glows = new ArrayList<>();
    private Random R = new Random();

    public LevelSelectScreen(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    } // R
    private Color white;
    private Color black;

    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        white = new Color(1.0F, 1.0F, 1.0F, 1.0F);
        black = new Color(0.0F, 0.0F, 0.0F, 1.0F);
        this.sbg = sbg;
        shapes = new HashMap<>();
        shapes.put(1, new Circle(Main.width() / 2, Main.height() / 2, 400));
        shapes.put(2, new Circle(Main.width() / 2, Main.height() / 2, 400));
        for (double i = 3; i <= 1000; i++) {
            Polygon gon = new Polygon();
            for (double j = 0; j < i; j++) {
                gon.addPoint((float)Math.sin(j/i*2*Math.PI),
                        (float)Math.cos(j/i*2*Math.PI));
            }
            shapes.put((int) i, gon.transform(Transform.createScaleTransform(400f, 400f)));
            shapes.get((int) i).setCenterX(Main.width() / 2);
            shapes.get((int) i).setCenterY(Main.height() / 2);
        }
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        gc.getGraphics().setColor(Color.white);
        if (!generated) {
            generated = true;
            for (var i = 0; i < 1000; i++) {
                glows.add(new GlowEffect(new Vector2f(R.nextInt(0, Main.width()), R.nextInt(0, Main.height())), new Vector2f(R.nextInt(-2, 2), R.nextInt(-2, 2)), Color.white));
            }
        }
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        counter++;
    if (R.nextInt(0, (int) (15 * (1/SCALING_FACTOR()))) == 4) glows.get(R.nextInt(0, glows.size() - 1)).inMotion = true;
        if (gc.getInput().isKeyDown(Input.KEY_UP) && level < Constants.COLOR_L.size() - 1) level++;
        if (gc.getInput().isKeyDown(Input.KEY_DOWN) && level > 1) level--;
        if (gc.getInput().isMousePressed(0) && shapes.get(level).contains(gc.getInput().getMouseX(), gc.getInput().getMouseY())) {
            if (level <= Main.highestLevel) {
                Game.curLevelID = level;
                sbg.enterState(Main.GAME_ID, new FadeOutTransition(Color.black), new FadeInTransition(Color.black));
                SoundManager.playSoundEffect("unlocked");
            } else {
                SoundManager.playSoundEffect("locked");
            }
        }
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        g.setBackground(black);
        if (Main.highestLevel >= level) {
            if (level < Constants.COLOR_L.size()) {
                g.setColor(Constants.COLOR_L.get(level));
                g.fill(shapes.get(level));
            }
            else g.setColor(white);
        } else g.setColor(white);
        glows.stream().filter(p -> p.inMotion).forEach(gl -> {
            gl.setColor(g.getColor());
            gl.motion();
        });
        g.draw(shapes.get(level));
        var scaledShape = shapes.get(level).transform(Transform.createScaleTransform(0.7f, 0.7f));
        scaledShape.setCenterX(Main.width() / 2);
        scaledShape.setCenterY(Main.height() / 2);
        if (level != 1) {
            if (Main.highestLevel >= level) {
                if (level < Constants.COLOR_R.size()) g.setColor(Constants.COLOR_R.get(level));
                g.draw(scaledShape);
                g.fill(scaledShape);
                g.setColor(white);
            } else {
                g.draw(scaledShape);
                g.setColor(white);
            }
        }
        if (level == 1) g.setColor(black);
        DrawUtilities.drawStringCentered(g, RomanNumber.toRoman(level), Main.fonts.ROMAN_NUMERAL, Main.width() / 2, Main.height() / 2);
        if (Main.highestLevel < level) ImageManager.getImage("lock").getScaledCopy(0.1f).drawCentered(Main.width() / 2, Main.height() / 2 + 100);
        if(Main.debug) debugRender(gc);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_RIGHT && level < Constants.COLOR_L.size() - 1) level++;
        if (key == Input.KEY_LEFT && level > 1) level--;
        if(key == Input.KEY_0 && Main.debug) {
            Game.curLevelID = 0;
            sbg.enterState(Main.GAME_ID, new FadeOutTransition(Color.white), new FadeInTransition(Color.white));
        }
    }
    public void debugRender(GameContainer gc)   {
        gc.getGraphics().setColor(Color.green);
        gc.getGraphics().drawString("DEBUG MODE ON", 100, 100);
    }
}
