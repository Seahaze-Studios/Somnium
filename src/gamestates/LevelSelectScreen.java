package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
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

import java.util.HashMap;

public class LevelSelectScreen extends AdvancedGameState {

    public final int id;
    private int counter = 0;
    private int level = 1;
    private HashMap<Integer, Shape> shapes;

    public LevelSelectScreen(int id)
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
        gc.getGraphics().setColor(Color.white);
        shapes = new HashMap<>();
        shapes.put(1, new Circle(Main.width() / 2, Main.height() / 2, 400));
        shapes.put(2, new Circle(Main.width() / 2, Main.height() / 2, 400));
        for (double i = 3; i <= 30; i++) {
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
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        counter++;
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        g.setBackground(Color.black);
        g.setColor(Color.white);
        g.draw(shapes.get(level));
        g.drawString(level + "", 400, 400);
    }

    @Override
    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_UP) level++;
        if (key == Input.KEY_DOWN && level > 1) level--;
    }
}
