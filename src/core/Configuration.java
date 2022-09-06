package core;

import org.newdawn.slick.AppGameContainer;

public final class Configuration {
    public int RESOLUTION_X;
    public int RESOLUTION_Y;
    public int FRAMES_PER_SECOND;
    public GraphicsQuality GRAPHICS_QUALITY;
    public boolean VSYNC;
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

    public int[] presetFPS = {24, 30, 48, 60, 75, 120, 144, 240, 360};

    public Configuration() {}

    public void init(AppGameContainer appgc) {
        RESOLUTION_X = appgc.getScreenWidth();
        RESOLUTION_Y = appgc.getScreenHeight();
        FRAMES_PER_SECOND = 60;
        VSYNC = false;
        System.out.println(RESOLUTION_X + " " + RESOLUTION_Y +  " " + FRAMES_PER_SECOND + " " + VSYNC);
        GRAPHICS_QUALITY = GraphicsQuality.MEDIUM;
    }
}
