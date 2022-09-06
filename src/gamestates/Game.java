package gamestates;

import core.Main;
import entities.Entity;
import entities.units.player.Player;
import gamestates.types.AdvancedGameState;
import managers.KeyManager;
import managers.SoundManager;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.particles.Particle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class Game extends AdvancedGameState {
    private final int id;
    private static GameContainer gc;

    private Entity entity;
    // Managers
    private KeyManager keyDown; // Key Manager
    public static Sound music;
    public static final Queue<Entity> entities = new ConcurrentLinkedQueue<>();
    public static final Queue<Particle> particles = new ConcurrentLinkedQueue<>();

    public void keyInput() { KeyManager.KEY_DOWN_LIST.stream().filter(keyDown).forEach(keyDown::keyDown); }

    public Game(int id) {
        this.id = id;
    }


    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        // This code happens when you enter a game state for the *first time.*
        gc.setShowFPS(true);
        this.gc = gc;
        // Initialize Managers
        keyDown = new KeyManager(gc.getInput(), this);
        System.out.println("[VERBOSE] KeyManager initialized");
        music = new Sound("res/audio/music/title.wav");
    }

    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        entities.forEach(Entity::render);
        g.drawLine(Main.width() / 2, 0, Main.width() / 2, Main.height());
    }

    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        keyInput();

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

    public Player getPlayer() {
        return null;
    }
}
