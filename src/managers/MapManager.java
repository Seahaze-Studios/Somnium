package managers;

import map.GameMap;
import org.newdawn.slick.geom.Shape;

import java.util.ArrayList;

public class MapManager {
    public GameMap map1;
    public GameMap map2;
    public static ArrayList<Shape> hitbox1;
    public static ArrayList<Shape> hitbox2;

    public MapManager(GameMap m1, GameMap m2)   {
        hitbox1 = new ArrayList<>();
        hitbox2 = new ArrayList<>();
        loadMaps(m1, m2);

    }

    public void loadMaps(GameMap m1, GameMap m2)    {
        map1 = m1;
        map2 = m2;
        generateHitboxes(map1, hitbox1);
        generateHitboxes(map2, hitbox2);
    }
    private void generateHitboxes(GameMap map, ArrayList<Shape> hitboxes) {

    }
}
