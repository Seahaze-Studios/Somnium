package core;

import org.newdawn.slick.Color;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.InkscapeLoader;
import org.newdawn.slick.svg.SimpleDiagramRenderer;

import java.util.ArrayList;

public final class Constants {

    public static final String VERSION = "Alpha 0.1.0";

    public static final int MAP_WIDTH = 17;
    public static final int MAP_HEIGHT = 20;

    public static final int TILE_SIZE = 54;

    public static final int LEVEL_TEST_ID = 0;
    public static final int LEVEL_1_ID = 1;
    public static final int LEVEL_2_ID = 2;
    public static final int LEVEL_3_ID = 3;
    public static final int LEVEL_4_ID = 4;
    public static final int LEVEL_5_ID = 5;
    public static final int LEVEL_6_ID = 6;
    public static final int LEVEL_7_ID = 7;
    public static final ArrayList<Color> COLOR_L = new ArrayList<>()    {{
        add(Color.gray);
        add(Color.white);
        add(Color.orange);
        add(Color.red);
        add(Color.pink);
        add(Color.lightGray);
        add(new Color(1f,.498f,.314f));//coral
        add(Color.magenta);
        add(Color.cyan);
        add(Color.green);
        add(Color.blue);
        add(Color.darkGray);
        add(Color.yellow);
    }};
    /*public static final ArrayList<Color> COLOR_R = new ArrayList<>()    {{
        add(Color.cyan);
        add(Color.black);
        add(Color.blue);
        add(Color.green);
        add(new Color(143,0,255));//violet
        add(Color.darkGray);
        add(Color.pink);
        add(new Color(48,213,200));//Turquoise
        add(Color.cyan);
    }}*/

    public static ArrayList<Color> COLOR_R = new ArrayList<>();

//    public static final SimpleDiagramRenderer SVG_L;
//    public static final SimpleDiagramRenderer SVG_R;
//
//    static {
//        try {
//             SVG_L = new SimpleDiagramRenderer(InkscapeLoader.load("res/player/face1.svg"));
//            SVG_R = new SimpleDiagramRenderer(InkscapeLoader.load("res/player/face2.svg"));
//        } catch (SlickException e) {
//            throw new RuntimeException(e);
//        }
//    }


    // List of Level IDs - index is level, element is ID.
    //public static final ArrayList<Integer> levelIDs = new ArrayList<>() {{
    //   add(0);
    //   add(1);
    //   add
    //}};


    public static final float PLAYER_MAX_SPEED = 5;
    public static final float SCALING_FACTOR() { return Main.config.UNLIMITED_FPS ? 60f / (float) Main.getRealFPS() : 60f / Main.config.FRAMES_PER_SECOND; }
    public static final float RES_X_FACTOR() { return Main.config.RESOLUTION[0] / 1920f; }
    public static final float RES_Y_FACTOR() { return Main.config.RESOLUTION[1] / 1080f; }

}
