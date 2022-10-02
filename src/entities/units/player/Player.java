package entities.units.player;

import core.Constants;
import core.Main;
import entities.units.Unit;
import gamestates.Game;
import map.GameMap;
import map.tile.interactable.Ice;
import map.tile.interactable.Interactable;
import map.tile.interactable.Portal;
import map.tile.interactable.hazard.Hazard;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import util.Vector2f;

import java.util.concurrent.atomic.AtomicBoolean;

public class  Player extends Unit {
    public void setRefSprite(Image refSprite) {
        this.refSprite = refSprite;
    }

    //    private SimpleDiagramRenderer spriteSVG;
    private Image refSprite;

    private Color color;

    private boolean immobile;
    private boolean kill;
    private boolean portaled;

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
        this.portaled = false;
    }

    public boolean isImmobile() {
        return immobile;
    }

    public boolean collides(GameMap gm) {
        AtomicBoolean returning = new AtomicBoolean(false);
        gm.getTileList().forEach(t -> {
            if(t instanceof Block && this.getHitbox().intersects(t.getHitbox())) {
                returning.set(true);
            }
        });
        if(pos.getX() + width/2 > Main.getScreenWidth()/2 + Constants.MAP_WIDTH*Constants.TILE_SIZE) {
            returning.set(true);
        }
        else if(pos.getX() - width/2 < Main.getScreenWidth()/2 - Constants.MAP_WIDTH*Constants.TILE_SIZE) {
            returning.set(true);
        }
        else if(pos.getY() + height/2 > Main.getScreenHeight()) {
            returning.set(true);
        }
        else if(pos.getY() - height/2 < 0) {
            returning.set(true);
        }
        return returning.get();
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
    }

    public void move(GameMap gm)    {
        boolean temp  = true;
        for(int i = 0; i < Math.abs(speed.x);i++) {
            move(new Vector2f(speed.x/Math.abs(speed.x),0));
            if(collides(gm) && temp){
                move(new Vector2f(speed.x/Math.abs(speed.x),0).negate());
                temp = false;
            }
        }
        temp = true;
        for(int i = 0; i < Math.abs(speed.y);i++) {
            move(new Vector2f(0, speed.y/Math.abs(speed.y)));
            if(collides(gm) && temp){
                move(new Vector2f(0, speed.y/Math.abs(speed.y)).negate());
                temp = false;
            }
        }
    }

    public void tileSpecialCollisions(GameMap map)    {
        this.speed = new Vector2f(0,0);
        immobile = false;
        map.getTileList().forEach(tile -> {
            if(this.hitbox.intersects(tile.getHitbox())) {
                if (tile instanceof Hazard) {
                    kill = true;
                }
                if (tile instanceof Interactable) {
                    if (tile instanceof Portal && !portaled) {
                        setPos(new Vector2f(((Portal) tile).getPair().getHitbox().getX(), ((Portal) tile).getPair().getHitbox().getY()));
                        portaled = true;
                    }
                    else {
                        portaled = false;
                    }
                    if (tile instanceof Ice) {
                        immobile = true;
                        switch (dir) {
                            case UP -> {
                                this.speed = new Vector2f(0, -Constants.PLAYER_MAX_SPEED* Constants.SCALING_FACTOR());
                            }
                            case DOWN -> {
                                this.speed = new Vector2f(0, Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR());
                            }
                            case LEFT -> {
                                this.speed = new Vector2f(-Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                            }
                            case RIGHT -> {
                                this.speed = new Vector2f(Constants.PLAYER_MAX_SPEED * Constants.SCALING_FACTOR(), 0);
                            }
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
        sprite.setImageColor(color.r, color.g, color.b, 1f);
        super.render();

    }


//    public void setSpriteSVG(SimpleDiagramRenderer svg)  {
//        this.spriteSVG = svg;
//    }
//    public SimpleDiagramRenderer getSpriteSVG() {
//        return spriteSVG;
//    }
    public void color(Color color)  {
        sprite = refSprite;
        if(color != Color.black) this.color = color;
    }
}
