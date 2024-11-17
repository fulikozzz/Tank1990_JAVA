public class Base {
    private Position pos;
    private boolean isDestroyed;

    public Base() {
        this.pos = new Position(1, 1);
        this.isDestroyed = false;
    }

    public Base(int x, int y, boolean isDestroyed) {
        this.pos = new Position(x, y);
        this.isDestroyed = isDestroyed;
    }

    public Position Get_Pos() {
        return this.pos;
    }

    public boolean Get_Is_Destroyed() {
        return this.isDestroyed;
    }

    public void Set_Pos(Position pos) {
        this.pos = pos;
    }

    public void Set_Is_Destroyed(boolean isDestroyed) {
        this.isDestroyed = isDestroyed;
    }
}
