package core;

import java.util.ArrayList;

public final class Constants {

    public static final String VERSION = "Alpha 0.1.0";

    public static final int MAP_WIDTH = 15;
    public static final int MAP_HEIGHT = 20;

    public static final int TILE_SIZE = 54;

    public static final int LEVEL_TEST_ID = -1;
    public static final int LEVEL_1_ID = 1;
    public static final int LEVEL_2_ID = 2;

    // List of Level IDs - index is level, element is ID.
    //public static final ArrayList<Integer> levelIDs = new ArrayList<>() {{
    //   add(0);
    //   add(1);
    //   add
    //}};


    public static final float PLAYER_MAX_SPEED = 5;
    public static final float SCALING_FACTOR() { return Main.config.UNLIMITED_FPS ? 60f / Main.getRealFPS() : 60f / Main.config.FRAMES_PER_SECOND; }

}
