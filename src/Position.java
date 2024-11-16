public class Position {
    private int pos_x;
    private int pos_y;

    public Position() {
        this.pos_x = 0;
        this.pos_y = 0;
    }

    public Position(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }

    public int Get_PosX() { return this.pos_x; }

    public int Get_PosY() { return this.pos_y; }

    public void Set_PosX (int x) { this.pos_x = x; }

    public void Set_PosY (int y) { this.pos_y = y; }
    public void setPos(int x, int y) {
        this.pos_x = x;
        this.pos_y = y;
    }
}
