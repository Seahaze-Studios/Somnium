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
import java.util.List;

import static core.Constants.RES_X_FACTOR;
import static core.Constants.RES_Y_FACTOR;
import static org.lwjgl.opengl.GL11.GL_RENDERER;
import static org.lwjgl.opengl.GL11.glGetString;

public class Settings extends AdvancedGameState {
    public final int id;
    private RoundedRectangle box;
    private ArrayList<Line> lines;
    private Tabber tabber;
    private Button saveButton;
    private String customFPS = "1000";

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
        lines.add(new Line(Main.width() / 7, 50 * RES_X_FACTOR(), Main.width() / 7, Main.height() - 50 * RES_Y_FACTOR()));
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        super.enter(gc, sbg);
        saveButton = new PaddedButton(Main.width() - 50 - 120, Main.height() - 50 - 40, Main.fonts.VariableWidth.P35.getWidth("Save and Exit") + 20, Main.fonts.VariableWidth.P35.getHeight() + 10, "Save and Exit", () -> sbg.enterState(Main.TITLE_ID, new FadeOutTransition(), new FadeInTransition()));
        tabber = new Tabber(
                new Tab(
                        new RoundedRectangle(50, 50, Main.width() / 7 - 50, 50, 20, 25, RoundedRectangle.TOP_LEFT),
                        "Graphics",
                        new TabBody(
                                new Bundle<>("Graphics Processor (for Multi GPU)", new ToggleBar(
                                        new Bundle<>(glGetString(GL_RENDERER), () -> { })
                                ).setToggledIndex(0)),
                                new Bundle<>("Render Quality", new ToggleBar(
                                    new Bundle<>(Configuration.GraphicsQuality.LOW.name(), () -> Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.LOW),
                                    new Bundle<>(Configuration.GraphicsQuality.MEDIUM.name(), () -> Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.MEDIUM),
                                    new Bundle<>(Configuration.GraphicsQuality.HIGH.name(), () -> Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.HIGH),
                                    new Bundle<>(Configuration.GraphicsQuality.ULTRA.name(), () -> Main.config.GRAPHICS_QUALITY = Configuration.GraphicsQuality.ULTRA)
                                ).setToggledIndex(Arrays.asList(Configuration.GraphicsQuality.values()).indexOf(Main.config.GRAPHICS_QUALITY))),
                                new Bundle<>("Resolution (don't touch)", new ToggleBar(
                                        new Bundle<>("1920x1080", () -> { Main.config.RESOLUTION = new int[] {1920, 1080}; try { enter(gc, sbg); } catch (Exception ignored) {} }),
                                        new Bundle<>("1600x900", () -> { Main.config.RESOLUTION = new int[] {1600, 900}; try { enter(gc, sbg); } catch (Exception ignored) {} }),
                                        new Bundle<>("1280x720", () -> { Main.config.RESOLUTION = new int[] {1280, 720}; try { enter(gc, sbg); } catch (Exception ignored) {} }),
                                        new Bundle<>("1024x576", () -> { Main.config.RESOLUTION = new int[] {1024, 576}; try { enter(gc, sbg); } catch (Exception ignored) {} }),
                                        new Bundle<>("800x450", () -> { Main.config.RESOLUTION = new int[] {800, 450}; try { enter(gc, sbg); } catch (Exception ignored) {} }),
                                        new Bundle<>("640x360", () -> { Main.config.RESOLUTION = new int[] {640, 360}; try { enter(gc, sbg); } catch (Exception ignored) {} })
                                ).setToggledIndex(Arrays.asList(new int[][] {{1920, 1080}, {1600, 900}, {1280, 720}, {1024, 576}, {800, 450}, {640, 360}}).indexOf(Main.config.RESOLUTION))),
                                new Bundle<>("Framerate (fps)", new ToggleBar(
                                        new Bundle<>("24", () -> Main.config.FRAMES_PER_SECOND = 24),
                                        new Bundle<>("30", () -> Main.config.FRAMES_PER_SECOND = 30),
                                        new Bundle<>("45", () -> Main.config.FRAMES_PER_SECOND = 45),
                                        new Bundle<>("50", () -> Main.config.FRAMES_PER_SECOND = 50),
                                        new Bundle<>("60", () -> Main.config.FRAMES_PER_SECOND = 60),
                                        new Bundle<>("75", () -> Main.config.FRAMES_PER_SECOND = 75),
                                        new Bundle<>("90", () -> Main.config.FRAMES_PER_SECOND = 90),
                                        new Bundle<>("120", () -> Main.config.FRAMES_PER_SECOND = 120),
                                        new Bundle<>("144", () -> Main.config.FRAMES_PER_SECOND = 144),
                                        new Bundle<>("165", () -> Main.config.FRAMES_PER_SECOND = 165),
                                        new Bundle<>("180", () -> Main.config.FRAMES_PER_SECOND = 180),
                                        new Bundle<>("240", () -> Main.config.FRAMES_PER_SECOND = 240),
                                        new Bundle<>("360", () -> Main.config.FRAMES_PER_SECOND = 360),
                                        new Bundle<>("390", () -> Main.config.FRAMES_PER_SECOND = 390),
                                        new Bundle<>("...", () -> Main.config.FRAMES_PER_SECOND = Integer.parseInt(customFPS))
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
                                ).setToggledIndex(Main.config.GSYNC_FREESYNC ? 1 : 0)),
                                new Bundle<>("Maximum Glow Count Per Tile", new ToggleBar(
                                        new Bundle<>("0", () -> Main.config.GLOW_MAX = 0),
                                        new Bundle<>("100", () -> Main.config.GLOW_MAX = 100),
                                        new Bundle<>("200", () -> Main.config.GLOW_MAX = 200),
                                        new Bundle<>("300", () -> Main.config.GLOW_MAX = 300),
                                        new Bundle<>("400", () -> Main.config.GLOW_MAX = 400),
                                        new Bundle<>("500", () -> Main.config.GLOW_MAX = 500)
                                ).setToggledIndex(Main.config.GLOW_MAX/100)),
                                new Bundle<>("Glow Size", new ToggleBar(
                                        new Bundle<>("0.0", () -> Main.config.GLOW_SIZE = 0.00f),
                                        new Bundle<>("0.5", () -> Main.config.GLOW_SIZE = 0.05f),
                                        new Bundle<>("1.0", () -> Main.config.GLOW_SIZE = 0.10f),
                                        new Bundle<>("1.5", () -> Main.config.GLOW_SIZE = 0.15f),
                                        new Bundle<>("2.0", () -> Main.config.GLOW_SIZE = 0.20f),
                                        new Bundle<>("2.5", () -> Main.config.GLOW_SIZE = 0.25f),
                                        new Bundle<>("3.0", () -> Main.config.GLOW_SIZE = 0.30f),
                                        new Bundle<>("3.5", () -> Main.config.GLOW_SIZE = 0.35f),
                                        new Bundle<>("4.0", () -> Main.config.GLOW_SIZE = 0.40f),
                                        new Bundle<>("4.5", () -> Main.config.GLOW_SIZE = 0.45f)
                                ).setToggledIndex((int) (Main.config.GLOW_SIZE/0.05f)))
                        )
                        ).setActive(true),
                new Tab(
                        new Rectangle(50, 100, Main.width() / 7 - 50, 50),
                        "Audio",
                        new TabBody(
                                new Bundle<>("SFX Volume", new ToggleBar(
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
                ).setActive(false),
                new Tab(
                        new Rectangle(50, 150, Main.width() / 7 - 50, 50),
                        "Language",
                        new TabBody(
                                new Bundle<>("Game Language", new ToggleBar(
                                        new Bundle<>("English", () -> {})
                                ))
                        )
                ).setActive(false)
        );
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
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
        Main.UI.render(gc);
    }
}
