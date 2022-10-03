package managers;

import core.Constants;
import core.Main;
import gamestates.Game;
import graphics.particle.effect.GlowBlackEffect;
import graphics.particle.effect.GlowEffect;
import map.GameMap;
import map.tile.Tile;
import map.tile.interactable.Ice;
import map.tile.interactable.Portal;
import map.tile.interactable.hazard.Hazard;
import map.tile.interactable.hazard.Lava;
import map.tile.interactable.utility.Goal;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import util.DrawUtilities;
import util.Vector2f;

import java.util.ArrayList;
import java.util.Random;

public class MapManager {
    public static GameMap mapL;
    public static GameMap mapR;
    private Random R = new Random();

    private int deathAnimCounter = -1;
    private int levelChangeCounter = -1;
    private boolean levelChange = false;

    private int portalTemp1 = -1;
    private int portalTemp2 = -1;
    private int portalTemp3 = -1;




    public MapManager(GameMap m1, GameMap m2)   {
        loadMaps(m1, m2);

    }

    public MapManager(int id) throws SlickException {
        loadStage(id);
        deathAnimCounter = -1;
        levelChangeCounter = -1;
    }

    public void loadStage(int id) throws SlickException {
        try {
            if (id < Constants.COLOR_L.size()) {
                loadMaps(new GameMap("res/maps/lvl" + id + "left.tmx", Constants.COLOR_L.get(id)),
                        new GameMap("res/maps/lvl" + id + "right.tmx", Constants.COLOR_R.get(id)));
                Game.getPlayerL().color(Constants.COLOR_L.get(id));
                Game.getPlayerL().setPos(mapL.plrPos);
                Game.getPlayerL().setHitbox(mapL.plrPos.x - Game.getPlayerL().getHitbox().getWidth() / 2, mapL.plrPos.y - Game.getPlayerL().getHitbox().getWidth() / 2);
                Game.getPlayerR().setPos(mapR.plrPos);
                Game.getPlayerR().color(Constants.COLOR_R.get(id));
                Game.getPlayerR().setHitbox(mapR.plrPos.x - Game.getPlayerR().getHitbox().getWidth() / 2, mapR.plrPos.y - Game.getPlayerR().getHitbox().getWidth() / 2);

                Game.getPlayerL().setPortals(mapL.getTileList().stream().filter(Portal.class::isInstance).toList());
                Game.getPlayerR().setPortals(mapR.getTileList().stream().filter(Portal.class::isInstance).toList());

           }else {
               Game.curLevelID = 1;
                var idd = 1;
                loadMaps(new GameMap("res/maps/lvl" + idd + "left.tmx", Constants.COLOR_L.get(idd)),
                        new GameMap("res/maps/lvl" + idd + "right.tmx", Constants.COLOR_R.get(idd)));
                Game.getPlayerL().color(Constants.COLOR_L.get(idd));
                Game.getPlayerL().setPos(mapL.plrPos);
                Game.getPlayerL().setHitbox(mapL.plrPos.x - Game.getPlayerL().getHitbox().getWidth() / 2, mapL.plrPos.y - Game.getPlayerL().getHitbox().getWidth() / 2);
                Game.getPlayerR().setPos(mapR.plrPos);
                Game.getPlayerR().color(Constants.COLOR_R.get(idd));
                Game.getPlayerR().setHitbox(mapR.plrPos.x - Game.getPlayerR().getHitbox().getWidth() / 2, mapR.plrPos.y - Game.getPlayerR().getHitbox().getWidth() / 2);
                Game.getPlayerL().setPortals(mapL.getTileList().stream().filter(Portal.class::isInstance).toList());
                Game.getPlayerR().setPortals(mapR.getTileList().stream().filter(Portal.class::isInstance).toList());
                Game.getSbg().enterState(Main.TITLE_ID);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadMaps(GameMap m1, GameMap m2)    {
        mapL = m1;
        mapR = m2;
        generateHitboxes(mapL);
        generateHitboxes(mapR);
        mapL.getTileList().forEach(t -> t.getHitbox().setX(t.getHitbox().getX() + Main.getScreenWidth()/2-(Constants.TILE_SIZE*mapR.getWidth())));
        mapR.getTileList().forEach(t -> t.getHitbox().setX(t.getHitbox().getX() + Main.getScreenWidth()/2));
        mapL.plrPos.add(new Vector2f((Main.getScreenWidth()/2)-(Constants.TILE_SIZE*mapR.getWidth()),0));
        mapR.plrPos.add(new Vector2f(Main.getScreenWidth()/2,0));

    }
    private void generateHitboxes(GameMap map) {
        portalTemp1 = -1;
        portalTemp2 = -1;
        portalTemp3 = -1;
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
                case "lava" ->  map.getTileList().add(new Lava(i*Constants.TILE_SIZE,j * Constants.TILE_SIZE));
                case "ice" -> map.getTileList().add(new Ice(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
                case "portal" -> {
                    map.getTileList().add(new Portal(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
                    switch(map.getTileProperty(map.getTileId(i,j,0),"id", "0"))   {
                        case "0" -> {
                            if(portalTemp1 < 0) {
                                portalTemp1 = map.getTileList().size()-1;
                            }
                            else  {
                                ((Portal)map.getTileList().get(portalTemp1)).setPair((Portal)map.getTileList().get(map.getTileList().size()-1));
                                ((Portal)map.getTileList().get(map.getTileList().size()-1)).setPair((Portal)map.getTileList().get(portalTemp1));
                            }
                        }
                        case "1" ->  {
                            if(portalTemp2 < 0)   {
                                portalTemp2 = map.getTileList().size()-1;
                            }
                            else  {
                                ((Portal)map.getTileList().get(portalTemp2)).setPair((Portal)map.getTileList().get(map.getTileList().size()-1));
                                ((Portal)map.getTileList().get(map.getTileList().size()-1)).setPair((Portal)map.getTileList().get(portalTemp2));
                            }
                        }
                        case "2" ->  {
                            if(portalTemp3 < 0)   {
                                portalTemp3 = map.getTileList().size()-1;
                            }
                            else  {
                                ((Portal)map.getTileList().get(portalTemp3)).setPair((Portal)map.getTileList().get(map.getTileList().size()-1));
                                ((Portal)map.getTileList().get(map.getTileList().size()-1)).setPair((Portal)map.getTileList().get(portalTemp3));
                            }
                        }
                    }

                }
            }
            /*if(map.getTileProperty(map.getTileId(i,j, 0), "type", "false").equals("block"))  {
                map.getTileList().add(new Block(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
            }*/

        }
    }


    public boolean win()    {
        if (Game.getPlayerL().enterGoal(mapL)) {
            var prevColor = Main.sbg.getContainer().getGraphics().getColor();
            Main.sbg.getContainer().getGraphics().setColor(new Color(0, 0, 0, 100));
            Main.sbg.getContainer().getGraphics().fill(new Rectangle(0, 0, Main.width() / 2, Main.height()));
            Main.sbg.getContainer().getGraphics().setColor(prevColor);
        }
        if (Game.getPlayerR().enterGoal(mapR)) {
            var prevColor = Main.sbg.getContainer().getGraphics().getColor();
            Main.sbg.getContainer().getGraphics().setColor(new Color(0, 0, 0, 100));
            Main.sbg.getContainer().getGraphics().fill(new Rectangle(Main.width() / 2, 0, Main.width(), Main.height()));
            Main.sbg.getContainer().getGraphics().setColor(prevColor);
        }
        return Game.getPlayerL().enterGoal(mapL) && Game.getPlayerR().enterGoal(mapR);
    }

    public void render(Graphics g) throws SlickException {
        if (R.nextInt(0, 15) == 4) Game.getGlows().get(R.nextInt(0, Game.getGlows().size() - 1)).inMotion = true;
        Game.getGlows().stream().filter(p -> p.inMotion).forEach(GlowEffect::motion);
        if (R.nextInt(0, 15) == 4) Game.getGlowsR().get(R.nextInt(0, Game.getGlowsR().size() - 1)).inMotion = true;
        Game.getGlowsR().stream().filter(p -> p.inMotion).forEach(GlowEffect::motion);
        g.setColor(mapR.getColor());
        g.fill(new Rectangle(Main.getScreenWidth()/2 - (Constants.MAP_WIDTH* Constants.TILE_SIZE),0,Main.getScreenWidth()/2,Main.getScreenHeight()));
        g.setColor(mapL.getColor());
        g.fill(new Rectangle(Main.getScreenWidth()/2,0, Main.getScreenWidth()/2 - 42,Main.getScreenHeight()));
        mapL.render(Main.getScreenWidth()/2 - Constants.MAP_WIDTH*Constants.TILE_SIZE ,0, g);
        mapR.render(Main.getScreenWidth()/2, 0, g);
//        Game.getPlayerL().tileSpecialCollisions(mapL);
//        Game.getPlayerR().tileSpecialCollisions(mapR);
//        colorMap(mapL, g);
//        colorMap(mapR, g);
    }

    public void deathRender(Graphics g) throws SlickException {
        if(Game.getPlayerL().dead() || Game.getPlayerR().dead())   {
            Game.getPlayerL().setKill(true);
            Game.getPlayerR().setKill(true);
            if (deathAnimCounter == -1) {
                deathAnimCounter = 120;
                return;
            }
            if (deathAnimCounter == 0) {
                Game.getPlayerR().revive();
                Game.getPlayerL().revive();
                loadStage(Game.curLevelID);
                deathAnimCounter = -1;
            } else {
                g.setColor(new Color(255, 255, 255, (int) ((deathAnimCounter / 120d) * 255d)));
                g.fillRect(0, 0, Main.width(), Main.height());
                deathAnimCounter--;
            }
        }
    }

    public void levelCompleteRender(Graphics g) throws SlickException {
        if (win() && !levelChange) {
            levelChangeCounter = 120;
            levelChange = true;
        }
        if(levelChange) {
            if (levelChangeCounter == 60) levelChange();
            if (levelChangeCounter == 0) {
                levelChangeCounter = -1;
                levelChange = false;
            } else {
                if (levelChangeCounter > 60) g.setColor(new Color(0, 0, 0, (int) (Math.abs(levelChangeCounter - 120) / 60d * 255d)));
                else g.setColor(new Color(0, 0, 0, (int) (((levelChangeCounter) / 60d) * 255d)));
                g.fillRect(0, 0, Main.width(), Main.height());
                levelChangeCounter--;
            }
        }
    }

    public void debugRender(Graphics g) {
        Color temp = g.getColor();
        for (Tile tile : mapL.getTileList())
            if (tile instanceof Goal) {
                g.setColor(new Color(0, 255, 255, 0.5f));
                g.fill(tile.getHitbox());
                g.setColor(temp);
            } else if(tile instanceof Hazard)  {
                g.setColor(new Color(255, 0, 0, 0.5f));
                g.fill(tile.getHitbox());
                g.setColor(temp);
            } else if(tile instanceof Ice)  {
                g.setColor(new Color(0, 0, 255, 0.5f));
                g.fill(tile.getHitbox());
                g.setColor(temp);
            } else if(tile instanceof Portal)  {
                g.setColor(new Color(0, 255, 0, 0.5f));
                g.fill(tile.getHitbox());
                g.setColor(temp);
            } else {
                g.fill(tile.getHitbox());
            }
        for (Tile t : mapR.getTileList())
            if (t instanceof Goal) {
                g.setColor(new Color(0, 255, 255, 0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            } else if(t instanceof Hazard)  {
                g.setColor(new Color(255, 0, 0, 0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            } else if(t instanceof Ice)  {
                g.setColor(new Color(0, 0, 255, 0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            } else if(t instanceof Portal)  {
                g.setColor(new Color(0, 255, 0, 0.5f));
                g.fill(t.getHitbox());
                g.setColor(temp);
            } else {
                g.fill(t.getHitbox());
            }
    }

//    public void colorMap(GameMap gm, Graphics g)  {
//        g.setColor(new Color(gm.getColor().getRed(), gm.getColor().getBlue(),gm.getColor().getGreen(),0.95f));
//        gm.getTileList().forEach(t -> {
//            if((t instanceof Ice)  ){
//                g.fill(t.getHitbox());
//            }
//        });
//    }

    private void levelChange() throws SlickException {
        Game.curLevelID++;
        loadStage(Game.curLevelID);
    }
}
