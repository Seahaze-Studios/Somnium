package core;

import gamestates.*;
import graphics.ui.UIElement;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.AbstractQueue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Main extends StateBasedGame 
{
	public static StateBasedGame sbg;
	private static AppGameContainer appgc;

	public static final int TEST_ID = 0;
	public static final int LOADING_ID = 1;
    public static final int INTRO_ID  = 2;
	public static final int TITLE_ID = 3;
	public static final int GAME_ID = 4;
	public static final int SETTINGS_ID = 5;
	public static TestState test;
	public static IntroCredit intro;
	public static LoadingScreen loading;
	public static TitleScreen title;
	public static Game game;
	public static boolean debug;
	public static final UI UI = new UI();
	public static TrueTypeFont font;
	public static Fonts fonts;

	public static Configuration config = new Configuration();

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
		//addState(test);
		addState(loading);
		addState(intro);
		addState(title);
		addState(game);

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
}