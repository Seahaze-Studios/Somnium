package entities.units.player;

import core.Constants;
import core.Main;
import entities.units.Unit;
import gamestates.Game;
import managers.MapManager;
import managers.SoundManager;
import map.GameMap;
import map.tile.Tile;
import map.tile.interactable.Ice;
import map.tile.interactable.Interactable;
import map.tile.interactable.Portal;
import map.tile.interactable.Wind;
import map.tile.interactable.hazard.Hazard;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import util.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class  Player extends Unit {
    public void setRefSprite(Image refSprite) {
        this.refSprite = refSprite;
    }

    private Image refSprite;

    private Color color;

    private boolean immobile;
    private boolean kill;
    private Shape lastPortal;

    public Player(int x, int y) throws SlickException {
        super(x,y);
        init();
    }

    public Player() throws SlickException {
        super();
        init();
    }

    public Player(Vector2f pos) throws SlickException {
        super(pos);
        init();
    }

    public void init() throws SlickException {
        super.init();
        width = Constants.TILE_SIZE - 2;
        height = Constants.TILE_SIZE - 2;
        this.refSprite = new Image("res/dev/placeholder.png").getScaledCopy(54,54);
        this.sprite = refSprite;
        this.color = new Color(Color.gray);
        this.immobile = false;
        this.kill = false;
    }

    public boolean isImmobile() {
        return immobile;
    }

    public boolean collides(GameMap gm, Vector2f pos) {
        boolean ret = false;
        if (!gm.getTileList().stream().filter(t -> t instanceof Block && new Rectangle(pos.x - width / 2, pos.y - height / 2, width, height).intersects(t.getHitbox())).toList().isEmpty()) ret = true;
        if(pos.getX() + width/2 > Main.width()/2 + Constants.MAP_WIDTH*Constants.TILE_SIZE ||
                pos.getX() - width/2 < Main.width()/2 - Constants.MAP_WIDTH*Constants.TILE_SIZE ||
                pos.getY() + height/2 > Main.height() ||
                pos.getY() - height/2 < 0) {
            ret = true;
        }
        return ret;
    }

    public boolean enterGoal(GameMap gm)  {
        AtomicBoolean returning = new AtomicBoolean(false);
        gm.getTileList().forEach(t -> {
            if (t instanceof Goal && this.getHitbox().intersects(t.getHitbox())) {
                returning.set(true);
            }
        });
        return returning.get();
    }

    public void update(GameMap gm)  {
        move(gm);
        Game.entities.forEach(e -> {
            if (e.getHitbox().intersects(this.hitbox)) collide(e);
        });
        tileSpecialCollisions(gm);
    }

    public void move(GameMap gm)    {
        move(gm, speed, 0);
    }

    public void move(GameMap gm, Vector2f disp, int n)    {
        if (immobile) return;
        if (!(collides(gm, pos.copy().add(disp)))) move(disp);
        else if (n < 20) move(gm, disp.scale(0.95f), n++);
    }

    public void accelerate(GameMap gm, Vector2f dv) {
        if (this.speed.length() < Constants.PLAYER_MAX_SPEED) speed.add(dv);
    }

    public void tileSpecialCollisions(GameMap map)    {
        this.speed = new Vector2f(0,0);
        immobile = false;
        if (lastPortal != null && !lastPortal.intersects(this.hitbox)) lastPortal = null;
        map.getTileList().forEach(tile -> {
            if(hitbox.intersects(tile.getHitbox())) {
                if (tile instanceof Hazard) {
                    kill = true;
                }
                if (tile instanceof Interactable) {
                    if (tile instanceof Portal portal) {
                        if (lastPortal == null || !lastPortal.intersects(this.hitbox)) {
                            SoundManager.playSoundEffect("portal");
                            lastPortal = portal.getPair().getHitbox();
                            setPos(new Vector2f((portal).getPair().getHitbox().getX() + (width / 2) + 1, (portal).getPair().getHitbox().getY() + (height / 2)+1));
                            setHitbox(pos.x - width / 2, pos.y - height / 2);
                        }
                    }
                    if (tile instanceof Ice) {
                        immobile = true;
                        switch (dir) {
                            case UP -> speed = new Vector2f(0, -Constants.PLAYER_MAX_SPEED* Constants.SCALING_FACTOR());
                            case DOWN -> speed = new Vector2f(0, Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR());
                            case LEFT -> speed = new Vector2f(-Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                            case RIGHT -> speed = new Vector2f(Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                        }
                    }
                    if (tile instanceof Wind wind) {
                        switch (wind.getDirection()) {
                            case UP -> speed = new Vector2f(0, -Constants.PLAYER_MAX_SPEED* Constants.SCALING_FACTOR());
                            case DOWN -> speed = new Vector2f(0, Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR());
                            case LEFT -> speed = new Vector2f(-Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                            case RIGHT -> speed = new Vector2f(Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                        }
                    }
                }
            }
        });
    }

    public boolean dead()   {
         return kill;
    }
    public void revive()    {
        kill = false;
    }

    public void render()    {
        if (kill) return;
        sprite.setImageColor(color.r, color.g, color.b, 1f);
        super.render();

    }

    public void color(Color color)  {
        sprite = refSprite;
        if(color != Color.black) this.color = color;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }

    public void setImmobile(boolean immobile) {
        this.immobile = immobile;
    }
}
