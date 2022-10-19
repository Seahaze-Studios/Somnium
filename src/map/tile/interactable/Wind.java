package map.tile.interactable;

import entities.Direction;

public class Wind extends Interactable {
    Direction direction;
    public Wind(int x, int y, String direction) {
        super(x, y);
        this.direction = switch(direction) {
            default -> Direction.UP;
            case "down" -> Direction.DOWN;
            case "left" -> Direction.LEFT;
            case "right" -> Direction.RIGHT;
        };
    }

    public Direction getDirection() {
        return direction;
    }
}
