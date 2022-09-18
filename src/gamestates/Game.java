package gamestates;

import core.Constants;
import core.Main;
import entities.Entity;
import entities.units.player.Player;
import gamestates.types.AdvancedGameState;
import managers.ImageManager;
import managers.KeyManager;
import managers.MapManager;
import managers.SoundManager;
import org.newdawn.slick.*;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;
import util.DrawUtilities;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static core.Main.debug;

public class Game extends AdvancedGameState {
    private final int id;
    private static GameContainer gc;

    private static StateBasedGame sbg;

    public static StateBasedGame getSbg()  {
        return sbg;
    }

    private static MapManager mapMan;

    public static int curLevelID;

    private Entity entity;
    // Managers
    private KeyManager keyDown; // Key Manager
    public static final Queue<Entity> entities = new ConcurrentLinkedQueue<>();
    public static final Queue<Particle> particles = new ConcurrentLinkedQueue<>();

    public static MapManager getMapMan() {
        return mapMan;
    }

    private static Player plrL;
    private static Player plrR;

    public void keyInput() { KeyManager.KEY_DOWN_LIST.stream().filter(keyDown).forEach(keyDown::keyDown); }

    public Game(int id) {
        this.id = id;
    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(debug);
        this.gc = gc;
        this.sbg = sbg;

        curLevelID = Constants.LEVEL_1_ID;

        mapMan = new MapManager(curLevelID);
        System.out.println("[VERBOSE] MapManager initialized");
        plrL = new Player(MapManager.mapL.plrPos);
        plrR = new Player(MapManager.mapR.plrPos);
        plrR.color(Color.black);
//        plrL.setSpriteSVG(Constants.SVG_L);
//        plrR.setSpriteSVG(Constants.SVG_R);

        // Initialize Managers
        keyDown = new KeyManager(gc.getInput(), this);
        System.out.println("[VERBOSE] KeyManager initialized");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        super.render(gc, sbg, g);
        entities.forEach(Entity::render);

        //g.drawLine(Main.width() / 2, 0, Main.width() / 2, Main.height());
        mapMan.render(g);
        g.setColor(Color.white);
        g.drawLine(Main.width() / 2, 0, Main.width() / 2, Main.height());
//        plrL.getSpriteSVG().render(g);
//        plrR.getSpriteSVG().render(g);
        plrL.render();
        plrR.render();
        if(debug) {
            debugRender(gc);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        super.update(gc, sbg, delta);
        keyInput();
        plrL.update();
        plrR.update();
        // This is where you put your game's logic that executes each frame that isn't about drawing
        entities.forEach(Entity::update);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a gameState.
        //entity.setPos(400, 400);
        super.enter(gc, sbg);
        gc.getGraphics().setBackground(org.newdawn.slick.Color.black);
        plrL.setSprite(ImageManager.getImage("1a").getScaledCopy(54, 54));
        plrR.setSprite(ImageManager.getImage("2").getScaledCopy(54, 54));
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        // This code happens when you leave a gameState.
    }


    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
        if (key == Input.KEY_ESCAPE) sbg.enterState(Main.TITLE_ID, new FadeOutTransition(), new FadeInTransition());
    }

    public void mousePressed(int button, int x, int y) {

    }


    // Returns the ID code for this game state
    public int getID() {
        return id;
    }

    public static GameContainer getGc() {
        return gc;
    }

    public void debugRender(GameContainer gc)   {
        var g = gc.getGraphics();
        g.setColor(new Color(255,255,0,0.5f));
        mapMan.debugRender(gc.getGraphics());
        g.fill(plrL.getHitbox());
        g.fill(plrR.getHitbox());
        g.setColor(Color.yellow);
        DrawUtilities.drawStringCentered(gc.getGraphics(), "DEBUG MODE", 1000, 100);
        g.drawString( "PLR_R @ " + plrR.getPos().x + ", " + plrR.getPos().y, 0,100);
        g.drawString("PLR_L @ " + plrL.getPos().x + ", " + plrL.getPos().y, 0,130);
        g.drawString("hitboxes (" + plrL.getHitbox().getX() + ", " + plrL.getHitbox().getY() + ")", 0,50);
    }

    public static Player getPlayerL() {
        return plrL;
    }
    public static Player getPlayerR(){return plrR;}
}
