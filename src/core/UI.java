package core;

import graphics.ui.UIElement;
import graphics.ui.menu.Menu;
import org.newdawn.slick.GameContainer;

import java.util.AbstractQueue;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class UI {

    public AbstractQueue<UIElement> elements = new ConcurrentLinkedQueue<>();
    public AbstractQueue<Menu> menus = new ConcurrentLinkedQueue<>();

    public UI() {
    }

    public AbstractQueue<Menu> getMenus() {
        return menus;
    }

    public Queue<UIElement> getElements() {
        return elements;
    }

    public void render(GameContainer gc) {
        this.getElements().forEach(m -> {
            m.update(gc);
            m.render(gc);
        });
        this.getMenus().forEach(m -> {
            m.update(gc);
            m.render(gc);
        });
    }
}
