// Вспомогательный класс для хранения данных о стене
public class WallInfo {
    private final Type type;
    private final Position position;

    public WallInfo(Type type, Position position) {
        this.type = type;
        this.position = position;
    }

    public Type getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }
}
