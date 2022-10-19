package graphics.ui.tabber;

import core.Main;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.RoundedRectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static core.Constants.RES_X_FACTOR;
import static core.Constants.RES_Y_FACTOR;

public class Tabber {
    private Rectangle box;
    private ArrayList<Tab> tabs;

    public Tabber(Tab... tabs) {
        this.tabs = new ArrayList<>(List.of(tabs)); // First tab: tleft rounded, all 3 others straight - last tab: bleft rounded
        this.box = new RoundedRectangle(0, 0, Main.width() - 100 * RES_X_FACTOR(), Main.height() - 100 * RES_Y_FACTOR(), 20);
        box.setCenterX(Main.width() / 2);
        box.setCenterY(Main.height() / 2);
    }

    public void update(GameContainer gc) {
        var active = tabs.stream().filter(Tab::isActive).collect(Collectors.toList());
        for (Tab t : tabs) {
            t.update(gc);
            if (t.mouseOver(gc) && gc.getInput().isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
                active.forEach(tt -> tt.setActive(false));
                t.setActive(true);
            }
        }
        //tabs.forEach(t -> t.update(gc));
        //if (tabs.stream().filter(Tab::isActive).count() > 1) tabs.stream().filter(Tab::isActive).findFirst().ifPresent(t -> t.setActive(false)); // No reuse of consumed Stream<>
    }

    public void render(GameContainer gc) {
        gc.getGraphics().setColor(Color.white);
        gc.getGraphics().draw(box);
        tabs.forEach(t -> {
            t.render(gc);
            if (t.isActive()) {
                t.getBody().render(gc, box.getX() + tabs.get(0).getBox().getWidth(), box.getY(), box.getWidth() - tabs.get(0).getBox().getWidth(), box.getHeight());
            }
        });
    }
}
