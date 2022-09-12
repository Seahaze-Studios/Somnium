package gamestates;

import core.Constants;
import core.Main;
import entities.Entity;
import entities.units.player.Player;
import gamestates.types.AdvancedGameState;
import managers.KeyManager;
import managers.MapManager;
import managers.SoundManager;
import org.newdawn.slick.*;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;
import util.DrawUtilities;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import static core.Main.debug;

public class Game extends AdvancedGameState {
    private final int id;
    private static GameContainer gc;



    private static MapManager mapMan;

    private Entity entity;
    // Managers
    private KeyManager keyDown; // Key Manager
    public static Sound music;
    public static final Queue<Entity> entities = new ConcurrentLinkedQueue<>();
    public static final Queue<Particle> particles = new ConcurrentLinkedQueue<>();

    public Player getPlayerLeft() {
        return plrL;
    }

    public Player getPlayerRight() {
        return plrR;
    }
    public static MapManager getMapMan() {
        return mapMan;
    }

    private Player plrL;
    private Player plrR;

    public void keyInput() { KeyManager.KEY_DOWN_LIST.stream().filter(keyDown).forEach(keyDown::keyDown); }

    public Game(int id) {
        this.id = id;
    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(debug);
        this.gc = gc;
        plrL = new Player();
        plrR = new Player();
        mapMan = new MapManager(Constants.LEVEL_1_ID);
        // Initialize Managers
        keyDown = new KeyManager(gc.getInput(), this);
        System.out.println("[VERBOSE] KeyManager initialized");
        music = new Sound("res/audio/music/title.wav");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        entities.forEach(Entity::render);
        plrL.render();
        plrR.render();
        //g.drawLine(Main.width() / 2, 0, Main.width() / 2, Main.height());
        mapMan.render(g);
        if(debug) {
            debugRender(gc);
        }
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        keyInput();
        plrL.update();
        plrR.update();
        // This is where you put your game's logic that executes each frame that isn't about drawing
        entities.forEach(Entity::update);
    }

    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        SoundManager.overrideBackgroundMusic(music);
        // This code happens when you enter a gameState.
        //entity.setPos(400, 400);
        gc.getGraphics().setBackground(org.newdawn.slick.Color.black);
    }

    public void leave(GameContainer gc, StateBasedGame sbg) {
        // This code happens when you leave a gameState.
    }


    public void keyPressed(int key, char c) {
        super.keyPressed(key, c);
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
        gc.getGraphics().setColor(new Color(255,255,0,0.5f));
        mapMan.debugRender(gc.getGraphics());
        gc.getGraphics().fill(plrL.getHitbox());
        gc.getGraphics().fill(plrR.getHitbox());
        gc.getGraphics().setColor(Color.yellow);
        DrawUtilities.drawStringCentered(gc.getGraphics(), "DEBUG MODE", 1000, 100);
        gc.getGraphics().drawString( "PLR_R @ " + plrR.getPos().x + ", " + plrR.getPos().y, 0,100);
        gc.getGraphics().drawString("PLR_L @ " + plrL.getPos().x + ", " + plrL.getPos().y, 0,130);
        gc.getGraphics().drawString("hitboxes (" + plrL.getHitbox().getX() + ", " + plrL.getHitbox().getY() + ")", 0,50);
    }

    public Player getPlayer() {
        return null;
    }
}
