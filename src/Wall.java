public class Wall {
    private Position pos;
    private Type type;


    public Wall() {
        this.pos = new Position(0, 0);
        this.type = Type.EMPTY;
    }

    public Wall(int x, int y, Type type) {
        this.pos = new Position(x, y);
        this.type = type;
    }

    public Position Get_Pos() {
        return this.pos;
    }

    public Type Get_Type() {
        return this.type;
    }

    public void Set_Pos(Position pos) {
        this.pos = pos;
    }

    public void Set_Type(Type type) {
        this.type = type;
    }
}
