package core;

import graphics.ui.UIElement;

import java.awt.*;
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
}
