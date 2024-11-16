public class Wall {
    private Position pos;
    private Type type;



    // Конструктор по умолчанию
    public Wall() {
        this.pos = new Position(0, 0);  // Инициализация позиции по умолчанию
        this.type = Type.EMPTY;  // Тип по умолчанию
    }

    // Конструктор с параметрами
    public Wall(int x, int y, Type type) {
        this.pos = new Position(x, y);  // Устанавливаем позицию
        this.type = type;  // Устанавливаем тип стены
    }

    // Геттеры и Сеттеры
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
