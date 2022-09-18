package managers;

import core.Constants;
import core.Main;
import gamestates.Game;
import map.GameMap;
import map.tile.Tile;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import util.DrawUtilities;
import util.Vector2f;

import java.util.ArrayList;

public class MapManager {
    public static GameMap mapL;
    public static GameMap mapR;



    public MapManager(GameMap m1, GameMap m2)   {
        loadMaps(m1, m2);

    }

    public MapManager(int id) throws SlickException {
        loadStage(id);
    }

    public void loadStage(int id) throws SlickException {
        try {
            if(id <= Constants.COLOR_L.size()) {
                loadMaps(new GameMap("res/maps/lvl" + id + "left.tmx", Constants.COLOR_L.get(id - 1)),
                        new GameMap("res/maps/lvl" + id + "right.tmx", Constants.COLOR_R.get(id - 1)));
                Game.getPlayerL().color(Constants.COLOR_R.get(id - 1));
                Game.getPlayerL().setPos(mapL.plrPos);
                Game.getPlayerL().setHitbox(mapL.plrPos.x - Game.getPlayerL().getHitbox().getWidth() / 2, mapL.plrPos.y - Game.getPlayerL().getHitbox().getWidth() / 2);
                Game.getPlayerR().setPos(mapR.plrPos);
                Game.getPlayerR().color(Constants.COLOR_L.get(id-1));
                Game.getPlayerR().setHitbox(mapR.plrPos.x - Game.getPlayerR().getHitbox().getWidth() / 2, mapR.plrPos.y - Game.getPlayerR().getHitbox().getWidth() / 2);
            }
        } catch (Exception e) {

        }
    }

    public void loadMaps(GameMap m1, GameMap m2)    {
        mapL = m1;
        mapR = m2;
        generateHitboxes(mapL);
        generateHitboxes(mapR);
        mapR.getTileList().forEach(t -> t.getHitbox().setX(t.getHitbox().getX() + Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth())));
        mapR.plrPos.add(new Vector2f(Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth()),0));
    }
    private void generateHitboxes(GameMap map) {
        for (int i = 0; i < Constants.MAP_WIDTH; i++)    {
            for (int j = 0; j < Constants.MAP_HEIGHT; j++)    {
                generateTile(i,j,map);
            }
        }
    }

    private void generateTile(int i, int j, GameMap map) {
        if(map.getTileId(i,j,0) != 0)   {
            switch(map.getTileProperty(map.getTileId(i,j,0),"type","block"))    {
                case "block" -> map.getTileList().add(new Block(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
                case "player" -> map.plrPos = new Vector2f((i + 0.5f) * Constants.TILE_SIZE, (j + 0.5f) * Constants.TILE_SIZE);
                case "goal" -> map.getTileList().add(new Goal(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
            }
            /*if(map.getTileProperty(map.getTileId(i,j, 0), "type", "false").equals("block"))  {
                map.getTileList().add(new Block(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
            }*/

        }
    }


    public boolean win()    {
        return Game.getPlayerL().enterGoal(mapL) && Game.getPlayerR().enterGoal(mapR);
    }

    public void render(Graphics g) throws SlickException {
        g.setColor(mapR.getColor());
        g.fill(new Rectangle(0,0,Main.getScreenWidth()/2,Main.getScreenHeight()));
        g.setColor(mapL.getColor());
        g.fill(new Rectangle(Main.getScreenWidth()/2,0, Main.getScreenWidth(),Main.getScreenHeight()));
        mapL.render(0,0, g);
        mapR.render(Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth()), 0, g);
//        colorMap(mapL, g);
//        colorMap(mapR, g);
        if(win()) levelChange();
    }

    public void debugRender(Graphics g) {
        for (Tile tile : mapL.getTileList())
            if (tile instanceof Goal) {
                Color temp = g.getColor();
                g.setColor(new Color(0, 255, 255, 0.5f));
                g.fill(tile.getHitbox());
                g.setColor(temp);
            } else {
                g.fill(tile.getHitbox());
            }
        for (Tile t : mapR.getTileList())
            if (t instanceof Goal) {
                Color temp = g.getColor();
                g.setColor(new Color(0, 255, 255, 0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            } else {
                g.fill(t.getHitbox());
            }
    }

    public void colorMap(GameMap gm, Graphics g)  {
        g.setColor(new Color(gm.getColor().getRed(), gm.getColor().getBlue(),gm.getColor().getGreen(),0.95f));
        gm.getTileList().forEach(t -> {if(!(t instanceof Goal)  )g.fill(t.getHitbox());});
    }

    private void levelChange() throws SlickException {
        Game.curLevelID++;
        loadStage(Game.curLevelID);
    }
}
