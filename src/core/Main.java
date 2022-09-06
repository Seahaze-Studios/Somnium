package core;

import gamestates.Game;
import gamestates.IntroCredit;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame 
{
	public static StateBasedGame sbg;
	private static AppGameContainer appgc;
	
    public static final int INTRO_ID  = 0;
	public static final int GAME_ID = 1;
	public static IntroCredit intro;
	public static Game game;
	
	public static final UI UI = new UI();
	public static TrueTypeFont font;
	public static Fonts fonts;

	public static Configuration config = new Configuration();

    
	public Main(String name) 
	{
		super(name);

		intro = new IntroCredit(INTRO_ID);
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
		addState(intro);
		addState(game);
	}

	public static void main(String[] args) 
	{
		try 
		{
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