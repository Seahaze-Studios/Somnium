package core;

import org.newdawn.slick.Color;

import java.util.ArrayList;

public final class Constants {

    public static final String VERSION = "Alpha 0.1.0";

    public static final int MAP_WIDTH = 17;
    public static final int MAP_HEIGHT = 20;

    public static final int TILE_SIZE = 54;

    public static final int LEVEL_TEST_ID = 0;
    public static final ArrayList<Color> COLOR_L = new ArrayList<>() {{
        add(Color.gray);
        add(Color.white);
        add(Color.orange);
        add(Color.red);
        add(Color.pink);
        add(Color.lightGray);
        add(new Color(1f,.498f,.314f));//coral
        add(Color.magenta);
        add(new Color(.125f, .125f, 1f));
        add(Color.green);
        add(new Color(.532f, .235f, .460f));
        add(Color.darkGray);
        add(new Color(.35f, .75f, 1f));
        add(new Color(.745f, .725f, 123f));
    }};

    public static ArrayList<Color> COLOR_R = new ArrayList<>();


    public static final float PLAYER_MAX_SPEED = 5;
    public static final float SCALING_FACTOR() { return Main.config.UNLIMITED_FPS ? 60f / (float) Main.getRealFPS() : 60f / Main.config.FRAMES_PER_SECOND; }
    public static final float RES_X_FACTOR() { return Main.config.RESOLUTION[0] / 1920f; }
    public static final float RES_Y_FACTOR() { return Main.config.RESOLUTION[1] / 1080f; }

}
