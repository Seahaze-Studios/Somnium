package gamestates;

import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.ui.selection.ToggleBar;
import graphics.ui.tabber.Tab;
import graphics.ui.tabber.TabBody;
import graphics.ui.tabber.Tabber;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;
import util.Commandable;
import util.DrawUtilities;
import util.bundle.Bundle;

import java.util.ArrayList;

public class Settings extends AdvancedGameState {
    public final int id;
    private RoundedRectangle box;
    private ArrayList<Line> lines;
    private Tabber tabber;
    private int counter = 0;

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
        lines = new ArrayList<>();
        lines.add(new Line(Main.width() / 7, 50, Main.width() / 7, Main.height() - 50));
    }

    @Override
    public void enter(GameContainer container, StateBasedGame game) throws SlickException {
        counter = 0;
        tabber = new Tabber(
                new Tab(
                        new RoundedRectangle(50, 50, Main.width() / 7 - 50, 50, 20, RoundedRectangle.TOP_LEFT),
                        "Graphics",
                        new TabBody(new Bundle<String, ToggleBar>("Framerate (fps)", new ToggleBar(
                                new Bundle<String, Commandable>("24", () -> {}),
                                new Bundle<String, Commandable>("30", () -> {}),
                                new Bundle<String, Commandable>("45", () -> {}),
                                new Bundle<String, Commandable>("60", () -> {}),
                                new Bundle<String, Commandable>("75", () -> {}),
                                new Bundle<String, Commandable>("90", () -> {}),
                                new Bundle<String, Commandable>("120", () -> {}),
                                new Bundle<String, Commandable>("144", () -> {}),
                                new Bundle<String, Commandable>("240", () -> {}),
                                new Bundle<String, Commandable>("360", () -> {})
                            ).setToggledIndex(3)))
                        ).setActive(true),
                new Tab(
                        new Rectangle(50, 100, Main.width() / 7 - 50, 50),
                        "Audio",
                        new TabBody(new Bundle<String, ToggleBar>("Volume", new ToggleBar(new Bundle<String, Commandable>("10", () -> {}))))
                )
        );
    }

    @Override
    public void update(GameContainer gameContainer, StateBasedGame stateBasedGame, int i) throws SlickException {
        counter++;
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        g.setBackground(Color.black);
        tabber.render(gc);
        g.draw(lines.get(0));
//        g.setColor(new Color(255, 255, 255));
//        g.draw(box);
//        DrawUtilities.animateLines(g, lines, counter, 1d);
    }
}
