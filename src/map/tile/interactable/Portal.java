package map.tile.interactable;

public class Portal extends Interactable {

    private Portal pair;
    public void setPair(Portal portal)   {
        this.pair = portal;
    }

    public Portal getPair() {
        return pair;
    }
    public Portal(int x, int y) {
        super(x, y);
    }
}
