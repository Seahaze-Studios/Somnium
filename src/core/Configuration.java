package core;

import org.newdawn.slick.AppGameContainer;

import java.util.ArrayList;
import java.util.List;

public final class Configuration {
    public int RESOLUTION_X = 1920;
    public int RESOLUTION_Y = 1080;
    public int FRAMES_PER_SECOND = 60;
    public GraphicsQuality GRAPHICS_QUALITY = GraphicsQuality.MEDIUM;
    public boolean VSYNC = false;
    public boolean GSYNC_FREESYNC = false;
    public boolean UNLIMITED_FPS = false;
    public int GLOW_MAX = 5;
    public float GLOW_SIZE = 0.3f;
    public enum GraphicsQuality {
        LOW("Low"),
        MEDIUM("Medium"),
        HIGH("High"),
        ULTRA("Ultra");

        private String name;
        GraphicsQuality(String name) { this.name = name; }

        public String getName() {
            return name;
        }
    }

    public static List<Integer> presetFPS = new ArrayList<>() {{
        add(24); add(30); add(45); add(50); add(60); add(75); add(90); add(120); add(144); add(165); add (180); add(240); add(360); add(390);
    }};

    public Configuration() {}

    public void init(AppGameContainer appgc) {
        RESOLUTION_X = 1920;
        RESOLUTION_Y = 1080;
        FRAMES_PER_SECOND = 60;
        VSYNC = false;
        System.out.println(RESOLUTION_X + " " + RESOLUTION_Y +  " " + FRAMES_PER_SECOND + " " + VSYNC);
        GRAPHICS_QUALITY = GraphicsQuality.MEDIUM;
        UNLIMITED_FPS = false;
        GSYNC_FREESYNC = false;
        GLOW_MAX = 100;
    }
}
