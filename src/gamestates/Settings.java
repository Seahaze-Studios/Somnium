package gamestates;

import core.Configuration;
import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.ui.button.Button;
import graphics.ui.button.PaddedButton;
import graphics.ui.selection.ToggleBar;
import graphics.ui.tabber.Tab;
import graphics.ui.tabber.TabBody;
import graphics.ui.tabber.Tabber;
import managers.SoundManager;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.Commandable;
import util.DrawUtilities;
import util.bundle.Bundle;

import java.util.ArrayList;
import java.util.Arrays;

import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.glGetString;

public class Settings extends AdvancedGameState {
    public final int id;
    private RoundedRectangle box;
    private ArrayList<Line> lines;
    private Tabber tabber;
    private Button saveButton;
    private String customFPS = "1000";
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
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        counter = 0;
        saveButton = new PaddedButton(Main.width() - 50 - 120, Main.height() - 50 - 40, Main.fonts.VariableWidth.P35.getWidth("Save and Exit") + 20, Main.fonts.VariableWidth.P35.getHeight() + 10, "Save and Exit", () -> sbg.enterState(Main.TITLE_ID, new FadeOutTransition(), new FadeInTransition()));
        tabber = new Tabber(
                new Tab(
                        new RoundedRectangle(50, 50, Main.width() / 7 - 50, 50, 20, 25, RoundedRectangle.TOP_LEFT),
                        "Graphics",
                        new TabBody(
                                new Bundle<>("Graphics Processor (for Multi GPU)", new ToggleBar(
                                        new Bundle<>(glGetString(GL_RENDERER), () -> { })
                                ).setToggledIndex(0)),
                                new Bundle<>("Graphics Quality Preset", new ToggleBar(
                                    new Bundle<>(Configuration.GraphicsQuality.LOW.name(), () -> { Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.LOW; }),
                                    new Bundle<>(Configuration.GraphicsQuality.MEDIUM.name(), () -> { Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.MEDIUM; }),
                                    new Bundle<>(Configuration.GraphicsQuality.HIGH.name(), () -> { Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.HIGH; }),
                                    new Bundle<>(Configuration.GraphicsQuality.ULTRA.name(), () -> { Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.ULTRA; })
                                ).setToggledIndex(Arrays.asList(Configuration.GraphicsQuality.values()).indexOf(Main.config.GRAPHICS_QUALITY))),
                                new Bundle<>("Framerate (fps)", new ToggleBar(
                                        new Bundle<>("24", () -> { Main.config.FRAMES_PER_SECOND = 24; }),
                                        new Bundle<>("30", () -> { Main.config.FRAMES_PER_SECOND = 30; }),
                                        new Bundle<>("45", () -> { Main.config.FRAMES_PER_SECOND = 45; }),
                                        new Bundle<>("50", () -> { Main.config.FRAMES_PER_SECOND = 50; }),
                                        new Bundle<>("60", () -> { Main.config.FRAMES_PER_SECOND = 60; }),
                                        new Bundle<>("75", () -> { Main.config.FRAMES_PER_SECOND = 75; }),
                                        new Bundle<>("90", () -> { Main.config.FRAMES_PER_SECOND = 90; }),
                                        new Bundle<>("120", () -> { Main.config.FRAMES_PER_SECOND = 120; }),
                                        new Bundle<>("144", () -> { Main.config.FRAMES_PER_SECOND = 144; }),
                                        new Bundle<>("165", () -> { Main.config.FRAMES_PER_SECOND = 165; }),
                                        new Bundle<>("180", () -> { Main.config.FRAMES_PER_SECOND = 180; }),
                                        new Bundle<>("240", () -> { Main.config.FRAMES_PER_SECOND = 240; }),
                                        new Bundle<>("360", () -> { Main.config.FRAMES_PER_SECOND = 360; }),
                                        new Bundle<>("390", () -> { Main.config.FRAMES_PER_SECOND = 390; }),
                                        new Bundle<>("...", () -> { Main.config.FRAMES_PER_SECOND = Integer.parseInt(customFPS); })
                                ).setToggledIndex(Configuration.presetFPS.indexOf(Main.config.FRAMES_PER_SECOND) > Configuration.presetFPS.size() ? Configuration.presetFPS.size() + 1 : Configuration.presetFPS.indexOf(Main.config.FRAMES_PER_SECOND))),
                                new Bundle<>("Unlimited FPS (not recommended)", new ToggleBar(
                                        new Bundle<>("O", () -> Main.config.UNLIMITED_FPS = false),
                                        new Bundle<>("I", () -> Main.config.UNLIMITED_FPS = true)
                                ).setToggledIndex(Main.config.UNLIMITED_FPS ? 1 : 0)),
                                new Bundle<>("Show FPS", new ToggleBar(
                                        new Bundle<>("O", () -> gc.setShowFPS(false)),
                                        new Bundle<>("I", () -> gc.setShowFPS(true))
                                ).setToggledIndex(gc.isShowingFPS() ? 1 : 0)),
                                new Bundle<>("VSync", new ToggleBar(
                                        new Bundle<>("O", () -> gc.setVSync(false)),
                                        new Bundle<>("I", () -> gc.setVSync(true))
                                ).setToggledIndex(gc.isVSyncRequested() ? 1 : 0)),
                                new Bundle<>("NVIDIA G-Sync / AMD FreeSync", new ToggleBar(
                                        new Bundle<>("O", () -> Main.config.GSYNC_FREESYNC = false),
                                        new Bundle<>("I", () -> Main.config.GSYNC_FREESYNC = true)
                                ).setToggledIndex(Main.config.GSYNC_FREESYNC ? 1 : 0))
                        )
                        ).setActive(true),
                new Tab(
                        new Rectangle(50, 100, Main.width() / 7 - 50, 50),
                        "Audio",
                        new TabBody(
                                new Bundle<>("Volume", new ToggleBar(
                                new Bundle<>("0", () -> gc.setSoundVolume(0f)),
                                new Bundle<>("1", () -> gc.setSoundVolume(0.1f)),
                                new Bundle<>("2", () -> gc.setSoundVolume(0.2f)),
                                new Bundle<>("3", () -> gc.setSoundVolume(0.3f)),
                                new Bundle<>("4", () -> gc.setSoundVolume(0.4f)),
                                new Bundle<>("5", () -> gc.setSoundVolume(0.5f)),
                                new Bundle<>("6", () -> gc.setSoundVolume(0.6f)),
                                new Bundle<>("7", () -> gc.setSoundVolume(0.7f)),
                                new Bundle<>("8", () -> gc.setSoundVolume(0.8f)),
                                new Bundle<>("9", () -> gc.setSoundVolume(0.9f)),
                                new Bundle<>("10", () -> gc.setSoundVolume(1f)))
                        ))
                ).setActive(false)
        );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        counter++;
        saveButton.update(gc);
        tabber.update(gc);
    }

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        g.setBackground(Color.black);
        tabber.render(gc);
        g.draw(lines.get(0));
        saveButton.setX(Main.width() - 50 - 120);
        saveButton.setY(Main.height() - 50 - 40);
        saveButton.render(gc);
//        g.setColor(new Color(255, 255, 255));
//        g.draw(box);
//        DrawUtilities.animateLines(g, lines, counter, 1d);
    }
}
