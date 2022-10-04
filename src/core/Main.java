package core;

import gamestates.*;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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

	public static int width() {
		return config.RESOLUTION_X;
	}

	public static int height() {
		return config.RESOLUTION_Y;
	}


    
	public Main(String name) 
	{
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

	public static int getScreenWidth()
	{
		return appgc.getScreenWidth();
	}
	
	public static int getScreenHeight()
	{
		return appgc.getScreenHeight();
	}
	

	public void initStatesList(GameContainer gc) throws SlickException 
	{
		addState(loading);
		addState(test);
		addState(intro);
		addState(title);
		addState(game);
		addState(settings);
		addState(levelSelect);
		addState(quit);
	}

	public static void main(String[] args) 
	{
		try {
			GraphicsEnvironment ge =
					GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/font/Aileron-Light.otf")));
		} catch (IOException | FontFormatException e) {
			//Handle exception
		}

		try {
			highestLevel = Integer.parseInt(new String(Files.readAllBytes(Paths.get("saves/level.txt"))));
		} catch (IOException | NumberFormatException e) {
			highestLevel = 1;
		}

//		Constants.COLOR_L.forEach(c -> {
//			Constants.COLOR_R.add(new org.newdawn.slick.Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue()));
//		});


		try 
		{
			debug = false;
			appgc = new AppGameContainer(new Main("Somnium"));
			config.init(appgc);
			System.setProperty("org.lwjgl.opengl.Window.undecorated", "true");
		
			appgc.setDisplayMode(config.RESOLUTION_X, config.RESOLUTION_Y, false);
			appgc.setTargetFrameRate(config.FRAMES_PER_SECOND);
			appgc.setVSync(config.VSYNC);
			appgc.start();

		} 
		catch (SlickException e) 
		{
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