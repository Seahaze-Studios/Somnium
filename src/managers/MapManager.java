package managers;

import core.Constants;
import core.Main;
import map.GameMap;
import map.tile.Tile;
import map.tile.obstacle.Block;
import map.tile.obstacle.Obstacle;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class MapManager {
    public static GameMap mapL;
    public static GameMap mapR;



    public MapManager(GameMap m1, GameMap m2)   {
        loadMaps(m1, m2);

    }

    public MapManager(int id) throws SlickException {
        loadMaps(new GameMap("res/maps/temp1.tmx"), new GameMap("res/maps/temp2.tmx"));

    }

    public void loadMaps(GameMap m1, GameMap m2)    {
        mapL = m1;
        mapR = m2;
        generateHitboxes(mapL);
        generateHitboxes(mapR);
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
            if(map.getTileProperty(map.getTileId(i,j,0), "type", "false").equals("true"))  {
                map.getTileList().add(new Block(i * Constants.TILE_SIZE, j * Constants.TILE_SIZE));
            }
        }
    }

    private void generatePlayer(GameMap map)   {

    }

    public void render(Graphics g)    {
        mapL.render(0,0);
        mapR.render(Main.getScreenWidth()/2, 0);
    }
}
