package gamestates;

import core.Constants;
import core.Fonts;
import core.Main;
import gamestates.types.AdvancedGameState;
import graphics.TitleBackground;
import managers.SoundManager;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.*;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.RoundedRectangle;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;
import util.DrawUtilities;
import util.Resource;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.lwjgl.opengl.GL11.GL_RENDERER;

public class LoadingScreen extends AdvancedGameState {
    public static final String RES = System.getProperty("user.dir") + "/res";

    private LoadingList loadingList;
    private String lastResource;
    public static Sound music;
    private Image logo;

    private final int id;

    private int totalTasks;
    private int tasksDone;

    public TitleBackground background;

    public LoadingScreen(int id)
    {
        this.id = id;
    }
    public int getID()
    {
        return id;
    } // Returns the ID code for this game state

    // Initialize LoadingList
    private void initializeLoadingList(File dir, LoadingList loadingList) {
        for (final File file: Objects.requireNonNull(dir.listFiles())) {
            if (file.isDirectory()) {
                initializeLoadingList(file, loadingList);
            } else {
                loadingList.add(new Resource(file));
            }
        }
    }

    // Initializer, first time
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException
    {
        gc.setShowFPS(false);

        this.loadingList = LoadingList.get();
        background = new TitleBackground();
        music = new Sound("res/audio/music/title.wav");
        logo = new Image("res/ui/start/logo.png");
    }

    @Override // Begin file loading upon entering the gamestate
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        //SoundManager.overrideBackgroundMusic(music);

        // Set Loading List to Deferred
        LoadingList.setDeferredLoading(true);

        // Initialize Loading List
        initializeLoadingList(new File(RES), loadingList);

        this.totalTasks = loadingList.getTotalResources();
        this.tasksDone = 0;

        gc.getGraphics().setBackground(Color.black);
    }

    @Override // Update, runs consistently
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException
    {
        background.update();
        // Load a new resource
        if(loadingList.getRemainingResources() > 0) {
            try {
                DeferredResource resource = loadingList.getNext();
                resource.load();
                lastResource = resource.getDescription();
            } catch(IOException e) {
                System.out.println("Failed to load a resource");
            }
        }
        // When loading is completed, automatically move to start menu
        else {
            // TextManager.initialize();
            sbg.enterState(Main.testMode ? Main.TEST_ID : Main.INTRO_ID);
            System.out.println("Leaving Loading");
        }
    }

    @Override // Render, all visuals
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException
    {
        g.setFont(Main.font);
        // Calculate the number of tasks done
        this.tasksDone = loadingList.getTotalResources() - loadingList.getRemainingResources();

        // Draw a Loading Bar
        final float BAR_WIDTH = gc.getWidth() - 0.25f * gc.getWidth();
        final float BAR_HEIGHT = 0.0726f * gc.getHeight();

        final float BAR_X = gc.getWidth() / 2 - BAR_WIDTH / 2;
        final float BAR_Y = gc.getHeight() / 2 - BAR_HEIGHT / 2;

        final float PERCENT_LOADED = (float) tasksDone / (float) totalTasks;

       // background.renderPre(g, PERCENT_LOADED);

        //DrawUtilities.drawImageCentered(g, logo, Main.getScreenWidth() / 2, Main.getScreenHeight() / 3);
        //DrawUtilities.drawStringCentered(g, "Version " + Constants.VERSION, Main.getScreenWidth() / 2, Main.getScreenHeight() / 3 - 200);
        g.setBackground(Color.black);
        //g.setBackground(new Color((int) (167 * PERCENT_LOADED), (int) (231 * PERCENT_LOADED), (int) (255 * PERCENT_LOADED)));

        // max loading bar
        g.setColor(new Color(0, 0, 0));
        g.fill(new RoundedRectangle(BAR_X, BAR_Y, BAR_WIDTH, BAR_HEIGHT, RoundedRectangle.ALL));

        // current loaded
        g.setColor(new Color(255, 255, 255));
        g.fill(new RoundedRectangle(BAR_X, BAR_Y, BAR_WIDTH * PERCENT_LOADED, BAR_HEIGHT, RoundedRectangle.ALL));

        // white outline
        g.setColor(new Color(255, 255, 255));
        g.draw(new RoundedRectangle(BAR_X, BAR_Y, BAR_WIDTH, BAR_HEIGHT, RoundedRectangle.ALL));

        DrawUtilities.drawStringCentered(g, "Loading resource: " + lastResource + "...", Main.getScreenWidth() / 2, BAR_Y + BAR_HEIGHT + 25f);
        super.render(gc, sbg, g);
    }
}
