package core;

import gamestates.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;
import util.Vector2f;
import util.bundle.Bundle;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main extends StateBasedGame 
{
	public static boolean testMode = false;

	public static StateBasedGame sbg;
	private static AppGameContainer appgc;

	public static final int TEST_ID = 1;
	public static final int LOADING_ID = 0;
    public static final int INTRO_ID  = 2;
	public static final int TITLE_ID = 3;
	public static final int GAME_ID = 4;
	public static final int SETTINGS_ID = 5;
	public static final int LEVELSELECT_ID = 6;
	public static final int QUIT_ID = 999;
	public static TestState test;
	public static IntroCredit intro;
	public static LoadingScreen loading;
	public static TitleScreen title;
	public static Game game;
	public static Settings settings;
	public static LevelSelectScreen levelSelect;
	public static QuitState quit;
	public static boolean debug;
	public static final UI UI = new UI();
	public static TrueTypeFont font;
	public static Fonts fonts;

	private static int realFPS;

	public static Configuration config = new Configuration();

	public static int highestLevel = 1;

	public static List<Bundle<String, Vector2f>> hints = new ArrayList<>() {{
		add(new Bundle<>("""
							 Use WASD to move.
							 All your movements on the left
							 will be mirrored on the right.""", new Vector2f(400, 800)));
		add(new Bundle<>("""
							 Sometimes, you might need to back up
							 one side into a wall in order to
							 independently move another.""", new Vector2f(700, 900)));
		add(new Bundle<>("""
							 """, new Vector2f(400, 400)));
		add(new Bundle<>("""
							 [Ice] is very slippery.
							 Upon contact, you will instantly slide
							 in the direction of approach until you
							 are off of the ice.""", new Vector2f(400, 600)));
		add(new Bundle<>("""
							 [Lava] will instantly kill you upon contact.
							 [Wind] will always push you in a fixed direction.
							 Pushing the button to move in the opposite direction
							 will freeze you in place, while the other two directions
							 are free for you to move in.
							 Get into tight 1-block spaces with this technique.""", new Vector2f(400, 520)));
		add(new Bundle<>("""
							 Mysterious portals...
							 [Portals] come in pairs and will teleport
							 you to the opposing portal upon contact.""", new Vector2f(700, 700)));
	}};

	public static int width() {
		return config.RESOLUTION[0];
	}

	public static int height() {
		return config.RESOLUTION[1];
	}


    
	public Main(String name) {
		super(name);
		test = new TestState(TEST_ID);
		loading = new LoadingScreen(LOADING_ID);
		intro = new IntroCredit(INTRO_ID);
		title = new TitleScreen(TITLE_ID);
		game = new Game(GAME_ID);
		settings = new Settings(SETTINGS_ID);
		levelSelect = new LevelSelectScreen(LEVELSELECT_ID);
		quit = new QuitState(QUIT_ID);
	}

	public void initStatesList(GameContainer gc) throws SlickException {
		addState(loading);
		addState(test);
		addState(intro);
		addState(title);
		addState(game);
		addState(settings);
		addState(levelSelect);
		addState(quit);
	}

	public static void main(String[] args) {
		try {
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/Aileron-Light.otf")));
		} catch (IOException | FontFormatException ignored) {}

		try {
			highestLevel = Integer.parseInt(new String(Files.readAllBytes(Paths.get("saves/level.txt"))));
		} catch (IOException | NumberFormatException e) { highestLevel = 1; }

		Constants.COLOR_L.forEach(c -> Constants.COLOR_R.add(new org.newdawn.slick.Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue())));

		try {
			debug = false;
			appgc = new AppGameContainer(new Main("Somnium"));
			config.init(appgc);
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(config.RESOLUTION[0], config.RESOLUTION[1], false);
			appgc.setTargetFrameRate(config.FRAMES_PER_SECOND);
			appgc.setVSync(config.VSYNC);
			appgc.start();
		} 
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

	public static AppGameContainer getAppgc() {
		return appgc;
	}

	public static int getRealFPS() {
		return realFPS;
	}

	public static void setRealFPS(int fps) {
		realFPS = fps;
	}
}