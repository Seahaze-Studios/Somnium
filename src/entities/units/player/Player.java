package entities.units.player;

import core.Constants;
import core.Main;
import entities.units.Unit;
import map.GameMap;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;
import util.Vector2f;

import java.util.concurrent.atomic.AtomicBoolean;

public class Player extends Unit {
    public void setRefSprite(Image refSprite) {
        this.refSprite = refSprite;
    }

    //    private SimpleDiagramRenderer spriteSVG;
    private Image refSprite;

    private Color color;


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
        width = Constants.TILE_SIZE;
        height = Constants.TILE_SIZE;
        this.refSprite = new Image("res/dev/placeholder.png").getScaledCopy(54,54);
        this.sprite = refSprite;
        this.color = new Color(Color.gray);
    }

    public boolean collides(GameMap gm) {
        AtomicBoolean returning = new AtomicBoolean(false);
        gm.getTileList().forEach(t -> {
            if(t instanceof Block && this.getHitbox().intersects(t.getHitbox())) {
                returning.set(true);
            }
        });
        if(pos.getX() + width/2 > Main.getScreenWidth()) {
            returning.set(true);
        }
        else if(pos.getX() - width/2 < 0) {
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
