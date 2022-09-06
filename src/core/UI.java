package core;

import graphics.ui.UIElement;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public final class UI {

    private Queue<UIElement> elements = new ConcurrentLinkedQueue<UIElement>();

    public UI() {

    }

    public Queue<UIElement> getElements() {
        return elements;
    }
}
