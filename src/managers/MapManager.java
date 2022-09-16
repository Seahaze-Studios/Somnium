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
        switch(id)  {
            case Constants.LEVEL_1_ID -> {
                loadMaps(new GameMap("res/maps/lvl"+id+"left.tmx"), new GameMap("res/maps/lvl"+id+"right.tmx"));
            }
        }

    }

    public void loadMaps(GameMap m1, GameMap m2)    {
        mapL = m1;
        mapR = m2;
        generateHitboxes(mapL);
        generateHitboxes(mapR);
        for(Tile t:mapR.getTileList())  {
            t.getHitbox().setX(t.getHitbox().getX() + Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth()));
        }
        mapR.plrPos.add(new Vector2f(Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth()),0));
    }
    private void generateHitboxes(GameMap map) {
        for(int i = 0; i < Constants.MAP_WIDTH; i++)    {
            for(int j = 0; j < Constants.MAP_HEIGHT; j++)    {
                generateTile(i,j,map);
            }
        }
    }

    private void generateTile(int i, int j, GameMap map) {
        if(map.getTileId(i,j,0) != 0)   {
            switch(map.getTileProperty(map.getTileId(i,j,0),"type","block"))    {
                case "block" ->     {
                    map.getTileList().add(new Block(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
                }
                case "player" ->    {
                    map.plrPos = new Vector2f((i + 0.5f) * Constants.TILE_SIZE, (j + 0.5f) * Constants.TILE_SIZE);
                }
                case "goal" -> {
                    map.getTileList().add(new Goal(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
                }
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
        mapL.render(0,0);
        mapR.render(Main.getScreenWidth()-(Constants.TILE_SIZE*mapR.getWidth()), 0);
        colorMap(mapL, g);
        colorMap(mapR, g);
        if(win()) levelChange();
    }

    public void debugRender(Graphics g) {
        for(Tile t: mapL.getTileList()) {
            if(t instanceof Goal)   {
                Color temp = g.getColor();
                g.setColor(new Color(0,255,255,0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            }
            else {
                g.fill(t.getHitbox());
            }

        }
        for(Tile t: mapR.getTileList()) {
            if(t instanceof Goal)   {
                Color temp = g.getColor();
                g.setColor(new Color(0,255,255,0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            }
            else{
                g.fill(t.getHitbox());
            }

        }
    }

    public void colorMap(GameMap gm, Graphics g)  {
        g.setColor(new Color(gm.getColor().getRed(), gm.getColor().getBlue(),gm.getColor().getGreen(),0.5f));
        gm.getTileList().forEach(t ->{
            g.fill(t.getHitbox());
        });
    }

    private void levelChange() throws SlickException {
        Game.curLevelID++;
        loadStage(Game.curLevelID);
    }
}
