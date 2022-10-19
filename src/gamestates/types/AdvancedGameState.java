package gamestates.types;

import core.Main;
import gamestates.Game;
import graphics.ui.button.CloseButton;
import graphics.ui.menu.DialogBox;
import org.newdawn.slick.*;
import org.newdawn.slick.imageout.ImageOut;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

public abstract class AdvancedGameState extends BasicGameState {
    protected long timestamp;

    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
        Main.getAppgc().setDisplayMode(Main.config.RESOLUTION[0], Main.config.RESOLUTION[1], false);
        frameRate(gc, sbg);

    }

    private void frameRate(GameContainer gc, StateBasedGame sbg) {
        Main.getAppgc().setTargetFrameRate(Main.config.UNLIMITED_FPS ? Integer.MAX_VALUE : Main.config.FRAMES_PER_SECOND);
        gc.setTargetFrameRate(Main.config.UNLIMITED_FPS ? Integer.MAX_VALUE : Main.config.FRAMES_PER_SECOND);
        sbg.getContainer().setTargetFrameRate(Main.config.UNLIMITED_FPS ? Integer.MAX_VALUE : Main.config.FRAMES_PER_SECOND);
    }

    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
        //Main.setRealFPS((int) (1 / ((System.nanoTime() - timestamp) / 1000000000d)));
        Main.setRealFPS((int) (1 / (((double) delta <= 0 ? 1d : (double) delta) / 1000d)));
        timestamp = System.nanoTime();
    }

    @Override
    public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
        Main.getAppgc().setDisplayMode(Main.config.RESOLUTION[0], Main.config.RESOLUTION[1], false);
        frameRate(gc, sbg);
    }

    public void debugRender(GameContainer gc){
    }

    @Override
    public void keyPressed(int key, char c) {
        if (key == Input.KEY_F2) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH.mm.ss");
            String dateAndTime = dateFormat.format(new Date());
            String filename = "screenshots/" + dateAndTime + ".png";
            File file = new File(filename);
            file.getParentFile().mkdirs();

            try {
                Image target = new Image(gamestates.Game.getGc().getWidth(), gamestates.Game.getGc().getHeight());
                Game.getGc().getGraphics().copyArea(target, 0, 0);
                ImageOut.write(target, filename);
                target.destroy();
            } catch (SlickException e) {
                throw new RuntimeException(e);
            }

            Main.UI.menus.add(new DialogBox(700, 400, "Screenshot", "Screenshot saved in screenshots/ folder.", new CloseButton("Got it")));
        }
        if (key == Input.KEY_F3) {
            Main.debug = !Main.debug;

        }
//        if (key == Input.KEY_ESCAPE && !Main.paused) {
//            Main.menus.add(new PauseMenu());
//            Main.paused = true;
//        }
    }
}
